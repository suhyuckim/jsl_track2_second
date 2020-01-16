<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<link href="/css/join.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
	<section id="main_visual">
		<div class="visual_wrap">
			<h2 class="sr-only">메인 비쥬얼</h2>
			<ul>
				<!-- <li><img src="images/bg.png" alt="학생단체관람" class="w100"></li> -->
				<li class="one"></li>
			</ul>
			<div class="visual_inner">
				<p class="title">CREATIVE <strong>DESIGN</strong></p> 
				<p class="txt">Challenging Technology Development, <br/> Leading-Edge Enterprise</p>
			</div>
		</div>
	</section>

	<section class="news_group">
		<div class="join-box">
			<form class="join" name="all">
			<input type="hidden" name="h_pw" value="${t_dto.getPw()}">			
			<input type="hidden" name="id" value="${t_dto.getId()}">			
				<fieldset>
					<h2 class="readonly" style="text-align:center">
					<i class="fas fa-user-circle fa-4x"></i></h2>
						<ul class="id_pw">
							<li>
								<label for="id">${t_dto.getId()}</label>
							</li>
							<li>	
								<label for="pw">${t_dto.getReg_date()}</label>
							</li>
							<li>	
								<label for="re_pw"><input type="password" name="re_pw" placeholder="비밀번호"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>	
								<label for="name"><input type="text" name="name" placeholder="이름" value="${t_dto.getName()}"></label>
							</li>
							<li>
								<label for="phone">
								<select class="phone" name="telecom">
									<option value="KT" 	<c:if test="${t_dto.getTelecom() eq 'KT'}">  out.print("selected");</c:if>>KT</option>
									<option value="SKT" <c:if test="${t_dto.getTelecom() eq 'SKT'}"> out.print("selected");</c:if>>SKT</option>
									<option value="LG" 	<c:if test="${t_dto.getTelecom() eq 'LG'}">  out.print("selected");</c:if>>LG</option>
								</select>
								<input type="text" name="phone_1" placeholder="010"  class="phone"  value="${t_dto.getPhone_1()}">-
								<input type="text" name="phone_2" placeholder="2531" class="phone" 	value="${t_dto.getPhone_2()}">-
								<input type="text" name="phone_3" placeholder="3213" class="phone" 	value="${t_dto.getPhone_3()}">								
								</label>
								<label for="certifi"></label>
								<label for="certifi"></label>
							</li>
							<li>
								<select class="area" name="area">
									<option value="서울" <c:if test="${t_dto.getArea() eq '서울'}"> out.print("selected");</c:if>>서울</option>
									<option value="대전" <c:if test="${t_dto.getArea() eq '대전'}"> out.print("selected");</c:if>>대전</option>
									<option value="광주" <c:if test="${t_dto.getArea() eq '광주'}"> out.print("selected");</c:if>>광주</option>
									<option value="대구" <c:if test="${t_dto.getArea() eq '대구'}"> out.print("selected");</c:if>>대구</option>
									<option value="울산" <c:if test="${t_dto.getArea() eq '울산'}"> out.print("selected");</c:if>>울산</option>
									<option value="인천" <c:if test="${t_dto.getArea() eq '인천'}"> out.print("selected");</c:if>>인천</option>
								</select>
								<input type="text" name="address" placeholder="상세주소" class="address" value="${t_dto.getAddress()}">
							</li>
							<li>
								<select class="sex" name="sex">
									<option value="남" <c:if test="${t_dto.getSex() eq '남'}"> out.print("selected");</c:if>>남</option>
									<option value="여" <c:if test="${t_dto.getSex() eq '여'}"> out.print("selected");</c:if>>여</option>
								</select>
								<input type="text" name=birth class="birth" placeholder="(ex) 1992-12-31" value="${t_dto.getBirth()}">
							</li>
							<li>
								<input type="text" name="email1" class="email" value="${t_dto.getEmail_1()}">&#64;
								<input type="text" name="email2" class="email" value="${t_dto.getEmail_2()}">
								<select name="emailtype" class="email" onChange="mailcheck()">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
						<ul class="signup">
						<input type="button" value="정보수정" onClick="check();">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="회원탈퇴" onClick="deleteMember();">
						</ul>
				</fieldset>
			</form>
			</div>
	</section>
	
	<script type="text/javascript">
		$(function(){
			$(".gnb>.nav_1depth>li").hover(function(){
				//console.log("gnb>.nav_1depth>li");
				$(this).parent().addClass("active");
				$(".taball").hide();
				$($(this).attr("href")).show();
			});
		});
		
		function mailcheck(){
				var email = document.all.emailtype.value;
			if(email == "직접입력"){
				document.all.email2.value = "";
			}else {
				document.all.email2.value = email;
			}
		}
		
		function deleteMember(){
			if(all.re_pw.value=="") {
				alert("비밀번호를 입력해주세요");
				all.re_pw.focus();
				return;
			}		
			if(confirm("정말 탈퇴 하겠습니까?")){
				all.action = "/MemberDelete";	
				all.method = "post";
				all.submit();
			}		
		}
		
		function check() {
			if(all.re_pw.value=="") {
				alert("비밀번호를 입력해주세요");
				all.re_pw.focus();
				return;
			}
			if(all.name.value=="") {
				alert("이름을 입력해주세요");
				all.name.focus();
				return;
			}
			if(all.telecom.value=="") {
				alert("통신사를 선택해주세요!");
				all.telecom.focus();
				return;
			}
			if(all.phone_1.value=="") {
				alert("연락받으실 번호를 입력해주세요");
				all.phone_1.focus();
				return;
			}
			if(all.phone_1.value.length != 3) { 
				alert("전화번호 입력 오류!");
				all.phone_1.focus();
				return;
			}	
			if(all.phone_2.value=="") {
				alert("연락받으실 번호를 입력해주세요");
				all.phone_2.focus();
				return;
			}
			if(all.phone_2.value.length != 4) { 
				alert("전화번호 입력 오류!");
				all.phone_2.focus();
				return;
			}	
			if(all.phone_3.value=="") {
				alert("연락받으실 번호를 입력해주세요");
				all.phone_3.focus();
				return;
			}
			if(all.phone_3.value.length != 4) { 
				alert("전화번호 입력 오류!");
				all.phone_3.focus();
				return;
			}	
			if(all.area.value=="") {
				alert("지역을 선택해주세요!");
				all.area.focus();
				return;
			}
			if(all.address.value=="") {
				alert("주소를 입력해주세요!");
				all.address.focus();
				return;
			}
			if(all.sex.value=="") {
				alert("성별을 선택해주세요!");
				all.sex.focus();
				return;
			}
			if(all.birth.value=="") {
				alert("생년월일을 입력해주세요!");
				all.brith.focus();
				return;
			}
			if(all.email1.value=="") {
				alert("이메일 아이디를 입력해주세요");
				all.email1.focus();
				return;
			}	
			if(all.email2.value=="") {
				alert("이메일을 입력해주세요");
				all.email2.focus();
				return;
			}	
				all.action = "/MemberUpdate";
				all.method = "post";
				all.submit();
			}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>