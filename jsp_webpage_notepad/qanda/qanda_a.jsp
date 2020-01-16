<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ page import="dao.Qanda_DAO,dto.Qanda_DTO"%>
<%
	Qanda_DAO dao 	 = new Qanda_DAO();
	String qanda_no  = request.getParameter("t_qandaNo");
	Qanda_DTO dto 	 = dao.getQandaView(qanda_no);
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
	height : 120px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function answer(){
		var fm 	  = document.qanda;		
		if(fm.t_answer.value == ""){
			alert("답변 입력!");
			fm.t_answer.focus();
			return;
		}		
		fm.action = "qanda_answer.jsp";
		fm.method = "post";
		fm.submit();
	}
</script>
		<form name ="qanda">
		<input type="hidden" name="t_work_gubun" value="update">
		<input type="hidden" name="t_qanda_no" value="<%=qanda_no%>">
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="10%">
						<col width="10%">
						<col width="30%">
						<col width="10%">
					</colgroup>
					<thead>	
						<tr>
							<td>제&nbsp;&nbsp;목</td>
							<td style="text-align:left;"><input name="t_title" value="<%=dto.getTitle()%>" type="text" style="width:650px;" readonly></td>	
						</tr>								
					</thead>					
					<tbody>
					<tr>		
						<td>작성자</td>
						<td id="title_box" style=text-align:left;><%=dto.getUser_name()%></td>
					</tr>					
					<tr>
						<td>내&nbsp;&nbsp;용</td>
						<td><textarea name="t_content" readonly><%=dto.getQuestion()%></textarea></td>
					</tr>						
					<tr>
						<td>답&nbsp;&nbsp;변</td>
						<td class="td_box" colspan="5" style = "text-align:left;">					
						<textarea name="t_answer"><%=dto.getAnswer()%></textarea></td>
					</tr>	
					<tr>
						<td>작성자</td>
						<td style="text-align:left;"><%=sessionName%></td>				
					</tr>					
					</tbody>
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/qanda/qanda_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
				<a href="javascript:answer()"><input type="button" value="답변 저장" style="color:white;background:#42464d;width:70px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>