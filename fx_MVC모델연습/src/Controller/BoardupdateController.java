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
    		alert.setContentText(" 수정실패 ");
    		alert.showAndWait(); // 확인 버튼 누르기전까지 대기상태 	
    		MainController.getinstance().loadpage("board");
    	}
    	
    	
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    	board =  BoardController.getinstance().getboard(); //받아오기
    	
    	txttitle.setText( board.getBtitle() );
		txtcontents.setText( board.getBcontents() ); //initialize의 정확한 역할을 모르겟음
    	
    	
    }
    
	
}
