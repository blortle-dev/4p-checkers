public class AConquerer implements Ability {
    int conqueredPieces = 0;
    @Override
    public void jump(Board b, Piece p, Piece p2, int[] end) {
        if(conqueredPieces==5){
            Ability.super.jump(b, p, p2, end);
        }else{
            conqueredPieces++;
            b.setLoc(p.getPosition(),null);
            p2.setPlayer(p.getPlayer());
            b.setLoc(end,p);
        }
    }
}