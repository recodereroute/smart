package controller.employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.EmployeeDAO;
import model.DTO.AuthInfo;
import model.DTO.EmployeeDTO;


public class EmpDetailPage {
	public void empDetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String empId = authInfo.getUserId();
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeDTO dto = dao.empDetail(empId);
		request.setAttribute("emp", dto); //이 페이지에서 정보를 받는 쪽은 emp.xxx 로 정보를 사용할 수 있다.
	}
}