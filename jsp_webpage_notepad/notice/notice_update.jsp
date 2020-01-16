<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import ="dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%@ page import = "com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	int sizeLimit 		 = 1024*1024*5;
	// String file_dir 	 = "C:/webserver/webapps/ROOT/file_room/notice/"; 
	String file_dir  = CommonUtil.file_dir_notice; 
	MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
	
	String notice_no 	= mpr.getParameter("t_notice_no");	
	String title 		= mpr.getParameter("t_title");	
	String content 		= mpr.getParameter("t_content");	
	String reg_id 		= sessionId;	
	String reg_date 	= CommonUtil.getToday();
	
	String saveFileName = "";
	String fileName 	= mpr.getFilesystemName("fileName_a"); 
	String delFile 		= CommonUtil.checkNull(mpr.getParameter("checkBox_del_fileName"));  //삭제시킬 파일명
	if(!delFile.equals("")){ //checkbox에 삭제할 파일이 있다
		File dFa = new File(file_dir, delFile);
		dFa.delete();
	} else {
		saveFileName   = mpr.getParameter("ori_fileName_a");
	}
	if(fileName != null){
		String delFile_1 = mpr.getParameter("ori_fileName_a");
		if(delFile_1 != null){
			File dFa = new File(file_dir, delFile_1);
			dFa.delete();
		}
		File oldFile = new File(file_dir, fileName);
		File newFile = new File(file_dir, notice_no+"-"+fileName);
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	}
	int result = dao.updateNotice(notice_no, title, content, saveFileName, reg_id, reg_date);
%>
<html>
	<head>
		<script>
			<%if(result > 0){%>
				alert("수정 되었습니다~");
			<%} else {%>
				alert("수정 처리 되지 못했습니다~");
			<% } %>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>