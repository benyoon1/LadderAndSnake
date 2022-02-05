import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Name and ID: Chul Bin YOON 40197998
 * COMP249
 * Assignment # 1
 * Due Date: February 7, 2021
 */

public class LadderAndSnake {
	private int numPlayers;
	private int[][] board = new int[10][10];
	private int numBoard = 101;
	private String[][][] playerBoard = new String[10][10][4];
	private Player[] players;
	private Player winner;
	private boolean gameover = false;
	
	/**
	 * Constructor which determines the number of players. 
	 * Minimum of 2 and maximum of 4.
	 * @param numPlayers number of players
	 */
	public LadderAndSnake(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	/**
	 * Default constructor - 2 players
	 */
	public LadderAndSnake() {
		this.numPlayers = 2;
	}
	
	/**
	 * Creates Player object with initial dice scores to determine the playing order.
	 */
	public void createPlayers() {
		if (numPlayers == 4) {
			players = new Player[4];
			players[0] = new Player("P1", flipDice());
			players[1] = new Player("P2", flipDice());
			players[2] = new Player("P3", flipDice());
			players[3] = new Player("P4", flipDice());
		}
		else if (numPlayers == 3) {
			players = new Player[3];
			players[0] = new Player("P1", flipDice());
			players[1] = new Player("P2", flipDice());
			players[2] = new Player("P3", flipDice());
		}
		else {
			players = new Player[2];
			players[0] = new Player("P1", flipDice());
			players[1] = new Player("P2", flipDice());
		}
	}
	
	/**
	 * Flips dice.
	 * @return random number between 1 and 6
	 */
	public static int flipDice() {
		return (int)(Math.random() * 6 + 1);
	}
	
	/**
	 * Flips dice for every player and move their position according to their values.
	 * Also sets player in a separate 3D array called playerBoard, in order to print
	 * their position along 2D array called board.
	 * Also checks if player is at 100, if so, announce winner and end game.
	 * Then finally, draws game board and resets playerBoard array.
	 * 
	 * @param players Player object array
	 */
	public void flipPlayers(Player[] players) {
		for (int i = 0; i < players.length; i++) {
			int diceValue = flipDice();
			System.out.print(players[i].getPlayerName() +  " got a dice value of " 
			+ diceValue + "; ");
	
			movePlayer(players[i], diceValue);
			setPlayer(players[i]);
			
			if (players[i].getPosition() == 100) {
				winner = players[i];
				announceWinner();
				break;
			}
		}
		System.out.println();
		
		drawBoard();
		resetPlayerBoard();
	}
	
	/**
	 * Moves player according to their dice value.
	 * If player steps on a ladder or a snake, set position accordingly 
	 * and print message. 
	 * @param player Player object
	 * @param diceValue dice value
	 */
	public void movePlayer(Player player, int diceValue) {
		
		player.setPosition(player.getPosition() + diceValue);
		
		// if player position exceeds 100
		if (player.getPosition() > 100) {
			player.setPosition(200 - player.getPosition());
		}
		// Ladders
		if (player.getPosition() == 1) {
			player.setPosition(38);
			System.out.print("gone to square 1 then up to square 38");
		}
		else if (player.getPosition() == 4) {
			player.setPosition(14);
			System.out.print("gone to square 4 then up to square 14");
		}
		else if (player.getPosition() == 9) {
			player.setPosition(31);
			System.out.print("gone to square 9 then up to square 31");
		}
		else if (player.getPosition() == 21) {
			player.setPosition(42);
			System.out.print("gone to square 21 then up to square 42");
		}
		else if (player.getPosition() == 28) {
			player.setPosition(84);
			System.out.print("gone to square 28 then up to square 84");
		}
		else if (player.getPosition() == 36) {
			player.setPosition(44);
			System.out.print("gone to square 36 then up to square 44");
		}
		else if (player.getPosition() == 51) {
			player.setPosition(67);
			System.out.print("gone to square 51 then up to square 67");
		}
		else if (player.getPosition() == 71) {
			player.setPosition(91);
			System.out.print("gone to square 71 then up to square 91");
		}
		else if (player.getPosition() == 80) {
			player.setPosition(100);
			System.out.print("gone to square 80 then up to square 100");
		}
		// Snakes
		else if (player.getPosition() == 16) {
			player.setPosition(6);
			System.out.print("gone to square 16 then down to square 6");
		}
		else if (player.getPosition() == 79) {
			player.setPosition(19);
			System.out.print("gone to square 79 then down to square 19");
		}
		else if (player.getPosition() == 48) {
			player.setPosition(30);
			System.out.print("gone to square 48 then down to square 30");
		}
		else if (player.getPosition() == 64) {
			player.setPosition(60);
			System.out.print("gone to square 64 then down to square 60");
		}
		else if (player.getPosition() == 93) {
			player.setPosition(68);
			System.out.print("gone to square 93 then down to square 68");
		}
		else if (player.getPosition() == 95) {
			player.setPosition(24);
			System.out.print("gone to square 95 then down to square 24");
		}
		else if (player.getPosition() == 97) {
			player.setPosition(76);
			System.out.print("gone to square 97 then down to square 76");
		}
		else if (player.getPosition() == 98) {
			player.setPosition(78);
			System.out.print("gone to square 98 then down to square 78");
		}
		else {
			System.out.print("now in square " + player.getPosition());
		}
		
		System.out.println();
	}
	
	/**
	 * Sets player in 3D playerBoard.
	 * Since the board is composed of 2 rows; left and right directions, 
	 * we need two formulas to calculate the appropriate playerBoard value 
	 * for each player's position.
	 * 
	 * PlayerBoard's array values are set according to their position in the map.
	 * Ex: 100 is [0][0], 1 is [0][9] and 10 is [9][9].
	 * 
	 * i starts with rows from 1-10 until 90-100.
	 * i is the tens value in a given position, from bottom to top.
	 * 
	 * j starts with rows from 100-91 until 1-10.
	 * j is the tens value in a given position, from top to bottom.
	 * 
	 * k serves to show players that are in the same tile.
	 * 
	 * If a given row is a right direction, posOdd is the ones value in a given position.
	 * If a given row is a left direction, posEven is the ones value in a given position.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		int pos = player.getPosition(); 
		int posOdd;
		int posEven;
		
		for (int i = 0, j = 9; j >= 0; i += 10, j--) {
			posOdd = pos - (i + 1);
			posEven = i + 10 - pos;
			
			if (pos > i && pos <= i + 10) {  // ex. if pos > 10 && pos <= 20 for row 10
				if (j % 2 == 1) { // for rows 1, 21, 41, 61 and 81
					for(int k = 0; k < 4; k++) {
					    if (playerBoard[posOdd][j][k] == null) {
					    	// ex. for row 21: [pos - (20 + 1)][j] because players move towards right
					    	playerBoard[posOdd][j][k] = player.getPlayerName();
					        break;
					    }
					}
				}
				else if (j % 2 == 0) { // for rows 20, 40, 60, 80 and 100
					for(int k = 0; k < 4; k++) {
					    if (playerBoard[posEven][j][k] == null) {
					    	// ex. for row 40: [(40 + 10) - pos][j] because players move towards left
					    	playerBoard[posEven][j][k] = player.getPlayerName();
					        break;
					    }
					}
				}
			}
		}
		
	}
	
	/**
	 * Prints player array value in playerBoard array.
	 */
	public void printPlayerPosition() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 4; k++) {
					if (playerBoard[i][j][k] == "P1") {
						System.out.println("P1 is at: " + i + ", " + j);
						
					}
					if (playerBoard[i][j][k] == "P2") {
						System.out.println("P2 is at: " + i + ", " + j);
						
					}
					if (playerBoard[i][j][k] == "P3") {
						System.out.println("P3 is at: " + i + ", " + j);
						
					}
					if (playerBoard[i][j][k] == "P4") {
						System.out.println("P4 is at: " + i + ", " + j);
					}
				}
			}
		}
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Sets all values in playerBoard array to null.
	 */
	public void resetPlayerBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 4; k++) {
					playerBoard[i][j][k] = null;
				}
			}
		}
	}
	
	/**
	 * Draws game board in console.
	 * There are 30 lines in total that are reserved for the game board.
	 * For each line, this method checks the conditions and prints appropriate 
	 * string.
	 * It starts with 100-91 row, then players row if there are any, 
	 * then lastly 1-10 row.
	 * To print player position, the method checks if given array value is null.
	 * If so, print tab. If not, it prints players in its tile.
	 */
	public void drawBoard() {
		numBoard = 101;
		
		System.out.println("\n\n--------------------------------Game Board-----------------------------------\n\n");
		
		for (int i = 0; i < 30; i++) {
			if (i % 6 == 0) { // for rows 20, 40, 60, 80 and 100 
				for (int j = 0; j < 10; j++) {
					board[i/3][j] = j + 1;
					System.out.print(numBoard - board[i/3][j] + "\t");
					
				}
				System.out.println();
				numBoard -= 10;
			}
			else if ((i - 3) % 6 == 0) { // for rows 1, 21, 41, 61 and 81
				for (int j = 10; j > 0; j--) {
					board[i/3][j - 1] = j;
					System.out.print(numBoard - board[i/3][j - 1] + "\t");
				
				}
				System.out.println();
				numBoard -= 10;
			}

			else {
				int p = i / 3;
				
				for (int k = 0; k < 10; k++) {
					if (playerBoard[k][p][0] == null) {
						System.out.print("\t");
					}
					else if (playerBoard[k][p][3] != null) {
						if ((i - 1) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][0] + "," + playerBoard[k][p][1] + "\t");
						}
						if ((i - 2) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][2] + "," + playerBoard[k][p][3] + "\t");
						}
						else {
							System.out.print("\t");
						}
					}
					else if (playerBoard[k][p][2] != null) {
						if ((i - 1) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][0] + "," + playerBoard[k][p][1] + "\t");
						}
						if ((i - 2) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][2] + "\t");
						}
						else {
							System.out.print("\t");
						}
					}
					else if (playerBoard[k][p][1] != null) {
						if ((i - 1) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][0] + "," + playerBoard[k][p][1] + "\t");
						}
						else {
							System.out.print("\t");
						}
					}
					else if (playerBoard[k][p][0] != null) {
						if ((i - 1) % 3 == 0) {
							System.out.print("  " + playerBoard[k][p][0] + "\t");
						}
						else {
							System.out.print("\t");
						}
					}
				}
				System.out.println();
				
			}
			
		}
		System.out.println("\n-----------------------------------------------------------------------------\n\n");
	}
	
	/**
	 * Initiates core engine of the game.
	 * Also prompts user to continue for each turn.
	 */
	public void play() {
		printWelcomeMessage();
		createPlayers();
		playerOrder();
		int count = 0;
		Scanner keyIn = new Scanner(System.in);
		
		while (!gameover) {
			System.out.println("\nPlease enter 1 or any key to continue.");
			System.out.print("If you would like to quit, please enter 0: ");
			int enter = keyIn.nextInt();
			if (enter == 0) {
				gameover = true;
				break;
			}
			else {
				System.out.println("\n\n-------------------------------------------------------------------");
				System.out.println("Turn " + ++count + ":\n");
				flipPlayers(players);
			}
		}
		
		System.out.println("Game Over! Thanks for playing!");
		
		keyIn.close();

	}
	
	/**
	 * Announces winner of the game.
	 */
	public void announceWinner() {
		System.out.println("\nWinner is: " + winner.getPlayerName());
		gameover = true;
	}
	
	/**
	 * Prints welcome message.
	 */
	public void printWelcomeMessage() {
		System.out.println("\n\n");
		System.out.println("--------------------------------------------------------");
		System.out.println("      Welcome to Ladder and Snake Game!");
		System.out.println("--------------------------------------------------------\n");
	}
	
	/**
	 * Determines order of players at start.
	 * This method sorts players at first with their initial score created in constructor.
	 * If duplicates are found, it multiplies the score by 10 and adds a new dice score.
	 * Then, outer loop is broken and starts loop again from 0.
	 * This process is repeated until no more duplicates are found.
	 * Player array is sorted according to its score. 
	 */
	public void playerOrder() {
		boolean duplicates = true;
		
		System.out.println("Now deciding which player will start playing, players will flip their dice:\n");
		printPlayerScore();
		sortArray();
		printPlayerOrder();
		
		while (duplicates) {
			outer:
			for (int i = 0; i < players.length; i++) {
				for (int j = i + 1; j < players.length; j++) {
					// duplicate found
					if (players[j].getPlayerScore() == players[i].getPlayerScore()) {
						for (int k = 0; k < players.length; k++)  {
							players[k].setPlayerScore(players[k].getPlayerScore() * 10 + flipDice());
						}
						
						System.out.println("\nTie between Player " + players[i].getPlayerName() + 
								" and Player " + players[j].getPlayerName() + ". " +
											"We will x10 the score and play another round.\n");
						sortArray();
						printPlayerScore();
						printPlayerOrder();
						
						break outer;
					}
					
				}
				// if outer loop is completely iterated, it means no duplicate
				if (i == players.length - 1) {
					System.out.println("\nThe Order of players is set according to their score.");
					System.out.print("The final ");
					printPlayerOrder();
					duplicates = false;  
				}
			}
		}
	}
	
	/**
	 * Prints player score for player order.
	 */
	public void printPlayerScore() {
		for (int y = 0; y < players.length; y++) {
			System.out.println("Player " + players[y].getPlayerName() + "'s score: " + 
					players[y].getPlayerScore());
		}
		System.out.println();
	}
	
	/**
	 * Sorts Player array according to their score.
	 */
	public void sortArray() {
		Arrays.sort(players, 0, players.length, new Comparator<Player>() {
		    @Override 
		    public int compare(Player a, Player b) {
		      return Integer.compare(b.getPlayerScore(), a.getPlayerScore());
		    }
		  });
	}
	
	/**
	 * Prints player order.
	 */
	public void printPlayerOrder() {
		System.out.print("Order of players: ");
		for (int i = 0; i < players.length; i++) {
			if (i == players.length - 1) {
				System.out.print(players[i].getPlayerName() + ".");
			}
			else {
				System.out.print(players[i].getPlayerName() + ", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints number of players.
	 */
	public String toString() {
		return "Number of players are: " + numPlayers;
	}
	
	/**
	 * Checks if two LadderAndSnake objects are equal.
	 * @param LAS LadderAndSnake object
	 * @return true if both playerBoard array is equal
	 */
	public boolean equals(LadderAndSnake LAS) {
		return Arrays.deepEquals(playerBoard, LAS.playerBoard);
	}

}

