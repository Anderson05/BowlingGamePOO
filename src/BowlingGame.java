import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
	
	public static final int MAX_PINS_PER_ROLL = 10;
	public static final int NUMBER_OF_ROLLS_PER_GAME = 10;
	public static final int NUMBER_OF_TRIES_PER_ROLL = 2;
	
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
	
	public int getRollScore(int roll_index) {
		Roll r = this.getRollAt(roll_index);
		if(r.isBonus()) {
			return r.getPinsKnocked();
		}
		if(r.isSpare()) {
			return (10 + rolls.get(roll_index+1).getFirstThrow().getPinsKnocked());
		}
		if(r.isStrike()) {
			Roll nextRoll = rolls.get(roll_index+1);
			if (nextRoll.isBonus()) { // Bonus Roll has only one throw
				return (10 + nextRoll.getFirstThrow().getPinsKnocked()
						+ rolls.get(roll_index+2).getFirstThrow().getPinsKnocked());
			}else {
				// 10 + sum of the next two throws
				if(nextRoll.isStrike()) {
					Roll nextRoll2 = rolls.get(roll_index+2);
					return 10 + nextRoll.getPinsKnocked() + nextRoll2.getFirstThrow().getPinsKnocked();
				}else {
					return 10 + nextRoll.getPinsKnocked();	
				} 
			}
		}
		return 0;
	}
}
