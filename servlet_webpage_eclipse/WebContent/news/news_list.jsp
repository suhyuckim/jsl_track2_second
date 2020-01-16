<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title">
	<h2>NEWS list</h2>
		<%@ include file="/common/common_menu.jsp" %>
	</div>
	
	<div class="container">
		<div class="search_wrap clearfix">
			<div class="record_group">
				<p>총게시글 <span>${NewsCount}</span>건</p>
			</div>
				<form name="news">
					<div class="search_group">
						<input type="hidden" name="r_page">
						<input type="hidden" name="t_newsNo">
						<select class="select" name="t_sel">
							<option value="title" <c:if test="${selValue == 'title'}"> out.print("selected");</c:if>>제목</option>
							<option value="content" <c:if test="${selValue == 'content'}"> out.print("selected");</c:if>>내용</option>
						</select>
						<input type="text" value="${txtValue}" name="t_search" class="search_word" >
						<button class="btn_search" type="button" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
					</div>
				</form>
		</div> <!-- search_wrap -->
		
		<c:set var="forYN" value="true" />
			<c:if test="${t_dtos.size() > 0}">
				<c:forEach items="${t_dtos}" var="dtos">
					<c:if test="${forYN}">
						<c:if test="${v_count == for_count}">
							<div class="port">
								<div class="date">
									<h2>${dtos.getNews_no().substring(5)}</h2>
										<p>${dtos.getReg_date()}</p>
								</div>
								<div class="img">
								<c:if test="${dtos.getFile_name_1() != null}">
									<a href=javascript:NewsView_Servlet("${dtos.getNews_no()}")>
										<img src="/file_room/news/${dtos.getFile_name_1()}">
									</a>
								</c:if>	
								<c:if test="${dtos.getFile_name_1() == null}">
									<a href=javascript:NewsView_Servlet("${dtos.getNews_no()}")>
										<img src="/images/no_image.png">
									</a>
								</c:if>
								</div>
								<div class="content">
									<span><i class="fa fa-eye"></i> ${dtos.getHit()}</span>
									<h2><a href=javascript:NewsView_Servlet("${dtos.getNews_no()}")>${dtos.getTitle()}</a></h2>
									<p>${dtos.getContent()}</p>
								</div>
							</div>
			<c:set var="v_count" value="${v_count+1}" />
				<c:set var="for_count" value="${for_count+1}" />
					</c:if>
						<c:if test="${v_count != for_count}">
							<c:set var="v_count" value="${v_count+1}" />
						</c:if>
						<c:if test="${v_count == a_count}">
							<c:set var="forYN" value="false" />
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
					<c:if test="${t_dtos.size() == 0}">
						<tr align="center" bgcolor="white">
							<td colspan="7">등록된 내용이 없습니다.</td>
						</tr>
					</c:if>
					
		<div class="board_list">
			<ul><li></li></ul>
			
			<div class="paging">
				<%
					Integer cp = (Integer) request.getAttribute("current_page");
					int current_page = cp.intValue();
					Integer tp = (Integer) request.getAttribute("total_page");
					int total_page = tp.intValue();
					out.println(CommonUtil.pageList2(current_page, total_page));
				%>
				<c:if test="${session_id eq 'manager'}">
					<a href="/NewsWriteForm_Servlet" class="write">글쓰기</a>
				</c:if>
			</div>
		</div>
	</div> <!-- container -->
	
<script>
	$(function(){
		$(".location .dropdown>a").on("click",function(e){
			e.preventDefault();
			if($(this).next().is(":visible")){
				$(".location .dropdown > a").next().hide();
			}else{
				$(".location .dropdown > a").next().hide();
				$(this).next().show();
			}
		});
	});
	
	function formSearch() { //제목 검색
		var fm = document.news;
		fm.action = "/NewList_Servlet";
		fm.method = "post";
		fm.submit();
	}
	
	function goPage(pageNum) {
		var fm = document.news;
		fm.r_page.value = pageNum;
		fm.action = "/NewList_Servlet";
		fm.method = "post";
		fm.submit();
	}
	
	function NewsView_Servlet(news_view){
		var fm = document.news;
		fm.t_newsNo.value = news_view;
		fm.action = "/NewsView_Servlet";
		fm.method = "post";
		fm.submit();
	}
</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>