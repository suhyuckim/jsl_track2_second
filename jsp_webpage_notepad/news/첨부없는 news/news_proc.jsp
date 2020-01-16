<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.News_DAO,common.CommonUtil,dto.News_DTO"%>
<%
	request.setCharacterEncoding("UTF-8");
	News_DAO dao = new News_DAO();
	String work_gubun 	= request.getParameter("t_work_gubun");	
	int result = 0;
	String news_no;
	String msg = "";
	
	String title 				= request.getParameter("t_title");
	if(title != null) title 	= title.replaceAll("\'","\''");
	String content 				= request.getParameter("t_content");
	if(content != null) content = content.replaceAll("\'","\''");	
	String reg_id 				= "관리자";	
	String reg_date 			= CommonUtil.getToday();
	
	if(work_gubun.equals("insert")){		
		news_no 	= dao.getNewsNo();	
		reg_date 	= CommonUtil.getToday();		
		News_DTO news_dto = new News_DTO(news_no, title, content, reg_id, reg_date, 0);
		result = dao.insertNews(news_dto);
		msg = "등록";		
	
	}else if(work_gubun.equals("update")){
		news_no = request.getParameter("t_news_no");	
		result	  = dao.updateNews(news_no, title, content, reg_id, reg_date);
		msg = "수정";
		
	}else if(work_gubun.equals("delete")) {
		news_no = request.getParameter("t_news_no");		
		result 	  = dao.deleteNews(news_no);
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
			location.href = "news_r.jsp";
		</script>
	</head>
</html>