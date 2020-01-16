<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function save(){
		if(member.t_name.value == ""){
			alert("이름 입력하세요.");
			member.t_name.focus();
			return;
		}
		if(member.t_kor.value == ""){
			alert("국어 입력하세요.");
			member.t_kor.focus();
			return;
		}	
		if(member.t_eng.value == ""){
			alert("영어 입력하세요.");
			member.t_eng.focus();
			return;
		}	
		if(member.t_mat.value == ""){
			alert("수학 입력하세요.");
			member.t_mat.focus();
			return;
		}	
		var form = document.member;
		form.action="/ScoreSave";
		form.method="post";
		form.submit();
	}
</script>
<body>
<form name="member">
	성명 : <input type="text" name="t_name"><br>
	국어 : <input type="text" name="t_kor"><br>
	영어 : <input type="text" name="t_eng"><br>
	수학 : <input type="text" name="t_mat"><br><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="입력" onClick="save()">
	<input type="button" value="List" onClick="location.href='/ScoreList'">
</form>
</body>
</html>