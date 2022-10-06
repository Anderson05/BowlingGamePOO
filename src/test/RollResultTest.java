package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import onepoint.techtest.wanp.RollResult;

class RollResultTest {

	@Test
	void testGetOrdinalValue() {
		assertAll(
            () -> assertEquals(0, RollResult.ZERO.ordinal()),
            () -> assertEquals(1, RollResult.ONE.ordinal()),
            () -> assertEquals(2, RollResult.TWO.ordinal()),
            () -> assertEquals(3, RollResult.THREE.ordinal()),
            () -> assertEquals(4, RollResult.FOUR.ordinal()),
            () -> assertEquals(5, RollResult.FIVE.ordinal()),
            () -> assertEquals(6, RollResult.SIX.ordinal()),
            () -> assertEquals(7, RollResult.SEVEN.ordinal()),
            () -> assertEquals(8, RollResult.EIGHT.ordinal()),
            () -> assertEquals(9, RollResult.NINE.ordinal()),
            () -> assertEquals(10, RollResult.TEN.ordinal()),
            () -> assertEquals(11, RollResult.SPARE.ordinal()),
            () -> assertEquals(12, RollResult.STRIKE.ordinal())
        );
	}

}
