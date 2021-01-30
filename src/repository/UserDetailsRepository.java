package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cryptography.AES;
import model.UserDetails;

public class UserDetailsRepository {
	static HashMap<Integer,List<UserDetails>>userDetailsMap=new HashMap<>();
	static int totalUsers=userDetailsMap.size();
	
	public final boolean addUserDetails(UserDetails userDetails)
	{
		UserRepository userRepository = new UserRepository();
		int userId=userDetails.getUserId();
		String secret=userRepository.getUser(userId).getEmail();
		String name=AES.encrypt(userDetails.getNameOfConfidentialData(), secret);
		String requiredDetails=AES.encrypt(userDetails.getRequiredDetails(), secret);
		
		if(userDetailsMap.containsKey(userId))
		{
			List<UserDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
			int currentAdditionAt=userConfidentialDetailsList.size();
			UserDetails toBeAdded=new UserDetails(userId,currentAdditionAt,name,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
		}
		else
		{
			List<UserDetails> userConfidentialDetailsList=new ArrayList<>();
			UserDetails toBeAdded=new UserDetails(userId,0,name,requiredDetails);
			userConfidentialDetailsList.add(toBeAdded);
			userDetailsMap.put(userId,userConfidentialDetailsList);
			totalUsers++;
		}
		printDetails(userId);
		return true;
	}
	
	public final void viewUserDetails(int userId)
	{
		if(userId>=totalUsers)
		{
			System.out.println("No data yet");
			return ;
		}
		List<UserDetails> confidentialDetailsList= userDetailsMap.get(userId);
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		int dataId=0;
		for(UserDetails userDetails: confidentialDetailsList)
		{
			String name=AES.decrypt(userDetails.getNameOfConfidentialData(), secret);
			String data=AES.decrypt(userDetails.getRequiredDetails(), secret);
			System.out.println(dataId+"\t"+name+"\t"+data);
			dataId++;
		}
		
	}
	
	public final boolean editUserDetails(int dataId,int userId,UserDetails userDetails)
	{
		
		UserRepository userRepository = new UserRepository();
		String secret=userRepository.getUser(userId).getEmail();
		String name=AES.encrypt(userDetails.getNameOfConfidentialData(), secret);
		String requiredDetails=AES.encrypt(userDetails.getRequiredDetails(), secret);
		List<UserDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
		if(dataId>=userConfidentialDetailsList.size())return false;
		UserDetails toBeAdded=new UserDetails(userId,dataId,name,requiredDetails);
		userConfidentialDetailsList.remove(dataId);
		userConfidentialDetailsList.add(dataId, toBeAdded);
		userDetailsMap.put(userId,userConfidentialDetailsList);
		return true;
	}
	private void printDetails(int userId) 
	{
		System.out.println();
		System.out.println("Printing all the confidential details");
		for(UserDetails userDetails: userDetailsMap.get(userId))
		{
			System.out.println(userDetails.getNameOfConfidentialData()+"\t"+userDetails.getRequiredDetails());
		}
		
	}

	public final boolean deleteUserDetails(int dataId, int userId) 
	{
		List<UserDetails> userConfidentialDetailsList=userDetailsMap.get(userId);
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
