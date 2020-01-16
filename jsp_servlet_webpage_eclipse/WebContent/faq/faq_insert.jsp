<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Faq_DAO, dto.Faq_DTO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Faq_DAO dao = new Faq_DAO();
	
	String faq_no 		= dao.getFaqNo();	
	String question 	= request.getParameter("title");	
	String answer		= request.getParameter("cont");
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();

	Faq_DTO faq_dto = new Faq_DTO(faq_no, question, answer, reg_id, reg_date);
	int result = dao.insertFaq(faq_dto);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%}else{%>
				alert("정상처리 되지 못했습니다~");
			<%}%>
			location.href = "faq_r.jsp";
		</script>
	</head>
</html>