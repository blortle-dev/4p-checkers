import java.util.Scanner;

public class Input {
    private Board board;
    private Player[] players;
    private int currentPlayer;
    private Scanner scanner;
    public Input(Board b) {
        this.board = b;
        players = b.getPlayers();
        this.scanner = new Scanner(System.in);
    }
    public void startSeq(){
        board.init();
        int counter = 0;
        for(Player p: players){
            counter++;
            System.out.print("Player "+counter+" name: ");
            p.setName(scanner.nextLine());
        }
    }
}
