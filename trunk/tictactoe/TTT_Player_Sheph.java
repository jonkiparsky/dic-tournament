package tictactoe;

import java.util.ArrayList;
import java.util.Iterator;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * My AI implementation for TicTacToe.
 * 
 * @author Richard Shepherd <Sheph @ www.dreamincode.net>
 */
public class TTT_Player_Sheph extends TicTacToePlayer implements MachinePlayer {

	private ArrayList<TTTPath> blockedPaths, unblockedPaths;

	private TTTNode[][] nodes;

	// How do we make sure people declare public constructors?
	/**
	 * When a player of my class is instantiated, it creates a list of all the
	 * possible paths through the grid, so that it can utilize them to reach a
	 * solution.
	 */
	public TTT_Player_Sheph() {
		blockedPaths = new ArrayList<TTTPath>();
		unblockedPaths = new ArrayList<TTTPath>();

		nodes = new TTTNode[3][3];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				GridLocation l = new GridLocation(x, y);
				nodes[x][y] = new TTTNode(l);
			}
		}

		for (int i = 0; i < 3; i++) {
			TTTPath row = new TTTPath(nodes[0][i], nodes[1][i], nodes[2][i]);
			TTTPath col = new TTTPath(nodes[i][0], nodes[i][1], nodes[i][2]);

			unblockedPaths.add(row);
			unblockedPaths.add(col);
		}

		TTTPath diag = new TTTPath(nodes[0][0], nodes[1][1], nodes[2][2]);
		TTTPath antiDiag = new TTTPath(nodes[0][2], nodes[1][1], nodes[2][0]);

		unblockedPaths.add(diag);
		unblockedPaths.add(antiDiag);
	}

	@Override
	public Move getMove() {
		GridLocation l = getChosenNode().getLocation();
		GridMove move = new GridMove(getPlayerMark(), l, this);

		// EVIL HACK! MWAHAHAHA ... really though should we prevent this?
		// TicTacToe g = new TicTacToe();

		// if (!g.isLegal(move)) {
		// System.out.println("TTT_Player_Sheph has made an illegal move!");
		// System.out.println("(" + l.getX() + ", " + l.getY() + ")");
		// }

		if (!isMoveLegal(l.getX(), l.getY())) {
			System.out.println("TTT_Player_Sheph has made an illegal move!");
		}

		return move;
	}

	@Override
	public void init() {
		super.init(); // clears the grid

		// reset the blocked paths to unblocked
		Iterator<TTTPath> i = blockedPaths.iterator();
		while (i.hasNext()) {
			unblockedPaths.add(i.next());
			i.remove();
		}
	}

	@Override
	public void update(Move move) {
		super.update(move);

		GridMark mark = Enum
				.valueOf(GridMark.class, move.getAnnotation("mark"));

		if (mark == getOpponentMark()) {
			GridLocation l = ((GridMove) move).getLocation();
			TTTNode node = getNodeForLocation(l);
			for (TTTPath path : node.getIntersectingPaths()) {
				blockedPaths.add(path);
				if (unblockedPaths.contains(path)) {
					unblockedPaths.remove(path);
				}
			}
		}
	}

	// Would this be useful in the default player?
	public GridMark getOpponentMark() {
		return getPlayerMark() == GridMark.X ? GridMark.O : GridMark.X;
	}

	private TTTNode getNodeForLocation(GridLocation l) {
		return nodes[l.getX()][l.getY()];
	}

	private GridMark getMarkAt(GridLocation l) {
		return grid[l.getX()][l.getY()];
	}

	/**
	 * This is where most of my AI logic heavily resides.
	 */
	private TTTNode getChosenNode() {
		ArrayList<TTTPath> winningPaths = new ArrayList<TTTPath>();

		/* ===== Check Win ===== */
		int max = 0;
		for (TTTPath path : unblockedPaths) {
			int count = path.countMarks(grid, getPlayerMark());
			if (count == 2) {
				for (TTTNode node : path) {
					if (getMarkAt(node.getLocation()) == GridMark.E) {
						return node;
					}
				}
			}
			if (count > max)
				max = count;
		}
		/* ===== End Check Win ===== */

		/* ===== Check Lose ===== */
		for (TTTPath path : blockedPaths) {
			int count = path.countMarks(grid, getOpponentMark());
			if (count == 2) {
				for (TTTNode node : path) {
					if (getMarkAt(node.getLocation()) == GridMark.E) {
						return node;
					}
				}
			}
		}
		/* ===== End Check Lose ===== */

		/* ===== Be Aggressive ===== */
		for (TTTPath path : unblockedPaths) {
			int count = path.countMarks(grid, getPlayerMark());
			if (count == max)
				winningPaths.add(path);
		}

		TTTNode winningNode = null;
		max = 0;
		for (TTTPath path : winningPaths) {
			for (TTTNode node : path) {
				int remainingPaths = 0;
				for (TTTPath rPath : node.getIntersectingPaths()) {
					if (unblockedPaths.contains(rPath)) {
						remainingPaths++;
					}
				}

				if (remainingPaths > max) {
					max = remainingPaths;
					winningNode = node;
				}
			}
		}

		if (winningNode != null) {
			return winningNode;
		} else {
			// Should never happen -> only to avoid NPE in getMove() above
			System.out.println("TTT_Player_Sheph has f**ked up!");
			return nodes[1][1];
		}
	}

	public void signalWinner(boolean isWinner) {
	}

	public void signalDraw() {
	}
}