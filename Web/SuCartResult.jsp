<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2><font color="yellow">카트 내용 확인</font></h2>
<table width="800">
	<tr height="40">
		<td aligh="center" width="200"><font size="2" color="white">상품이미지</font></td>
		<td aligh="center" width="150"><font size="2" color="white">상품명</font></td>
		<td aligh="center" width="100"><font size="2" color="white">상품가격</font></td>
		<td aligh="center" width="100"><font size="2" color="white">상품수량</font></td>
		<td aligh="center" width="150"><font size="2" color="white">상품총금액</font></td>
		<td aligh="center" width="100"><font size="2" color="white">상품취소</font></td>
	</tr>
<c:forEach var="item" items="${sessionScope.cart.itemlist }">
	<tr height="80">
		<td align="center"><img src="img/${item.suimg }" width="150" height="80"></td>
		<td align="center"><font size="2" color="white">${item.suname }</font></td>
		<td align="center"><font size="2" color="white">${item.suprice }원</font></td>
		<td align="center"><font size="2" color="white">${item.suqty }</font></td>
		<td align="center"><font size="2" color="white">${item.suprice*item.suqty }원</font></td>
		<td align="center">
			<input type="button" value="카트삭제" onclick="location.href='sucartdel.do?suno=${item.suno}'">
		</td>
		
	</tr>

</c:forEach>
	<tr height="70">
		<td align="center" colspan="6"><font size="3" color="white">${msg }</font></td>
	</tr>
	<tr height="50">
		<td align="center" colspan="6">
			<input type="button" onclick="location.href='sujak.do'" value="목록보기">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="location.href='sucartbuy.do'" value="계산하기">
		</td>
	</tr>
</table>
</center>

</body>
</html>