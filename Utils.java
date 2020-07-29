import java.util.Random;
import java.io.Console;

public class Utils {
	public static final Random generator = new Random();
	public static final Console console = System.console();
	public static final String NEW_LINE = System.getProperty("line.separator");



    /**
     * rotates the game
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * into the game
     *
     * 7 | 4 | 1
     * ----------
     * 8 | 5 | 2
     * ----------
     * 9 | 6 | 3
     */


    public static void rotate(int lines, int columns, int[] transformedBoard){
    	if(lines != columns) {
    		throw new IllegalArgumentException("Cannot rotate a non square board");
    	}
    	if(transformedBoard == null) {
    		throw new NullPointerException("transformedBoard cannot be null");
    	}
    	if((lines < 1) || (columns < 1) || (transformedBoard.length != lines*columns)){
    		throw new IllegalArgumentException("rotate called with incorrect arguments");
    	}

    	int[] tmp;
    	tmp = new int[transformedBoard.length];

    	for(int i =0 ; i < transformedBoard.length; i++) {
	                tmp[i] = transformedBoard[i];
	    }

    	for(int i =0 ; i < columns; i++) {
    		for(int j = 0; j < lines ; j++) {
    			transformedBoard[j*lines+i]=tmp[(columns-i-1)*lines+j];
    		}
	    }

    }

    /**
     * horizontalFlip flips the game
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * into the game
     *
     * 7 | 8 | 9
     * ----------
     * 4 | 5 | 6
     * ----------
     * 1 | 2 | 3
     */

    public static  void horizontalFlip(int lines, int columns, int[] transformedBoard){
    	if(transformedBoard == null) {
    		throw new NullPointerException("transformedBoard cannot be null");
    	}
    	if((lines < 1) || (columns < 1) || (transformedBoard.length != lines*columns)){
    		throw new IllegalArgumentException("horizontalFlip called with incorrect arguments");
    	}
    	int tmp;
	   	for(int i = 0; i < (lines/2); i++) {
    		for(int j=0 ; j< columns; j++) {
    			tmp = transformedBoard[(lines-i-1)*columns + j];
    			transformedBoard[(lines-i-1)*columns + j] = transformedBoard[i*columns + j];
    			transformedBoard[i*columns + j] = tmp;
    		}
    	}
    }

   /**
     * verticalFlip flips the game
     *
     * 1 | 2 | 3
     * ----------
     * 4 | 5 | 6
     * ----------
     * 7 | 8 | 9
     *
     * into the game
     *
     * 3 | 2 | 1
     * ----------
     * 6 | 5 | 4
     * ----------
     * 9 | 8 | 7
     */

    public static  void verticalFlip(int lines, int columns, int[] transformedBoard){
    	if(transformedBoard == null) {
    		throw new NullPointerException("transformedBoard cannot be null");
    	}
    	if((lines < 1) || (columns < 1) || (transformedBoard.length != lines*columns)){
    		throw new IllegalArgumentException("verticalFlip called with incorrect arguments");
    	}
    	int tmp;
	   	for(int i = 0; i < (lines); i++) {
    		for(int j=0 ; j< (columns/2); j++) {
    			tmp = transformedBoard[(i+1)*columns -j-1];
    			transformedBoard[(i+1)*columns -j-1] = transformedBoard[i*columns + j];
    			transformedBoard[i*columns + j] = tmp;
    		}
    	}
    }

    private static void test(int lines, int columns){
    	int[] test;
    	test = new int[lines*columns];
    	for(int i = 0 ; i < test.length; i++){
    		test[i] = i;
    	}
    	System.out.println("testing " + lines + " lines and " + columns + " columns.");
    	System.out.println(java.util.Arrays.toString(test));
    	horizontalFlip(lines,columns,test);
    	System.out.println("HF => " + java.util.Arrays.toString(test));
    	horizontalFlip(lines,columns,test);
    	System.out.println("HF => " + java.util.Arrays.toString(test));
    	verticalFlip(lines,columns,test);
    	System.out.println("VF => " + java.util.Arrays.toString(test));
    	verticalFlip(lines,columns,test);
    	System.out.println("VF => " + java.util.Arrays.toString(test));
    	if(lines == columns){
    		for(int i = 0; i < 4; i++) {
		    	rotate(lines,columns,test);
		    	System.out.println("ROT => " + java.util.Arrays.toString(test));    			
    		}
    	}
    }

    public static void main(String[] args){
    	int[] test;
    	int lines, columns;

    	test(2,2);
    	test(2,3);
    	test(3,3);
    	test(4,3);
    	test(4,4);


    }
}