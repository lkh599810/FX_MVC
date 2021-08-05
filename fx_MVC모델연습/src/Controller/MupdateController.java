package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.MemberDao;
import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MupdateController implements Initializable{

		@FXML
	    private Button btnupdate;

	    @FXML
	    private Button btncancel;

	    @FXML
	    private TextField txtname;

	    @FXML
	    private TextField txtemail;

	    @FXML
	    void cancel(ActionEvent event) {
	    	
	    }
	    
	    @FXML
	    void update(ActionEvent event) {
	    	
	    	Member member=new Member();
	    	member.setId(LoginController.getinstance().getid());
	    	member.setName(txtname.getText());
	    	member.setEmail(txtname.getText());
	    	
	    	
	    	MemberDao dao=new MemberDao();
	    	
	    	int r=dao.updatemember(member);
	    	
	    	if(r==1) {
	    		MainController.getinstance().loadpage("info");
	    	}else {
	    		
	    	}
	    	
	    	
	    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    // TODO Auto-generated method stub
	    
	    String	loginid=LoginController.getinstance().getid();
	    			
	    MemberDao dao=new MemberDao();
	    
	    Member member=dao.getmember(loginid);
	    txtname.setText(member.getName());
	    txtemail.setText(member.getEmail());
	    	
	    }
	
}
