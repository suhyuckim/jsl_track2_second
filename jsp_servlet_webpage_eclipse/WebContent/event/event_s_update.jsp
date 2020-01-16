<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO, dto.Event_DTO, dao.Event_M_DAO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Event_M_DAO dao 	= new Event_M_DAO();	
	String event_no  	= request.getParameter("t_event_no");	
	String[] reg_id 	= request.getParameterValues("t_reg_id");		
	int result = 0; 
		
	for(int k=0; k<reg_id.length; k++){
		result = dao.updateStatus_m(event_no, reg_id[k]);
	}
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("당첨 수정 되었습니다.~");
			<%}else{%>
				alert("당첨 처리 되지 못했습니다~");
			<%}%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>