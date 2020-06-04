package game;
import java.io.*;
/**
 * This class reads Sudoku grids from .txt files.
 * @author Lewis Drew
 * @version 03/06/20
 */
public class SudokuRead {

	/**
	 * This method uses a BufferedReader to take a file as input and read it to determine whether
	 * it is suitable for converting into a Sudoku grid. If it meets the conditions, it is then
	 * converted into an array of integers.
	 * @param fileName The name of the file to be taken as input
	 * @return A Sudoku object created from the .txt file taken as input
	 * @throws IllegalArgumentException If the file isn't suitable for converting into a Sudoku
	 * @throws IOException If the file is missing or corrupt
	 */
	public static Sudoku readSudoku(String fileName) throws IllegalArgumentException, IOException {
		int[][] array = new int[9][9];
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		int i = 0;
		while ((line = in.readLine()) != null && i < 9) {
			if (line.length() < 9)
				throw new IllegalArgumentException("File contains too many or not enough numbers");
			else
				for (int j = 0; j < 9; j++) {
					int charIntVal = Character.getNumericValue(line.charAt(j));
					if (line.charAt(j) == ' ')
						array[i][j] = 0;
					else if (charIntVal <= 9 && charIntVal >= 0)
						array[i][j] = charIntVal;
					else
						throw new IllegalArgumentException("File contains unsuitable characters");
				}
			i++;
		}
		in.close();
		return new Sudoku(array);
	}
}