package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Event_DTO;
import dto.Notice_DTO;

public class Event_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public Event_DTO getEventView(String even_no){ //event 상세조회
		Event_DTO dto = null;
		String query = " select a.event_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), "+
				 	   " to_char(a.reg_start, 'yyyy-MM-dd'), to_char(a.reg_end, 'yyyy-MM-dd'), a.hit "+
					   " from A02_TRACK2_HOME_EVENT a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.event_no = '"+even_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String notice_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);				
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				String reg_start 	= rs.getString(6);
				String reg_end 		= rs.getString(7);
				int    hit 			= rs.getInt(8);
				dto = new Event_DTO(notice_no, title, content, reg_id, reg_date, reg_start, reg_end, hit);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int eventHit(String event_no){ //조회수 증가
		int result = 0;
		String query = " update A02_TRACK2_HOME_EVENT set hit = hit+1 where event_no ='"+event_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException eventHit():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception eventHit():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("eventHit() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getMaxNo(){  //event_no 최대값 검사
		String query  = " select max(event_no) from A02_TRACK2_HOME_EVENT ";
		String result = null;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMaxNo():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMaxNo():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMaxNo() close:"+e.getMessage());
			}
		}
		return result;
	}
	
	public String getEventNo() { //event_no 최대값 검사해서 id 만들기
		String eventNo = getMaxNo();
				
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(eventNo == null) {
			eventNo = nowYear+"_0001";
		} else {
			String dataYear = eventNo.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(eventNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				eventNo = dataYear +"_"+r;
			} else {
				eventNo = nowYear+"_"+"0001";
			}		
		}		
		return eventNo;
	}
	
	public int updateEvent(String event_no, String title, String content, String reg_id, String reg_date, String reg_start, String reg_end) { //faq 수정
		int result = 0;	
		String query = " update A02_TRACK2_HOME_EVENT set title='"+title+"', content='"+content+"', reg_id='"+reg_id+"', "+
				       " reg_date = '"+reg_date+"', reg_start = '"+reg_start+"', reg_end = '"+reg_end+"' "+
					   " where event_no = '"+event_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateEvent():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateEvent():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateEvent() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int deleteEvent(String event_no) { //event 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_HOME_EVENT where event_no = '"+event_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteEvent():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteEvent():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteEvent() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertEvent(Event_DTO dto) { //event 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_HOME_EVENT(event_no, title, content, reg_id, reg_date, reg_start, reg_end, hit) "+
					   " values('"+dto.getEvent_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', '"+dto.getReg_start()+"', '"+dto.getReg_end()+"', "+dto.getHit()+") ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertEvent():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertEvent():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertEvent() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public ArrayList<Event_DTO> getEventList(String selValue, String txtValue){ //event 목록조회
		ArrayList<Event_DTO> dtos = new ArrayList<Event_DTO>();
		String query = " select a.event_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), "+
					   " to_char(a.reg_start, 'yyyy-MM-dd'), to_char(a.reg_end, 'yyyy-MM-dd'), a.hit "+
					   " from A02_TRACK2_HOME_EVENT a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id ";
		
		if(selValue.equals("title") || selValue.equals("content")){
			query += " and "+selValue+" like '%"+txtValue+"%' order by a.event_no desc ";
		} else if(selValue.equals("all")) {
			query += " order by a.event_no desc ";
		}
		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String event_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);	
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				String reg_start 	= rs.getString(6);
				String reg_end 		= rs.getString(7);
				int hit 			= rs.getInt(8);
				Event_DTO dto = new Event_DTO(event_no, title, content, reg_id, reg_date, reg_start, reg_end, hit);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
}