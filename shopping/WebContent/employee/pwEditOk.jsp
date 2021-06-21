<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 비밀번호변경</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function pwChk(){
		$("#btn").click(function(){
			if($("#empPw").val()==""){
				alert("현재 비밀번호를 입력하세요");
				$("#empPw").focus();
				return false;
			}
			if($("#newPw").val ==""){
				alert("변경 비밀번호를 입력하세요.");
				$("#newPw").focus();
				return false;
			}
			if($("#newPwCon").val()==""){
				alert("변경비밀번호 확인을 입력하세요.");
				$("#newPwCon").focus();
				return false;
			}else{
				if($("#newPw").val() != $("#newPwCon").val()){
					alert("비밀번호 확인값이 다릅니다.");
					$("#newPw").val("");
					$("#newPwCon").val("");
					$("#newPw").focus();
					return false;
				}
			}
			$("#frm").submit();
		});
	});

</script>
</head>
<body>
<form action = "ChangePw.em" name = "frm" method = "post" id = "frm" onclick = "return pwChk();"><!-- onclick는 유효성 검사를 위해서 추가 -->
	현재 비밀번호 : <input type = "password" name = "empPw" id = "empPw"/><br />
	변경 비밀번호 : <input type = "password" name = "newPw" id = "newPw"/><br />
	변경 비밀번호 확인 : <input type = "password" name = "newPwCon" id = "newPwCon"/><br />
	<input type = "button" value = "비밀번호 변경" id = "btn" />
</form>
</body>
</html>