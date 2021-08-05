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
																							//servertime=utc로 하니까 db연동이 안됐음.. ==>뭐가문제지..
		} catch (Exception e) {
			
		}
	}
	
	// 제품 등록 메소드 
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
		
		
		// 모든 제품 출력 
		
		public ObservableList<Product> allproduct(){
			
			// db에서 찾은 모든 제품을 저장할 리스트 
			ObservableList<Product> products = FXCollections.observableArrayList();
		
			// 1. sql 작성
			String SQL = "select * from product";
						// * : 와일드카드 => 모든 필드
			// 2. sql 조작
			try {
				PreparedStatement statement = conn.prepareStatement(SQL);
				// 3. sql 실행
				ResultSet resultSet = statement.executeQuery(); // select 검색 => 쿼리 실행 
				// 4. sql 결과 
					// 결과 하나씩 제품의 객체 생성해서 => 리스트에 객체 하나씩 담기 
				while( resultSet.next() ) {
					// 결과의 객체 생성 
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
					// 리스트에 저장 
					products.add(product);
				}
				return products;	// 성공시 리스트 반환 
			}catch (Exception e) {	}
			return null; // 실패시 null
		}
		





}
