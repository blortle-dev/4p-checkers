public class ATraveler implements Ability{
    @Override
    public void jump(Board b, Piece p, Piece p2, int[] end) {
        b.setLoc(p2.getPosition(),null);
        b.setLoc(p.getPosition(), null);
    }
    public void move(Board b, Piece p, int[] end) {
        int[] start = p.getPosition();
        b.setLoc(start,null);
        b.setLoc(end,p);
        end = new int[]{
                end[0]+(end[0]-start[0]),
                end[1]+(end[1]-start[1]),
        };
        if(b.validMove(p.getPosition(),end,p.getPromoted(),false)) {
            b.setLoc(p.getPosition(),null);
            b.setLoc(end,p);
        }
    }
}
