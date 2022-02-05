/**
 * Name and ID: Chul Bin YOON 40197998
 * COMP249
 * Assignment # 1
 * Due Date: February 7, 2021
 */

public class Player {
	private int playerScore;
	private int playerPosition;
	private String playerName;
	
	/**
	 * Constructor which sets player information.
	 * @param name name of player
	 * @param score score to determine the order of players
	 */
	public Player(String name, int score) {
		this.playerName = name;
		this.playerScore = score;
		this.playerPosition = 0;
	}
	
	/**
	 * Default constructor.
	 */
	public Player() {
		this.playerName = null;
		this.playerScore = 0;
		this.playerPosition = 0;
	}
	
	/**
	 * Gets player score
	 * @return player score
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Sets player score
	 * @param score player score
	 */
	public void setPlayerScore(int score) {
		this.playerScore = score;
	}
	
	/**
	 * Gets player name
	 * @return player name
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Gets player position
	 * @return player position
	 */
	public int getPosition() {
		return playerPosition;
	}
	
	/**
	 * Sets player position
	 * @param pos player position
	 */
	public void setPosition(int pos) {
		this.playerPosition = pos;
	}
}
