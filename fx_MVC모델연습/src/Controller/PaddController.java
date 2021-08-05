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
    void padd(ActionEvent event) { // ��� ��ư�� �������� 
    	
    	
    	String pname = txtpname.getText();
    	String pcontents = txtpcontents.getText();
       	String pcategory ="" ;
    	// ī�װ� 
       		// 4. ���õ� ī�װ� �ؽ�Ʈ �ֱ� => ��ư��.isSelected()
    	if( opt_1.isSelected()  )  pcategory = "�Ƿ�";
    	if( opt_2.isSelected()  )  pcategory = "�Ź�";
    	if( opt_3.isSelected()  )  pcategory = "�Ǽ��縮";
    	if( opt_4.isSelected()  )  pcategory = "��Ÿ";
    	
    	int pprice;
    	int pstock;
    	try {
    		// 3. ��ǰ����/��� �� �����ϰ�� ���� => int ������ ��ȯ  
    		pprice = Integer.parseInt(  txtpprice.getText() ) ;
    		pstock = Integer.parseInt( txtstock.getText() );
    		
    	 	// 5. 5���� �ϳ��� �����̸� X
        	if( pname.equals("") || pcontents.equals("") || pcategory.equals("") ) {
        		Alert alert = new Alert( AlertType.INFORMATION);
    			alert.setContentText(" [ ����] �Է� �ȵ� �׸��� �ֽ��ϴ� ");
    			alert.setHeaderText("��� ����");
    			alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 
    			return;
        	}
        	// ��ǰ���� 3~10 ���� ����
        	if( pname.length() < 3 || pname.length()>10 ) {
          		Alert alert = new Alert( AlertType.INFORMATION);
        		alert.setContentText(" [ ����] ��ǰ���� 3~10���� ���̸� �Է� ");
        		alert.setHeaderText("��� ����");
        		alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 
        		return;
        	}
    	}
    	catch (Exception e) { // ���� �Է�ĭ�� ���� �Է½� ���� 
    		Alert alert = new Alert( AlertType.INFORMATION);
			alert.setContentText(" [ ����] ����/����� ���ڸ� �Է� ");
			alert.setHeaderText("��� ����");
			alert.showAndWait(); // Ȯ�� ��ư ������������ ������ 
			return;
		}
    	// ���� ��� ����ϸ� db�� ���� 
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
    void load(ActionEvent event) { // ���� ��� ã�� => DB�� �ֱ� 
    	
    	// ���� ���� Ŭ���� [ ���ϼ��� ȭ�� ���� ]  
    	FileChooser fileChooser = new FileChooser();

    	fileChooser.getExtensionFilters().addAll( new ExtensionFilter("�׸����� : Image File", "*.png" , "*jpg" ,"*gif") );
    									// import javafx.stage.FileChooser.ExtensionFilter;
    	// ���õ� ������ ����� ����Ŭ������ ����
    		// ���ϼ����� �������� ���� 
    	File file = fileChooser.showOpenDialog(filestage);
    				// ���õ� ���ϸ��� fileŬ���� ���� 
    		
    	// ���õ� ���ϸ��� ���̺� ���� 
    	if( file != null ) {
    		txtpath.setText("���ϰ�� : " + file.getPath() );
    									// ����.getpath() : ������ ��� 
    		// �̹����信 �̹��� �ֱ� 
    				//System.out.println( file.getPath());	//  ��� ���� \
    				//System.out.println( file.toURI().toString()); // ��� ���� /
    		
    		pimage = file.toURI().toString();
    		Image image = new Image( pimage );
    		imgp.setImage(image);
    		
    	}else {
    	}
    	
    }
    
    
    

    
    
    
    
    
    
    
    
   
	
	

}
