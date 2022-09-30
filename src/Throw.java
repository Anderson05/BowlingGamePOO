
public class Throw {
	
    private static final String ERROR_WRONG_PINS_VALUE = "Invalid number of pins knocked down";
	
	private int number;
	private int pinsKnocked;
	
	public Throw() {
	}

	public Throw(int number, int pinsKnocked) {
		super();
		this.number = number;
		this.pinsKnocked = pinsKnocked;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getPinsKnocked() {
		return pinsKnocked;
	}

	public void setPinsKnocked(int pinsKnocked) throws Exception {
		if (pinsKnocked > BowlingGame.MAX_PINS_PER_ROLL) {
			throw new Exception(ERROR_WRONG_PINS_VALUE);
		}
		this.pinsKnocked = pinsKnocked;
	}
	
}
