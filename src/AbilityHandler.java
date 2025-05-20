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


}

// oh phew 😘
// brb pooks
//bro what is platypi 😭
// note - platypi font
// back to plearth

//oi
// check google slides
// shared with me k Object clazz = Class.forName(name);
//        Ability ability = (Ability) clazz; 🔥 bro sophomore year looks so light fr
// and we have baguettes