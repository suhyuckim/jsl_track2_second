<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import = "dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%@ page import = "com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao 		= new Notice_DAO();	
	String notice_no 	= dao.getNoticeNo();
	
	int sizeLimit 		= 1024*1024*5;
	// String file_dir 	= "C:/webserver/webapps/ROOT/file_room/notice/"; 
	String file_dir     = CommonUtil.file_dir_notice;
	MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
	
	String fileName 	= mpr.getFilesystemName("fileName_a");
	out.print("fileName : "+fileName);
	String saveFileName = "";
	if(fileName != null){
		File oldFile = new File(file_dir, fileName);
		File newFile = new File(file_dir, notice_no+"-"+fileName);
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	} 
	
	String title 		= mpr.getParameter("title");	
	String content 		= mpr.getParameter("cont");
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();
	int hit 			= 0;
	
	Notice_DTO notice_dto = new Notice_DTO(notice_no, title, content, saveFileName, reg_id, reg_date, hit);
	int result = dao.insertNotice(notice_dto);	
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("등록되었습니다~");
			<%} else {%>
				alert("정상처리 되지 못했습니다~");
			<% } %>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>