package controller;

import java.util.Scanner;


public class UserConfidentialDetailsMain {

	public static boolean manipulateYourData(int userId) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Choose data from menu");
		System.out.println("1.Pan card data\n2.Debit card data\n3.Other details\n");
		int operation=scanner.nextInt();
		boolean wantToContinue=true;
		do
		{
		if(operation==1)
		{
			wantToContinue=PanCardDetailsMain.manipulateYourData(userId);
		}
		else if(operation==2)
		{
			wantToContinue=DebitCardDetailsMain.manipulateYourData(userId);
			
		}
		else if(operation==3)
		{
			wantToContinue=OtherConfidentialDetailsMain.manipulateYourData(userId);
		}
		else
		{
			System.out.print("Please give an appropriate number");
			operation=scanner.nextInt();
		}
		}while(wantToContinue==true);
		System.out.println("Do you wish to continue with different data(Y)/Go back(N)");
		String toContinue=scanner.next();
		return toContinue.equalsIgnoreCase("Y");
	}

	

}
