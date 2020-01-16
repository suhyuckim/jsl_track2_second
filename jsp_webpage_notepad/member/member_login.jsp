<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp" %>
 <style> 
.home_icon{
	width : 15px;
}
#content{
	font : 10px "돋음", Gulim ; 
	color :#848484; 
}	
#content .title a{
	font : 10px "돋음", Gulim ; 
	color :#6E6E6E; 
}	

/* login css */
	.login-group {overflow:hidden; width:40%; margin:0 auto; background-color:#18103e; padding:5% 3% 3% 3%;}
	.sub-wrap .float-left {float:left; width:67%; margin-right:2%;}
	.sub-wrap .float-right {float:right; width:25%;}
	.login-group .txt {width:100%; height:46px; line-height:45px;  border:1px solid #585858; border-radius:3px; color:#A4A4A4; margin-bottom:10px; padding:0 10px;}
	.login-group h2 {margin-bottom:30px; font-size:18px; font-weight:bold; color:#fff;}
	.login-group .submit {width:95%; border-radius:3px; color:#fff; height:120px; margin-top:16px;background-color:#9b81f5ad; border:0 none; cursor:pointer;}
	.login-group p {color:#fff; clear:both;}
	.login-group p.btn {margin-bottom:15px;}
	.login-group p.btn a {display:inline-block; width:48%; height:45px; line-height:45px; text-align:center; color:#fff; background-color:#9b81f5ad; border:1px solid #707b92; border-radius:3px;}
h2{
	text-align:center;
}	
 </style> 
 <script>
	function login_check(){
		var form = document.member;
		if(!checkEmpty(form.t_id, "아이디를 입력하시요!")) return; 
		if(!checkEmpty(form.t_pw, "비밀번호를 입력하시요!")) return; 
		form.method = "post";
		form.action = "member_login_proc.jsp";
		form.submit();			
	}
	function go_password(){ //로그인에서 ID치고 엔터누르면 비밀번호쪽으로 내려감
		document.member.t_pw.focus();
	}
 </script>
	<div id="menu">
		<ul>
			<li><a href="/member/member_w.jsp"><img class="arrow" src="../images/arrow.gif"> 회원가입</a></li>
			<li><a href="/member/member_find_id.jsp"><img class="arrow" src="../images/arrow.gif"> 아이디찾기</a></li>
			<li><a href="/member/member_find_pw.jsp"><img class="arrow" src="../images/arrow.gif"> 비밀번호찾기</a></li>
		</ul>
	</div>
	<div id="content">
		<ul>
			<!-- <li class="btn_home"> -->
				<!-- <a href="index.html"><i class="fa fa-home btn_plus"></i></a> -->
			<!-- </li> -->
			<li class="btn_home">
				<a href="/index.jsp">
					<img src="../images/home3.png" class="home_icon">
				
				&nbsp;HOME</a> | &nbsp;Login
			</li>
		</ul>

	<form name="member">
		<div class="bord_list">
			<section id="content">
				<div class="sub-wrap">
					<div class="login-group">
						<h2>아이디/비밀번호 로그인</h2>
						<form name="login" method="post" enctype="multipart/form-data" action="logincheck.html">
							<fieldset> <!-- 로그인이라는 필드셋의 묶음 -->
								<div class="float-left">
									<p>
									<label class="readonly">아이디</label>
									<input type="text" name="t_id" onkeypress="if(event.keyCode==13){go_password()}" class="txt" autofocus="autofocus" placeholder="아이디를 입력하세요">
									</p> <!-- label의 for 명과 input의 id 명은 같아야 한다 -->
									<p>
									<label class="readonly">비밀번호</label>
									<input type="password" name="t_pw" onkeypress="if(event.keyCode==13){login_check()}" class="txt" placeholder="비밀번호를 입력하세요">
									</p>
								</div>
								<div class="float-right">
									<p>
									<input type="button" value="로그인" class="submit" onClick="login_check();"> <!-- 사용자 함수 -->
									</p> <!-- 여기서 value는 버튼이라는 타입에 나타나는 글자 -->
								</div>
								<p class="btn">
									<a href="member_pw_find.jsp">ID/PW 찾기</a>
									<a href="/member/member_w.jsp">회원가입</a>
								</p>
							</fieldset>
						</form>
					</div>						
				</div>
			</section>			

		</div>			
	</form>
	
	</div>	
<%@ include file="/common_footer.jsp"%>
    </div>
  </body>
</html>