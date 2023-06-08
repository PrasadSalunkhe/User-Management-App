package com.usermanagementapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.usermanagementapp.constants.AppConstants;
import com.usermanagementapp.service.UserService;


@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * This method is responsible to load login page
	 @return String
	 *
	 */
	@GetMapping("/")
	public String index() {
		return AppConstants.INDEX_VIEW_NAME;
	}
	/**
	 * This method used to handle login functionality
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/signin")
	public String handleSignInBtn(HttpServletRequest req, Model model) {
		//HttpServletRequest req object -used to capture form data 
		// Model model object - used to send data to UI
		String viewName=AppConstants.EMPTY_STR;
		String email=req.getParameter(AppConstants.EMAIL);
		String pwd=req.getParameter(AppConstants.PAZZWORD);
		String status=userService.loginCheck(email, pwd);
		if(status.equals(AppConstants.VALID)) {
			viewName=AppConstants.DASHBOARD_VIEW_NAME;
		}else {
			viewName=AppConstants.INDEX_VIEW_NAME;
			model.addAttribute(AppConstants.FAIL_MSG, status);
		}
		return viewName;
	}
}
