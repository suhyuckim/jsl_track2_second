<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/join.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		<script type = "text/javascript" src="/js/common.js"></script>
		
	<body>
		<!-- skip navigation -->
		<dl id="access">
			<dt>바로가기 및 건너띄기 링크</dt>
			<dd><a href="#contents">본문바로가기</a></dd>
			<dd><a href="#navigation">주메뉴 바로가기</a></dd>
		</dl>
		<hr>
		
		<div id="big-box">
			<header>
			<div id="header">
				<div class="nav">
					<h2 class="readonly">주메뉴</h2>
						<ul class="nav-menu">
						<li><a href="sub1.html">ABOUT EL WIDE</a></li>
						<li><a href="sub2.html">MUSIC</a></li>
						<li><a href="sub3.html">MEDIA</a></li>
						<li><a href="sub4.html">CULTURE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<li><a href="member_login.jsp"><i class="fas fa-user"></i></a></li>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
							<li>CONNECT WITH WIDE</li>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>
		
		<!--  header end -->
		
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><span><i class="fas fa-sign-in-alt"></i> SIGN UP</span></h2>	
				<p>- 회원가입을 위해, 작성해주세요 -</p>
			</div>
			
		<!--join start-->
			<div class="join-box">
			<form class="join" name="all">			
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i class="fas fa-id-card-alt fa-2x"></i>
								<label for="id">
									<input type="text" name="id" placeholder="아이디">
									<input type="button" onClick="check_id()" value="중복검사"/>
									<input type="hidden" name="id_check_value">
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
								<label for="phone"><input type="text" name="phone" placeholder="연락처 ex)01034232534" class="phone"></label>
								<label for="certifi"></label>
								<label for="certifi"></label>
							</li>
							<li>
								<input type="text" name="email1" class="email">&#64;
								<input type="text" name="email2" class="email" >
								<select name="emailtype" class="email" onChange="mailcheck()">
									<option>직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
								<label for="agree"><input type="radio" name="agree" value='y' id="agree" checked> 1년 정보유지</label>
								<label for="agree2"><input type="radio" name="agree" value='n' id="agree2"> 탈퇴시까지 정보유지</label>
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
			
				<div class="login_img">
					<li class="photo1"><img src="/images/join_01.JPG"></li>
					<li class="photo2"><img src="/images/join_02.JPG"></li>
					<li class="photo3"><img src="/images/join_03.JPG"></li>
					<li class="photo4"><img src="/images/join_04.JPG"></li>
				</div>
			</div>
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="/index/index.jsp" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>
	
		<script type="text/javascript">		
			function mailcheck(){
 				var email = document.all.emailtype.value;
				if(email == "직접입력"){
					document.all.email2.value = "";
				}else {
					document.all.email2.value = email;
				}
			}
			function check_id(){	
				var id = document.all.id.value;
		         if (id ==""){
		            alert("입력된 아이디가 없습니다.");
		            document.all.id.focus();
		         }else{
		         window.open("check_id.jsp?id="+id,"아이디 중복검사","width=420, height=220");
		         }		
	 		}	 
			function check() {			
				if(!checkEmpty(all.id, "아이디 입력!")) return;
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
				if(all.phone.value=="") {
					alert("연락받으실 번호를 입력해주세요");
					all.phone.focus();
					return;
					}
				if(all.phone.value.length != 11) { //전화번호 11자리 체크
					alert("전화번호 입력은 11자리로 입력해주세요. ");
					all.phone.focus();
					return;
				}
				/*if(all.certifi.value=="") {
					alert("인증번호를 입력해주세요");
					all.certifi.focus();
					return;
				}*/
				if(all.email1.value=="") {
					alert("이메일을 입력해주세요");
					all.email1.focus();
					return;
					}	
				if(all.email2.value=="") {
					alert("이메일을 입력해주세요");
					all.email2.focus();
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
					all.action = "member_proc.jsp";
					all.method = "post";
					all.submit();
				}
		</script>		
	</body>
</html>    