<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function newsDelete(){
		var tf = confirm("삭제 하겠습니끼?")
		if(true){
			write.t_gubun.value = "delete";
			write.action = "/News";
			write.method = "post";
			write.submit();
		}
	}
	
	function newsUpdate(){
		write.t_gubun.value = "updateForm";
		write.action = "/News";
		write.method = "post";
		write.submit();
	}
</script>		
		
		<!-- sub page start -->
		<div class="news">
			<div class="sub-notice">
			<h2><span><i class="fas fa-edit"></i> NEWS-view</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write">
				<input type="hidden" name="t_gubun">
				<input type="hidden" name="t_no" value="${t_dto.getNo()}">				
					<h2 class="readonly">제목, 첨부파일, 내용을 작성합니다</h2>
				
					<fieldset>
						<legend>공지사항 글쓰기</legend>
						
						<table class="table">
							<caption>공지사항 글쓰기</caption>
							<colgroup>
								<col width="15%">
								<col width="60%">
								<col width="15%">
								<col width="*">
							</colgroup>
							<tr>
								<th><label for="title">제목</label></th>
								<td align="left">&nbsp;&nbsp;${t_dto.getTitle()}</td>
								<th><label for="title">조회수</label></th>
								<td>&nbsp;&nbsp;${t_dto.getHit()}</td>
							</tr>
							<tr>
								<th><label for="cont">내용</label></th>
								<td colspan="3">
									<textarea type="cont" name="t_content" id="cont" class="cont" readonly>${t_dto.getContent()}</textarea>
							</tr>
							<tr>
								<th>등록자</th>
								<td align="left">&nbsp;&nbsp;${t_dto.getReg_info()}</td>
								<th>등록일</th>
								<td align="left">&nbsp;&nbsp;${t_dto.getReg_date()}</td>
							</tr>

							<tr>
								<td colspan="4">
									<input type="button" onclick="history.back();" value="목 록" class="btn">
									<input type="button" value="수 정" onClick="newsUpdate()" class="btn" >
									<input type="button" onClick="newsDelete()" value="삭 제" class="btn" >
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