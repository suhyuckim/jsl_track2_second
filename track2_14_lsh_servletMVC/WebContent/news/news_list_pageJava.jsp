<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common_head.jsp" %>
<%@ page import="java.util.*, dto.*,common.*" %>
<%
	ArrayList<News_dto> dtos = (ArrayList<News_dto>)request.getAttribute("t_dtos");	
	String select = (String)request.getAttribute("t_select");
	String search = (String)request.getAttribute("t_search");
	//******** page 시작*********/
	String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
	int			current_page;					// 현재페이지 번호
	int			total_page;						// 총페이지 수
	int			total_count;					// 총레코드 수
	int			for_count;
	
	//***************************************//
	int			list_setup_count = 5;			// 한번에 출력될 List 수
	//***************************************//
	int			p_no;
	int			v_count;
	int			a_count;
	String		url				= null;	
	// 조회된 건수 구하기  total_count : 설정
		if(dtos == null) total_count =0;
		else total_count = dtos.size(); 

		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
			
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;		// 전체페이지수 계산 (if 23개 게시물이면 2)
	 
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
	//******** page 끝*********/	
%>
<script type="text/javascript">
	function goWriteForm(){
		news.t_gubun.value ="writeForm";
		news.action ="<%=request.getContextPath()%>/News";
		news.method ="post";
		news.submit();
	}
	function formSearch(){
		newsForm.action ="<%=request.getContextPath()%>/News";
		newsForm.method ="post";
		newsForm.submit();
	}
	function goNewsView(no){
		news.t_gubun.value = "view";
		news.t_no.value = no;
		news.action ="<%=request.getContextPath()%>/News";
		news.method ="post";
		news.submit();		
	}
	function goPage(pageNum){
		newsForm.r_page.value = pageNum;
		newsForm.action ="<%=request.getContextPath()%>/News";
		newsForm.method ="post";
		newsForm.submit();
	}
</script>
		<form name="news">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_no">
		</form>
		<!--  header end -->
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
				<h2><a href="<%=request.getContextPath()%>/notice/Notice"> NOTICE</a></h2>	
				<h2><a href=""> QnA</a></h2>
				<h2><a href=""> FAQ</a></h2>	
				<h2 class="color"><a href="<%=request.getContextPath()%>/News"><i class="fas fa-check"></i> NEWS</a></h2>	
			</div>

		<div class="search_wrap">
			<div class="record_group">
				<p>* 총게시글 : <span>${t_dtos.size()}</span>건</p>	
			</div>
			<form name="newsForm">
				<input type="hidden" name="r_page">
				<div class="search_group">
					<select name="t_select" class="select">
						<option value="title" <c:if test="${t_select eq 'title'}">selected</c:if> >제목</option>
						<option value="content" <c:if test="${t_select eq 'content'}">selected</c:if>>내용</option>
					</select>
					<input type="text" name="t_search" value="${t_search}" class="search_word">
					<button class="btn_search" onClick="formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
				</div>
			</form>
		</div>

			
			<!-- table start-->
			<div class="table-box">
				<table class="table">
					<caption>공지사항 - 번호, 제목, 첨부, 작성일, 조회수</caption>
					<colgroup>
						<col width="5%">
						<col width="*">
						<col width="15%">
						<col width="10%">
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성일</th>
							<th scope="col">조회수</th>
						</tr>
					</thead>
					<tbody>
<%	
	if ( total_count > 0 ){
		for(int k = 0 ; k < total_count ; k++ )	{
			if(v_count == for_count){ 
%>
							<tr>
								<td><a href="javascript:goNewsView('<%=dtos.get(k).getNo()%>')"><%=dtos.get(k).getNo()%></a></td>
								<td class="txt"><a href="javascript:goNewsView('<%=dtos.get(k).getNo()%>')"><%=dtos.get(k).getTitle()%></a></td>
								<td><%=dtos.get(k).getReg_date()%></td>
								<td><%=dtos.get(k).getHit()%></td>
							</tr>
<%
				v_count = v_count + 1;
				for_count = for_count + 1;
			} else { 
				v_count = v_count + 1;
			}
			if(v_count == a_count) break;
		}
	}
%>
					</tbody>
				</table>
			</div>
			
			<div class="page-number">
				<div class="page-number">
<!-- 				<a href="#" class="icon"><i class="fas fa-arrow-circle-left fa-lg"></i></a>
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
-->
<%
	/* 
		url = request.getContextPath()+"/News?t_select="+select+"&t_search="+search;	
		out.println(CommonUtil.pageListGet(current_page, total_page, url)); 
	*/
	
	out.println(CommonUtil.pageListPost(current_page, total_page, 3)); // 3이라는 숫자를 입력하여 게시판별로 페이지수를 별도로 지정가능 
%>
					<a href="javascript:goWriteForm()" class="btn-write">글쓰기</a>
				</div>				
			</div>
		
		</div>
		
		
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">JSL 인재개발원</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>	
	</body>
</html>