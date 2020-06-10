<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function save(){
		if(write.t_title.value == ""){
			alert("제목을 입력해주세요.");
			return;
		}
		if(write.t_content.value == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		// 첨부파일 용량체크, 확장자 체크
		var fileName = write.fileName_a.value;
		if(fileName !=""){
			var pathFileName = fileName.lastIndexOf(".")+1;    //확장자 제외한 경로+파일명
			var extension = (fileName.substr(pathFileName)).toLowerCase();	//확장자명
			//파일명.확장자
			if(extension == "exe" || extension == "jar" || extension == "zip"){
				alert(extension +" 형식 파일은 업로드 안됩니다.");
				return;
			}		
			// 첨부 용량 체크		
			var file = write.fileName_a;
			if(file.value !=""){
				// 사이즈체크
				var maxSize  = 1024 * 1024 *  5 ;   //5MB
				var fileSize = 0;
				// 브라우저 확인
				var browser=navigator.appName;
				// 익스플로러일 경우
				if (browser=="Microsoft Internet Explorer"){
					var oas = new ActiveXObject("Scripting.FileSystemObject");
					fileSize = oas.getFile(file.value).size;
				}else {
				// 익스플로러가 아닐경우
					fileSize = file.files[0].size;
				}

				if(fileSize > maxSize){
					alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.    ");
					return;
				}	
			}
		}
		write.action = "/NoticeSave";
		write.method = "post";
		write.submit();
	}
</script>		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-edit"></i> NOTICE-write</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write" enctype="multipart/form-data">
				<input type="hidden" name="t_gubun" value="save">	
					<h2 class="readonly">제목, 첨부파일, 내용을 작성합니다</h2>
				
					<fieldset>
						<legend>공지사항 글쓰기</legend>
						
						<table class="table">
							<caption>공지사항 글쓰기</caption>
							<colgroup>
								<col width="20%">
								<col width="*">
							</colgroup>
							
							<tr>
								<th><label for="title">제목</label></th>
								<td><input type="text" name="t_title" id="title" class="title" placeholder="제목을 입력해주세요"></td>
							</tr>
							
							<tr>
								<th><label for="cont">내용</label></th>
								<td><textarea type="cont" name="t_content" id="cont" class="cont" placeholder="내용을 입력해주세요"></textarea>
							</tr>
							
							<tr>
								<th><label for="file">파일 첨부a</label></th>
								<td><input type="file" name="fileName_a" class="file" id="file"></label></td>
							</tr>
							
							<tr>
								<th><label for="file">파일 첨부b</label></th>
								<td><input type="file" name="fileName_b" class="file" id="file"></label></td>
							</tr>
							
							<tr>
								<td colspan="2">
								<input type="button" onclick="save()" value="저장" class="btn" >
								<input type="button" onclick="history.back();" value="목록" class="btn">
								</td>
							</tr>

							</table>
					</fieldset>
				</form>
			</div>
			

		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">JSL 인재개발원</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
	</body>
</html> 