package cl.triggers.security;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cl.triggers.security.model.Capacitacion;
import cl.triggers.security.repositorio.ICapacitacionRepositorio;


@Controller
public class IndexTriggerController {
	
	@Autowired
	ICapacitacionRepositorio capaRepositorio;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexTriggerController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		 
		
		return "index";
	}
	
	
	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public String contact() {
		
		
		return "contacto";
	}
	

	@RequestMapping(value="/contacto", method = RequestMethod.POST)
	public void contact(@RequestParam("nombre") String nombre, @RequestParam("email") String email, 
			@RequestParam("telefono") int telefono, @RequestParam("mensaje") String mensaje) {
		
		LOGGER.info("Informacion de posible contacto: " + "\nNombre: " +
				nombre + "\nemail: " + email + "\nTelefono: " + telefono + "\nMensaje: "
				+mensaje);
		
		
		
	}
	

	
	@RequestMapping(value="/listarcapacitacion", method = RequestMethod.GET)
	public String inicio(Model model) {
		
		List<Capacitacion> capa = capaRepositorio.findAll();
		
		model.addAttribute("capa", capa);
		
		
		LOGGER.info(" Metodo listar capacitacion exitoso");
		
		return "listarcapacitaciones";
		
	}
	
	@RequestMapping(value="/crearcapacitacion", method = RequestMethod.GET)
	public String capa () {
		
		return "crearcapacitacion";
		
	}
	
	@RequestMapping(value = "/crearcapacitacion", method = RequestMethod.POST)
	public String capa (  @RequestParam("idCapacitacion") int idCapacitacion, @RequestParam("rutCliente") int rutCliente,
		@RequestParam("diaCapacitacion") String diaCapacitacion, @RequestParam("horaCapacitacion") String horaCapacitacion, 
		@RequestParam("lugarCapacitacion") String lugarCapacitacion, @RequestParam("duracionCapacitacion") String duracionCapacitacion,
		@RequestParam ("cantidadAsistentes") int cantidadAsistentes){
		
				
		Capacitacion capa = new Capacitacion(idCapacitacion, rutCliente, diaCapacitacion, horaCapacitacion, lugarCapacitacion, duracionCapacitacion, cantidadAsistentes);
		
		capaRepositorio.save(capa);
		
		LOGGER.info(" Metodo crear capacitacion exitoso: " + capa.toString());

		return "redirect:/listarcapacitacion";
	}
	
	/*
	 	
	 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		 
		
		return "index";
	}
	
	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public String contact() {
		
		
		return "contacto";
	}
	
	@RequestMapping(value="/crearcapacitacion", method = RequestMethod.GET)
	public String capa () {
		
		return "crearcapacitacion";
		
	}
	
	@RequestMapping(value = "/crearcapacitacion", method = RequestMethod.POST)
	public String capa (  @RequestParam("idCapacitacion") int idCapacitacion, @RequestParam("rutCliente") int rutCliente,
		@RequestParam("diaCapacitacion") String diaCapacitacion, @RequestParam("horaCapacitacion") String horaCapacitacion, 
		@RequestParam("lugarCapacitacion") String lugarCapacitacion, @RequestParam("duracionCapacitacion") String duracionCapacitacion,
		@RequestParam ("cantidadAsistentes") int cantidadAsistentes){
		
				
		Capacitacion capa = new Capacitacion(idCapacitacion, rutCliente, diaCapacitacion, horaCapacitacion, lugarCapacitacion, duracionCapacitacion, cantidadAsistentes);
		
		cDao.crearCapacitacion(capa);
		
		LOGGER.info(" Metodo crear capacitacion exitoso: " + capa.toString());

		return "listarcapacitaciones";
	}
	
	@RequestMapping(value = "/listarcapacitacion", method = RequestMethod.GET)
	public String listarcapa(Model modelo) {
		
		
		List<Capacitacion> lista = cDao.obtenerCapacitaciones();
		
		modelo.addAttribute("capacitaciones", lista);
		
		LOGGER.info(" Metodo listar capacitacion exitoso: " + lista);
			
		return "listarcapacitaciones";
	}
	
	@RequestMapping(value = "/crearusuario", method = RequestMethod.GET)
	public String crearUsuario(Model modelo) {
		
		return "crearusuario";
	}
	  
	  
	 */
	
}
