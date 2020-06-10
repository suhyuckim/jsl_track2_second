<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update Form</title>
<script type="text/javascript">
	function update(){
		if(mem.t_name.value==""){
			alert("이름을 입력해주세요.");
			mem.t_name.focus();
			return;
		}
		if(mem.t_age.value==""){
			alert("나이를 입력해주세요.");
			mem.t_age.focus();
			return;
		}
		var ch = isNaN(mem.t_age.value);
		if(ch){
			alert("나이는 숫자만 입력해주세요.");
			mem.t_age.focus();
			return;
		}
		mem.action = "/MemberUpdate";
		mem.method = "post";
		mem.submit();
	}
</script>
</head>
<body>
<form name="mem"> 
	<input type="hidden" name="t_id" value="${t_dto.getId()}">
	<table border="1">
		<tr>
			<td width="100" align="center" >ID</td>
			<td width="300" align="left">${t_dto.getId()}</td>
		</tr>
		
		<tr>
			<td width="100" align="center">NAME</td>
			<td width="300" align="left">
				<input type="text" name="t_name" value="${t_dto.getName()}" size="10">
			</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AREA</td>
			<td width="300" align="left">
				<select name="t_area" style="width:100px; height:25px;">
					<option value="서울" <c:if test="${t_dto.getArea() eq '서울'}">selected</c:if>>서울</option>
					<option value="대전" <c:if test="${t_dto.getArea() eq '대전'}">selected</c:if>>대전</option>
					<option value="부산" <c:if test="${t_dto.getArea() eq '부산'}">selected</c:if>>부산</option>
				</select>
			</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AGE</td>
			<td width="300" align="left">
				<input type="text" name="t_age" value="${t_dto.getAge()}" size="10">
			</td>		
		</tr>
	</table>
</form>	
	<br>
	<table width="420px" border="0">
		<tr>
			<td width="100" align="center">
				<input type="button" onClick="history.back()" value="이 전">
				<input type="button" onClick="update()" value="저 장">
			</td>		
		</tr>
	</table>
</body>
</html>