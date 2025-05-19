import java.io.IOException;
import java.util.Scanner;

public class Input {
    private final Board board;
    private final Player[] players;
    private int currentPlayer;
    private final Scanner scanner;
    public Input(Board b) {
        this.board = b;
        players = b.getPlayers();
        this.scanner = new Scanner(System.in);
        init();
    }
    public void init(){
        board.init();

        boolean running = true, win = false;
        while(running){
            startSeq();
            board.setLoc(new int[]{0,6},new Piece(0,6,board.getPlayers()[0]));

            for(int i=0;i<4;i++){
                System.out.println(board.getPlayers()[i].getName()+"'s Turn: ");
                System.out.print(board.draw()+"\nWhat would you like to move (? to ?): ");

                String[] line = scanner.nextLine().toLowerCase().split(" ");

                int[] start = new int[]{Integer.parseInt(line[0].charAt(1)+"")-1,line[0].charAt(0)-97};
                int[] end = new int[]{Integer.parseInt(line[2].charAt(1)+"")-1,line[2].charAt(0)-97};

                if(!board.move(board.getLoc(start[0],start[1]),end)){
                    System.out.println("Invalid Move");
                }

                System.out.println();
                //board.rotateBoard();
            }

            System.out.println("Would you like to play again?");
            if(scanner.nextLine().toLowerCase().charAt(0) == 'n'){
                running = false;
            }
        }
    }
    private void startSeq(){
        System.out.println("WELCOME TO FOUR PLAYER CHECKERS");

        board.init();
        int counter = 0;
        for(Player p: players){
            counter++;
            System.out.print("Player "+counter+" name: ");
            p.setName(scanner.nextLine());
        }

        System.out.println("Setting up the game board...");

        //TODO: implement abilities
    }
}
