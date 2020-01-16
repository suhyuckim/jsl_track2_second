<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
	<div class="sub_title">
		<h2>NEWS view</h2>
		<div class="container">
			<%@ include file="/common/common_menu.jsp" %>
		</div>
	</div>
	<form name="news_view">
	<input type="hidden" name="t_news_no" value="${t_dto.getNews_no()}">
	<input type="hidden" name="t_fileName" value="${t_dto.getFile_name_1()}">
		<div class="container">
			<div class="board_view">
				<h2>${t_dto.getTitle()}</h2>
				<p class="info"><span class="user">${session_name}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"> ${t_dto.getHit()}</i>
				<div class="board_body">
					<c:if test="${t_dto.getFile_name_1() ne null}">	
						<img src="/file_room/news/${t_dto.getFile_name_1()}" alt="뉴스1" class="imgS"><br>
					</c:if>
					<p>${t_dto.getContent()}</p>
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
						<a href="/NewList_Servlet">목록</a>
						<c:if test="${session_id eq 'manager'}">	
							<a href=javascript:NewsUpdateForm_Servlet("${t_dto.getNews_no()}")>수정</a> 
							<a href="javascript:NewsDelete_Servlet()">삭제</a>
						</c:if>	
					</div>
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
	</form>
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
		
		function NewsUpdateForm_Servlet(news_update){
			var fm = document.news_view;
			fm.t_news_no.value = news_update;
			fm.action = "/NewsUpdateForm_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function NewsDelete_Servlet(){
			var yn = confirm("삭제하시겠습니까?");
			if(yn == true){
				var fm = document.news_view;
				fm.action = "/NewsDelete_Servlet";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>