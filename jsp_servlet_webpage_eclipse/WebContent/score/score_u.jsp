<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function update(){
		var form = document.member;
		form.action="/ScoreUpdate";
		form.method="post";
		form.submit();
	}
</script>
</head>
<body>
<form name="member">
	<input type="hidden" name="t_id" value="${t_dto.getId()}">
	아디 : ${t_dto.getId()}<br>
	성명 : <input type="text" name="t_name" value="${t_dto.getName()}"><br>
	국어 : <input type="text" name="t_kor" value="${t_dto.getKor()}"><br>
	영어 : <input type="text" name="t_eng" value="${t_dto.getEng()}"><br>
	수학 : <input type="text" name="t_mat" value="${t_dto.getMat()}"><br>
	총점 : ${t_dto.getTotal()}<br>
	평균 : ${t_dto.getAva()}<br><br>
	<input type="button" value="update" onClick="update()">
	<input type="button" value="List" onClick="location.href='/ScoreList'">
</form>
</body>
</html>