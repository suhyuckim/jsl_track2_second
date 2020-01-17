<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
	<div class="sub_title">
		<h2>EVENT view</h2>
			<%@ include file="/common/common_menu.jsp" %>
	</div>
	<form name="event_view">
	<input type="hidden" name="t_event_no" value="${t_dto.getEvent_no()}">
	<input type="hidden" name="t_fileName" value="${t_dto.getFile_name_1()}">
	<div class="container">
		<div class="board_view">
			<h2>[공지] ${t_dto.getTitle()}</h2>
			<p class="info"><span class="user">${t_dto.getReg_id()}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"> ${t_dto.getHit()}</i>
			<div class="board_body">
				<p>이벤트 기간 : ${t_dto.getStart_date()}~${t_dto.getEnd_date()}<p>
				<p>${t_dto.getContent()}</p>
				<c:if test="${t_dto.getFile_name_1() ne null}">
				<p><i class="far fa-file-archive"></i>&nbsp;
				<a href="/common/filedown.jsp?t_file=${t_dto.getFile_name_1()}&t_gubun=event">${t_dto.getFile_name_1().substring(8)}</a>
				</p>
				</c:if>
			</div>
			<div class="prev_next">
				<a href="" class="btn_prev">
					<i class="fa fa-angle-left"></i>
					<span class="prev_wrap">
						<strong>이전글</strong>
						<span>이전글제목표시</span>
					</span>
				</a>
				<div class="btn_3wrap">
					<a href="/EventList_Servlet">목록</a> 
				<c:if test="${session_id eq 'manager'}">	
					<a href=javascript:EventUpdateForm_Servlet("${t_dto.getEvent_no()}")>수정</a> 
					<a href="javascript:EventDelete_Servlet()">삭제</a>
				</c:if>	
					<a href=javascript:EventApplyForm_Servlet("${t_dto.getEvent_no()}")>신청</a>
				</div>
	</form>			
				<a href="" class="btn_next">
					<span class="next_wrap">
						<strong>다음글</strong>
						<span>다음글제목표시</span>
					</span>
					<i class="fa fa-angle-right"></i>
				</a>
			</div>
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
		
		function EventUpdateForm_Servlet(event_update){
			var fm = document.event_view;
			fm.t_event_no.value = event_update;
			fm.action = "/EventUpdateForm_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function EventDelete_Servlet(){
			var yn = confirm("삭제하시겠습니까?");
			if(yn == true){
				var fm = document.event_view;
				fm.action = "/EventDelete_Servlet";
				fm.method = "post";
				fm.submit();
			}
		}
		
		function EventApplyForm_Servlet(event_apply){
			var fm = document.event_view;
			fm.t_event_no.value = event_apply;
			fm.action = "/EventApplyForm_Servlet";
			fm.method = "post";
			fm.submit();
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>