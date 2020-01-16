<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<c:if test="${session_level != 'manager'}">
		<script>
			alert("관리자 화면입니다. 관리자 로그인하세요!");
			location.href = "/Index";
		</script>
	</c:if>