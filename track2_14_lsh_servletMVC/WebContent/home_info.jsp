<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
- ���� : <%=application.getServerInfo() %>

 - ���� : <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>

 - JSP : <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %> 
</body>
</html>