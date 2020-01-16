<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ page import ="dao.Freeboard_DAO, dto.Freeboard_DTO"%>
<%
	Freeboard_DAO dao 	 = new Freeboard_DAO();
	String freeboard_no  = request.getParameter("t_freeboardNo");
	int nHit 			 = dao.freeboardHit(freeboard_no);
	Freeboard_DTO dto 	 = dao.getFreeboardView(freeboard_no);
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
	height : 320px;
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
	// function deleteFreeboard(){
		// var yn = confirm("정말 삭제 하겠습니까?");
		// if(yn==true){
			// var fm = document.freeboard;
			// fm.action = "freeboard_delete.jsp";
			// fm.action = "notice_proc.jsp";
			// fm.method = "post";
			// fm.submit();
		// }		
		// if(fm.t_pw.value == ""){
			// alert("비밀번호 입력!");
			// fm.t_pw.focus();
			// return;
		// }		
		// if(fm.t_pw.value != fm.h_pw_2.value){
			// alert("비밀번호가 같지 않습니다.");
			// fm.t_pw.focus();
			// return;
		// }
	// }
</script>
		<form name="freeboard">
			<input type="hidden" name="t_work_gubun" value="delete">
			<input type="hidden" name="t_freeboard_no" value="<%=freeboard_no%>">
			<input type="hidden" name="h_pw_2" value="<%=dto.getPassword()%>"/>
		</form>
		<div class="bord_list">
			<table class="bord_table">
				<colgroup>
					<col width="10%">
					<col width="20%">
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
					<tr>
						<td>작 성 자</td>
						<td id="title_box" style=text-align:left;><%=dto.getReg_id()%></td>						
						<td style=text-align:right;> 등록일자 : <%=dto.getReg_date()%></td>	
						
					</tr>					
				</tbody>
			</table>
		</div>
		<p style="text-align:center; padding-top:10px">
			<a href="/freeboard/freeboard_r.jsp"><input type="button" value="목 록" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
<%
	if(sessionId.equals("manager") || dto.getReg_id().equals(sessionId)){
%>
			<a href="/freeboard/freeboard_u.jsp?t_freeboardNo=<%=dto.getFreeboard_no()%>"><input type="button" value="수 정" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>			
			<a href="/freeboard/freeboard_d.jsp?t_freeboardNo=<%=dto.getFreeboard_no()%>"><input type="button" value="삭 제" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
<%
	}
%>

<%
	if(sessionId.equals("") && dto.getStatus().equals("n")){
%>
			<a href="/freeboard/freeboard_u.jsp?t_freeboardNo=<%=dto.getFreeboard_no()%>"><input type="button" value="수 정" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>			
			<a href="/freeboard/freeboard_d.jsp?t_freeboardNo=<%=dto.getFreeboard_no()%>"><input type="button" value="삭 제" style="color:white;background:#F5A9BC;width:50px;height:30px;border:none;"></a>
<%
	}
%>

		</p>
	</div>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>