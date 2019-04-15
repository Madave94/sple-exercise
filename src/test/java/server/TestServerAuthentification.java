package server;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class TestServerAuthentification {
	
	@Test
	public void testLoginFails() {
		String entered = "12345";
		assertThat(entered, is(not(equalTo(new ServerAuthentification().login()))));
	}
	
	@Test
	public void testLoginWorks() {
		String entered = "hodor";
		assertThat(entered, is(equalTo(new ServerAuthentification().login())));
	}
	
}
