<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function update(){
		var form = document.mem;
		//form.action="testjsp_2.jsp";
		form.action="/MemberUpdate";
		form.method="post";
		form.submit();
	}
</script>
</head>
<body>
<form name="mem">
	<input type="hidden" name="t_id" value="${t_dto.getId()}">
	아디 	: ${t_dto.getId()}<br>
	성명  : <input type="text" name="t_name" value="${t_dto.getName()}"><br>
	지역  : <input type="text" name="t_area" value="${t_dto.getArea()}"><br>
	나이  : <input type="text" name="t_age"  value="${t_dto.getAge()}"><br><br>
	<input type="button" value="update" onClick="update()">
	<input type="button" value="List" onClick="location.href='/memberList'">
</form>
</body>
</html>