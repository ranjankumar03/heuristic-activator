 *         Main class to run : PawnHeuristicActivator.java
 
 
 
 

 *         Activator class : Trigger point Calling PawnHeuristicSolver to
 *         execute the main logic to solve this heuristic pawn problem
 * 
 *         Applied Warnsdorff’s heuristics:
 * 
 *         We can start from any initial position of the prawn on the board. We
 *         always move to an adjacent, unvisited square with minimal degree
 *         (minimum number of unvisited adjacent).
 * 
 *         ChequerBoardTile representing the possible tile coordinates in the
 *         ChequerBoard of dimension DxD The output is a JSON structure of the
 *         form: {"x1,y1": { "startPosition": "x1, y1", "sequence": ["x1, y1", "xi, yi",
 *         ...]}, "x2, y2": { "startPosition": "x2, y2", "sequence": ["x2, y2", "xi, yi",
 *         ...]}, ...}
 * 
 *         where the "startPosition" is the position where the Prawn starts the tour
 *         and sequence is the sequence of coordinates visited by the Prawn
 *         considering the top left tile of the Chequer Board is (0,0) and the
 *         bottom right tile of the chess board is (10,10).
 * 
 *         size : 10 x 10 board
 *         @author ranjan kumar