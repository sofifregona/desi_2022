package com.tsti.smn.capaPresentacion.personas;

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
import com.tsti.smn.capaServicios.PersonaService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.Persona;


@Controller
@RequestMapping("/personasEditar")
public class PersonasEditarController {
	@Autowired
    private PersonaService service;
	@Autowired
    private CiudadService serviceCiudad;
     
    @RequestMapping(path = {"", "/{id}"},method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("id") Optional<Long> dni) throws Exception {
    	if (dni.isPresent()) {
    		Persona entity = service.getPersonaById(dni.get());
    		PersonaForm form = new PersonaForm(entity);
			modelo.addAttribute("formBean", form);
		} else {
 
	       modelo.addAttribute("formBean",new PersonaForm());
		}
       return "personasEditar";
    }
     
    @ModelAttribute("allCiudades")
    public List<Ciudad> getAllCiudades() {
        return this.serviceCiudad.getAll();
    }
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String deletePersonaById(Model model, @PathVariable("id") Long id) 
	{
		service.deletePersonaByid(id);
		return "redirect:/personasBuscar";
	}
 
    
    @RequestMapping( method=RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") @Valid PersonaForm formBean,BindingResult result, ModelMap modelo,@RequestParam String action) throws Exception  {
    	
    	
    	if(action.equals("Aceptar"))
    	{
    		//para poner errores personalizados asociados a
//            FieldError error2 = new FieldError("formBean","dni","este es otro error.");
//            result.addError(error2);
//    		ObjectError error = new ObjectError("globalError", "aplicacion en modo demo, no puede continuar");
//            result.addError(error);
            
    		if(result.hasErrors())
    		{
    			
                
    			modelo.addAttribute("formBean",formBean);
    			 return "personasEditar";
    		}
    		else
    		{
    			Persona p=formBean.toPojo();
    			p.setCiudad(serviceCiudad.getById(formBean.getIdCiudad()));
    			try {
					service.save(p);
					
					return "redirect:/personasBuscar";
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
	    			return "personasEditar";//Como existe un error me quedo en la misma pantalla
				}
    		}

    		
        	
        	
    	}
    
    	
    	if(action.equals("Cancelar"))
    	{
    		modelo.clear();
    		return "redirect:/personasBuscar";
    	}
    		
    	return "redirect:/";
    	
    	
    }


 
}
