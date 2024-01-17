package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.Helper.Message;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("tittle","home - Smart Contact Manager");
		return "home";
	}
	 
	@GetMapping("/about")
	public String about(Model m) {
		m.addAttribute("tittle","About - Smart Contact Manager");
		
		return"about";
	}
	
	@GetMapping("/signup")
	public String SignUp(Model m) {
		m.addAttribute("tittle","Sign Up - Smart Contact Manager");
		m.addAttribute("user", new User());
		
		return"signup";
	}
	
	@PostMapping("/do-register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult Bresult, @RequestParam(value = "agreement",defaultValue = "false")boolean agreement,Model model, HttpSession session) throws Exception {
		

		
		try {
			
			if(!agreement) {
				
				System.out.println("you have not agreed the terms and condition");
				throw new Exception("you have not agreed the terms and condition");  
				
			}
			if(Bresult.hasErrors()) {
				model.addAttribute("user", user);
				System.out.println(Bresult);
				return "signup";
			}
			
			
			
			user.setRole("role_user");
			user.setEnabled(true);
			user.setImageUrl("default.png");

			System.out.println("agreement:" + agreement);
			System.out.println("User:" + user);
			User result = this.repository.save(user);
			model.addAttribute("user", new User());
			
			session.setAttribute("message", new Message("succesfully Registerd!!", "alert-success"));
			return "signup";
			
		} catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("user", user);
	        session.setAttribute("message", new Message("Something Went Wrong!!" + e.getMessage(), "alert-danger"));
	        return"signup";
	}
	

}
	
	
}
