package Controller;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	
	@FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btnsigup;

    @FXML 
    private Label lblfindid;

    @FXML
    private Label lblfindpassword;

    @FXML
    private ImageView loading;
	
	//로그인 성공시 main컨트롤러에게 로그인성공한 id 넘기기
	private static LoginController instance;
	
	public LoginController() {
		instance=this;
	}
	
	public static LoginController getinstance() {
		return instance;
	}
	
	public String getid() {//다른곳에서 쓸 로그인된 아이디
		return txtid.getText();
	}
	
	  @FXML
	    void findid(MouseEvent event) {
	    	
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("/FXML/findid.fxml"));
				
				Scene scene = new Scene( parent );
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Nike Find ID");
				stage.show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	  
	  @FXML
	    void findpassword(MouseEvent event) {
	    	
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("/FXML/findpassword.fxml"));
				
				Scene scene = new Scene( parent );
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Nike Find Passowrd");
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	  
	  
	  
	  @FXML
	  void login(ActionEvent event) {
	    	loading.setVisible(true);
	    		// 텍스트상자.getText() : 텍스트에 입력된 데이터 호출
	    	
	    	MemberDao memberDao=new MemberDao();
	    	
	    	String logid=txtid.getText();
	    	
	    	String logpw=txtpassword.getText();
	    	
	    	int result=memberDao.login(logid, logpw);
	    	
	    	if(result==1) {
	    		
	    		btnlogin.getScene().getWindow().hide();
				
				try {
					Stage stage = new Stage();
					Parent parent = FXMLLoader.load(getClass().getResource("/FXML/main.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
				}
				catch (Exception e) {
					// TODO: handle exception
				}	
	    		
	    		
	    	}else {
	    		Alert alert=new Alert(AlertType.CONFIRMATION);
	    		alert.setContentText("로그인 실패");
	    		alert.showAndWait();
	    		
	    		return;
	    	}
	    	
	  	}
	
	  @FXML
	    void signup(ActionEvent event) {
	    	try {
				Parent parent = FXMLLoader.load( getClass().getResource("/FXML/signup.fxml"));
				
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Nike signup");
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loading.setVisible(false);
	}
	
}
