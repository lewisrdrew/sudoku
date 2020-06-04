package gui;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.paint.Color;
/**
 * This class creates the GUI and provides the functionality for the Sudoku.
 * @author Lewis Drew
 * @version 04/06/20
 */
public class SudokuGUI extends Application {

	final static int PLACEMENT = 40;
	private static int thick = 4;
	private static int thin = (thick / 2);
	private static int size = 40;
	private static int[][] sudArray;
	private static TextField[][] nums = new TextField[9][9];
	public static TextField announcement;
	public static String filename;
	private static game.Sudoku sud;
	public static final int X_SIZE = 440;
	private static Line line;
	private static Rectangle[][] boxes;
	public static Group root;

	/**
	 * Method to create the Group object for the SuodkuGUI.
	 * @return The group object for the SudokuGUI.
	 */
	public static Group createRoot() {
		root = new Group();
		try {
			sud = game.SudokuRead.readSudoku(filename);
			sudArray = sud.getArray();
			sudokuToNums(sudArray);
		} catch (IOException e) {
			System.out.println("File not found");
		} catch (IllegalArgumentException e) {
			System.out.println("File not suitable");
		}
		boxes = new Rectangle[9][9];
		line = new Line();
		createSudoku();
		createButtons();
		return root;
	}

	/**
	 * Method to convert a double array of integers into a double array of TextFields.
	 * @param sudArray The double integer array to be converted.
	 * @return The double array of TextFields.
	 */
	public static TextField[][] sudokuToNums(int[][] sudArray) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudArray[i][j] == 0)
					nums[i][j] = new TextField(null);
				else {
					nums[i][j] = new TextField(String.valueOf(sudArray[i][j]));
					nums[i][j].setEditable(false);
				}
				nums[i][j].setPrefWidth(size);
				nums[i][j].setPrefHeight(size);
				nums[i][j].setAlignment(Pos.CENTER);
				nums[i][j].setFocusTraversable(false);
			}
		}
		return nums;
	}

	/**
	 * Method to create the Sudoku grid and place the numbers from the nums array.
	 */
	public static void createSudoku() {
		// for rows
		for (int i = 0; i < 9; i++) {
			// for columns
			for (int j = 0; j < 9; j++) {
				// for thick vertical lines
				if (j % 3 == 0) {
					if (j == 0)
						line = new Line(PLACEMENT + (j * size), PLACEMENT + (i * size), PLACEMENT + (j * size), PLACEMENT + (i * 42) + size);
					else
						line = new Line(PLACEMENT + (j * size), PLACEMENT + (i * size), PLACEMENT + (j * size), PLACEMENT + (i * 42) + size);
					line.setStrokeWidth(thick);
					boxes[i][j] = new Rectangle(PLACEMENT + (j * size), PLACEMENT + (i * 42), size, size);
					boxes[i][j].setFill(Color.LIGHTGREY);
					if (!nums[i][j].isEditable())
						boxes[i][j].setFill(Color.DARKGREY);
					root.getChildren().add(boxes[i][j]);
					root.getChildren().add(line);
				} else {
					line = new Line(PLACEMENT + (j * size), PLACEMENT + (i * size), PLACEMENT + (j * size), PLACEMENT + (i * 42) + size);
					line.setStrokeWidth(thin);
					boxes[i][j] = new Rectangle(PLACEMENT + (j * size), PLACEMENT + (i * 42), size, size);
					boxes[i][j].setFill(Color.LIGHTGREY);
					if (!nums[i][j].isEditable())
						boxes[i][j].setFill(Color.DARKGREY);
					root.getChildren().add(boxes[i][j]);
					root.getChildren().add(line);
				}
				nums[i][j].setLayoutX(boxes[i][j].getX());
				nums[i][j].setLayoutY(boxes[i][j].getY());
				nums[i][j].setBackground(null);
				root.getChildren().add(nums[i][j]);
			}
			// thin lines
			if (i % 3 == 0 && i != 0) {
				line = new Line(PLACEMENT, boxes[i][0].getY(), boxes[i][8].getX() + size, boxes[i][8].getY());
				line.setStrokeWidth(thick);
				root.getChildren().add(line);
			} else {
				line = new Line(PLACEMENT, boxes[i][0].getY() - 1, boxes[i][8].getX() + size, boxes[i][8].getY() - 1);
				line.setStrokeWidth(thin);
				root.getChildren().add(line);
			}
		}

		// top line
		line = new Line(PLACEMENT, PLACEMENT, boxes[0][8].getX() + size, PLACEMENT);
		line.setStrokeWidth(thick);
		root.getChildren().add(line);
		// bottom line
		line = new Line(PLACEMENT, boxes[8][0].getY() + size, boxes[0][8].getX() + size, boxes[8][8].getY() + size);
		line.setStrokeWidth(thick);
		root.getChildren().add(line);
		// right line
		line = new Line(boxes[0][8].getX() + size, boxes[0][8].getY(), boxes[8][8].getX() + size, boxes[8][8].getY() + size);
		line.setStrokeWidth(thick);
		root.getChildren().add(line);

		// text at bottom
		announcement = new TextField("Fill out the blank boxes and click check to continue");
		announcement.setBackground(null);
		announcement.setEditable(false);
		announcement.setPrefWidth(X_SIZE);
		announcement.setLayoutY(boxes[8][0].getY() + (size + thick));
		announcement.setAlignment(Pos.CENTER);
		announcement.setFocusTraversable(false);
		announcement.setFont(Font.font ("Helvetica Neue", 15));
		root.getChildren().add(announcement);
	}

	/**
	 * Method to create the buttons and their handlers for the SudokuGUI.
	 */
	public static void createButtons() {
		// buttons
		Button check = new Button("Check");
		Button clear = new Button("Clear");
		Button exit = new Button("Exit");
		check.setLayoutX(PLACEMENT);
		check.setLayoutY(boxes[8][0].getY() + size * 2);
		clear.setLayoutX(boxes[0][4].getX());
		clear.setLayoutY(boxes[8][0].getY() + size * 2);
		exit.setLayoutX(boxes[0][8].getX());
		exit.setLayoutY(boxes[8][8].getY() + size * 2);
		check.setFont(Font.font ("Helvetica Neue", 12));
		clear.setFont(Font.font ("Helvetica Neue", 12));
		exit.setFont(Font.font ("Helvetica Neue", 12));
		root.getChildren().addAll(check, clear, exit);

		// exit button clicked
		final EventHandler<MouseEvent> eventExit = e -> {
			System.out.println("Sudoku terminated");
			System.exit(1);
		};
		exit.addEventFilter(MouseEvent.MOUSE_CLICKED, eventExit);

		// clear button clicked
		final EventHandler<MouseEvent> eventClear = e -> {
			announcement.setText("Sudoku reset");
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++) {
					if (nums[i][j].isEditable() || boxes[i][j].getFill() == Color.GREEN) {
						nums[i][j].setText(String.valueOf(sudArray[i][j]));
						nums[i][j].setText(null);
						nums[i][j].setEditable(true);
						boxes[i][j].setFill(Color.LIGHTGREY);
					}
				}
		};
		clear.addEventFilter(MouseEvent.MOUSE_CLICKED, eventClear);

		// check button clicked
		final EventHandler<MouseEvent> eventCheck = e -> {
			check();
		};
		check.addEventFilter(MouseEvent.MOUSE_CLICKED, eventCheck);
	}

	/**
	 * Method to check the numbers the player has entered to see if they are correct.
	 */
	public static void check() {
		int valid = 0;
		int[][] tempArray = new int[9][9];

		// creates a temporary double array of integers to make a new Sudoku with in order to check correctness
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				tempArray[i][j] = sudArray[i][j];
				if (nums[i][j].isEditable() && nums[i][j].getText() != null && nums[i][j].getText().length() > 0) 
					tempArray[i][j] = Character.getNumericValue(nums[i][j].getText().charAt(0));
			}
		sud = new game.Sudoku(tempArray);
		boolean[][] checkArray = game.SudokuCheck.check(sud);

		// colours all boxes, checks for acceptable inputs
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (nums[i][j].getText() == null || nums[i][j].getText().length() == 0) {
					boxes[i][j].setFill(Color.LIGHTGREY);
					valid = 1;
				} else if (nums[i][j].getText().length() > 1 ||
						Character.getNumericValue(nums[i][j].getText().charAt(0)) > 9 ||
						Character.getNumericValue(nums[i][j].getText().charAt(0)) < 1) {
					boxes[i][j].setFill(Color.TOMATO);
					valid = 1;
					announcement.setText("Numbers entered must be between 1 and 9");
				} else if (nums[i][j].isEditable() && nums[i][j].getText() != null)
					boxes[i][j].setFill(Color.LIGHTSEAGREEN); 
				else
					boxes[i][j].setFill(Color.DARKGREY);
			}
		}

		// colours incorrect boxes
		for (int i = 0; i < 9; i++) {
			if (checkArray[0][i] == false) {
				for (int j = 0; j < 9; j++)
					if (nums[i][j].isEditable())
						boxes[i][j].setFill(Color.TOMATO);
				valid = -1;
			}
			if (checkArray[1][i] == false) {
				for (int j = 0; j < 9; j++)
					if (nums[j][i].isEditable())
						boxes[j][i].setFill(Color.TOMATO);
				valid = -1;
			}
		}

		// colours incorrect squares
		for (int i = 0; i < 9; i++)
			if (checkArray[2][i] == false) {
				if (i < 3) {
					for (int j = 0; j < 3; j++)
						for (int k = i * 3; k < (i * 3) + 3; k++)
							if (nums[j][k].isEditable())
								boxes[j][k].setFill(Color.TOMATO);
				}
				else if (i < 6) {
					for (int j = 3; j < 6; j++)
						for (int k = i % 3 * 3; k < i % 3 * 3 + 3; k++)
							if (nums[j][k].isEditable())
								boxes[j][k].setFill(Color.TOMATO);
				}
				else
					for (int j = 6; j < 9; j++)
						for (int k = i % 3 * 3; k < i % 3 * 3 + 3; k++)
							if (nums[j][k].isEditable())
								boxes[j][k].setFill(Color.TOMATO);
				valid = -1;
			}
		if (valid == 0)
			announcement.setText("Congratulations, you have completed the Sudoku!");
		else if (valid == 1)
			announcement.setText("Sudoku incomplete");
	}

	/**
	 * Method to create and show the SudokuGUI scene.
	 */
	@Override
	public void start(Stage stage) {
		createRoot();
		Scene scene = new Scene(root, boxes[0][8].getX() + size + PLACEMENT, boxes[8][8].getY() + (size * 2) + PLACEMENT);
		stage.setTitle("Sudoku");
		stage.setScene(scene);
		stage.show();
	}
}