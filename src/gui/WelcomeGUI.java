package gui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This class creates the GUI for the Welcome page, which allows users to choose a difficulty or upload
 * their own Sudoku grid.
 * @author Lewis Drew
 * @version 04/06/20
 */
public class WelcomeGUI extends Application {
	private final int X_SIZE = 440;
	private final int Y_SIZE = 496;
	private final int CENTRE = X_SIZE / 2;
	private final int IMAGE_SIZE = 150;
	private final int OFFSET = IMAGE_SIZE / 3;
	private Group root = new Group();
	private int rootNo = 0;
	private FileChooser fileChooser = new FileChooser();
	private Text squareText;

	/**
	 * Method to create the Welcome scene, constructing the title and images.
	 * @throws FileNotFoundException Thrown if the images aren't found.
	 */
	public void createWelcome() throws FileNotFoundException {
		// title
		TextField title = new TextField("Welcome to Sudoku! Choose a difficulty to continue");
		title.setPrefWidth(X_SIZE);
		title.setBackground(null);
		title.setEditable(false);
		title.setLayoutY(OFFSET / 2);
		title.setAlignment(Pos.CENTER);
		title.setFocusTraversable(false);
		title.setFont(Font.font ("Helvetica Neue", 15));
		root.getChildren().add(title);

		// images
		FileInputStream easyIn = new FileInputStream("src/gui/easy.png");
		FileInputStream mediumIn = new FileInputStream("src/gui/medium.png");
		FileInputStream difficultIn = new FileInputStream("src/gui/difficult.png");
		Image easyImage = new Image(easyIn);
		Image mediumImage = new Image(mediumIn);
		Image difficultImage = new Image(difficultIn);
		ImageView easyView = new ImageView(easyImage);
		ImageView mediumView = new ImageView(mediumImage);
		ImageView difficultView = new ImageView(difficultImage);
		Rectangle square = new Rectangle();
		square.setHeight(IMAGE_SIZE);
		square.setWidth(IMAGE_SIZE);
		square.setFill(Color.LIGHTGREY);
		square.setStrokeType(StrokeType.INSIDE);
		square.setStroke(Color.BLACK);
		squareText = new Text("Upload your own Sudoku grid from a .txt file");
		squareText.maxHeight(IMAGE_SIZE);
		squareText.maxWidth(IMAGE_SIZE);
		squareText.setWrappingWidth(IMAGE_SIZE - 10);
		squareText.setTextAlignment(TextAlignment.CENTER);
		squareText.setFont(Font.font ("Helvetica Neue", 15));
		StackPane custom = new StackPane();
		custom.getChildren().addAll(square, squareText);
		easyView.setFitHeight(IMAGE_SIZE);
		easyView.setFitWidth(IMAGE_SIZE);
		mediumView.setFitHeight(IMAGE_SIZE);
		mediumView.setFitWidth(IMAGE_SIZE);
		difficultView.setFitHeight(IMAGE_SIZE);
		difficultView.setFitWidth(IMAGE_SIZE);
		GridPane grid = new GridPane();
		grid.add(easyView, 0, 0);
		grid.add(mediumView, 0, 1);
		grid.add(difficultView, 1, 0);
		grid.add(custom, 1, 1);
		grid.setHgap(OFFSET);
		grid.setVgap(OFFSET);
		grid.setPrefWidth(X_SIZE);
		grid.setPrefHeight(Y_SIZE);
		grid.setAlignment(Pos.CENTER);
		root.getChildren().add(grid);
	}

	/**
	 * Method to create the buttons and their event handlers for the Welcome screen.
	 * @param oldStage The stage created for the Welcome GUI.
	 */
	public void createButtons(Stage oldStage) {
		//buttons
		Button easyButton = new Button("Easy");
		Button mediumButton = new Button("Medium");
		Button difficultButton = new Button("Difficult");
		Button otherButton = new Button("Custom");
		Button exitButton = new Button("Exit");
		easyButton.setFont(Font.font ("Helvetica Neue", 13));
		mediumButton.setFont(Font.font ("Helvetica Neue", 13));
		difficultButton.setFont(Font.font ("Helvetica Neue", 13));
		otherButton.setFont(Font.font ("Helvetica Neue", 13));
		exitButton.setFont(Font.font ("Helvetica Neue", 13));
		GridPane buttonGrid = new GridPane();
		buttonGrid.add(easyButton, 0, 0);
		buttonGrid.add(mediumButton, 1, 0);
		buttonGrid.add(difficultButton, 0, 1);
		buttonGrid.add(otherButton, 1, 1);
		buttonGrid.setHgap(OFFSET);
		buttonGrid.setVgap(IMAGE_SIZE + OFFSET / 2);
		buttonGrid.setPrefWidth(X_SIZE);
		buttonGrid.setPrefHeight(Y_SIZE + IMAGE_SIZE + OFFSET);
		buttonGrid.setAlignment(Pos.CENTER);
		ColumnConstraints buttonCols = new ColumnConstraints();
		buttonCols.setPrefWidth(IMAGE_SIZE);
		buttonCols.setHalignment(HPos.CENTER);
		buttonGrid.getColumnConstraints().addAll(buttonCols, buttonCols);
		exitButton.setPrefWidth(50);
		exitButton.setLayoutX(CENTRE - 25);
		exitButton.setLayoutY(Y_SIZE - 30);
		root.getChildren().addAll(buttonGrid, exitButton);

		// easy button clicked
		final EventHandler<MouseEvent> eventEasy = e -> {
			SudokuGUI.filename = "easy.txt";
			SudokuGUI.createRoot();
			root = SudokuGUI.root;
			rootNo = 1;
			Stage stage = new Stage();
			try {
				start(stage);
			} catch (FileNotFoundException e1) {
			}
		};
		easyButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventEasy);

		// medium button clicked
		final EventHandler<MouseEvent> eventMedium = e -> {
			SudokuGUI.filename = "medium.txt";
			SudokuGUI.createRoot();
			root = SudokuGUI.root;
			rootNo = 1;
			Stage stage = new Stage();
			try {
				start(stage);
			} catch (FileNotFoundException e1) {
			}
		};
		mediumButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventMedium);

		// difficult button clicked
		final EventHandler<MouseEvent> eventDifficult = e -> {
			SudokuGUI.filename = "difficult.txt";
			SudokuGUI.createRoot();
			root = SudokuGUI.root;
			rootNo = 1;
			Stage stage = new Stage();
			try {
				start(stage);
			} catch (FileNotFoundException e1) {
			}
		};
		difficultButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventDifficult);

		// custom button clicked
		final EventHandler<MouseEvent> eventOther = e -> {
			fileChooserConfiguration(fileChooser);
			File file = fileChooser.showOpenDialog(oldStage);
			try {
				game.SudokuRead.readSudoku(file.getName());
				SudokuGUI.filename = file.getName();
				SudokuGUI.createRoot();
				root = SudokuGUI.root;
				rootNo = 1;
				Stage stage = new Stage();
				start(stage);
			} catch (IllegalArgumentException e1) {
				squareText.setText("File is invalid: must be a .txt file containing a grid of valid numbers");
			} catch (IOException e1) {
				squareText.setText("File is invalid: must be a .txt file containing a grid of valid numbers");
			}
		};
		otherButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventOther);

		// exit button clicked
		final EventHandler<MouseEvent> eventExit = e -> {
			System.out.println("Sudoku terminated");
			System.exit(1);
		};
		exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventExit);
	}

	/**
	 * Method to configure the FileChooser so that it only accepts .txt files.
	 * @param fileChooser The FileChooser to be configured.
	 */
	public void fileChooserConfiguration(FileChooser fileChooser) {
		fileChooser.setTitle("Custom");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
	}

	/**
	 * Method to set the JavaFX stage and scenes. Also calls the methods that create the
	 * Welcome scene.
	 */
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		if (rootNo == 0) {
			createWelcome();
			createButtons(stage);
		}

		Scene scene = new Scene(root, X_SIZE, Y_SIZE);
		stage.setTitle("Sudoku");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Main method to launch the window.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}