package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cryptography.AES;
import model.User;

public class UserRepository {
	private static List<User> userList=new ArrayList<User>();
	private static Map<String,Integer> emailMap=new  HashMap<>();
	private static int countOfUsers=0;
	public final int addUser(User user)
	{
		if(emailMap.containsKey(user.getEmail()))
		{
			return -1;
		}
		User userElement=new User(countOfUsers,user.getEmail(),AES.encrypt(user.getPassword(), user.getEmail()));
		userList.add(userElement);
		emailMap.put(user.getEmail(),countOfUsers);
		printDetails();
		countOfUsers++;
		return countOfUsers-1;
	}
	private void printDetails() {
		System.out.println();
		System.out.println("Printing all the user details");
		for(User user:userList)
		{
			System.out.println(user.getId()+"\t"+user.getEmail()+"\t"+user.getPassword());
		}
		
	}
	public User getUser(int userId)
	{
		return userList.get(userId);
	}
	public int getUserId(User user)
	{
		return emailMap.get(user.getEmail());
	}
	public final int isSignedUpUser(User user)
	{
		if(!emailMap.containsKey(user.getEmail()))
		{
			return -1;
		}
		Integer userId=emailMap.get(user.getEmail());
		User userElement=userList.get(userId);
		String decryptedPassword=AES.decrypt(userElement.getPassword(), userElement.getEmail());
		if(decryptedPassword.equals(user.getPassword()))return userId;
		return -1;
	}
	
	
}
