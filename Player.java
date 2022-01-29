
public class Player {
	private int playerScore;
	private int playerPosition;
	private String playerName;
	
	public Player(String name, int score) {
		this.playerName = name;
		this.playerScore = score;
		this.playerPosition = 0;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public void setPlayerScore(int score) {
		this.playerScore = score;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getPosition() {
		return playerPosition;
	}
	
	public void setPosition(int pos) {
		this.playerPosition = pos;
	}
}
