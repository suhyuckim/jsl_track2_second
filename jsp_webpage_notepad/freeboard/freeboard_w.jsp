<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>	
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
	height : 260px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function save(){
		var fm = document.freeboard;
		if(fm.t_name.value == ""){
			alert("작성자 입력!");
			fm.t_name.focus();
			return;
		}	
		if(fm.t_pw.value == ""){
			alert("비밀번호 입력!");
			fm.t_pw.focus();
			return;
		}		
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
		fm.action = "freeboard_insert.jsp";
		fm.method = "post";
		fm.submit();
	}
</script>
		<form name ="freeboard">
		<input type="hidden" name="t_work_gubun" value="insert">
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<td>작성자</td>
<%
	if(!sessionId.equals("")){
%>
							<td style="text-align:left;"><input name="t_name" type="hidden" style="width:100px;" value="<%=sessionId%>"><%=sessionId%></td>				
<%
	}else{		
%>
							<td style="text-align:left;"><input name="t_name" type="text" style="width:100px;"></td>
<%
	}
%>	
						</tr>
						
						<tr>
							<td>비밀번호</td>
							<td style="text-align:left;"><input name="t_pw" type="password" style="width:100px;"></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>제&nbsp;&nbsp;목</td>
							<td colspan="3" style="text-align:left;">
							<input name="t_title" type="text" style="width:650px;">							
							</td>
						</tr>
						<tr>
							<td>내&nbsp;&nbsp;용</td>
							<td colspan="3"><textarea name="t_content"></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/freeboard/freeboard_r.jsp"><input type="button" value="목 록" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
				<a href="javascript:save()"><input type="button" value="등 록" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>