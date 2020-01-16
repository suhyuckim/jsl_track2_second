<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp" %>
<%@ page import="java.util.*,dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Notice_DAO dao = new Notice_DAO();
	String selValue = request.getParameter("t_sel"); 
	String txtValue = request.getParameter("t_search");
	if(selValue == null){
		selValue = "title";
		txtValue = "";
	}
		ArrayList<Notice_DTO> dtos = dao.getNoticeList(selValue,txtValue);
		
//************* page 시작 *************/

	String tdCount ="5";				

	String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
	int			current_page;					// 현재페이지 번호
	int			total_page;						// 총페이지 수
	int			total_count;					// 총레코드 수
	int			for_count;						
	int			list_setup_count = 10;			// 한번에 출력될 List 수, 한페이지당 몇줄 보여줄것인지
	int			p_no;
	int			v_count;
	int			a_count;
	String		url				= null;	

	// 조회된 건수 구하기  total_count : 설정
	if(dtos == null) total_count =0; 			//게시판 테이블에 있는 전체 숫자
	else total_count = dtos.size(); 


	// 페이지번호가 없으면 1페이지로 간주
	if(r_page.equals("")) current_page = 1;
	else current_page = Integer.parseInt(r_page);
		
	for_count		= list_setup_count;
	p_no			= list_setup_count;				// 페이지수가 10
	total_page = total_count / list_setup_count;	// 전체페이지수 계산 (if 23개 게시물이면 2)
   
	if(current_page == 1) {
		v_count		= 0;
		a_count		= list_setup_count;
		for_count	= 0;
	} else{
		v_count		= 0;
		a_count		= p_no * current_page;
		for_count	= a_count - list_setup_count;
	}
	if(total_page * list_setup_count != total_count)   total_page = total_page +1;

//************* page 끝 *************/ 
%>
	<div id="menu">
		<ul>
		  <li><img class="arrow" src = "../images/arrow.gif"><a href="/notice/notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;NEWS</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="/freeboard/freeboard_r.jsp">&nbsp;자유게시판</a></li>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="/qanda/qanda_r.jsp">&nbsp;Q & A</a></li>
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
	padding : 8px ;
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
.button {
	width : 50px;
	height : 20px;
	color : #fff;
	background:#42464d;
	border:none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function formSearch(){ //제목 검색
		var fm = document.notice; 		
		fm.action = "notice_r.jsp";
		fm.method = "post";
		fm.submit();
	}
</script>
		<form name="notice">
		<p class="select_Box">
			<select name="t_sel">
				<option value="title" <%if(selValue.equals("title")) out.print("selected");%>>제목</option>
				<option value="content" <%if(selValue.equals("content")) out.print("selected");%>>내용</option>
			</select>
			<input type="text" value="<%=txtValue%>" name="t_search" size="30" maxlength="30"/>
			<input class="button" type="button" onClick="javascript:formSearch()" value="검색">
		</p>
		</form>
		<div class="bord_list">
			<table class="bord_table">
				<colgroup>
					<col width="10%">
					<col width="*">
					<col width="10%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
<%	
	if ( total_count > 0 ){
		for(int k = 0 ; k < total_count ; k++ )	{
			if(v_count == for_count){ 
%> 			
					<tr>									
						<td><a href="notice_v.jsp?t_noticeNo=<%=dtos.get(k).getNotice_no()%>"><%=dtos.get(k).getNotice_no()%></a></td>
						<td class="title"><a href="notice_v.jsp?t_noticeNo=<%=dtos.get(k).getNotice_no()%>"><%=dtos.get(k).getTitle()%></a></td>
						<td><%=dtos.get(k).getReg_id()%></td>
						<td><%=dtos.get(k).getReg_date()%></td>
						<td><%=dtos.get(k).getHit()%></td>
					</tr>
<%
			v_count = v_count + 1;
			for_count = for_count + 1;
			}else { 
				v_count = v_count + 1;
			}
			if(v_count == a_count)break; 
		}
	}else{	
%>
				   <TR align="center" bgcolor="white" >
					   <TD colspan="<%=tdCount%>" >등록된 내용이 없습니다.</TD>
				   </TR>
<%	} %> 				
				</tbody>
			</table>
			<div class="paging">
<%
			url = "notice_r.jsp?t_sel="+selValue+"&t_search="+txtValue;		
			out.println(CommonUtil.pageList(current_page, total_page, url));
%>					
<%
		if(sessionId.equals("manager")){
%>			
				<a href="/notice/notice_w.jsp" class="btn_write">글쓰기</a> 
<%
		}
%>
			</div>
		</div>
	</div>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>