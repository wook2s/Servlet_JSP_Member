<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="sec01.ex01.*"
    isELIgnored="false"   
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>    
<%request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<c:choose>
		<c:when test="${msg=='addMember'}">
			<script type="text/javascript">
			window.onload = function(){
				alert("회원을 등록하였습니다.");
			}
			</script>
		</c:when>
		<c:when test="${msg=='modified'}">
			<script type="text/javascript">
			window.onload = function(){
				alert("회원을 수정함.");
			}
			</script>
		</c:when>
		<c:when test="${msg=='deleted'}">
			<script type="text/javascript">
			window.onload = function(){
				alert("회원 삭제함.");
			}
			</script>
		</c:when>
	</c:choose>



<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p align="center">Member information</p>
	<table border="1">
	<tr style="background:yellow">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="7%">이름</td>
		<td width="7%">이메일</td>
		<td width="7%">가입일</td>
		<td width="7%">수정</td>
		<td width="7%">삭제</td>
	</tr>
	<c:choose>
		<c:when test="${memberList == null }">
			<tr>
				<td colspan=5>등록된 회원이 없습니다.</td>
			</tr>
		</c:when>
		<c:when test="${memberList != null }">
			<c:forEach var="mem" items="${memberList }">
				<tr>
					<td>${mem.id }</td>		
					<td>${mem.pwd }</td>		
					<td>${mem.name }</td>		
					<td>${mem.email }</td>		
					<td>${mem.joinDate }</td>		
					<td><a href="${contextPath}/member/modMemberForm.do?id=${mem.id}">수정</a></td>		
					<td><a href="${contextPath}/member/delMember.do?id=${mem.id}">삭제</a></td>		
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
	
	<a href="${contextPath}/member/memberForm.do">회원 가입하기</a>
	
</body>
</html>