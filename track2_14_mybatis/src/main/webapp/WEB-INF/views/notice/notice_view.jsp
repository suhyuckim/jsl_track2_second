<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>    
<script>
	function goView(no){
		viewForm.t_no.value = no;
		viewForm.action = "/NoticeView";
		viewForm.method = "post";
		viewForm.submit();
	}
	function update(){
		viewForm.action = "/NoticeUpdateForm";
		viewForm.method = "post";
		viewForm.submit();
	}
	function deleteNotice(){
		if(confirm("정말로 삭제하시겠습니까?")){
			viewForm.action = "/NoticeDelete";
			viewForm.method = "post";
			viewForm.submit();
		}
	}
</script>
<form name="viewForm">
	<input type="hidden" name="t_no" value="${t_dto.getNo()}">
</form>
<div class="sub_title">
	<h2></h2>
		<div class="container">
		  <div class="location">
			<ul>
				<li class="btn_home">
					<a href="index.html"><i class="fa fa-home btn_plus"></i></a>
				</li>
				<li class="dropdown">
					<a href="">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="allclass.html">학과및모집안내</a>
						<a href="portfolio.html">포트폴리오</a>
						<a href="online.html">온라인접수</a>
						<a href="notice.html">커뮤니티</a>
					</div>
				</li>
				<li class="dropdown">
					<a href="">질문답변<i class="fa fa-plus btn_plus"></i></a>
					<div class="dropdown_menu">
						<a href="gratings.html">공지사항</a>
						<a href="gratings.html">질문답변</a>
						<a href="gratings.html">취업실적</a>
					</div>
				</li>
			</ul>
		  </div>
		</div><!-- container end -->
	</div>

	<div class="container">
		<div class="board_view">
			<h2>[공지] ${t_dto.getTitle()} </h2>
			<p class="info"><span class="user">${t_dto.getReg_info()}</span> | ${t_dto.getReg_date()} | <i class="fa fa-eye"></i> ${t_dto.getHit()}</p>
			<div class="board_body">
				<textarea name="t_content" readonly>${t_dto.getContent()}</textarea>
			</div>
			<div class="prev_next">
				<c:if test="${t_np.getPrevNo() ne null}">
					<a href="javascript:goView('${t_np.getPrevNo()}')" class="btn_prev"><i class="fa fa-angle-left"></i>
						<span class="prev_wrap">
							<strong>이전글</strong>
							<span>${t_np.getPrevTitle()}
								<c:if test="${t_np.getPrevTitle().length() > 14}">
								...
								</c:if>
							</span>
						</span>
					</a>
				</c:if>
				<div class="btn_3wrap">
					<a href="/Notice">목록</a> 
					<a href="javascript:update()">수정</a> 
					<a href="javascript:deleteNotice()">삭제</a>
				</div>
				<c:if test="${t_np.getNextNo() ne null}">
					<a href="javascript:goView('${t_np.getNextNo()}')" class="btn_next">
						<span class="next_wrap">
							<strong>다음글</strong>
							<span>${t_np.getNextTitle()}
								<c:if test="${t_np.getNextTitle().length() > 14}">
								...
								</c:if>
							</span>
						</span>
					<i class="fa fa-angle-right"></i></a>
				</c:if>
			</div>
		</div>
	</div>

	<!-- end contents -->
	
	<script>
		$(function() {
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
		});
	</script>
<%@ include file="../footer.jsp" %>	
 </body>
</html>