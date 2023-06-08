package com.usermanagementapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usermanagementapp.constants.AppConstants;
import com.usermanagementapp.domain.UserAccount;
import com.usermanagementapp.properties.AppProperties;
import com.usermanagementapp.service.UserService;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProps;
	
	
	// The common logic for executed for form loading we keep in one method to avoid boilerplate code
	@ModelAttribute
	public void loadFormData(Model model) {
		UserAccount userAccObj = new UserAccount();
		model.addAttribute(AppConstants.USER_ACCOUNT, userAccObj);
		Map<Integer, String> countriesMap = userService.loadCountries();
		model.addAttribute(AppConstants.COUNTRIES, countriesMap);
	}

	@GetMapping("/register")
	public String loadRegForm(Model model) {
//		UserAccount userAccObj = new UserAccount();
//		model.addAttribute("userAcc", userAccObj);
//		Map<Integer, String> countriesMap = userService.loadCountries();
//		model.addAttribute("countries", countriesMap); // one attribute for form binding other for countries drop-down
		// for state and cities to be load only after selecting country and then state
		// but for selecting country it
		// should be come by default on page
		// We can load state and cities by using Ajax
		return AppConstants.REGISTRATION_VIEW_NAME;
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam("email") String email) {
		// Instead of logical view page
		// @ResponseBody is used to return response directly from controller to UI
		return userService.isUniqueEmail(email)?AppConstants.UNIQUE:AppConstants.DUPLICATE;

	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvent(@RequestParam("countryId") Integer countryId) {
		return userService.loadStatesByCountryId(countryId);
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvent(@RequestParam("stateId") Integer stateId) {
		return userService.loadCitiesByStateId(stateId);
	}

	@PostMapping("/userregistration")
	public String handleRegisterBtn(UserAccount userAcc, RedirectAttributes redatt) {
		Boolean isSaved = userService.saveUserAccount(userAcc);
		if (isSaved) {
			String regSuccMsg=appProps.getMessages().get(AppConstants.REG_SUCC); // "regSuccMsg" key is replaced by AppConstants.REG_SUCC
			redatt.addFlashAttribute(AppConstants.SUCCESS_MSG,regSuccMsg);
			//("succMsg", "Registration Successfull");
		} else {
			String regFailMsg=appProps.getMessages().get(AppConstants.REG_FAIL);
			redatt.addFlashAttribute(AppConstants.FAIL_MSG,regFailMsg);
		}

//		UserAccount userAccObj = new UserAccount();
//		model.addAttribute("userAcc", userAccObj);
//		Map<Integer, String> countriesMap = userService.loadCountries();
//		model.addAttribute("countries", countriesMap);

		// above ,We requird to reload the page after registratipn so we need form
		// binding object and country dropdown
		// here as well.

		return AppConstants.REGISTRATION_VIEW_NAME;
	}

}
