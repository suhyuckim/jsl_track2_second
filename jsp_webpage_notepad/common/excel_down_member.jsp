<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp" %>
<%@ include file="/common/sessionCheckManager.jsp" %>
<%@ page import="java.util.*,dao.Manager_DAO,dto.Member_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Manager_DAO dao   = new Manager_DAO();
	String selValue   = request.getParameter("t_sel"); 
	String selValue2  = request.getParameter("t_sel2");
	String txtValue   = CommonUtil.checkNull(request.getParameter("t_search"));
	String checkValue = request.getParameter("t_check");
	if(selValue == null){
		selValue  = "all";		
		txtValue  = "";
		selValue2 = "";
	}	
	if(checkValue == null){
		checkValue = "reg_date";
	}
		ArrayList<Member_DTO> dtos = dao.getListMember(selValue, selValue2, txtValue, checkValue);
%>
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
<%   
	for(int k = 0 ; k < dtos.size() ; k++ )	{    
%>	
	<tr>
		<td style="text-align:center"><%=dtos.get(k).getId()%></td>
		<td style="text-align:center"><%=dtos.get(k).getName()%></td>
		<td style="text-align:center"><%=dtos.get(k).getPhone_1()%>-<%=dtos.get(k).getPhone_2()%>-<%=dtos.get(k).getPhone_3()%></td>
		<td style="text-align:center"><%=dtos.get(k).getEmail_1()%>@<%=dtos.get(k).getEmail_2()%></td>
		<td style="text-align:center"><%=dtos.get(k).getArea()%></td>
		<td style="text-align:center"><%=dtos.get(k).getReg_date()%></td>
		<td style="text-align:center">
<%
			if(!dtos.get(k).getStatus().equals("-")) out.print("<p style='color:red'>탈퇴</p>");
			else out.print("-");
%>					

		</td>
	</tr>
<%   
	}
%>	
	
    </table>

</body>
</html>
