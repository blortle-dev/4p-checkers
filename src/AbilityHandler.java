import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class AbilityHandler {

    // wait u wanted to do it so thatone ability no each player gets to pick an ability
    // is picked at the start of the game and applies to everybody on every turn right
    // that works for the whole game? sounds good
    // so abilities.txt is how we'll store them
    // default syntax is there k pookie
    // and to make an ability i think we have an Ability parent class (i'll rename this one)
    // and each other one can extend Ability
    public static Ability getAbility(String name){
        try{
            Class<?> clazz = Class.forName("A"+name);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            return instance instanceof Ability ? (Ability) instance: null;
        }catch(Exception e){
            return null;
        }
    }

    public static String[][] getAbilities() {
        Scanner sc;
        try {
            sc = new Scanner(new File("src/abilities.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Abilities file not found.");
            return new String[][]{{"Default","The player has no abilities."}};
        }

        ArrayList<String[]> out = new ArrayList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.isEmpty() || line.charAt(0) == '@') continue;
            String[] split = line.split(" !! ");
            out.add(split);
        }

        return out.toArray(new String[0][]);
    }


}
