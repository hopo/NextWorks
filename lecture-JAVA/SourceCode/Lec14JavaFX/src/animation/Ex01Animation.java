package animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex01Animation extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("Ex01AnimationRoot.fxml"));
		
		Scene scene =  new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Ex01Animation");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
