<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title" >
		<h2>회원 목록</h2>
		<!--<img src="images/tyoko_1.jpeg" alt="My Image">  -->
			<%@ include file="/common/common_menu.jsp" %>
	</div>

	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총회원수 <span>${MemberCount}</span>명</p>
			</div>	
				<form name="member_list">
						<div class="search_group">
						<input type="hidden" name="r_page">
						<input type="hidden" name="t_memberNo">
						<input type="radio" value="reg_date" name="t_check" class="search_sel" <c:if test="${checkValue == 'reg_date'}"> checked</c:if>>가입일
						<input type="radio" value="name"     name="t_check" class="search_sel" <c:if test="${checkValue == 'name'}"> 	 checked</c:if>>성명
						<input type="radio" value="id"    	 name="t_check" class="search_sel" <c:if test="${checkValue == 'id'}">		 checked</c:if>>아이디
						<select class="select" name="t_sel" onChange="t_selChange()">
							<option value="all"  <c:if test="${selValue == 'all'}">  selected</c:if>>전체</option>
							<option value="id" 	 <c:if test="${selValue == 'id'}"> 	 selected</c:if>>ID</option>
							<option value="name" <c:if test="${selValue == 'name'}"> selected</c:if>>성명</option>
						</select>
						<input type="text" value="${txtValue}" name="t_search" class="search_word" <c:if test="${selValue eq 'all'}"> disabled = 'disabled'</c:if>>
						<select class="select" name="t_sel2" >
							<option value=""      <c:if test="${selValue2 eq ''}">   selected</c:if>>지역전체</option>
							<option value="서울"   <c:if test="${selValue2 eq '서울'}"> selected</c:if>>서울</option>
							<option value="경기"   <c:if test="${selValue2 eq '경기'}"> selected</c:if>>경기</option>
							<option value="대전"   <c:if test="${selValue2 eq '대전'}"> selected</c:if>>대전</option>
							<option value="광주"   <c:if test="${selValue2 eq '광주'}"> selected</c:if>>광주</option>
							<option value="대구"   <c:if test="${selValue2 eq '대구'}"> selected</c:if>>대구</option>
							<option value="부산"   <c:if test="${selValue2 eq '부산'}"> selected</c:if>>부산</option>
						</select>
						<button class="btn_search" type="button" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
						<button class="btn_search"  type="button" onClick="javascript:goExcel()"><i class="far fa-file-excel"></i><span class="sr-only">액셀</span></button>
					</div>
				</form>	
			</div>
		</div>
		<div class="board_list">
			<table class="board_table" summary="이표는 번호, 제목, 글쓴이, 날자, 조회수로 구성되어 있습니다">
				<caption class="sr-only">공지사항 리스트</caption>
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
						<th scope="col">ID</th>
						<th scope="col">성명</th>
						<th scope="col">연락처</th>
						<th scope="col">이메일</th>
						<th scope="col">지역</th>
						<th scope="col">가입일</th>
						<th scope="col">탈퇴유무</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="forYN" value="true" />
					<c:if test="${t_dtos.size() > 0}">
						<c:forEach items="${t_dtos}" var="dtos">
							<c:if test="${forYN}">
								<c:if test="${v_count == for_count}">
									<tr>
										<td><a href=javascript:MemberView_Servlet("${dtos.getId()}")>${dtos.getId()}</a></td>
										<td class="title" style="text-align:center">
											<a href=javascript:MemberView_Servlet("${dtos.getId()}")>${dtos.getName()}</a></td>
										<td>${dtos.getPhone_1()}-${dtos.getPhone_2()}-${dtos.getPhone_3()}</td>
										<td>${dtos.getEmail_1()}@${dtos.getEmail_2()}</td>
										<td>${dtos.getArea()}</td>
										<td>${dtos.getReg_date()}</td>									
										<c:if test="${dtos.getStatus() eq '탈퇴'}">
											<td><i class="far fa-times-circle"></i></td>
										</c:if>
										<c:if test="${dtos.getStatus() ne '탈퇴'}">
											<td>${dtos.getStatus()}</td>
										</c:if>
									</tr>
									<c:set var="v_count" value="${v_count+1}" />
									<c:set var="for_count" value="${for_count+1}" />
								</c:if>
								<c:if test="${v_count != for_count}">
									<c:set var="v_count" value="${v_count+1}" />
								</c:if>
								<c:if test="${v_count == a_count}">
									<c:set var="forYN" value="false" />
								</c:if>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${t_dtos.size() == 0}">
						<tr align="center" bgcolor="white">
							<td colspan="9-">등록된 내용이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div class="paging">
				<%
					Integer cp = (Integer) request.getAttribute("current_page");
					int current_page = cp.intValue();
					Integer tp = (Integer) request.getAttribute("total_page");
					int total_page = tp.intValue();
					out.println(CommonUtil.pageList2(current_page, total_page));
				%>
			</div>
		</div>
	</div>

	<script>
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
		
		function formSearch() { //제목 검색
			var fm = document.member_list;
			fm.action = "/MemberList_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function goPage(pageNum) {
			var fm = document.member_list;
			fm.r_page.value = pageNum;
			fm.action = "/MemberList_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function MemberView_Servlet(member_view){
			var fm = document.member_list;
			fm.t_memberNo.value = member_view;
			fm.action = "/MemberView_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function t_selChange(){
			var fm = document.member_list;
			if(fm.t_sel.value == "all"){
				fm.t_search.disabled = "disabled";
			} else {
				fm.t_search.disabled = "";
			}
		}
		
		function goExcel(){ //엑셀
			var fm = document.member_list; 		
			fm.action = "/MemberExcelDown";
			fm.method = "post";
			fm.submit();
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>