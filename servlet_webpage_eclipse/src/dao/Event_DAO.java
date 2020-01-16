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

public class Event_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int updateEvent_servlet(String event_no, String title, String content, 
								   String reg_id, String reg_date, String start_date, 
								   String end_date, String file_name_1) { //이벤트 수정
		int result = 0;	
		String query = ""; 
		if(file_name_1 == null) {
			query = " update A02_TRACK2_SERVLET2_EVENT set title ='"+title+"', content ='"+content+"', "+
					" reg_id='"+reg_id+"', reg_date = '"+reg_date+"', start_date = '"+start_date+"', "+
					" end_date = '"+end_date+"', file_name_1 = null "+
					" where event_no = '"+event_no+"' ";
		} else {
			query = " update A02_TRACK2_SERVLET2_EVENT set title ='"+title+"', content ='"+content+"', "+
				    " reg_id='"+reg_id+"', reg_date = '"+reg_date+"', start_date = '"+start_date+"', "+
				    " end_date = '"+end_date+"', file_name_1 = '"+file_name_1+"' "+
				    " where event_no = '"+event_no+"' ";
		}
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateEvent_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateEvent_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateEvent_servlet() close:"+e.getMessage());
			}
		}
		return result;
	}
				
	public int deleteEvent_servlet(String event_no) { //이벤트 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_SERVLET2_EVENT where event_no = '"+event_no+"' ";
		System.out.println(query);
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteEvent_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteEvent_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteEvent_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public Event_DTO getEventView_servlet(String even_no){ //event 상세조회
		Event_DTO dto = null;
		String query = " select a.event_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), "+
				 	   " to_char(a.start_date, 'yyyy-MM-dd'), to_char(a.end_date, 'yyyy-MM-dd'), a.file_name_1, a.hit "+
					   " from A02_TRACK2_SERVLET2_EVENT a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.event_no = '"+even_no+"' ";
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
				String start_date 	= rs.getString(6);
				String end_date 	= rs.getString(7);
				String file_name_1 	= rs.getString(8);
				int hit 			= rs.getInt(9);
				dto = new Event_DTO(event_no, title, content, reg_id, reg_date, start_date, end_date, file_name_1, hit);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventView_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventView_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventView_servlet() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int eventHit_servlet(String event_no){ //조회수 증가
		int result = 0;
		String query = " update A02_TRACK2_SERVLET2_EVENT set hit = hit+1 where event_no ='"+event_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException eventHit_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception eventHit_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("eventHit_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getMaxNo(){  //event_no 최대값 검사
		String query  = " select max(event_no) from A02_TRACK2_SERVLET2_EVENT ";
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
	
	public int getEventCount(){ //게시물조회
		String query  = " select count(*) from A02_TRACK2_SERVLET2_EVENT ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventCount() close:"+e.getMessage());
			}
		}
		return count;
	}	
	
	public int insertEvent_servlet(Event_DTO dto) { //event 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET2_EVENT(event_no, title, content, file_name_1, reg_id, reg_date, start_date, end_date, hit) "+
					   " values('"+dto.getEvent_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getFile_name_1()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', '"+dto.getStart_date()+"', '"+dto.getEnd_date()+"', "+dto.getHit()+") ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertEvent_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertEvent_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertEvent_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public ArrayList<Event_DTO> getEventList_servlet(String selValue, String txtValue){ //event 목록조회
		ArrayList<Event_DTO> dtos = new ArrayList<Event_DTO>();
		String query = " select a.event_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), "+
					   " to_char(a.start_date, 'yyyy-MM-dd'), to_char(a.end_date, 'yyyy-MM-dd'), a.file_name_1, a.hit "+
					   " from A02_TRACK2_SERVLET2_EVENT a, A02_TRACK2_SERVLET2_MEMBER b "+
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
				String start_date 	= rs.getString(6);
				String end_date 	= rs.getString(7);
				String file_name_1 	= rs.getString(8);
				int hit 			= rs.getInt(9);
				Event_DTO dto = new Event_DTO(event_no, title, content, reg_id, reg_date, start_date, end_date, file_name_1, hit);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventList_serlvet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventList_serlvet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventList_serlvet() close:"+e.getMessage());
			}
		}
		return dtos;
	}
}