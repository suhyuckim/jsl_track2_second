<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Freeboard_DAO, dto.Freeboard_DTO, common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Freeboard_DAO dao = new Freeboard_DAO();
	
	String freeboard_no 	= request.getParameter("t_freeboard_no");
	String title 			= request.getParameter("t_title");	
	String content 			= request.getParameter("t_content");
	String reg_id 			= request.getParameter("t_name");
	String reg_date 		= CommonUtil.getToday();
	
	int result = dao.updateFreeboard(freeboard_no, title, content, reg_id, reg_date);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("수정 되었습니다.~");
			<%}else{%>
				alert("수정 처리 되지 못했습니다~");
			<%}%>
			location.href = "freeboard_r.jsp";
		</script>
	</head>
</html>