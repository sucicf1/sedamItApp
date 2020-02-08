package com.ivsucic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivsucic.model.Service;
import com.ivsucic.model.User;
import com.ivsucic.repository.ServiceRepository;
import com.ivsucic.repository.UserRepository;

@Controller
public class ServiceController {
	@Autowired
	private ServiceRepository serviceRepo;
	@Autowired
	private UserRepository userRepo;

	@RequestMapping({ "/", "/service" })
	public String index(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		model.put("services", user.getServices());
		return "list-services";
	}

	@RequestMapping("/service/{externId}/showBills")
	public String showBillsByService(@PathVariable long externId, ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Service service = serviceRepo.findByExternId(externId);
		if (service.getUser().getId() != user.getId())
			throw new SecurityException("Not your service");

		model.put("externId", externId);
		model.put("bills", service.getBills());
		return "show-bills-by-service";
	}

	@RequestMapping(value = "/service/add", method = RequestMethod.GET)
	public String showAddServicePage(ModelMap model) {
		model.addAttribute("service", new Service());
		return "add-service";
	}

	@RequestMapping(value = "/service/delete/{id}", method = RequestMethod.GET)
	public String deleteService(@PathVariable long id) throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Service service = serviceRepo.getOne(id);
		if (service.getUser().getId() != user.getId())
			throw new SecurityException("Not your service");
		serviceRepo.delete(service);
		return "redirect:/service";
	}

	@RequestMapping(value = "/service/add", method = RequestMethod.POST)
	public String addService(ModelMap model, @Valid Service service, BindingResult result) {
		if (result.hasErrors()) {
			return "add-service";
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		service.setUser(user);
		serviceRepo.save(service);
		return "redirect:/service";
	}
}
