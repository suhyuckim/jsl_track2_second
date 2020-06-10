<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>
<!doctype html>
<html lang="ko">

		<!-- sub page start -->
		<div class="notice">
			<div class="sub-join">
			<h2><span><i class="fas fa-sign-in-alt"></i> My Information Update</span></h2>	
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" method="post" name="all" action="/User">
				<input type="hidden" name="t_gubun" value="userUpdate">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id"><input type="text" name="t_id" class="t_idd" value="${t_dto.getId()}" readonly></label>
							</li>
							<li>	
								<i class="fas fa-unlock-alt fa-2x" ></i>
								<label for="pw"><input type="password" name="t_pw_1" value="${t_dto.getPw()}"></label>
							</li>	
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input type="password" name="t_pw_2"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>						
								<label for="name"><input type="text" name="t_name" value="${t_dto.getName()}"></label>
							</li>
							<li>
								<label for="phone"><input type="text" name="t_tel" value="${t_dto.getTel()}" class="phone"></label>
							</li>
							<li>
								<input type="text" name="t_email_1" class="email" value="${t_dto.getEmail_1()}">&#64;
								<input type="text" name="t_email_2" class="email" value="${t_dto.getEmail_2()}">
								<select name="t_emailtype" class="email" onChange="emailType()">
									<option value="">직접입력</option>
									<option value="naver.com"<c:if test="${t_dto.getEmail_2() eq 'naver.com'}">selected</c:if>>naver.com</option>
									<option value="daum.net" <c:if test="${t_dto.getEmail_2() eq 'daum.net'}">selected</c:if>>daum.net</option>
									<option value="gmail.com"<c:if test="${t_dto.getEmail_2() eq 'gmail.com'}">selected</c:if>>gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
								<label for="agree"><input type="radio" name="t_info_yn"  value="y" id="agree" <c:if test="${t_dto.getInfo_yn() eq 'y'}">  checked</c:if>>  1년 정보유지</label>
								<label for="agree2"><input type="radio" name="t_info_yn" value="n" id="agree2" <c:if test="${t_dto.getInfo_yn() eq 'n'}"> checked</c:if>> 탈퇴시까지 정보유지</label>
							</li>
						</ul>
						
						<ul class="check">
							<p>
								<span style="color:red">회원탈퇴 신청합니다.</span>
								<input type="checkbox" name="t_user_exit" value='n' onChange="userExit()">
							</p>
						</ul>
						
						<ul class="signup">
						<input type="button" value="✔ 수정" onClick="update()">
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
			function userExit(){
				var checkVal = confirm("회원탈퇴 하시겠습니까?");
				if(checkVal){
					alert("회원님의 로그인 정보는 삭제됩니다.");
				 	all.t_gubun.value = "userExit";
					all.submit();
				} else {
					all.t_user_exit.checked = false;
				}
			}
			function emailType(){
				var eVal = all.t_emailtype.value;
				all.t_email_2.value = eVal;
			}
			function update() {
				if(all.t_pw_1.value=="") {
					alert("비밀번호를 입력해주세요");
					all.t_pw_1.focus();
					return;
				}
				if(all.t_pw_2.value=="") {
					alert("비밀번호를 재입력해주세요");
					all.t_pw_2.focus();
					return;
				}
				if(all.t_pw_1.value != all.t_pw_2.value) {
					alert("비밀번호가 일치하지 않습니다.");
					all.t_pw_2.focus();
					return;
				}
				if(all.t_name.value=="") {
					alert("이름을 입력해주세요");
					all.t_name.focus();
					return;
				}
				if(all.t_tel.value=="") {
					alert("연락받으실 번호를 입력해주세요");
					all.t_tel.focus();
					return;
				}
				if(all.t_email_1.value=="") {
					alert("이메일을 입력해주세요");
					all.t_email_1.focus();
					return;
				}	
				if(all.t_email_2.value=="") {
					alert("이메일을 입력해주세요");
					all.t_email_2.focus();
					return;
				}	
				all.submit();
			}
		</script>		
	</body>
</html>