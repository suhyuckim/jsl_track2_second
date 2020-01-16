<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>	
<%@ include file="/common/sessionCheckMember.jsp"%>
<%@ page import="dao.Manager_DAO,dto.Member_DTO"%>
<%
	Manager_DAO dao = new Manager_DAO();	
	String memberId = request.getParameter("t_managerNo");
	Member_DTO dto 	= dao.getMemberInfo(memberId);
	
	if(dto != null){		
%>
	<div id="menu">
		<ul>
		    <li><img class="arrow" src = "../images/arrow2.gif"><a href="/mana/mmember_r.jsp">&nbsp;회원목록</a></li>
		    <li><img class="arrow" src = "../images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;직원목록</a></li>
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
						<td style="text-align:left">
							<%=dto.getId()%></td>
					</tr>
					<tr>
						<td class="td_title2">가입일</td>
						<td style="text-align:left">
							<%=dto.getReg_date()%></td>
					</tr>		

					<tr>
					   <td>성명</td>
					   <td style="text-align:left"><%=dto.getName()%></td>
					</tr>
					
					<tr>
					   <td>생년월일</td>
					   <td colspan="3" style="text-align:left"><%=dto.getBirth()%></td>
					</tr>

					<tr>
					   <td>주소</td>
					   <td colspan="3" style="text-align:left">    
						<%=dto.getArea()%>&nbsp;<%=dto.getAddress()%></td>					 
					</tr>

					<tr>
					   <td>Mobile</td>
					   <td colspan="3" style="text-align:left";>
					
						[<%=dto.getTelecom()%>]&nbsp;&nbsp;<%=dto.getPhone_1()%>-<%=dto.getPhone_2()%>-<%=dto.getPhone_3()%></td>
					</tr>

					<tr>
					   <td>E-Mail</td>
					   <td colspan="3" style="text-align:left;">
					   <%=dto.getEmail_1()%>@<%=dto.getEmail_2()%>					  
					</tr>					  
					  
					<tr>
					   <td>관심분야</td>
					   <td colspan="3" style="text-align:left;">
						   <%if(dto.getAtt_desk().equals("y"))out.print("desk");%>
						   <%if(dto.getAtt_note().equals("y"))out.print("note");%>
						   <%if(dto.getAtt_print().equals("y"))out.print("print");%>
						   <%if(dto.getAtt_beam().equals("y"))out.print("beam");%>
						</td>
					</tr>					
					
					<tr>
					   <td>비밀번호</td>
					   <td colspan="3" style="text-align:left"><%=dto.getPw()%></td>
					</tr>
					
				</table>			
			</div>			
			</form>
	</div>	
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>
<% } %>