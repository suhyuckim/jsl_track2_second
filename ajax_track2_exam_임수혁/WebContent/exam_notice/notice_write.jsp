<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">	
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/layout.css" rel="stylesheet" >	
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
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>작성자</th>
							<td class="th_left">
								<input name="name"  class="input_100Per" type="text">
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td class="th_left">
								<input name="title"  class="input_100Per" type="text">
							</td>
						</tr>						
						<tr>
							<th>내용</th>
							<td class="th_left">
								<textarea name="content"></textarea>
							</td>
						</tr>
						
						<tr>
							<th><label for="file">파일 첨부</label></th>
							<td><input type="file" name="fileName_a" class="file" id="file"></label></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="javascript:noticecheck()"><input type="button" value="등록" class="btn_ok"></a>&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<a href="/NoticeList"><input type="button" value="목록" class="btn_list"></a>
			</div>
			</form>
		</div>
	</div>
<script>
	function noticecheck(){
		if(write.name.value==""){
			alert("작성자명을 입력하세요.");
			write.name.focus();
			return;
		}
		if(write.title.value==""){
			alert("제목을 입력하세요.");
			write.title.focus();
			return;
		}
		if(write.content.value==""){
			alert("내용을 입력하세요.");
			write.content.focus();
			return;
		}
		var file = write.fileName_a; 
		if(file.value != ""){

			var position = file.value.lastIndexOf("\\")+1;
			var fName = file.value.substr(position);
			var len = fName.length;
			
			if(len > 20){
				alert("첨부파일명 길이 20자리 이내");
				return;
			}
			var maxSize  = 2 * 1024 * 1024;
			var fileSize = 0;

			var browser=navigator.appName;
			
			if(browser=="Microsoft Internet Explorer"){ 
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else{	
				fileSize = file.files[0].size;
			}
			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.    ");
				return;
			}
		}
		write.action="/NoticeSave";
		write.method="post";
		write.submit();
	}	
</script>	
</body>
</html>