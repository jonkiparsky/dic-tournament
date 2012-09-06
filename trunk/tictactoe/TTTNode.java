package tictactoe;

import java.util.ArrayList;

/**
 * TTTNode represents a node on a TicTacToe grid. Many different paths can cross
 * through the same node, therefore TTTNode provides a means to retrieve all
 * paths which include this node.
 */
public class TTTNode {
	private final ArrayList<TTTPath> intersectingPaths;
	private final GridLocation location;

	public TTTNode(GridLocation loc) {
		intersectingPaths = new ArrayList<TTTPath>();
		this.location = loc;
	}

	/**
	 * Every TTTPath given a node as a parameter should add itself to the list
	 * of paths intersecting this node.
	 * 
	 * @param path
	 *            The path intersecting this node.
	 */
	public void addPath(TTTPath path) {
		intersectingPaths.add(path);
	}

	public GridLocation getLocation() {
		return location;
	}

	public ArrayList<TTTPath> getIntersectingPaths() {
		return intersectingPaths;
	}
}