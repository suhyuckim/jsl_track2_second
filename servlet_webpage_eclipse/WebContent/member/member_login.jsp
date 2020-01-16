<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<link href="/css/login.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
	$(document).ready(function() { //아이디 중복검사 ajax
			$("#password").keyup(function() { //keyup 글자가 입력되거나 값이 바뀌었을때
					$("#pw_yn").empty();
					if ($('#password').val() != "") {
						var urlLocation = "/MemberLoginCheck";
						var params = "id=" + $('#id').val()+ "&pw=" + $('#password').val();
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
								$("#pw_yn").html(data);
							}
						});
					}
				});
			});
</script>
<style>
 #pw_yn{color:red;}
</style>
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
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> LOGIN </span></h2>	
				<p>- 로그인이 필요합니다 -</p>
			</div>
			
		<!--login start-->
			<div class="login-box">
			<form class="login" name="loginform">
				<fieldset>
					<legend>로그인</legend>
					<div class="left-box">
						<p><label for="id" class="readonly">아이디</label>ID &nbsp;&nbsp;&nbsp;
						<input type="text" class="txt" id="id" name="id" onkeypress="if(event.keyCode==13){go_password()}" autofocus="autofocus" placeholder="&nbsp;&nbsp;아이디를 입력하세요"></p>
						<p><label for="password" class="readonly">비밀번호</label>PW &nbsp;
						<input type="password" class="txt" id="password" name="pw" onkeypress="if(event.keyCode==13){logincheck()}" placeholder="&nbsp;&nbsp;비밀번호를 입력하세요"></p>
						<span id="pw_yn"></span>
					</div>
					
					<div class="right-box">
						<input type="button" value="LOGIN" class="log" onClick="logincheck();">
					</div>
					
					<div class="checksave">
						<input type="checkbox" value="1" id="idsave" name="idsave"><label for="idsave">&nbsp;&nbsp;&nbsp;아이디 저장</label>
				
						<input type="checkbox" value="1" id="pwsave" name="pwsave" class="margin"><label for="pwsave">&nbsp;&nbsp;&nbsp;비밀번호 저장</label>
					</div>
						
					<p class="btn">
						<a href="#">ID/PW찾기</a>
						<a href="/MemberJoinForm">회원가입</a>
					</p>
						
				</fieldset>
			</form>
			</div>
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
		
		function logincheck() {
			if(loginform.id.value=="") {
				alert("아이디를 입력하세요");
				loginform.id.focus();
				return;
			}
			if(loginform.password.value=="") {
				alert("비밀번호를 입력하세요");
				loginform.password.focus();
				return;
			}
			loginform.action = "/MemberLogin";
			loginform.method = "post";
			loginform.submit();
		}			
		function go_password(){ //로그인에서 ID치고 엔터누르면 비밀번호쪽으로 내려감
			document.loginform.pw.focus();
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>