package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.BoardDao;
import domain.Board;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BoardController implements Initializable{
	
		@FXML
	    private TableView<Board> tableview;
	    		// tableview : ����Ʈ ���� [ ObservableList ]
	    		//  <  > : ���׸� => ���̺� ���� Ŭ������ 
	    @FXML
	    private Button btnwrite;

	
		private static BoardController instance;
		private static Board board; //�Խù� ��ü ==> �߿�.
		
		public BoardController() {
			instance=this;
		}
		
		public static BoardController getinstance() {
			return instance;
		}
		
		public Board getboard() {
			return board;
		}
		
		 	@FXML
		    void write(ActionEvent event) {
		    	// ���� ��ư�� �������� => ȭ����ȯ 
		    	MainController.getinstance().loadpage("boardwrite");
		    }
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) { //dao =>allboard => tc.setCellvalueFactory ���ʴ��. =>tableview.setItems
		// TODO Auto-generated method stub
		BoardDao boardDao=new BoardDao();
		
		ObservableList<Board> boards=boardDao.allboard();
		
		TableColumn tc=tableview.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("bno"));
		
		tc = tableview.getColumns().get(1); 
		tc.setCellValueFactory( new PropertyValueFactory<>("btitle"));
		
		tc = tableview.getColumns().get(2); 
		tc.setCellValueFactory( new PropertyValueFactory<>("bwriter"));
		
		tc = tableview.getColumns().get(3); 
		tc.setCellValueFactory( new PropertyValueFactory<>("bdate"));
		
		tc = tableview.getColumns().get(4); 
		tc.setCellValueFactory( new PropertyValueFactory<>("bcount"));
		
		tableview.setItems(boards);
		
		//���̺� �� Ŭ�� �� �̵� �̺�Ʈ : tableview.setOnMouseClicked ( (MouseEvent event)->{�����ڵ�} );
		tableview.setOnMouseClicked( (MouseEvent event ) -> {
			
			if( event.getButton().equals( MouseButton.PRIMARY) ) {
				// selectionmodel : ���õ� �׸� 
				board =  tableview.getSelectionModel().getSelectedItem();
				board.setBcount( board.getBcount()+1);
				// ��ȸ�� ���� �޼ҵ�
				boardDao.addcount(board);
			
				// ȭ�� ��ȯ 
				MainController.getinstance().loadpage("boardview"); 
			

			}
		} );
		
		
		}

}
