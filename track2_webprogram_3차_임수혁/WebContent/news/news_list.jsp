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
		title : track2 WebProgram 3차 수련생 임수혁	
	******************************************** 
 -->
<title>track2 WebProgram 3차 임수혁</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="/track2_webprogram_3차_임수혁/css/common.css" rel="stylesheet">
<link href="/track2_webprogram_3차_임수혁/css/layout.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!--
	******************************************** 
		성명 : track2 WebProgram 3차 수련생 임수혁	
	******************************************** 
 -->
		<div class="leftmargin">
			<h1 class="t_tit">성명 :track2 WebProgram 3차 임수혁</h1>
		</div>
		<div class="search_wrap">
			<div class="record_group">
				<p>
					총게시글 : <span>2</span>건
				</p>
			</div>
			<div class="search_group">
			<form name=news>
			<input type="hidden" name="t_newsNo"> 
				<select
						class="select" name="t_sel">
						<option value="title"
							<c:if test="${selValue == 'title'}"> selected</c:if>>제목</option>
						<option value="content"
							<c:if test="${selValue == 'content'}"> selected</c:if>>내용</option>
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
				<col width="8%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="8%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:set var="forYN" value="true" />
					<c:if test="${t_dtos.size() > 0}">
						<c:forEach items="${t_dtos}" var="dtos">
							<c:if test="${forYN}">
								<c:if test="${v_count == for_count}">
									<tr>
										<td align="center"><a href=javascript:NewsView("${dtos.getNews_no()}")>${dtos.getNews_no()}</a></td>
										<td align="left"><a href=javascript:NewsView("${dtos.getNews_no()}")>${dtos.getTitle()}</a></td>
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
							<td colspan="5">등록된 내용이 없습니다.</td>
						</tr>
					</c:if>
				</tr>
			</tbody>
		</table>
		<div class="paging">
			<a href="/track2_webprogram_3차_임수혁/NewsWriteForm_Exam" class="write">글쓰기</a>
		</div>
	</div>
	<script>
	function formSearch() { //제목 검색
		var fm = document.news;
		fm.action = "/track2_webprogram_3차_임수혁/NewsList_Exam";
		fm.method = "post";
		fm.submit();
	}
	
	function NewsView(newsview){ //상세보기
		var fm = document.news;
		fm.t_newsNo.value = newsview;
		fm.action = "/track2_webprogram_3차_임수혁/NewsView_Exam";
		fm.method = "post";
		fm.submit();
	}
</script>
</body>
</html>