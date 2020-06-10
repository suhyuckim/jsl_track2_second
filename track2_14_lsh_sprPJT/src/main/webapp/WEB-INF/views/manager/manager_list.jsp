<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="common.*"%>
<%@ include file="../common_head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function formSearch(){
		memberForm.action ="/Manager";
		memberForm.method ="post";
		memberForm.submit();
	}
	function goMemberView(id){
		member.t_gubun.value = "view";
		member.t_id.value = id;
		member.action = "/Manager";
		member.metion = "post";
		member.submit();
	}
	function goPage(pageNum){
		memberForm.r_page.value = pageNum;
		memberForm.action ="/Manager";
		memberForm.method ="post";
		memberForm.submit();
	}
</script>
		<form name="member">
			<input type="hidden" name="t_gubun">
			<input type="hidden" name="t_id">
		</form>
		<!--  header end -->
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
				<h2><a href="/Notice"> NOTICE</a></h2>	
				<h2><a href="/Qna"> QnA</a></h2>
				<h2><a href="/Faq"> FAQ</a></h2>	
				<h2><a href="/News"> NEWS</a></h2>	
				<c:if test="${session_id eq 'manager'}">
					<h2  class="color"><a href="/Member"><i class="fas fa-check"></i> MEMBER</a></h2>
				</c:if>
			</div>

		<div class="search_wrap">
			<div class="record_group">
				<p>* 총회원수 : <span>${t_dtos.size()}</span>명</p>	
			</div>
			<form name="memberForm">
			<input type="hidden" name="r_page">
				<div class="search_group">
					<select name="t_select" class="select">
						<option value="name" <c:if test="${t_select eq 'name'}">selected</c:if> >성명</option>
						<option value="area" <c:if test="${t_select eq 'area'}">selected</c:if>>지역</option>
					</select>
					<input type="text" name="t_search" value="${t_search}" class="search_word">
					<button class="btn_search" onClick="formSearch()"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
				</div>
			</form>
		</div>
			
			<!-- table start-->
			<div class="table-box">
				<table class="table">
					<caption>공지사항 - 번호, 제목, 첨부, 작성일, 조회수</caption>
					<colgroup>
						<col width="15%">
						<col width="15%">
						<col width="20%">
						<col width="*">
						<col width="20%">
						<col width="5%">
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">성명</th>
							<th scope="col">연락쳐</th>
							<th scope="col">이메일</th>
							<th scope="col">가입일</th>
							<th scope="col">상태</th>
						</tr>
					</thead>
					<tbody>
			<c:set var="forYN" value="true"/>
			<c:if test="${t_dtos.size() > 0}">
				<c:forEach items="${t_dtos}" var="dtos">
					<c:if test="${forYN == true}">
						<c:choose>
							<c:when test="${v_count == for_count}">
								<tr>
									<td><a href="javascript:goMemberView('${dtos.getId()}')">${dtos.getId()}</a></td>
									<td class="txt" style="align:center"><a href="javascript:goMemberView('${dtos.getId()}')">${dtos.getName()}</a></td>
									<td>${dtos.getTel()}</td>
									<td>${dtos.getEmail_1()}@${dtos.getEmail_2()}</td>
									<td>${dtos.getReg_date()}</td>
									<c:if test="${dtos.getIng_yn() eq 'y'}">
										<td style="color:blue">회원</td>
									</c:if>
									<c:if test="${dtos.getIng_yn() eq 'n'}">
										<td style="color:red">탈퇴</td>
									</c:if>
									
								</tr>
							<c:set var="v_count" value="${v_count+1}"/>
							<c:set var="for_count" value="${for_count+1}"/>
						</c:when>
						<c:otherwise>
							<c:set var="v_count" value="${v_count+1}"/>
						</c:otherwise>				
					</c:choose>		
					<c:if test="${v_count == a_count}">
						<c:set var="forYN" value="false"/>		
					</c:if>
				</c:if>
				</c:forEach>
			</c:if>													
					</tbody>
				</table>
			</div>
			
			<div class="page-number">
				<div class="page-number">
<%
				int current_page = (int)request.getAttribute("current_page");
				int total_page 	 = (int)request.getAttribute("total_page");
				out.println(CommonUtil.pageListPost(current_page, total_page, 5)); // 3이라는 숫자를 입력하여 게시판별로 페이지수를 별도로 지정가능 
%>
				</div>				
			</div>
		</div>
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">JSL 인재개발원</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>	
	</body>
</html>