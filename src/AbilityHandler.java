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
            Class<?> clazz = Class.forName(name);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            return instance instanceof Ability ? (Ability) instance: null;
        }catch(Exception e){
            return null;
        }
    }

    public ArrayList<String[]> getAbilities() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("src/abilities.txt"));
        } catch (FileNotFoundException _) {
            // hi! :D
        }

        ArrayList<String[]> out = new ArrayList<>();

        int len = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.isEmpty() || line.charAt(0) == '@') continue;
            String[] split = line.split(" !! ");
            out.add(split);
            len++;
        }

        return out;
    }


}

// oh phew 😘
// brb pooks
//bro what is platypi 😭
// note - platypi font
// back to plearth

//oi
// check google slides
// shared with me k Object clazz = Class.forName(name);
//        Ability ability = (Ability) clazz; 🔥 bro sophomore year looks so light
//        fr
//        and we have baguettes
