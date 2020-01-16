/*
문자치환
*/
function js_removeChar(str, chr) {
    var src = new String(str);
    var tar = new String();
    var i, len=src.length;
    for (i=0; i < len; i++) {
        if (src.charAt(i) == chr)
            tar += '';
        else
            tar += src.charAt(i);
    }
    return tar;
}


/*
공백체크
*/
function checkEmpty(obj, alertString ){ //입력상자, 공백
   var errorMesg =  alertString ;
   if(trim(obj.value) == "" ) { //trim 양쪽 공백 없애기(ex. spacebar)
		alert(errorMesg);
		obj.focus();
		return false;
   } else {
		return true;   
   }	
}
/*
문자열 앞뒤에있는 공백없에기
*/
function trim( arg )
{
   var st = 0;
   var len = arg.length;

   //문자열앞에 공백문자가 들어 있는 Index 추출
   while( (st < len) && (arg.charCodeAt(st) == 32) )
   {
      st++;
   }
   //문자열뒤에 공백문자가 들어 있는 Index 추출
   while( (st < len) && (arg.charCodeAt(len-1) == 32) )
   {
      len--;
   }
   return ((st > 0) || (len < arg.length)) ? arg.substring(st, len) : arg;
}