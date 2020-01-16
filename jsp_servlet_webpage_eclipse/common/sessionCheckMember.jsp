<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common_session_info.jsp"%>
<%
	if(sessionName.equals("")){
%>
		<script>
			alert("로그인정보 만료되었습니다. 다시 로인하세요!");
			location.href = "/index.jsp";
		</script>
<%
	}
%>