
public class ComputerRandomPlayer extends Player {

	public  void play(TicTacToeGame game) {

		if(game.getLevel() == game.lines*game.columns){
			throw new IllegalArgumentException("Game is finished already!");
		}
	
		int choice;
		do {
			choice = Utils.generator.nextInt(game.lines*game.columns);
		} while (game.valueAt(choice) != CellValue.EMPTY);

		game.play(choice);
	}

}