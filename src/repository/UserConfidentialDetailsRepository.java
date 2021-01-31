package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cryptography.AES;
import model.UserConfidentialDetails;

public class UserConfidentialDetailsRepository {
	private static HashMap<Integer,List<UserConfidentialDetails>>userDetailsMap=new HashMap<>();
	static int totalUsers=userDetailsMap.size();
	
	public final boolean addUserConfidentialDetails(UserConfidentialDetails userConfidentialDetails)
	{
		UserRepository userRepository = new UserRepository();
		int userId=userConfidentialDetails.getUserId();
		String secret=userRepository.getUser(userId).getEmail();
		String typeOfData=AES.encrypt(userConfidentialDetails.getNameOfConfidentialData(), secret);
		String requiredDetails=AES.encrypt(userConfidentialDetails.getRequiredDetails(), secret);
		
		if(userDetailsMap.containsKey(userId))
		{
			List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
			int currentAdditionAt=userConfidentialDetailsList.size();
			UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,currentAdditionAt,typeOfData,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
		}
		else
		{
			List<UserConfidentialDetails> userConfidentialDetailsList=new ArrayList<>();
			UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,0,typeOfData,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
			totalUsers++;
		}
		printDetails(userId);
		return true;
	}
	
	public final void viewUserConfidentialDetails(int userId)
	{
		if(userId>=totalUsers)
		{
			System.out.println("No data yet");
			return ;
		}
		List<UserConfidentialDetails> confidentialDetailsList= userDetailsMap.get(userId);
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		int dataId=0;
		for(UserConfidentialDetails userConfidentialDetails: confidentialDetailsList)
		{
			String name=AES.decrypt(userConfidentialDetails.getNameOfConfidentialData(), secret);
			String data=AES.decrypt(userConfidentialDetails.getRequiredDetails(), secret);
			System.out.println(dataId+"\t"+name+"\t"+data);
			dataId++;
		}
		
	}
	
	public final boolean editUserConfidentialDetails(int dataId,int userId,UserConfidentialDetails userConfidentialDetails)
	{
		
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		String typeOfData=AES.encrypt(userConfidentialDetails.getNameOfConfidentialData(), secret);
		String requiredDetails=AES.encrypt(userConfidentialDetails.getRequiredDetails(), secret);
		List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
		if(dataId>=userConfidentialDetailsList.size())return false;
		UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,dataId,typeOfData,requiredDetails);
		userConfidentialDetailsList.remove(dataId);
		userConfidentialDetailsList.add(dataId, toBeAdded);
		userDetailsMap.put(userId,userConfidentialDetailsList);
		return true;
	}
	private void printDetails(int userId) 
	{
		System.out.println();
		System.out.println("Printing all the confidential details");
		for(UserConfidentialDetails userConfidentialDetails: userDetailsMap.get(userId))
		{
			System.out.println(userConfidentialDetails.getNameOfConfidentialData()+"\t"+userConfidentialDetails.getRequiredDetails());
		}
		
	}

	public final boolean deleteUserConfidentialDetails(int dataId, int userId) 
	{
		List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
		if(dataId>=userConfidentialDetailsList.size())return false;
		userConfidentialDetailsList.remove(dataId);
		int totalDetails=userConfidentialDetailsList.size();
		for(int number=dataId;number<totalDetails;number++)
		{
			userConfidentialDetailsList.get(dataId).setDataId(dataId);
		}
		
		return true;
	}
}
