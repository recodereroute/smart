package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeModifyPage {
	public void empModify(HttpServletRequest request) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmployeeID(request.getParameter("employeeID"));
		dto.setEmail(request.getParameter("email"));
		dto.setEmpAddress(request.getParameter("empAddress"));
		dto.setJobId(request.getParameter("jobId"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		dto.setPhNumber(request.getParameter("phNumber"));
		EmployeeDAO dao = new EmployeeDAO();
		dao.empUpadte(dto);
	}
}
