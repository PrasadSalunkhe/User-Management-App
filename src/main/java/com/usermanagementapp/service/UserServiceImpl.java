package com.usermanagementapp.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagementapp.constants.AppConstants;
import com.usermanagementapp.domain.UserAccount;
import com.usermanagementapp.entity.CityMasterEntity;
import com.usermanagementapp.entity.CountryMasterEntity;
import com.usermanagementapp.entity.StateMasterEntity;
import com.usermanagementapp.entity.UserAccountEntity;
import com.usermanagementapp.repository.CityMasterRepository;
import com.usermanagementapp.repository.CountryMasterRepository;
import com.usermanagementapp.repository.StateMasterRepository;
import com.usermanagementapp.repository.UserAccountRepository;
import com.usermanagementapp.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private UserAccountRepository userAccRepo;

	@Autowired
	private CountryMasterRepository countryRepo;

	@Autowired
	private StateMasterRepository stateRepo;

	@Autowired
	private CityMasterRepository cityRepo;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountEntity entity = userAccRepo.findByUserEmailAndUserPazzword(email, pwd);
		if (entity == null) {
			return "Invalid Credentials";
		} else if (entity.getAccountStatus().equals(AppConstants.LOCKED)) {
			return "Your account is locked please unlock it first";
		} else {
			return AppConstants.VALID;
		}
	}

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<CountryMasterEntity> entityList = countryRepo.findAll();
		entityList.forEach(entity -> countryMap.put(entity.getCountryId(), entity.getCountryName()));
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer, String> stateMap = new HashMap<>();
		List<StateMasterEntity> stateList = stateRepo.findByCountryId(countryId);
		stateList.forEach(entity -> stateMap.put(entity.getStateId(), entity.getStateName()));
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer, String> citiesMap = new HashMap<>();
		List<CityMasterEntity> citieslist = cityRepo.findByStateId(stateId);
		citieslist.forEach(entity -> citiesMap.put(entity.getCityId(), entity.getCityName()));
		return citiesMap;
	}

	@Override
	public Boolean isUniqueEmail(String email) {
		UserAccountEntity userAccEntity = userAccRepo.findByUserEmail(email);
		// if record available then it will return false
		return !(userAccEntity != null);
	}

	@Override
	public String generateTempPwd() {
		// choose a Character random from this String
		String AlphaNumericString =AppConstants.ALPHA_NUMERIC_STRING;

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@Override
	public Boolean saveUserAccount(UserAccount userAccount) {

		userAccount.setAccountStatus(AppConstants.LOCKED);
		userAccount.setUserPazzword(generateTempPwd());
		// Above two values are not coming from form thats why we are setting them from
		// here
		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userAccount, entity);
		UserAccountEntity savedEntity = userAccRepo.save(entity);
		if (savedEntity.getUserId() != null) {
			String to = userAccount.getUserEmail();
			String subject = "Registration Successfull : IBM";
			String body = getRegSuccMailBody(userAccount);
			sendRegSuccEmail(to, subject, body);
			return true;
		}
		return false;
	}

	@Override
	public String getRegSuccMailBody(UserAccount userAccount) {
		String fileName = "User-account-body-template.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, AppConstants.EMPTY_STR);
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line -> line.replace(AppConstants.FNAME, userAccount.getFirstName())
					.replace(AppConstants.LNAME, userAccount.getUserLastName())
					.replace(AppConstants.TEMP_PWD, userAccount.getUserPazzword()).replace(AppConstants.EMAIL_PLACE_HOLDER, userAccount.getUserEmail()))
					.collect(Collectors.toList());

			mailBody = String.join(AppConstants.EMPTY_STR, replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public Boolean sendRegSuccEmail(String to, String subject, String body) {
		return emailUtils.sendEmail(to, subject, body);
	}

	@Override
	public Boolean isTempPwdValidForGivenEmail(String email, String tempPwd) {
		UserAccountEntity entity = userAccRepo.findByUserEmailAndUserPazzword(email, tempPwd);
		return entity != null;
	}

	@Override
	public Boolean unlockAccount(String email, String pwd) {
		UserAccountEntity entity = userAccRepo.findByUserEmail(email);
		entity.setAccountStatus(AppConstants.ACTIVE);
		entity.setUserPazzword(pwd);
		UserAccountEntity savedEntity = userAccRepo.save(entity);
		return savedEntity.getUserId() != null;
	}

	@Override
	public String recoverPwd(String email) {
		UserAccountEntity entity = userAccRepo.findByUserEmail(email);
		if (entity != null) {
			UserAccount userAcc = new UserAccount();
			BeanUtils.copyProperties(entity, userAcc);
			String body = getRecoverPwdEmailBody(userAcc);
			String to = userAcc.getUserEmail();
			String subject = "Password Recovery | IBM";
			sendPwdToEmail(to, subject, body);
			return AppConstants.SUCCESS;
		} else {
			return AppConstants.FAILED;
		}
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccount userAccount) {
		String fileName = "Recover-password-mail-body.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName,AppConstants.EMPTY_STR);
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line -> line.replace(AppConstants.FNAME, userAccount.getFirstName())
					.replace(AppConstants.LNAME, userAccount.getUserLastName())
					.replace(AppConstants.PWD, userAccount.getUserPazzword()))
					.collect(Collectors.toList());

			mailBody = String.join(AppConstants.EMPTY_STR, replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String sendPwdToEmail(String email, String subject, String body) {
		boolean isSent = emailUtils.sendEmail(email, subject, body);
		if(isSent) {
			return AppConstants.EMAIL_SENT_MSG;
		}else {
			return AppConstants.EMAIL_SENT_FAILED_MSG;
		}
		
	}

}
