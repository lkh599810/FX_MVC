package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.MemberDao;
import domain.Member;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class InfoController implements Initializable{
	
	   @FXML
	    private Button btndelete;

	    @FXML
	    private Button btnupdate;

	    @FXML
	    private Label txtid;

	    @FXML
	    private Label txtname;

	    @FXML
	    private Label txtemail;
	    
	    @FXML
	    void delete(ActionEvent event){
	    	
	    	String logid=LoginController.getinstance().getid();
	    	
	    	
	    	
	    	Alert alert = new Alert( AlertType.CONFIRMATION);
	    	alert.setContentText(" Á¤¸» Å»ÅðÇÏ½Ã°Ú½À´Ï±î? ");
	    	alert.setHeaderText("È¸¿øÅ»Åð");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
	    	if(result.get()==ButtonType.OK) {
	    		
	    		MemberDao dao= new MemberDao();
	    		int result2=dao.deletemember(logid);
	    		
	    		if(result2==1) {
	    			Alert alert2 = new Alert( AlertType.CONFIRMATION);
	    	    	alert2.setContentText(" »èÁ¦¿Ï·á ");
	    	    	Platform.exit();
	    		}
	    		
	    	}else {
    			return;
    		}
	    	
	    }
	    
	    @FXML
	    void update(ActionEvent event) {
	    	MainController.getinstance().loadpage("mupdate");
	    }
	    
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	// TODO Auto-generated method stub
	    	
	    	String id=LoginController.getinstance().getid();
	    	
	    	MemberDao dao= new MemberDao();
	    	
	    	Member member=dao.getmember(id);
	    	
	    	txtid.setText(member.getId());
	    	txtname.setText(member.getName());
	    	txtemail.setText(member.getEmail());
	    	
	    }


}
