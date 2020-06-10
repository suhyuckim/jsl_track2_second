<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function faqSave(){
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
			<h2><span><i class="fas fa-edit"></i> FAQ-write</span></h2>	
			</div>
			<div class="notice-write">
			<form name="write" method="post" action="<%=request.getContextPath()%>/Faq">
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
								<th><label for="title"> 질 문 </label></th>
								<td><input type="text" name="t_question" id="title" class="title" placeholder="질문을 입력해주세요"></td>
							</tr>
							
							<tr>
								<th><label for="cont"> 답 변 </label></th>
								<td><textarea type="cont" name="t_answer" id="cont" class="cont" placeholder="답변을 입력해주세요"></textarea>
							</tr>

							<tr>
								<th><label for="title"> 순 번 </label></th>
								<td align="left"><input type="text" name="t_seq" id="title" class="titleFaq" placeholder="정렬 순번"></td>
							</tr>

							<tr>
								<td colspan="2">
								<input type="button" onClick="faqSave()" value="저장" class="btn" >
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









    