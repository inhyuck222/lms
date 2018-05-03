package com.cafe24.lms.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.ItemService;
import com.cafe24.security.AuthUser;

@Controller
public class MainController {

	@Autowired
	ItemService itemService;

	@RequestMapping({ "/", "/main/{path1:(?!images).*}", "/{path1:(?!images).*}" })
	public String index(Model model, @PathVariable("path1") Optional<Integer> pageNum) {

		Map<String, Object> map = itemService.getAllItems(pageNum);

		model.addAttribute("map", map);

		return "main/index";
	}

	@RequestMapping(value="/rent", method = RequestMethod.GET)
	public String rent(@AuthUser User authUser, Model model, @RequestParam("no") Long itemNo) {
		
		boolean result = itemService.rentItem(itemNo, authUser);
		
		model.addAttribute("isRented", result);

		return "main/rent";
	}

}
