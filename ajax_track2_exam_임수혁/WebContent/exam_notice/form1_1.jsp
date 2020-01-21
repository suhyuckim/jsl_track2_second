<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax 간단 테스트</title>
 
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" language="javascript">
 
    $(document).ready(function(){
    	$("#btnOK").click(function(){
    		
    	    var urlLocation="form1_2.jsp";  
    	    var params="id="+ $('#t_id').val()+"&name="+$('#t_name').val();  
		        $.ajax({
		            type : "GET",
		            url : urlLocation,
		            data:params,
		            dataType : "text",
		            error : function(){
		                alert('통신실패!!');
		            },
		            success : function(data){
		                alert("통신데이터 값 : " + data);
	                	$("#dataArea").html(data);
		            }
		             
		        });
    	 });
 
    });
 
</script>
 
</head>
<body>
 	<form name="ffo">
	    <input type="text" name="t_id" id="t_id">
	    <input type="text" name="t_name" id="t_name">
	    <input type="button" id="btnOK" value="button~~">
	    <div id="dataArea"></div>
 	</form>
</body>
</html>

