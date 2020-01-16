<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO,dto.Event_DTO,common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	
	String event_no 	= dao.getEventNo();	
	String title 		= request.getParameter("t_title");	
	String content		= request.getParameter("t_cont");
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();
	String reg_start 	= request.getParameter("reg_start");
	String reg_end 		= request.getParameter("reg_end");
	int hit 			= 0;

 	Event_DTO event_dto = new Event_DTO(event_no, title, content, reg_id, reg_date, reg_start, reg_end, hit);
	int result = dao.insertEvent(event_dto);
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