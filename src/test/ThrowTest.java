package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import onepoint.techtest.wanp.Throw;

class ThrowTest {

	@Test
	void testThrowNumber() {
		Throw th = new Throw(1,0);
		assertEquals(1, th.getNumber());
	}
	
	@Test
	void testThrowNumberNotGreaterThan2() {
		Throw t = new Throw(2,0);
		assertTrue((t.getNumber()>0) & (t.getNumber()<=2));
	}

}
