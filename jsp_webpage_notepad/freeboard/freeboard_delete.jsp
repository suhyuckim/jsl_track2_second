<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Freeboard_DAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Freeboard_DAO dao 	 = new Freeboard_DAO();	
	String freeboard_no = request.getParameter("t_freeboard_no");		
	int result 		 = dao.deleteFreeboard(freeboard_no);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("삭제 되었습니다.~");
			<%}else{%>
				alert("삭제 처리 되지 못했습니다~");
			<%}%>
			location.href = "freeboard_r.jsp";
		</script>
	</head>
</html>