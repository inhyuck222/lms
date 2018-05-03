package com.cafe24.lms.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.lms.service.RentService;
import com.cafe24.security.Auth;

@Auth(value=Auth.Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	@Autowired
	RentService rentService;
	
	@RequestMapping( { "", "/rent/{path1:(?!images).*}", "/main/{path1:(?!images).*}", "/{path1:(?!images).*}" } )
	public String main( Model model, @PathVariable("path1") Optional<Integer> pageNum ) {

		Map<String, Object> map = rentService.findAllRents(pageNum);

		model.addAttribute("map", map);

		return "admin/rent";
	}
	
	@RequestMapping( {"/reserve", "/reserve/{path1:(?!images).*}"} )
	public String board( Model model, @PathVariable("path1") Optional<Integer> pageNum ) {

		System.out.println(pageNum);
		Map<String, Object> map = rentService.findAllReservation(pageNum);

		model.addAttribute("map", map);

		return "admin/reserve";
	}
	
}
