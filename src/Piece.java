public class Piece {
    private int row,column;
    private Player plr;
    private boolean promoted;
    public Piece(int row, int column, Player p){
        this.row=row;
        this.column=column;
        plr = p;
        promoted = false;
    }
    public Piece(int row, int column){
        plr = new Player(""," ");
    }

    public String getIcon(){return plr.getIcon();}

    public Player getPlayer(){return plr;}
    public void setPlayer(Player p){plr=p;}

    public int[] getPosition(){
        return new int[]{row,column};
    }
    public void setPosition(int[] loc){this.row = loc[0];this.column=loc[1];}

    public boolean getPromoted(){return promoted;}
    public void setPromoted(boolean p){promoted = p;}
}
