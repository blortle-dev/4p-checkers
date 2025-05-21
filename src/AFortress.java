public class AFortress implements Ability{
    @Override
    public void jump(Board b, Piece p, Piece p2, int[] end) {
        int[] start = p.getPosition();
        b.setLoc(end,p);
        b.setLoc(start,null);
        p2.setPlayer(new Player("","X","FortressExt"));
    }
}
