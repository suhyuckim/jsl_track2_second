<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Event_M_DAO"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Event_M_DAO dao 	= new Event_M_DAO();	
	String event_no  	= request.getParameter("t_event_no");		
	String event_m_no   = dao.getEvent_m_View(event_no, sessionId);		
	int result 		 	= dao.deleteEvent_m(event_no, event_m_no);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("응모 취소 되었습니다.~");
			<%}else{%>
				alert("응모 취소 되지 못했습니다~");
			<%}%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>