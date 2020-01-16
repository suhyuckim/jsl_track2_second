<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Qanda_DAO,common.CommonUtil,dto.Qanda_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%@ include file="/common/sessionCheckMember.jsp"%>

<%
	request.setCharacterEncoding("UTF-8");
	Qanda_DAO dao = new Qanda_DAO();
	
	String qanda_no 	= request.getParameter("t_qanda_no");	
	String title 		= request.getParameter("t_title");	
	String question 	= request.getParameter("t_content");
	String user_id 		= sessionId;
	String reg_date_q 	= CommonUtil.getToday();
	
	int result = dao.updateQanda(qanda_no, title, question, user_id, reg_date_q);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("수정 되었습니다.~");
			<%}else{%>
				alert("수정 처리 되지 못했습니다~");
			<%}%>
			location.href = "qanda_r.jsp";
		</script>
	</head>
</html>