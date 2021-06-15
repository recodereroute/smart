package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseInfo {
	static String jdbcDriver;//변수같은 경우는 멤버필드에 초기화 시켜서 사용하는게 일반적
	static String jdbcUrl;
	static Connection conn;
	String sql;
	PreparedStatement pstmt;
	Integer result;
	ResultSet rs;
	
	static {
		jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	public static void getConnect() {//db랑 connect할 준비
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl,"subin","oracle");//db 호스트주소, id, pw
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if(rs != null) try {rs.close(); } 
					   catch (SQLException e) {}
		if(pstmt != null) try {pstmt.close(); } 
						  catch (SQLException e) {}
		if(conn != null) try {conn.close(); } 
					  	 catch (SQLException e) {}
	}
}
