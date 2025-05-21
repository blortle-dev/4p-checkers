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

            System.out.print("Player "+counter+"'s "+"("+p.getIcon()+"\u001B[0m) name (type \"skip\" to skip this player): ");
            String name = scanner.nextLine();
            p.setName(name);
            if(!name.equals("skip")){
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
                    System.out.println();
                }
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
                System.out.println(board.getPlayers()[i].getName()+" ("+board.getPlayers()[i].getIcon()+"\u001B[0m)"+"'s Turn: ");
                System.out.println(board.draw());

                while(!validMove&&!board.getPlayers()[board.getCurrentPlayer()].isSkipped()){
                    String[] line = {};

                    boolean brk = false; while(!brk){
                        System.out.print("What would you like to move (? to ?): ");
                        String nextLn = scanner.nextLine();
                        if(!nextLn.trim().isEmpty()&&!nextLn.isBlank()){
                            line = nextLn.toLowerCase().split(" ");
                            brk = true;
                        }else{
                            System.out.println("Invalid move, try again.");
                        }
                    }

                    String exitCode = "exit";
                    if (line[0].equals(exitCode)) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }

                    if(tryMove(line)){
                        validMove = true;
                    }else{
                        System.out.println("Invalid move, try again.");
                    }
                }

                System.out.println();
                board.rotateBoard(1);
                if(board.winner()!=null){
                    win = true;
                    break;
                }
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

}
