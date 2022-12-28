package com.tsti.smn.capaPresentacion.climaExtendido;

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

import com.tsti.smn.capaPresentacion.personas.PersonasBuscarForm;
import com.tsti.smn.capaServicios.CiudadService;
import com.tsti.smn.capaServicios.ClimaExtendidoService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaExtendido;
import com.tsti.smn.pojos.Persona;

@Controller
@RequestMapping("/climaExtendidoBuscar")
public class ClimaExtendidoBuscarController {
	@Autowired
    private ClimaExtendidoService servicioClimaExtendido;
   
	@Autowired
    private CiudadService servicioCiudad;
   
	
    @RequestMapping(path = {"","/{idCiudad}"}, method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("idCiudad") Long idCiudad) {
    	ClimaExtendidoBuscarForm form =  new ClimaExtendidoBuscarForm();
    	modelo.addAttribute("formBean",form);
    	return "climaExtendidoBuscar";
    }
     
    @ModelAttribute("allCiudades")
    public List<Ciudad> getAllCiudades() {
        return this.servicioCiudad.getAll();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String submit( @ModelAttribute("formBean") @Valid ClimaExtendidoBuscarForm formBean, BindingResult result, ModelMap modelo, @RequestParam String action) throws Excepcion {
    	
    	if(action.equals("Buscar"))
    	{

    		try {
    			List<ClimaExtendido> climasExtendidos = servicioClimaExtendido.filter(formBean);
    			modelo.addAttribute("resultados", climasExtendidos);
			} catch (Exception e) {
				ObjectError error = new ObjectError("globalError", e.getMessage());
	            result.addError(error);
			}
    		
    		modelo.addAttribute("formBean",formBean);
        	return "climaExtendidoBuscar";
    	}
    
    	
    	if(action.equals("Cancelar"))
    	{
    		modelo.clear();
    		return "redirect:/ciudadesBuscar";
    	}
    	
    	if(action.equals("Registrar"))
    	{
    		modelo.clear();
    		return "redirect:/climaExtendidoEditar";
    	}
    		
    	return "redirect:/";
    	
    }

 
}