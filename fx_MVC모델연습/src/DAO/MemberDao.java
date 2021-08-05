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
	
	public MemberDao() {//생성자
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fx_teacherfile?serverTimezone=UTC" , "root" , "1234");
																							//servertime=utc로 하니까 db연동이 안됐음.. ==>뭐가문제지..
		} catch (Exception e) {
			
		}
		
	}
	
	public int setmember(Member temp) {
		
		String SQL="insert into member( id , name , pw , email )"+"values( ? , ? , ? , ? )";		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
				// PreparedStatement : 연결된 DB 조작 하는 인터페이스 
				// 매개변수에 데이터 넣기 
					// setString( ?순서 , 데이터 ) 
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getName());
			pstmt.setString(3, temp.getPassword());
			pstmt.setString(4, temp.getEmail());
			// SQL 실행 
				pstmt.executeUpdate(); // SQL 업데이트 => 실행 
			// 회원가입 성공시 1 반환  
			return 1; 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		// 회원가입 실패시 0 반환
		return 0; 
	}
	
	public int login( String id , String password  ) {
		
		// 1. SQL 작성 
		String SQL = "select * from member where id = ? and pw = ? ";	
			// 검색 
			// 1. select 검색할필드명 from 테이블명 where 조건[ 필드명 = 값 ] 
			// 2. select *[모든필드검색] from 테이블명 
				// * [ 모든 필드 대응 ] 
				// 조건1 and 조건2  : 이면서
				// 조건1 or 조건2   : 이거나
		
		// 2. SQL 연결  
		try {
			PreparedStatement statement = conn.prepareStatement(SQL);
			// 3. SQL 조작 
			statement.setString(1, id);
			statement.setString(2, password);
			// 4. SQL 실행 
			// 검색결과을 인터페이스로 연결해서 가져오기 
			ResultSet resultSet = statement.executeQuery();
				// 연결.executeQuery();  sql 실행 
				// ResultSet 인터페이스 : select 결과와 연결해주는 인터페이스 
					// .next() : 검색결과를 하나씩 이동
			if( resultSet.next() ) { // 하나의 결과가 존재했을때 
				return 1; // 로그인 성공 
			}else {
				return 2; // 동일한 아이디와 비밀번호 없다 
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; // db 오류 
	
	}//login 끝
	
	
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
	}//allmember 끝
	
	
	public Member getmember(String logid) {
		
		Member member=new Member();
		
		String SQL="select * from member where id=?";
		
		try {
			PreparedStatement statement = conn.prepareStatement(SQL);
				statement.setString( 1 , logid );
		// 3.SQL 실행 
			ResultSet resultSet = statement.executeQuery();
		// 4. 실행 결과 
			if( resultSet.next() ) {
				member.setId( resultSet.getString(2) );
				member.setName( resultSet.getString(3));
				member.setPassword( resultSet.getString(4) );
				member.setEmail( resultSet.getString(5));
				return member; // 해당하는 아이디의 회원이 있을경우
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return null ; // 해당하는 아이디의 회원이 없을경우 
	}
		
	//회원삭제
	public int deletemember(String logid) {
		
		String SQL="select * from member where id=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				SQL="delete from member where id=?";
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, rs.getString(2)); //db 2번째열 : id
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
