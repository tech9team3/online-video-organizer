package rs.levi9.tech9.team3.web.validation.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {

	public boolean isPasswordVaild(String password) {
		if (password.length() >= 8 && password.length()<=20) {
			Pattern letter = Pattern.compile("[a-zA-z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
			Matcher hasLetter = letter.matcher(password);
			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecial = special.matcher(password);

			return hasLetter.find() && hasDigit.find() && hasSpecial.find();

		} else
			return false;
	}
}
