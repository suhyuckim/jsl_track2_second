<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="dao.Member_DAO"%>
<%
	request.setCharacterEncoding("UTF-8");
	Member_DAO dao = new Member_DAO();
	String id 	   = request.getParameter("id");
	String pw 	   = request.getParameter("pw");
	String name    = dao.checkLogin(id,pw);
	
	if(name != null){
		session.setAttribute("session_name",name);
		session.setAttribute("session_id",id);
		if(id.equals("manager")){
			session.setAttribute("session_level","manager");
		}
		session.setMaxInactiveInterval(60*60*1);
	}
%>
<html>
	<head>
		<script>		
<%
		if(name != null){		
%>
		alert("<%=name%>님 환영홥니다.");
		location.href = "/index/index.jsp";
<%
		}else{
%>
		alert("아이디 비밀번호가 정확하지 않습니다.");
		location.href = "member_login.jsp";
<%
		}
%>		
		</script>
	</head>
</html>