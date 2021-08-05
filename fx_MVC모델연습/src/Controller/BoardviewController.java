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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardviewController implements Initializable{
	
	 	@FXML
	    private Button btndelete;

	    @FXML
	    private TextField txttitle;

	    @FXML
	    private TextArea txtcontents;

	    @FXML
	    private Button btnboard;

	    @FXML
	    private Label lblcount;

	    @FXML
	    private Label lbldate;

	    @FXML
	    private Label lblwriter;

	    @FXML
	    private Button btnupdate;

	   
	    private static Board board;
	    
	    @FXML
	    void board(ActionEvent event) {
	    	MainController.getinstance().loadpage("board");
	    }
	
	    
	    @FXML
	    void delete(ActionEvent event) {
	    	BoardDao dao=new BoardDao();
	    	int r=dao.delboard(board);
	    	
	    	if(r==1) {
	    		MainController.getinstance().loadpage("board");
	    		
	    	}else {
	    		Alert alert = new Alert( AlertType.INFORMATION);
	    		alert.setContentText(" [ �������� ] �����ڿ��� ���� : 000-0000-0000 ");
	    		alert.setHeaderText(" �Խù� ���� ����");
	    		alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 	
	    		MainController.getinstance().loadpage("board");
	    	}
	    }
	
	    @FXML
	    void update(ActionEvent event) {
	    	MainController.getinstance().loadpage("boardupdate");
	    }
	
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		board= BoardController.getinstance().getboard();
		
		txttitle.setText(board.getBtitle());
		txtcontents.setText( board.getBcontents() );
		lblcount.setText("��ȸ�� : " + board.getBcount() );
		lblwriter.setText("�ۼ��� : " + board.getBwriter() );
		lbldate.setText("�ۼ��� : " + board.getBdate() );
		
		String logid=LoginController.getinstance().getid();
		
		if(board.getBwriter().equals(logid)) {
			
			btndelete.setVisible(true);
			btnupdate.setVisible(true);
			
		}else {
			btndelete.setVisible(false);
			btndelete.setVisible(false);
		}
		
	}

}
