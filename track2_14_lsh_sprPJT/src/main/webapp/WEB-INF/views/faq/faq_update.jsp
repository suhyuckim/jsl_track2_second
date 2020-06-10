<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function faqUpdate(){
		if(write.t_question.value == ""){
			alert(" 질문 입력! ");
			return;
		}
		if(write.t_answer.value == ""){
			alert(" 답변 입력! ");
			return;
		}
		write.submit();
	}
</script>		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-edit"></i> FAQ-update</span></h2>	
			</div>
			<div class="notice-write">
			<form name="write" method="post" action="/Faq">
				<input type="hidden" name="t_gubun" value="update">
				<input type="hidden" name="t_no" value="${t_dto.getNo()}">
					<h2 class="readonly">제목, 첨부파일, 내용을 작성합니다</h2>
				
					<fieldset>
						<legend>공지사항 글쓰기</legend>
						
						<table class="table">
							<caption>공지사항 글쓰기</caption>
							<colgroup>
								<col width="15%">
								<col width="*">
								<col width="15%">
								<col width="*">
							</colgroup>
							
							<tr>
								<th><label for="title"> 질 문 </label></th>
								<td colspan="3"><input type="text" name="t_question" id="title" class="title" value="${t_dto.getQuestion()}"></td>
							</tr>
							
							<tr>
								<th><label for="cont"> 답 변 </label></th>
								<td colspan="3"><textarea type="cont" name="t_answer" id="cont" class="cont">${t_dto.getAnswer()}</textarea>
							</tr>

							<tr>
								<th><label for="title"> 순 번 </label></th>
								<td colspan="3" align="left"><input type="text" name="t_seq" id="title" class="titleFaq" value="${t_dto.getSeq()}"></td>
							</tr>
							
							<tr>
								<th><label for="title"> 등록자 </label></th>
								<td align="left">${t_dto.getReg_member_info()}</td>
								<th><label for="title"> 등록일 </label></th>
								<td align="left">${t_dto.getReg_date()}</td>
							</tr>
							
							<tr>
								<th><label for="title"> 수정자 </label></th>
								<td align="left">${t_dto.getUpdate_member_info()}</td>
								<th><label for="title"> 수정일 </label></th>
								<td align="left">${t_dto.getUpdate_date()}</td>
							</tr>

							<tr>
								<td colspan="4">
								<input type="button" onClick="faqUpdate()" value="수정" class="btn" >
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