package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;

public class UserValidation {
	public boolean isValid(User user) {
		boolean isValidEmail=isValidEmail(user.getEmail());
		boolean isValidPassword=isValidPassword(user.getPassword());
		return isValidEmail && isValidPassword;
	}

	private boolean isValidPassword(String password) {
		String passwordRegex="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}";
		Pattern pattern=Pattern.compile(passwordRegex);
	    Matcher matcher=pattern.matcher(password);
		boolean isValidPassword= matcher.matches();
		return isValidPassword;
	}

	private boolean isValidEmail(String email) {
		String emailSplitter[]=email.split("[@]");
		int emailParts=emailSplitter.length;
		if(emailParts>2)return false;
		try {
			String emailUsername=emailSplitter[0];
			String emailDomain=emailSplitter[1];
			String emailUsernameRegex="[a-z0-9.]+$";
			Pattern pattern=Pattern.compile(emailUsernameRegex);
		    Matcher matcher=pattern.matcher(emailUsername);
			boolean isValidEmailUsername= matcher.matches();
			
			String emailDomainRegex="[a-z.]+$";
			pattern=Pattern.compile(emailDomainRegex);
		    matcher=pattern.matcher(emailDomain);
		    boolean isValidEmailDomain=matcher.matches();
		    return isValidEmailUsername && isValidEmailDomain;
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			return false;
		}
		
	}

}
