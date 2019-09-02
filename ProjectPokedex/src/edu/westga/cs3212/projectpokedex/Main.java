package edu.westga.cs3212.projectpokedex;

import edu.westga.cs3212.projectpokedex.view.MainPageCodeBehind;
import edu.westga.cs3212.projectpokedex.view.utils.FXMLContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * The entry point to launch the JavaFX program.
 * 
 * @author Kyle Riggs, Cody Graham, Dylan Knox, Joseph Fuller
 */
public class Main extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new FXMLContainer<MainPageCodeBehind>("MainPageGui").getPane();
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			this.setupStage(primaryStage, scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setupStage(Stage primaryStage, Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("Project Pokedex");
		primaryStage.setMaxWidth(650);
		primaryStage.setWidth(650);
		primaryStage.show();
		primaryStage.setOnCloseRequest((winEvent)->{
			System.exit(0);
		});
		//TODO replace when done
		///new Thread(()-> {new MediaPlayer(new Media("https://docs.google.com/uc?id=1OzPWVmnd2MYRjSAwID5egFnqJpiTeQmC")).play();}).start();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
