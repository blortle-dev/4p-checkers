import java.util.Scanner;

public class Input {
    private final Board board;
    private final Scanner scanner;
    private final String[][] abilitiesCells;
    public Input(Board b) {
        this.board = b;
        this.scanner = new Scanner(System.in);
        abilitiesCells = AbilityHandler.getAbilities();

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
            boolean brk = false;while(!brk) {
                System.out.print(p.getName() + "'s ability is (type \"list\" for a list): ");

                String nextLn = scanner.nextLine();
                if (nextLn.equals("list")) {
                    for (String[] line : abilitiesCells) {
                        System.out.println(line[0] + " - " + line[1]);
                    }
                }else if(AbilityHandler.getAbility(nextLn)!=null){
                    p.setAbility(AbilityHandler.getAbility(nextLn));
                    brk = true;
                }else{
                    System.out.println("Invalid ability, try again.");
                }
                System.out.println();
            }
        }

        System.out.println("Setting up the game board...");
    }
    public void init(){
        board.init();
        startSeq();

        boolean win = false;
        while(!win){
            for(int i=0;i<4;i++){
                board.setCurrentPlayer(i);

                boolean validMove = false;
                while(!validMove){
                    System.out.println(board.getPlayers()[i].getName()+" ("+board.getPlayers()[i].getIcon()+"\u001B[0m)"+"'s Turn: ");
                    System.out.print(board.draw()+"\nWhat would you like to move (? to ?): ");

                    String[] line = scanner.nextLine().toLowerCase().split(" ");

                    if(!board.getPlayers()[board.getCurrentPlayer()].isSkipped()) {
                        int[] start = new int[]{Integer.parseInt(line[0].substring(1))-1,line[0].charAt(0)-97};
                        int[] end = new int[]{Integer.parseInt(line[2].substring(1))-1,line[2].charAt(0)-97};

                        if(board.jump(board.getLoc(start[0],start[1]),end) || board.move(board.getLoc(start[0],start[1]),end)){
                            validMove = true;
                        }
                        else{
                            System.out.println("Invalid Move.");
                        }
                    }else{
                        validMove = true;
                    }
                }

                System.out.println();
                board.rotateBoard(1);
            }
        }

        boolean temp = true; while(temp){
            System.out.print("Would you like to play again? ");
            if(scanner.nextLine().toLowerCase().charAt(0) == 'y'){
                temp = false;
                init();
            }else if(scanner.nextLine().toLowerCase().charAt(0) == 'n'){
                temp = false;
            }
            System.out.println();
        }
    }

}
