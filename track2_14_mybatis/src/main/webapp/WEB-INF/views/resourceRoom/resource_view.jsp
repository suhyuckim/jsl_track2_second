<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script>
	 function resUpdate(){
		res.action = "/ResourceUpdateForm";
		res.method = "post";
		res.submit(); 
	 }
	 function resDelete(){
		 
	 }
</script>
<form name="res">
	<input type="hidden" name="t_no" value="${t_dto.getNo()}">
</form>
	<!-- sub contents -->
	<div class="sub_title_res">
		<h2></h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">질문답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">질문답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="board_view">
			<h2>${t_dto.getTitle()}</h2>
			<p class="info"><span class="user">${t_dto.getReg_info()}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"></i> ${t_dto.getHit()}</p>
			<div class="board_pds">
			첨부파일 1 : <a href="/ResFileDown?t_file=${t_dto.getFile_1()}&t_gubun=resDir">${t_dto.getFile_1()}</a><br>
			첨부파일 2 : <a href="/ResFileDown?t_file=${t_dto.getFile_2()}&t_gubun=resDir">${t_dto.getFile_2()}</a>
			</div>
			<div class="board_body">
				<textarea readonly>${t_dto.getContent()}</textarea>
			</div>
			<div class="prev_next">
				<a href="" class="btn_prev"><i class="fa fa-angle-left"></i>
				<span class="prev_wrap">
					<strong>이전글</strong><span>이전글제목표시</span>
				</span>
				</a>
				<div class="btn_3wrap">
					<a href="/Resource">목록</a> 
					<a href="javascript:resUpdate()">수정</a> 
					<a href="javascript:resDelete()">삭제</a>
				</div>
				<a href="" class="btn_next">
				<span class="next_wrap">
					<strong>다음글</strong><span>다음글제목표시</span>
				</span>
				<i class="fa fa-angle-right"></i></a>
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