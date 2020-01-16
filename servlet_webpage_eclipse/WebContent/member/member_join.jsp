<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<link href="/css/join.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() { //아이디 중복검사 ajax
			$("#t_id").keyup(function() { //keyup 글자가 입력되거나 값이 바뀌었을때
					$("#id_check").empty();
					if ($('#id').val() != "") {
						var urlLocation = "/MemberJoinCheck";
						var params = "id=" + $('#t_id').val();
						$.ajax({
							type : "GET",
							url : urlLocation,
							data : params,
							dataType : "text",
							error : function() {
								alert('통신실패!!');
							},
							success : function(data) {
								//alert("통신데이터 값 : " + data);
								var data_1 = $.trim(data);
								if(data_1 == "사용가능한 아이디입니다."){
									$('#id_check_1').css("display", "block");
									$('#id_check_2').css("display", "none");
									$("#id_check_1").html(data);
								}else{
									$('#id_check_1').css("display", "none");
									$('#id_check_2').css("display", "block");
									$("#id_check_2").html(data);
								}
							}
						});
					}
				});
			});
</script>
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
				<fieldset>
					<h2 class="readonly" style="text-align:center">
					<i class="fas fa-sign-in-alt"></i>&nbsp;&nbsp;SIGN UP</h2>
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id">
									<input type="text" name="id" id="t_id" placeholder="아이디">
									<input type="button" onClick="check_id()" value="중복검사"/><br>
									<input type="hidden" name="id_check_value">
									<span id="id_check_1" style=color:green;></span>
									<span id="id_check_2" style=color:red;></span>
								</label>
							</li>
							<li>	
								<i class="fas fa-unlock-alt fa-2x" ></i>
								<label for="pw"><input type="password" name="pw" placeholder="비밀번호"></label>
							</li>	
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input type="password" name="re_pw" placeholder="비밀번호 재확인"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>	
								<label for="name"><input type="text" name="name" placeholder="이름"></label>
							</li>
							<li>
								<label for="phone">
								<select class="phone" name="telephone">
									<option value="">통신사</option>
									<option value="KT">KT</option>
									<option value="SKT">SKT</option>
									<option value="LG">LG</option>
								</select>
								<input type="text" name="phone_1" placeholder="010" class="phone">-
								<input type="text" name="phone_2" placeholder="2531" class="phone">-
								<input type="text" name="phone_3" placeholder="3213" class="phone">								
								</label>
								<label for="certifi"></label>
								<label for="certifi"></label>
							</li>
							<li>
								<select class="area" name="area">
									<option value="">지역</option>
									<option value="서울">서울</option>
									<option value="대전">대전</option>
									<option value="광주">광주</option>
									<option value="대구">대구</option>
									<option value="울산">울산</option>
									<option value="인천">인천</option>
								</select>
								<input type="text" name="address" placeholder="상세주소" class="address">
							</li>
							<li>
								<select class="sex" name="sex">
									<option value="">성별</option>
									<option value="남">남</option>
									<option value="여">여</option>
								</select>
								<input type="date" name=birth class="birth">
							</li>
							<li>
								<input type="text" name="email1" class="email">&#64;
								<input type="text" name="email2" class="email" >
								<select name="emailtype" class="email" onChange="mailcheck()">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
						<ul class="signup">
						<input type="button" value="✔ SIGN UP" onClick="check();">
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
		
		function check_id(){
			var fm = document.all;
			var id = document.all.id.value;
	         if (id ==""){
	            alert("입력된 아이디가 없습니다.");
	            document.all.id.focus();
	         }else{
	         	//window.open("/member/check_id.jsp?id="+id,"아이디 중복검사","width=420, height=220");
	         	var url   = "/CheckId";
	        	var title = "아이디 중복검사";
	         	window.open("",title,"width=420, height=220");
	         	fm.target = title;
	         	fm.action = "/CheckId";
	         	fm.method = "post";
	         	fm.submit();
	         }		
 		}	 
		
		function check() {
			if(all.id.value == ""){ //id체크
				alert("아이디를 입력하세요!");
				all.id.focus();
				return;
			}
			if(all.id_check_value.value == ""){ //id 중복체크 했는지 체크
				alert("아이디 중복체크 하세요!");
				return;
			}
			if(all.id.value != all.id_check_value.value){
				alert("아이디 변경 후 중복검사 하세요!");
				all.id.focus();
				return;
			}
			if(all.pw.value=="") {
				alert("비밀번호를 입력해주세요");
				all.pw.focus();
				return;
			}
			if(all.re_pw.value=="") {
				alert("비밀번호를 재입력해주세요");
				all.re_pw.focus();
				return;
			}
			if(all.pw.value != all.re_pw.value){ //비밀번호 = 비밀번호 확인이랑 값이 같은지 확인
				alert("비밀번호가 같지 않습니다.");
				form.pw.focus();
				return;
			}
			if(all.name.value=="") {
				alert("이름을 입력해주세요");
				all.name.focus();
				return;
			}
			if(all.telephone.value=="") {
				alert("통신사를 선택해주세요!");
				all.telephone.focus();
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
				all.action = "/MemberJoin";
				all.method = "post";
				all.submit();
			}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>