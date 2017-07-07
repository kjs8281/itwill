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
<!-- ShoppingController안에.. joinProc메소드에서 리턴받아온 result값이 1일때.. -->
<c:if test="${result==1 }">
	<script type="text/javascript">
		alert("해당 아이디는 존재 합니다. 다른 아이디를 넣어주세요");
	</script>
</c:if>
<!-- ShoppingController안에.. joinProc메소드에서 리턴 받아온 result값이 2일떄... -->
<c:if test="${result==2 }">
	<script type="text/javascript">
		alert("패스워드가 틀립니다. 잘 확인 해주세요");
	</script>
</c:if>
<center>
<h2><font color="white">회원 가입</font></h2>
<form action="joinproc.do" method="post">
	<table width="400" border="0" cellspacing="1" cellpadding="4">
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">아이디</font></td>
			<td width="220"><input type="text" name="memid" size="20"></td>
		</tr>
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">패스워드</font></td>
			<td width="220"><input type="password" name="mempasswd1" size="20"></td>
		</tr>
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">패스워드 확인</font></td>
			<td width="220"><input type="password" name="mempasswd2" size="20"></td>
		</tr>
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">이름</font></td>
			<td width="220"><input type="text" name="memname" size="20"></td>
		</tr>
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">전화번호</font></td>
			<td width="220"><input type="text" name="memphone" size="20"></td>
		</tr>
		<tr height="40">
			<td align="right" width="180"><font color="white" size="3">기념일</font></td>
			<td width="220"><input type="date" name="memdate" size="20"></td>
		</tr>
		<tr height="40">
			<td colspan="2" align="center">
				<input type="submit" value="회원가입"> &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="다시작성">
		</tr>
	</table>
	

</form>

</center>

</body>
</html>