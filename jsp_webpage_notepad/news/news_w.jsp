<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common_head.jsp"%>
<%@ include file="/common/sessionCheckManager.jsp"%>
	<div id="menu">
		<ul>
		  <li><i class="fas fa-flag"></i><a href="/notice/notice_r.jsp">&nbsp;NOTICE</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/news/news_r.jsp">&nbsp;NEWS</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/freeboard/freeboard_r.jsp">&nbsp;자유게시판</a></li>
		  <li><img src = "../images/arrow2.gif"><a href="/qanda/qanda_r.jsp">&nbsp;Q & A</a></li>
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
textarea {
	width : 650px;
	height : 260px;
	resize : none;
}
</style>
	<div id="content">
		<p>
			<a href = "../index.html"><img src="../images/home3.png" class="home_icon"></a>
			<a href = "../index.html">HOME</a> | 커뮤니티 | NOTICE
		</p>
<script>
	function save(){
		var fm = document.news;		
		if(fm.t_title.value == ""){
			alert("제목 입력!");
			fm.t_title.focus();
			return;
		}
		if(fm.t_content.value == ""){
			alert("내용 입력!");
			fm.t_content.focus();
			return;
		}
		
// 첨부 용량 체크 		
		var file = fm.fileName_a;
		if(file.value != ""){
			
			var position = file.value.lastIndexOf("\\")+1;
			var fName = file.value.substr(position);
			var len = fName.length;
			
			if(len > 20){
				alert("첨부파일명 길이 20자리 이내");
				return;
			}
			// 사이즈체크
			var maxSize  = 2 * 1024 * 1024    //2MB
			var fileSize = 0;

			var browser=navigator.appName; // 브라우저 확인
			
			if(browser=="Microsoft Internet Explorer"){ // 익스플로러일 경우
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else{	// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}
			
			// alert("파일사이즈 : "+fileSize);
			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.    ");
				return;
			}
		}
		fm.action = "news_insert.jsp";
		// fm.action = "news_proc.jsp";
		fm.method = "post";
		fm.submit();
	}
</script>
		<form name ="news" enctype="multipart/form-data">
		<input type="hidden" name="t_work_gubun" value="insert">
			<div class="bord_list">
				<table class="bord_table">
					<colgroup>
						<col width="10%">
						<col width="*">
					</colgroup>
					<thead>
						<tr>
							<td>작성자</td>
							<td style="text-align:left;"><%=sessionName%></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>제&nbsp;&nbsp;목</td>
							<td style="text-align:left;">
							<input name="t_title" type="text" style="width:650px;">							
							</td>
						</tr>
						<tr>
							<td>내&nbsp;&nbsp;용</td>
							<td><textarea name="t_content"></textarea></td>
						</tr>
						
						<tr>
							<td>첨부파일</td>
							<td style="text-align:left;">
								<input type="file" name="fileName_a">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p style="text-align:center; padding-top:10px">
				<a href="/news/news_r.jsp"><input type="button" value="목 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
				<a href="javascript:save()"><input type="button" value="등 록" style="color:white;background:#42464d;width:50px;height:30px;border:none;"></a>
			</p>
	</div>
		</form>
	<%@ include file="/common_footer.jsp" %>
    </div>
  </body>
</html>