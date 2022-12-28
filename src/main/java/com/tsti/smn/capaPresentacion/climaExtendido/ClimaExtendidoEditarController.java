package com.tsti.smn.capaPresentacion.climaExtendido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsti.smn.capaServicios.CiudadService;
import com.tsti.smn.capaServicios.ClimaExtendidoService;
import com.tsti.smn.capaServicios.ProvinciaService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.ClimaExtendido;
import com.tsti.smn.pojos.Provincia;

@Controller
@RequestMapping("/climaExtendidoEditar")
public class ClimaExtendidoEditarController {
	@Autowired
    private ClimaExtendidoService servicioClimaExtendido;
	@Autowired
    private CiudadService servicioCiudad;
     
    @RequestMapping(path = {"","/{id}","/{id}/{fecha}"},method=RequestMethod.GET)
    public String preparaForm(Model modelo, @PathVariable("id") Long id, @PathVariable("fecha") Optional<String> fecha) throws Exception {
    	if (fecha.isPresent()) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate fecha_conv = LocalDate.parse(fecha.get(), formatter);
    		
    		ClimaExtendido entity = servicioClimaExtendido.getByFechaAndIdCiudad(fecha_conv, id);
    		ClimaExtendidoForm form = new ClimaExtendidoForm(entity);
			modelo.addAttribute("formBean", form);
		} else {
	        modelo.addAttribute("formBean",new ClimaExtendidoForm());
		}
       return "climaExtendidoEditar";
    }
     
    @ModelAttribute("allClimasExtendidos")
    public List<ClimaExtendido> getAllClimaExtendido() {
        return this.servicioClimaExtendido.getAll();
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") @Valid ClimaExtendidoForm formBean, BindingResult result, ModelMap modelo, @RequestParam String action) {
    	
    	if(action.equals("Aceptar")) {
    		System.out.println(formBean.getFecha());
    		if(result.hasErrors()) {
    			System.out.print(result.getAllErrors());
    			modelo.addAttribute("formBean", formBean);
    			return "redirect:/";
    			 
    		} else {
				ClimaExtendido ce=formBean.toPojo();
				ce.setCiudad(servicioCiudad.getById(formBean.getIdCiudad()));
				
				if(servicioClimaExtendido.getByFechaAndIdCiudad(formBean.getFecha(), formBean.getIdCiudad()) != null) {
					ce.setId(servicioClimaExtendido.getByFechaAndIdCiudad(formBean.getFecha(), formBean.getIdCiudad()).getId());
				}
					
					try {
	    				servicioClimaExtendido.save(ce);
						return "redirect:/";
						
					} catch (Excepcion e) {

						if(e.getAtributo()==null){
							ObjectError error = new ObjectError("globalError", e.getMessage());
				            result.addError(error);
						} else {
				    		FieldError error1 = new FieldError("formBean", e.getAtributo(), e.getMessage());
				            result.addError(error1); 
						}
						
			            modelo.addAttribute("formBean",formBean);
			            return "redirect:/";  
					}
				}
    		}
       	
    	if(action.equals("Cancelar")) {
    		
    		modelo.clear();
    		return "redirect:/";
    		
    	}
    		
    	return "redirect:/";
    	
    }
 
}