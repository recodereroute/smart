package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet
	implements Servlet{
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memRegist.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);//대체
		}else if(command.equals("/memJoin.mem")) {
			MemberJoinPage action = new MemberJoinPage();
			action.memInsert(request);
			response.sendRedirect("main.sm");//이동
		}else if(command.equals("/memList.mem")) {
			MemberListPage action = new MemberListPage();
			action.memList(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.include(request, response); //포함
		}else if(command.equals("/memInfo.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/memMod.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memModifyOk.mem")) {
			MemberModifyPage action = new MemberModifyPage();
			action.memUpdate(request);
			response.sendRedirect("memList.mem");
		}else if(command.equals("/memDel.mem")) {
			MemberDeletePage action = new MemberDeletePage();
			action.memDel(request);
			response.sendRedirect("memList.mem");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}