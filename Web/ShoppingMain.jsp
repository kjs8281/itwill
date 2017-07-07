<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl core태그 사용을 위한 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="black">
<center>
	<!-- 파라미터를 통하여 값(left값 센터값이 있느냐에 따라화면 구성을 틀리게) -->
	<c:set var="center" value="${center }"/>
	<c:set var="left" value="${left }"/>
	
	<!-- 만약 처음 실행하면 자동으로 center값이 null이기에 초기값으로 Center.jsp값을 넣어줌 -->
	<c:if test="${center==null }">
		<c:set var="center" value="Center.jsp"/>
	</c:if>
	
	<table width="1000">
		<!-- Top 설정 부분 -->
		<tr height="80" align="center">
			<td align="center" colspan="2"><jsp:include page="Top.jsp"/></td>
		</tr>
	
	<!-- 센터 설정 -->
	<tr height="470" align="center" valign="top">
		<c:if test="${left==null }">
			<td align="center">
				<jsp:include page="${center }"/>
			</td>
		</c:if>
		<c:if test="${left!=null }">
			<td align="center" width="200">
				<jsp:include page="${left }"/>
			</td>
			<td align="center" width="800">
				<jsp:include page="${center }"/>
			</td>
		</c:if>
	</tr>
	
	<!-- Bottom 부분 설정 -->
	<tr height="80" align="center">
		<td align="center" colspan="2"><jsp:include page="Bottom.jsp"/></td>
	</tr>
	
	</table>
</center>
</body>
</html>