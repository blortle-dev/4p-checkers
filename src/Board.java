public class Board {
    private Piece[][] board;
    private Player p1,p2,p3,p4;
    /*
    *  Board has a 2x2 area in each corner cut out, to allow for player space
    *  Players start in the 2x8 areas in the middle of each edge
    *  TODO: init() method initializes each player's pieces on the size
    *  DONE: Implement checks to ensure no players can move into the cutouts
    *  DONE: rendering method
    *  DONE: Represent players with ASCII chars
    *  DONE: Implement rotation method for renderer!
    *  Actual symbols and rendered board size will differ in final version
     */

    //--INITIALIZERS & CONSTRUCTORS--

    public Board() {
        this(
                new Player("p1", "▲"),
                new Player("p2", "●"),
                new Player("p3", "■"),
                new Player("p4","◆")
        );
    }

    public Board(Player p1, Player p2, Player p3, Player p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        board = new Piece[12][12];
    }

    public void init(){

    }

    public Player[] getPlayers(){
        return new Player[]{p1,p2,p3,p4};
    }

    //--BOARD LOGIC--

    private String formatCell(int row, int col) {
        if (isInBounds(row, col)) {
            return board[row][col].getIcon();
        }
        return " ";
    }

    private String[] formatBoard(){
        String[] arr = new String[board.length*board[0].length];
        int counter = 0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!=null){
                    arr[counter++] = formatCell(i,j);
                }else if(!isInBounds(i,j)){
                    arr[counter++] = "---";
                }else{
                    arr[counter++] = " ";
                }
            }
        }
        return arr;
    }

    public void rotateBoard(int times){
        for(int x=0;x<times;x++) {
            int n = board.length;
            Piece[][] temp = new Piece[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    temp[j][n - i - 1] = board[i][j];
                }
            }
            board = temp;
        }
    }

    public String draw(){
        String out = "      A   B   C   D   E   F   G   H   I   J   K   L\n" +
                "    |-----------------------------------------------|\n" +
                "  1 |%s|%s| %s | %s | %s | %s | %s | %s | %s | %s |%s|%s| 1\n" +
                "  2 |%s|%s| %s | %s | %s | %s | %s | %s | %s | %s |%s|%s| 2\n" +
                "  3 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 3\n" +
                "  4 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 4\n" +
                "  5 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 5\n" +
                "  6 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 6\n" +
                "  7 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 7\n" +
                "  8 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 8\n" +
                "  9 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 9\n" +
                " 10 | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | 10\n" +
                " 11 |%s|%s| %s | %s | %s | %s | %s | %s | %s | %s |%s|%s| 11\n" +
                " 12 |%s|%s| %s | %s | %s | %s | %s | %s | %s | %s |%s|%s| 12\n" +
                "    |-----------------------------------------------|\n" +
                "      A   B   C   D   E   F   G   H   I   J   K   L";
        return String.format(out, (Object[]) formatBoard());
    }

    //--ACCESSORS & MUTATORS
    public void setLoc(int[] location, Piece piece) {
        board[piece.getPosition()[0]][piece.getPosition()[1]] = null;
        board[location[0]][location[1]] = piece;
    }
    public Piece getLoc(int row, int col){
        return board[row][col];
    }

    //--MOVE LOGIC--
    private boolean isInBounds(int row, int col) {
        if(row>=0&&row<board.length&&col>=0&&col<board[0].length){
            return ((row >= 2 && row <= 9) || (col >= 2 && col <= 9));
        }
        return false;
    }

    private boolean isDiagonal(int[] start,int[] end) {
        return (start[0]!=end[0])&&(start[1]!=end[1]);
    }

    public boolean validMove(int[] start, int[] end, boolean isPromoted, boolean jump){
        boolean isJumping = Math.abs(end[0]-start[0])==2&&Math.abs(end[1]-start[1])==2;
        if(isInBounds(end[0],end[1]) && isDiagonal(start,end)){
            if(start[0]-end[0] <= -1) {
                if(isJumping){//if they are moving backwards by two, aka if they are attempting a jump
                    return isPromoted&&jump;
                }else{
                    return isPromoted && (start[0] - end[0] == -1);
                }
            }else{
                if(isJumping){
                    return jump;
                }else{
                    return (start[0] - end[0] == 1);
                }
            }
        }
        return false;
    }



    public void jump(Piece p1, Piece p2) {
        int[] p1Pos = p1.getPosition();
        int[] p2Pos = p2.getPosition();
        int[] p3Pos = new int[]{//just the change from p1 to p2
                p1Pos[0]+2*(p2Pos[0] - p1Pos[0]),
                p1Pos[1]+2*(p2Pos[1] - p1Pos[1])
        };

        if(validMove(p1Pos,p3Pos,p1.getPromoted(),true)) {
            setLoc(p1Pos,null);
            setLoc(p2Pos,null);
            setLoc(p3Pos,p1);
        }
    }
    public boolean move(Piece p, int[] end) {
        int[] start = p.getPosition();
        if(validMove(p.getPosition(),end,p.getPromoted(),false)) {
            p.setPosition(end);
            setLoc(start,new Piece(start[0],start[1]));
            setLoc(end,p);
            return true;
        }
        return false;
    }
}
