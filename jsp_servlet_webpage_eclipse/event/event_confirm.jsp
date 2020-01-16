<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,dto.Event_M_DTO,dao.Event_M_DAO,common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Event_M_DAO dao = new Event_M_DAO();
	String event_no = request.getParameter("t_eventNo"); 
	
	ArrayList<Event_M_DTO> dtos = dao.getEventConfirm(sessionId); 
	
	
//************* page 시작 *************/

	String tdCount ="7";				

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

<!doctype html>

<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-notice.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		
	<body>
		<!-- skip navigation -->
		<dl id="access">
			<dt>바로가기 및 건너띄기 링크</dt>
			<dd><a href="#contents">본문바로가기</a></dd>
			<dd><a href="#navigation">주메뉴 바로가기</a></dd>
		</dl>
		<hr>
		
		<div id="big-box">
			<header>
			<div id="header">
				<div class="nav">
					<h2 class="readonly">주메뉴</h2>
						<ul class="nav-menu">
						<li><a href="sub1.html">ABOUT EL WIDE</a></li>
						<li><a href="sub2.html">MUSIC</a></li>
						<li><a href="sub3.html">MEDIA</a></li>
						<li><a href="sub4.html">CULTURE</a></li>
						<li><a href="/notice/notice_r.jsp">NOTICE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/index/index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<%if(!sessionName.equals("")){%>
								<li><a href="/member/member_logout.jsp"><i class="fas fa-sign-out-alt"></i></a></li>
							<%}else{%>	
								<li><a href="/member/member_login.jsp"><i class="fas fa-user"></i></a></li>							
							<%}%>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
						 <%if(!sessionName.equals("")){%>	
						 	<li>[<%=sessionName%>]님</li>
						 <%}else{%>						
							<li>CONNECT WITH WIDE</li>
						 <%}%>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>
		
		<!--  header end -->
		
<style>
.table .red span{
	background:#F6CED8;	
	padding:5px;
	color:#FFFFFF;
}
.table .blue span{
	background:#A9BCF5;
	padding:5px;
	color:#FFFFFF;
}
.table .end span{
	background:#D0F5A9;
	padding:5px;
	color:#FFFFFF;
}
.table .progress span{
	background:#F5A9D0;
	padding:5px;
	color:#FFFFFF;
}
</style>
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/notice/notice_r.jsp"> NOTICE</a></h2>	
			<h2 class="color"><a href="/event/event_r.jsp"><i class="fas fa-check"></i> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2><a href="/news/news_r.jsp"> NEWS</a></h2>	
			</div>
		
			<!-- table start-->
			
			<form name="event">
				<input type="hidden" name="t_event_no" value="<%=event_no%>">
			<div class="table-box">			
				<table class="table">
					<caption>공지사항 - 번호, 제목, 첨부, 작성일, 조회수</caption>
					<colgroup>					
						<col width="12%">
						<col width="*%">
						<col width="12%">
						<col width="12%">
						<col width="12%">
						<col width="10%">
					</colgroup>
					
					<thead>
						<tr>							
							<th scope="col">번호</th>							
							<th scope="col">제목</th>
							<th scope="col">이름</th>
							<th scope="col">응모일</th>
							<th scope="col">진행여부</th>
							<th scope="col">당첨여부</th>									
						</tr>
					</thead>
					
					<tbody>
<%
			if ( total_count > 0 ){
				for(int k = 0 ; k < total_count ; k++ )	{
					if(v_count == for_count){
%>

<%
	String today	= CommonUtil.getToday();
	int getToday  = Integer.parseInt(today);
	String reg_end = dtos.get(k).getReg_end().replace("-","");
	int end_date  = Integer.parseInt(reg_end);
%>
						<tr>									
							<td class="title">
								<a href="event_v.jsp?t_eventNo=<%=dtos.get(k).getEvent_no()%>"><%=dtos.get(k).getEvent_no()%></a></td>
							<td><a href="event_v.jsp?t_eventNo=<%=dtos.get(k).getEvent_no()%>"><%=dtos.get(k).getTitle()%></a></td>
							<td><%=dtos.get(k).getReg_id()%></td>
							<td><%=dtos.get(k).getReg_date()%></td>	
						<%if(getToday > end_date){%>
							<td class="end"><span>종료</span></td>	
						<%}else{%>
							<td class="progress"><span>진행중</span></td>	
						<%}%>														
						<%if(dtos.get(k).getStatus().equals("n")){%>																				
							<td class="red"><span>탈락</span></td>
						<%}else{%>	
							<td class="blue"><span>당첨</span></td>	
						<%}%>	
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
				<tr align="center" bgcolor="white" >
					<TD colspan="<%=tdCount%>">등록된 내용이 없습니다.</TD>
				</tr>
			<%}%> 
				</tbody>
			</table>
		</div>

			<div class="page-number">
				<%out.println(CommonUtil.pageList(current_page, total_page, url));%>
				<a href="/event/event_r.jsp" class="btn-write">뒤로가기</a>			
			</div>
		</div>
	</form>
		
			<!--  footer start  -->
			<div id="footer">
				<div class="footer-text">
					<ul class="sub-logo">
						<li><a href="/index/index.jsp" alt="서브로고">EL WIDE</a></li>
					</ul>
					
					<ul class="copy">
						<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
					</ul>
				</div>
			</div>
		</div>	
</body>
</html>