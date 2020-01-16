<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Event_M_DAO, dto.Event_M_DTO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Event_M_DAO dao 	= new Event_M_DAO();
	
	String event_no 	= request.getParameter("t_event_no");
	String event_m_no 	= dao.getMaxId(event_no);	
	String title 		= request.getParameter("t_title");	
	String content		= request.getParameter("t_cont");
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();

	Event_M_DTO event_m_dto = new Event_M_DTO(event_no, event_m_no, title, content, reg_id, reg_date, "");
	int result = dao.insertEvent_M(event_m_dto);	 
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%}else{%>
				alert("정상처리 되지 못했습니다~");
			<%}%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>