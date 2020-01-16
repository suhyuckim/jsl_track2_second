<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ page import="dao.News_DAO,dto.News_DTO"%>
<%
	News_DAO dao 	 = new News_DAO();
	String news_no   = request.getParameter("t_newsNo");
	int nHit 		 = dao.newsHit(news_no);
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
	height : 300px;
	border : none;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
		
<script>
	function deleteNews(){
		var yn = confirm("정말 삭제 하겠습니까?");
		if(yn==true){
			var fm = document.news;
			fm.action = "news_delete.jsp";
			// fm.action = "news_proc.jsp";
			fm.method = "post";
			fm.submit();
		}
	}
</script>
		<form name="news">
			<input type="hidden" name="t_work_gubun" value="delete">
			<input type="hidden" name="t_news_no" value="<%=news_no%>">
		</form>
		<div class="bord_list">
			<table class="bord_table">
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<td>제&nbsp;&nbsp;목</td>
						<td id="title_box" style=text-align:left;><%=dto.getTitle()%></td>
						<td style=text-align:right;><i class="fa fa-eye">&nbsp;<%=dto.getHit()%></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>내&nbsp;&nbsp;용</td>
						<td class="td_box" colspan="2" style = "text-align:left;">
						<textarea readonly><%=dto.getContent()%></textarea>
						</td>
					</tr>

				<%if(dto.getFile_name_1() != null){%>
					<tr>
						<td>첨 부</td>
						<td colspan="2" style=text-align:left;>
							<a href="/common/filedown.jsp?t_file=<%=dto.getFile_name_1()%>&t_gubun=news"><%=dto.getFile_name_1().substring(8)%></a>
						</td>						
					</tr>
				<%}%>
					<tr>
						<td>작 성 자</td>
						<td id="title_box" style=text-align:left;><%=dto.getReg_id()%></td>						
						<td style=text-align:right;> 등록일자 : <%=dto.getReg_date()%></td>						
					</tr>
				</tbody>
			</table>
		</div>
		<p style="text-align:center; padding-top:10px">
			<a href="/news/news_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			<%if(sessionId.equals("manager")){%>
			<a href="/news/news_u.jsp?t_newsNo=<%=dto.getNews_no()%>"><input type="button" value="수 정" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			<a href="javascript:deleteNews()"><input type="button" value="삭 제" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			<%}%>
		</p>
	</div>
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