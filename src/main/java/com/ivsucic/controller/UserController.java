package com.ivsucic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivsucic.model.User;
import com.ivsucic.repository.UserRepository;
import com.ivsucic.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String showUpdateUserPage(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		model.put("userForm", user);
		return "update-user";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userForm") User userForm, BindingResult result) {
		if (result.hasErrors()) {
			return "update-user";
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User userCurrent = userRepo.findByUsername(username);
		userForm.setUsername(username);

		if (!(userForm.getPassword() == null || userForm.getPassword().equals(""))) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
		}

		userForm.setId(userCurrent.getId());
		userRepo.save(userForm);
		return "redirect:/service";
	}
}
