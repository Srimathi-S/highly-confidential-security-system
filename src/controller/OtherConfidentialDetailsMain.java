package controller;

import java.util.Scanner;
import model.UserConfidentialDetails;
import repository.OtherConfidentialDetailsRepository;

public class OtherConfidentialDetailsMain {

	public static boolean manipulateYourData(int userId) {
		Scanner scanner=new Scanner(System.in);
		OtherConfidentialDetailsRepository otherConfidentialDetailsRepository=new OtherConfidentialDetailsRepository();
		System.out.println("Enter the operation that you want to perform");
		System.out.println("1.View your data\n2.Add new data\n3.Edit the data by specifying its number\n4. Delete the data\n");
		int operation=scanner.nextInt();
		
		if(operation==1)
		{
			otherConfidentialDetailsRepository.viewUserConfidentialDetails(userId);
		}
		else if(operation==2)
		{
			addData(otherConfidentialDetailsRepository,userId);
			
		}
		else if(operation==3)
		{
			System.out.println("Enter the data number you want to edit");
			int dataId=scanner.nextInt();
			editData(otherConfidentialDetailsRepository,userId,dataId);
		}
		else if(operation==4)
		{
			deleteData(otherConfidentialDetailsRepository,userId);
		}
		else
		{
			System.out.print("Please give an appropriate number");
		}
		System.out.println("Do you wish to continue(Y/N)");
		String toContinue=scanner.next();
		return toContinue.equalsIgnoreCase("Y");
	}

	private static void editData(OtherConfidentialDetailsRepository otherConfidentialDetailsRepository,int userId,int dataId) 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the type of data");
		String typeOfData =scanner.nextLine();
		System.out.println("Required information");
		String requiredInformation = scanner.nextLine();
		boolean isEdited=otherConfidentialDetailsRepository.editUserConfidentialDetails(dataId,userId,new UserConfidentialDetails(typeOfData,requiredInformation));
		if(isEdited==true)System.out.println("Added successfully");
		else System.out.println("Please give an appropriate number to edit");
		
	}

	private static void addData(OtherConfidentialDetailsRepository otherConfidentialDetailsRepository,int userId) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the type of data");
		String typeOfData =scanner.nextLine();
		System.out.println("Required information");
		String requiredInformation = scanner.nextLine();
		otherConfidentialDetailsRepository.addUserConfidentialDetails(new UserConfidentialDetails(userId,typeOfData,requiredInformation));
		System.out.println("Added successfully");
	}
	private static void deleteData(OtherConfidentialDetailsRepository otherConfidentialDetailsRepository,int userId)
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the data number you want to delete");
		int dataId=scanner.nextInt();
		boolean isDeleted=otherConfidentialDetailsRepository.deleteUserConfidentialDetails(dataId,userId);
		if(isDeleted==true)System.out.println("Deleted successfully");
		else System.out.println("Please give an appropriate number to delete");
	}

}
