<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function cart(){
		//히든에 선언한 아디를 통해서 데이터를 읽어 드림
		var qtyid = document.getElementById("qtyid").value;
		var sunoid = document.getElementById("sunoid").value;
		var suimgid = document.getElementById("suimgid").value;
		var supriceid = document.getElementById("supriceid").value;
		var sunameid = document.getElementById("sunameid").value;
		
		//하나의 url을 만들어서 해당하는 카트담기 sutoolcart.do호출
		var url = "sutoolcart.do?suno="+sunoid+"&suimg="+suimgid+
				"&suprice="+supriceid+"&suname="+sunameid+"&suqty="+qtyid;
		location.href=url;
	}
</script>

</head>
<body bgcolor="white">
<center>
	<h2><font color="yellow">수작업 공구 구매</font></h2>
	<!-- 구매하기 버튼을눌렀을때... 요청!! -->
	<form action="sutoolbuy.do" method="post">
		<table width="800" border="0" align="center">
			<tr align="center">
				<td rowspan="4" width="300">
					<img alt="" src="img/${sbean.suimg }" width="300">
				</td>
				<td align="center" width="100">
					<font size="2" color="white">공구 이름</font>
				</td>
				<td align="center" width="150">
					<font size="2" color="white">${sbean.suname }</font>
				</td>
			</tr>
			<tr>
				<td align="center">
					<select name="suqty" id="qtyid">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr align="center">
				<td align="center" width="100">
					<font size="2" color="white">판매금액</font>
				</td>
				<td align="center" width="150">
					<font size="2" color="white">${sbean.suprice }원</font>
				</td>
			</tr>
			<tr align="center">
				<td align="center" colspan="2">
					<!-- 카트 담기 또는 구매하기 버튼을 눌렀을때... 히든으로 넘길 값들 -->
					<input type="hidden" name="suno" id="sunoid" value="${sbean.suno }"><!-- 수작업공구 상품넘버 -->
					<input type="hidden" name="suimg" id="suimgid" value="${sbean.suimg }"><!-- 수작업공구 이미지명 -->
					<input type="hidden" name="suprice" id="supriceid" value="${sbean.suprice }"><!-- 수작업공구 가격 -->
					<input type="hidden" name="suname" id="sunameid" value="${sbean.suname }"><!-- 수작업공구 이름 -->
					
					<input type="button" onclick="location.href='sujak.do'" value="목록보기">
					<input type="button" onclick="cart()" value="카트담기">
					<input type="submit" value="구매하기">
				</td>
			</tr>
		</table>
	</form>
	<p>
	<hr color="yellow">
	<font size="2" color="white">
		<b>상품 상세보기</b>
		<p>${sbean.suinfo }</p>
	</font>
</center>

</body>
</html>