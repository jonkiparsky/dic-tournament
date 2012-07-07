package prisoner;


import tourney.Player;
import tourney.Move;

public abstract class  IPD_Player implements Player
{

	protected int score;
	
	protected IPD_Move previousMove;

	public abstract Move getMove();

	public abstract String getID();
		
	public abstract void update(Move move);


	public void setScore (int score)

	{
		this.score = score;
	}

	public int getScore ()
	{
		return score;
	}

	public String toString() {
		return getID();
	}
}
