package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.ProductDao;
import domain.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class PaddController implements Initializable {
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private TextField txtpprice;

    @FXML
    private TextField txtstock;

    @FXML
    private Button btnpadd;

    @FXML
    private RadioButton opt_1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt_2;

    @FXML
    private RadioButton opt_3;

    @FXML
    private RadioButton opt_4;

    @FXML
    void padd(ActionEvent event) { // 등록 버튼을 눌렀을때 
    	
    	
    	String pname = txtpname.getText();
    	String pcontents = txtpcontents.getText();
       	String pcategory ="" ;
    	// 카테고리 
       		// 4. 선택된 카테고리 텍스트 넣기 => 버튼명.isSelected()
    	if( opt_1.isSelected()  )  pcategory = "의류";
    	if( opt_2.isSelected()  )  pcategory = "신발";
    	if( opt_3.isSelected()  )  pcategory = "악세사리";
    	if( opt_4.isSelected()  )  pcategory = "기타";
    	
    	int pprice;
    	int pstock;
    	try {
    		// 3. 제품가격/재고 에 문자일경우 제어 => int 형으로 변환  
    		pprice = Integer.parseInt(  txtpprice.getText() ) ;
    		pstock = Integer.parseInt( txtstock.getText() );
    		
    	 	// 5. 5개중 하나라도 공백이면 X
        	if( pname.equals("") || pcontents.equals("") || pcategory.equals("") ) {
        		Alert alert = new Alert( AlertType.INFORMATION);
    			alert.setContentText(" [ 오류] 입력 안된 항목이 있습니다 ");
    			alert.setHeaderText("등록 실패");
    			alert.showAndWait(); // 확인 버튼 누르기전까지 대기상태 
    			return;
        	}
        	// 제품명이 3~10 글자 제한
        	if( pname.length() < 3 || pname.length()>10 ) {
          		Alert alert = new Alert( AlertType.INFORMATION);
        		alert.setContentText(" [ 오류] 제품명은 3~10글자 사이만 입력 ");
        		alert.setHeaderText("등록 실패");
        		alert.showAndWait(); // 확인 버튼 누르기전까지 대기상태 
        		return;
        	}
    	}
    	catch (Exception e) { // 숫자 입력칸에 문자 입력시 예외 
    		Alert alert = new Alert( AlertType.INFORMATION);
			alert.setContentText(" [ 오류] 가격/재고에는 숫자만 입력 ");
			alert.setHeaderText("등록 실패");
			alert.showAndWait(); // 확인 버튼 누르기전까지 대기상태 
			return;
		}
    	// 제어 모두 통과하면 db에 저장 
    	Product product 
    	= new Product(pname, pcontents, pprice, pstock, pcategory, 0, 0, pimage);
    	
    	ProductDao productDao =  new ProductDao();
    	productDao.addproduct(product);
    	
    	MainController.getinstance().loadpage("plist");

    }
    
    @FXML
    private Button btnupload;

    @FXML
    private Label txtpath;

    @FXML
    private ImageView imgp;
    
    @FXML
    private Stage filestage;
    private static String pimage;
    
    @FXML
    void load(ActionEvent event) { // 파일 경로 찾기 => DB에 넣기 
    	
    	// 파일 선택 클래스 [ 파일선택 화면 제공 ]  
    	FileChooser fileChooser = new FileChooser();

    	fileChooser.getExtensionFilters().addAll( new ExtensionFilter("그림파일 : Image File", "*.png" , "*jpg" ,"*gif") );
    									// import javafx.stage.FileChooser.ExtensionFilter;
    	// 선택된 파일의 경로을 파일클래스에 저장
    		// 파일선택의 스테이지 설정 
    	File file = fileChooser.showOpenDialog(filestage);
    				// 선택된 파일명을 file클래스 저장 
    		
    	// 선택된 파일명을 레이블 띄우기 
    	if( file != null ) {
    		txtpath.setText("파일경로 : " + file.getPath() );
    									// 파일.getpath() : 파일의 경로 
    		// 이미지뷰에 이미지 넣기 
    				//System.out.println( file.getPath());	//  경로 구분 \
    				//System.out.println( file.toURI().toString()); // 경로 구분 /
    		
    		pimage = file.toURI().toString();
    		Image image = new Image( pimage );
    		imgp.setImage(image);
    		
    	}else {
    	}
    	
    }
    
    
    

    
    
    
    
    
    
    
    
   
	
	

}
