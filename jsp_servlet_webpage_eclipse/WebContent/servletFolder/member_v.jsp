<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="mem">
	아디 	: ${t_dto.getId()}<br>
	성명  : ${t_dto.getName()}<br>
	지역  : ${t_dto.getArea()}<br>
	나이  : ${t_dto.getAge()}<br>
	<input type="button" value="List"   onClick="location.href='/memberList'">
	<input type="button" value="Update" onClick="location.href='/MemberUpdateForm?t_id=${t_dto.getId()}'">
	<input type="button" value="Delete" onClick="location.href='/MemberDelete?t_id=${t_dto.getId()}'">
</form>
</body>
</html>