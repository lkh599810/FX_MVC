package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {

	Connection conn;
	private static BoardDao boardDao= new BoardDao();
	
	
	
	public BoardDao() {
		// TODO Auto-generated constructor stub
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fx_teacherfile?serverTimezone=UTC" , "root" , "1234");
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public int boardwrite(Board board) {
		
		String SQL ="insert into board(btitle,bcontents,bwriter,bdate,bcount)"+"values(?,?,?,?,?)";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontents());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBdate());
			pstmt.setInt(5,board.getBcount());
			
			pstmt.executeUpdate();
			
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
	
	public ObservableList<Board> allboard(){
		
		ObservableList<Board> boards=FXCollections.observableArrayList();
		
		String SQL="select * from board";
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board board=new Board();
				
				board.setBno(rs.getInt(1));
				board.setBtitle(rs.getString(2));
				board.setBcontents(rs.getString(3));
				board.setBwriter(rs.getString(4));
				board.setBdate(rs.getString(5));
				board.setBcount(rs.getInt(6));
				
				boards.add(board);
				
			}
			return boards;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	//조회수
	public void addcount(Board board) {
		
		String SQL="update board set bcount=? where bno=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getBcount());
			pstmt.setInt(2, board.getBno());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	//삭제
	public int delboard(Board board) {
		
		String SQL="delete from board where bno=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getBno());
			
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public int updateboard(Board board, String title, String contents) {
		
		String SQL="update board set btitle=? , bcontents=? where bno=?";
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			
			pstmt.setString(1, title);
			pstmt.setString(2,contents);
			pstmt.setInt(3, board.getBno());
			
			pstmt.executeUpdate();
			
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return -1;
	}
	
}
