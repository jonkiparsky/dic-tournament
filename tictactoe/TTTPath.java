package tictactoe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A TTTPath represents any of the possible paths through a TicTacToe grid that
 * can lead to victory. Each path must be created via a series of TTTNodes.
 * 
 * @see TTTNode
 */
public class TTTPath implements Iterable<TTTNode> {
	private final TTTNode[] nodes;

	/** Create a path from the nodes. */
	public TTTPath(TTTNode... nodes) {
		this.nodes = nodes;
		for (TTTNode node : this.nodes) {
			node.addPath(this);
		}
	}

	/**
	 * Counts the number of occurrences of <code>mark</code> along this path in
	 * the <code>grid</code>.
	 * 
	 * @param grid
	 *            The TTT grid
	 * @param mark
	 *            The mark thats being counted.
	 * @return The number of <code>mark</code>s in this path.
	 */
	public int countMarks(GridMark[][] grid, GridMark mark) {
		int count = 0;
		for (TTTNode node : nodes) {
			GridLocation l = node.getLocation();
			if (grid[l.getX()][l.getY()] == mark) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public Iterator<TTTNode> iterator() {
		return new Iterator<TTTNode>() {
			private int i = 0;

			@Override
			public boolean hasNext() {
				return i < nodes.length;
			}

			@Override
			public TTTNode next() {
				if (i >= nodes.length || i < 0) {
					throw new NoSuchElementException();
				}
				return nodes[i++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}