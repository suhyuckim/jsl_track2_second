<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.News_DAO,common.CommonUtil"%>
<%@ page import ="java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	News_DAO dao 	 = new News_DAO();
	// String file_dir  = "C:/webserver/webapps/ROOT/file_room/notice/"; 
	String file_dir 	= CommonUtil.file_dir_news;
	String news_no 		= request.getParameter("t_news_no");		
	String fileName  	= request.getParameter("t_fileName");
	if(fileName != null){
		File dFa = new File(file_dir, fileName);
		dFa.delete();
	}
	int result 		 = dao.deleteNews(news_no);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("삭제 되었습니다.~");
			<%}else{%>
				alert("삭제 처리 되지 못했습니다~");
			<%}%>
			location.href = "news_r.jsp";
		</script>
	</head>
</html>