<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Faq_DAO, dto.Faq_DTO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Faq_DAO dao = new Faq_DAO();
	
	String faq_no 		= request.getParameter("t_faq_no");	
	String question 	= request.getParameter("question");	
	String answer		= request.getParameter("answer");
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();

	int result = dao.updateFaq(faq_no, question, answer, reg_id, reg_date);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("수정 되었습니다.~");
			<%}else{%>
				alert("수정 처리 되지 못했습니다~");
			<%}%>
			location.href = "faq_r.jsp";
		</script>
	</head>
</html>