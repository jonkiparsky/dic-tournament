package prisoner;


import tourney.Player;
import tourney.Move;

public abstract class  IPD_Player extends Player
{

	protected int score;
	
	protected IPD_Move previousMove;

	public IPD_Player(String name)
	{
		super(name);
	}		

	public void init()
	{
		this.score = 0;
	}


	public abstract void update(Move move);


	public void setScore (int score)

	{
		this.score = score;
	}

	public void updateScore(int deltaScore)
	{
		this.score += deltaScore;
	}
	
	public int getScore ()
	{
		return score;
	}

	public String toString() {
		return getID();
	}
}
