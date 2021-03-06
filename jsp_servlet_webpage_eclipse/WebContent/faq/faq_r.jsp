<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*, dao.Faq_DAO, dto.Faq_DTO,common.CommonUtil"%>
<%@ include file="/common_session_info.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	Faq_DAO dao = new Faq_DAO();
	String selValue = request.getParameter("t_sel"); 
	String txtValue = request.getParameter("t_search");
	if(selValue == null){
		selValue = "all";
		txtValue = "";
	}
	if(txtValue == null){
		txtValue = "";
	}
		
		ArrayList<Faq_DTO> dtos = dao.getFaqList(selValue,txtValue);
		
//************* page 시작 *************/

	String tdCount ="5";				

	String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
	int			current_page;					// 현재페이지 번호
	int			total_page;						// 총페이지 수
	int			total_count;					// 총레코드 수
	int			for_count;						
	int			list_setup_count = 3;			// 한번에 출력될 List 수, 한페이지당 몇줄 보여줄것인지
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
		<link href="/css/sub-faq.css" rel="stylesheet">
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
.select_Box{
	text-align:right;
	margin-right:50px;
	vertical-align : middle;
}
.select_Box select{
	vertical-align : middle;
	height : 25px;
}
.select_Box input[type="text"]{
	height : 25px;
	vertical-align : middle;
}
.button{
	vertical-align : middle;
	width:50px;
	height:25px;
	background:#76b7c0;
	border:none;
	color:white;
}
.text a{
	text-align:right; 
	margin:5px;
	font-size:15px;
	padding:5px; 
	transition:all 0.5s;
	background:#000000; color:#fff;
}
.faq_button{
	float:right;
}
</style>		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/notice/notice_r.jsp">NOTICE</a></h2>		
			<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
			<h2 class="color"><a href="/faq/faq_r.jsp"><i class="fas fa-check"></i> FAQ</a></h2>	
			<h2><a href="/news/news_r.jsp"> NEWS</a></h2>	
			</div>
			
			<form name="faq">
				<p class="select_Box">
					<select name="t_sel" onChange="t_selChange()">
						<option value="all"  <%if(selValue.equals("all"))  out.print("selected");%>>전체</option>
						<option value="question" <%if(selValue.equals("question")) out.print("selected");%>>제목</option>
						<option value="answer" <%if(selValue.equals("answer")) out.print("selected");%>>내용</option>
					</select>
					<input type="text" value="<%=txtValue%>" name="t_search" size="10" maxlength="30" <%if(selValue.equals("all")) out.print("disabled = 'disabled'");%> />
					<input class="button" type="button" onClick="javascript:formSearch()" value="검색">
				</p>
			</form>
			
			<form name="faqdelete">
				<input type="hidden" name="t_faq_no">
			</form>
			
			<!-- faq start-->
			<div class="faq-box">
<%	
				if ( total_count > 0 ){
					for(int k = 0 ; k < total_count ; k++ )	{
						if(v_count == for_count){ 
%> 				
		
				<button class="accordion"> ▶ <%=dtos.get(k).getQuestion()%></button>
				<div class="text">
					<span><%=dtos.get(k).getAnswer()%></span>		
					<%if(sessionId.equals("manager")){%>
					<span class="faq_button">
						<a href="faq_u.jsp?t_faqNo=<%=dtos.get(k).getFaq_no()%>">Modify</a>
						<a href="javascript:deleteFaq('<%=dtos.get(k).getFaq_no()%>')">Delete</a>
					</span>
					<%}%>		
				</div> 
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
				  <button class="accordion"> ▶ 등록된 내용이 없습니다. </button>
				<div class="text">
					<p>등록된 내용이 없습니다.</p>		
				</div> 
<%}%> 
			<div class="page-number">
				<%
					url = "faq_r.jsp?t_sel="+selValue+"&t_search="+txtValue;		
					out.println(CommonUtil.pageList(current_page, total_page, url));
				%>
				<%if(sessionId.equals("manager")){%>
					<a href="/faq/faq_w.jsp" class="btn-write">글쓰기</a>
				<%}%>
			</div>
			
		</div>
		<!-- notice css end -->
		
		
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
	
		<script>
			$(document).ready(function() {
				$(".accordion").click(function() {
					$(".text").not($(this).next().slideToggle(250)).slideUp();
					$(this).siblings().removeClass("active");	
					$(this).toggleClass("active");	
				});
			});
			
			function formSearch(){ //제목 검색
				var fm = document.faq; 		
				fm.action = "faq_r.jsp";
				fm.method = "post";
				fm.submit();
			}
			
			function deleteFaq(delNo){
				var yn = confirm("정말 삭제 하겠습니까?");
				if(yn){
					var fm = document.faqdelete;
					fm.t_faq_no.value = delNo;
					fm.action = "faq_delete.jsp";
					fm.method = "post";
					fm.submit();
				}
			}
			
			function t_selChange(){
				var fm = document.faq;
				if(fm.t_sel.value == "all"){
					fm.t_search.disabled = "disabled";
				} else {
					fm.t_search.disabled = "";
				}
			}
		</script>
	</body>
</html>