<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="black">
<center>
	<table width="1000" height="5">
		<tr height="50">
			<!-- 로고 이미지 -->
			<td align="left" width="500">
			<a href="ShoppingMain.jsp">
				<img alt="" src="img/logo.JPG" border="0" align="left">
			</a>
			</td>
		<!-- 회원 로그인 여부 -->
		<!-- 세션값이 없으면!!! [로그인] [회원가입] 버튼 보이게 -->
		<c:if test="${mbean==null }">
			<td align="right" width="300">
				<input type="button" onclick="location.href='login.do'" value="로그인">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="location.href='joinform.do'" value="회원가입">
			</td>	
		</c:if>
		<!-- 세션값이 있다면(로그인이 되었다면 ) ???님 환영합니다. [로그아웃] 버튼 보이게-->
		<c:if test="${mbean!=null }">
			<td align="right" width="300">
				<font size="3" color="white">${mbean.memid}님 환영 합니다.</font>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="location.href='logout.do'" value="로그아웃">
			</td>
		</c:if>
		<!-- 검색 -->
		<form action="mainsearch.do" method="post">
			<td align="right" width="200">
				<input type="text" name="msearch" size="10">
				<input type="submit" value="검색">
			</td>
		</form>
		</tr>
	</table>
	<!-- 메뉴들이 나타나는 공간 -->
	<table width="1000" height="5">
		<tr>
			<td align="center" bgcolor="black">
				<a href="sujak.do">
					<img src="img/sujak.JPG">
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="jundong.do">
					<img src="img/jundong.JPG">
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="stanlyinfo.do">
					<img src="img/stanlyinfo.JPG">
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="tooluse.do">
					<img src="img/tooluse.JPG">
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="download.do">
					<img src="img/download.JPG">
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="board.do">
					<img src="img/gogak.JPG">
				</a>
			</td>
			
		</tr>
	</table>
</center>

</body>
</html>