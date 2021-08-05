package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.MemberDao;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupController implements Initializable{

		@FXML
	    private TextField txtid;

	    @FXML
	    private PasswordField txtpassword;

	    @FXML
	    private Button btnsignup;

	    @FXML
	    private Button btnlogin;

	    @FXML
	    private PasswordField txtpassword2;

	    @FXML
	    private TextField txtname;

	    @FXML
	    private TextField txtemail;
	    
	    @FXML
	    private Label lblerror;

	    @FXML
	    void login(ActionEvent event) {
	    }
	
	    
	    @FXML
	    void signup(ActionEvent event) {
	    	
	    	String id=txtid.getText();
	    	String password = txtpassword.getText();
	    	String password2 = txtpassword2.getText();
	    	String name = txtname.getText();
	    	String email = txtemail.getText();
	    	
	    	MemberDao memberDao=new MemberDao();
	    	
	    	ArrayList<Member> members=memberDao.allmember();
	    	
	    
	    	
	    	
	    	for(Member temp: members) {
	    		
	    		if(temp.getId().equals(id)) {
	    			lblerror.setText("가입 실패: 동일아이디 존재");
	    			return;
	    		}
	    		
	    	}
	    	
	    	
	    	if(! password.equals(password2)) {
	    		lblerror.setText("가입실패 : 패스워드가 서로 다릅니다 ");
	    		// ! : 부정 [ 반대 ]
				return;
	    	}
	    	
	    	if( email.indexOf("@") == -1 ) {
	    		lblerror.setText("가입실패 : 이메일 형식이 아닙니다 ");
				return;
	    	}
	    	
	    	Member temp=new Member(id, name, password2, email);
	    	
	    	int result=memberDao.setmember(temp);
	    	
	    	
	    	if(result==1) {
	    		
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setContentText("가입완료");
	    		alert.showAndWait();
	    		
	    		btnsignup.getScene().getWindow().hide();
	    	}else {
	    		lblerror.setText("회원가입 실패 : [데이터베이스 오류] 관리자에게 문의 ");
	    		// 메세지 창 띄우기 
	    			Alert alert = new Alert( AlertType.INFORMATION);
	    			alert.setContentText(" [데이터베이스 오류] 관리자에게 문의 ");
	 				alert.setHeaderText("회원가입 실패");
	    			alert.showAndWait(); // 확인 버튼 누르기전까지 대기상태 
	    	}
	    	System.out.println(result);
	    	System.out.println(temp.getId());
	    	System.out.println(temp.getPassword());
	    	System.out.println(temp.getName());
	    	System.out.println(temp.getEmail());
	    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	
	    	
	    }
}
