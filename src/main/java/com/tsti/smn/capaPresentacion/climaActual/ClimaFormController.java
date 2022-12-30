package com.tsti.smn.capaPresentacion.climaActual;

import java.util.List;

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
import com.tsti.smn.capaServicios.ClimaService;
import com.tsti.smn.capaServicios.EstadoService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.ClimaActual;
import com.tsti.smn.pojos.Estado;
import com.tsti.smn.pojos.Provincia;

@Controller
@RequestMapping("/ciudadEditarClima")
public class ClimaFormController {
	@Autowired
	private ClimaService servicioClima;
	@Autowired
	private CiudadService servicioCiudad;
	@Autowired
	private EstadoService servicioEstado;
	
	@RequestMapping(path = {"", "/{id}"},method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("id") Long id) throws Exception {
    	
		if(servicioClima.findByIdCiudad(id)!=null) {
    		ClimaActual entity = servicioClima.findByIdCiudad(id);//busca el clima a partir del id ciudad?
    		ClimaForm form = new ClimaForm(entity);// genera el form a partir de los datos de ese clima?
			modelo.addAttribute("formBean", form); //lo pasa como atributo del modelo??
		}
	else {
		 
	       modelo.addAttribute("formBean",new ClimaForm());
		}
       return "ciudadEditarClima"; //vuelve al html, con los datos cargados?
    }
	
	@ModelAttribute("allEstados")
	public List<Estado> getAllEstados() {
        return this.servicioEstado.getAll();
    }
	

	@RequestMapping(method=RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") @Valid ClimaForm formBean,BindingResult result, 
    		ModelMap modelo,@RequestParam String action) {
    	    	
    	if(action.equals("Aceptar"))
    	{
    		if(result.hasErrors())
    		{		
    			modelo.addAttribute("formBean",formBean);
    			return "ciudadEditarClima";
    		}
    		else
    		{
    			ClimaActual p=formBean.toPojo();
				p.setCiudad(servicioCiudad.getById(formBean.getIdCiudad()));
				p.setEstado(servicioEstado.getById(formBean.getIdEstado()));
				
				servicioClima.save(p);
				
				return "redirect:/ciudadesBuscar";
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
