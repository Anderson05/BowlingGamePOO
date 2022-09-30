import java.util.Scanner;

public class Play {

	private BowlingGame party;
	
	private static final String ERROR_NOT_ENOUGHT_ROLLS = "ERROR: NOT ENOUGH ROLLS";
	private static final String ERROR_TOO_MUCH_ROLLS = "ERROR: TOO MUCH ROLLS";
	
	public static void main(String[] args) throws Exception {
	
		System.out.println("Player Name :");
		Scanner sc = new Scanner(System.in);
		BowlingGame game = new BowlingGame("Demo");
//		System.out.println(RollResult.values()[12]);
//		System.out.println(RollResult.SPARE.ordinal());

		int index=0, bonusStrike=0, bonusSpare=0;
		String continue_input = "y";
		System.out.println("Fill in the sequence of rolls "+ continue_input);
		do {
			System.out.println("Roll #"+ (index+1));
			Roll roll = new Roll(index);
			System.out.println("Number of pins knocked in first throw");		
			int firstThrow = sc.nextInt();
			roll.setFirstThrow(new Throw(1, firstThrow));
			
			if(index <= BowlingGame.NUMBER_OF_ROLLS_PER_GAME) { // Force all bonus roll has only one throw
				if (firstThrow < BowlingGame.MAX_PINS_PER_ROLL) {
					System.out.println("Number of pins knocked in second throw");
					int secondThrow = sc.nextInt();
					roll.setSecondThrow(new Throw(2, secondThrow));
				
				}
			}
			game.addRoll(roll);
			index+=1;
			System.out.println("Add another roll ? (y/n)");			
			continue_input = sc.next();			
			
		} while (continue_input.equalsIgnoreCase("y"));
		
		//TODO : Check valid sequence
		if(game.numberOfRolls() < BowlingGame.NUMBER_OF_ROLLS_PER_GAME) {
			throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
		}else if (game.numberOfRolls() > (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+2)) {
			throw new Exception(ERROR_TOO_MUCH_ROLLS);
		}else {
			
			Roll lastRoll = game.getRollAt(BowlingGame.NUMBER_OF_ROLLS_PER_GAME);
			
			if( lastRoll.isSpare() & (game.numberOfRolls() != (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+1) )) {
				throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
			}
			if( lastRoll.isStrike() & (game.numberOfRolls() != (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+2) )) {
				throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
			}
			
			// Compute game score
			System.out.println("MAX ROLLS : " + BowlingGame.NUMBER_OF_ROLLS_PER_GAME);
			for(int i=0;i<BowlingGame.NUMBER_OF_ROLLS_PER_GAME;i++) {
				System.out.println(game.getRollAt(i).getNumber()
						+ " : " + game.getRollAt(i).getPinsKnocked() + " Pins"
						+ " | " + game.getRollAt(i).getResult()
						+ " | " + game.getRollScore(i));
			}
		}
		
		
		
		// Check number of frames...
	}

}
