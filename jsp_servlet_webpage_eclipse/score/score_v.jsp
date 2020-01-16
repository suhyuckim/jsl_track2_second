<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function ScoreDelete(){
		var yn = confirm("정말로 삭제 할로고요??");
		if(yn){
			var form = document.member;
			form.action="/ScoreDelete?t_id=${t_dto.getId()}";
			form.method="post";
			form.submit();
		}
	}
</script>
</head>
<body>
<form name="member">
	아디 : ${t_dto.getId()}<br>
	성명 : ${t_dto.getName()}<br>
	국어 : ${t_dto.getKor()}<br>
	영어 : ${t_dto.getEng()}<br>
	수학 : ${t_dto.getMat()}<br>
	총점 : ${t_dto.getTotal()}<br>
	평균 : ${t_dto.getAva()}<br>
	<input type="button" value="List" onClick="location.href='/ScoreList'">
	<input type="button" value="Update" onClick="location.href='/ScoreUpdateForm?t_id=${t_dto.getId()}'">
	<input type="hidden" value="Delete" onClick="location.href='/ScoreDelete?t_id=${t_dto.getId()}'">
	<input type="button" value="Delete" onClick="ScoreDelete()">
</form>
</body>
</html>