package testing;


import org.junit.Assert;
import org.junit.Test;

import model.User;
import utility.UserValidation;


public class TestUserValidation {
	
	@Test
	public void validUser()
	{
		boolean expected=true;
		UserValidation userValidation=new UserValidation();
		User user=new User("srimathi@yahoo.com","21Twoone@");
		Assert.assertEquals(expected,userValidation.isValid(user));
		
		user=new User("srimathi.s@rajalakshmi.edu.in","21Twoone@");
		Assert.assertEquals(expected,userValidation.isValid(user));
	}
	
	@Test
	public void invalidEmail()
	{
		boolean expected=false;
		UserValidation userValidation=new UserValidation();
		User user=new User("srimathi_yahoo.com","21Twoone@");
		Assert.assertEquals(expected,userValidation.isValid(user));
		
		user=new User("srimathi@yahoo@com","21Twoone@");
		Assert.assertEquals(expected,userValidation.isValid(user));
		

	}
	
	@Test
	public void invalidPassword()
	{
		boolean expected=false;
		UserValidation userValidation=new UserValidation();
		User user=new User("srimathi@yahoo.com","twoone");
		Assert.assertEquals(expected,userValidation.isValid(user));
		
		user=new User("srimathi@yahoo.com","21twoone");
		Assert.assertEquals(expected,userValidation.isValid(user));
	}
	
}
