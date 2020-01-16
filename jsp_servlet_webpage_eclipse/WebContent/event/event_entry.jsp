<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="java.util.*,dto.EventEntry_DTO"%>
<%
	EventEntry_DTO dto_1= new EventEntry_DTO("E_19001","응모합니다.1~~~","홍길동","n");
	EventEntry_DTO dto_2= new EventEntry_DTO("E_19002","응모합니다.2~~~","이상은","n");
	EventEntry_DTO dto_3= new EventEntry_DTO("E_19003","응모합니다.3~~~","김정은","n");
	ArrayList<EventEntry_DTO> arr = new ArrayList<EventEntry_DTO>();
	arr.add(dto_1);
 	arr.add(dto_2);
 	arr.add(dto_3);
	
%> 
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-notice.css?ver=1" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		<script>
			function eventProc(){
				var form = document.event;
				
				form.action = "event_entry_t.jsp";
				form.method = "post";
				form.submit();
				
			}	
		</script>
		
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
<!-- 							<li><a href="/notice/notice_r.jsp">COMMUNITE</a></li> -->
							<li><a href="/NoticeList">COMMUNITE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/index.html"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank" ><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<li><a href="login.html" value="login"><i class="fas fa-user"></i></a></li>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
							<li>CONNECT WITH WIDE</li>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>
		
		<!--  header end -->
		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/notice/notice_r.jsp">NOTICE</a></h2>	
			<h2><a href="/qanda/qanda_r.jsp"> QnA</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2 class="color"><a href="/news/news_r.jsp"><i class="fas fa-check"></i>NEWS</a></h2>	
			</div>
			
			<!-- table start-->
			<form name="event">
			<div class="table-box">
				<table class="table">
					<caption>공지사항 - 번호, 제목, 작성일, 조회수</caption>
					<colgroup>
						<col width="15%">
						<col width="*">
						<col width="20%">
						<col width="15%">
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col">이밴트번호</th>
							<th scope="col">응모제목</th>
							<th scope="col">응모자명</th>
							<th scope="col">당첨여부</th>
						</tr>
					</thead>
					
					<tbody>
<%
			for(int k=0; k < arr.size(); k++){
%>					
					
					 <tr>
						<td><%=arr.get(k).getEventNo()%></td>
						<td class="txt"><%=arr.get(k).getEventTitle()%></td>
						<td><%=arr.get(k).getEntryName()%></td>
						<td>
						<input type="text" name="t_name">
						<input type="checkbox" name="t_chkbox" <%if(arr.get(k).getGubun().equals("y")) out.print("checked");%>></td>
					 </tr>
<%
			}
%>					 
					</tbody>
				</table>
			</div>
			</form>
			<div class="page-number">
				<a href="#" class="icon"><i class="fas fa-arrow-circle-left fa-lg"></i></a>
				<a href="#" class="on">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
				<a href="#">6</a>
				<a href="#">7</a>
				<a href="#">8</a>
				<a href="#">9</a>
				<a href="#" class="more">…</a>
				<a href="#" class="icon"><i class="fas fa-arrow-circle-right fa-lg"></i></a>
				<a href="javascript:eventProc()" class="btn-write">당첨 처리</a>
			</div>
		
		</div>
		
		
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
	
	
	
	</body>
</html>









