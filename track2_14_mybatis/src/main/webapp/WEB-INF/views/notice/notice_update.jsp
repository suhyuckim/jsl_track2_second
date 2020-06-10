<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>    
<!-- sub contents -->
	<div class="sub_title_notice">
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
					<a href="/Notice">공지사항<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">DW인터뷰</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
	  <div class="write_wrap">
	  <h2 class="sr-only">공지사항 글쓰기</h2>
	  <form name="notice">
	  	<input type="hidden" name="t_no" value="${t_dto.getNo()}">
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="15%">
					<col width="*">
					<col width="15%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>제목</th>
						<td colspan="3"><input type="text" name="t_title" value="${t_dto.getTitle()}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea name="t_content">${t_dto.getContent()}</textarea></td>
					</tr>
					<tr>
						<th>첨부</th>
						<td colspan="3"><input type="file" name="photo"></td>
					</tr>
					<tr>
						<th>등록자</th>
						<td>${t_dto.getReg_info()}</td>
						<th>등록일</th>
						<td>${t_dto.getReg_date()}</td>
					</tr>
						<tr>
						<th>수정자</th>
						<td>${t_dto.getUpdate_id()}</td>
						<th>수정일</th>
						<td>${t_dto.getUpdate_date()}</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="button" value="수 정" onClick="noticeUpdate()" class="btn_reset">&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;
				<input type="button" value="이 전" class="btn_list" onClick="history.back()">
			</div>
		</form>
	  </div>
	  
	</div>
	<!-- end contents -->
	<script>
		function noticeUpdate() {
			if(notice.t_title.value=="") {
				alert("제목을 입력");
				notice.t_title.focus();
				return;
			}
			if(notice.t_content.value=="") {
				alert("내용을 입력");
				notice.t_content.focus();
				return;
			}
			notice.action="/NoticeUpdate";
			notice.method="post";
			notice.submit();
		}
	</script>
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