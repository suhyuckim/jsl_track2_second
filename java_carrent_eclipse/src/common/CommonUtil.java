package common;
import java.text.*;
import java.util.Date;
public class CommonUtil{
	
	//날짜 비교
	public int compareDate(String a) {
		int result = 0;
		String today = getToday();
		if(Integer.parseInt(today)>Integer.parseInt(a)) {
			result = 1; //입력한 날짜보다 오늘날짜가 최근이면 result는 1값을 가짐.
		}
		return result;
	}
	
	// 오늘날짜
	public String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}
	
	// 날짜형식 검사2
		public boolean checkDate2(String checkDate){
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
	
	// 날짜형식 검사
	public boolean checkDate(String checkDate){
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
    //문자 형식 검사
    public static boolean getCheckstr(String str) {
    	boolean Checkstr = true;
    	try {
    		int str_int = Integer.parseInt(str);
    	}catch(Exception e){
    		Checkstr = false;
    	}
    	return Checkstr;
    }
}