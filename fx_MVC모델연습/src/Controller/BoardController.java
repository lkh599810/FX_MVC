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
	    		// tableview : 리스트 저장 [ ObservableList ]
	    		//  <  > : 제네릭 => 테이블에 담을 클래스명 
	    @FXML
	    private Button btnwrite;

	
		private static BoardController instance;
		private static Board board; //게시물 객체 ==> 중요.
		
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
		    	// 쓰기 버튼을 눌렀을때 => 화면전환 
		    	MainController.getinstance().loadpage("boardwrite");
		    }
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) { //dao =>allboard => tc.setCellvalueFactory 차례대로. =>tableview.setItems
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
		
		//테이블에 행 클릭 시 이동 이벤트 : tableview.setOnMouseClicked ( (MouseEvent event)->{실행코드} );
		tableview.setOnMouseClicked( (MouseEvent event ) -> {
			
			if( event.getButton().equals( MouseButton.PRIMARY) ) {
				// selectionmodel : 선택된 항목 
				board =  tableview.getSelectionModel().getSelectedItem();
				board.setBcount( board.getBcount()+1);
				// 조회수 증가 메소드
				boardDao.addcount(board);
			
				// 화면 전환 
				MainController.getinstance().loadpage("boardview"); 
			

			}
		} );
		
		
		}

}
