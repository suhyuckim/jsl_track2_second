<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Qanda_DAO,common.CommonUtil,dto.Qanda_DTO"%>
<%@ include file="/common_session_info.jsp"%>	
<%@ include file="/common/sessionCheckMember.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Qanda_DAO dao = new Qanda_DAO();
	
	String qanda_no 	          = dao.getQandaNo();	
	String title 		          = request.getParameter("t_title");	
	if(title != null) title 	  = title.replaceAll("\'","\''");
	String question 		      = request.getParameter("t_content");
	if(question != null) question = question.replaceAll("\'","\''");
	String user_id 				  = sessionId;
	String secret				  = request.getParameter("t_secret");
	String reg_date_q 			  = CommonUtil.getToday();
	int hit 					  = 0;

	Qanda_DTO qanda_dto = new Qanda_DTO(qanda_no, title, question, "", user_id, reg_date_q,"","",secret,hit,"");
	int result = dao.insertQanda(qanda_dto);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%}else{%>
				alert("정상처리 되지 못했습니다~");
			<%}%>
			location.href = "qanda_r.jsp";
		</script>
	</head>
</html>