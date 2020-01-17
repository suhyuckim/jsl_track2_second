<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ include file="/common/common_header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title">
		<h2>이벤트 응모 신청</h2>
			<%@ include file="/common/common_menu.jsp" %>
	</div>
	<div class="container">
		<div class="write_wrap">
		<h2 class="sr-only">이벤트 글쓰기</h2>
			<form name="event_check" enctype="multipart/form-data">
			<input type="hidden" name="t_event_no" value="${t_dto.getEvent_no()}">
			<div class="board_list">
				<table class="board_table">
					<caption class="sr-only">공지사항 입력 표</caption>
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
							<th>제목</th>
							<th><input type="text" name="t_title"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th><textarea name="contents"></textarea></th>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="javascript:eventcheck()"><input type="button" value="등록" class="btn_ok"></a>&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='/EventList_Servlet';">
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
		
		function eventcheck(){
			if(event_check.t_title.value==""){
				alert("제목을 입력하세요");
				event_check.t_title.focus();
				return;
			}
			if(event_check.contents.value==""){
				alert("내용을 입력하세요");
				event_check.contents.focus();
				return;
			}
			event_check.action = "/EventApply_Servlet";
			event_check.method = "post";
			event_check.submit();
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>