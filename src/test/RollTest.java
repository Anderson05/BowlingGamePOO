package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import onepoint.techtest.wanp.*;

class RollTest {

	@Test
	void testRoll() {
		Roll r = new Roll(1);
		assertInstanceOf(Roll.class, r);
	}

	@Test
	void testGetPinsKnocked() throws Exception {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1,3));
		r.setSecondThrow(new Throw(2, 4));
		assertEquals(7, r.getPinsKnocked());
	}

	@Test
	void testGetNumber() {
		Roll r = new Roll(2);
		assertEquals(2, r.getNumber());
	}

	@Test
	void testRollNumber4IsBonus() {
		Roll r = new Roll(4);
		assertFalse(r.isBonus());
	}

	@Test
	void testRollNumber12IsBonus() {
		Roll r = new Roll(12);
		assertTrue(r.isBonus());
	}

	@Test
	void testRollNumber3IsBonus() {
		Roll r = new Roll(3);
		assertFalse(r.isBonus());
	}
	
	@Test
	void testFirsThrowHitTenPinsIsStrike() {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1, 10));
		assertEquals(10, r.getFirstThrow().getPinsKnocked());
	}

	@Test
	void testFirsThrowHitTenPinsIsSpare() {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1, 10));
		assertEquals(10, r.getFirstThrow().getPinsKnocked());
	}

	@Test
	void testRollHitAllPinsInTwoTriesIsSpare() throws Exception {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1, 4));
		r.setSecondThrow(new Throw(2, 6));
		assertEquals(10, r.getPinsKnocked());
	}

	@Test
	void testRollIsStrike() {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1,10));
		assertEquals(RollResult.STRIKE, r.getResult());
	}
	
	@Test
	public void testThrowNullException_secondThrowIsNullWhenRollIsStrike() throws Exception {
		Roll r = new Roll(1);
		r.setFirstThrow(new Throw(1,10));
		
		assertThrows(NullPointerException.class, () -> {       
			int second = r.getSecondThrow().getPinsKnocked();
		});
	}
}
