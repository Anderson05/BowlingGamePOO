package onepoint.techtest.wanp;

public class Roll {
	private static final String ERROR_ROLL_NOT_INITIALIZED = "ERROR: The roll is not yet initialized";
	private int number;
	private int pinsKnocked;
	private Throw firstThrow;
	private Throw secondThrow;

	/**
	 * a Roll is a turn or a frame, it can have one or two throws, depending on the
	 * result of the first throw
	 * 
	 * @param number
	 */
	public Roll(int number) {
		super();
		this.number = number;
		this.pinsKnocked = 0;
	}

	public int getPinsKnocked() throws Exception {
		if (null == this.firstThrow) {
			throw new Exception(ERROR_ROLL_NOT_INITIALIZED);
		} else if (null == this.secondThrow) {
			return this.firstThrow.getPinsKnocked();
		} else {
			return (this.firstThrow.getPinsKnocked() + this.secondThrow.getPinsKnocked());
		}
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

	public RollResult getResult() {
		if (this.firstThrow.getPinsKnocked() == BowlingGame.MAX_PINS_PER_ROLL) {
			return RollResult.STRIKE;
		} else if (null == this.secondThrow) {
			return RollResult.values()[this.firstThrow.getPinsKnocked()];
		} else {
			int totalHits = this.firstThrow.getPinsKnocked() + this.secondThrow.getPinsKnocked();
			if (totalHits == BowlingGame.MAX_PINS_PER_ROLL) {
				return RollResult.SPARE;
			} else {
				return RollResult.values()[totalHits];
			}

		}
	}

	public boolean isBonus() { // a bonus is a roll that appear in addition for scoring purpose.
		return this.number > (BowlingGame.NUMBER_OF_ROLLS_PER_GAME - 1); // -1 for Java Zero-indexing
	}

	// a bonus cannot be a strike or a spare
	public boolean isStrike() {
		return this.isBonus() ? false : (this.getResult() == RollResult.STRIKE);
	}

	public boolean isSpare() {
		return this.isBonus() ? false : (this.getResult() == RollResult.SPARE);
	}

	public String getDetails() {
		if (null != this.secondThrow) {
			return "[" + this.firstThrow.getPinsKnocked() + "/" + this.secondThrow.getPinsKnocked() + "]";
		} else {
			return "[" + this.firstThrow.getPinsKnocked() + "]";
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Roll<" + this.number + "> [Hit: " + this.pinsKnocked + " pins," + " Result:" + this.getResult() + " ]";
	}
}
