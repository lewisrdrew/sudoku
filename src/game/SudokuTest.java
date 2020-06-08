package game;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * Test cases for the Sudoku class.
 * @author Lewis Drew
 * @version 08/06/20
 */
class SudokuTest {
	Sudoku testSud;
	int[][] arrFilled = {{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}};

	int[][] arrUnfilled = {{1,2,3,4,5,6,7}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,7,8,9}};

	int[][] arrEmpty = {{}};

	int[][] arrLong = {{1,2,3,4,5,6,7,8,9, 10}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9}};

	int[][] arrIncorrect = {{1,2,3,4,5,6,15,8,-9}, {1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,27,-6,7,8,9}, {1,2,3,4,5,6,7,8,9},
			{1,2,3,4,5,6,7,8,9}, {1,2,3,4,5,6436,7,8,9}, {1,2,3,4,5,6,7,8,9}};

	/**
	 * Sudoku takes a filled array and returns true.
	 */
	@Test
	void sudTakesFilled() {
		testSud = new Sudoku(arrFilled);
		assertEquals(true, testSud.isFilled());
	}

	/**
	 * Sudoku returns the same array that is given to it.
	 */
	@Test
	void sudReturnsArray() {
		testSud = new Sudoku(arrFilled);
		assertEquals(arrFilled, testSud.getArray());
	}

	/**
	 * Sudoku takes an unfilled array and returns false.
	 */
	@Test
	void sudTakesUnfilled() {
		testSud = new Sudoku(arrUnfilled);
		assertEquals(false, testSud.isFilled());
	}

	/**
	 * Sudoku takes an empty array and returns false.
	 */
	@Test
	void sudTakesEmpty() {
		testSud = new Sudoku(arrEmpty);
		assertEquals(false, testSud.isFilled());
	}

	/**
	 * Sudoku takes an empty array and returns the array.
	 */
	@Test
	void sudTakesEmptyReturnsArray() {
		testSud = new Sudoku(arrEmpty);
		assertEquals(arrEmpty, testSud.getArray());
	}

	/**
	 * Sudoku takes an array that is slightly too long and returns false.
	 */
	@Test
	void sudTakesLong () {
		testSud = new Sudoku(arrLong);
		assertEquals(false, testSud.isFilled());
	}

	/**
	 * Sudoku takes an array including numbers not between 1 and 9 and returns false.
	 */
	@Test
	void sudTakesIncorrect () {
		testSud = new Sudoku(arrIncorrect);
		assertEquals(false, testSud.isFilled());
	}

	/**
	 * Sudoku sets a new array.
	 */
	@Test
	void sudSetsArray () {
		testSud = new Sudoku(arrIncorrect);
		testSud.setArray(arrFilled);
		assertEquals(arrFilled, testSud.getArray());
	}
}