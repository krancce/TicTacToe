import java.util.LinkedList;


public class ComputerMenacePlayer extends Player {

	private LinkedList<LinkedList<MenaceTicTacToeGame>> allGames;

//	private static final int[] INITIAL_WEIGHT = {4,4,3,3,2,2,1,1,1};
	private static final int[] INITIAL_WEIGHT = {8,8,4,4,2,2,1,1,1};
	private static final int PENALTY_WIN = 3;
	private static final int PENALTY_DRAW = 1;
	private static final int PENALTY_LOSE = -1;


	private LinkedList<MenaceTicTacToeGame> seriesOfGames;

	public ComputerMenacePlayer(){
		super();

		allGames = new LinkedList<LinkedList<MenaceTicTacToeGame>>();

		// start with the empty game
		allGames.add(new LinkedList<MenaceTicTacToeGame>());
		allGames.get(0).add(new MenaceTicTacToeGame());

		//build the new games by adding the next moves to the
		// previously built games
		for(int i=1; i<= 9; i++) {
			LinkedList<MenaceTicTacToeGame> newList;
			newList = new LinkedList<MenaceTicTacToeGame>();
			allGames.add(newList);
			for(MenaceTicTacToeGame game: allGames.get(i-1)){
				if(game.getGameState() == GameState.PLAYING) {
					for(int j = 0;
						j < 9;
						j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							
							// the following is OK but adds beads for symmetrically
							// equivalent moves
							//game.setOdds(j,INITIAL_WEIGHT[i-1]);
							
							MenaceTicTacToeGame newGame = new MenaceTicTacToeGame(game,j);
							//checking that this game was not already found
							boolean isNew = true;
							for(MenaceTicTacToeGame existingGame: allGames.get(i)){
								if(newGame.equalsWithSymmetry(existingGame)){
									isNew = false;
									break;
								}
							}
							if(isNew) {
								newList.add(newGame);
							}					
						}
					}
				}

			}
		}
				
		// now adding the odds for menace, without adding beads for
		// symmetrically equivalent moves
		for(int i=0; i< 9; i++) {
			for(MenaceTicTacToeGame game: allGames.get(i)){
				if(game.getGameState() == GameState.PLAYING) {
					LinkedList<MenaceTicTacToeGame> listOfNextGames;
					listOfNextGames = new LinkedList<MenaceTicTacToeGame>();
					for(int j=0;
						j < 9;
						j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							MenaceTicTacToeGame newGame = new MenaceTicTacToeGame(game,j);
							//checking that a symmetrical game was not already found
							boolean isNew = true;
							for(MenaceTicTacToeGame existingGame: listOfNextGames){
								if(newGame.equalsWithSymmetry(existingGame)){
									isNew = false;
									break;
								}
							}
							if(isNew) {
								listOfNextGames.add(newGame);
								game.setOdds(j,INITIAL_WEIGHT[i]);
							}
						}
					}
				}

			}
		}
	
	
	}
	
	public void startNewGame(CellValue myMove){
		super.startNewGame(myMove);
		seriesOfGames = new LinkedList<MenaceTicTacToeGame>();
	}


	public void gameFinished(GameState result){
		super.gameFinished(result);
		int penalty;
		if(result == GameState.DRAW) {
			penalty = PENALTY_DRAW;
		} else if (result == GameState.XWIN) {
			if(myMove == CellValue.X) {
				penalty = PENALTY_WIN;
			} else {
				penalty = PENALTY_LOSE;				
			}
		} else {
			if(myMove == CellValue.O) {
				penalty = PENALTY_WIN;
			} else {
				penalty = PENALTY_LOSE;				
			}
		} 
		for( MenaceTicTacToeGame game: seriesOfGames ){
			game.verdict(penalty);
		}
	}


	public  void play(TicTacToeGame game) {

		if(game.getLevel() == game.lines*game.columns){
			throw new IllegalArgumentException("Game is finished already!");
		}
	
		// find the menaceGame corresponding to the state of game
		for(MenaceTicTacToeGame menaceGame: allGames.get(game.getLevel())){
			if(menaceGame.equalsWithSymmetry(game)){
				game.play(menaceGame.chooseMenaceMove());
				seriesOfGames.add(menaceGame);
				return;
			}
		}

		//Should never reach here
		throw new IllegalStateException("Game not found: " + game);

	}

}