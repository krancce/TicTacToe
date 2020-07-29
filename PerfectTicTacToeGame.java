public class PerfectTicTacToeGame extends TicTacToeGame {
	
	public static final int NOT_SET = 0;
	public static final int WIN  = 1;
	public static final int LOSE = 2;
	public static final int DRAW = 3;
	private int[] outcomes;
	private int gameOutcome;

	public PerfectTicTacToeGame(){
		super(3,3,3);
		outcomes = new int[9];
		gameOutcome = NOT_SET;
	}

	public PerfectTicTacToeGame(PerfectTicTacToeGame base, int next){
		super(base,next);
		outcomes = new int[9];
		gameOutcome = NOT_SET;
	}

	public void setMoveOutcome(int move, int outcome){
		if(move < 0 || move >= outcomes.length || 
		outcome < WIN || outcome > DRAW || outcomes[move] != 0 ) {
			throw new IllegalArgumentException();
		}
		outcomes[move] = outcome;
		if(outcome == WIN) {
			gameOutcome = WIN; 
		} else if (outcome == DRAW && gameOutcome != WIN ) {
			gameOutcome = DRAW; 
		} else if (outcome == LOSE && gameOutcome == NOT_SET) {
			gameOutcome = LOSE;
		}
	}

	public int getGameOutcome() {
		if(getGameState() == GameState.XWIN || getGameState() == GameState.OWIN ){
			// from the viewpoint of a player who would have to play next, a
			// game that has just been won is losing
			return LOSE; 
		} else if(getGameState() == GameState.DRAW ){
			return DRAW;
		} else {
			return gameOutcome;
		}
	}

	public int choosePerfectMove(){
		if(getGameState() != GameState.PLAYING){
			throw new IllegalStateException("Game already finished");
		}
		int choices = 0;
		for(int i : outcomes) {
			if(i == gameOutcome)
				choices++;
		}


		int randomChoice = Utils.generator.nextInt(choices);
		int currentSelection = 0;
		boolean search = true;
		while(search) {
			if(outcomes[transformedBoard[currentSelection]] == gameOutcome){
				if(randomChoice == 0) {
					search = false;
				} else {
					randomChoice--;
					currentSelection++;
				}
			} else {
				currentSelection++;
			}
		}
		return currentSelection;
	}

}