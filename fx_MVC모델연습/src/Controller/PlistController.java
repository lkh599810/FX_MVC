package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.ProductDao;
import domain.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PlistController implements Initializable{
	  	@FXML
	  	private TableView<Product> tableview;
	    			// < ? > : 테이블에 넣을 객체의 클래스 
	    				// 테이블 저장 : ObserbleList
	    @FXML
	    private ImageView imgp;

	    @FXML
	    private Label lblpcontents;

	    @FXML
	    private Label lblpactivation;

	    @FXML
	    private Button btnupdate;

	    @FXML
	    private Button btnpdelete;

	    @FXML
	    private Button btnrefresh;
	   
	    
	    
	    @FXML
	    void pdelete(ActionEvent event) {

	    }

	    @FXML
	    void pupdate(ActionEvent event) {

	    }

	    @FXML
	    void refresh(ActionEvent event) {

	    }
		
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
			// 1. db에 모든 제품 가져오기 
			ProductDao productDao = new ProductDao();
			ObservableList<Product> products = productDao.allproduct();
			// 2. 모든 제품을 테이블에 셋팅
				// 각 열에 각 객체의 필드를 넣기 
				TableColumn tc = tableview.getColumns().get(0);
				tc.setCellValueFactory( new PropertyValueFactory<>("pno") );
																// 리스트내 객체의 필드 
				tc = tableview.getColumns().get(1);
				tc.setCellValueFactory( new PropertyValueFactory<>("pname") );
				
				tc = tableview.getColumns().get(2);
				tc.setCellValueFactory( new PropertyValueFactory<>("pcategory") );
				
				tc = tableview.getColumns().get(3);
				tc.setCellValueFactory( new PropertyValueFactory<>("pprice") );
				
				tc = tableview.getColumns().get(4);
				tc.setCellValueFactory( new PropertyValueFactory<>("pstock") );
				
				tc = tableview.getColumns().get(5);
				tc.setCellValueFactory( new PropertyValueFactory<>("pquantity") );
				
			// 테이블에 리스트 담기 
			tableview.setItems(products);
			
				// 테이블에서 행을 클릭했을때 
			//tableview.setOnMouseClicked( ( MouseEvent event) -> { 실행코드 } );
			tableview.setOnMouseClicked( ( MouseEvent event) -> { 
				
				if( event.getButton().equals( MouseButton.PRIMARY) ) {
					//해당 이벤트가 마우스의 클릭과 동일하면 
				
						// 클릭된 아이이템 저장 
					Product product = tableview.getSelectionModel().getSelectedItem();
					
					// 설명
					lblpcontents.setText( product.getPcontents() );
					
					// 이미지
					try {
						// 이미지가 없는경우 예외처리 발생
						Image image = new Image( product.getPimage() );
						imgp.setImage(image);
					}
					catch (Exception e) {
						// 이미지가 없습니다 
					}
					// 활성화
					if( product.getPactivation() == 1 ) {
						lblpactivation.setText( "제품 판매중");
					}else if( product.getPactivation() == 2 ) {
						lblpactivation.setText( "재고 품절");
					}else {
						lblpactivation.setText( "제품 준비중");
					}

				}
			} );
			
		
		
		}
		
		
	
	
	
}
