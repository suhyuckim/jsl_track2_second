<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>InputData</title>
</head>

<body>
<%
	if(id.equals("101")){
		out.print("ȯ���մϴ�~");
	} else {
		out.print("�ƴϴ�~");
	}
%>

</body>

</htm>
