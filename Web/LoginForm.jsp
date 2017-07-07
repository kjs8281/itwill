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
<!-- ShoppingController.java의 loginProc메소드내에서 else구문을 실행하여...
로그인시 입력한! 아이디와 패스워드가 DB에 없다면 다시 LoginForm.jsp페이지로 이동 해오면서...
login변수를 전달해 오므로 아래의 소스에서 login변수를 전달받는다. -->
<!-- 만약 로그인시 입력한! 아이디와 패스워드가 DB에 없다면.. -->
<c:if test="${login == 1 }">
	<script type="text/javascript">
		alert("아이디/패스워드를 확인 하세요");
	</script>
</c:if>
<center>
	<h2><font color="yellow">회원 로그인</font></h2>
	<form action="Loginproc.do" method="post">
		<table width="500">
			<tr height="40">
				<td width="200" align="center"><font size="2" color="white">아이디</font></td>
				<td width="200" align="center"><input type="text" name="memid" size="30"></td>		
			</tr>
			<tr height="40">
				<td width="200" align="center"><font size="2" color="white">패스워드</font></td>
				<td width="200" align="center"><input type="password" name="mempasswd1" size="30"></td>		
			</tr>
			<tr height="40">
				<td colspan="2" align="center">
					<input type="submit" value="로그인"> &nbsp;&nbsp;&nbsp;&nbsp;
					<font size="2" color="white">아이디 / 패스워드찾기</font>
				</td>
			</tr>
		</table>
		
	</form>
</center>

</body>
</html>