<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_head.jsp" %>
<!doctype html>
<html lang="ko">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" language="javascript">
    $(document).ready(function(){
    	$("#idCheckBtn").click(function(){  
    	 	var urlLocation="/UserIdCheck";  
    	    var params= "t_id="+all.t_id.value; 
    	    if(all.t_id.value == ""){
    	    	alert("아이디를 입력해주세요.");
    	    	all.t_id.focus();
    	    	return;
    	    }
			$.ajax({
				type : "POST",
				url : urlLocation,
				data: params,
				dataType : "text",
				error : function(){
					alert('통신실패!!');
				},
				success : function(data){
//					alert("data : "+data);
 					$(".t_id_info").html(data);
				 	if($.trim(data) =="중복된 아이디입니다."){
						$(".t_id_info").css("color","red");
						all.t_id_checkValue.value = "";
					} else {
						all.t_id_checkValue.value = all.t_id.value;
						$(".t_id_info").css("color","green");
 					} 
				} 
			});
    	 });
    });
</script> 
	
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-join">
			<h2><span><i class="fas fa-sign-in-alt"></i> SIGN UP</span></h2>	
				<p>- 회원가입을 위해, 작성해주세요 -</p>
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" method="post" name="all" action="/User">
				<input type="hidden" name="t_gubun" value="userSave">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id">
									<input type="text" name="t_id" class="t_id" placeholder="아이디">
									<input type="button" class="t_id_button" id="idCheckBtn" value="ID중복검사">
									<span class="t_id_info"></span>
									<input type="hidden" name="t_id_checkValue">
								</label>
							</li>
							<li>	
								<i class="fas fa-unlock-alt fa-2x" ></i>
								<label for="pw"><input type="password" name="t_pw_1" placeholder="비밀번호"></label>
							</li>	
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input type="password" name="t_pw_2" placeholder="비밀번호 재확인"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>						
								<label for="name"><input type="text" name="t_name" placeholder="이름"></label>
							</li>
							<li>
								<label for="phone"><input type="text" name="t_tel" placeholder="연락처 ex)010-3423-2534" class="phone"></label>
							</li>
							<li>
								<input type="text" name="t_email_1" class="email">&#64;
								<input type="text" name="t_email_2" class="email">
								<select name="t_emailtype" class="email" onChange="emailType()">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
								<label for="agree"><input type="radio" name="t_info_yn"  value="y" id="agree" checked> 1년 정보유지</label>
								<label for="agree2"><input type="radio" name="t_info_yn" value="n" id="agree2"> 탈퇴시까지 정보유지</label>
							</li>
							<li>
							<label for="yak1"><input type="checkbox" name="yak1" id="yak1">이용약관</label>
								<a href="#">전문보기</a>
								
							<label for="yak2"><input type="checkbox" name="yak2" id="yak2">개인정보이용동의</label>
								<a href="#">전문보기</a>
							</li>
						</ul>
						
						<ul class="signup">
						<input type="button" value="✔ SIGN UP" onClick="check();">
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
			function emailType(){
				var eVal = all.t_emailtype.value;
				all.t_email_2.value = eVal;
			}
			function check() {
				if(all.t_id.value=="") {
					alert("아이디를 입력해주세요");
					all.t_id.focus();
					return;
				}
				if(all.t_id_checkValue.value == ""){
					alert("ID중복검사를 해주세요.");
					all.t_id.focus();
					return;
				}
				if(all.t_id_checkValue.value != all.t_id.value){
					alert("ID 수정후 중복검사를 해주세요.");
					all.t_id.focus();
					return;
				}
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
				if(!(all.yak1.checked)) {
					alert("이용약관 동의를 체크해주세요");
					all.yak1.focus();
					return;
				}					
				if(!(all.yak2.checked)) {
					alert("개인정보 동의를 체크해주세요");
					all.yak2.focus();
					return;
				}					
				all.submit();
			}
		</script>		
	</body>
</html>