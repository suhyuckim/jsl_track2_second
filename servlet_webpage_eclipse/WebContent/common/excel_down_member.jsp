<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp" %>
<%@ include file="/common/sessionCheckManager.jsp" %>
<%
	response.setContentType("application/vnd.ms-excel;charset=utf-8");
	response.setHeader("Content-Disposition","attachment;filename=member.xls");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
</head>
<body >
<br><br>
  
<table width="900" border="1">
   <TR align="center" height="50">
		<th colspan="7"> 회원현황</th>
   </TR> 
   <TR align="center" bgcolor="#EBEC96" height="25">
		<th>ID</th>
		<th>성명</th>
		<th>연락처</th>
		<th>e-mail</th>
		<th>지역</th>
		<th>가입일</th>
		<th>탈퇴유무</th>
   </TR> 
   <c:forEach items="${t_dtos}" var="dtos">	
	<tr>
		<td style="text-align:center">${dtos.getId()}</td>
		<td style="text-align:center">${dtos.getName()}</td>
		<td style="text-align:center">${dtos.getPhone_1()}-${dtos.getPhone_2()}-${dtos.getPhone_3()}</td>
		<td style="text-align:center">${dtos.getEmail_1()}@${dtos.getEmail_2()}</td>
		<td style="text-align:center">${dtos.getArea()}</td>
		<td style="text-align:center">${dtos.getReg_date()}</td>
		<td style="text-align:center">
		<c:if test="${dtos.getStatus() eq '-'}">-</c:if>
		<c:if test="${dtos.getStatus() ne '-'}"><p style='color:red'>탈퇴</p></c:if>
		</td>
	</tr>
	</c:forEach>
    </table>
</body>
</html>