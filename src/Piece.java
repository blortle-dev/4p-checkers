public class Piece {
    public int row,column;
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
}
