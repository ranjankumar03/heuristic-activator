/**
 * 
 */
package com.truecaller.test.pawn.utils;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.truecaller.test.pawn.constants.PawnHeuristicConstants;

/**
 * @author ranjan kumar
 * 
 * PawnHeuristicUtils having utility methods
 *
 */
public class PawnHeuristicUtils {

	/**
	 * Print the output in json format for better output readability
	 * 
	 * @param x
	 * @param y
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject formatCurrentPrawnTourResult(int x, int y, int[][] chequerBoard) {
		
		JSONObject jsonOutput = new JSONObject();
		jsonOutput.put("startPosition", x + ", " + y);

		HashMap<Integer, String> posMap = new HashMap<Integer, String>();
		for (int i = 0; i < PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE; ++i) {
			for (int j = 0; j < PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE; ++j) {
				posMap.put(chequerBoard[i][j], i + ", " + j);
			}
		}
		JSONArray jsonArray = new JSONArray();
		for (int sequence = 1; sequence <= PawnHeuristicConstants.CHEQUER_BORAD_ROW_SIZE * PawnHeuristicConstants.CHEQUER_BORAD_COLUMN_SIZE; ++sequence) {
			if (posMap.containsKey(sequence)) {
				jsonArray.add(posMap.get(sequence));
			}
		}
		jsonOutput.put("sequence", jsonArray);
		return jsonOutput;
	}
}
