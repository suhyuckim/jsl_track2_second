<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="common.CommonUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : track2 수련생 성명	
	******************************************** 
 -->
<title>track2 임수혁</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/layout.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!--
	******************************************** 
		성명 : track2 수련생 성명	
	******************************************** 
 -->
		<div class="leftmargin">
			<h1>성명 : track2_임수혁</h1>
		</div>
		<div class="search_wrap">
			<div class="record_group">
				<p>
					총게시글 : <span>2</span>건
				</p>
			</div>
			<form name="notice">
				<div class="search_group">
					<input type="hidden" name="t_noticeNo"> 
					<select
						class="select" name="t_sel">
						<option value="title"
							<c:if test="${selValue == 'title'}"> out.print("selected");</c:if>>제목</option>
						<option value="content"
							<c:if test="${selValue == 'content'}"> out.print("selected");</c:if>>내용</option>
					</select> <input type="text" value="${txtValue}" name="t_search"
						class="search_word">
					<button class="btn_search" type="button"
						onClick="javascript:formSearch()">
						<i class="fa fa-search"></i><span class="sr-only">검색버튼</span>
					</button>
				</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="8%">
				<col width="12%">
				<col width="12%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>파일</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="forYN" value="true" />
				<c:if test="${t_dtos.size() > 0}">
					<c:forEach items="${t_dtos}" var="dtos">
						<c:if test="${forYN}">
							<c:if test="${v_count == for_count}">
								<tr>
									<td align="center"><a href=javascript:NoticeView("${dtos.getNotice_no()}")>${dtos.getNotice_no()}</a></td>
									<td align="left"><a href=javascript:NoticeView("${dtos.getNotice_no()}")>${dtos.getTitle()}</a></td>
									<td align="center"><c:if
											test="${dtos.getFile_name() ne null}">
											<i class="far fa-save"></i>
										</c:if></td>
									<td align="center">${dtos.getReg_id()}</td>
									<td align="center">${dtos.getReg_date()}</td>
									<td align="center">${dtos.getHit()}</td>
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
			<a href="/NoticeWriteForm" class="write">글쓰기</a>
		</div>
	</div>
	<script>
	function formSearch() { //제목 검색
		var fm = document.notice;
		fm.action = "/NoticeList";
		fm.method = "post";
		fm.submit();
	}
	function NoticeView(noticeview){
		var fm = document.notice;
		fm.t_noticeNo.value = noticeview;
		fm.action = "/NoticeView";
		fm.method = "post";
		fm.submit();
	}
</script>
</body>
</html>
