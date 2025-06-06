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

            System.out.print("Player "+counter+"'s "+"("+p.getIcon()+"\u001B[0m) name (leave blank to skip this player): ");
            String name = scanner.nextLine();
            p.setName(name);
            if(!name.isBlank()||counter==1){
                boolean brk = false;while(!brk) {
                    System.out.print(p.getName() + "'s ability is (type \"list\" for a list, leave blank for default): ");

                    String nextLn = scanner.nextLine().toLowerCase();
                    if(nextLn.isBlank()) {
                        p.setAbility(AbilityHandler.getAbility("Default"));
                        break;
                    }

                    nextLn = nextLn.substring(0,1).toUpperCase() + nextLn.substring(1);

                    if (nextLn.equals("List")) {
                        System.out.println();
                        for (String[] line : abilitiesCells) {
                            System.out.println(line[0] + " - " + line[1]);
                        }
                    }else if(AbilityHandler.getAbility(nextLn)!=null){
                        p.setAbility(AbilityHandler.getAbility(nextLn));
                        brk = true;
                    }else{
                        System.out.println("Invalid ability, try again.");
                    }
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
        while(!win){//main game loop
            for(int i=0;i<4;i++){//each player loop, resets to stay in check with current player
                board.setCurrentPlayer(i);
                if(!board.getPlayers()[board.getCurrentPlayer()].isSkipped()) {

                    System.out.println(board.getPlayers()[i].getName() + " (" + board.getPlayers()[i].getIcon() + "\u001B[0m)" + "'s Turn: ");
                    System.out.println(board.draw());

                    boolean validMove = false;

                    if(!hasPieces(board.getPlayers()[board.getCurrentPlayer()])){
                        validMove = true;
                        board.getPlayers()[i].setSkipped(true);
                    }

                    while (!validMove) {
                        String[] line = {};

                        boolean temp = false;
                        while (!temp) {
                            System.out.print("What would you like to move (? to ?): ");
                            String nextLn = scanner.nextLine();
                            if (!nextLn.trim().isEmpty() && !nextLn.isBlank()) {
                                line = nextLn.toLowerCase().split(" ");
                                temp = true;
                            } else {
                                System.out.println("Invalid move, try again.");
                            }
                        }

                        if (line[0].equals("exit")) {
                            System.out.println("Exiting...");
                            System.exit(0);
                        }

                        if (tryMove(line)) {
                            validMove = true;
                        } else {
                            System.out.println("Invalid move, try again.");
                        }
                    }
                }

                System.out.println();
                board.rotateBoard(1);
                if(board.getActivePlayers().length==1){
                    win = true;
                    break;
                }
            }
        }

        boolean temp = true; while(temp){
            System.out.print("Would you like to play again? ");
            String nextLn = scanner.nextLine();
            if(nextLn.toLowerCase().charAt(0) == 'y'){
                temp = false;
                init();
            }else if(nextLn.toLowerCase().charAt(0) == 'n'){
                temp = false;
            }
            System.out.println();
        }
    }

    private boolean tryMove(String[] line){
        try {
            int[] start = new int[]{Integer.parseInt(line[0].substring(1)) - 1, line[0].charAt(0) - 97};
            int[] end = new int[]{Integer.parseInt(line[line.length - 1].substring(1)) - 1, line[line.length - 1].charAt(0) - 97};
            if (board.jump(board.getLoc(start[0], start[1]), end) || board.move(board.getLoc(start[0], start[1]), end)) {
                return true;
            }
        }catch(Exception _){return false;}
        return false;
    }

    private boolean hasPieces(Player plr){
        for(Player p: board.getActivePlayers()){
            if(p==plr){return true;}
        }
        return false;
    }

}
