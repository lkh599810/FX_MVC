package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
 

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
	
		// FXML 첫 실행시 파일 가져오기 
//		memberlist = FileUtil.readfile( 
//				new File("C:/Users/User/git/web_0518/javafx_2/src/File/", "memberlist.txt"));

		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
		
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setResizable(false); // stage 크기 고정 
		stage.setTitle("Nike"); // stage 상단 이름 

		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
