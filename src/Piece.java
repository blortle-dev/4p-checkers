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

    public boolean jump(Piece enemy, int[] endLocation){
        //TODO: custom logic goes here, boolean indicates if the piece jumped is removed or not
        row = endLocation[0];
        column = endLocation[1];
        return true;
    }
    public void move(int[] start, int[] end){
        row = end[0];
        column = end[1]; //TODO: add extra logic here
    }
}
