package com.tsti.smn.capaPresentacion.ciudades;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsti.smn.capaServicios.CiudadService;
import com.tsti.smn.capaServicios.ProvinciaService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.Provincia;


@Controller
@RequestMapping("/ciudadEditar")
public class CiudadEditarController {
	@Autowired
    private CiudadService servicioCiudad;
	@Autowired
    private ProvinciaService servicioProvincia;
     
    @RequestMapping(path = {"", "/{id}"},method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("id") Optional<Long> dni) throws Exception {
    	if (dni.isPresent()) {
    		Ciudad entity = servicioCiudad.getById(dni.get());
    		CiudadForm form = new CiudadForm(entity);
			modelo.addAttribute("formBean", form);
		} else {
 
	       modelo.addAttribute("formBean",new CiudadForm());
		}
       return "ciudadEditar";
    }
     
    @ModelAttribute("allProvincias")
    public List<Provincia> getAllProvincias() {
        return this.servicioProvincia.getAll();
    }
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deleteById(Model model, @PathVariable("id") Long id) 
	{
		servicioCiudad.deleteByid(id);
		return "redirect:/ciudadesBuscar";
	}
 
    
    @RequestMapping( method=RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") @Valid  CiudadForm formBean,BindingResult result, ModelMap modelo,@RequestParam String action) {
    	
    	
    	if(action.equals("Aceptar"))
    	{
    		if(result.hasErrors())
    		{
    			
                
    			modelo.addAttribute("formBean",formBean);
    			 return "ciudadEditar";
    		}
    		else
    		{
    			try {
					Ciudad p=formBean.toPojo();
					p.setProvincia(servicioProvincia.getById(formBean.getIdProvincia()));
					servicioCiudad.save(p);
					
					return "redirect:/ciudadesBuscar";
				} catch (Excepcion e) {
					if(e.getAtributo()==null) //si la excepcion estuviera referida a un atributo del objeto, entonces mostrarlo al lado del compornente (ej. dni)
					{
						ObjectError error = new ObjectError("globalError", e.getMessage());
			            result.addError(error);
					}
					else
					{
			    		FieldError error1 = new FieldError("formBean",e.getAtributo(),e.getMessage());
			            result.addError(error1);

					}
		            modelo.addAttribute("formBean",formBean);
	    			return "ciudadEditar";  //Como existe un error me quedo en la misma pantalla
				}
    		}
    	}
    
    	
    	if(action.equals("Cancelar"))
    	{
    		modelo.clear();
    		return "redirect:/ciudadesBuscar";
    	}
    		
    	return "redirect:/";
    	
    	
    }

 
}
