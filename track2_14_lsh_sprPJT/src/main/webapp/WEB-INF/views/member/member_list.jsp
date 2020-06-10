<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="common.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
<title>Member List</title>
<script type="text/javascript">
	function goView(id){
		view.t_id.value = id;
		view.action = "/MemberView";
		view.metion = "post";
		view.submit();
	}
	function formSearch(){
		member.action = "/MemberList";
		member.method = "post";
		member.submit();
	}
	function goPage(pageNum){
		member.r_page.value = pageNum;
		member.action ="/MemberList";
		member.method ="post";
		member.submit();
	}
</script>

</head>
	<body>
	총 : ${t_dtos.size()}건
	<br>
	<form name="view">
		<input type="hidden" name="t_id">
	</form>
		<form name="member">
		<input type="hidden" name="r_page">
			<table border="0" width="523">
				<tr>
					<td align="right">
						<select name="t_select">
							<option value="name" <c:if test="${t_select eq 'name'}">selected</c:if>>성명</option>
							<option value="area" <c:if test="${t_select eq 'area'}">selected</c:if>>지역</option>
						</select>
						<input type="text" name="t_search" size="4">
						<input type="button" onClick="formSearch()" value="검색">
					</td>
				</tr>
			</table>
		</form>		
	
		<table border="1">
			<tr>
				<td width="100" align="center">ID</td>
				<td width="200" align="center">NAME</td>
				<td width="100" align="center">AREA</td>
				<td width="100" align="center">AGE</td>
			</tr>
<c:set var="forYN" value="true"/>
<c:if test="${t_dtos.size() > 0}">
	<c:forEach items="${t_dtos}" var="dtos">
		<c:if test="${forYN == true}">
			<c:choose>
				<c:when test="${v_count == for_count}">			
					<tr>
						<td align="center"><a href="javascript:goView('${dtos.getId()}')">${dtos.getId()}</td>
						<td align="center"><a href="javascript:goView('${dtos.getId()}')">${dtos.getName()}</td>
						<td align="center">${dtos.getArea()}</td>
						<td align="center">${dtos.getAge()}</td>
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
		</table>
		
		<table border="0" width="525"> 
			<tr>
				<td align="center">
<%
					int current_page = (int)request.getAttribute("current_page");
					int total_page 	 = (int)request.getAttribute("total_page");
					out.println(CommonUtil.pageListPost(current_page, total_page, 4)); // 3이라는 숫자를 입력하여 게시판별로 페이지수를 별도로 지정가능 
%>
				</td>
			</tr>
		</table>
		
		<table border="0" width="525"> 
			<tr>
				<td align="right">
					<input type="button" onClick="location.href='/MemberWriteForm'" value="등록">
				</td>
			</tr>
		</table>
		
	</body>
</html>