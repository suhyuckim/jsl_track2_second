package common;

import java.text.*;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtil{
	
	public static String file_dir_notice = "C:/임수혁_eclipse_source/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/lsh_servlet_homepage/file_room/notice"; 
	public static String file_dir_news = "C:/임수혁_eclipse_source/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/lsh_servlet_homepage/file_room/news"; 
	public static String file_dir_event = "C:/임수혁_eclipse_source/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/lsh_servlet_homepage/file_room/event"; 

	//비밀번호_암호화메소드
    public static String encryptSHA256(String value) throws NoSuchAlgorithmException{
        String encryptData ="";
         
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
 
        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
        }
        return encryptData;
    }
    
	//문자치환
	public static Integer removeChar(String str) {
		String result = str.substring(0,4)+str.substring(5,7)+str.substring(8);
		return Integer.parseInt(result);
	}
	
	//오늘날짜
		public static String getToday() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String today = sdf.format(date);
			return today;
		}
		
	// 날짜형식 검사
		public static boolean checkDate(String checkDate){
			SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd");
			boolean checkD = true;
			dateFormat.setLenient(false);
			try{
				dateFormat.parse(checkDate);
			}catch(ParseException e){
				checkD = false;
			}
			return checkD;
		}
		
	// 날짜형식 검사2
		public static boolean checkDate2(String checkDate){
			SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyyMM");
			boolean checkD = true;
			dateFormat.setLenient(false);
			try{
				dateFormat.parse(checkDate);
			}catch(ParseException e){
				checkD = false;
			}
			return checkD;
		}
		
	// 날짜형식 검사3
		public static boolean checkDate3(String checkDate){
			SimpleDateFormat dateFormat = 
				new SimpleDateFormat("yyyyMMdd");
			boolean checkD = true;
			dateFormat.setLenient(false);
			try{
				dateFormat.parse(checkDate);
			}catch(ParseException e){
				checkD = false;
			}
				return checkD;
		}
		
	// 부족한 자리수 만큼 채우기 왼쪽채우기
	    public static String getLPad(String str, int size, String strFillText) {
	        for(int i = (str.getBytes()).length; i < size; i++) {
	            str = strFillText + str;
	        }
	        return str;
	    }
		
	// 부족한 자리수 만큼 채우기 양쪽채우기
	    public static String getCPad(String str, int size, String strFillText) {
	        int intPadPos = 0;
	        for(int i = (str.getBytes()).length; i < size; i++) {
	            if(intPadPos == 0) {
	                str += strFillText;
	                intPadPos = 1;
	            } else {
	                str = strFillText + str;
	                intPadPos = 0;
	            }
	        }
	        return str;
	    }
		
	// 부족한 자리수 만큼 채우기 오른쪽채우기
	    public static String getRPad(String str, int size, String strFillText) {
	        for(int i = (str.getBytes()).length; i < size; i++) {
	            str += strFillText;
	        }
	        return str;
	    }
		
	//null 검사
		public static String checkNull(String sValue){
			String result="";
			if(sValue != null) result = sValue;
			return result;
		}
		
		
	// 홈페이지 페이지수(방법 2) - 꼬리표 없애기
		public static String pageList2(int current_page, int totalpage){
			int pagenumber;    //화면에 보여질 페이지 인덱스수
			int startpage;     //화면에 보여질 시작 페이지 번호
			int endpage;       //화면에 보여질 마지막 페이지 번호
			int curpage;       //이동하고자 하는 페이지 번호
					
			String strList=""; //리턴될 페이지 인덱스 리스트
					
			pagenumber = 3;   //한 화면의 페이지 인덱스수
					
	//시작 페이지 번호 구하기
			startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
	//마지막 페이지 번호 구하기
			endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
	//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
	//총페이지수가 마지막 페이지 번호가 됨
					
			if(totalpage <= endpage)  endpage = totalpage;
								
	//첫번째 페이지 인덱스 화면이 아닌경우
			if(current_page > pagenumber){
				curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
				strList = strList +"<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-left'></i></a>";
			}
									
	//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
				curpage = startpage;
				while(curpage <= endpage){
					if(curpage == current_page){
						strList = strList +"<a href='' class='active'>["+current_page+"]</a>";
					} else {
						strList = strList +"<a href=javascript:goPage("+curpage+")><font color=#666699>["+curpage+"]</font></a>";
					}
					curpage++;
				}
	//뒤에 페이지가 더 있는 경우
					if(totalpage > endpage){
						curpage = endpage+1;
						strList = strList + "<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-right'></i></a>";
					}		
					return strList;
				}	
		
		
	// 홈페이지 페이지수(GET방식)
		public static String pageList(int current_page,int totalpage, String list_url){
			int pagenumber;    //화면에 보여질 페이지 인덱스수
			int startpage;     //화면에 보여질 시작 페이지 번호
			int endpage;       //화면에 보여질 마지막 페이지 번호
			int curpage;       //이동하고자 하는 페이지 번호
			
			String strList=""; //리턴될 페이지 인덱스 리스트
			
			pagenumber = 5;   //한 화면의 페이지 인덱스수
			
			//시작 페이지 번호 구하기
			startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
			//마지막 페이지 번호 구하기
			endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
			//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
			//총페이지수가 마지막 페이지 번호가 됨
			
			if(totalpage <= endpage)  endpage = totalpage;
						
			//첫번째 페이지 인덱스 화면이 아닌경우
			if(current_page > pagenumber){
				curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-left'></i></a>";
			}
							
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
			curpage = startpage;
			while(curpage <= endpage){
				if(curpage == current_page){
					strList = strList +"<a href='' class='active'>["+current_page+"]</a>";
				} else {
					strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>["+curpage+"]</font></a>";
				}
				curpage++;
			}
			//뒤에 페이지가 더 있는 경우
			if(totalpage > endpage){
				curpage = endpage+1;
				strList = strList + "<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-right'></i></a>";
			}		
			return strList;
		}
}