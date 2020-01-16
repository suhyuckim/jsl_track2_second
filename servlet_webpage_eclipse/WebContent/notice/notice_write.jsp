<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ include file="/common/common_header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title">
		<h2>NOTICE write</h2>
			<%@ include file="/common/common_menu.jsp" %>
	</div>
	<div class="container">
		<div class="write_wrap">
		<h2 class="sr-only">공지사항 글쓰기</h2>
			<form name="notice" action="/NoticeSave_Servlet" method="post" enctype="multipart/form-data" onSubmit="return noticecheck();">
			<!-- action을 처리하기 전에 check()사용자 함수를 실행하고 되돌아 와라-->
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
							<th><input type="text" name="title"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th><textarea name="contents"></textarea></th>
						</tr>
						<tr>
							<th>첨부</th>
							<th><input type="file" name="fileName_a" class="file" id="file"></th>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="submit" value="등록" class="btn_ok">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list" onClick="location.href='/NoticeList';">
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
		
		function noticecheck(){
			if(notice.title.value==""){
				alert("제목을 입력하세요");
				notice.title.focus();
				return false;
			}
			if(notice.contents.value==""){
				alert("내용을 입력하세요");
				notice.contents.focus();
				return false;
			}
			return true;
			// 첨부 용량 체크 
			var file = notice.fileName_a; 
			if(file.value != ""){ //첨부했을때 사이즈 체크
			var position = file.value.lastIndexOf("\\")+1;
			var fName = file.value.substr(position);
			var len = fName.length;
				
			if(len > 20){
				alert("첨부파일명 길이 20자리 이내");
				return;
			}
			// 사이즈체크
			var maxSize  = 2 * 1024 * 1024    //2MB
			var fileSize = 0;
			var browser=navigator.appName; // 브라우저 확인
			if(browser=="Microsoft Internet Explorer"){ // 익스플로러일 경우
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else{	// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}
			// alert("파일사이즈 : "+fileSize);
			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.");
				return;
				}
			}
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>