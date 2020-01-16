<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function save(){
		var form = document.mem;
		//form.action="testjsp_2.jsp";
		form.action="/memberSave";
		form.method="post";
		form.submit();
	}
</script>
</head>
<body>
<form name="mem">
아디 	: <input type="text" name="t_id"><br>
성명  : <input type="text" name="t_name"><br>
지역  : <input type="text" name="t_area"><br>
나이  : <input type="text" name="t_age"><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="save" onClick="save()">
</form>
</body>
</html>