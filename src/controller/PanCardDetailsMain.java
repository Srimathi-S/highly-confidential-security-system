package controller;

import java.util.Scanner;

import model.PanCardDetails;
import repository.PanCardDetailsRepository;

public class PanCardDetailsMain {

	public static boolean manipulateYourData(int userId) {
			Scanner scanner=new Scanner(System.in);
			PanCardDetailsRepository PanCardDetailsRepository=new PanCardDetailsRepository();
			System.out.println("Enter the operation that you want to perform");
			System.out.println("1.View your data\n2.Add new data\n3.Edit the data \n4. Delete the data\n");
			int operation=scanner.nextInt();
			if(operation==1)
			{
				PanCardDetailsRepository.viewPanCardDetails(userId);
			}
			else if(operation==2)
			{
				addData(PanCardDetailsRepository,userId);
				
			}
			else if(operation==3)
			{
				editData(PanCardDetailsRepository,userId);
			}
			else if(operation==4)
			{
				deleteData(PanCardDetailsRepository,userId);
			}
			else
			{
				System.out.print("Please give an appropriate number");
			}
			System.out.println("Do you wish to continue with pan card(Y/N)");
			String toContinue=scanner.next();
			return toContinue.equalsIgnoreCase("Y");
		}

		private static void editData(PanCardDetailsRepository panCardDetailsRepository,int userId) 
		{
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter pan number");
			String panNumber =scanner.nextLine();
			System.out.println("Name on pancard");
			String name = scanner.nextLine();
			System.out.println("Adress on pancard");
			String address = scanner.nextLine();
			System.out.println("Other remarks");
			String requiredDetails = scanner.nextLine();
			boolean isEdited=panCardDetailsRepository.editPanCardDetails(userId,new PanCardDetails(userId,requiredDetails,panNumber,name,address));
			if(isEdited==true)System.out.println("Added successfully");
			else System.out.println("Please give an appropriate number to edit");
			
		}

		private static void addData(PanCardDetailsRepository panCardDetailsRepository,int userId) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("Enter pan number");
			String panNumber =scanner.nextLine();
			System.out.println("Name on pancard");
			String name = scanner.nextLine();
			System.out.println("Adress on pancard");
			String address = scanner.nextLine();
			System.out.println("Other remarks");
			String requiredDetails = scanner.nextLine();
			boolean isAdded=panCardDetailsRepository.addPanCardDetails(new PanCardDetails(userId,requiredDetails,panNumber,name,address));
			if(isAdded==true)System.out.println("Added successfully");
			else System.out.println("Please give an appropriate number to edit");
		}
		private static void deleteData(PanCardDetailsRepository panCardDetailsRepository,int userId)
		{
			Scanner scanner=new Scanner(System.in);
			boolean isDeleted=panCardDetailsRepository.deletePanCardDetails(userId);
			if(isDeleted==true)System.out.println("Deleted successfully");
			else System.out.println("Please give an appropriate number to delete");
		}


	

}
