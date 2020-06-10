<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common_head.jsp" %>
<!doctype html>
<html lang="ko">
	
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-join">
			<h2><span><i class="fas fa-sign-in-alt"></i> My Information</span></h2>	
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" method="post" name="all" action="/User">
				<input type="hidden" name="t_gubun" value="updateForm">
				<input type="hidden" name="t_pw" value="${t_dto.getPw()}">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id">
									<input type="text" name="t_id" value="${t_dto.getId()}" class="t_idd" readonly>
								</label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>						
								<label for="name"><input type="text" name="t_name" value="${t_dto.getName()}" readonly></label>
							</li>
							<li>
								<label for="phone"><input type="text" name="t_tel" value="${t_dto.getTel()}" class="phone" readonly></label>
							</li>
							<li>
								<input type="text" name="t_email_1" value="${t_dto.getEmail_1()}" class="t_email" readonly>&#64;
								<input type="text" name="t_email_2" value="${t_dto.getEmail_2()}" class="t_email" readonly>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
							<c:if test="${t_dto.getInfo_yn() eq 'y'}">
								<label for="agree"
									><input type="radio" name="t_info_yn"  value="y" id="agree" checked> 1년 정보유지
								</label>
							</c:if>	
							<c:if test="${t_dto.getInfo_yn() eq 'n'}">
								<label for="agree2">
									<input type="radio" name="t_info_yn" value="n" id="agree2"> 탈퇴시까지 정보유지
								</label>
							</c:if>		
							</li>
						</ul>
						
						<ul class="signup">
						<input type="button" value="✔ 정보수정" onClick="updateForm();">
						</ul>
				</fieldset>
			</form>
			</div>
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
	
		<script type="text/javascript">
			function updateForm() {
				var pwCheckVal = prompt("회원님의 비밀번호를 입력하세요.");
				if(pwCheckVal != all.t_pw.value){
					alert("비밀번호가 일치하지 않습니다.");
					return;
				}
				all.submit();
			}
		</script>		
	</body>
</html>