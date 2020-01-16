<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao 	 = new Notice_DAO();	
	String event_no  = request.getParameter("t_event_no");		
	int result 		 = dao.deleteEvent(event_no);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("삭제 되었습니다.~");
			<%}else{%>
				alert("삭제 처리 되지 못했습니다~");
			<%}%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>