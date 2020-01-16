<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Freeboard_DAO, dto.Freeboard_DTO, common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>	
<%
	request.setCharacterEncoding("UTF-8");
	Freeboard_DAO dao = new Freeboard_DAO();
	
	String freeboard_no = dao.getFreeboardNo();	
	String title 		= request.getParameter("t_title");	
	String content 		= request.getParameter("t_content");
	String reg_id 		= request.getParameter("t_name");	
	String reg_date 	= CommonUtil.getToday();
	int hit 			= 0;
	String password 	= request.getParameter("t_pw");
	String status		= "n";

	if(!sessionId.equals("")){
		status = "y";		
	}
	Freeboard_DTO freeboard_dto = new Freeboard_DTO(freeboard_no, title, content, reg_id, reg_date, hit, password, status);
	
	int result = dao.insertFreeboard(freeboard_dto);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%}else{%>
				alert("등록처리 되지 못했습니다~");
			<%}%>
			location.href = "freeboard_r.jsp";
		</script>
	</head>
</html>