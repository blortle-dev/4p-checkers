public class Player {
    private String icon,name;
    public Player( String name, String icon) {
        this.icon = icon;
        this.name = name;
    }
    public String getIcon() {return icon;}
    public String getName() {return name;}

    public void setIcon(String icon) {this.icon = icon;}
    public void setName(String name) {this.name = name;}
}
