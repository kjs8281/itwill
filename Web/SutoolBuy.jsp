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
<h2><font color="yellow">결제 완료 페이지</font></h2>
<table width="800">
	<tr height="40">
		<td align="center" width="200"><font size="2" color="white">상품이미지</font></td>
		<td align="center" width="200"><font size="2" color="white">상품명</font></td>
		<td align="center" width="100"><font size="2" color="white">상품가격</font></td>
		<td align="center" width="100"><font size="2" color="white">상품수량</font></td>
		<td align="center" width="200"><font size="2" color="white">상품총금액</font></td>
	</tr>
<!-- 총결제 금액 저장 변수 -->
<c:set var="total" value="${0 }"/>	

<!-- 카트에 담아서 구매하지 않고!! 즉시 구매하기 -->
<c:if test="${sessionScope.cart == null }">
	<tr height="80">
		<td align="center"><img src="img/${subean.suimg }" width="180" height="70"></td>
		<td align="center"><font size="2" color="white">${subean.suname }</font></td>
		<td align="center"><font size="2" color="white">${subean.suprice }원</font></td>
		<td align="center"><font size="2" color="white">${subean.suqty }개</font></td>
		<td align="center"><font size="2" color="white">${subean.suprice*subean.suqty }원</font></td>
	</tr>
</c:if>
<!-- 총 결제 금액 : 상품가격 * 상품 수량 -->
<c:set var="total" value="${subean.suprice*subean.suqty }"/>
	<tr height="70">
		<td align="center" colspan="5"><font size="5" color="red">
		총 결제 금액은 = ${total }원 입니다.</font></td>
	</tr>
	<tr height="70">
		<td align="center" colspan="5">
			<input type="button" onclick="location.href='index.do', alert('계산 완료')" value="계산 완료하기"><!-- ShoppingMain.jsp로 -->
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" onclick="location.href='index.do'" value="취소하기">
		</td>
	</tr>
</table>
</center>

</body>
</html>