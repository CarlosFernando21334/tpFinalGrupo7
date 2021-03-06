package ar.edu.unju.fi.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ciudadano;
import ar.edu.unju.fi.service.ICiudadanoService;
import ar.edu.unju.fi.service.ICursoService;
import ar.edu.unju.fi.service.IOfertaService;
import ar.edu.unju.fi.service.IPostuladoService;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.service.util.Categoria;
import ar.edu.unju.fi.service.util.Provincia;

@Controller
@RequestMapping("/ciudadano")
public class CiudadanoController {
	private static final Log LOGGER = LogFactory.getLog(CiudadanoController.class);
	@Autowired
	ICiudadanoService ciudadanoService;
	@Autowired
	IOfertaService ofertaService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	private Provincia listaProvincia;
	@Autowired
	IPostuladoService postuladoService;
	@Autowired
	private Categoria categoriaLista;
	@Autowired
	ICursoService cursoService;



//creacion de nuevo ciudadano
@GetMapping("/nuevo")
public ModelAndView nuevociudadano(Model model) {
	ModelAndView mav = new ModelAndView("alta_ciudadano");
	
	mav.addObject("ciudadano", ciudadanoService.getCiudadano());
	
	mav.addObject("lista", listaProvincia.getLista());
	
	
	return mav;
}
// guardando nuevo ciudadano
@PostMapping("/guardar")
public ModelAndView guardarciudadano(@Validated @ModelAttribute("ciudadano")Ciudadano ciudadano, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
    	ModelAndView mav = new ModelAndView("alta_ciudadano");
    	mav.addObject("lista", listaProvincia.getLista());
    	mav.addObject("ciudadano", ciudadano);
    	LOGGER.info("error de validacion");
    	return mav;
    }
    //verifica edad
    if(ciudadano.getEdad()>=18)
    {
    	ciudadanoService.guardarCiudadano(ciudadano);
    	LOGGER.info("ciudadano guardado");
    	ModelAndView mav = new ModelAndView("redirect:/login");
    	return mav;
    }else
    {
    	ModelAndView mav = new ModelAndView("menor_edad");
    	return mav;
    }
	
	

}
// pagina principal del ciudadano
@GetMapping("/home")
public ModelAndView homeciudadano(Model model,Principal principal) {

	ModelAndView mav = new ModelAndView("home_ciudadano");
	mav.addObject("lista", ofertaService.getListaOferta());
	mav.addObject("listaProvincia", listaProvincia.getLista());
	//System.out.println(principal.getClass);
	
	return mav;
	
}


@GetMapping("/login")
public ModelAndView loginc() {
	ModelAndView mav = new ModelAndView("login_ciudadano");
	mav.addObject("ciudadano", ciudadanoService.getCiudadano());
return mav;	
}

//filtrado por provincia
@GetMapping("/filtrar/{pro}")
public ModelAndView filtrarProvincia(@PathVariable(name="pro")String pro)
{
	ModelAndView mav = new ModelAndView("home_ciudadano");
	mav.addObject("lista", ofertaService.getListaFiltroProvincia(pro));
	mav.addObject("listaProvincia", listaProvincia.getLista());
	return mav;
}

//control de postulaciones
@GetMapping("/postulaciones")
public ModelAndView verPostulaciones(Principal prin)
{
	ModelAndView mav = new ModelAndView("postulados_ciudadano");
	mav.addObject("lista", postuladoService.buscarPostulacionCiudadano(Long.parseLong(prin.getName())));
	return mav;
}

@GetMapping("/contrataciones")
public ModelAndView verContrataciones(Principal prin)
{
	ModelAndView mav = new ModelAndView("ofertas_contratado");
	mav.addObject("lista", ciudadanoService.buscarOfertasContratado(Long.parseLong(prin.getName())));
	return mav;
}
@GetMapping("/inscripciones")
public ModelAndView verInscripciones(Principal prin)
{
	ModelAndView mav = new ModelAndView("cursos_inscriptos");
	mav.addObject("lista", ciudadanoService.getListaIncriptas(Long.parseLong(prin.getName())));
	return mav;
}
//filtrado por categ
@GetMapping("/categoria/{cat}")
public ModelAndView filtrarCategoria(@PathVariable(name="cat")String cat)
{
	ModelAndView mav = new ModelAndView("lista_curso");
	mav.addObject("categorias", categoriaLista.getLista() );
	mav.addObject("lista", cursoService.getListaCategoria(cat));
	return mav;
}



}