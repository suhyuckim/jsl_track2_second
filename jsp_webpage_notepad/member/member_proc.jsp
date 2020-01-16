<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Member_DAO,dto.Member_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Member_DAO dao = new Member_DAO();
	
	String id 			= request.getParameter("t_id");
	String pw 			= request.getParameter("t_pw_1");
	String name 		= request.getParameter("t_name");
	String birth 		= request.getParameter("t_birth");
	String area 		= request.getParameter("t_area");
	String address  	= request.getParameter("t_address");
	String telecom  	= request.getParameter("t_telecom");
	String phone_1 		= request.getParameter("t_phone_1");
	String phone_2  	= request.getParameter("t_phone_2");
	String phone_3  	= request.getParameter("t_phone_3");
	String email_1  	= request.getParameter("t_email_1");
	String email_2  	= request.getParameter("t_email_2");
	String att_desk  	= CommonUtil.checkNull(request.getParameter("t_att_desk"));
	String att_note  	= CommonUtil.checkNull(request.getParameter("t_att_note"));
	String att_print  	= CommonUtil.checkNull(request.getParameter("t_att_print"));
	String att_beam     = CommonUtil.checkNull(request.getParameter("t_att_beam"));
	String reg_date 	= CommonUtil.getToday();
	
	int result = dao.insertMember(id,pw,name,birth,area,address,telecom, phone_1,phone_2,phone_3,
								  email_1,email_2,att_desk,att_note,att_print,att_beam,reg_date);						   
%>
<html>
	<head>
		<script>		
			<% if(result > 0){ %>
				alert("회원가입 되었습니다~");
			<% }else{ %>
				alert("회원가입 처리 되지 못했습니다~");
			<% } %>
			location.href = "/index.jsp";
		</script>
	</head>
</html>