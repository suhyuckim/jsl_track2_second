package common;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.*;  //筌ｂ뫀占썸꽴占쏙옙�졃
public class CommonUtil{
	public static String file_dir_notice = "";
	public static String file_dir_news = "";
	
	// login session level
	public static String getSessionLevel(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String)session.getAttribute("session_level");
	}
	
	// login session name
	public static String getSessionName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String)session.getAttribute("session_name");
	}
	
	// login session id
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String)session.getAttribute("session_id");
	}
	
	//첨부파일 용량 검사
    public static boolean chkFileSize(String ff, int size, String dir){
		boolean yn = true;
		File fileSizeLong = new File(dir,ff);
        long fileLength = fileSizeLong.length();
        if(fileLength ==0){
            File dF = new File(dir,ff);
            dF.delete();
            yn= false;
        }
        if(fileLength > 1024 * 1024 * size){
            File dF = new File(dir,ff);
            boolean aa  = dF.delete();
            yn= false;
        }
		return yn;
	}	
	// null 野껓옙占쎄텢
	public static String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		return result;
	}
	// 占쎌뿯占쎌젾獄쏆룇占� 占쎄텊筌욎뮇肉� 占쎄텊筌욎뮆�쐭占쎈릭疫뀐옙
	public String getInputPlusDate(String inDate, int dayC){
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
        Date toDate = new Date();
        try {
        	toDate = df.parse(inDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }		
		cal.setTime(toDate);
		

		cal.add(Calendar.DATE, dayC);
		
		return df.format(cal.getTime());
	}	
	
	// 占쎌궎占쎈뮎 占쎄텊筌욎뮇肉� 占쎄텊筌욎뮆�쐭占쎈릭疫뀐옙
	public String getPlusToday(int dayC){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.add(Calendar.DATE, dayC);
		
		return df.format(cal.getTime());
	}		
	
	// 占쎌궎占쎈뮎占쎄텊筌욑옙
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}	
	// 占쎄텊筌욎뮉�굨占쎈뻼 野껓옙占쎄텢
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
	// �겫占썼�곌퉲釉� 占쎌쁽�뵳�딅땾 筌띾슦寃� 筌�袁⑹뒭疫뀐옙 占쎌뇢筌잛럩肄덌옙�뒭疫뀐옙
    public static String getLPad(String str, int size, String strFillText) {
//    	                          "13"           4            "9"
//    	                          "0013"
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// �겫占썼�곌퉲釉� 占쎌쁽�뵳�딅땾 筌띾슦寃� 筌�袁⑹뒭疫뀐옙 占쎈펶筌잛럩肄덌옙�뒭疫뀐옙
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
	// �겫占썼�곌퉲釉� 占쎌쁽�뵳�딅땾 筌띾슦寃� 筌�袁⑹뒭疫뀐옙 占쎌궎�몴紐꾠걹筌�袁⑹뒭疫뀐옙
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }

	// �럹�씠吏� post諛⑹떇
	public static String pageListPost(int current_page,int totalpage, int pageCount){
		int pagenumber;    //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞占쎈땾
		int startpage;     //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 占쎈뻻占쎌삂 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		int endpage;       //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		int curpage;       //占쎌뵠占쎈짗占쎈릭�⑥쥙�쁽 占쎈릭占쎈뮉 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		
		String strList=""; //�뵳�뗪쉘占쎈쭍 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞 �뵳�딅뮞占쎈뱜
		
//		pagenumber = 5;   //占쎈립 占쎌넅筌롫똻�벥 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞占쎈땾
		pagenumber = pageCount;   //占쎈립 占쎌넅筌롫똻�벥 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞占쎈땾'
		
		
		//占쎈뻻占쎌삂 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 �뤃�뗫릭疫뀐옙
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 �뤃�뗫릭疫뀐옙
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//�룯�빜�읂占쎌뵠筌욑옙占쎈땾揶쏉옙 �④쑴沅쏉옙留� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈癰귣��뼄 占쎌삂占쎌뱽 野껋럩�뒭
		//�룯�빜�읂占쎌뵠筌욑옙占쎈땾揶쏉옙 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈揶쏉옙 占쎈쭡
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//筌ｃ꺂苡뀐쭪占� 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞 占쎌넅筌롫똻�뵠 占쎈툡占쎈빒野껋럩�뒭
		if(current_page > pagenumber){
			curpage = startpage -1;  //占쎈뻻占쎌삂占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈癰귣��뼄 1占쎌읅占쏙옙 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗
			strList = strList +"<a href=javascript:goPage("+curpage+") class='icon'><i class='fas fa-arrow-circle-left fa-lg'></i></a>";
		}
						
		//占쎈뻻占쎌삂占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈�겫占쏙옙苑� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈繹먮슣占� 占쎌넅筌롫똻肉� 占쎈ご占쎈뻻
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='on'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage("+curpage+")>"+curpage+"</a>";
			}
			curpage++;
		}

		//占쎈츟占쎈퓠 占쎈읂占쎌뵠筌욑옙揶쏉옙 占쎈쐭 占쎌뿳占쎈뮉 野껋럩�뒭
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage("+curpage+") class='icon'><i class='fas fa-arrow-circle-right fa-lg'></i></a>";
								///NoticeList?t_sel="+selValue+"&t_search="+txtValue&r_page="+curpage+"
		}
		
		return strList;
	}			
    
    
	// �럹�씠吏� get諛⑹떇
	public static String pageListGet(int current_page,int totalpage, String list_url){
		int pagenumber;    //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞占쎈땾
		int startpage;     //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 占쎈뻻占쎌삂 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		int endpage;       //占쎌넅筌롫똻肉� 癰귣똻肉э쭪占� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		int curpage;       //占쎌뵠占쎈짗占쎈릭�⑥쥙�쁽 占쎈릭占쎈뮉 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		
		String strList=""; //�뵳�뗪쉘占쎈쭍 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞 �뵳�딅뮞占쎈뱜
		
		pagenumber = 2;   //占쎈립 占쎌넅筌롫똻�벥 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞占쎈땾
		
		//占쎈뻻占쎌삂 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 �뤃�뗫릭疫뀐옙
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈 �뤃�뗫릭疫뀐옙
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//�룯�빜�읂占쎌뵠筌욑옙占쎈땾揶쏉옙 �④쑴沅쏉옙留� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈癰귣��뼄 占쎌삂占쎌뱽 野껋럩�뒭
		//�룯�빜�읂占쎌뵠筌욑옙占쎈땾揶쏉옙 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈揶쏉옙 占쎈쭡
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//筌ｃ꺂苡뀐쭪占� 占쎈읂占쎌뵠筌욑옙 占쎌뵥占쎈쑔占쎈뮞 占쎌넅筌롫똻�뵠 占쎈툡占쎈빒野껋럩�뒭
		if(current_page > pagenumber){
			curpage = startpage -1;  //占쎈뻻占쎌삂占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈癰귣��뼄 1占쎌읅占쏙옙 占쎈읂占쎌뵠筌욑옙嚥∽옙 占쎌뵠占쎈짗
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"' calss='icon'><i class='fas fa-arrow-circle-left fa-lg'></i></a>";
		}
						
		//占쎈뻻占쎌삂占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈�겫占쏙옙苑� 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈繹먮슣占� 占쎌넅筌롫똻肉� 占쎈ご占쎈뻻
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='on'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'>"+curpage+"</a>";
			}
			curpage++;
		}
		//占쎈츟占쎈퓠 占쎈읂占쎌뵠筌욑옙揶쏉옙 占쎈쐭 占쎌뿳占쎈뮉 野껋럩�뒭
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"' class='icon'><i class='fas fa-arrow-circle-right fa-lg'></i></a>";
		}
		
		return strList;
	}			
	
}