<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common_header.jsp" %>
<%@ page import="common.CommonUtil"%>
<!DOCTYPE html>
<html lang="ko">
	<div class="sub_title" >
		<h2>EVENT list</h2>
		<!--<img src="images/tyoko_1.jpeg" alt="My Image">  -->
			<%@ include file="/common/common_menu.jsp" %>
	</div>

	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 <span>${EventCount}</span>건</p>
			</div>	
				<form name="event">
						<div class="search_group">
						<input type="hidden" name="r_page">
						<input type="hidden" name="t_eventNo">
						<select class="select" name="t_sel" onChange="t_selChange()">
							<option value="all" <c:if test="${selValue == 'all'}"> out.print("selected");</c:if>>전체</option>
							<option value="title" <c:if test="${selValue == 'title'}"> out.print("selected");</c:if>>제목</option>
							<option value="content" <c:if test="${selValue == 'content'}"> out.print("selected");</c:if>>내용</option>
						</select>
						<input type="text" value="${txtValue}" name="t_search" class="search_word" >
						<button class="btn_search" type="button" onClick="javascript:formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
					</div>
				</form>	
			</div>
		</div>
		
		<div class="board_list">
			<table class="board_table" summary="이표는 번호, 제목, 글쓴이, 날자, 조회수로 구성되어 있습니다">
				<caption class="sr-only">공지사항 리스트</caption>
				<colgroup>
					<col width="10%">					
					<col width="*">
					<col width="10%">
					<col width="5%">
					<col width="10%">
					<col width="20%">						
					<col width="7%">
					<c:if test="${session_id eq 'manager'}">						
						<col width="7%">
					</c:if>	
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">첨부</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">이벤트 기간</th>
						<th scope="col">조회수</th>
						<c:if test="${session_id eq 'manager'}">
							<th scope="col">관리</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:set var="forYN" value="true" />
					<c:if test="${t_dtos.size() > 0}">
						<c:forEach items="${t_dtos}" var="dtos">
							<c:if test="${forYN}">
								<c:if test="${v_count == for_count}">
									<tr>
										<td><a href=javascript:EventView_Servlet("${dtos.getEvent_no()}")>${dtos.getEvent_no()}</a></td>
										<td class="title">
											<a href=javascript:EventView_Servlet("${dtos.getEvent_no()}")>${dtos.getTitle()}</a></td>
										<td>
											<c:if test="${dtos.getFile_name_1() ne null}">
												<i class="far fa-save"></i>
											</c:if>
										</td>
										<td>${dtos.getReg_id()}</td>
										<td>${dtos.getReg_date()}</td>
										<td>${dtos.getStart_date()}~${dtos.getEnd_date()}</td>
										<td>${dtos.getHit()}</td>
										<c:if test="${session_id eq 'manager'}">
											<td><a href="/event/event_s.jsp?t_eventNo=${dtos.getEvent_no()}" class="management"><span>관리</span></a></td>
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
							<td colspan="7">등록된 내용이 없습니다.</td>
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
				<c:if test="${session_id eq 'manager'}">
					<a href="/EventWriteForm_Servlet" class="write">글쓰기</a>
				</c:if>
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
			var fm = document.event;
			fm.action = "/EventList_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function goPage(pageNum) {
			var fm = document.event;
			fm.r_page.value = pageNum;
			fm.action = "/EventList_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function EventView_Servlet(event_view){
			var fm = document.event;
			fm.t_eventNo.value = event_view;
			fm.action = "/EventView_Servlet";
			fm.method = "post";
			fm.submit();
		}
		
		function t_selChange(){
			var fm = document.event;
			if(fm.t_sel.value == "all"){
				fm.t_search.disabled = "disabled";
			} else {
				fm.t_search.disabled = "";
			}
		}
	</script>
	<%@ include file="/common/common_footer.jsp" %>
 </body>
</html>