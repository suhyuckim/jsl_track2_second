<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="common.*"%>
<%@ include file="../common_head.jsp" %>
<script type="text/javascript">
	function goWriteForm(){
		news.t_gubun.value = "writeForm";
		news.action ="/News";
		news.method ="post";
		news.submit();
	}
	function formSearch(){
		newsForm.action ="/News";
		newsForm.method ="post";
		newsForm.submit();
	}
	function goNewsView(no){
		news.t_gubun.value = "view";
		news.t_no.value = no;
		news.action ="/News";
		news.method ="post";
		news.submit();		
	}
	function goPage(pageNum){
		newsForm.r_page.value = pageNum;
		newsForm.action ="/News";
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
				<h2><a href="/Notice"> NOTICE</a></h2>	
				<h2><a href="/Qna"> QnA</a></h2>
				<h2><a href="/Faq"> FAQ</a></h2>	
				<h2 class="color"><a href="/News"><i class="fas fa-check"></i> NEWS</a></h2>	
				<c:if test="${session_id eq 'manager'}">
					<h2><a href="/Manager"> MEMBER</a></h2>
				</c:if>
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
			<c:set var="forYN" value="true"/>
			<c:if test="${t_dtos.size() > 0}">
				<c:forEach items="${t_dtos}" var="dtos">
					<c:if test="${forYN == true}">
						<c:choose>
							<c:when test="${v_count == for_count}">
								<tr>
									<td><a href="javascript:goNewsView('${dtos.getNo()}')">${dtos.getNo()}</a></td>
									<td class="txt"><a href="javascript:goNewsView('${dtos.getNo()}')">${dtos.getTitle()}</a></td>
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
			</div>
			
			<div class="page-number">
				<div class="page-number">
<!-- 	
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
-->
<%
	/* 
		url = request.getContextPath()+"/News?t_select="+select+"&t_search="+search;	
		out.println(CommonUtil.pageListGet(current_page, total_page, url)); 
	*/
		int current_page = (int)request.getAttribute("current_page");
		int total_page 	 = (int)request.getAttribute("total_page");
		out.println(CommonUtil.pageListPost(current_page, total_page, 2)); // 3이라는 숫자를 입력하여 게시판별로 페이지수를 별도로 지정가능 
%>
				<c:if test="${session_id eq 'manager'}">
					<a href="javascript:goWriteForm()" class="btn-write">글쓰기</a>
				</c:if>
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