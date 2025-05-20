import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new Input(new Board());
        AbilityHandler ah = new AbilityHandler();
        ArrayList<String[]> abs = ah.getAbilities();

        for (String[] ab : abs) {
            System.out.println(ab[0] + " - " + ab[1] + " - " + ab[2]);
        }
    }
}
