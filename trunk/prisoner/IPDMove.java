package prisoner;

import tourney.Move;

public class IPDMove extends Move
{
	private boolean defect;	

	public IPDMove(boolean defect)
	{
		this.defect = defect;
	}

	public boolean getMove()
	{
		return defect;
	}



}
