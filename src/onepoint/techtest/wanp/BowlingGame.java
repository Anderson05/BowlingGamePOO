package onepoint.techtest.wanp;
import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

	public static final int MAX_PINS_PER_ROLL = 10;
	public static final int NUMBER_OF_ROLLS_PER_GAME = 10;
	public static final int NUMBER_OF_TRIES_PER_ROLL = 2;
	public static final String ERROR_NOT_ENOUGHT_ROLLS = "ERROR: NOT ENOUGH ROLLS";
	public static final String ERROR_TOO_MUCH_ROLLS = "ERROR: TOO MUCH ROLLS";

	private String player;
	private List<Roll> rolls;

	public BowlingGame() {
		this.player = "Player1";
		this.rolls = new ArrayList<Roll>();
	}

	public BowlingGame(String playerName) {
		this.player = playerName;
		this.rolls = new ArrayList<Roll>();
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public List<Roll> getRolls() {
		return rolls;
	}

	public void setRolls(List<Roll> rolls) {
		this.rolls = rolls;
	}

	public void addRoll(Roll roll) {
		this.rolls.add(roll);
	}

	public int numberOfRolls() {
		return this.getRolls().size();
	}

	public Roll getRollAt(int index) throws IndexOutOfBoundsException {
		return this.getRolls().get(index);
	}

	public int getRollScore(int roll_index) throws Exception {
		Roll r = this.getRollAt(roll_index);

		if (r.isSpare()) { // Player hits all pins in two tries
			return (10 + this.getRollAt(roll_index + 1).getFirstThrow().getPinsKnocked());
		} else if (r.isStrike()) {
			Roll nextRoll = this.getRollAt(roll_index + 1);
			if (nextRoll.isBonus()) { // Bonus Roll has only one throw
				return (10 + nextRoll.getFirstThrow().getPinsKnocked()
						+ this.getRollAt(roll_index + 2).getFirstThrow().getPinsKnocked());
			} else {
				// 10 + sum of the next two throws
				if (nextRoll.isStrike()) {
					Roll nextRoll2 = this.getRollAt(roll_index + 2);
					return 10 + nextRoll.getPinsKnocked() + nextRoll2.getFirstThrow().getPinsKnocked();
				} else {
					return 10 + nextRoll.getPinsKnocked();
				}
			}
		} else { // It is a bonus roll or a roll where the player failed to hits all the pins
			return r.getPinsKnocked(); // Simple sum of next two tries
		}
	}

	public int getGameScore() throws Exception {
		if(this.getRolls().size()>(NUMBER_OF_ROLLS_PER_GAME+2)){
			throw new Exception(ERROR_TOO_MUCH_ROLLS);
		}else if (this.getRolls().size() < NUMBER_OF_ROLLS_PER_GAME) {
			throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
		}
		int score = 0;
		for (int i = 0; i < NUMBER_OF_ROLLS_PER_GAME; i++) {
			score += getRollScore(i);
		}
		return score;
	}
}
