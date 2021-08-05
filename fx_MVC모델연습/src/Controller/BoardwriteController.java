package Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class BoardwriteController implements Initializable{

	 	@FXML
	    private Button btnwrite;

	    @FXML
	    private TextField txttitle;

	    @FXML
	    private TextArea txtcontents;

	    @FXML
	    private Button btncancel;

	    
	    @FXML
	    void cancel(ActionEvent event) {
	    	MainController.getinstance().loadpage("board");
	    }
	    @FXML
	    void write(ActionEvent event) {
	    	
	    	String btitle=txttitle.getText();
	    	String bcontents=txtcontents.getText();
	    	String bwriter=LoginController.getinstance().getid(); //�α��ε� ���̵�
	    	Date date= new Date();
	    	
	    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	    	String bdate=format.format(date);
	    	
	    	Board board=new Board(btitle, bcontents, bwriter, bdate, 0);
	    	
	    	BoardDao boardDao=new BoardDao();
	    	
	    	int result=boardDao.boardwrite(board);
	    	
	    	if (result==1) {
	    		MainController.getinstance().loadpage("board");
	    	}else {  // ����
	    		// �޼��� â ���� 
	    		Alert alert = new Alert( AlertType.INFORMATION);
	    		alert.setContentText(" ��� ���� : DB���� ");
	    		alert.setHeaderText(" �Խù� ��� ����");
	    		alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 	
	    		MainController.getinstance().loadpage("board");
	    	}
	    	
	    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
