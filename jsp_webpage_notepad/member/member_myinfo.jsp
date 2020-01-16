<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>	
<%@ include file="/common/sessionCheckMember.jsp"%>
<%@ page import="dao.Member_DAO,dto.Member_DTO"%>
<%
	Member_DAO dao 	= new Member_DAO();	
	Member_DTO dto 	= dao.getMemberInfo(sessionId);
	
	if(dto != null){		
%>
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
	function update(){
		var form = document.member_myinfo;
		
		if(!checkEmpty(form.t_pw, "비밀번호 입력!")) return;
		if(form.h_pw.value != form.t_pw.value){ 
			alert("비밀번호가 같지 않습니다.");
			form.t_pw.focus();
			return;
		}
		if(!checkEmpty(form.t_name, "성명 입력!")) return; 
		if(!checkEmpty(form.t_birth,"생년월일 입력!")) return;
		if(form.t_birth.value.length != 6) {
			alert("날짜형식 오류! (ex)850512");
			form.t_birth.focus();
			return;
		}
		if(form.t_area.value == "선택안함"){
			alert("지역선택 하세요");
			form.t_area.focus();
			return;
		}
		if(!checkEmpty(form.t_address, "나머지 주소 입력!")) return;
		if(form.t_telecom.value == ""){ 
			alert("통신사 선택!");
			return;
		}		
		if(!checkEmpty(form.t_phone_1, "전화번호 입력!")) return;		
		if(!checkEmpty(form.t_phone_2, "전화번호 입력!")) return; 
		if(!checkEmpty(form.t_phone_3, "전화번호 입력!")) return;
		if(form.t_phone_1.value.length != 3) { 
			alert("전화번호 입력 오류!");
			form.t_phone_1.focus();
			return;
		}		
		if(form.t_phone_2.value.length != 4) { 
			alert("전화번호 입력 오류!");
			form.t_phone_2.focus();
			return;
		}
		if(form.t_phone_3.value.length != 4) {
			alert("전화번호 입력 오류!");
			form.t_phone_3.focus();
			return;
		}
		if(!checkEmpty(form.t_email_1, "E-mail 입력!")) return;
		if(!checkEmpty(form.t_email_2, "E-mail 입력!")) return;			
						
		form.action = "member_update.jsp";
		form.method = "post";
		form.submit();
	}	
	function deleteMember(){
		var form = document.member_myinfo;		
		if(!checkEmpty(form.t_pw, "비밀번호 입력!")) return; 			
		if(form.h_pw.value != form.t_pw.value){ 
			alert("비밀번호가 같지 않습니다.");
			form.t_pw.focus();
			return;
		}
		if(confirm("정말 탈퇴 하겠습니까?")){
			form.method = "post";
			form.action = "member_delete.jsp";			
			form.submit();
		}		
	}
</script>
		<form name="member_myinfo">
			<input type="hidden" name="h_pw" value="<%=dto.getPw()%>"/>
			<input type="hidden" name="t_id" value="<%=dto.getId()%>"/>
			<div class="bord_list">				
				<table class="bord_table">				
					<colgroup>
						<col width="10%">						
						<col width="*">
					</colgroup>
					<tr>
						<td class="td_title2">아이디</td>
						<td style="text-align:left"><%=dto.getId()%></td>
					</tr>
					<tr>
						<td class="td_title2">가입일</td>
						<td style="text-align:left"><%=dto.getReg_date()%></td>
					</tr>		

					<tr>
					   <td>성명</td>
					   <td colspan="3" style="text-align:left"><input name="t_name" type="text" maxlength="5" value="<%=dto.getName()%>"/></td>
					</tr>
					
					<tr>
					   <td>생년월일</td>
					   <td colspan="3" style="text-align:left"> 
					   <input name="t_birth" class="input_50px" type="text" style="text-align:center" placeholder="(ex) 080523" maxlength="6" value="<%=dto.getBirth()%>"/></td>
					</tr>

					<tr>
					   <td>주소</td>
					   <td colspan="3" style="text-align:left">
						<select name="t_area" class="select">						  
						  <option value="서울" <%if(dto.getArea().equals("서울"))out.print("selected");%>>서울</option>
						  <option value="경기" <%if(dto.getArea().equals("경기"))out.print("selected");%>>경기</option>
						  <option value="대전" <%if(dto.getArea().equals("대전"))out.print("selected");%>>대전</option>             
						  <option value="광주" <%if(dto.getArea().equals("광주"))out.print("selected");%>>광주</option>             
						  <option value="대구" <%if(dto.getArea().equals("대구"))out.print("selected");%>>대구</option>             
						  <option value="부산" <%if(dto.getArea().equals("부산"))out.print("selected");%>>부산</option>             
						</select>
						<input name="t_address" class="input_400px" type="text" size="60" placeholder="나머지 주소" maxlength="25" value="<%=dto.getAddress()%>"/></td>					 
					</tr>

					<tr>
					   <td>Mobile</td>
					   <td colspan="3" style="text-align:left";>
						<input type="radio" value="SKT" name="t_telecom" <%if(dto.getTelecom().equals("SKT"))out.print("checked");%>/> SKT
						<input type="radio" value="KT"  name="t_telecom" <%if(dto.getTelecom().equals("KT"))out.print("checked");%>/> KT
						<input type="radio" value="LG"  name="t_telecom" <%if(dto.getTelecom().equals("LG"))out.print("checked");%>/> LG
						&nbsp;&nbsp;
						<input name="t_phone_1" class="input_40px" type="text" size="6" style="text-align:center" maxlength="3" value="<%=dto.getPhone_1()%>"/> - 
						<input name="t_phone_2" class="input_50px" type="text" size="6" style="text-align:center" maxlength="4" value="<%=dto.getPhone_2()%>"/> -
						<input name="t_phone_3" class="input_50px" type="text" size="6" style="text-align:center" maxlength="4" value="<%=dto.getPhone_3()%>"/></td>
					</tr>

					<tr>
					   <td>E-Mail</td>
					   <td colspan="3" style="text-align:left;">
					   <input name="t_email_1" type="text" size="12" style="text-align:center" maxlength="20" value="<%=dto.getEmail_1()%>"/> @
					   <input name="t_email_2" type="text" size="20" style="text-align:center" maxlength="20" value="<%=dto.getEmail_2()%>" list="mail_list"/>
					   <datalist id="mail_list">
							<option value="google.com">
							<option value="daum.net">
							<option value="naver.com">	
						</datalist></td>
					</tr>					  
					  
					<tr>
					   <td>관심분야</td>
					   <td colspan="3" style="text-align:left;">					
					   <input type="checkbox" value="y" name="t_att_desk"  <%if(dto.getAtt_desk().equals("y"))out.print("checked");%>/> DESKTOP
					   <input type="checkbox" value="y" name="t_att_note"  <%if(dto.getAtt_note().equals("y"))out.print("checked");%>/> NOTEBOOK
					   <input type="checkbox" value="y" name="t_att_print" <%if(dto.getAtt_print().equals("y"))out.print("checked");%>/> PRINT
					   <input type="checkbox" value="y" name="t_att_beam"  <%if(dto.getAtt_beam().equals("y"))out.print("checked");%>/> BEAM</td>
					</tr>					
					
					<tr>
					   <td>비밀번호</td>
					   <td colspan="3" style="text-align:left"><input name="t_pw" type="password" maxlength="15"/></td>
					</tr>
					
				</table>			
			</div>
				<p style="text-align:center; padding-top:10px">				
					<input type="button" onClick="update()" value="정보수정" style="color:white;background:#42464d;width:50px;height:30px;border:none;font-size:5px;">
					<input type="button" onClick="deleteMember()" value="회원탈퇴" style="color:white;background:#42464d;width:50px;height:30px;border:none;font-size:5px;">
				</p>
			</form>
	</div>	
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>
<% } %>