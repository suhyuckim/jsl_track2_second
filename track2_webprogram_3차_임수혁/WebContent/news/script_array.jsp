<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="java.util.*,dto.Array_dto" %>
<% 
Array_dto dto_1 = new Array_dto("R_20_001","세탁기","2","홍기동","2020-01-02");
Array_dto dto_2 = new Array_dto("R_20_001","TV","2","홍기동","2020-01-02");
Array_dto dto_3 = new Array_dto("R_20_001","가습기","1","홍기동","2020-01-02");
Array_dto dto_4 = new Array_dto("R_20_001","건조기","2","홍기동","2020-01-02");

	ArrayList<Array_dto> arr = new ArrayList<Array_dto>();
	arr.add(dto_1);
  	arr.add(dto_2);
 	arr.add(dto_3);
 	arr.add(dto_4); 

%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>array</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">	
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function pro_amount(){
	
		}
		function pro_del(){ //먼저 배열인지 아닌지 체크
			var len = product.p_del.length;
			if(len == undefined){ 
				alert(product.p_del.value);
			} else { 
				for(var k=0; k<len; k++){ 
					alert(product.p_del[k].value);
				}
			}
			product.action="/JavaScriptArray";
			product.method="post";
			product.submit();
		}	
		function allDel(){
			//var len = product.p_del.length; //배열이 아닌 길이를 세려고 하면 undefined가 뜬다
			//alert(len);
			var len = product.p_del.length; //배열인지 아닌치 체크
			if(product.p_allDel.checked == true) {//true이면 전체선택부분이 체크되어있을시
				if(len == undefined){ //배열 아닐때
					product.p_del.checked = true;
				} else { //배열일때
					for(var k=0; k<len; k++){ //전체선택
						product.p_del[k].checked = true; 
					}
				}
			} else{ //체크를 해지, 배열인지 아닌지부터 체크
				if(len == undefined){ //배열 아닐때
					product.p_del.checked = false;
				} else { //배열일때
					for(var k=0; k<len; k++){ //전체선택해지
						product.p_del[k].checked = false; 
					}
				}
			}
		}
	</script>	
</head>
<body>
	<div class="container">
		<div class="search_wrap">
			<div class="record_group">
				<p>총주문 수 : <span><%=arr.size()%></span>건</p>
			</div>
		</div>
	</div>
	<form name="product">
	<div class="board_list">
	
		<table class="board_table">
			<colgroup>
				<col width="15%">
				<col width="*">
				<col width="15%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>주문번호</th>
					<th>제품명</th>
					<th>수량</th>
					<th>삭 제<input type="checkbox" name="p_allDel" onChange="allDel()">전체선택</th> 
					<!-- onChange() 값이 바뀌면 실행해라 체크해지하거나 체크할시 -->
					<th>주문자</th>
					<th>주문일</th>
				</tr>
			</thead>
			<tbody>
<%
			for(int k=0; k < arr.size(); k++){
%>			
				<tr>
<%
					if(k == 0){
%>					
					<td align="center" rowspan="<%=arr.size()%>"><%=arr.get(k).getOrderNo()%></td>
<%
					}
%>
					
					<td class="title"><%=arr.get(k).getProductName()%></td>
					<td class="title" style="text-align:center;">
						<input type="text" name ="p_count" class="input_50px" value="<%=arr.get(k).getAmount()%>"> 
					</td>
					<td align="center"><input type="checkbox" name="p_del"  value="<%=arr.get(k).getProductName()%>"></td>
					<td align="center"><%=arr.get(k).getOrderName()%></td>
					<td align="center"><%=arr.get(k).getOrderDate()%></td>
				</tr>
<%
			}
%>	
			
			</tbody>
		</table>
		</form>

		<div class="btn_wrap">
			<input type="button" onClick="pro_amount()" value="수량 일괄처리" class="btn_ok">&nbsp;&nbsp;
			<input type="button" onClick="pro_del()" value="삭제 일괄처리" class="btn_ok">
		</div>		
	</div>
 </body>
</html>