
public class Roll {
	private int number;
	private int pinsKnocked;
	private Throw firstThrow;
	private Throw secondThrow;
	private int nextRoll;
	private RollResult result;
	
	public Roll(int number) {
		super();
		this.number = number;
		this.pinsKnocked = 0;
	}
	
	public int getPinsKnocked() {
		return pinsKnocked;
	}

	public void setPinsKnocked(int pinsKnocked) {
		this.pinsKnocked = pinsKnocked;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Throw getFirstThrow() {
		return firstThrow;
	}

	public void setFirstThrow(Throw firstThrow) {
		this.firstThrow = firstThrow;
		this.pinsKnocked += firstThrow.getPinsKnocked();
	}

	public Throw getSecondThrow() {
		return secondThrow;
	}

	public void setSecondThrow(Throw secondThrow) {
		this.secondThrow = secondThrow;
		this.pinsKnocked += this.secondThrow.getPinsKnocked();
	}

	public int getNextRoll() {
		return nextRoll;
	}

	public void setNextRoll(int nextRoll) {
		this.nextRoll = nextRoll;
	}
	
	public RollResult getResult() {
		if (this.firstThrow.getPinsKnocked() == BowlingGame.MAX_PINS_PER_ROLL) {
			return RollResult.STRIKE;
		}else if(null == this.secondThrow){
			return RollResult.values()[this.firstThrow.getPinsKnocked()];
		}else {
			int totalHits = this.firstThrow.getPinsKnocked() + this.secondThrow.getPinsKnocked();
			if (totalHits == BowlingGame.MAX_PINS_PER_ROLL) {
				return RollResult.SPARE;
			}else {
				return RollResult.values()[totalHits];
			}
			
		}
	}

	public boolean isBonus() {
		return this.number > BowlingGame.NUMBER_OF_ROLLS_PER_GAME;
	}
	
	public boolean isStrike() {
		return this.isBonus() ? false : (this.getResult() == RollResult.STRIKE);
 	}

	public boolean isSpare() {
		return this.isBonus() ? false : (this.getResult() == RollResult.SPARE);
 	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Roll<"+ this.number +"> [Hit: "+this.pinsKnocked+" pins,"
				+ " Result:"+ this.getResult() +" ]";
	}
}
