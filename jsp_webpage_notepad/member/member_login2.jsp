<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>	
	<div id="menu">
		<ul>
		  <li><img src = "../images/arrow.gif"><a href="/notice/notice_r.jsp">&nbsp;회원가입</a></li>
		  <li><img src = "../images/arrow.gif"><a href="/notice/notice_r.jsp">&nbsp;아이디 찾기</a></li>
		  <li><img src = "../images/arrow.gif"><a href="/notice/notice_r.jsp">&nbsp;비밀번호 찾기</a></li>
		</ul>
	</div>
<style>
.bord_list { padding-top : 10px; }
.bord_table { width : 100%; }
.bord_list th {
	border-top : 1px solid #848484 ;
	border-bottom : 1px solid #848484 ;
	padding : 10px ;
}
.bord_table td{
	text-align : center ;
	padding : 10px ;
	border-bottom : 1px solid #D8D8D8 ;
}
td.title {
	text-align : left ;
}
.home_icon{
	width : 15px ;
}
#content .title a {
	color : #6E6E6E;
}
.select_Box {
	text-align : right;
}
.select_Box select {
	height : 20px;
}
#content p a {
	color : black;
}

.member {
 font-size: 20px;
 background : url("../images/mini2.jpg");
 text-shadow: 0 0 5px #FE9A2E;
 color: #FE9A2E;
 margin: 0 auto;
 text-align: center;
 text-transform: capitalize;
 font-family: "맑은 고딕"; 
}
body {
 font-family: "맑은 고딕";
 font-size: 12px;
}
.form {
 width: 400px;
 height: 200px;
 border-radius: 20px;
 border: 5px double #999;
 margin: 30px auto;
}
.form2 {
 width: 380px;
 min-width: 320px;
 height: 200px;
 margin: 60px auto;
 margin-left:20px;
}
.form3 {
 float: left;
 /*background:#f00;*/
}
.form3 label {
 width: 100px;
 height: 20px;
 /*display: block;*/
 float: left;
 text-align:center;
}
.form4 {
 padding: 0px 60px 50px 70px;
}
#wrap {
 width: 600px;
 height: 500px;
 margin: 0 auto;
}
.clear {
 clear: both;
}
input[type="submit"] {
 padding: 0px 10px 0px 10px;
 float:center;
 /*display:block;*/
 height: 40px;
 background : orange;
 border:none;
 font-family: "맑은 고딕";
}
input[type="button"] {
 width:115px;
 height: 35px;
 background : orange;
 font-family:"맑은 고딕";
 text-align:center;
 margin-bottom:100px;
 margin-top:20px;
}
input[type="checkbox"] {
 margin-top:50px;
}

</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | Login
		</p>
			
		<form>
		  <div id="wrap">
		   <h1 class="member">membmer login</h1>
		   <div class="form">
			<div class="form2">
			 <div class="form3">
			  <label for="user">아이디</label>
			  <input type="text" size="25" id="user" placeholder="ID" >
			  <div class="clear">
			  </div>
			  <label for="user">비밀번호</label>
			  <input type="password" size="25" id="user" placeholder="password">
			 </div>
			 
			 <input type="submit" value="Login">
			 
			 <div class="clear"></div>
			 <div class="form4">			  
			  <div class="clear"></div>
			  <label><input type="button" value="회원가입"></label> 
			  <label><input type="button" value="ID/PW 찾기"></label>
			 </div>
			</div>
		   </div>
		  </div>
		 </form>
		 
	</div>	
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>