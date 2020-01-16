<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO, dto.Event_DTO, dao.Event_M_DAO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Event_M_DAO dao 	= new Event_M_DAO();	
	String event_no  	= request.getParameter("t_event_no");	
	String title  		= request.getParameter("t_title");	
	String content  	= request.getParameter("t_cont");	
	String reg_id		= sessionId;
	String event_m_no   = dao.getEvent_m_View(event_no, sessionId);		
	int result 		 	= dao.updateEvent_m(event_no, event_m_no, title, content, reg_id);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("응모 수정 되었습니다.~");
			<%}else{%>
				alert("응모 수정 처리 되지 못했습니다~");
			<%}%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>