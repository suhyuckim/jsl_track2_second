<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp" %>
<%@ page import="java.util.*,dao.Manager_DAO,dto.Member_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	Manager_DAO dao   = new Manager_DAO();
	String selValue   = request.getParameter("t_sel"); 
	String selValue2  = request.getParameter("t_sel2");
	String txtValue   = CommonUtil.checkNull(request.getParameter("t_search"));
	String checkValue = request.getParameter("t_check");
	if(selValue == null){
		selValue  = "all";		
		txtValue  = "";
		selValue2 = "";
	}	
	if(checkValue == null){
		checkValue = "reg_date";
	}
		ArrayList<Member_DTO> dtos = dao.getListMember(selValue, selValue2, txtValue, checkValue);
		int getMemberCount = dao.getMemberCount();
//************* page 시작 *************/

	String tdCount ="7";				

	String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
	int			current_page;					// 현재페이지 번호
	int			total_page;						// 총페이지 수
	int			total_count;					// 총레코드 수
	int			for_count;						
	int			list_setup_count = 10;			// 한번에 출력될 List 수, 한페이지당 몇줄 보여줄것인지
	int			p_no;
	int			v_count;
	int			a_count;
	String		url				= null;	

	// 조회된 건수 구하기  total_count : 설정
	if(dtos == null) total_count =0; 			//게시판 테이블에 있는 전체 숫자
	else total_count = dtos.size(); 


	// 페이지번호가 없으면 1페이지로 간주
	if(r_page.equals("")) current_page = 1;
	else current_page = Integer.parseInt(r_page);
		
	for_count		= list_setup_count;
	p_no			= list_setup_count;				// 페이지수가 10
	total_page = total_count / list_setup_count;	// 전체페이지수 계산 (if 23개 게시물이면 2)
   
	if(current_page == 1) {
		v_count		= 0;
		a_count		= list_setup_count;
		for_count	= 0;
	} else{
		v_count		= 0;
		a_count		= p_no * current_page;
		for_count	= a_count - list_setup_count;
	}
	if(total_page * list_setup_count != total_count)   total_page = total_page +1;

//************* page 끝 *************/ 
%>
	<div id="menu">
		<ul>
		  <li><img class="arrow" src = "../images/arrow2.gif"><a href="/notice/notice_r.jsp">&nbsp;회원목록</a></li>
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
	padding : 8px ;
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
.button {
	width : 50px;
	height : 20px;
	color : #fff;
	background:#42464d;
	border:none;
}
.far{
	color:gray;	
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | MANAGER | MEMBER
		</p>
<script>
	function formSearch(){ //제목 검색
		var fm = document.manager; 		
		fm.action = "mmember_r.jsp";
		fm.method = "post";
		fm.submit();
		
	}
	function goExcel(){ //엑셀
		var fm = document.manager; 		
		fm.action = "/common/excel_down_member.jsp";
		fm.method = "post";
		fm.submit();
	}
	function t_selChange(){
		var fm = document.manager;
		if(fm.t_sel.value == "all"){
			fm.t_search.disabled = "disabled";
		} else {
			fm.t_search.disabled = "";
		}
	}
</script>
		<form name="manager">			
		<p class="select_Box">	
			<input type="radio" value="reg_date"  name="t_check" <%if(checkValue.equals("reg_date")) out.print("checked");%>/>가입일
			<input type="radio" value="name"      name="t_check" <%if(checkValue.equals("name")) out.print("checked");%>/>성명
			<input type="radio" value="id"  	  name="t_check" <%if(checkValue.equals("id")) out.print("checked");%>/>아이디
			<select name="t_sel" onChange="t_selChange()">
				<option value="all"  <%if(selValue.equals("all"))  out.print("selected");%>>전체</option>
				<option value="name" <%if(selValue.equals("name")) out.print("selected");%>>성명</option>
				<option value="id"   <%if(selValue.equals("id"))   out.print("selected");%>>아이디</option>
			</select>
			<input type="text" value="<%=txtValue%>" name="t_search" size="10" maxlength="30" <%if(selValue.equals("all")) out.print("disabled = 'disabled'");%> />				
			<select name="t_sel2">
				<option value=""     <%if(selValue2.equals(""))  out.print("selected");%>>지역전체</option>
				<option value="서울" <%if(selValue2.equals("서울")) out.print("selected");%>>서울</option>
				<option value="경기" <%if(selValue2.equals("경기")) out.print("selected");%>>경기</option>
				<option value="대전" <%if(selValue2.equals("대전")) out.print("selected");%>>대전</option>
				<option value="광주" <%if(selValue2.equals("광주")) out.print("selected");%>>광주</option>
				<option value="대구" <%if(selValue2.equals("대구")) out.print("selected");%>>대구</option>
				<option value="부산" <%if(selValue2.equals("부산")) out.print("selected");%>>부산</option>				
			</select>					
			<input class="button" type="button" onClick="javascript:formSearch()" value="검색">
			<input class="button" type="button" onClick="javascript:goExcel()"    value="Excel">
		</p>
		</form>
		전체 회원수 : <%=getMemberCount%>명							
		<div class="bord_list">
			<table class="bord_table">			
				<colgroup>
					<col width="7%">
					<col width="6%">
					<col width="10%">
					<col width="15%">
					<col width="7%">
					<col width="10%">
					<col width="7%">					
				</colgroup>
				<thead>		
					<tr>
						<th>ID</th>
						<th>성명</th>						
						<th>연락처</th>
						<th>e-mail</th>	
						<th>지역</th>						
						<th>가입일</th>			
						<th>탈퇴유무</th>			
					</tr>
				</thead>
				<tbody>
	
	<%if ( total_count > 0 ){
		for(int k = 0 ; k < total_count ; k++ )	{
			if(v_count == for_count){%>
					<tr>									
						<td><a href="mmember_v.jsp?t_managerNo=<%=dtos.get(k).getId()%>"><%=dtos.get(k).getId()%></a></td>
						<td class="title">
							<a href="mmember_v.jsp?t_managerNo=<%=dtos.get(k).getId()%>"><%=dtos.get(k).getName()%></a></td>						
						<td><%=dtos.get(k).getPhone_1()%>-<%=dtos.get(k).getPhone_2()%>-<%=dtos.get(k).getPhone_3()%></td>				
						<td><%=dtos.get(k).getEmail_1()%>@<%=dtos.get(k).getEmail_2()%></td>
						<td><%=dtos.get(k).getArea()%></td>
						<td><%=dtos.get(k).getReg_date()%></td>	
						<%if(dtos.get(k).getStatus().equals("탈퇴")){%>
							<td style="color:red"><%=dtos.get(k).getStatus()%></td>						
						<%}else{%>
							<td><%=dtos.get(k).getStatus()%></td>	
						<%}%>
					</tr>
				<%v_count = v_count + 1;
				for_count = for_count + 1;
				}else { 
					v_count = v_count + 1;
				}
				if(v_count == a_count)break; 
			}
		}else{%>
				   <tr align="center" bgcolor="white" >
					   <TD colspan="<%=tdCount%>" >등록된 내용이 없습니다.</TD>
				   </tr>
		<%	} %> 				
				</tbody>
			</table>
			<div class="paging">
			<%url = "mmember_r.jsp?t_sel="+selValue+"&t_search="+txtValue+"&t_sel2="+selValue2;		
			out.println(CommonUtil.pageList(current_page, total_page, url));%>
			</div>
		</div>
	</div>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>