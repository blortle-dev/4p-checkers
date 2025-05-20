public interface Ability {
    default void init(Board b, Piece p){}
    default void move(Board b, Piece p, int[] end){
        b.setLoc(p.getPosition(),null);
        b.setLoc(end,p);
    }
    default void jump(Board b, Piece p, Piece p2, int[] end){
        b.setLoc(p.getPosition(),null);
        b.setLoc(p2.getPosition(),null);
        b.setLoc(end,p);
    }
    default void promote(Board b, Piece p){
        p.setPromoted(true);
    }
}
