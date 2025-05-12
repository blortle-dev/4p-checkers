public class Board {
    private Piece[][] board = new Piece[12][12];
    /*
    *  Board has a 2x2 area in each corner cut out, to allow for player space
    *  Players start in the 2x8 areas in the middle of each edge
    *
    *  TODO: Implement checks to ensure no players can move into the cutouts
    *  TODO: rendering method
    *  TODO: Represent players with ASCII chars
    *  TODO: Implement rotation method for renderer
    *
    *       [A |B |C |D |E |F |G |H |I |J |K |L ]
    *  [1 ] [||||||..|..|..|..|..|..|..|..||||||] [1 ]
    *  [2 ] [||||||..|..|..|..|..|..|..|..||||||] [2 ]
    *  [3 ] [++|++|  |  |  |  |  |  |  |  |**|**] [3 ]
    *  [4 ] [++|++|  |  |  |  |  |  |  |  |**|**] [4 ]
    *  [5 ] [++|++|  |  |  |  |  |  |  |  |**|**] [5 ]
    *  [6 ] [++|++|  |  |  |  |  |  |  |  |**|**] [6 ]
    *  [7 ] [++|++|  |  |  |  |  |  |  |  |**|**] [7 ]
    *  [8 ] [++|++|  |  |  |  |  |  |  |  |**|**] [8 ]
    *  [9 ] [++|++|  |  |  |  |  |  |  |  |**|**] [9 ]
    *  [10] [++|++|  |  |  |  |  |  |  |  |**|**] [10]
    *  [11] [||||||^^|^^|^^|^^|^^|^^|^^|^^||||||] [11]
    *  [12] [||||||^^|^^|^^|^^|^^|^^|^^|^^||||||] [12]
    *       [A |B |C |D |E |F |G |H |I |J |K |L ]
    *
    *  EXAMPLE VISUAL KEY:
    *  || - out-of-bounds or display item
    *  ^^ - player 1 pieces
    *  ** - player 2 pieces
    *  .. - player 3 pieces
    *  ++ - player 4 pieces
    *
    *  Actual symbols and rendered board size will differ in final version
     */

    public Board() {

    }

    public Board(Player p1, Player p2, Player p3, Player p4) {
        this.p1 = p1;
        this.p2 = p1;
        this.p3 = p1;
        this.p4 = p1;
        board = new Piece[12][12];
    }

    public void draw(){
        String out = "      A   B   C   D   E   F   G   H   I   J   K   L\n" +
                "    |-----------------------------------------------|\n" +
                "  1 |---|---|   |   |   |   |   |   |   |   |---|---| 1\n" +
                "  2 |---|---|   |   |   |   |   |   |   |   |---|---| 2\n" +
                "  3 |   |   |   |   |   |   |   |   |   |   |   |   | 3\n" +
                "  4 |   |   |   |   |   |   |   |   |   |   |   |   | 4\n" +
                "  5 |   |   |   |   |   |   |   |   |   |   |   |   | 5\n" +
                "  6 |   |   |   |   |   |   |   |   |   |   |   |   | 6\n" +
                "  7 |   |   |   |   |   |   |   |   |   |   |   |   | 7\n" +
                "  8 |   |   |   |   |   |   |   |   |   |   |   |   | 8\n" +
                "  9 |   |   |   |   |   |   |   |   |   |   |   |   | 9\n" +
                " 10 |   |   |   |   |   |   |   |   |   |   |   |   | 10\n" +
                " 11 |---|---|   |   |   |   |   |   |   |   |---|---| 11\n" +
                " 12 |---|---|   |   |   |   |   |   |   |   |---|---| 12\n" +
                "    |-----------------------------------------------|\n" +
                "      A   B   C   D   E   F   G   H   I   J   K   L";
    }

    //--PIECE LOGIC--

    private boolean isInBounds(int row, int col) {
        if(row>=0&&row<board.length&&col>=0&&col<board[0].length){
            if((row<2||row>9)&&(col<2||col>9)){
                return false;
            }
            return true;
        }
        return false;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void jump(Piece p1, Piece p2) {
        int[] p1Pos = p1.getPosition();
        int[] p2Pos = p2.getPosition();
        int[] skew = new int[]{
                p2Pos[0] - p1Pos[0],
                p2Pos[1] - p1Pos[1]
        };
        int[] temp = new int[]{p1Pos[0]+skew[0]*2,p1Pos[1]+skew[1]*2};
        if(isInBounds(temp[0],temp[1])) {
            board[temp[0]-skew[0]][temp[1]-skew[1]]=null;
            //TODO: add custom logic that checks the player who owns the piece and executes custom logic
            p1.setPosition(temp[0],temp[1]);
        }
    }

    /*

      A   B   C   D   E   F   G   H   I   J   K   L
    |-----------------------------------------------|
  1 |---|---|   |   |   |   |   |   |   |   |---|---| 1
  2 |---|---|   |   |   |   |   |   |   |   |---|---| 2
  3 |   |   |   |   |   |   |   |   |   |   |   |   | 3
  4 |   |   |   |   |   |   |   |   |   |   |   |   | 4
  5 |   |   |   |   |   |   |   |   |   |   |   |   | 5
  6 |   |   |   |   |   |   |   |   |   |   |   |   | 6
  7 |   |   |   |   |   |   |   |   |   |   |   |   | 7
  8 |   |   |   |   |   |   |   |   |   |   |   |   | 8
  9 |   |   |   |   |   |   |   |   |   |   |   |   | 9
 10 |   |   |   |   |   |   |   |   |   |   |   |   | 10
 11 |---|---|   |   |   |   |   |   |   |   |---|---| 11
 12 |---|---|   |   |   |   |   |   |   |   |---|---| 12
    |-----------------------------------------------|
      A   B   C   D   E   F   G   H   I   J   K   L

     */
}
