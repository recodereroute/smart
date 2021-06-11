package controller.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeListPage {
	public void empList(HttpServletRequest request) {
		//레코드 하나가 EmployeeDTO 임을 기억.
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeeDTO> list = dao.getEmpList();
		request.setAttribute("empList", list);
	}
}