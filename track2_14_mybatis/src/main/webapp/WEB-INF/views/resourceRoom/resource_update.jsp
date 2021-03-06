<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
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
					<a href="">자료실<i class="fa fa-plus btn_plus"></i></a>
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
	  <h2 class="sr-only">자료실 글쓰기</h2>
	  <form name="res" enctype="multipart/form-data">
	  	<input type="hidden" name="t_no" value="${t_dto.getNo()}">
	  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
			<table class="bord_table">
				<caption class="sr-only">공지사항 입력 표</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="first">
						<th>글쓴이</th>
						<td><input type="text" name="t_reg_id" value="manager" readonly></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="t_title" value="${t_dto.getTitle()}"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="t_content">${t_dto.getContent()}</textarea></td>
					</tr>
					<tr>
						<th>첨부 1</th>
						<td>
							<c:if test="${t_dto.getFile_1() ne null}">
								${t_dto.getFile_1()}
								&nbsp;파일삭제
								<input type="checkbox" class="checkB" name="t_delfile_1" value="${t_dto.getFile_1()}">
							</c:if>	
							<input type="file" name="t_file_1">
							<input type="hidden" name="t_ori_file_1" value="${t_dto.getFile_1()}">
						</td>
					</tr>
					<tr>
						<th>첨부 2</th>
						<td>
							<c:if test="${t_dto.getFile_2() ne null}">
								${t_dto.getFile_2()}
								&nbsp;파일삭제
								<input type="checkbox" class="checkB" name="t_delfile_2" value="${t_dto.getFile_2()}">
							</c:if>		
							<input type="file" name="t_file_2">
							<input type="hidden" name="t_ori_file_2" value="${t_dto.getFile_2()}">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_wrap">
				<input type="button" value="목록" class="btn_list" onClick="history.back()">&nbsp;
				<input type="button" onclick="resUpdate()" value="저장" class="btn_ok">
			</div>
		</form>
	  </div>
	  
	</div>
	<!-- end contents -->
	<script>
		function resUpdate() {
			if(res.t_title.value=="") {
				alert("제목을 입력");
				res.t_title.focus();
				return;
			}
			if(res.t_content.value=="") {
				alert("내용을 입력");
				res.t_content.focus();
				return;
			}			
			res.action="/ResourceUpdate";
			res.method="post";
			res.submit();
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