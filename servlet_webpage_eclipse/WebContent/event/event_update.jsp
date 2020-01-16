<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
<%@ include file="/common/common_header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title">
		<h2>EVENT write</h2>
			<%@ include file="/common/common_menu.jsp" %>
	</div>
	<div class="container">
		<div class="write_wrap">
		<h2 class="sr-only">이벤트 글쓰기</h2>
			<form name="event_check" enctype="multipart/form-data">
			<input type="hidden" name="t_event_no"  value="${t_dto.getEvent_no()}">
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
							<th><input type="text" name="t_title" value="${t_dto.getTitle()}"></th>
						</tr>
						<tr>
							<th>내용</th>
							<th><textarea name="contents">${t_dto.getContent()}</textarea></th>
						</tr>
						<tr>
							<th>이벤트 기간</th>
							<th>
								<input type="hidden" name="t_date">
								<input type="date" name="start_date" class="start_date" value="${t_dto.getStart_date()}">~<input type="date" name="end_date" value="${t_dto.getEnd_date()}">
							</th>
						</tr>						
						<tr>
							<th>첨부</th>
							<th>
							<c:if test="${t_dto.getFile_name_1() ne null}">
								<a href="/common/filedown.jsp?t_file=${t_dto.getFile_name_1()}&t_gubun=notice">${t_dto.getFile_name_1()}</a>
								&nbsp;&nbsp;파일삭제
								<input type="checkbox" name="checkBox_del_fileName" value="${t_dto.getFile_name_1()}">
								<br>
							</c:if>	
								<br>
								<input type="file" name="fileName_a">
								<input type="hidden" name="ori_fileName_a" value="${t_dto.getFile_name_1()}">
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="javascript:eventcheck('${reg_date}')"><input type="button" value="등록" class="btn_ok"></a>&nbsp;&nbsp;
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
		
		function eventcheck(reg_d){
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
			if(event_check.start_date.value==""){
				alert("시직일을 입력하세요");
				event_check.start_date.focus();
				return;
			}
			if(event_check.end_date.value==""){
				alert("종료일을 입력하세요");s
				event_check.end_date.focus();
				return;
			}
			
			event_check.t_date.value = reg_d;
			var s_date = js_removeChar(event_check.start_date.value, '-');
			var e_date = js_removeChar(event_check.end_date.value, '-');
			if(reg_d > s_date){
				alert("시직일자는 오늘 이후로 설정해야 합니다.");
				event_check.start_date.focus();
				return;
			}
			if(s_date > e_date){
				alert("종료일자는 시작일 이후로 설정해야 합니다.");
				event_check.end_date.focus();
				return;					
			}
			
			// 첨부 용량 체크 
			var file = event_check.fileName_a; 
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
			event_check.action = "/EventUpdate_Servlet";
			event_check.method = "post";
			event_check.submit();
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>