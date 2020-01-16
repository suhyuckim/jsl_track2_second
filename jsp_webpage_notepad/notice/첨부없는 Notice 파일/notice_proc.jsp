<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO,common.CommonUtil,dto.Notice_DTO"%>
<%@ include file="/common_session_info.jsp"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	String work_gubun 	= request.getParameter("t_work_gubun");	
	int result = 0;
	String notice_no;
	String msg = "";
	
	String title 				= request.getParameter("t_title");
	if(title != null) title 	= title.replaceAll("\'","\''");
	String content 				= request.getParameter("t_content");
	if(content != null) content = content.replaceAll("\'","\''");
	String file_name_1  		= "";
	String reg_id 				= sessionId;
	String reg_date 			= CommonUtil.getToday();
	
	if(work_gubun.equals("insert")){		
		notice_no 	= dao.getNoticeNo();	
		reg_date 	= CommonUtil.getToday();		
		Notice_DTO notice_dto = new Notice_DTO(notice_no, title, content, file_name_1, reg_id, reg_date, 0);
		result = dao.insertNotice(notice_dto);
		msg = "등록";
		
	}else if(work_gubun.equals("update")){
		notice_no = request.getParameter("t_notice_no");	
		result	  = dao.updateNotice(notice_no, title, content, reg_id, reg_date);
		msg = "수정";
		
	}else if(work_gubun.equals("delete")) {
		notice_no = request.getParameter("t_notice_no");		
		result 	  = dao.deleteNotice(notice_no);
		msg = "삭제";		
	}
	// int result = dao.insertNotice(notice_no, title, content, reg_id, reg_date);	
%>
<html>
	<head>
		<script>
			<% if(result > 0){ %>
				alert("<%=msg%>되었습니다~");
			<% }else{ %>
				alert("<%=msg%>처리 되지 못했습니다~");
			<% } %>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>