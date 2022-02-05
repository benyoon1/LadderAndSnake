import java.util.Scanner;

/**
 * Name and ID: Chul Bin YOON 40197998
 * COMP249
 * Assignment # 1
 * Due Date: February 7, 2021
 */

public class PlayLadderAndSnake {

	/**
	 * This program prompts user for number of players in the game
	 * and initiates Ladder and Snake game.
	 * @param args args not used.
	 */
	public static void main(String[] args) {
		int attempt = 0;
		boolean attemptLoop = true;
		int numOfPlayers = 0;
		Scanner keyIn = new Scanner(System.in);
		
		System.out.print("Enter the # of players for your game – "
				+ "\nNumber must be between 2 and 4 inclusively: ");
		
		while (attemptLoop) {
			numOfPlayers = keyIn.nextInt();
			attempt++;
			
			if (numOfPlayers >= 2 && numOfPlayers <= 4) {
				attemptLoop = false;
			}
			else if (attempt == 3) {
				System.out.print("\nBad Attempt " + attempt + " - Invalid # of players. "
						+ "\nPlease enter a # between 2 and 4 inclusively. This is your last attempt: ");
			}
			else if (attempt == 4) {
				System.out.print("\nBad Attempt " + attempt + "! You have exhausted all your chances. "
						+ "Program will terminate!");
				System.exit(0);
			}
			else {
				System.out.print("\nBad Attempt " + attempt + " - Invalid # of players. "
						+ "\nPlease enter a # between 2 and 4 inclusively: ");
			}
		}
		
		LadderAndSnake game = new LadderAndSnake(numOfPlayers);
		game.play();
		
		keyIn.close();
		
	}

}
