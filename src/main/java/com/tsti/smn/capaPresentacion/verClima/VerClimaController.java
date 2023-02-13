package com.tsti.smn.capaPresentacion.verClima;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsti.smn.capaPresentacion.climaExtendido.ClimaExtendidoBuscarForm;
import com.tsti.smn.capaPresentacion.personas.PersonasBuscarForm;
import com.tsti.smn.capaServicios.CiudadService;
import com.tsti.smn.capaServicios.ClimaExtendidoService;
import com.tsti.smn.capaServicios.ClimaService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaActual;
import com.tsti.smn.pojos.ClimaExtendido;
import com.tsti.smn.pojos.Persona;

@Controller
@RequestMapping("/verClima")
public class VerClimaController {
	@Autowired
    private ClimaService servicioClima;
	
	@Autowired
    private ClimaExtendidoService servicioClimaExtendido;
   
	@Autowired
    private CiudadService servicioCiudad;
   
	
    @RequestMapping(path = {""}, method=RequestMethod.GET)
    public String preparaForm(Model modelo) {
    	VerClimaForm form =  new VerClimaForm();
    	modelo.addAttribute("formBean",form);
    	return "verClima";
    	
    }
     
    @ModelAttribute("allCiudades")
    public List<Ciudad> getAllCiudades() {
        return this.servicioCiudad.getAll();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String submit( @ModelAttribute("formBean") @Valid VerClimaForm formBean, BindingResult result, ModelMap modelo, @RequestParam String action) throws Excepcion {
    	
    	if(action.equals("Aceptar"))
    	{

    		try {
    			ClimaActual climaActual = servicioClima.findByIdCiudad(formBean.getId());
    			List<ClimaExtendido> climasExtendidos = servicioClimaExtendido.filter2(formBean);
    			modelo.addAttribute("resultados_climaActual", climaActual);
    			modelo.addAttribute("resultados_climaExtendido", climasExtendidos);
			} catch (Exception e) {
				ObjectError error = new ObjectError("globalError", e.getMessage());
	            result.addError(error);
			}
    		
    		modelo.addAttribute("formBean",formBean);
        	return "verClima";
    	}
    
    	
    	if(action.equals("Cancelar"))
    	{
    		modelo.clear();
    		return "redirect:/";
    	}
    	
    	return "redirect:/";
    	
    }

}