<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>	
	<div id="menu">
		<ul>
		  <li><img src = "../images/arrow.gif"><a href="/member/member_w.jsp">&nbsp;회원가입</a></li>
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
h, td, tr, input, textarea, select, FORM
{
  font-family:"Sandoll 미생",HS;
  font-size:1em;
  border-radius:8px;
  color:black;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 회원가입
		</p>
<script>
	function check_id(){	
		var id = document.member.t_id.value;
		if(id == ""){
			alert("입력된 아이디가 없습니다.");
			document.member.t_id.focus();
		}else{
			window.open("check_id.jsp?t_id="+id, "아이디 중복검사", "width:400, height:210"); 
			//첫번째:새창에 띄울 이미지, 두번째:새창의 제목, 세번째:check_id를 얼마만큼의 사이즈로 열겠는지
		}		
	}
	function save(){ //회원가입에서 공백 메시지 뛰우기
		var form = document.member;
		if(!checkEmpty(form.t_id,      "아이디 입력!"))        return;
		if(form.id_check_value.value == ""){ //id 중복체크 했는지 체크
			alert("아이디 중복체크 하세요!");
			return;
		}
		if(form.t_id.value != form.id_check_value.value){
			alert("아이디 변경 후 중복검사 하세요!");
			form.t_id.focus();
			return;
		}
		//if문은 공백체크(값이 유효한지 체크x)
		if(!checkEmpty(form.t_pw_1,    "비밀번호 입력!"))      return; //return은 true, false(공백이 아니면)로 받음 -> return;
		if(!checkEmpty(form.t_pw_2,    "비밀번호 확인 입력!")) return;
		if(form.t_pw_1.value != form.t_pw_2.value){ //비밀번호 = 비밀번호 확인이랑 값이 같은지 확인
			alert("비밀번호가 같지 않습니다.");
			form.t_pw_1.focus();
			return;
		}
		if(!checkEmpty(form.t_name,    "성명 입력!")) 	  	   return; 
		if(!checkEmpty(form.t_birth,   "생년월일 입력!")) 	   return;
		if(form.t_birth.value.length != 6) { //생년월일 6자리 인지 체크
			alert("날짜형식 오류! (ex)850512");
			form.t_birth.focus();
			return;
		}
		if(form.t_area.value == "선택안함"){ //지역선택 했는지 체크
			alert("지역선택 하세요");
			form.t_area.focus();
			return;
		}
		if(!checkEmpty(form.t_address, "나머지 주소 입력!"))   return;
		if(form.t_telecom.value == ""){ //통신사 체크(radio 버튼)
			alert("통신사 선택!");
			return;
		}
		if(!checkEmpty(form.t_phone_1, "전화번호 입력!"))      return;		
		if(!checkEmpty(form.t_phone_2, "전화번호 입력!"))      return; 
		if(!checkEmpty(form.t_phone_3, "전화번호 입력!"))      return;
		if(form.t_phone_1.value.length != 3) { //전화번호 3자리 체크
			alert("전화번호 입력 오류!");
			form.t_phone_1.focus();
			return;
		}
		if(form.t_phone_2.value.length != 4) { //전화번호 4자리 체크
			alert("전화번호 입력 오류!");
			form.t_phone_2.focus();
			return;
		}
		if(form.t_phone_3.value.length != 4) {
			alert("전화번호 입력 오류!");
			form.t_phone_3.focus();
			return;
		}
		if(!checkEmpty(form.t_email_1, "E-mail 입력!"))        return;
		if(!checkEmpty(form.t_email_2, "E-mail 입력!"))        return;			
		
		form.action = "member_proc.jsp";
		form.method = "post";
		form.submit();
/*
		if(form.t_pw_1.value == ""){
			alert("비밀번호 입력!");
			form.t_pw_1.focus();
			return; //비밀번호 입력안됬으면 다음번으로 안넘어가고 바로 끝내기
		}
		if(form.t_pw_2.value == ""){
			alert("비밀번호 확인 입력!");
			form.t_pw_2.focus();
			return;
		}
		if(form.t_name.value == ""){
			alert("성명 입력!");
			form.t_name.focus();
			return;
		}
*/
	}
</script>
		<form name="member">	
			<div class="bord_list">	
				<table class="bord_table">
					<colgroup>
						<col width="10%">						
						<col width="*">
					</colgroup>
					<tr>
						<td class="td_title2">아이디</td>
						<td colspan="3" style="text-align:left">
							<input name="t_id" type="text" autofocus="autofocus" maxlength="20"/>							
							<input type="button" onClick="check_id()" value="중복검사"/>
							<input type="hidden" name="id_check_value"></td>
					</tr>

					<tr>
						<td class="td_title">비밀번호</td>
						<td style="text-align:left"><input name="t_pw_1" type="password" maxlength="15"/></td>
					</tr>

					<tr>
					   <td>비밀번호 확인</td>
					   <td colspan="3" style="text-align:left"><input name="t_pw_2" type="password" maxlength="15"/></td>
					</tr>

					<tr>
					   <td>성명</td>
					   <td colspan="3" style="text-align:left"><input name="t_name" type="text" maxlength="5"/></td>
					</tr>
					
					<tr>
					   <td>생년월일</td>
					   <td colspan="3" style="text-align:left"> 
					   <input name="t_birth" class="input_50px" type="text" style="text-align:center" placeholder="(ex) 080523" maxlength="6"/></td>
					</tr>

					<tr>
					   <td>주소</td>
					   <td colspan="3" style="text-align:left">
						<select name="t_area" class="select">
						  <option value="선택안함">지역선택</option>
						  <option value="서울">서울</option>
						  <option value="경기">경기</option>
						  <option value="대전">대전</option>             
						  <option value="광주">광주</option>             
						  <option value="대구">대구</option>             
						  <option value="부산">부산</option>             
						</select>
						<input name="t_address" class="input_400px" type="text" size="60" placeholder="나머지 주소" maxlength="25"/></td>					 
					</tr>

					<tr>
					   <td>Mobile</td>
					   <td colspan="3" style="text-align:left";>
						<input type="radio" value="SKT" name="t_telecom"/> SKT
						<input type="radio" value="KT"  name="t_telecom"/> KT
						<input type="radio" value="LG"  name="t_telecom"/> LG
						&nbsp;&nbsp;
						<input name="t_phone_1" class="input_40px" type="text" size="6" style="text-align:center" maxlength="3" value="010"/> - 
						<input name="t_phone_2" class="input_50px" type="text" size="6" style="text-align:center" maxlength="4"/> -
						<input name="t_phone_3" class="input_50px" type="text" size="6" style="text-align:center" maxlength="4"/></td>
					</tr>

					<tr>
					   <td>E-Mail</td>
					   <td colspan="3" style="text-align:left;">
					   <input name="t_email_1" type="text" size="12" style="text-align:center" maxlength="20"/> @
					   <input name="t_email_2" type="text" size="20" style="text-align:center" maxlength="20" list="mail_list"/>
					   <datalist id="mail_list">
							<option value="google.com">
							<option value="daum.net">
							<option value="naver.com">	
						</datalist></td>
					</tr>					  
					  
					<tr>
					   <td>관심분야</td>
					   <td colspan="3" style="text-align:left;">					
					   <input type="checkbox" value="y" name="t_att_desk"/> DESKTOP
					   <input type="checkbox" value="y" name="t_att_note"/> NOTEBOOK
					   <input type="checkbox" value="y" name="t_att_print"/> PRINT
					   <input type="checkbox" value="y" name="t_att_beam"/> BEAM</td>
					</tr>				 
				</table>			
			</div>
				<p style="text-align:center; padding-top:10px">				
					<input type="button" onClick="save()" value="회원가입" style="color:white;background:#42464d;width:50px;height:30px;border:none;font-size:5px;">
					<input type="reset" value="다시작성" style="color:white;background:#42464d;width:50px;height:30px;border:none;font-size:5px;">
				</p>
			</form>
	</div>	
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>