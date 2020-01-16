package common;

import java.text.*;
import java.util.*;
import java.io.*;

public class CommonUtil{
	
	public static String file_dir_notice = "C:/webserver/webapps/ROOT/file_room/notice/"; 
	public static String file_dir_news = "C:/webserver/webapps/ROOT/file_room/news/"; 
	
	//���ó�¥
	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}
	
	// ��¥���� �˻�
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
	
	// ��¥���� �˻�2
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
	
	// ��¥���� �˻�3
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
	
	// ������ �ڸ��� ��ŭ ä��� ����ä���
    public static String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	
	// ������ �ڸ��� ��ŭ ä��� ����ä���
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
	
	// ������ �ڸ��� ��ŭ ä��� ������ä���
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }
	
	//null �˻�
	public static String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		return result;
	}

	// Ȩ������ ��������
	public static String pageList(int current_page,int totalpage, String list_url){
		int pagenumber;    //ȭ�鿡 ������ ������ �ε�����
		int startpage;     //ȭ�鿡 ������ ���� ������ ��ȣ
		int endpage;       //ȭ�鿡 ������ ������ ������ ��ȣ
		int curpage;       //�̵��ϰ��� �ϴ� ������ ��ȣ
		
		String strList=""; //���ϵ� ������ �ε��� ����Ʈ
		
		pagenumber = 5;   //�� ȭ���� ������ �ε�����
		
		//���� ������ ��ȣ ���ϱ�
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//������ ������ ��ȣ ���ϱ�
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//������������ ���� ������ ������ ��ȣ���� ���� ���
		//������������ ������ ������ ��ȣ�� ��
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//ù��° ������ �ε��� ȭ���� �ƴѰ��
		if(current_page > pagenumber){
			curpage = startpage -1;  //���������� ��ȣ���� 1���� �������� �̵�
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//���������� ��ȣ���� ������ ������ ��ȣ���� ȭ�鿡 ǥ��
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a href='' class='active'>["+current_page+"]</a>";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>["+curpage+"]</font></a>";
			}
			curpage++;
		}
		//�ڿ� �������� �� �ִ� ���
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-right'></i></a>";
		}		
		return strList;
	}
}