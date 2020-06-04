package game;
/**
 * This class creates a Sudoku object, which consists primarily of a double integer
 * array that it takes as a parameter. The object includes a method to check that
 * it is filled, and a toString() method to print out the Sudoku object in text format.
 * @author Lewis Drew
 * @version 04/06/20
 */
public class Sudoku {

	private int[][] array;

	/**
	 * Constructor method for the Sudoku object.
	 * @param array The double integer array to be converted into a Sudoku object.
	 */
	public Sudoku(int[][] array) {
		this.array = array;
	}

	/**
	 * Getter method to return the Sudoku's double integer array.
	 * @return The Sudoku's double integer array.
	 */
	public int[][] getArray() {
		return array;
	}

	/**
	 * Setter method to set the Sudoku's double integer array.
	 * @param array The new array for the Sudoku object.
	 */
	public void setArray(int[][] array) {
		this.array = array;
	}

	/**
	 * Method to check whether the array taken by the object is correctly filled.
	 * @return True if the array is filled, false if it is not.
	 */
	public boolean isFilled() {
		if (array.length != 9)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].length != 9)
				return false;
			for (int j = 0; j < array[i].length; j++)
				if (array[i][j] < 1 || array[i][j] > 9)
					return false;
		}
		return true;
	}

	/**
	 * ToString method to print out a text version of the Sudoku grid for this object.
	 */
	public String toString() {
		System.out.println("++===+===+===++===+===+===++===+===+===++");
		for (int i = 0; i < array.length; i++) {
			System.out.print("||");
			for (int j = 0; j < array[i].length; j++) {
				if (j == 3 || j == 6) {
					System.out.print("|");
				}
				if (array[i][j] == 0)
					System.out.print("   |");
				else
					System.out.print(" " + array[i][j] + " |");
			}
			System.out.print("|\n");
			if (i == 2 || i == 5)
				System.out.println("++===+===+===++===+===+===++===+===+===++");
			else if (i != 8)
				System.out.println("++---+---+---++---+---+---++---+---+---++");
		}
		return "++===+===+===++===+===+===++===+===+===++";
	}
}