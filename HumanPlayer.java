public class HumanPlayer extends Player {
	

	public  void play(TicTacToeGame game) {

		if(game.getLevel() == game.lines*game.columns){
			throw new IllegalArgumentException("Game is finished already!");
		}

		boolean success = false;

		while(!success) {
        	System.out.println(game);
        	System.out.print(game.nextCellValue() + " to play: ");
        	String answer = Utils.console.readLine();
        	int value;
        	try {
                value = Integer.parseInt(answer)-1;
            } catch (NumberFormatException e) {
            	value = -1;
            }
            if(value < 0 || value >= (game.lines*game.columns)){
            	System.out.println("The value should be between 1 and " + (game.lines*game.columns));
            } else if(game.valueAt(value) != CellValue.EMPTY) {
            	System.out.println("This cell has already been played");
            } else {
            	game.play(value);
            	success = true;
            }
        }

	}
}