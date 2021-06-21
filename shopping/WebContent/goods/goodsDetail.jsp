<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function onQty(){
		var qty = document.frm.PurchaseQty.value;
		document.getElementById("tot").innerHTML = qty * ${dto.prodPrice}
	}
	function goodsCartAdd(prodNum){
		var qty = document.frm.PurchaseQty.value;
		location.href="goodsCartAdd.gd?prodNum="+prodNum+"&qty="+qty+"&prodPrice=${dto.prodPrice}";
	}
</script>
</head>
<body>
<form action="#" name="frm" method="post">
<input type ="hidden" name = "prodNum" value = "${dto.prodNum }"/>

${dto.ctgr }: ${dto.prodName}의 상품설명 입니다.
<table width = "800px" align = "center" border= 1>

	<tr><td rowspan = "6">
		<img width = "400px" src = "goods/upload/${dto.prodImage.split(',')[0] }"/>
		</td><td width = "400px">${dto.prodName }</td></tr>
	<tr><td>${dto.prodPrice }원</td></tr>
	<tr><td><c:if test="${dto.prodDelFee == 0 }">무료배송</c:if>
			<c:if test="${dto.prodDelFee != 0 }">${dto.prodDelFee }원</c:if></td></tr>
	<tr><td>
		수량 : 
		<input type = "number" min = "1" value = "1" name="PurchaseQty" onchange="onQty();"/>
		  개
		</td></tr>
	<tr><td>총 상품금액 :&nbsp;
		<span id ="tot">${dto.prodPrice }</span></td></tr>
	<tr><td><input type = "button" value = "장바구니" onclick = "goodsCartAdd('${dto.prodNum}');"/>
			<input type = "submit" value = "바로구매"/></td></tr>
	<tr><td colspan="2">
		용량 : ${dto.prodCapacity}<br />
		공급업체 : ${dto.prodSupplyer}<br />
		${dto.prodDetail }<br />
		<c:forTokens items="${dto.prodImage }" delims="," var = "file">
			<c:if test="${file != 'null' }">
			<img width = "800px" src = "goods/upload/${file }" /><br />
			</c:if>
		</c:forTokens>
		</td></tr>
</table>
</form>
</body>
</html>