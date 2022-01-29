/**
 * Name and ID: Chul Bin YOON 40197998
 * COMP249
 * Assignment # 1
 * Due Date: February 4, 2021
 */

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		// prompt for num of user
		// give 4 chances
		
		LadderAndSnake game = new LadderAndSnake(4);
		game.drawBoard();
		game.play();
		
	}

}
