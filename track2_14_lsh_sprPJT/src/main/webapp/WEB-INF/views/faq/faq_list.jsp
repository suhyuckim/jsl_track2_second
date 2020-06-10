<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="common.*"%>
<!doctype html>
<%@ include file="../common_head.jsp" %>
<script type="text/javascript">
	function goWriteForm(){
		faq.t_gubun.value = "writeForm";
		faq.action = "/Faq";
		faq.method = "post";
		faq.submit();		
	}
	function updateFaq(no){
		faq.t_gubun.value = "updateForm";
		faq.t_no.value = no;
		faq.action = "/Faq";
		faq.method = "post";
		faq.submit();		
	}
	function deleteFaq(no){
		var tf = confirm("삭제 하겠습니끼?")
		
		if(true){
			faq.t_no.value = no;
			faq.t_gubun.value = "delete";
			faq.action = "/Faq";
			faq.method = "post";
			faq.submit();
		}
	}
</script>
		<!--  header end -->
		<form name="faq">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_no">
		</form>	
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/Notice">NOTICE</a></h2>	
			<h2><a href="/Qna"> QnA</a></h2>
			<h2 class="color"><a href="/Faq"><i class="fas fa-check"></i> FAQ</a></h2>	
			<h2><a href="/News"> NEWS</a></h2>	
			<c:if test="${session_id eq 'manager'}">
				<h2><a href="/Manager"> MEMBER</a></h2>
			</c:if>
			</div>
			
			<!-- fqa start-->
		<div class="faq-box">
			
			<c:forEach items="${t_dtos}" var="dtos">
				<button class="accordion"> ▶ ${dtos.getQuestion()}</button>
				<div class="text">
					<p>
						${dtos.getAnswer()}
						<span class="buttonRight">
							<input type="button" onClick="updateFaq('${dtos.getNo()}')" value="수정">
							<input type="button" onClick="deleteFaq('${dtos.getNo()}')" value="삭제">
						</span>
					</p>
				</div>
			</c:forEach>
	
				<div class="page-number">
					<c:if test="${session_id eq 'manager'}">
						<a href="javascript:goWriteForm()" class="btn-write">글쓰기</a>
					</c:if>
				</div>
			</div>
		<!-- notice css end -->
		
		<!--  footer start  -->
			<div id="footer">
				<div class="footer-text">
					<ul class="sub-logo">
						<li><a href="index.html" alt="�꽌釉뚮줈怨�">EL WIDE</a></li>
					</ul>
					
					<ul class="copy">
						<li>Copyright �뱬 EL WIDE. All Rights Reserved.</li>
					</ul>
				</div>
			</div>
		</div>
	
		<script>
			$(document).ready(function() {
				$(".accordion").click(function() {
					$(".text").not($(this).next().slideToggle(250)).slideUp();
					$(this).siblings().removeClass("active");	
					$(this).toggleClass("active");	
				});
			});
		</script>
		
	</body>
</html>