package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Event_DTO;
import dto.Event_M_DTO;

public class Event_M_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;

	public int getStatusCount(String event_no){ //
		String query = " select count(*) from a02_track2_home_event_m "+
					   " where event_no = '"+event_no+"' and status ='y' ";
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		}catch(SQLException se){
			System.out.println("SQLException getStatusCount():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception getStatusCount():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("getStatusCount close() 오류~:"+e.getMessage());
			}
		}
		return result;
	}
	
	public int updateStatus_m(String event_no, String reg_id) { //event 당첨 체크
		int result = 0;	
		String query = " update a02_track2_home_event_m set status='y' "+
					   " where event_no = '"+event_no+"' and reg_id = '"+reg_id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateStatus_m():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateStatus_m():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateStatus_m() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateEvent_m(String event_no, String event_m_no, String title, String content, String reg_id) { //event 응모 수정
		int result = 0;	
		String query = " update a02_track2_home_event_m set title='"+title+"', content='"+content+"', reg_id='"+reg_id+"' "+
					   " where event_no = '"+event_no+"' and event_m_no = '"+event_m_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateEvent_m():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateEvent_m():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateEvent_m() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getEvent_m_View(String even_no, String reg_id_m){ //event 상세조회
		String result = "";
		String query = " select event_m_no from a02_track2_home_event_m where event_no = '"+even_no+"' and reg_id = '"+reg_id_m+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {			
				result 	= rs.getString(1);							
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEvent_m_View():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEvent_m_View():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEvent_m_View() close:"+e.getMessage());
			}
		}
		return result;
	}
	
	public int deleteEvent_m(String event_no, String event_m_no) { //event 응모취소
		int result = 0;	
		String query = " delete from A02_TRACK2_HOME_EVENT_M where event_no = '"+event_no+"' and event_m_no = '"+event_m_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteEvent_m():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteEvent_m():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteEvent_m() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int EventStatusCount(String event_no) { //event 응모자 상태 체크
		String query = " select count(*) from a02_track2_home_event_m where event_no = '"+event_no+"' and status = 'y' ";
		int count = 0;	
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				count = rs.getInt(1);							
			}
		}catch(SQLException se) {
			System.out.println("SQLException EventStatusCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception EventStatusCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("EventStatusCount() close:"+e.getMessage());
			}
		}
		return count;		
	}
	
	public int EventMemberCount(String event_no, String reg_id) { //event 응모자 체크
		String query = " select count(*) from A02_TRACK2_HOME_EVENT_M where event_no = '"+event_no+"' and reg_id = '"+reg_id+"' ";
		int count = 0;	
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				count = rs.getInt(1);							
			}
		}catch(SQLException se) {
			System.out.println("SQLException EventMemberCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception EventMemberCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("EventMemberCount() close:"+e.getMessage());
			}
		}
		return count;		
	}	
	
	public String getMaxId(String event_no) {		
		String query = " select max(event_m_no) from A02_TRACK2_HOME_EVENT_M where event_no = '"+event_no+"' ";
		int maxId = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				maxId = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getMaxId:"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMaxId:"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMaxId:"+e.getMessage());
		}
		try{
			common.close(con,ps,rs);
		} catch(Exception e) {
			System.out.println("close() 오류~"+e.getMessage());
		}
		if(maxId == 0) maxId = 0001;  
		else maxId = maxId + 1;
		return Integer.toString(maxId);
	}
	
	public ArrayList<Event_M_DTO> getEventConfirm(String regid){ //event 
		ArrayList<Event_M_DTO> dtos = new ArrayList<Event_M_DTO>();
		String query = " select b.event_no, b.title, c.name, to_char(a.reg_date, 'yyyy-MM-dd'), to_char(b.reg_end, 'yyyy-MM-dd'), a.status "+
					   " from A02_TRACK2_HOME_EVENT_M a, A02_TRACK2_HOME_EVENT b, A02_TRACK2_HOME_MEMBER c "+
					   " where a.event_no = b.event_no and a.reg_id = c.id and a.reg_id = '"+regid+"' order by event_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String event_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String reg_id 		= rs.getString(3);
				String reg_date 	= rs.getString(4);
				String reg_end 		= rs.getString(5);
				String status 		= rs.getString(6);
				Event_M_DTO dto = new Event_M_DTO(event_no, title, reg_id, reg_date, reg_end, status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventConfirm():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventConfirm():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventConfirm() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public ArrayList<Event_M_DTO> getEventApplyList(String selValue, String txtValue){ //event_l 목록조회
		ArrayList<Event_M_DTO> dtos = new ArrayList<Event_M_DTO>();
		String query = " select a.event_no, a.event_m_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'),  "+
					   " a.status "+
					   " from A02_TRACK2_HOME_EVENT_M a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+
					   " order by a.event_no desc";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String event_no 	= rs.getString(1);
				String event_m_no 	= rs.getString(2);
				String title 		= rs.getString(3);
				String content 		= rs.getString(4);	
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				String status 		= rs.getString(7);
				Event_M_DTO dto = new Event_M_DTO(event_no, event_m_no, title, content, reg_id, reg_date, status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventApplyList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventApplyList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventApplyList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public ArrayList<Event_M_DTO> getEventApplyList_m(String even_no){ // 이벤트 목록 리스트 상세조회
		ArrayList<Event_M_DTO> dtos = new ArrayList<Event_M_DTO>();
		String query = " select a.event_no, a.event_m_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), a.status "+
					   " from A02_TRACK2_HOME_EVENT_M a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.event_no = '"+even_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String event_no 	= rs.getString(1);
				String event_m_no 	= rs.getString(2);
				String title 		= rs.getString(3);
				String content 		= rs.getString(4);				
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				String status 		= rs.getString(7);
				Event_M_DTO dto = new Event_M_DTO(event_no, event_m_no, title, content, reg_id, reg_date, status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventApplyList_m():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventApplyList_m():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventApplyList_m() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public Event_M_DTO getEventView_m(String even_no, String reg_id_m){ //event_m 상세조회
		Event_M_DTO dto = null;
		String query = " select a.event_no, a.event_m_no, a.title, a.content, b.name "+
					   " from A02_TRACK2_HOME_EVENT_M a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.event_no = '"+even_no+"' and a.reg_id = '"+reg_id_m+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String event_no 	= rs.getString(1);
				String event_m_no 	= rs.getString(2);
				String title 		= rs.getString(3);
				String content 		= rs.getString(4);				
				String reg_id 		= rs.getString(5);
				dto = new Event_M_DTO(event_no, event_m_no, title, content, reg_id, "", "");
			}
		}catch(SQLException se) {
			System.out.println("SQLException getEventView_m():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getEventView_m():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getEventView_m() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int insertEvent_M(Event_M_DTO dto) { //event 응모 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_HOME_EVENT_M(event_no, event_m_no, title, content, reg_id, reg_date) "+
					   " values('"+dto.getEvent_no()+"', '"+dto.getEvent_m_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertEvent_M():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertEvent_M():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertEvent_M() close:"+e.getMessage());
			}
		}
		return result;		
	}
}