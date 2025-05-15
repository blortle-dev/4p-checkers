public class Piece {
    private int row,column;
    private Player plr;
    public Piece(int row, int column, Player p){
        this.row=row;
        this.column=column;
        plr = p;
    }

    public String getIcon(){return plr.getIcon();}

    public Player getPlayer(){return plr;}
    public void setPlayer(Player p){plr=p;}

    public int[] getPosition(){
        return new int[]{row,column};
    }
    public void setPosition(int[] loc){this.row = loc[0];this.column=loc[1];}

    public boolean jump(Piece enemy, int[] endLocation){
        //TODO: custom logic goes here, boolean indicates if the piece jumped is removed or not
        row = endLocation[0];
        column = endLocation[1];
        return true;
    }
}
