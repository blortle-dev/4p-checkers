public class Piece {
    private int row,column;
    private char character;
    private Player plr;
    public Piece(int row, int column, char character, Player p){
        this.row=row;
        this.column=column;
        this.character = character;
        plr = p;
    }

    public char getCharacter(){return character;}
    public void setCharacter(char character){this.character = character;}

    public Player getPlayer(){return plr;}
    public void setPlayer(Player p){plr=p;}

    public int[] getPosition(){
        return new int[]{row,column};
    }
    public void setPosition(int row, int column){
        this.row=row;
        this.column=column;
    }
}
