package common;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.client.HttpServerErrorException;

import java.io.*;  //嶺뚳퐘維��뜝�뜽苑닷뜝�룞�삕占쎌죨
public class CommonUtil{
	public static String file_dir_notice = "";
	public static String file_dir_news = "";
	
	//로그인 정보 관리자인지 아닌지
	public static boolean getCheckManager(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String ses_level = (String)session.getAttribute("session_level");
		boolean checkManager = true;
		if(ses_level == null) {
			model.addAttribute("t_msg","로그인 정보가 만료되었습니다.");
			model.addAttribute("t_url","/Member");
			checkManager = false;
		} else {
			if(!ses_level.equals("top")) {
				model.addAttribute("t_msg","관리자 메뉴입니다.");
				model.addAttribute("t_url","/Member");
				checkManager = false;
			}
		}
		return checkManager;
	}
	
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
	
	//泥⑤��뙆�씪 �슜�웾 寃��궗
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
	// null �뇦猿볦삕�뜝�럡�뀬
	public static String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		return result;
	}
	// �뜝�럩肉��뜝�럩�졑�뛾�룇猷뉐뜝占� �뜝�럡�뀏嶺뚯쉸裕뉓굢占� �뜝�럡�뀏嶺뚯쉸裕놅옙�맠�뜝�럥由��뼨�먯삕
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
	
	// �뜝�럩沅롥뜝�럥裕� �뜝�럡�뀏嶺뚯쉸裕뉓굢占� �뜝�럡�뀏嶺뚯쉸裕놅옙�맠�뜝�럥由��뼨�먯삕
	public String getPlusToday(int dayC){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.add(Calendar.DATE, dayC);
		
		return df.format(cal.getTime());
	}		
	
	// �뜝�럩沅롥뜝�럥裕롥뜝�럡�뀏嶺뚯쉻�삕
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}	
	// �뜝�럡�뀏嶺뚯쉸裕됵옙援ⓨ뜝�럥六� �뇦猿볦삕�뜝�럡�뀬
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
	// 占쎄껀�뜝�띁占쎄퀗�돯�뇡占� �뜝�럩�겱占쎈뎨占쎈봾�빢 嶺뚮씭�뒭野껓옙 嶺뚳옙熬곣뫗�뮡�뼨�먯삕 �뜝�럩�눁嶺뚯옕�윪�굜�뜉�삕占쎈뮡�뼨�먯삕
    public static String getLPad(String str, int size, String strFillText) {
//    	                          "13"           4            "9"
//    	                          "0013"
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// 占쎄껀�뜝�띁占쎄퀗�돯�뇡占� �뜝�럩�겱占쎈뎨占쎈봾�빢 嶺뚮씭�뒭野껓옙 嶺뚳옙熬곣뫗�뮡�뼨�먯삕 �뜝�럥�렧嶺뚯옕�윪�굜�뜉�삕占쎈뮡�뼨�먯삕
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
	// 占쎄껀�뜝�띁占쎄퀗�돯�뇡占� �뜝�럩�겱占쎈뎨占쎈봾�빢 嶺뚮씭�뒭野껓옙 嶺뚳옙熬곣뫗�뮡�뼨�먯삕 �뜝�럩沅롳옙紐댐쭗袁좉국嶺뚳옙熬곣뫗�뮡�뼨�먯삕
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }

	// 占쎈읂占쎌뵠筌욑옙 post獄쎻뫗�뻼
	public static String pageListPost(int current_page,int totalpage, int pageCount){
		int pagenumber;    //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕욃뜝�럥�빢
		int startpage;     //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� �뜝�럥六삣뜝�럩�굚 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		int endpage;       //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		int curpage;       //�뜝�럩逾졾뜝�럥吏쀥뜝�럥由�占썩뫁伊숋옙�겱 �뜝�럥由��뜝�럥裕� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		
		String strList=""; //占쎈뎨占쎈뿪�돇�뜝�럥彛� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕� 占쎈뎨占쎈봾裕욃뜝�럥諭�
		
//		pagenumber = 5;   //�뜝�럥由� �뜝�럩�꼨嶺뚮∥�샍占쎈꺄 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕욃뜝�럥�빢
		pagenumber = pageCount;   //�뜝�럥由� �뜝�럩�꼨嶺뚮∥�샍占쎈꺄 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕욃뜝�럥�빢'
		
		
		//�뜝�럥六삣뜝�럩�굚 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰 占쎈쨨占쎈뿫由��뼨�먯삕
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰 占쎈쨨占쎈뿫由��뼨�먯삕
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//占쎈／占쎈튇占쎌쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�빢�뤆�룊�삕 占썩몿�뫒亦낆룊�삕筌랃옙 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�솻洹ｏ옙占쎈펲 �뜝�럩�굚�뜝�럩諭� �뇦猿뗫윪占쎈뮡
		//占쎈／占쎈튇占쎌쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�빢�뤆�룊�삕 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�뤆�룊�삕 �뜝�럥彛�
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//嶺뚳퐙爰귟떋�먯�ゅ뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕� �뜝�럩�꼨嶺뚮∥�샍占쎈턄 �뜝�럥�닡�뜝�럥鍮믧뇦猿뗫윪占쎈뮡
		if(current_page > pagenumber){
			curpage = startpage -1;  //�뜝�럥六삣뜝�럩�굚�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�솻洹ｏ옙占쎈펲 1�뜝�럩�쓤�뜝�룞�삕 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�슖�댙�삕 �뜝�럩逾졾뜝�럥吏�
			strList = strList +"<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//�뜝�럥六삣뜝�럩�굚�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰占쎄껀�뜝�룞�삕�땻占� 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰濚밸Ŧ�뒩�뜝占� �뜝�럩�꼨嶺뚮∥�샍�굢占� �뜝�럥�걫�뜝�럥六�
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='selPage'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage("+curpage+")>"+curpage+"</a>";
			}
			curpage++;
		}

		//�뜝�럥痢잌뜝�럥�뱺 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�뤆�룊�삕 �뜝�럥�맠 �뜝�럩肉녑뜝�럥裕� �뇦猿뗫윪占쎈뮡
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-right'></i></a>";
								///NoticeList?t_sel="+selValue+"&t_search="+txtValue&r_page="+curpage+"
		}
		
		return strList;
	}			
    
    
	// 占쎈읂占쎌뵠筌욑옙 get獄쎻뫗�뻼
	public static String pageListGet(int current_page,int totalpage, String list_url){
		int pagenumber;    //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕욃뜝�럥�빢
		int startpage;     //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� �뜝�럥六삣뜝�럩�굚 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		int endpage;       //�뜝�럩�꼨嶺뚮∥�샍�굢占� �솻洹ｋ샍�굢�띿�ゅ뜝占� 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		int curpage;       //�뜝�럩逾졾뜝�럥吏쀥뜝�럥由�占썩뫁伊숋옙�겱 �뜝�럥由��뜝�럥裕� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰
		
		String strList=""; //占쎈뎨占쎈뿪�돇�뜝�럥彛� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕� 占쎈뎨占쎈봾裕욃뜝�럥諭�
		
		pagenumber = 2;   //�뜝�럥由� �뜝�럩�꼨嶺뚮∥�샍占쎈꺄 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕욃뜝�럥�빢
		
		//�뜝�럥六삣뜝�럩�굚 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰 占쎈쨨占쎈뿫由��뼨�먯삕
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰 占쎈쨨占쎈뿫由��뼨�먯삕
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//占쎈／占쎈튇占쎌쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�빢�뤆�룊�삕 占썩몿�뫒亦낆룊�삕筌랃옙 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�솻洹ｏ옙占쎈펲 �뜝�럩�굚�뜝�럩諭� �뇦猿뗫윪占쎈뮡
		//占쎈／占쎈튇占쎌쓡�뜝�럩逾좂춯�쉻�삕�뜝�럥�빢�뤆�룊�삕 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�뤆�룊�삕 �뜝�럥彛�
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//嶺뚳퐙爰귟떋�먯�ゅ뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뜝�럩逾ε뜝�럥�몦�뜝�럥裕� �뜝�럩�꼨嶺뚮∥�샍占쎈턄 �뜝�럥�닡�뜝�럥鍮믧뇦猿뗫윪占쎈뮡
		if(current_page > pagenumber){
			curpage = startpage -1;  //�뜝�럥六삣뜝�럩�굚�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰�솻洹ｏ옙占쎈펲 1�뜝�럩�쓤�뜝�룞�삕 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�슖�댙�삕 �뜝�럩逾졾뜝�럥吏�
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"' calss='icon'><i class='fas fa-arrow-circle-left fa-lg'></i></a>";
		}
						
		//�뜝�럥六삣뜝�럩�굚�뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰占쎄껀�뜝�룞�삕�땻占� 嶺뚮씭�쐠�뜝�룞彛뺝뜝占� �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 �뵓怨뺣쐡占쎄퉰濚밸Ŧ�뒩�뜝占� �뜝�럩�꼨嶺뚮∥�샍�굢占� �뜝�럥�걫�뜝�럥六�
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='on'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'>"+curpage+"</a>";
			}
			curpage++;
		}
		//�뜝�럥痢잌뜝�럥�뱺 �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕�뤆�룊�삕 �뜝�럥�맠 �뜝�럩肉녑뜝�럥裕� �뇦猿뗫윪占쎈뮡
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"' class='icon'><i class='fas fa-arrow-circle-right fa-lg'></i></a>";
		}
		
		return strList;
	}			
	
}