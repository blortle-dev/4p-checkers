import java.util.Scanner;

public class Input {
    private final Board board;
    private final Scanner scanner;
    public Input(Board b) {
        this.board = b;
        this.scanner = new Scanner(System.in);
        init();
    }

    private void startSeq(){
        System.out.println("WELCOME TO FOUR PLAYER CHECKERS");

        board.init();
        int counter = 0;
        for(Player p: board.getPlayers()){
            counter++;
            System.out.print("Player "+counter+"'s "+"("+p.getIcon()+"\u001B[0m) name: ");
            p.setName(scanner.nextLine());
        }

        System.out.println("Setting up the game board...");

        //TODO: implement abilities
    }
    public void init(){
        board.init();
        startSeq();

        boolean running = true, win = false;
        while(running){
            for(int i=0;i<4;i++){
                board.setCurrentPlayer(i);

                boolean validMove = false;
                while(!validMove){
                    System.out.println(board.getPlayers()[i].getName()+" ("+board.getPlayers()[i].getIcon()+"\u001B[0m)"+"'s Turn: ");
                    System.out.print(board.draw()+"\nWhat would you like to move (? to ?): ");

                    String[] line = scanner.nextLine().toLowerCase().split(" ");

                    int[] start = new int[]{Integer.parseInt(line[0].substring(1))-1,line[0].charAt(0)-97};
                    int[] end = new int[]{Integer.parseInt(line[2].substring(1))-1,line[2].charAt(0)-97};

                    if(board.jump(board.getLoc(start[0],start[1]),end) || board.move(board.getLoc(start[0],start[1]),end)){
                        validMove = true;
                    }
                    else{
                        System.out.println("Invalid Move.");
                    }
                }

                System.out.println();
                board.rotateBoard(1);
            }

            System.out.println("Would you like to play again?");
            if(scanner.nextLine().toLowerCase().charAt(0) == 'n'){
                running = false;
            }
        }
    }

}
