public class Player {
    private String icon,name;
    private Ability ability;
    public Player(String name, String icon, String ability) {
        this.icon = icon;
        this.name = name;
        this.ability = AbilityHandler.getAbility(ability);
    }
    public String getIcon() {return icon;}
    public String getName() {return name;}

    public void setIcon(String icon) {this.icon = icon;}
    public void setName(String name) {this.name = name;}

    public void setAbility(Ability ability) {this.ability = ability;}
    public Ability getAbility() {return ability;}
}
