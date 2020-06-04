package game;
import javafx.scene.control.TextField;
/**
 * This class checks the logic of a Sudoku and fills a double array of booleans to denote whether
 * rows, columns, or squares are correct.
 * @author Lewis Drew
 * @version 04/06/20
 */
public class SudokuCheck {
	private static TextField announcement = gui.SudokuGUI.announcement;

	/**
	 * Method to create a double boolean array to represent whether different parts of the Sudoku
	 * are correct. check[0] shows whether each row is correct, check[1] shows whether each
	 * column is correct, and check[2] shows whether each square is correct.
	 * @param sudoku The Sudoku to be checked.
	 * @return A double boolean array representing whether each section of the Sudoku is correct.
	 */
	public static boolean[][] check(Sudoku sudoku) {
		int[][] array = sudoku.getArray();
		boolean[][] result = new boolean[3][9];

		// result[0] is for rows 1-9
		// loops through number of rows
		for (int i = 0; i < array.length; i++) {
			result[0][i] = true;
			// loops through elements of each row
			for (int j = 0; j < array[i].length; j++)
				// loops through elements again for comparison if element isn't the last one
				if (j != array[i].length) {
					for (int k = j + 1; k < array[i].length; k++)
						// if element is the same as primary element, return false
						if (array[i][j] == array[i][k] && array[i][j] != 0) {
							announcement.setText("Hint: " + array[i][j] + " appears more than once in row " + (i + 1));
							result[0][i] = false;
						}
				}
		}

		// result[1] is for columns 1-9
		// loops through number of rows
		for (int i = 0; i < 9; i++) {
			result[1][i] = true;
			// loops through each element of row
			for (int j = 0; j < 8; j++)
				// loops through elements of column
				for (int k = j + 1; k < 9; k++)
					// if column contains duplicates, return false
					if (array[j][i] == array[k][i] && array[j][i] != 0) {
						announcement.setText("Hint: " + array[j][i] + " appears more than once in in column " + (i + 1));
						result[1][i] = false;
					}
		}

		for (int i = 0; i < result[2].length; i++)
			result[2][i] = true;

		// result[2] is for squares
		// loops through squares, left to right (columns)
		int squareNo = 0;
		for (int i = 0; i < 3; i++)
			// loops through squares, top to bottom (rows)
			for (int j = 0; j < 3; j++)	{	
				int[] square = new int[9];
				squareNo++;
				int count = 0;
				// loops through rows of one square, adding contents to an array
				for (int k = (3 * j); k < (3 * j) + 3; k++)
					// loops through elements of square, adds them to square[] array
					for (int l = (3 * i); l < (3 * i) + 3; l++) {
						square[count] = array[l][k];
						count++;
					}
				// loop through square to find duplicates
				for (int m = 0; m < 9; m++) 
					for (int n = m + 1; n < 9; n++)
						if (square[m] == square[n] && square[m] != 0) {
							announcement.setText("Hint: " + square[m] + " appears more than once in square " + squareNo);
							result[2][squareNo - 1] = false;
						}
			}
		return result;
	}
}