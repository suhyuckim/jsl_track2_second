<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수목록 조회</title>
<script>
	function goWrite(){
		var form = document.member;
		form.action="/ScoreWriteForm";
		form.method="post";
		form.submit();
	}
</script>
</head>
<body>
<form name="member">
	<table>
		<tr>
			<td>아디</td>
			<td>성명</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>총점</td>
			<td>평균</td>
		</tr>
<c:if test="${empty t_dtos}">
	<td colspan="7">등록된 내용이 없습니다.</td>
</c:if>
<c:forEach items="${t_dtos}" var="arr">
	<tr>
		<td><a href="/ScoreView?t_id=${arr.getId()}">${arr.getId()}</a></td>
		<td>${arr.getName()}</td>
		<td>${arr.getKor()}</td>
		<td>${arr.getEng()}</td>
		<td>${arr.getMat()}</td>
		<td>${arr.getTotal()}</td>
		<td>${arr.getAva()}</td>
	</tr>
</c:forEach>
	</table>
	<input type="button" value="등록" onClick="goWrite()">
</form>
</body>
</html>