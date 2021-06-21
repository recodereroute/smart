<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 비밀번호확인</title>
</head>
<body>
<form action = "pwEditOk.em" name = "frm" method = "post">
	비밀번호 : <input type = "password" name = "empPw"/>
	<span>${pwFail}</span><br />
	<input type = "submit" value = "확인"/>
</form>
</body>
</html>