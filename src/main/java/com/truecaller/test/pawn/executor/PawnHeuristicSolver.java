package com.truecaller.test.pawn.executor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truecaller.test.pawn.constants.PawnHeuristicConstants;
import com.truecaller.test.pawn.pojo.ChequerBoardTile;
import com.truecaller.test.pawn.utils.PawnHeuristicUtils;

/**
 * @author ranjan kumar
 * 
 *         PawnHeuristicSolver is the actual class solving the prawn problem
 *         using Warnsdorff’s Rule heuristic way A pawn can move on 10x10
 *         chequerboard horizontally, vertically and diagonally by these rules:
 *         1) 3 tiles moving North (N), West (W), South (S) and East (E) 2) 2
 *         tiles moving NE, SE, SW and NW 3) Moves are only allowed if the
 *         ending tile exists on the board 4) Starting from initial position,
 *         the pawn can visit each cell only once
 * 
 */
public class PawnHeuristicSolver {

	/**
	 * x and y coordinates North (N), West (W), South (S) and East (E) , NE, SE, SW
	 * and NW in sequence
	 */
	public static int dxPrawn[] = { -3, 3, 0, 0, -2, 2, 2, -2 };
	public static int dyPrawn[] = { 0, 0, 3, -3, 2, 2, -2, -2 };

	private int chequerBoard[][];

	public int[][] getChequerBoard() {
		return chequerBoard;
	}

	public void setChequerBoard(int[][] board) {
		this.chequerBoard = board;
	}

	/**
	 * initialize chequerBoard with -1 as empty
	 */
	private void initializeBoard() {
		chequerBoard = new int[PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE][PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE];
		for (int i = 0; i < PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE; ++i) {
			Arrays.fill(chequerBoard[i], -1);
		}
	}

	/**
	 * Find Possible all legal Paths for Prawn using Warnsdorff’s Rule
	 * findPossibleLegalPathsForPrawn
	 * 
	 * @return output in json format
	 */
	@SuppressWarnings("unchecked")
	public String findPossibleLegalPathsForPrawn() {

		JSONObject tours = new JSONObject();
		for (int x = 0; x < PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE; x++) {
			for (int y = 0; y < PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE; y++) {
				initializeBoard();
				int currStep = 1;
				ChequerBoardTile initBoardPosition = new ChequerBoardTile(x, y);
				ChequerBoardTile.visit(initBoardPosition, chequerBoard, currStep);
				if (!solve(chequerBoard, initBoardPosition, currStep + 1)) {
					System.out.println("Sorry!! Cannot perform PRWAN TOUR starting at: " + "[" + x + ", " + y + "]!");
				} else {
					tours.put(x + ", " + y, PawnHeuristicUtils.formatCurrentPrawnTourResult(x, y, chequerBoard));
				}
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(tours);
	}

	/**
	 * Returns the number of accessible positions.
	 * 
	 * @param chequerBoardTile
	 * @return int
	 */
	private int weight(ChequerBoardTile chequerBoardTile) {
		
		return fetchNeighbours(chequerBoardTile).size();
	}

	/**
	 * solve method will try to traverse the entire chequer board recursively
	 * 
	 * @param board
	 * @param chequerBoardTile
	 * @param currentStepCount
	 * @return boolean if path possible
	 */
	private boolean solve(int board[][], ChequerBoardTile chequerBoardTile, int currentStepCount) {

		/**
		 * If we covered all tiles in the board and return
		 */
		if (currentStepCount == ((PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE
				* PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE) + 1)) {
			return true;
		}
		
		List<ChequerBoardTile> neighbours = fetchNeighbours(chequerBoardTile);
		ChequerBoardTile chosen = null;
		int minWeight = Integer.MAX_VALUE;
		for (ChequerBoardTile tilePosition : neighbours) {
			int currWeight = weight(tilePosition);
			if (currWeight < minWeight) {
				chosen = tilePosition;
				minWeight = currWeight;
			}
		}
		if (chosen == null) {
			return false;
		}
		ChequerBoardTile.visit(chosen, board, currentStepCount);
		return solve(board, chosen, currentStepCount + 1);
	}

	/**
	 * Returns accessible neighbors
	 * 
	 * @param chequerBoardTile
	 * @return List<ChequerBoardTile> neighbours
	 */
	private List<ChequerBoardTile> fetchNeighbours(ChequerBoardTile chequerBoardTile) {

		List<ChequerBoardTile> neighbours = new LinkedList<ChequerBoardTile>();
		for (int i = 0; i < dxPrawn.length; ++i) {
			ChequerBoardTile newBoardPosition = new ChequerBoardTile(dxPrawn[i] + chequerBoardTile.getX(),
					dyPrawn[i] + chequerBoardTile.getY());
			if (ChequerBoardTile.ifCurrentTileIsWithinRange(newBoardPosition,
					PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE, PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE)
					&& !ChequerBoardTile.ifAlreadyVisited(newBoardPosition, chequerBoard)) {
				neighbours.add(newBoardPosition);
			}
		}
		return neighbours;
	}
}
