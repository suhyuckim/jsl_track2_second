<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ page import="common.*" %>
<script>
	function formSearch(){
		myform.action = "/Notice";
		myform.method = "post";
		myform.submit();
	}
	function goPage(pageNum){
		myform.r_page.value = pageNum;
		myform.action ="/Notice";
		myform.method ="post";
		myform.submit();
	}
	function goView(no){
		viewForm.t_no.value = no;
		viewForm.action = "/NoticeView";
		viewForm.method = "post";
		viewForm.submit();
	}
</script>
<form name="viewForm">
	<input type="hidden" name="t_no">
</form>
	<!-- sub contents -->
	<div class="sub_title_notice">
		<h2></h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="/"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="/Notice">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="/Notice">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="/Notice">공지사항</a>
						<a href="qa.html">질문과답변</a>
						<a href="faq.html">FAQ</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="search_wrap">
		<div class="record_group">
			<p>총게시글<span> ${t_dtos.size()}</span>건</p>
		</div>
		<div class="search_group">
			<form name="myform">
			<input type="hidden" name="r_page">
				<select name="t_select" class="select">
					<option value="title"   <c:if test="${t_select eq 'title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${t_select eq 'content'}">selected</c:if>>내용</option>
				</select>
				<input type="text" name="t_search" class="search_word" value="${t_search}">
				<button onClick="formSearch()" class="btn_search" type="button"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<caption class="sr-only">공지사항 리스트</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="15%">
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
				<c:set var="forYN" value="true"/>
				<c:if test="${t_dtos.size() > 0}">
					<c:forEach items="${t_dtos}" var="dtos">
						<c:if test="${forYN == true}">
							<c:choose>
								<c:when test="${v_count == for_count}">
									<tr>
										<td><a href="javascript:goView('${dtos.getNo()}')">${dtos.getNo()}</a></td>
										<td class="title"><a href="javascript:goView('${dtos.getNo()}')">${dtos.getTitle()}</a></td>
										<td>${dtos.getReg_info()}</td>
										<td>${dtos.getReg_date()}</td>
										<td>${dtos.getHit()}</td>
									</tr>
							<c:set var="v_count" value="${v_count+1}"/>
								<c:set var="for_count" value="${for_count+1}"/>
							</c:when>
							<c:otherwise>
								<c:set var="v_count" value="${v_count+1}"/>
							</c:otherwise>				
						</c:choose>		
						<c:if test="${v_count == a_count}">
							<c:set var="forYN" value="false"/>		
						</c:if>
					</c:if>
					</c:forEach>
				</c:if>	
			</tbody>
		</table>
		<div class="paging">
<!-- 			
			<a href=""><i class="fa  fa-angle-double-left"></i></a>
			<a href=""><i class="fa fa-angle-left"></i></a>
			<a href="" class="active">1</a>
			<a href="">2</a>
			<a href="">3</a>
			<a href="">4</a>
			<a href="">5</a>
			<a href=""><i class="fa fa-angle-right"></i></a>
			<a href=""><i class="fa  fa-angle-double-right"></i></a> 
-->
<%
			Integer current_page = (Integer)request.getAttribute("current_page");
			Integer total_page 	 = (Integer)request.getAttribute("total_page");
			out.println(CommonUtil.pageListPost(current_page, total_page, 2)); // 3이라는 숫자를 입력하여 게시판별로 페이지수를 별도로 지정가능 
%>
			<a href="NoticeWriteForm" class="btn_write">글쓰기</a>
		</div>
	  </div>
	</div>
	<!-- end contents -->
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
	<%@ include file="../footer.jsp" %>
 </body>
</html>