import java.util.LinkedList;

public class ComputerPerfectPlayer extends Player {
	
	private LinkedList<LinkedList<PerfectTicTacToeGame>> allGames;

	public ComputerPerfectPlayer(){
		super();

		allGames = new LinkedList<LinkedList<PerfectTicTacToeGame>>();

		// start with the empty game
		allGames.add(new LinkedList<PerfectTicTacToeGame>());
		allGames.get(0).add(new PerfectTicTacToeGame());

		//build the new games by adding the next moves to the
		// previously built games
		for(int i=1; i<= 9; i++) {
			LinkedList<PerfectTicTacToeGame> newList;
			newList = new LinkedList<PerfectTicTacToeGame>();
			allGames.add(newList);
			for(PerfectTicTacToeGame game: allGames.get(i-1)){
				if(game.getGameState() == GameState.PLAYING) {
					for(int j = 0;
						j < 9;
						j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							PerfectTicTacToeGame newGame = new PerfectTicTacToeGame(game,j);
							//checking that this game was not already found
							boolean isNew = true;
							for(PerfectTicTacToeGame existingGame: allGames.get(i)){
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

		// now adding the game outcomes
		for(int i=8; i>= 0; i--) {
			for(PerfectTicTacToeGame game: allGames.get(i)){
				if(game.getGameOutcome() == PerfectTicTacToeGame.NOT_SET) {
					for(int j=0;
						j < 9;
						j++) {
						if(game.valueAt(j) == CellValue.EMPTY) {
							PerfectTicTacToeGame newGame = new PerfectTicTacToeGame(game,j);
							//looking for the game reached by j is played
							for(PerfectTicTacToeGame existingGame: allGames.get(i+1)){
								if(newGame.equalsWithSymmetry(existingGame)){
									// reverse the outcome
									if(existingGame.getGameOutcome() == PerfectTicTacToeGame.WIN) {
										game.setMoveOutcome(j,PerfectTicTacToeGame.LOSE);
									} else if(existingGame.getGameOutcome() == PerfectTicTacToeGame.LOSE) {
										game.setMoveOutcome(j,PerfectTicTacToeGame.WIN);
									} else if(existingGame.getGameOutcome() == PerfectTicTacToeGame.DRAW) {
										game.setMoveOutcome(j,PerfectTicTacToeGame.DRAW);
									} else {
										System.out.println(existingGame);

										throw new IllegalStateException("This should not be happening");
									}
									break;
								}
							}						
						}
					}
				}

			}
		}

	}


	public  void play(TicTacToeGame game) {

		if(game.getLevel() == game.lines*game.columns){
			throw new IllegalArgumentException("Game is finished already!");
		}
	
		// find the menaceGame corresponding to the state of game
		for(PerfectTicTacToeGame perfectGame: allGames.get(game.getLevel())){
			if(perfectGame.equalsWithSymmetry(game)){
				game.play(perfectGame.choosePerfectMove());
				return;
			}
		}

		//Should never reach here
		throw new IllegalStateException("Game not found: " + game);

	}

}