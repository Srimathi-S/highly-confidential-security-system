package controller;

import java.util.Scanner;

import javax.servlet.RequestDispatcher;

import model.User;
import repository.UserRepository;
import utility.UserValidation;

public class SignupUserMain {
	public static int signupUser()
	{ 
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter email data");
		String email = scanner.nextLine(); 
		System.out.println("Enter Your password");
		String password = scanner.nextLine(); 
		
		UserValidation checkUser=new UserValidation();
		User user=new User(email,password);
		UserRepository userRepository=new UserRepository();
		if(checkUser.isValid(user))
		{
			int isAdded=userRepository.addUser(user);
			if(isAdded!=-1)
			{
				System.out.println("Registration Successful");
				return isAdded;
			}
		}
	    System.out.println( "Check your email and password Or you might have already registered");
	    return -1;
	}
}
