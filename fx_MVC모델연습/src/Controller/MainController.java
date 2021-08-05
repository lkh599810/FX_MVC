package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private static MainController instance; // �ش� Ŭ������ ��ü ����� 
 	
	public MainController() {
		instance = this;
	}
	
	public static MainController getinstance() {
		return instance;
	}
	

	private String loginid; // �α��ε� ���̵� ������ ���� ���� 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ��Ʈ�ѿ��� ���̵� �������� 
		loginid = LoginController.getinstance().getid() ;
		// ���̵� ǥ�� 
		lbllogin.setText ( loginid );
	}
	
	@FXML
    private BorderPane borderpane;

    @FXML
    private AnchorPane cp;

    @FXML
    private AnchorPane lp;

    @FXML
    private Button btnhome;

    @FXML
    private Button btnpadd;

    @FXML
    private Button btnplist;

    @FXML
    private Button btnboard;

    @FXML
    private Button btnpsum;

    @FXML
    private Button btninfo;

    @FXML
    private Label lbllogin;

    @FXML
    private Button btnlogout;

    @FXML
    void board(ActionEvent event) {
    		loadpage("board");
    }

    @FXML
    void home(ActionEvent event) {
    		borderpane.setCenter(cp);
    }

    @FXML
    void info(ActionEvent event) {
    	loadpage("info");
    }

    @FXML
    void logout(ActionEvent event) {
    	
    	// �α׾ƿ� Ȯ�� �޼���
    	Alert alert = new Alert( AlertType.CONFIRMATION );
    	alert.setContentText(" ���� �α׾ƿ� �Ͻðڽ��ϱ�? ");
    	alert.setHeaderText("�α׾ƿ�");
    	
    	// ������ ��ư ��ü �������� => Optional Ŭ���� ��ưŸ�� ���� 
    	Optional<ButtonType> result =   alert.showAndWait(); 
    	
    	if( result.get() == ButtonType.OK ) { // Ȯ�� ��ư�� �������� 
    	  	// ���� �������� ����� 
        	btnhome.getScene().getWindow().hide();
        	// �α��� �������� ���� 
        	try {
	        	Stage stage = new Stage();
	        	Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
	        	Scene scene = new Scene( parent);
	        	stage.setScene(scene);
	        	stage.show();
        	}
        	catch (Exception e) {
    			// TODO: handle exception
    		}        	
    	}else { // �׿� ��ư 
    		return; // �޼ҵ� ���� 
    	}

    }

    @FXML
    void padd(ActionEvent event) {
    	loadpage("padd");
    }

    @FXML
    void plist(ActionEvent event) {
    	loadpage("plist");
    }

    @FXML
    void psum(ActionEvent event) {
    	loadpage("psum");
    }
    
    
    // ��� ���̾ƿ����� 
    public void loadpage( String page ) {
    	try {
    		Parent parent = FXMLLoader.load(getClass().getResource( "/FXML/"+page+".fxml"));
    		borderpane.setCenter(parent);
    	} 
    	catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	
	

}
