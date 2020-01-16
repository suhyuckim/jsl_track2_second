<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Qanda_DAO,common.CommonUtil,dto.Qanda_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%@ include file="/common/sessionCheckMember.jsp"%>

<%
	request.setCharacterEncoding("UTF-8");
	Qanda_DAO dao = new Qanda_DAO();
	
	String qanda_no 	= request.getParameter("t_qanda_no");		
	String answer 	    = request.getParameter("t_answer");
	String reg_date_a 	= CommonUtil.getToday();
	String status 		= "y";
	
	int result = dao.updateAnswer(qanda_no, answer, reg_date_a, status);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("답변 저장 되었습니다.~");
			<%}else{%>
				alert("답변 처리 되지 못했습니다~");
			<%}%>
			location.href = "qanda_r.jsp";
		</script>
	</head>
</html>