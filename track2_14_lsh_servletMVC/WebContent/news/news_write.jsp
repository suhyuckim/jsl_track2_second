<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common_head.jsp" %>  		
		<!--  header end -->
<script type="text/javascript">
	function newsSave(){
		if(write.t_title.value ==""){
			alert("제목 입력!");
			return;
		}
		if(write.t_content.value ==""){
			alert("내용 입력!");
			return;
		}
		write.submit();
	}
</script>		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-edit"></i> NEWS-write</span></h2>	
			</div>
			<div class="notice-write">
			<form name="write" method="post" action="<%=request.getContextPath()%>/News">
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
								<td colspan="2">
								<input type="button" onClick="newsSave()" value="저장" class="btn" >
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