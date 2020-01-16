<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ page import="dao.Qanda_DAO,dto.Qanda_DTO"%>
<%
	Qanda_DAO dao 	 = new Qanda_DAO();
	String qanda_no  = request.getParameter("t_qandaNo");
	int nHit 		 = dao.qandaHit(qanda_no);
	Qanda_DTO dto 	 = dao.getQandaView(qanda_no);
%>
	<div id="menu">
		<ul>
		  <li><i class="fas fa-flag"></i><a href="/notice/notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img src = "/images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;NEWS</a></li>
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
	height : 130px;
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
	function deleteQanda(){
		var yn = confirm("정말 삭제 하겠습니까?");
		if(yn==true){
			var fm = document.qanda;
			fm.action = "qanda_delete.jsp";
			fm.method = "post";
			fm.submit();
		}
	}
	
	function deleteQanda_2(){
		var yn = confirm("정말 삭제 하겠습니까?");
		if(yn==true){
			var fm = document.qanda;
			fm.action = "qanda_delete2.jsp";
			fm.method = "post";
			fm.submit();
		}
	}
</script>
		<form name="qanda">
			<input type="hidden" name="t_work_gubun" value="delete">
			<input type="hidden" name="t_qanda_no" value="<%=qanda_no%>">
		</form>
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
						<td id="title_box" style=text-align:left;><%=dto.getTitle()%></td>
						
						<td>작 성 자</td>
						<td id="title_box" style=text-align:left;><%=dto.getUser_name()%></td>						
						<td style=text-align:right;> 등록일자 : <%=dto.getReg_date_q()%></td>
						<td style=text-align:right;><i class="fa fa-eye">&nbsp;<%=dto.getHit()%></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>질&nbsp;&nbsp;문</td>
						<td class="td_box" colspan="5" style = "text-align:left;">
						<textarea readonly><%=dto.getQuestion()%></textarea>
						</td>
					</tr>
					
					<tr>
						<td>답&nbsp;&nbsp;변</td>
						<td class="td_box" colspan="5" style = "text-align:left;">					
						<textarea readonly><%=dto.getAnswer()%></textarea></td>
					</tr>	
					<tr>
						<td>작 성 자</td>
						<td id="title_box" colspan="3" style=text-align:left;>매니저</td>						
						<td colspan="2" style=text-align:right;> 등록일자 : <%=dto.getReg_date_a()%></td>						
					</tr>					
				</tbody>
			</table>
		</div>
		<p style="text-align:center; padding-top:10px">
			<a href="/qanda/qanda_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
<%
			if(dto.getStatus().equals("답변대기")){
%>
			<a href="/qanda/qanda_u.jsp?t_qandaNo=<%=dto.getQanda_no()%>"><input type="button" value="수 정" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			
<%
			}
%>				
<%
			if(sessionId.equals("manager") && dto.getStatus().equals("n")){
%>
			<a href="/qanda/qanda_a.jsp?t_qandaNo=<%=dto.getQanda_no()%>"><input type="button" value="답 변" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
<%
			}
%>	
		
<%
			if(sessionId.equals("manager") && dto.getStatus().equals("y")){
%>
			<a href="/qanda/qanda_u2.jsp?t_qandaNo=<%=dto.getQanda_no()%>"><input type="button" value="답변수정" style="color:white;background:#42464d;width:60px;height:30px;border:none;"></a>
			<a href="javascript:deleteQanda_2()"><input type="button" value="답변삭제" style="color:white;background:#42464d;width:60px;height:30px;border:none;"></a>
<%
			}
%>		

			<a href="javascript:deleteQanda()"><input type="button" value="전체삭제" style="color:white;background:#42464d;width:60px;height:30px;border:none;"></a>	

		</p>
	</div>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>