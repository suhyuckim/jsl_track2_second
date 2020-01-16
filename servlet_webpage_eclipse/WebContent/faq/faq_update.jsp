<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ include file="/common/common_header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title">
		<h2>FAQ update</h2>
			<%@ include file="/common/common_menu.jsp" %>
	</div>
	<div class="container">
		<div class="write_wrap">
		<h2 class="sr-only">faq update</h2>
			<form name="faq" action="/FaqUpdate_Servlet" method="post" onSubmit="return faqcheck();">
			<input type="hidden" name="faq_id" value="${t_dto.getFaq_id()}">
			<!-- action을 처리하기 전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<div class="board_list">
				<table class="board_table">
					<caption class="sr-only">faq 입력 표</caption>
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>글쓴이</th>
							<th><input type="hidden" name="writer" value="${session_id}">${session_name}</th>
						</tr>
						<tr>
							<th>질문</th>
							<th><input type="text" name="question" value="${t_dto.getQuestion()}"></th>
						</tr>
						<tr>
							<th>답변</th>
							<th><textarea name="answer">${t_dto.getAnswer()}</textarea></th>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="submit" value="저장" class="btn_ok">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='/FaqList_Servlet';">
			</div>
			</form>
		</div>
	</div>

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
		
		function faqcheck(){
			if(faq.question.value==""){
				alert("질문을 입력하세요");
				faq.question.focus();
				return false;
			}
			if(faq.answer.value==""){
				alert("답변을 입력하세요");
				faq.answer.focus();
				return false;
			}
			return true;
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>