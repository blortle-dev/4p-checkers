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

    }

    private boolean isInBounds(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || ((x < 2 || x > 9) && (y < 2 || y > 9))) {
            return false;
        }
        return true;
    }

    public Piece getPiece(int x, int y) {
        if (  ) {
            return null;
        }
        return board[x][y];
    }
}
