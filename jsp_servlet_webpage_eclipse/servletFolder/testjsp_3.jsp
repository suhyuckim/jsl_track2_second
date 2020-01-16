<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
/*
	String name = request.getParameter("t_name");
	String tt_name = (String)request.getAttribute("tt_name");
	Notice_DTO dto = (Notice_DTO)request.getAttribute("t_dto");
	ArrayList<String> arr = (ArrayList<String>)request.getAttribute("t_arr");
*/
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testjsp3.jsp</title>
</head>
<body>
name  : ${tt_name}<br>
title : ${t_dto.getTitle()}
<%-- test3.jsp 	: t_name = <%=name%><br> --%>
<%-- tt_name 	: <%=tt_name%><br> --%>
<%-- notice_no 	: <%=dto.getNotice_no()%><br> --%>
<%-- title 		: <%=dto.getTitle()%><br> --%>
<%-- content 	: <%=dto.getContent()%><br> --%>
<%-- file_name_1 : <%=dto.getFile_name_1()%><br> --%>
<%-- reg_id 	: <%=dto.getReg_id()%><br> --%>
<%-- reg_date 	: <%=dto.getReg_date()%><br> --%>
<%-- hit 		: <%=dto.getHit()%><br> --%>
<%-- arr		: <%=arr.get(0)%>&nbsp;<%=arr.get(1)%> --%>
</body>
</html>