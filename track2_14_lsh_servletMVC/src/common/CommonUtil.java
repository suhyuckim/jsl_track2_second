package common;
import java.text.*;
import java.util.*;
import java.io.*;  //泥⑤�愿��젴
public class CommonUtil{
	public static String file_dir_notice = "";
	public static String file_dir_news = "";
	
	//泥⑤��뙆�씪 �슜�웾寃��궗
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
	// null 寃��궗
	public static String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		return result;
	}
	// �엯�젰諛쏆� �궇吏쒖뿉 �궇吏쒕뜑�븯湲�
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
	
	// �삤�뒛 �궇吏쒖뿉 �궇吏쒕뜑�븯湲�
	public String getPlusToday(int dayC){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.add(Calendar.DATE, dayC);
		
		return df.format(cal.getTime());
	}		
	
	// �삤�뒛�궇吏�
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}	
	// �궇吏쒗삎�떇 寃��궗
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
	// 遺�議깊븳 �옄由ъ닔 留뚰겮 梨꾩슦湲� �쇊履쎌콈�슦湲�
    public static String getLPad(String str, int size, String strFillText) {
//    	                          "13"           4            "9"
//    	                          "0013"
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// 遺�議깊븳 �옄由ъ닔 留뚰겮 梨꾩슦湲� �뼇履쎌콈�슦湲�
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
	// 遺�議깊븳 �옄由ъ닔 留뚰겮 梨꾩슦湲� �삤瑜몄そ梨꾩슦湲�
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }

	// 페이지 post방식
	public static String pageListPost(int current_page,int totalpage, int pageCount){
		int pagenumber;    //�솕硫댁뿉 蹂댁뿬吏� �럹�씠吏� �씤�뜳�뒪�닔
		int startpage;     //�솕硫댁뿉 蹂댁뿬吏� �떆�옉 �럹�씠吏� 踰덊샇
		int endpage;       //�솕硫댁뿉 蹂댁뿬吏� 留덉�留� �럹�씠吏� 踰덊샇
		int curpage;       //�씠�룞�븯怨좎옄 �븯�뒗 �럹�씠吏� 踰덊샇
		
		String strList=""; //由ы꽩�맆 �럹�씠吏� �씤�뜳�뒪 由ъ뒪�듃
		
//		pagenumber = 5;   //�븳 �솕硫댁쓽 �럹�씠吏� �씤�뜳�뒪�닔
		pagenumber = pageCount;   //�븳 �솕硫댁쓽 �럹�씠吏� �씤�뜳�뒪�닔'
		
		
		//�떆�옉 �럹�씠吏� 踰덊샇 援ы븯湲�
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//留덉�留� �럹�씠吏� 踰덊샇 援ы븯湲�
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//珥앺럹�씠吏��닔媛� 怨꾩궛�맂 留덉�留� �럹�씠吏� 踰덊샇蹂대떎 �옉�쓣 寃쎌슦
		//珥앺럹�씠吏��닔媛� 留덉�留� �럹�씠吏� 踰덊샇媛� �맖
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//泥ル쾲吏� �럹�씠吏� �씤�뜳�뒪 �솕硫댁씠 �븘�땶寃쎌슦
		if(current_page > pagenumber){
			curpage = startpage -1;  //�떆�옉�럹�씠吏� 踰덊샇蹂대떎 1�쟻�� �럹�씠吏�濡� �씠�룞
			strList = strList +"<a href=javascript:goPage("+curpage+") class='icon'><i class='fas fa-arrow-circle-left fa-lg'></i></a>";
		}
						
		//�떆�옉�럹�씠吏� 踰덊샇遺��꽣 留덉�留� �럹�씠吏� 踰덊샇源뚯� �솕硫댁뿉 �몴�떆
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='on'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage("+curpage+")>"+curpage+"</a>";
			}
			curpage++;
		}

		//�뮘�뿉 �럹�씠吏�媛� �뜑 �엳�뒗 寃쎌슦
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage("+curpage+") class='icon'><i class='fas fa-arrow-circle-right fa-lg'></i></a>";
								///NoticeList?t_sel="+selValue+"&t_search="+txtValue&r_page="+curpage+"
		}
		
		return strList;
	}			
    
    
	// 페이지 get방식
	public static String pageListGet(int current_page,int totalpage, String list_url){
		int pagenumber;    //�솕硫댁뿉 蹂댁뿬吏� �럹�씠吏� �씤�뜳�뒪�닔
		int startpage;     //�솕硫댁뿉 蹂댁뿬吏� �떆�옉 �럹�씠吏� 踰덊샇
		int endpage;       //�솕硫댁뿉 蹂댁뿬吏� 留덉�留� �럹�씠吏� 踰덊샇
		int curpage;       //�씠�룞�븯怨좎옄 �븯�뒗 �럹�씠吏� 踰덊샇
		
		String strList=""; //由ы꽩�맆 �럹�씠吏� �씤�뜳�뒪 由ъ뒪�듃
		
		pagenumber = 2;   //�븳 �솕硫댁쓽 �럹�씠吏� �씤�뜳�뒪�닔
		
		//�떆�옉 �럹�씠吏� 踰덊샇 援ы븯湲�
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//留덉�留� �럹�씠吏� 踰덊샇 援ы븯湲�
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//珥앺럹�씠吏��닔媛� 怨꾩궛�맂 留덉�留� �럹�씠吏� 踰덊샇蹂대떎 �옉�쓣 寃쎌슦
		//珥앺럹�씠吏��닔媛� 留덉�留� �럹�씠吏� 踰덊샇媛� �맖
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//泥ル쾲吏� �럹�씠吏� �씤�뜳�뒪 �솕硫댁씠 �븘�땶寃쎌슦
		if(current_page > pagenumber){
			curpage = startpage -1;  //�떆�옉�럹�씠吏� 踰덊샇蹂대떎 1�쟻�� �럹�씠吏�濡� �씠�룞
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"' calss='icon'><i class='fas fa-arrow-circle-left fa-lg'></i></a>";
		}
						
		//�떆�옉�럹�씠吏� 踰덊샇遺��꽣 留덉�留� �럹�씠吏� 踰덊샇源뚯� �솕硫댁뿉 �몴�떆
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='on'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'>"+curpage+"</a>";
			}
			curpage++;
		}
		//�뮘�뿉 �럹�씠吏�媛� �뜑 �엳�뒗 寃쎌슦
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"' class='icon'><i class='fas fa-arrow-circle-right fa-lg'></i></a>";
		}
		
		return strList;
	}			
	
}









