package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DTO.AuthInfo;

public class MemberPwConfirmPage {
	public String pwConfrim(HttpServletRequest request) {
		String path = null;
		//새로운 세션변수를 만드는 것 - 세션 자체는 한 브라우저 내에서 계속 사용될수 있음 - 이미 밖에 만들어져 있음.
		HttpSession session = request.getSession();//request로 넘겨받은 세션에 있는 비밀번호 값과 비교할거임.
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(request.getParameter("memPw").equals(authInfo.getUserPw())) {
			path = "member/pwChangeOk.jsp";
		}else {
			request.setAttribute("pwFail1", "비밀번호가 틀렸습니다.");//request는 이 페이지에서 밖에 사용 못하므로 지울필요x
			path = "member/pwChange.jsp";
		}
		return path;
	}
}
