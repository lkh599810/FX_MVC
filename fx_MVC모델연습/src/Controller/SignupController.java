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
	    			lblerror.setText("���� ����: ���Ͼ��̵� ����");
	    			return;
	    		}
	    		
	    	}
	    	
	    	
	    	if(! password.equals(password2)) {
	    		lblerror.setText("���Խ��� : �н����尡 ���� �ٸ��ϴ� ");
	    		// ! : ���� [ �ݴ� ]
				return;
	    	}
	    	
	    	if( email.indexOf("@") == -1 ) {
	    		lblerror.setText("���Խ��� : �̸��� ������ �ƴմϴ� ");
				return;
	    	}
	    	
	    	Member temp=new Member(id, name, password2, email);
	    	
	    	int result=memberDao.setmember(temp);
	    	
	    	
	    	if(result==1) {
	    		
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setContentText("���ԿϷ�");
	    		alert.showAndWait();
	    		
	    		btnsignup.getScene().getWindow().hide();
	    	}else {
	    		lblerror.setText("ȸ������ ���� : [�����ͺ��̽� ����] �����ڿ��� ���� ");
	    		// �޼��� â ���� 
	    			Alert alert = new Alert( AlertType.INFORMATION);
	    			alert.setContentText(" [�����ͺ��̽� ����] �����ڿ��� ���� ");
	 				alert.setHeaderText("ȸ������ ����");
	    			alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 
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
