package controller.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeController extends HttpServlet
	implements Servlet{
	public void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(
				contextPath.length());
		if(command.equals("/empList.em")) {
			EmployeeListPage action = new EmployeeListPage();
			action.empList(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"employee/employeeList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empRegest.em")) {
			EmployeeNumPage action = new EmployeeNumPage();
			action.getNum(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empJoin.em")) {
			EmployeeJoinPage action = new EmployeeJoinPage();
			action.empInsert(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empInfo.em")) {
			EmployeeInfoPage action = new EmployeeInfoPage();
			action.empInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeInfo.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empModify.em")) {
			EmployeeInfoPage action = new EmployeeInfoPage();
			action.empInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empModifyOk.em")) {
			EmployeeModifyPage action = new EmployeeModifyPage();
			action.empModify(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empDelete.em")) {
			EmployeeDeletePage action = new EmployeeDeletePage();
			action.empDelete(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empMyPage.em")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/empMyPage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empDetail.em")) {
			EmpDetailPage action = new EmpDetailPage();
			action.empDetail(request);
			//위에서 받은 request 정보를 아래의 경로에 dispatch 해준다.(붙여넣어준다.)
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/employeeInfo.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empPwEdit.em")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee/pwEdit.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/pwEditOk.em")) {
			EmpPwConfirm action = new EmpPwConfirm();
			int i = action.confirm(request);
			if(i == 1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("employee/pwEditOk.jsp");
				dispatcher.forward(request, response);
			}else {
				response.sendRedirect("empPwEdit.em");
			}
		}else if(command.equals("/ChangePw.em")) {
			EmpPwChange action = new EmpPwChange();
			int i = action.pwChange(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			}else {
				
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
