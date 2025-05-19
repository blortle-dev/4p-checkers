public class Board {
    private Piece[][] board;
    private final Player[] players;
    private int currentPlayer = 0;
    /*
    *  Board has a 2x2 area in each corner cut out, to allow for player space
    *  Players start in the 2x8 areas in the middle of each edge
    *  DONE: init() method initializes each player's pieces on the size
    *  DONE: Implement checks to ensure no players can move into the cutouts
    *  DONE: rendering method
    *  DONE: Represent players with ASCII chars
    *  DONE: Implement rotation method for renderer!
    *  Actual symbols and rendered board size will differ in final version
     */

    //--INITIALIZERS & CONSTRUCTORS--

    public Board() {
        this(
                new Player("p1", "\u001B[34m▲\u001B[37m"),
                new Player("p2", "\u001B[32m●\u001B[37m"),
                new Player("p3", "\u001B[33m■\u001B[37m"),
                new Player("p4","\u001B[31m◆\u001B[37m")
        );
    }

    public Board(Player p1, Player p2, Player p3, Player p4) {
        players = new Player[]{p1, p2, p3, p4};
        board = new Piece[12][12];
    }

    public void init(){
        int[][] p1Positions = {{10,2},{10,4},{10,6},{10,8},{11,3},{11,5},{11,7},{11,9}};
        int[][] p2Positions = {{2,10},{4,10},{6,10},{8,10},{3,11},{5,11},{7,11},{9,11}};
        int[][] p3Positions = {{0,2},{0,4},{0,6},{0,8},{1,3},{1,5},{1,7},{1,9}};
        int[][] p4Positions = {{2,0},{4,0},{6,0},{8,0},{3,1},{5,1},{7,1},{9,1}};

        for(int i=0;i<p1Positions.length;i++){
            board[p1Positions[i][0]][p1Positions[i][1]] = new Piece(p1Positions[i][0],p1Positions[i][1], players[0]);
            board[p1Positions[i][0]][p1Positions[i][1]].setPromoted(true);
        }
        for(int i=0;i<p2Positions.length;i++){
            board[p2Positions[i][0]][p2Positions[i][1]] = new Piece(p2Positions[i][0],p2Positions[i][1], players[1]);
        }
        for(int i=0;i<p3Positions.length;i++){
            board[p3Positions[i][0]][p3Positions[i][1]] = new Piece(p3Positions[i][0],p3Positions[i][1], players[2]);
        }
        for(int i=0;i<p4Positions.length;i++){
            board[p4Positions[i][0]][p4Positions[i][1]] = new Piece(p4Positions[i][0],p4Positions[i][1], players[3]);
        }
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
                    if(board[i][j].getPromoted()){
                        arr[counter] = "x";
                    }else{
                        arr[counter] = " ";
                    }
                    arr[counter++] += formatCell(i,j)+" ";
                }else if(!isInBounds(i,j)){
                    arr[counter++] = "---";
                }else{
                    arr[counter++] = "   ";
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
                    Piece p = getLoc(i,j);
                    if(p!=null){
                        p.setPosition(new int[]{j,n-i-1});
                    }
                    temp[j][n - i - 1] = board[i][j];
                }
            }
            board = temp;
        }
    }

    public String draw(){
        String out = "\u001B[37m      A   B   C   D   E   F   G   H   I   J   K   L\n" +
                "    |-----------------------------------------------|\n" +
                "  1 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 1\n" +
                "  2 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 2\n" +
                "  3 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 3\n" +
                "  4 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 4\n" +
                "  5 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 5\n" +
                "  6 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 6\n" +
                "  7 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 7\n" +
                "  8 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 8\n" +
                "  9 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 9\n" +
                " 10 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 10\n" +
                " 11 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 11\n" +
                " 12 |%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s| 12\n" +
                "    |-----------------------------------------------|\n" +
                "      A   B   C   D   E   F   G   H   I   J   K   L\u001B[0m";
        return String.format(out, (Object[]) formatBoard());
    }

    //--ACCESSORS & MUTATORS
    public void setLoc(int[] location, Piece piece) {
        board[location[0]][location[1]] = piece;
    }
    public Piece getLoc(int row, int col){
        return board[row][col];
    }

    public Player[] getPlayers(){
        return players;
    }

    public void setCurrentPlayer(int currentPlayer){
        this.currentPlayer = currentPlayer;
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    //--MOVE LOGIC--
    private boolean isInBounds(int row, int col) {
        if(row>=0&&row<board.length&&col>=0&&col<board[0].length){
            return ((row >= 2 && row <= 9) || (col >= 2 && col <= 9));
        }
        return false;
    }

    private boolean isDiagonal(int[] start,int[] end) {
        return (start[0]!=start[1])&&(end[0]!=end[1]);
    }

    public boolean validMove(int[] start, int[] end, boolean isPromoted, boolean jump){
        boolean isJumping = Math.abs(end[0]-start[0])==2&&Math.abs(end[1]-start[1])==2;

        if(!isInBounds(end[0],end[1]) || !isDiagonal(start,end) || getLoc(start[0],start[1]).getPlayer()!=players[currentPlayer]){
            return false;
        }

        int rowDiff = end[0]-start[0];

        if(rowDiff == 1 || (isJumping && rowDiff == 2)){
            return rowDiff == 2 ? isPromoted && jump: isPromoted;
        }

        if(rowDiff == -1 || rowDiff == -2){
            return rowDiff != -2 || jump;
        }
        return false;
    }



    public boolean jump(Piece p, int[] end) {
        if(p==null||getLoc(end[0],end[1])!=null){return false;}

        int[] p1Pos = p.getPosition();
        int[] p2Pos = new int[]{
                (p1Pos[0]+end[0])/2,
                (p1Pos[1]+end[1])/2,
        };

        if(getLoc(p2Pos[0],p2Pos[1])==null||getLoc(p2Pos[0],p2Pos[1]).getPlayer()==players[currentPlayer]){return false;}

        if(validMove(p1Pos,end,p.getPromoted(),true)) {
            setLoc(p1Pos,null);
            setLoc(p2Pos,null);
            setLoc(end,p);
            return true;
        }
        return false;
    }
    public boolean move(Piece p, int[] end) {
        if(p==null||getLoc(end[0],end[1])!=null){return false;}

        int[] start = p.getPosition();
        if(validMove(p.getPosition(),end,p.getPromoted(),false)) {
            p.setPosition(end);
            setLoc(start,null);
            setLoc(end,p);
            return true;
        }
        return false;
    }
}
