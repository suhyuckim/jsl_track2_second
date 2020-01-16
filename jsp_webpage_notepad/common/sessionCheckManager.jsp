<%
	if(!sessionLevel.equals("manager")){
%>
		<script>
			alert("관리자 화면입니다. 관리자 로그인하세요!");
			location.href = "/index.jsp";
		</script>
<%
	}
%>