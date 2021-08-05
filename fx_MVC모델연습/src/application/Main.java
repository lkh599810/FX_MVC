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
	
		// FXML ù ����� ���� �������� 
//		memberlist = FileUtil.readfile( 
//				new File("C:/Users/User/git/web_0518/javafx_2/src/File/", "memberlist.txt"));

		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
		
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setResizable(false); // stage ũ�� ���� 
		stage.setTitle("Nike"); // stage ��� �̸� 

		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
