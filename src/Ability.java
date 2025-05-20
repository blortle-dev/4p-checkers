public interface Ability {
    public void init(Board b, Piece p);
    public void move(Board b, Piece p, int[] end);
    public void jump(Board b, Piece p, int[] end);
    public void promote(Board b, Piece p);
}
