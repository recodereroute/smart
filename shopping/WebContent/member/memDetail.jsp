<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나의 상세정보<br />
아이디 : ${emp.memId } <br />
이름 : ${emp.memName } <br />
생년월일 : ${emp.memBirth } <br />
성별 : ${emp.memGender } <br />
우편번호 : ${emp.postNumber } <br />
주소 : ${emp.memAddress } <br />
상세주소 : ${emp.detailAdd } <br />
연락처 : ${emp.memPhone } <br />
이메일 : ${emp.memEmail } <br />
계좌번호 : ${emp.memAccount }  <br />
수신여부 : <c:if test="${emp.memEmailCk == 'Y' }" > 
			이메일 수신 함
		</c:if> 
		<c:if test="${emp.memEmailCk == 'N'}" > 
			이메일 수신 안함
		</c:if>
		<br />
<a href = "memSujung.mem">수정</a>
</body>
</html>