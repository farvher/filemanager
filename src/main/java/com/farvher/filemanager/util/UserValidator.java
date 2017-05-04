package com.farvher.filemanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.farvher.filemanager.entity.User;
import com.farvher.filemanager.services.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
		if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
			errors.rejectValue("userName", "Size");
		}
		if (userService.findByUsername(user.getUserName()) != null) {
			errors.rejectValue("userName", "Duplicate");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "NotEmpty");
		if (user.getPass().length() < 8 || user.getPass().length() > 32) {
			errors.rejectValue("pass", "Size");
		}
		  if (!user.getPasswordConfirm().equals(user.getPass())) {
	            errors.rejectValue("passwordConfirm", "Diff");
	        }

	}

}
