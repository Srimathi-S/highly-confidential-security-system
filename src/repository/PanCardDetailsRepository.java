package repository;

import java.util.HashMap;
import cryptography.AES;
import model.PanCardDetails;

public class PanCardDetailsRepository{
	private static HashMap<Integer,PanCardDetails>panCardDetailsMap=new HashMap<>();
	public final boolean addPanCardDetails(PanCardDetails panCardDetails)
	{
		UserRepository userRepository = new UserRepository();
		int userId=panCardDetails.getUserId();
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.encrypt(panCardDetails.getNumber(), secret);
		String requiredDetails=AES.encrypt(panCardDetails.getRequiredDetails(), secret);
		String name=AES.encrypt(panCardDetails.getName(), secret);
		String address=AES.encrypt(panCardDetails.getAddress(), secret);
		PanCardDetails encryptedPanCardDetails=new PanCardDetails(userId , requiredDetails, number, name,  address);
		panCardDetailsMap.put(userId, encryptedPanCardDetails);
		printDetails(userId);
		return true;
	}
	
	
	public final void viewPanCardDetails(int userId)
	{
		if(!panCardDetailsMap.containsKey(userId))
		{
			System.out.println("No data yet");
			return;
		}
		UserRepository userRepository = new UserRepository();
		PanCardDetails panCardDetails=panCardDetailsMap.get(userId);
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.decrypt(panCardDetails.getNumber(), secret);
		String requiredDetails=AES.decrypt(panCardDetails.getRequiredDetails(), secret);
		String name=AES.decrypt(panCardDetails.getName(), secret);
		String address=AES.decrypt(panCardDetails.getAddress(), secret);
		
		System.out.println("Your pan card details");
		System.out.println("Pan number:"+number);
		System.out.println("Name in pan card:"+name);
		System.out.println("Address in pan card:"+address);
		System.out.println("Other required details:"+requiredDetails);
		
	}
	public void printDetails(int userId)
	{
		PanCardDetails panCardDetails=panCardDetailsMap.get(userId);
		
		System.out.println("Your pan card details");
		System.out.println("Pan number:"+panCardDetails.getNumber());
		System.out.println("Name in pan card:"+panCardDetails.getName());
		System.out.println("Address in pan card:"+panCardDetails.getAddress());
		System.out.println("Other required details:"+panCardDetails.getRequiredDetails());
	}
	public final boolean editPanCardDetails(int userId,PanCardDetails panCardDetails)
	{
		if(!panCardDetailsMap.containsKey(userId))
		{
			System.out.println("No data yet so you are not editting but adding new data");
		}
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		String number=AES.encrypt(panCardDetails.getNumber(), secret);
		String requiredDetails=AES.encrypt(panCardDetails.getRequiredDetails(), secret);
		String name=AES.encrypt(panCardDetails.getName(), secret);
		String address=AES.encrypt(panCardDetails.getAddress(), secret);
		PanCardDetails encryptedPanCardDetails=new PanCardDetails(userId , requiredDetails, number, name,  address);
		panCardDetailsMap.put(userId, encryptedPanCardDetails);
		return true;
	}
	public final boolean deletePanCardDetails(int userId)
	{
		if(!panCardDetailsMap.containsKey(userId))return false;
		panCardDetailsMap.remove(userId);
		return true;
	}
}
