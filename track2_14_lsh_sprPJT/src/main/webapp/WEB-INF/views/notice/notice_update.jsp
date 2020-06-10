<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function update(){
		if(write.t_title.value == ""){
			alert("제목을 입력해주세요.");
			return;
		}
		if(write.t_content.value == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		write.action = "/NoticeUpdate";
		write.method = "post";
		write.submit();
	}
</script>		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-edit"></i> NOTICE-update</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write" enctype="multipart/form-data">
				<input type="hidden" name="t_no" value="${t_dto.getNo()}">
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
								<td><input type="text" name="t_title" id="title" class="title" value="${t_dto.getTitle()}"></td>
							</tr>
							
							<tr>
								<th><label for="cont">내용</label></th>
								<td><textarea type="cont" name="t_content" id="cont" class="cont"> ${t_dto.getContent()}</textarea>
							</tr>
							
							<tr>
								<th><label for="file">파일 첨부a</label></th>
								<td style="text-align:left">
									<c:if test="${t_dto.getFile_name_1() ne null}">
										${t_dto.getFile_name_1()}
									&nbsp;&nbsp;파일삭제
									<input type="checkbox" name="t_delCheckBox" value="${t_dto.getFile_name_1()}">
									</c:if>
									<br>
									<input type="file" name="fileName_a" class="file" id="file"></label>
									<input type="text" name="t_ori_fileName" value="${t_dto.getFile_name_1()}">
								</td>
							</tr>
							
							<tr>
								<td colspan="2">
								<input type="button" onclick="update()" value="저장" class="btn" >
								<input type="button" onclick="history.back();" value="이전" class="btn">
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