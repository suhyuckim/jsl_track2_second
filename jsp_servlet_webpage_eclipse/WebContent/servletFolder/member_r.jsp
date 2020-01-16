<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 조회</title>
<script>
	function goWrite(){
		var form = document.mem;
//		form.action = "/servletFolder/member_r.jsp";
		form.action = "/MemberWriteForm";
		form.method = "post";
		form.submit();
	}
</script>
</head>
<body>
<form name="mem">
<c:set var="t_value" value="10"/>
1 : ${t_value}<br>
<c:if test="${t_value eq 10}">
	<c:set var="t_value" value="${t_value+1}"/>
</c:if>
2 : ${t_value}<br>
${t_value == 11 ? '맞아' : '안맞아'}

	<table>
		<tr>
			<td>아디</td>
			<td>성명</td>
			<td>지역</td>
			<td>나이</td>
		</tr>
<c:if test="${empty t_dtos}"> 
	<td colspan="4">등록된 내용이 없습니다.</td>
</c:if>
<c:forEach items="${t_dtos}" var="arr">
		<tr>
			<td><a href="/MemberView?t_id=${arr.getId()}">${arr.getId()}</a></td>
			<td>${arr.getName()}</td>
			<td>${arr.getArea()}</td>
			<td>${arr.getAge()}</td>
		</tr>
</c:forEach>
	</table>
	<input type="button" value="등록" onClick="goWrite()">
</form>
</body>
</html>