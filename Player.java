public abstract class Player {

	private int numberOfWin;
	private int numberOfLoss;
	private int numberOfDraw;
	private int numberOfGame;
	private char[] slidingWindow;
	private int currentSlidingIndex;
	protected CellValue myMove;

	public static final int WINDOWSIZE = 50;
	public static final char IWIN = 'w';
	public static final char ILOSE = 'l';
	public static final char IDRAW = 'd';

	public Player(){
		numberOfWin = 0;
		numberOfLoss= 0;
		numberOfDraw= 0;
		numberOfGame= 0;
		slidingWindow = new char[WINDOWSIZE];
		currentSlidingIndex= 0;
	}

	public abstract void play(TicTacToeGame game);

	public void startNewGame(CellValue myMove){
		this.myMove = myMove;
	}

	public void gameFinished(GameState result){
		if(result == GameState.DRAW) {
			numberOfDraw++;
			slidingWindow[currentSlidingIndex] = IDRAW;
		} else if (result == GameState.XWIN) {
			if(myMove == CellValue.X) {
				numberOfWin++;
				slidingWindow[currentSlidingIndex] = IWIN;
			} else {
				numberOfLoss++;
				slidingWindow[currentSlidingIndex] = ILOSE;				
			}
		} else if (result == GameState.OWIN) {
			if(myMove == CellValue.O) {
				numberOfWin++;
				slidingWindow[currentSlidingIndex] = IWIN;
			} else {
				numberOfLoss++;
				slidingWindow[currentSlidingIndex] = ILOSE;				
			}
		} else {
			throw new IllegalArgumentException("Result can't be " + result);
		}
		numberOfGame++;
		currentSlidingIndex = (currentSlidingIndex+1)%WINDOWSIZE;
	}


	public String toString(){
		String result;

		result = "This player has won " + numberOfWin + " games, lost " 
				+ numberOfLoss + " games and " + numberOfDraw + " were draws.";

		if(numberOfGame >= WINDOWSIZE) {
			int w = 0;
			int l = 0;
			int d = 0;
			for(char c: slidingWindow) {
				switch(c){
					case IWIN:
						w++;
						break;
					case ILOSE:
						l++;
						break;
					case IDRAW:
						d++;
						break;
					default:
						System.out.println("Unknown value: " + c);
				}
			}
			result += " Over the last " + WINDOWSIZE + " games, this player has won " + w + " games, lost " 
				+ l + " games and " + d + " were draws.";
		}
		return result;
	}
	 
}