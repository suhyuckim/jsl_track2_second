<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/common_header.jsp"%>
<%@ page import="common.CommonUtil"%>

<!DOCTYPE html>
<html lang="ko">
<div class="sub_title">
	<h2>FAQ list</h2>
	<%@ include file="/common/common_menu.jsp"%>
</div>

<div class="container">
	<div class="search_wrap clearfix">
		<div class="record_group">
			<p>
				총게시글 <span>${FaqCount}</span>건
			</p>
		</div>
		<form name="faq">
			<div class="search_group">
				<input type="hidden" name="r_page"> 
				<select class="select" name="t_sel">
					<option value="question"
						<c:if test="${t_sel eq 'question'}"> out.print("selected");</c:if>>제목</option>
					<option value="answer"
						<c:if test="${t_search eq 'answer'}"> out.print("selected");</c:if>>내용</option>
				</select> <input type="text" value="${t_search}" name="t_search"
					class="search_word">
				<button class="btn_search" type="button"
					onClick="javascript:formSearch()">
					<i class="fa fa-search"></i><span class="sr-only">검색버튼</span>
				</button>
			</div>
		</form>
		
		<form name="faq_delete_update">
			<input type="hidden" name="faq_id">
		</form>
		
	</div>
	<!-- search_wrap -->
	
	<div class="board_list">
		<div class="faq_group">
			<c:set var="forYN" value="true" />
			<c:if test="${t_dtos.size() > 0}">
				<c:forEach items="${t_dtos}" var="dtos">
					<c:if test="${forYN}">
						<c:if test="${v_count == for_count}">
							<ul>
								<li>
									<button class="accordion">${dtos.getQuestion()}</button>
									<div class="panel">
										<p>${dtos.getAnswer()}</p>
										<c:if test="${session_id eq 'manager'}">
											<span class="faq_button"> 
											<a href=javascript:UpdateFaq("${dtos.getFaq_id()}")>수정</a> 
											<a href=javascript:deleteFaq("${dtos.getFaq_id()}")>삭제</a>
											</span>
										</c:if>
									</div>
								</li>
							</ul>
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
					<button class="accordion"> 등록된 내용이 없습니다.</button>
						<div class="panel">
							<p>등록된 내용이 없습니다.</p>
						</div>
				</c:if>
		</div>
		<script>
			$(function() {
				$(".accordion").on("click", function() {
					$(".panel").not($(this).next().slideToggle()).slideUp();
					$(".accordion").removeClass("active");
					$(this).toggleClass("active");
				});
			});
		</script>

		<div class="paging">
			<%
				Integer cp = (Integer) request.getAttribute("current_page");
				int current_page = cp.intValue();
				Integer tp = (Integer) request.getAttribute("total_page");
				int total_page = tp.intValue();
				out.println(CommonUtil.pageList2(current_page, total_page));
			%>
			<c:if test="${session_id eq 'manager'}">
				<a href="/FaqWriteForm_Servlet" class="write">글쓰기</a>
			</c:if>
		</div>
	</div>
</div>

<script>
	$(function() {
		$(".location .dropdown>a").on("click", function(e) {
			e.preventDefault();
			if ($(this).next().is(":visible")) {
				$(".location .dropdown > a").next().hide();
			} else {
				$(".location .dropdown > a").next().hide();
				$(this).next().show();
			}
		});
	});

	function formSearch() { //제목 검색
		var fm = document.faq;
		fm.action = "/FaqList_Servlet";
		fm.method = "post";
		fm.submit();
	}
	
	function UpdateFaq(update_faq){
		var fm 			= document.faq_delete_update;
		fm.faq_id.value = update_faq;
		fm.action 		= "/FaqUpdateForm_Servlet";
		fm.method 		= "post";
		fm.submit();
	}
	
	function deleteFaq(delfaq_id){
		var yn = confirm("정말 삭제 하겠습니까?");
		if(yn){
			var fm 			= document.faq_delete_update;
			fm.faq_id.value = delfaq_id;
			fm.action 		= "/FaqDelete_Servlet";
			fm.method		= "post";
			fm.submit();
		}
	}
</script>
<%@ include file="/common/common_footer.jsp"%>
</body>
</html>