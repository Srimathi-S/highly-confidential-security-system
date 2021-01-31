package repository;

import java.util.HashMap;

import cryptography.AES;
import model.DebitCardDetails;


public class DebitCardDetailsRepository {
	private static HashMap<Integer,DebitCardDetails>debitCardDetailsMap=new HashMap<>();
	public boolean addDebitCardDetails(DebitCardDetails debitCardDetails)
	{
		UserRepository userRepository = new UserRepository();
		int userId=debitCardDetails.getUserId();
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.encrypt(debitCardDetails.getNumber(), secret);
		String requiredDetails=AES.encrypt(debitCardDetails.getRequiredDetails(), secret);
		String nameOnCard=AES.encrypt(debitCardDetails.getNameOnCard(), secret);
		String cvv=AES.encrypt(debitCardDetails.getCvv(), secret);
		DebitCardDetails encryptedPanCardDetails=new DebitCardDetails(userId , requiredDetails, number, nameOnCard,  cvv);
		debitCardDetailsMap.put(userId, encryptedPanCardDetails);
		printDetails(userId);
		return true;
	}
	
	
	public void viewDebitCardDetails(int userId)
	{
		if(!debitCardDetailsMap.containsKey(userId))
		{
			System.out.println("No data yet");
			return;
		}
		UserRepository userRepository = new UserRepository();
		DebitCardDetails debitCardDetails=debitCardDetailsMap.get(userId);
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.decrypt(debitCardDetails.getNumber(), secret);
		String requiredDetails=AES.decrypt(debitCardDetails.getRequiredDetails(), secret);
		String nameOnCard=AES.decrypt(debitCardDetails.getNameOnCard(), secret);
		String cvv=AES.decrypt(debitCardDetails.getCvv(), secret);
		System.out.println("Your debit card details");
		System.out.println("Debit card number:"+number);
		System.out.println("Name in debit card:"+nameOnCard);
		System.out.println("cvv of debit card:"+cvv);
		System.out.println("Other required details"+requiredDetails);
		
	}
	public void printDetails(int userId)
	{

		DebitCardDetails debitCardDetails=debitCardDetailsMap.get(userId);
		System.out.println("Your debit card details");
		System.out.println("Debit card number:"+debitCardDetails.getNumber());
		System.out.println("Name in debit card:"+debitCardDetails.getNameOnCard());
		System.out.println("cvv of debit card:"+debitCardDetails.getCvv());
		System.out.println("Other required details"+debitCardDetails.getRequiredDetails());
	}
	public boolean editDebitCardDetails(int userId,DebitCardDetails debitCardDetails)
	{
		if(!debitCardDetailsMap.containsKey(userId))
		{
			System.out.println("No data yet so you are not editting but adding new data");
		}
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.encrypt(debitCardDetails.getNumber(), secret);
		String requiredDetails=AES.encrypt(debitCardDetails.getRequiredDetails(), secret);
		String nameOnCard=AES.encrypt(debitCardDetails.getNameOnCard(), secret);
		String cvv=AES.encrypt(debitCardDetails.getCvv(), secret);
		DebitCardDetails encryptedPanCardDetails=new DebitCardDetails(userId , requiredDetails, number, nameOnCard,  cvv);
		debitCardDetailsMap.put(userId, encryptedPanCardDetails);
		return true;
	}
	public boolean deleteDebitCardDetails(int userId)
	{
		if(!debitCardDetailsMap.containsKey(userId))return false;
		debitCardDetailsMap.remove(userId);
		return true;
	}
}
