package com.tsti.smn.capaPresentacion.evento;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsti.smn.capaServicios.CiudadService;
import com.tsti.smn.capaServicios.EventoService;
import com.tsti.smn.excepciones.Excepcion;
import com.tsti.smn.pojos.Ciudad;
import com.tsti.smn.pojos.Evento;



@Controller
@RequestMapping("/eventoEditar")
public class EventoEditarController {
	@Autowired
	private EventoService service;
	@Autowired
	private CiudadService serviceCiudad;
	private LocalDate hoy = LocalDate.now();
	private LocalDate maniana = hoy.plusDays(1);
	
	
	@RequestMapping(method=RequestMethod.GET)
    public String preparaForm(Model modelo) throws Exception {
		
		
		modelo.addAttribute("hoy", hoy);
		modelo.addAttribute("maniana", maniana);
		modelo.addAttribute("formBean",new EventoForm());
		return "eventoEditar";
    }
	
	 @ModelAttribute("allCiudades")
	 public List<Ciudad> getAllCiudades() {
		 return this.serviceCiudad.getAll();
	 }
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("formBean") @Valid EventoForm formBean,BindingResult result, ModelMap modelo,@RequestParam String action) throws Exception  {
	    	
		if(action.equals("Aceptar")) {
			
			
			if (result.hasErrors()) {
				modelo.addAttribute("hoy", hoy);
				modelo.addAttribute("maniana", maniana);
				modelo.addAttribute("formBean", formBean);
				return "eventoEditar";
				
			} else {
				
					Evento e = formBean.toPojo();
	    			e.setCiudad(serviceCiudad.getById(formBean.getIdCiudad()));
	    			try {
						service.save(e);
						String mensaje = "Se ha registrado un nuevo evento y se ha enviado por mail";
						modelo.addAttribute("confirmacion", mensaje);
						return "eventoConfirmar";
					} catch (Excepcion ex) {
						if(ex.getAtributo()==null) //si la excepcion estuviera referida a un atributo del objeto, entonces mostrarlo al lado del compornente (ej. dni)
						{
							ObjectError error = new ObjectError("globalError", ex.getMessage());
				            result.addError(error);
						}
						else
						{
				    		FieldError error1 = new FieldError("formBean",ex.getAtributo(),ex.getMessage());
				            result.addError(error1);

						}
						modelo.addAttribute("hoy", hoy);
						modelo.addAttribute("maniana", maniana);	            
			            modelo.addAttribute("formBean",formBean);
		    			return "eventoEditar";//Como existe un error me quedo en la misma pantalla
					}
	    		}
			
			}
	    	
	    	if(action.equals("Cancelar"))
	    	{
	    		modelo.clear();
	    		return "redirect:/";
	    	}
	    		
	    	return "redirect:/";
	    	
	    	
	    }

}
