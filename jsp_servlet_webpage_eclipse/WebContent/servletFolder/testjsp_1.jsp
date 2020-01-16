<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goPage(){
		var form = document.mem;
		//form.action="testjsp_2.jsp";
		form.action="/test_1";
		form.method="post";
		form.submit();
	}
</script>
</head>
<body>
<form name="mem">
	<input type="text" name="t_name">
	<input type="button" value="click" onClick="goPage()">
</form>
</body>
</html>