package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	
	Connection conn;
	
	private static ProductDao productDao=new ProductDao();
	
	public static ProductDao getProductDao() {
		return productDao;
	}
	
	public ProductDao() {
	// TODO Auto-generated constructor stub
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fx_teacherfile?serverTimezone=UTC" , "root" , "1234");
																							//servertime=utc�� �ϴϱ� db������ �ȵ���.. ==>����������..
		} catch (Exception e) {
			
		}
	}
	
	// ��ǰ ��� �޼ҵ� 
		public int addproduct( Product product ) {
			try {
				String SQL = "insert into product(pname,pcontents,pprice,pstock,pcategory,pactivation,pquantity,pimage)"+"values(?,?,?,?,?,?,?,?)";
				
				PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setString(1, product.getPname());
				statement.setString(2, product.getPcontents());
				statement.setInt(3, product.getPprice());
				statement.setInt(4, product.getPstock());
				statement.setString(5, product.getPcategory());
				statement.setInt(6, product.getPactivation());
				statement.setInt(7, product.getPquantity());
				statement.setString(8, product.getPimage());
				statement.executeUpdate();
				
				return 1;
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return 0;
		}
		
		
		// ��� ��ǰ ��� 
		
		public ObservableList<Product> allproduct(){
			
			// db���� ã�� ��� ��ǰ�� ������ ����Ʈ 
			ObservableList<Product> products = FXCollections.observableArrayList();
		
			// 1. sql �ۼ�
			String SQL = "select * from product";
						// * : ���ϵ�ī�� => ��� �ʵ�
			// 2. sql ����
			try {
				PreparedStatement statement = conn.prepareStatement(SQL);
				// 3. sql ����
				ResultSet resultSet = statement.executeQuery(); // select �˻� => ���� ���� 
				// 4. sql ��� 
					// ��� �ϳ��� ��ǰ�� ��ü �����ؼ� => ����Ʈ�� ��ü �ϳ��� ��� 
				while( resultSet.next() ) {
					// ����� ��ü ���� 
					Product product = new Product(
							resultSet.getInt(1),
							resultSet.getString(2),
							resultSet.getString(3),
							resultSet.getInt(4),
							resultSet.getInt(5),
							resultSet.getString(6),
							resultSet.getInt(7),
							resultSet.getInt(8),
							resultSet.getString(9));
					// ����Ʈ�� ���� 
					products.add(product);
				}
				return products;	// ������ ����Ʈ ��ȯ 
			}catch (Exception e) {	}
			return null; // ���н� null
		}
		





}
