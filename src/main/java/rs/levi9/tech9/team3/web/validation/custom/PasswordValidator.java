package rs.levi9.tech9.team3.web.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import rs.levi9.tech9.team3.web.validation.password.PasswordUtil;

public class PasswordValidator implements ConstraintValidator<Password, String>{

	@Override
	public void initialize(Password constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return new PasswordUtil().isPasswordVaild(password);
	}

}
