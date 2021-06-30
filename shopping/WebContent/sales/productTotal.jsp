<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품별 판매 현황</title>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	   <script type="text/javascript">
	      google.charts.load('current', {'packages':['corechart']});
	      google.charts.setOnLoadCallback(drawVisualization);
	   
	      function drawVisualization() { 
	         var data = google.visualization.arrayToDataTable(${googleList});
	         var options = {
	               title : '상품별 판매 현황',
	               vAxis: {title: '금액 및 수량'},
	               hAxis: {title: '상품'}, 
	               seriesType: 'bars',
	               series: {5: {type: 'line'}}
	            };
	         
	         var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
	         chart.draw(data, options);
	      }
	   </script>
</head>
<body>
<div id="chart_div" style="width:900px; height: 500px;"></div>
상품별 판매 현황
<table border = 1 width = "600">
	<tr><td style="text-align:right" width = "200">상품</td><td style="text-align:right" width = "100">총 판매개수</td><td style="text-align:right" width = "300">총 판매금액</td></tr>
	<c:forEach items="${list }" var = "dto">
		<tr><td style="text-align:right">${dto.prodName }</td>
			<td style="text-align:right">${dto.totQty }</td>
			<td style="text-align:right">${dto.totPrice }</td></tr>
	</c:forEach>

</table>
</body>
</html>