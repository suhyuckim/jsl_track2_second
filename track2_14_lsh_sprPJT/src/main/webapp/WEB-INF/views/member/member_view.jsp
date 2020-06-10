<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member View</title>
<script type="text/javascript">
	function updateForm(){
		mem.action="/MemberUpdateForm";
		mem.method="post";
		mem.submit();
	}
	function deleteMember(){
		mem.action="/MemberDelete";
		mem.method="post";
		mem.submit();
	}
</script>
</head>
<body>
<form name="mem"> 
	<input type="hidden" name="t_id" value="${t_dto.getId()}">
</form>
	<table border="1">
		<tr>
			<td width="100" align="center">ID</td>
			<td width="300" align="left">${t_dto.getId()}</td>
		</tr>
		
		<tr>
			<td width="100" align="center">NAME</td>
			<td width="300" align="left">${t_dto.getName()}</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AREA</td>
			<td width="300" align="left">${t_dto.getArea()}</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AGE</td>
			<td width="300" align="left">${t_dto.getAge()}</td>		
		</tr>
	</table>
	
	<br>
	<table width="420px" border="0">
		<tr>
			<td width="100" align="center">
				<input type="button" onClick="history.back()" value="목 록">
				<input type="button" onClick="updateForm()"   value="수 정">
				<input type="button" onClick="deleteMember()" value="삭 제">
			</td>		
		</tr>
	</table>
</body>
</html>