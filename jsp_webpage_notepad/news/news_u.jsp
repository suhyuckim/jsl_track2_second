﻿<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ page import="dao.News_DAO,dto.News_DTO"%>
<%
	News_DAO dao 	 = new News_DAO();
	String news_no   = request.getParameter("t_newsNo");
	News_DTO dto 	 = dao.getNewsView(news_no);
%>
	<div id="menu">
		<ul>
		  <li><i class="fas fa-flag"></i><a href="/notice/notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;NEWS</a></li>
		  <li><i class="fas fa-comment-dots"></i><a href="/freeboard/freeboard_r.jsp">&nbsp;자유게시판</a></li>
		  <li><i class="fas fa-question"></i><a href="/qanda/qanda_r.jsp">&nbsp;Q & A</a></li>
		</ul>
	</div>
<style>
.bord_list { padding-top : 10px; }
.bord_table { width : 100%; }
.bord_list th {
	border-top : 1px solid #848484 ;
	border-bottom : 1px solid #848484 ;
	padding : 10px ;
}
.bord_table td{
	text-align : center ;
	padding : 10px ;
	border-bottom : 1px solid #D8D8D8 ;
}
td.title {
	text-align : left ;
}
.home_icon{
	width : 15px ;
}
#content .title a {
	color : #6E6E6E;
}
.select_Box {
	text-align : right;
}
.select_Box select {
	height : 20px;
}
#content p a {
	color : black;
}
textarea {
	width : 650px;
	height : 315px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function update(){
		var fm 	  = document.news;
		if(fm.t_title.value == ""){
			alert("제목 입력!");
			fm.t_title.focus();
			return;
		}
		if(fm.t_content.value == ""){
			alert("내용 입력!");
			fm.t_content.focus();
			return;
		}		
		fm.action = "news_update.jsp";
		// fm.action = "news_proc.jsp";
		fm.method = "post";
		fm.submit();
	}
</script>
		<form name ="news" enctype="multipart/form-data">
		<input type="hidden" name="t_work_gubun" value="update">
		<input type="hidden" name="t_news_no" value="<%=news_no%>">
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<td>작성자</td>
							<td style="text-align:left;"><%=sessionName%></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>제&nbsp;&nbsp;목</td>
							<td style="text-align:left;"><input name="t_title" value="<%=dto.getTitle()%>" type="text" style="width:650px;"></td>
						</tr>
						<tr>
							<td>내&nbsp;&nbsp;용</td>
							<td><textarea name="t_content"><%=dto.getContent()%></textarea></td>
						</tr>
						<tr>
							<td>첨&nbsp;&nbsp;부</td>
							<td colspan="2" style=text-align:left;>
								<%if(dto.getFile_name_1() != null){%>
									<a href="/common/filedown.jsp?t_file=<%=dto.getFile_name_1()%>&t_gubun=news"><%=dto.getFile_name_1()%></a>
									&nbsp;&nbsp;파일삭제
									<input type="checkbox" name="checkBox_del_fileName" value="<%=dto.getFile_name_1()%>">
									<br>
								<%}%>	
									<br>
									<input type="file" 	 name="fileName_a">
									<input type="hidden" name="ori_fileName_a" value="<%=dto.getFile_name_1()%>">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/notice/notice_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
				<a href="javascript:update()"><input type="button" value=" 저 장" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
<style>
#footer{
	background : #42464d ;
	padding-top : 10px ;
	padding-bottom : 10px ;
}
#footer .address {
	font-style : narmal;
	color : #ababb1;
	margin-bottom : 10px;
}
#footer .address .title{
	font-size : 14px ;
	margin-bottom : 8px;
	color : #fff;
}
#footer .copyright {
	color : #fff ;
}
</style>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>