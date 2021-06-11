package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeJoinPage { //여기 담아서 DB로 보내줄거임.
	public void empInsert(HttpServletRequest request) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmail(request.getParameter("email"));//form에 있는 input name 을 적어줘야 함.
		dto.setEmpAddress(request.getParameter("empAddress"));
		dto.setEmployeeID(request.getParameter("employeeId"));
		dto.setEmpName(request.getParameter("empName"));
		dto.setEmpPw(request.getParameter("empPw"));
		dto.setEmpUserID(request.getParameter("empUserId"));
		dto.setHireDate(request.getParameter("hireDate"));
		dto.setJobId(request.getParameter("jobId"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		dto.setPhNumber(request.getParameter("phNumber"));
		EmployeeDAO dao = new EmployeeDAO();
		dao.empInsert(dto);
	}
}