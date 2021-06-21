package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDetailPage {
	public void memberDetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String memId = authInfo.getUserId();
//		memId = request.getParameter("memId"); // session이 있는 경우에는 session을 사용한다
											// request를 사용하면 쿼리스트링 (주소 뒤에 정보)이 붙어서 날라가기 때문
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.memDetail(memId);
		request.setAttribute("emp", dto);//memDetail.jsp로 넘겨줄 정보 - dto
	}
}