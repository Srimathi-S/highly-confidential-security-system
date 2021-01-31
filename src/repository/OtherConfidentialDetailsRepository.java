package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cryptography.AES;
import model.UserConfidentialDetails;

public class OtherConfidentialDetailsRepository  {
	private static HashMap<Integer,List<UserConfidentialDetails>>userDetailsMap=new HashMap<>();
	
	public boolean addUserConfidentialDetails(UserConfidentialDetails userConfidentialDetails)
	{
		UserRepository userRepository = new UserRepository();
		int userId=userConfidentialDetails.getUserId();
		String secret=userRepository.getUser(userId).getEmail();
		String nameOfData=userConfidentialDetails.getNameOfConfidentialData();
		String requiredDetails=AES.encrypt(userConfidentialDetails.getRequiredDetails(), secret);
		
		if(userDetailsMap.containsKey(userId))
		{
			List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
			UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,nameOfData,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
		}
		else
		{
			List<UserConfidentialDetails> userConfidentialDetailsList=new ArrayList<>();
			UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,nameOfData,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
		}
		printDetails(userId);
		return true;
	}
	
	public void viewUserConfidentialDetails(int userId)
	{
		if(!userDetailsMap.containsKey(userId) || userDetailsMap.get(userId).size()==0)
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
			String name=userConfidentialDetails.getNameOfConfidentialData();
			String data=AES.decrypt(userConfidentialDetails.getRequiredDetails(), secret);
			System.out.println(dataId+"\t"+name+"\t"+data);
			dataId++;
		}
		
	}
	
	public boolean editUserConfidentialDetails(int dataId,int userId,UserConfidentialDetails userConfidentialDetails)
	{
		
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		String nameOfData=userConfidentialDetails.getNameOfConfidentialData();
		String requiredDetails=AES.encrypt(userConfidentialDetails.getRequiredDetails(), secret);
		List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
		if(dataId>=userConfidentialDetailsList.size())return false;
		UserConfidentialDetails toBeAdded=new UserConfidentialDetails(userId,nameOfData,requiredDetails);
		userConfidentialDetailsList.remove(dataId);
		userConfidentialDetailsList.add(dataId, toBeAdded);
		userDetailsMap.put(userId,userConfidentialDetailsList);
		return true;
	}
	private void printDetails(int userId) 
	{
		System.out.println();
		System.out.println("Printing all the confidential details");
		int dataId=0;
		for(UserConfidentialDetails userConfidentialDetails: userDetailsMap.get(userId))
		{
			System.out.println(dataId+"\t"+userConfidentialDetails.getNameOfConfidentialData()+"\t"+userConfidentialDetails.getRequiredDetails());
			dataId++;
		}
		
	}

	public boolean deleteUserConfidentialDetails(int dataId, int userId) 
	{
		List<UserConfidentialDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
		if(dataId>=userConfidentialDetailsList.size())return false;
		userConfidentialDetailsList.remove(dataId);
		userDetailsMap.put(userId,userConfidentialDetailsList);
		return true;
	}
}
