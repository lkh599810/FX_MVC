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

	private static MainController instance; // 해당 클래스에 객체 만들기 
 	
	public MainController() {
		instance = this;
	}
	
	public static MainController getinstance() {
		return instance;
	}
	

	private String loginid; // 로그인된 아이디를 저장할 변수 선언 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 컨트롤에서 아이디 가져오기 
		loginid = LoginController.getinstance().getid() ;
		// 아이디 표시 
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
    	
    	// 로그아웃 확인 메세지
    	Alert alert = new Alert( AlertType.CONFIRMATION );
    	alert.setContentText(" 정말 로그아웃 하시겠습니까? ");
    	alert.setHeaderText("로그아웃");
    	
    	// 선택한 버튼 객체 가져오기 => Optional 클래스 버튼타입 저장 
    	Optional<ButtonType> result =   alert.showAndWait(); 
    	
    	if( result.get() == ButtonType.OK ) { // 확인 버튼을 눌렀을때 
    	  	// 현재 스테이지 숨기기 
        	btnhome.getScene().getWindow().hide();
        	// 로그인 스테이지 열기 
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
    	}else { // 그외 버튼 
    		return; // 메소드 종료 
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
    
    
    // 가운데 레이아웃변경 
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
