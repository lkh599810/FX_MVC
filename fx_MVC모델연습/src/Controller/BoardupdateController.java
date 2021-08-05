package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.BoardDao;
import domain.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardupdateController implements Initializable{
	
	
	@FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;
    
    private static Board board;

    @FXML
    void cancel(ActionEvent event) {
    	MainController.getinstance().loadpage("board");
    }
	
    @FXML
    void update(ActionEvent event) {
    	
    	
    	
    	BoardDao dao = new BoardDao();
    	
    	int result=dao.updateboard(board, txttitle.getText(), txtcontents.getText());
    	
    	if(result==1) {
    		MainController.getinstance().loadpage("board");
    		
    	}else {
    		Alert alert=new Alert(AlertType.CONFIRMATION);
    		alert.setContentText(" �������� ");
    		alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 	
    		MainController.getinstance().loadpage("board");
    	}
    	
    	
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    	board =  BoardController.getinstance().getboard(); //�޾ƿ���
    	
    	txttitle.setText( board.getBtitle() );
		txtcontents.setText( board.getBcontents() ); //initialize�� ��Ȯ�� ������ �𸣰���
    	
    	
    }
    
	
}
