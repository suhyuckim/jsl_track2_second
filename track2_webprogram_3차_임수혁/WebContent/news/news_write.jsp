<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">	
	<link href="/track2_webprogram_3차_임수혁/css/common.css" rel="stylesheet">
	<link href="/track2_webprogram_3차_임수혁/css/layout.css" rel="stylesheet" >	
</head>
<body>
	<div class="container">
<!--
	******************************************** 
		성명 : track2 WebProgram 3차 수련생 임수혁	
	******************************************** 
 -->	
		<div class="leftmargin">
			<h1 class="t_tit">성명 : track2 WebProgram 3차 임수혁</h1>
		</div>		
		<div class="write_wrap">
			<form name="write">
			<input type="hidden" name="t_gubun" value="save">
			<input type="hidden" name="t_gubun" value="view">
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>NEWS 제 목</th>
							<td class="th_left">
								<input name="t_title"  class="input_800px" type="text">
							</td>
						</tr>
						<tr>
							<th>NEWS 내 용</th>
							<td class="th_left">
								<textarea name="t_content"></textarea>
							</td>
						</tr>
						<tr>
							<th>등록자</th>
							<td class="th_left">
								<input name="t_reg_name"  class="input_100px" type="text">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="javascript:newscheck()"><input type="button" value="저장" class="btn_ok"></a>&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn_list">
			</div>
			</form>
		</div>
	</div>
<script>
	function newscheck(){
		if(write.t_title.value==""){
			alert("제목을 입력하세요.");
			write.t_title.focus();
			return;
		}
		if(write.t_content.value==""){
			alert("내용을 입력하세요.");
			write.t_content.focus();
			return;
		}
		if(write.t_reg_name.value==""){
			alert("작성자명을 입력하세요.");
			write.t_reg_name.focus();
			return;
		}
		write.action="/track2_webprogram_3차_임수혁/NewsSave_Exam";
		write.method="post";
		write.submit();
	}
</script>
</body>
</html>