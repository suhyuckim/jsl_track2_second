<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.Smember_DTO" %> 
<%
	ArrayList<Smember_DTO> dtos = (ArrayList<Smember_DTO>)request.getAttribute("t_dtos");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 조회</title>
<script>
	function goWrite(){
		var form = document.mem;
//		form.action = "/servletFolder/member_r.jsp";
		form.action = "/MemberWriteForm";
		form.method = "post";
		form.submit();
	}
</script>
</head>
<body>
<form name="mem">
	<table>
		<tr>
			<td>아디</td>
			<td>성명</td>
			<td>지역</td>
			<td>나이</td>
		</tr>
<%for(int k=0; k<dtos.size(); k++){%>
		<tr>
			<td><%=dtos.get(k).getId()%></td>
			<td><%=dtos.get(k).getName()%></td>
			<td><%=dtos.get(k).getArea()%></td>
			<td><%=dtos.get(k).getAge()%></td>
		</tr>
<%}%>		
	</table>
	<input type="button" value="등록" onClick="goWrite()">
</form>
</body>
</html>