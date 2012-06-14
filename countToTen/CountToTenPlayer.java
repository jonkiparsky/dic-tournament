package countToTen;

import tourney.*;

public class CountToTenPlayer extends Player

{
	private String name;
	
	public CountToTenPlayer(String name)
	{
		this.name = name;
	}

	public Move makeMove(Board b)
	{
		CountToTenBoard cb = (CountToTenBoard)b;
		return new CountToTenMove(cb.getMove(), this);
	}
	
}
