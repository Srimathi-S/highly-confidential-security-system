package controller;

import java.util.Scanner;
import model.UserDetails;
import repository.UserDetailsRepository;

public class ConfidentialDetailsMain {

	public static boolean yourData(int userId) {
		Scanner scanner=new Scanner(System.in);
		UserDetailsRepository userDetailsRepository=new UserDetailsRepository();
		System.out.println("Enter the operation that you want to perform");
		System.out.println("1.View your data\n2.Add new data\n3.Edit the data by specifying its number\n4. Delete the data\n");
		int operation=scanner.nextInt();
		if(operation==1)
		{
			userDetailsRepository.viewConfidentialDetails(userId);
		}
		else if(operation==2)
		{
			addData(userDetailsRepository,userId);
			
		}
		else if(operation==3)
		{
			System.out.println("Enter the data number you want to edit");
			int dataId=scanner.nextInt();
			editData(userDetailsRepository,userId,dataId);
		}
		else if(operation==4)
		{
			deleteData(userDetailsRepository,userId);
		}
		else
		{
			System.out.print("Please give an appropriate number");
		}
		System.out.println("Do you wish to continue(Y/N)");
		String toContinue=scanner.next();
		return toContinue.equalsIgnoreCase("Y");
	}

	private static void editData(UserDetailsRepository userDetailsRepository,int userId,int dataId) 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the type of data");
		String nameOfData =scanner.nextLine();
		System.out.println("Required information");
		String requiredInformation = scanner.nextLine();
		boolean isEdited=userDetailsRepository.editConfidentialDetails(dataId,userId,new UserDetails(nameOfData,requiredInformation));
		if(isEdited==true)System.out.println("Added successfully");
		else System.out.println("Please give an appropriate number to edit");
		
	}

	private static void addData(UserDetailsRepository userDetailsRepository,int userId) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the type of data");
		String nameOfData =scanner.nextLine();
		System.out.println("Required information");
		String requiredInformation = scanner.nextLine();
		userDetailsRepository.addConfidentialDetails(new UserDetails(userId,0,nameOfData,requiredInformation));
		System.out.println("Added successfully");
	}
	private static void deleteData(UserDetailsRepository userDetailsRepository,int userId)
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the data number you want to delete");
		int dataId=scanner.nextInt();
		boolean isDeleted=userDetailsRepository.deleteConfidentialDetails(dataId,userId);
		if(isDeleted==true)System.out.println("Deleted successfully");
		else System.out.println("Please give an appropriate number to delete");
	}

}
