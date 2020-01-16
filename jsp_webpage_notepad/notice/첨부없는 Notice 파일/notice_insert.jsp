<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO,common.CommonUtil,dto.Notice_DTO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	
	String notice_no 	= dao.getNoticeNo();	
	String title 		= request.getParameter("t_title");	
	String content 		= request.getParameter("t_content");
	String file_name_1 	= "";
	String reg_id 		= "관리자";	
	String reg_date 	= CommonUtil.getToday();
	int hit 			= 0;
	
	// int result = dao.insertNotice(notice_no, title, content, reg_id, reg_date);

	Notice_DTO notice_dto = new Notice_DTO(notice_no, title, content, file_name_1, reg_id, reg_date, hit);
	int result = dao.insertNotice(notice_dto);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%}else{%>
				alert("정상처리 되지 못했습니다~");
			<%}%>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>