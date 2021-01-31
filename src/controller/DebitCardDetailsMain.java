package controller;

import java.util.Scanner;

import model.DebitCardDetails;
import model.PanCardDetails;
import repository.DebitCardDetailsRepository;
import repository.PanCardDetailsRepository;

public class DebitCardDetailsMain {

	public static boolean manipulateYourData(int userId) {
		Scanner scanner=new Scanner(System.in);
		DebitCardDetailsRepository debitCardDetailsRepository=new DebitCardDetailsRepository();
		System.out.println("Enter the operation that you want to perform");
		System.out.println("1.View your data\n2.Add new data\n3.Edit the data \n4. Delete the data\n");
		int operation=scanner.nextInt();
		if(operation==1)
		{
			debitCardDetailsRepository.viewDebitCardDetails(userId);
		}
		else if(operation==2)
		{
			addData(debitCardDetailsRepository,userId);
			
		}
		else if(operation==3)
		{
			editData(debitCardDetailsRepository,userId);
		}
		else if(operation==4)
		{
			deleteData(debitCardDetailsRepository,userId);
		}
		else
		{
			System.out.print("Please give an appropriate number");
		}
		System.out.println("Do you wish to continue with debit card(Y/N)");
		String toContinue=scanner.next();
		return toContinue.equalsIgnoreCase("Y");
	}

	private static void editData(DebitCardDetailsRepository debitCardDetailsRepository,int userId) 
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter debit card number");
		String cardNumber =scanner.nextLine();
		System.out.println("Name on debit card");
		String nameOnCard = scanner.nextLine();
		System.out.println("cvv number");
		String cvv = scanner.nextLine();
		System.out.println("Other remarks");
		String requiredDetails = scanner.nextLine();
		boolean isEdited=debitCardDetailsRepository.editDebitCardDetails(userId,new DebitCardDetails(userId,requiredDetails,cardNumber,nameOnCard,cvv));
		if(isEdited==true)System.out.println("Added successfully");
		else System.out.println("Please give an appropriate number to edit");
		
	}

	private static void addData(DebitCardDetailsRepository debitCardDetailsRepository,int userId) {

		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter debit card number");
		String cardNumber =scanner.nextLine();
		System.out.println("Name on debit card");
		String nameOnCard = scanner.nextLine();
		System.out.println("cvv number");
		String cvv = scanner.nextLine();
		System.out.println("Other remarks");
		String requiredDetails = scanner.nextLine();
		boolean isAdded=debitCardDetailsRepository.addDebitCardDetails(new DebitCardDetails(userId,requiredDetails,cardNumber,nameOnCard,cvv));
		if(isAdded==true)System.out.println("Added successfully");
		else System.out.println("Please give an appropriate number to edit");
	}
	private static void deleteData(DebitCardDetailsRepository debitCardDetailsRepository,int userId)
	{
		Scanner scanner=new Scanner(System.in);
		boolean isDeleted=debitCardDetailsRepository.deleteDebitCardDetails(userId);
		if(isDeleted==true)System.out.println("Deleted successfully");
		else System.out.println("Please give an appropriate number to delete");
	}
}
