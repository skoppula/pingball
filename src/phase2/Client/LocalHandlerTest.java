package phase2.Client;

import org.junit.Before;
import org.junit.Test;

public class LocalHandlerTest {

	@Before
	public void setUp() throws Exception {
	}
	
	/*
	 * We didn't actually write these tests, because testing Socket connections is
	 * difficult to automate. However, the following testing protocol was implemented manually:
	 * 
	 * LocalManager
	 * Pass a string of a message from Socket into LocalManager and see whether it puts it on the queue
	 * Pass a message into the outputQueue and see whether it passes the correct string through the socket
	 * 
	 */

	@Test
	public void test() {
	    assert(true);
	}

}
