package com.usermanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.usermanagementapp.constants.AppConstants;
import com.usermanagementapp.domain.UnlockAccount;
import com.usermanagementapp.domain.UserAccount;
import com.usermanagementapp.properties.AppProperties;
import com.usermanagementapp.service.UserService;


@Controller
public class UnlockAccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProps;

	/** This method is used to load unlock acc form
	 * Hiperlink is in their mail, when we click on that link 
	 * this method we capture email from that and then we execute to load form (unlockAcc.jsp) 
	 * @param email
	 * @param model
	 * @return String
	 */
	@GetMapping("/loadUnlockAccForm")
	public String loadUnlockAccForm(@RequestParam("email") String email, Model model) {
		UnlockAccount account= new UnlockAccount();
		account.setEmail(email);
		model.addAttribute(AppConstants.USER_ACCOUNT, account);
		return AppConstants.UNLOCK_ACCOUNT_VIEW_NAME;
	}
	
	/**
	 * This method for handle unlock account form submission
	 * @param unloackAcc
	 * @param model
	 * @return String
	 */
	@GetMapping("/unloackAccount")
	public String handleSubmitBtn(@ModelAttribute("userAcc") UnlockAccount unlockAcc, Model model) {
		Boolean isValid = userService.isTempPwdValidForGivenEmail(unlockAcc.getEmail(),unlockAcc.getTempPazzword());
		if(isValid) {
			userService.unlockAccount(unlockAcc.getEmail(), unlockAcc.getPazzword());
			String unlockAccSuccMsg = appProps.getMessages().get("unlockAccSuccMsg");
		   model.addAttribute(AppConstants.SUCCESS_MSG,unlockAccSuccMsg);
		}else {
			String invalidTempPwd = appProps.getMessages().get("invalidTempPwd");
			model.addAttribute(AppConstants.FAIL_MSG,invalidTempPwd );
		}
		return AppConstants.UNLOCK_ACCOUNT_VIEW_NAME;
	}
}
