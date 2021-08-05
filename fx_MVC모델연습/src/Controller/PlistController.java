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
	    			// < ? > : ���̺� ���� ��ü�� Ŭ���� 
	    				// ���̺� ���� : ObserbleList
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
		
			// 1. db�� ��� ��ǰ �������� 
			ProductDao productDao = new ProductDao();
			ObservableList<Product> products = productDao.allproduct();
			// 2. ��� ��ǰ�� ���̺� ����
				// �� ���� �� ��ü�� �ʵ带 �ֱ� 
				TableColumn tc = tableview.getColumns().get(0);
				tc.setCellValueFactory( new PropertyValueFactory<>("pno") );
																// ����Ʈ�� ��ü�� �ʵ� 
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
				
			// ���̺� ����Ʈ ��� 
			tableview.setItems(products);
			
				// ���̺��� ���� Ŭ�������� 
			//tableview.setOnMouseClicked( ( MouseEvent event) -> { �����ڵ� } );
			tableview.setOnMouseClicked( ( MouseEvent event) -> { 
				
				if( event.getButton().equals( MouseButton.PRIMARY) ) {
					//�ش� �̺�Ʈ�� ���콺�� Ŭ���� �����ϸ� 
				
						// Ŭ���� �������� ���� 
					Product product = tableview.getSelectionModel().getSelectedItem();
					
					// ����
					lblpcontents.setText( product.getPcontents() );
					
					// �̹���
					try {
						// �̹����� ���°�� ����ó�� �߻�
						Image image = new Image( product.getPimage() );
						imgp.setImage(image);
					}
					catch (Exception e) {
						// �̹����� �����ϴ� 
					}
					// Ȱ��ȭ
					if( product.getPactivation() == 1 ) {
						lblpactivation.setText( "��ǰ �Ǹ���");
					}else if( product.getPactivation() == 2 ) {
						lblpactivation.setText( "��� ǰ��");
					}else {
						lblpactivation.setText( "��ǰ �غ���");
					}

				}
			} );
			
		
		
		}
		
		
	
	
	
}
