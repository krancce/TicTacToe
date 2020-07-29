public class TicTacToe {
    public static final int TRAINING_ROUNDS = 500;

    private static void train(Player[] players) {

        //int first  = Utils.generator.nextInt(2);
        int numberOfPlays = TRAINING_ROUNDS;
        while(numberOfPlays > 0) {
            TicTacToeGame game = new TicTacToeGame();
            int turn = 0; //(first++)%2;
            players[turn%2].startNewGame(CellValue.X);
            players[(turn+1)%2].startNewGame(CellValue.O);
            while(game.getGameState() == GameState.PLAYING) {
                players[turn%2].play(game);
                turn++;
            }
            players[0].gameFinished(game.getGameState());
            players[1].gameFinished(game.getGameState());
            numberOfPlays--;
        }
        System.out.println("player 1: " + players[0]) ;  
        System.out.println("player 2: " + players[1]) ;  
   }
   /**
     * <b>main</b> of the application. 
     * 
     * @param args
     *            command line parameters
     */

     public static void main(String[] args) {

        StudentInfo.display();

        
        
        ComputerMenacePlayer menace = new ComputerMenacePlayer();
        ComputerMenacePlayer menace2 = new ComputerMenacePlayer();
        ComputerRandomPlayer random = new ComputerRandomPlayer();
        ComputerPerfectPlayer  perfect = new ComputerPerfectPlayer();
        HumanPlayer human = new HumanPlayer();

        Player[] players = new Player[2];

        players[0] = new ComputerMenacePlayer();
        boolean stop = false;
//        int first = 0;
        while(!stop) {
            System.out.println("(1) Human to play menace");
            System.out.println("(2) Train Menace against perfect player");
            System.out.println("(3) Train Menace against random player");
            System.out.println("(4) Train Menace against another menace");
            System.out.println("(5) Delete (both) Menace training sets");
            System.out.println("(Q)uit");
            String answer = Utils.console.readLine().toLowerCase();
            if(answer.equals("q")) {
                stop = true;
            } else if (answer.equals("4")) {
                players[1] = menace2;
                train(players);
            } else if (answer.equals("3")) {
                players[1] = random;
                train(players);
            } else if (answer.equals("5")) {
                menace = new ComputerMenacePlayer();
                players[0] = menace;
                menace2 = new ComputerMenacePlayer();
            } else if (answer.equals("2")) {
                players[1] = perfect;
                train(players);
            } else if (answer.equals("1")) {
                players[1] = human;
                TicTacToeGame game;
                game = new TicTacToeGame();
                int turn = 0; //(first++)%2;;
                players[turn%2].startNewGame(CellValue.X);
                players[(turn+1)%2].startNewGame(CellValue.O);
                while(game.getGameState() == GameState.PLAYING) {
                    players[turn%2].play(game);
                    turn++;
                }
                players[0].gameFinished(game.getGameState());
                players[1].gameFinished(game.getGameState());
                
                System.out.println(game);
                System.out.println("Result: " + game.getGameState());
                System.out.println(players[0]);
                
            }
        }
    } 

}