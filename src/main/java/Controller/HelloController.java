package Controller;

import DTO.ClientDTO;
import Entity.ClientEntity;
import Entity.UserEntity;
import Service.*;
import Validator.ClientValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import java.util.Collection;

@Controller
public class HelloController {

	private static final int ENABLED = 1;

	@Autowired
	private ClientService serviceClient;
	@Autowired
	private UserService serviceUser;

	@Autowired
	private ClientValidator clientValidator;

	private static final Logger LOGGER = Logger.getLogger(HelloController.class);

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if(error!=null){
			model.addObject("error", "Не верно введен логин или пароль");
		}
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied(Principal user) {

		ModelAndView model = new ModelAndView();
		model.setViewName("accessDenied");
		return model;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String redirectByAuth() {
		Collection auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String role = auth.iterator().next().toString();
		LOGGER.info("User entered by " + role);

		if(role.contains("USER")){
			return "redirect:/client";
		}
		if(role.contains("ADMIN")){
			return "redirect:/admin";
		}
		if(role.contains("WEB")){
			return "redirect:/employee/Web";
		}
		if(role.contains("QAD")){
			return "redirect:/employee/QA";
		}
		if(role.contains("SAP")){
			return "redirect:/employee/SAP";
		}
		if(role.contains("SYSTEM")){
			return "redirect:/employee/Sys";
		}

		return "redirect:/index";
	}

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String registration(ModelMap model) {

		model.addAttribute("clientNew", new ClientDTO());
		return "registration";
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String regClient(@ModelAttribute("clientNew") ClientDTO cl, BindingResult br,
							ModelMap model) {

		clientValidator.validate(cl, br);
		model.addAttribute("clientNew", cl);

		if(br.hasErrors()) {
			return "registration";
		}
		else {
			ClientEntity newClient = new ClientEntity();
			newClient.setName(cl.getName());
			newClient.setLastname(cl.getLastname());
			newClient.setSurname(cl.getSurname());
			newClient.setLogin(cl.getLogin());

			UserEntity newUser = new UserEntity();
			newUser.setEnabled(ENABLED);
			newUser.setUsername(cl.getLogin());
			newUser.setPassword(passwordEncoder(cl.getPassword()));
			serviceUser.createUser(newUser);
			serviceClient.createClient(newClient);

			return "redirect:/";
		}
	}

	public String passwordEncoder(String pass){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);
		return hashedPassword;
	}
}