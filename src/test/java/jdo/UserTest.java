package jdo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jdo.User;

public class UserTest {

	private User user;

	@Before
	public void setUp() {
		user = new User("test-login", "test-login", "passwd");
	}

	@Test
	public void testGetName() {
		assertEquals("test-login", user.getName());
	}

	@Test
	public void testGetMail() {
		assertEquals("test-login", user.getMail());
	}

	@Test
	public void testGetPassword() {
		assertEquals("passwd", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("newpasswd");
		assertEquals("newpasswd", user.getPassword());
	}

	@Test
	public void testToString() {
		String expected = "User: name --> test-login, mail -->  test-login";
		assertEquals(expected, user.toString());
	}

}
