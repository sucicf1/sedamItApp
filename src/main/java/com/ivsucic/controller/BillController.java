package com.ivsucic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivsucic.model.Bill;
import com.ivsucic.model.Service;
import com.ivsucic.model.User;
import com.ivsucic.repository.BillRepository;
import com.ivsucic.repository.ServiceRepository;
import com.ivsucic.repository.UserRepository;

@Controller
public class BillController {
	@Autowired
	private BillRepository billRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ServiceRepository serviceRepo;

	@RequestMapping(value = "/bill/add/{externId}", method = RequestMethod.GET)
	public String showAddBillPage(@PathVariable long externId, ModelMap model) {
		model.addAttribute("billForm", new Bill());
		return "bill";
	}

	@RequestMapping(value = "/bill/add/{externId}", method = RequestMethod.POST)
	public String addBill(@PathVariable long externId, ModelMap model, @Valid Bill bill, BindingResult result) {
		if (result.hasErrors()) {
			return "bill";
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Service service = serviceRepo.findByExternId(externId);
		if (service.getUser().getId() != user.getId())
			throw new SecurityException("Not your service");

		bill.setService(service);
		billRepo.save(bill);
		return "redirect:/service/" + externId + "/showBills";
	}

	@RequestMapping(value = "/bill/update/{id}", method = RequestMethod.GET)
	public String showUpdateBillPage(@PathVariable long id, ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Bill bill = billRepo.findById(id);
		if (bill.getService().getUser().getId() != user.getId())
			throw new SecurityException("Not your bill");
		model.put("billForm", bill);
		return "bill";
	}

	@RequestMapping(value = "/bill/update/{id}", method = RequestMethod.POST)
	public String updateBill(@PathVariable long id, @ModelAttribute("billForm") Bill billForm, BindingResult result) {
		if (result.hasErrors()) {
			return "bill";
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Bill bill = billRepo.findById(id);
		if (bill.getService().getUser().getId() != user.getId())
			throw new SecurityException("Not your bill");

		billForm.setService(bill.getService());
		billRepo.save(billForm);
		long externId = billForm.getService().getExternId();
		return "redirect:/service/" + externId + "/showBills";
	}

	@RequestMapping(value = "/bill/delete/{id}", method = RequestMethod.GET)
	public String deleteService(@PathVariable long id) throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username);
		Bill bill = billRepo.getOne(id);
		if (bill.getService().getUser().getId() != user.getId())
			throw new SecurityException("Not your bill");
		billRepo.delete(bill);

		long externId = bill.getService().getExternId();
		return "redirect:/service/" + externId + "/showBills";
	}
}
