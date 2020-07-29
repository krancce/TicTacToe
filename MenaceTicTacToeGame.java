public class MenaceTicTacToeGame extends TicTacToeGame {
	
	private int[] currentOdds;
	private int currentTotal;
	private int currentSelection;

	public MenaceTicTacToeGame(){
		super(3,3,3);
		currentOdds = new int[9];
		currentTotal = 0;
		currentSelection = -1;
	}

	public MenaceTicTacToeGame(MenaceTicTacToeGame base, int next){
		super(base,next);
		currentOdds = new int[9];
		currentTotal = 0;
		currentSelection = -1;		
	}

	public void setOdds (int location, int number) {
		if(location < 0 || location >= currentOdds.length || 
			number < 0 || currentOdds[location] != 0 ) {
			throw new IllegalArgumentException();
		}
		currentOdds[location] = number;
		currentTotal += number;
	}

	public int chooseMenaceMove(){
		if(currentSelection != -1) {
			throw new IllegalStateException("This menace game has already selected a move and is waiting on the verdict");
		}
		int randomDraw = Utils.generator.nextInt(currentTotal)+1;
		currentSelection = 0;
		int sum = currentOdds[transformedBoard[currentSelection]];
		while(sum<randomDraw) {
			currentSelection++;
			sum += currentOdds[transformedBoard[currentSelection]];
		}
		return currentSelection;
	}

	public void verdict(int penalty) {
		if(currentSelection == -1) {
			throw new IllegalStateException("This menace is not waiting on a verdict");
		}

		if(currentTotal+penalty <= 0) {
			//System.out.println("Removing last possible move. Will keep it to avoid deadlock");
		} else {
			currentTotal += penalty;
			currentOdds[transformedBoard[currentSelection]]+= penalty;
		}
		currentSelection = -1;	
	}

	// debug method
	public String getCurrentOdds(){
		return java.util.Arrays.toString(currentOdds);
	}

}