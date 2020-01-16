<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Member_DAO,dto.Member_DTO,common.CommonUtil"%>
<%@ include file="/common_head.jsp"%>	
<%@ include file="/common/sessionCheckMember.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Member_DAO dao = new Member_DAO();
	
	String id = request.getParameter("t_id");	
	
	int result = dao.deleteMember(id);
	session.invalidate();
%>
<html>
	<head>
		<script>		
			<% if(result > 0){ %>
				alert("회원탈퇴 되었습니다.");
			<% }else{ %>
				alert("회원탈퇴 실패하였습니다.");
			<% } %>
			location.href = "/index.jsp";
		</script>
	</head>
</html>