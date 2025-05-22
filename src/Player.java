public class Player {
    private final String icon;
    private String name;
    private Ability ability;
    private boolean skip;
    public Player(String name, String icon, String ability) {
        this.icon = icon;
        this.name = name;
        this.ability = AbilityHandler.getAbility(ability);
        skip = name.isBlank();
    }
    public String getIcon() {return icon;}
    public String getName() {return name;}

    //public void setIcon(String icon) {this.icon = icon;}
    public void setName(String name) {
        this.name = name;
        skip = name.isBlank();
    }

    public void setAbility(Ability ability) {this.ability = ability;}
    public Ability getAbility() {return ability;}

    public boolean isSkipped() {return skip;}
    public void setSkipped(boolean skip) {this.skip = skip;}
}
