<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script>
	function updateForm(){
		news.t_gubun.value = "updateForm";
		news.action = "/News";
		news.method = "post";
		news.submit();
	}
	function deleteNews(){
		var tf = confirm("정말로 삭제하시겠습니까?");
		if(tf == true){
			news.t_gubun.value = "delete";
			news.action = "/News";
			news.method = "post";
			news.submit();
		}
	}
</script>
<style>
.textAreaView {
		border = 0px;
	}
</style>
<form name="news">
	<input type="hidden" name="t_gubun">
	<input type="hidden" name="t_no" value="${t_dto.getNo()}">
</form>
	<div class="sub_title">
		<h2><span style="color:gray">NEWS view</span></h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
					<a href="/News">BOARD<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
							<a href="gratomgs/html">비전소개</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="/News">NEWS<i class="fa fa-plus btn_plus"></i></a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="board_view">
			<h2>${t_dto.getTitle()}</h2>
			<p class="info"><span class="user">${t_dto.getReg_info()}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"> ${t_dto.getHit()}</i>
			<div class="board_body">
				<textarea rows="20" cols="100" class="textAreaView" readonly>${t_dto.getContent()}</textarea>
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
					<a href="/News">목록</a> 
					<a href="javascript:updateForm()">수정</a> 
					<a href="javascript:deleteNews()">삭제</a>
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
	</script>

	<%@ include file="../footer.jsp" %>
 </body>
</html>