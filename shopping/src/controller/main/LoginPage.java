package controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO.LoginDAO;
import model.DTO.AuthInfo;

public class LoginPage {
	//쿠키를 심으려면 response를 받아와야 함.
	public void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		LoginDAO dao = new LoginDAO();
		AuthInfo authInfo = dao.login(userId);
		if(authInfo == null) {
			session.removeAttribute("pwFail");
			session.setAttribute("userFail", "아이디가 존재하지 않습니다.");
		}else {
			session.removeAttribute("userFail");
			if(userPw.equals(authInfo.getUserPw())) {
				session.removeAttribute("pwFail");
				/// 웹브라우저를 닫기 전까지 사용할 수 있도록 session에 저장
				session.setAttribute("authInfo", authInfo);
				// 자동로그인 체크박스
				String autoLogin = request.getParameter("autoLogin");
				if(autoLogin != null && autoLogin.equals("auto")) {
					Cookie cookie = new Cookie("autoLogin",userId);//쿠키 이름은 autoLogin임
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					//웹브라우저에 쿠키를 넘김
					response.addCookie(cookie);
				}
				
				// 쿠키를 만들어 달라고 요청
				String idStore = request.getParameter("idStore");
				if(idStore != null  && idStore.equals("store")) {
					//쿠키생성
					Cookie cookie = new Cookie("idStore", userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					//웹브라우저에 쿠키를 넘김
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idStore", userId);
					cookie.setPath("/");
					cookie.setMaxAge(0); // 유지시간 : 0
					//웹브라우저에 쿠키를 넘김
					response.addCookie(cookie); // 사라짐.
				}
			}else {
				session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			}
		}
	}
}