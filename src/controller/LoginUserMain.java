package controller;

import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import model.User;
import repository.UserRepository;

public class LoginUserMain {

	public static int LoginUser() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter email data");
		String email = scanner.nextLine(); 
		System.out.println("Enter Your password");
		String password = scanner.nextLine(); 
		UserRepository userRepository = new UserRepository();
		User user=new User(email,password);
		int isSignedUp=userRepository.isSignedUpUser(user);
		if(isSignedUp!=-1) {
			
			System.out.println( "You are an active user:"+userRepository.getUserId(user));
			return isSignedUp;
	
		}
	
		System.out.println("Please check your details");
		return -1;
	}

}
