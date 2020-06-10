<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="common.*" %>
<%@ include file="../header.jsp" %>
	<div class="sub_title">
		<h2><span style="color:white" >회원 로그인</span></h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="company01.html"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">MEMBER<i class="fa fa-plus btn_plus"></i></a>
					</li>
					<li class="dropdown">
						<a href="/Member">LOGIN<i class="fa fa-plus btn_plus"></i></a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="member_boxL">
         	<h2>개인회원</h2>
              <div class="login_form">
                   <form name="frmLogin">
					<input type="hidden" name="t_gubun" value="login">
	                <div class="fl_clear">
	                <label for="mbrId">아이디</label>
	                <input name="t_id" id="mbrId" type="text" onkeypress="if(event.keyCode==13){goPw()}"> 
	                </div> 
	                <div class="fl_clear"><label for="scrtNo">비밀번호</label>
	                <input name="t_pw" id="scrtNo" type="password" onkeypress="if(event.keyCode==13){goPw()}">
	                </div>
	                 	<a class="btn_login btn_Blue" href="javascript:memberLogin()">로그인</a>
                    </form>
               </div>
        	</div>
		</div>
	<br>
	

<script>
		function goPw(){
			frmLogin.t_pw.focus();
		}

		$(function(){
			$(".location .dropdown>a").on("click",function(e){
				e.preventDefault();
				if($(this).next().is(":visible")){
					$(".location .dropdown > a").next().hide();
				}else{
					$(".location .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
		
		function memberLogin(){
			if(frmLogin.t_id.value == ""){
				alert("아이디 입력해주세요.");
				frmLogin.t_id.focus();
				return;
			}
			if(frmLogin.t_pw.value == ""){
				alert("비밀번호 입력해주세요.");
				frmLogin.t_pw.focus();
				return;
			}
			frmLogin.action = "/Member";
			frmLogin.method = "post";
			frmLogin.submit();
		}
</script>

<%@ include file="../footer.jsp" %>
</body>
</html>