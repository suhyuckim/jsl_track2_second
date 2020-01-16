<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ page import ="dao.Freeboard_DAO, dto.Freeboard_DTO"%>>
<%
	Freeboard_DAO dao 	 = new Freeboard_DAO();
	String freeboard_no  = request.getParameter("t_freeboardNo");
	Freeboard_DTO dto 	 = dao.getFreeboardView(freeboard_no);
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
	function deleteFreeboard(){
		var fm = document.t_freeboard;
		if(fm.t_pw.value == ""){
			alert("비밀번호 입력!");
			fm.t_pw.focus();
			return;
		}		
		if(fm.t_pw.value != fm.h_pw_2.value){
			alert("비밀번호가 같지 않습니다.");
			fm.t_pw.focus();
			return;
		}	
		var yn = confirm("정말 삭제 하겠습니까?");		
		if(yn==true){			
			fm.action = "freeboard_delete.jsp";
			fm.method = "post";
			fm.submit();
		}		
	}
</script>
		<form name ="t_freeboard">
		<input type="hidden" name="t_work_gubun" value="delete">
		<input type="hidden" name="t_freeboard_no" value="<%=freeboard_no%>"/>
		<input type="hidden" name="h_pw_2" value="<%=dto.getPassword()%>"/>
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<td>비밀번호</td>							
						</tr>						
							<td style="text-align:center;"><input name="t_pw" type="password" style="width:100px;" ></td>						
					</thead>				
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/freeboard/freeboard_r.jsp"><input type="button" value="취 소" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
				<a href="javascript:deleteFreeboard()"><input type="button" value="확 인" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>