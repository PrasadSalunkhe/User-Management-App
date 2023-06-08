package com.usermanagementapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.usermanagementapp.constants.AppConstants;
import com.usermanagementapp.properties.AppProperties;
import com.usermanagementapp.service.UserService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProps;

	/**
	 * This method is to load forgot pwd  form
	 * @return String
	 */
	@GetMapping("/forgotPwdform")
	public String loadForgotpwdForm() {
		return AppConstants.FORGOT_PAZZWORD_VIEW_NAME;
	}
	
	/**
	 * This method to handle submit button 
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/forgotPwd")
	public String handleForgotPwdSubmitBtn(HttpServletRequest req, Model model) {
		//HttpServletRequest req to capture form data as we dont have any object to get it from frontend.
		String email = req.getParameter(AppConstants.EMAIL);
		String status = userService.recoverPwd(email);
		if(status.equals(AppConstants.SUCCESS)) {
			String pwdRecSuccMsg = appProps.getMessages().get(AppConstants.PWD_REC_SUCC_MSG);
			model.addAttribute(AppConstants.SUCCESS_MSG,pwdRecSuccMsg);
		}else {
			String pwdRecInvlidMail = appProps.getMessages().get(AppConstants.PWD_REC_EMAIL_INVALID_MSG);
			model.addAttribute(AppConstants.FAIL_MSG,pwdRecInvlidMail);
		}
		return AppConstants.FORGOT_PAZZWORD_VIEW_NAME;
	}
}
