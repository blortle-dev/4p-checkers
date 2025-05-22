public class ABuddha implements Ability {
    boolean movable = false;

    @Override
    public void move(Board b, Piece p, int[] end) {
        if(movable)
            Ability.super.move(b, p, end);
        else if(is1v1(b)){
            movable = true;
            move(b, p, end);
        }else{
            System.out.print("You may not move now.");
        }
    }

    @Override
    public void jump(Board b, Piece p, Piece p2, int[] end) {
        if (movable)
            Ability.super.jump(b, p, p2, end);
        else if(is1v1(b)){
            movable = true;
            jump(b, p, p2, end);
        }else{
            System.out.print("You may not move now.");
        }
    }

    private boolean is1v1(Board b){
        Player[] p = b.getActivePlayers();
        return p.length == 2 && (p[0].getAbility() == this || p[1].getAbility() == this);
    }
}
