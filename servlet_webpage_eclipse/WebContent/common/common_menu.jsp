<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="/Index"><i class="fa fa-home"></i></a></li>
					<li class="dropdown">
						<a href="">학원소개<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">인사말</a>
							<a href="gratomgs/html">회사연혁</a>
							<a href="gratomgs/html">조직도</a>
							<a href="gratomgs/html">오시는길</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="">교육과정<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">일본어 기초과정</a>
							<a href="gratomgs/html">일본어 심화과정</a>
							<a href="gratomgs/html">일본어 회화과정</a>
							<a href="gratomgs/html">일본어 시험대비과정</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="">수강신청<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratomgs/html">JLPT</a>
							<a href="gratomgs/html">JPT</a>
						</div>
					</li>
					<li class="dropdown">
						<a href="/NoticeList">커뮤니티<i class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="/NoticeList">NOTICE</a>
							<a href="/NewList_Servlet">NEWS</a>
							<a href="gratomgs/html">FREEBOARD</a>
							<a href="gratomgs/html">Q&A</a>
							<a href="/FaqList_Servlet">FAQ</a>
							<a href="/EventList_Servlet">EVENT</a>
						</div>
					</li>
					<c:if test="${session_id eq 'manager'}">
						<li class="dropdown">
							<a href="">회원관리<i class="fa fa-plus btn_plus"></i></a>
							<div class="dropdown_menu">
								<a href="/MemberList_Servlet">회원목록</a>
								<a href="">직원관리</a>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
		</div>