import java.util.Scanner;

public class Play {
	
	private static final String ERROR_NOT_ENOUGHT_ROLLS = "ERROR: NOT ENOUGH ROLLS";
	private static final String ERROR_TOO_MUCH_ROLLS = "ERROR: TOO MUCH ROLLS";
	
	
	public static void main(String[] args) throws Exception {
	
		System.out.println("Player Name :");
		Scanner sc = new Scanner(System.in);
		String pname = sc.next();	
		BowlingGame game = new BowlingGame(pname);

		int index = 0;
		String continue_input = "y";
		System.out.println("Fill in the sequence of " + BowlingGame.NUMBER_OF_ROLLS_PER_GAME +" rolls ");
		do {
			System.out.println("Roll #"+ (index+1));
			Roll roll = new Roll(index);
			int firstThrow = 0;	
			do{
				System.out.println("Number of pins knocked in first throw [0-"+BowlingGame.MAX_PINS_PER_ROLL+"]");
				firstThrow = sc.nextInt();
	        }while((firstThrow < 0 || firstThrow > BowlingGame.MAX_PINS_PER_ROLL));
			
			roll.setFirstThrow(new Throw(1, firstThrow));
			
			if(index < BowlingGame.NUMBER_OF_ROLLS_PER_GAME) { // Force all bonus roll has only one throw
				if (firstThrow < BowlingGame.MAX_PINS_PER_ROLL) {
					int pinsRemaining = BowlingGame.MAX_PINS_PER_ROLL - firstThrow;
					int secondThrow = 0;
					do{
						System.out.println("Number of pins knocked in second throw [0-"+pinsRemaining+"]");
						secondThrow = sc.nextInt();
					}while((secondThrow < 0  || secondThrow > pinsRemaining));
					roll.setSecondThrow(new Throw(2, secondThrow));
				}
			}
			game.addRoll(roll);
			index+=1;
			
			System.out.println("Add another roll ? (y/n)");			
			continue_input = sc.next();			
			
		} while (!continue_input.equalsIgnoreCase("n"));
		
		// Check for valid sequence of rolls
		if(game.numberOfRolls() < BowlingGame.NUMBER_OF_ROLLS_PER_GAME) {
			throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
		}else if (game.numberOfRolls() > (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+2)) {
			throw new Exception(ERROR_TOO_MUCH_ROLLS);
		}else {
			
			Roll lastRoll = game.getRollAt(BowlingGame.NUMBER_OF_ROLLS_PER_GAME-1);
			
			if( lastRoll.isSpare() & (game.numberOfRolls() != (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+1) )) {
				throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
			}
			if( lastRoll.isStrike() & (game.numberOfRolls() != (BowlingGame.NUMBER_OF_ROLLS_PER_GAME+2) )) {
				throw new Exception(ERROR_NOT_ENOUGHT_ROLLS);
			}
			
			// Compute and display game score
			//int gameScore = 0;
			System.out.println("Game summary \n "
					+ "Player : " + game.getPlayer() + " - MAX ROLLS : " + BowlingGame.NUMBER_OF_ROLLS_PER_GAME);
			for(int i=0;i<BowlingGame.NUMBER_OF_ROLLS_PER_GAME;i++) {
				System.out.println(
						"Roll #"+ game.getRollAt(i).getNumber()
						+ " : " + game.getRollAt(i).getPinsKnocked()
						+ " - " + game.getRollAt(i).getDetails() + " Pins"
						+ " | " + game.getRollAt(i).getResult()
						+ " | " + game.getRollScore(i));
				//gameScore += game.getRollScore(i);
			}
			System.out.println("Final game result : "+ game.getGameScore() +" Points");
		}
		
		
		
		
	}

}
