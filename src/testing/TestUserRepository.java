package testing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Assert;
import model.User;
import repository.UserRepository;
import org.junit.jupiter.api.Test;
public class TestUserRepository {
	
	@BeforeAll
	public static void successfullyAddedUser()
	{
		int expected=0;
		UserRepository userRepository=new UserRepository();
		User user=new User("srimathi@yahoo.com","21Twoone@");
		int actual=userRepository.addUser(user);
		Assert.assertEquals(expected, actual);
		expected++;
		user=new User("srimathi.s@rajalakshmi.edu.in","21Twoone@");
		actual=userRepository.addUser(user);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void properlySignedUpUser()
	{
		int expected=0;
		UserRepository userRepository=new UserRepository();
		User user=new User("srimathi@yahoo.com","21Twoone@");
		int actual=userRepository.isSignedUpUser(user);
		Assert.assertEquals(expected, actual);
		expected++;
		user=new User("srimathi.s@rajalakshmi.edu.in","21Twoone@");
		actual=userRepository.isSignedUpUser(user);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void notSignedUpUser()
	{
		int expected=-1;
		UserRepository userRepository=new UserRepository();
		User user=new User("s@yahoo.com","21Twoone@");
		int actual=userRepository.isSignedUpUser(user);
		Assert.assertEquals(expected, actual);
		user=new User("srimathi.s@edu.in","21Twoone@");
		actual=userRepository.isSignedUpUser(user);
		Assert.assertEquals(expected, actual);
	}
}
