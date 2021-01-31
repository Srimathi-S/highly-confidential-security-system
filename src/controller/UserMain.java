package controller;

import java.util.Scanner;


public class UserMain {
	public static void main(String args[]) 
	{
		Scanner scanner=new Scanner(System.in);
		boolean wantToComeInAgain=false;
		do
		{
		int userId=-1;
		do
		{
		System.out.println("Are you a new user(Press y or n)");
		String isNewUser=scanner.next();
		
		if(isNewUser.equalsIgnoreCase("Y"))
		{
			userId =SignupUserMain.signupUser();
		}
		else
		{
			userId=LoginUserMain.LoginUser();
		}
		}while(userId==-1);
		boolean wantToContinue=false;
		do
		{
		wantToContinue=UserConfidentialDetailsMain.manipulateYourData(userId);
		}while(wantToContinue);
		System.out.println("Want to logout and login again(press y)/Just logout(press n)");
		String yOrN=scanner.next();
		wantToComeInAgain=yOrN.equalsIgnoreCase("Y");
		}while(wantToComeInAgain);
		
	}
}
