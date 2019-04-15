package server;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class TestServerAuthentification {
	
	@Test
	public void testLoginFails() {
		String entered = "12345";
		assertThat(false, is(not(equalTo(new ServerAuthentification().login(entered)))));
	}
	
	@Test
	public void testLoginWorks() {
		String entered = "hodor";
		assertThat(true, is(equalTo(new ServerAuthentification().login(entered))));
	}
	
}
