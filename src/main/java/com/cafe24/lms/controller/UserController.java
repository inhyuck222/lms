package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute User user) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute User userInfo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/user/user_join";
		}

		Boolean result = userService.joinUser(userInfo);
		if (result == false) {
			return "/user/user_join";
		}

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser User authUser) {
		System.out.println("modify.GET : " + authUser);
		return "user/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute User userInfo, @AuthUser User authUser) {
		System.out.println("modify.POST : " + authUser);
		if (authUser == null) {
			return "/";
		}

		Boolean result = userService.modify(userInfo, authUser);
		if (result == false) {
			return "user/modify";
		}

		return "redirect:/";
	}

}
