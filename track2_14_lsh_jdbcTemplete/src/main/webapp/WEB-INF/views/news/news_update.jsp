<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>    
<div class="sub_title">
		<h2><span style="color:gray">NEWS updateForm</span></h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">BOARD<i class="fa fa-plus btn_plus"></i></a>
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
		<div class="write_wrap">
		<h2 class="sr-only">>공지사항 글쓰기</h2>
			<form name="news">
			<input type="hidden" name="t_gubun" value="update">
			<input type="hidden" name="t_no" value="${t_dto.getNo()}">
			<div class="board_list">
				<table class="board_table">
					<caption class="sr-only">공지사항 입력 표</caption>
					<colgroup>
						<col width="12%">
						<col width="60%">
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>제목</th>
							<th colspan="3"><input type="text" name="t_title" value="${t_dto.getTitle()}"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th colspan="3"><textarea name="t_content">${t_dto.getContent()}</textarea></th>
						</tr>
						<tr>
							<th>등록자</th>
							<th style="text-align:left">${t_dto.getReg_info()}</th>
							<th>등록일</th>
							<th style="text-align:left">${t_dto.getReg_date()}</th>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div class="btn_wrap">
				<input type="button" value="저장" onClick="update()" class="btn_list">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="이전" class="btn_list" onClick="history.back();">
			</div>
			<br>
		</div>
	</div>

	<script>
		function update(){
			if(news.t_title.value==""){
				alert("제목 입력");
				news.t_title.focus();
				return;
			}
			if(news.t_content.value==""){
				alert("내용 입력");
				news.t_content.focus();
				return;
			}
			news.action = "/News";
			news.method = "post";
			news.submit();
		}
	</script>
	
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