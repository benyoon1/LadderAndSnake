import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Name and ID: Chul Bin YOON 40197998
 * COMP249
 * Assignment # 1
 * Due Date: February 4, 2021
 */

public class LASBUp {
	private int numPlayers;
	private int[][] board = new int[10][10];
	private int numBoard = 101;
	private String[][] playerBoard = new String[10][10];
	private Player[] players;
	private Player winner;
	private boolean gameover = false;
	
	public LASBUp(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public LASBUp() {
		this.numPlayers = 2;
	}
	
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
		
		// test purposes
		else if (numPlayers == 8) {
			players = new Player[8];
			players[0] = new Player("P1", flipDice());
			players[1] = new Player("P2", flipDice());
			players[2] = new Player("P3", flipDice());
			players[3] = new Player("P4", flipDice());
			players[4] = new Player("P5", flipDice());
			players[5] = new Player("P6", flipDice());
			players[6] = new Player("P7", flipDice());
			players[7] = new Player("P8", flipDice());
			
		}
		else {
			players = new Player[2];
			players[0] = new Player("P1", flipDice());
			players[1] = new Player("P2", flipDice());
		}
	}
	
	public static int flipDice() {
		return (int)(Math.random() * 6 + 1);
	}
	
	
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
		
		//System.out.println();
		printPlayerPosition();
		
		drawBoard();
		resetBoard();
	}
	
	public void movePlayer(Player player, int diceValue) {
		
		player.setPosition(player.getPosition() + diceValue);
		
		// 101 -> 99 : 101 ... = 99 => 101 + 99 = 200 => 200 - 101 = 99
		// 104 -> 96 : 104 ... = 96 => 104 + 96 = 200 => 200 - 104 = 96
		if (player.getPosition() > 100) {
			player.setPosition(200 - player.getPosition());
			//System.out.print("back to square " + player.getPosition());
		}
		// Ladder
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
		// Snake
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
	
	public void setPlayer(Player player) {
		int pos = player.getPosition(); 
		int posOdd;
		int posEven;
		//resetBoard();
		for (int i = 0, j = 9; j >= 0; i += 10, j--) {
			posOdd = pos - (i + 1);
			posEven = i + 10 - pos;
			// ex. if pos > 10 && pos <= 20
			if (pos > i && pos <= i + 10) {
				// for 10, 30, 50, 70 ,90
				if (j % 2 == 1) {
					// ex. for row 21: [pos - (20 + 1)][j] because players move towards right
					playerBoard[posOdd][j] = player.getPlayerName();
				}
				// for 20, 40, 60, 80, 100
				else if (j % 2 == 0) {
					// ex. for row 40: [(40 + 10) - pos][j] because players move towards left
					playerBoard[posEven][j] = player.getPlayerName();
				}
			}
		}
		
	}
	
	// test purpose
	public void printPlayerPosition() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (playerBoard[i][j] == "P1") {
					System.out.println("P1 is at: " + i + ", " + j);
					break;
				}
				if (playerBoard[i][j] == "P2") {
					System.out.println("P2 is at: " + i + ", " + j);
					break;
				}
				if (playerBoard[i][j] == "P3") {
					System.out.println("P3 is at: " + i + ", " + j);
					break;
				}
				if (playerBoard[i][j] == "P4") {
					System.out.println("P4 is at: " + i + ", " + j);
					break;
				}
			}
		}
	}
	
	public void resetBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				playerBoard[i][j] = null;
			}
		}
	}
	
	public void drawBoard() {
		numBoard = 101;
		for (int i = 0; i < 30; i++) {
			if (i % 6 == 0) {
				for (int j = 0; j < 10; j++) {
					board[i/3][j] = j + 1;
					System.out.print(numBoard - board[i/3][j] + "\t");
					
				}
				System.out.println();
				numBoard -= 10;
			}
			else if ((i - 3) % 6 == 0) {
				for (int j = 10; j > 0; j--) {
					board[i/3][j - 1] = j;
					System.out.print(numBoard - board[i/3][j - 1] + "\t");
				
				}
				System.out.println();
				numBoard -= 10;
			}

			else {
				System.out.print("  ");
				int p = i / 3;
				
				for (int k = 0; k < 10; k++) {
					if (playerBoard[k][p] == null) {
						System.out.print("        ");
					}
					else if ((i - 1) % 3 == 0) {
						System.out.print(playerBoard[k][p] + "      ");
					}
//					else {
//						System.out.println();
//					}
				}
				System.out.println();
				
			}
			
		}
		System.out.println();
	}
	
	
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
				System.out.println("\n\nTurn " + ++count + ":\n");
				//drawBoard();
				//System.out.println();
				flipPlayers(players);
				//checkPosition(players);
			}
		}
		
		System.out.println("Game Over! Thanks for playing!");
		
		keyIn.close();
		// 1.determine order of turns
		// Player( P4,p2,p1,p3)

	}
	
	
	public void announceWinner() {
		System.out.println("\nWinner is: " + winner.getPlayerName());
		gameover = true;
	}
	
	public void printWelcomeMessage() {
		System.out.println("--------------------------------------------------------");
		System.out.println("      Welcome to Ladder and Snake Game!");
		System.out.println("--------------------------------------------------------");
	}
	
	
	
	
	// Determine Order of players
	public void playerOrder() {
		boolean duplicates = true;
		
		System.out.println("Now deciding which player will start playing, players will flip their dice:\n");
		printPlayerScore();
		sortArray();
		printPlayerOrder();
		//printPlayerScore();
		
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
						
						break outer; // restart loop because of first 2 indexes
					}
					
				}
				// if outer loop is completely iterated, it means no duplicate.
				if (i == players.length - 1) {
					System.out.println("\nThe Order of players is set according to their score.");
					System.out.print("The final ");
					printPlayerOrder();
					duplicates = false;  
				}
			}
			
			//printPlayerOrder();
		}
	}
	
	public void printPlayerScore() {
		for (int y = 0; y < players.length; y++) {
			System.out.println("Player " + players[y].getPlayerName() + "'s score: " + 
					players[y].getPlayerScore());
		}
		System.out.println();
	}
	
	public void sortArray() {
		Arrays.sort(players, 0, players.length, new Comparator<Player>() {
		    @Override 
		    public int compare(Player a, Player b) {
		      return Integer.compare(b.getPlayerScore(), a.getPlayerScore());
		    }
		  });
	}
	
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
	
	public String toString() {
		String a = "";
		// draw map and num of turn
		return a;
	}
	
//	public void playerOrder(int start, int end) {
//		
//		
//		Arrays.sort(players, 0, players.length, new Comparator<Player>() {
//		    @Override public int compare(Player a, Player b) {
//		      return Integer.compare(b.getPlayerScore(), a.getPlayerScore());
//		    }
//		  });
//		

//		
//		int i = start;
//		  while (i < end) {
//		    // Try to find a "run" of players with the same number.
//		    int runStart = i;
//		    int diceNumberRolled = players[runStart].getPlayerScore();
//		    ++i;
//		    while (i < end && players[i].getPlayerScore() == diceNumberRolled) {
//		      ++i;
//		    }
//
//		    if (i - runStart > 1) {
//		      // We have found more than one player with the same dice number.
//		      // Get all of the players with that dice number to roll again.
//		      playerOrder(runStart, i);
//		    }
//		  }
//		  System.out.println();
//			System.out.println(players[0].getPlayerScore());
//			System.out.println(players[1].getPlayerScore());
//			System.out.println(players[2].getPlayerScore());
//			System.out.println(players[3].getPlayerScore());
//		
//	}

}

//class SortbyRoll implements Comparator<Player> {
//	public int compare(Player a, Player b) {
//		return a.getPlayerScore() - b.getPlayerScore();
//	}
//}
