<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Faq_DAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Faq_DAO dao 	 = new Faq_DAO();	
	String faq_no 	 = request.getParameter("t_faq_no");		
	int result 		 = dao.deleteFaq(faq_no);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("삭제 되었습니다.~");
			<%}else{%>
				alert("삭제 처리 되지 못했습니다~");
			<%}%>
			location.href = "faq_r.jsp";
		</script>
	</head>
</html>