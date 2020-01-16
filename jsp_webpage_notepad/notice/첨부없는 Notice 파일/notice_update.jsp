<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO,common.CommonUtil,dto.Notice_DTO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	
	String notice_no 	= request.getParameter("t_notice_no");	
	String title 		= request.getParameter("t_title");	
	String content 		= request.getParameter("t_content");
	String file_name_1 	= "";
	String reg_id 		= "관리자";	
	String reg_date 	= CommonUtil.getToday();
	
	int result = dao.updateNotice(notice_no, title, content, reg_id, reg_date);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("수정 되었습니다.~");
			<%}else{%>
				alert("수정 처리 되지 못했습니다~");
			<%}%>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>