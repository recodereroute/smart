package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.EmployeeDTO;

public class EmployeeDAO {
	//column이 삭제되거나 추가되면 에러가 날 수 있기때문에 필요한 속성들을 전부 표기해 줘야한다.
	final String COLUMNS = "EMPLOYEE_ID, EMP_USERID, EMP_PW, EMP_NAME, HIRE_DATE, JOB_ID, PH_NUMBER, OFFICE_NUMBER, EMAIL, EMP_ADDRESS";
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
	public void empUpadte(EmployeeDTO dto) {
		sql = "update employees "  
				+ " set JOB_ID = ?, PH_NUMBER = ?, OFFICE_NUMBER = ?,"
				+ " EMAIL = ?, EMP_ADDRESS = ?" 
				+ " where employee_id = ?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getJobId());
			pstmt.setString(2, dto.getPhNumber());
			pstmt.setString(3, dto.getOfficeNumber());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getEmpAddress());
			pstmt.setString(6, dto.getEmployeeID());//DTO에 있는값이 String 이라 setString을 사용중.
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void empDelete(String empId) {
		sql = "delete from employees " + " where employee_id = ?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	public EmployeeDTO empInfo(String empId) {
		EmployeeDTO dto = new EmployeeDTO();
		sql = "select "+ COLUMNS +" from employees " + " where employee_id = ?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);// DB랑 무관, 날리는 값에 따라 자료형 맞춰줌
			//result set - 쿼리 날린후 결과
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setEmployeeID(rs.getString("EMPLOYEE_ID"));
				dto.setEmpUserID(rs.getString(2));
				dto.setEmpPw(rs.getString(3));
				dto.setEmpName(rs.getString(4));
				dto.setHireDate(rs.getString("HIRE_DATE"));
				dto.setJobId(rs.getString("JOB_ID"));
				dto.setPhNumber(rs.getString("PH_NUMBER"));
				dto.setOfficeNumber(rs.getString("OFFICE_NUMBER"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEmpAddress(rs.getString("EMP_ADDRESS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	
	public List<EmployeeDTO> getEmpList() {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		sql = "select "+ COLUMNS +" from employees";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeDTO dto = new EmployeeDTO();
				//rs 의 결과로 여러 레코드가 출력될텐데 어떤식으로 getString 하는건지?
				dto.setEmployeeID(rs.getString("EMPLOYEE_ID"));
				dto.setEmpUserID(rs.getString(2));
				dto.setEmpPw(rs.getString(3));
				dto.setEmpName(rs.getString(4));
				dto.setHireDate(rs.getString("HIRE_DATE"));
				dto.setJobId(rs.getString("JOB_ID"));
				dto.setPhNumber(rs.getString("PH_NUMBER"));
				dto.setOfficeNumber(rs.getString("OFFICE_NUMBER"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEmpAddress(rs.getString("EMP_ADDRESS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	public int getEmpNo() {
		getConnect();
		sql = "select nvl(max(employee_id), 10000) + 1 from employees";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();//pstmt에서 실행된 쿼리값(레코드)을 받아 옴
			rs.next();//BOF(파일의 시작 Begin of File) - 레코드 - EOF(파일의 마지막을 알림) - 즉 첫 값은 BOF가 들어있을거기 때문에 next(); 한번 실행
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	public void empInsert(EmployeeDTO dto) {
		sql = "insert into employees ( " + COLUMNS + " )"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		getConnect();//커넥션 정보를 가져옴.
		try {
			pstmt = conn.prepareStatement(sql);//sql문을 db로 전달
			pstmt.setString(1, dto.getEmployeeID());//인덱스는 '?' 갯수만 따짐
			pstmt.setString(2, dto.getEmpUserID());
			pstmt.setString(3, dto.getEmpPw());
			pstmt.setString(4, dto.getEmpName());
			pstmt.setString(5, dto.getHireDate());
			pstmt.setString(6, dto.getJobId());
			pstmt.setString(7, dto.getPhNumber());
			pstmt.setString(8, dto.getOfficeNumber());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getEmpAddress());
			result = pstmt.executeUpdate();//integer 반환
			System.out.println(result + "개행이 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	private void close() {
		if(rs != null) try {rs.close(); } 
					   catch (SQLException e) {}
		if(pstmt != null) try {pstmt.close(); } 
						  catch (SQLException e) {}
		if(conn != null) try {conn.close(); } 
					  	 catch (SQLException e) {}
	}
	
}