package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import domain.Member;

public class MemberDao {

	Connection conn;
	private static MemberDao memberDao =new MemberDao();
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberDao() {//������
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fx_teacherfile?serverTimezone=UTC" , "root" , "1234");
																							//servertime=utc�� �ϴϱ� db������ �ȵ���.. ==>����������..
		} catch (Exception e) {
			
		}
		
	}
	
	public int setmember(Member temp) {
		
		String SQL="insert into member( id , name , pw , email )"+"values( ? , ? , ? , ? )";		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
				// PreparedStatement : ����� DB ���� �ϴ� �������̽� 
				// �Ű������� ������ �ֱ� 
					// setString( ?���� , ������ ) 
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getName());
			pstmt.setString(3, temp.getPassword());
			pstmt.setString(4, temp.getEmail());
			// SQL ���� 
				pstmt.executeUpdate(); // SQL ������Ʈ => ���� 
			// ȸ������ ������ 1 ��ȯ  
			return 1; 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		// ȸ������ ���н� 0 ��ȯ
		return 0; 
	}
	
	public int login( String id , String password  ) {
		
		// 1. SQL �ۼ� 
		String SQL = "select * from member where id = ? and pw = ? ";	
			// �˻� 
			// 1. select �˻����ʵ�� from ���̺�� where ����[ �ʵ�� = �� ] 
			// 2. select *[����ʵ�˻�] from ���̺�� 
				// * [ ��� �ʵ� ���� ] 
				// ����1 and ����2  : �̸鼭
				// ����1 or ����2   : �̰ų�
		
		// 2. SQL ����  
		try {
			PreparedStatement statement = conn.prepareStatement(SQL);
			// 3. SQL ���� 
			statement.setString(1, id);
			statement.setString(2, password);
			// 4. SQL ���� 
			// �˻������ �������̽��� �����ؼ� �������� 
			ResultSet resultSet = statement.executeQuery();
				// ����.executeQuery();  sql ���� 
				// ResultSet �������̽� : select ����� �������ִ� �������̽� 
					// .next() : �˻������ �ϳ��� �̵�
			if( resultSet.next() ) { // �ϳ��� ����� ���������� 
				return 1; // �α��� ���� 
			}else {
				return 2; // ������ ���̵�� ��й�ȣ ���� 
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; // db ���� 
	
	}//login ��
	
	
	public ArrayList<Member> allmember(){
		
		ArrayList<Member> members=new ArrayList<>();
		
		//SQL
		String SQL="select * from member";
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Member member=new Member();
				
				member.setId(rs.getString(2));
				member.setName(rs.getString(3));
				member.setPassword(rs.getString(4));
				member.setEmail(rs.getString(5));
				
				members.add(member);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return members;
	}//allmember ��
	
	
	public Member getmember(String logid) {
		
		Member member=new Member();
		
		String SQL="select * from member where id=?";
		
		try {
			PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setString( 1 , logid );
		// 3.SQL ���� 
			ResultSet resultSet = statement.executeQuery();
		// 4. ���� ��� 
			if( resultSet.next() ) {
				member.setId( resultSet.getString(2) );
				member.setName( resultSet.getString(3));
				member.setPassword( resultSet.getString(4) );
				member.setEmail( resultSet.getString(5));
				return member; // �ش��ϴ� ���̵��� ȸ���� �������
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return null ; // �ش��ϴ� ���̵��� ȸ���� ������� 
	}
		
	//ȸ������
	public int deletemember(String logid) {
		
		String SQL="select * from member where id=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				SQL="delete from member where id=?";
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, rs.getString(2)); //db 2��°�� : id
				pstmt.executeUpdate();
				
				return 1;
				
			}else {
				return 2;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
	public int updatemember(Member member) {
		
		String SQL="update member set name=? , email=? where id=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getId());
			
			pstmt.executeUpdate();
			
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			return 0;
	}
	
	
	
}
