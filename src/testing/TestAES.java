package testing;
import org.junit.Assert;
import org.junit.Test;

import cryptography.AES;
public class TestAES {
	
	@Test
	public void correctlyEncryptedAndDecrypted()
	{
		String expected="Hello";
		String secret="123467729380981";
		String encrypted=AES.encrypt(expected, secret);
		String actual=AES.decrypt(encrypted, secret);
		Assert.assertEquals(expected,actual);
		
		
		 expected="srimathi";
		 secret="123467729380981";
		 encrypted=AES.encrypt(expected, secret);
		 actual=AES.decrypt(encrypted, secret);
		 Assert.assertEquals(expected,actual);
	}
	
}
