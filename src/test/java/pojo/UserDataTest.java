package pojo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pojo.UserData;

public class UserDataTest {

	UserData userData;
	
	@Before
    public void setUp() {
        userData = new UserData();
        userData.setName("test-login");
        userData.setMail("test-login");
        userData.setPassword("passwd");
    }

	@Test
	public void testGetName() {
		assertEquals("test-login", userData.getName());
	}

	@Test
	public void testSetName() {
		userData.setName("newtest-login");
        assertEquals("newtest-login", userData.getName());
	}

	@Test
	public void testGetMail() {
		assertEquals("test-login", userData.getMail());
	}

	@Test
	public void testSetMail() {
		userData.setMail("newtest-login");
        assertEquals("newtest-login", userData.getMail());
	}

	@Test
	public void testGetPassword() {
		assertEquals("passwd", userData.getPassword());
	}

	@Test
	public void testSetPassword() {
		userData.setPassword("newpasswd");
        assertEquals("newpasswd", userData.getPassword());
	}

	@Test
	public void testToString() {
		String expected = "[Name: test-login, mail: test-login]";
		assertEquals(expected, userData.toString());
	}

}
