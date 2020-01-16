<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Qanda_DAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Qanda_DAO dao 	 = new Qanda_DAO();	
	String qanda_no  = request.getParameter("t_qanda_no");		
	int result 		 = dao.deleteQanda(qanda_no);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("삭제 되었습니다.~");
			<%}else{%>
				alert("삭제 처리 되지 못했습니다~");
			<%}%>
			location.href = "qanda_r.jsp";
		</script>
	</head>
</html>