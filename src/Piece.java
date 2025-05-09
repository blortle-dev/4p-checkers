public class Piece {
    public int posX,posY;
    public char character;
    private Board board;
    public Piece(int x, int y, char character, Board b){
        posX=x;
        posY=y;
        this.character = character;
        board = b;
    }

    public int[] getSkewTo(Piece p){
        return new int[]{posX - p.posX, posY + p.posY};
    }

    public boolean jump(Piece p){
        int[] skew = getSkewTo(p);
        int[] jumpingTo = {posX-skew[0]*2,posY+skew[1]*2};
        if(p.character!=character&&board.getPiece(jumpingTo[0],jumpingTo[1]) == null){
            board.move(this,jumpingTo);
            board.remove(p, new int[]{p.posX, p.posY});
            return true;
        }

        return false;
    }
}
