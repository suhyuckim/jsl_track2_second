<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Write Save</title>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" language="javascript">
    $(document).ready(function(){
    	$("#idCheckBtn").click(function(){  
    	 	urlLocation="/MemberIdCheck";  
    	    var params= "t_id="+mem.t_id.value; 
    	    if(mem.t_id.value == ""){
    	    	alert("아이디를 입력해주세요.");
    	    	mem.t_id.focus();
    	    	return;
    	    }
			$.ajax({
				type : "POST",
				url : urlLocation,
				data: params,
				dataType : "text",
				error : function(){
					alert('통신실패!!');
				},
				success : function(data){
//					alert("data : "+data)
					$(".t_id_info").html(data);
				 	if($.trim(data) =="중복된 아이디입니다."){
						$(".t_id_info").css("color","red");
						mem.t_idCheckValue.value = "";
					} else {
						mem.t_idCheckValue.value = mem.t_id.value;
						$(".t_id_info").css("color","green");
 					} 
				}
			});
    	 });
    });
</script> 
<script type="text/javascript">
	function save(){
		if(mem.t_id.value==""){
			alert("아이디를 입력해주세요.");
			mem.t_id.focus();
			return;
		}
		if(mem.t_idCheckValue.value == ""){ //id입력후 중복검사하지 않고 이름을 입력할 경우
			alert("아이디 중복검사를 해주세요.");
			mem.t_id.focus();
			return;
		}
		if(mem.t_idCheckValue.value != mem.t_id.value){ //id를 변경하고 중복체크안하고 등록을 클릭할 경우
			alert("아이디 중복검사를 해주세요.");
			mem.t_id.focus();
			return;
		}
		if(mem.t_name.value==""){
			alert("이름을 입력해주세요.");
			mem.t_name.focus();
			return;
		}
		if(mem.t_age.value==""){
			alert("나이를 입력해주세요.");
			mem.t_age.focus();
			return;
		}
		var ch = isNaN(mem.t_age.value);
		if(ch){
			alert("나이는 숫자만 입력해주세요.");
			mem.t_age.focus();
			return;
		}
		mem.action = "/MemberSave";
		mem.method = "post";
		mem.submit();
	}
</script>
</head>
<body>
<form name="mem"> 
	<table border="1">
		<tr>
			<td width="100" align="center" >ID</td>
			<td width="300" align="left">
				<input type="text" name="t_id" size="10">
				<input type="button" id="idCheckBtn" value="ID중복검사">
				<span class="t_id_info"></span>
				<input type="hidden" name="t_idCheckValue">
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">NAME</td>
			<td width="300" align="left">
				<input type="text" name="t_name" size="10">
			</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AREA</td>
			<td width="300" align="left">
				<select name="t_area" style="width:100px; height:25px;">
					<option value="서울">서울</option>
					<option value="대전">대전</option>
					<option value="부산">부산</option>
				</select>
			</td>		
		</tr>
		
		<tr>
			<td width="100" align="center">AGE</td>
			<td width="300" align="left">
				<input type="text" name="t_age" size="10">
			</td>		
		</tr>
	</table>
</form>	
	<br>
	<table width="420px" border="0">
		<tr>
			<td width="100" align="center">
				<input type="button" onClick="history.back()" value="목 록">
				<input type="button" onClick="save()" value="등 록">
			</td>		
		</tr>
	</table>
</body>
</html>