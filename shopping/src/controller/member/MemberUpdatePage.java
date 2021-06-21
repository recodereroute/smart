package controller.member;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberUpdatePage {
	public int memberUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		MemberDTO dto = new MemberDTO();
		dto.setDetailAdd(request.getParameter("detailAdd"));
		dto.setMemAccount(request.getParameter("memAccount"));
		dto.setMemAddress(request.getParameter("memAddress"));
		dto.setMemEmail(request.getParameter("memEmail"));
		dto.setMemEmailCk(request.getParameter("memEmailCk"));
		dto.setMemPhone(request.getParameter("memPhone"));
		dto.setPostNumber(request.getParameter("postNumber"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = sf.parse(request.getParameter("memBirth"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setMemBirth(date);
		dto.setMemPw(request.getParameter("memPw"));
		dto.setMemId(authInfo.getUserId());
		if(request.getParameter("memPw").equals(authInfo.getUserPw())) {
			MemberDAO dao = new MemberDAO();
			dao.memUpdate(dto);
			session.removeAttribute("pwFail");//세션에 있는 pwFail 속성만 제거 - 세션은 살아있음
			return 1;
		}else {
			session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			return 2;
		}
		
	}
}