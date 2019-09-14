/**
 * 
 */
package com.truecaller.test.pawn.pojo;

/**
 * @author ranjan kumar
 * 
 *         ChequerBoardTile describes the actual tile location in the chequer board 
 *         Immutable
 */
final public class ChequerBoardTile {

	final private int xCoordinateOfTile;
	final private int yCoordinateOfTile;

	public int getX() {
		return xCoordinateOfTile;
	}

	public int getY() {
		return yCoordinateOfTile;
	}

	/**
	 * constructor
	 * 
	 * @param xCoordinateOfTile
	 * @param yCoordinateOfTile
	 */
	public ChequerBoardTile(int xCoordinateOfTile, int yCoordinateOfTile) {
		this.xCoordinateOfTile = xCoordinateOfTile;
		this.yCoordinateOfTile = yCoordinateOfTile;
	}

	/**
	 * checks if current Tile ss within Range
	 * 
	 * @param chequerBoardTile
	 * @param xBoundary
	 * @param yBoundary
	 * @return boolean
	 */
	public static boolean ifCurrentTileIsWithinRange(ChequerBoardTile chequerBoardTile, int xBoundary, int yBoundary) {

		int x = chequerBoardTile.getX(), y = chequerBoardTile.getY();
		return x >= 0 && x < xBoundary && y >= 0 && y < yBoundary;
	}

	/**
	 * check if current board tile already visited
	 * 
	 * @param chequerBoardTile
	 * @param board
	 * @return boolean
	 */
	public static boolean ifAlreadyVisited(ChequerBoardTile chequerBoardTile, int board[][]) {

		return board[chequerBoardTile.getX()][chequerBoardTile.getY()] != -1;
	}

	/**
	 * assigning value while visiting current tile
	 * 
	 * @param chequerBoardTile
	 * @param board
	 * @param val
	 */
	public static void visit(ChequerBoardTile chequerBoardTile, int board[][], int val) {

		board[chequerBoardTile.getX()][chequerBoardTile.getY()] = val;
	}
}