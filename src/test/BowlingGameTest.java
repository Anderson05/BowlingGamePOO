package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import onepoint.techtest.wanp.*;

class BowlingGameTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void testAddNewRollToTheGame() {
		BowlingGame bg = new BowlingGame("Tester");
		Roll firstRoll = new Roll(1);
		bg.addRoll(firstRoll);
		
		assertTrue(bg.getRolls().contains(firstRoll));
	}

	@Test
	public void testThrowExceptioneWhenInvalidRollSequences() throws Exception {
		BowlingGame bg = new BowlingGame("Test");
		Exception exception = assertThrows(Exception.class, () -> {       
			for(int i=0;i<14;i++) {
				Roll r = new Roll(i);
				r.setFirstThrow(new Throw(1,10));;
				bg.addRoll(r);
			}
			int score = bg.getGameScore();
		 });
		 String expectedMessage = BowlingGame.ERROR_TOO_MUCH_ROLLS;
		 String exceptionMessage = exception.getMessage();
		 
		 assertTrue(exceptionMessage.contains(expectedMessage));
	}
	
	@Test
	public void testTenRollSequencesOfStrike() throws Exception {
		BowlingGame bg = new BowlingGame("Test");
		for(int i=0;i<12;i++) {
			Roll r = new Roll(i);
			r.setFirstThrow(new Throw(1,10));
			bg.addRoll(r);
		}
		assertEquals(300, bg.getGameScore());
	}
	

	@Test
	public void testTenRollSequencesOfNinePins() throws Exception {
		BowlingGame bg = new BowlingGame("Test");
		for(int i=0;i<10;i++) {
			Roll r = new Roll(i);
			r.setFirstThrow(new Throw(1,9));
			r.setSecondThrow(new Throw(2,0));
			bg.addRoll(r);
		}
		assertEquals(90, bg.getGameScore());
	}
	
	@Test
	public void testTenRollSequencesOfFiveAndSpare() throws Exception {
		BowlingGame bg = new BowlingGame("Test");
		for(int i=0;i<11;i++) {
			Roll r = new Roll(i);
			r.setFirstThrow(new Throw(1,5));
			if(i<10) {
				r.setSecondThrow(new Throw(2,5));
			}
			bg.addRoll(r);
		}
		assertEquals(150, bg.getGameScore());
	}

}
