<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : track2 수련생 성명	
	******************************************** 
 -->
<title>track2 임수혁</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/layout.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(
		function() {
			//좋아요 ajax
			$("#btnOK").click(function() {
				var urlLocation = "/NoticeLike";
				var params = "no=" + $('#t_no').val();
				$.ajax({
					type : "GET",
					url : urlLocation,
					data : params,
					dataType : "text",
					error : function() {
						alert('통신실패!!');
					},
					success : function(data) {
						alert("통신데이터 값 : " + data);
						$("#divLike").html(data);
					}
				});
			});
				//아이디 중복검사 ajax	    	
			$("#t_pw").keyup(
				function() { //keyup 글자가 입력되거나 값이 바뀌었을때
					$("#pw_yn").empty();
					if ($('#t_pw').val() != "") {
						var urlLocation = "/MemberLoginCheck";
						var params = "t_id=" + $('#t_id').val()+ "&t_pw=" + $('#t_pw').val();
						$.ajax({
							type : "GET",
							url : urlLocation,
							data : params,
							dataType : "text",
							error : function() {
								alert('통신실패!!');
							},
							success : function(data) {
								//alert("통신데이터 값 : " + data);
								$("#pw_yn").html(data);
							}
						});
					}
				});
			});
</script>
<style>
#pw_yn {
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<!--
	******************************************** 
		성명 : track2 수련생 성명	
	******************************************** 
 -->
		<div class="leftmargin">
			<h1>성명 : track2_임수혁</h1>
		</div>
		<div class="write_wrap">
			<form name="write" enctype="multipart/form-data">
				<input type="hidden" name="t_notice_no">
				<div class="board_list">
					좋아요! <span id="divLike">${t_dto.getLikecount()}</span> <input
						type="button" id="btnOK" , value="좋아요!"> <input
						type="hidden" id="t_no" , value="${t_dto.getNotice_no()}">

					<input type="text" id="t_id" size="7">
					<input type="text" id="t_pw" size="7"> 
					<span id="pw_yn"></span>

					<table class="board_table">
						<colgroup>
							<col width="12%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>작성자</th>
								<td class="th_left">${t_dto.getReg_id()}</td>
							</tr>

							<tr>
								<th>제목</th>
								<td class="th_left">${t_dto.getTitle()}</td>
							</tr>

							<tr>
								<th>내용</th>
								<td class="th_left"><textarea name="content">${t_dto.getContent()}</textarea></td>
							</tr>

							<tr>
								<th>첨부</th>
								<c:if test="${t_dto.getFile_name() ne null}">
									<div>
										<td><a
											href="/common/filedown.jsp?t_file=${t_dto.getFile_name()}&t_gubun=notice">${t_dto.getFile_name().substring(8)}</a>
										</td>
									</div>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn_wrap">
					<a href="/NoticeList"><input type="button" value="목록"
						class="btn_ok"></a>&nbsp;&nbsp; <a
						href=javascript:NoticeUpdate("${t_dto.getNotice_no()}")> <input
						type="button" value="수정" class="btn_reset"></a>&nbsp;&nbsp; <a
						href="javascript:deleteNotice()"> <input type="button"
						value="삭제" class="btn_list"></a>
				</div>
			</form>
		</div>
	</div>
	<script>
		function deleteNotice() {
			var yn = confirm("정말 삭제 하겠습니까?");
			if (yn == true) {
				var fm = document.write;
				fm.action = "/NoticeDelete";
				fm.method = "post";
				fm.submit();
			}
		}

		function NoticeUpdate(noticeupdate) {
			var fm = document.write;
			fm.t_noticeNo.value = noticeupdate;
			fm.action = "/NoticeUpdateForm";
			fm.method = "post";
			fm.submit();
		}
	</script>
</body>
</html>