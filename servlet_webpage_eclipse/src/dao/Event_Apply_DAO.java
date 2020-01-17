package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DBConnectionOracle;
import dto.Event_Apply_DTO;

public class Event_Apply_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int EventMemberCount(String event_no, String reg_id) { //event 응모자 체크
		String query = " select count(*) from a02_track2_servlet2_event_a where event_no = '"+event_no+"' and reg_id = '"+reg_id+"' ";
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
	
	public int InsertEvent_Apply(Event_Apply_DTO dto) { //event 응모 등록
		int result = 0;	
		String query = " insert into a02_track2_servlet2_event_a(event_no, event_no_m, title, content, reg_id, reg_date) "+
					   " values('"+dto.getEvent_no()+"', '"+dto.getEvent_no_m()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException InsertEvent_Apply():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception InsertEvent_Apply():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("InsertEvent_Apply() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getMaxId(String event_no) { //이벤트 응모 아이디 체크 최대값 검사
		String query = " select max(event_no_m) from a02_track2_servlet2_event_a where event_no = '"+event_no+"' ";
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
			System.out.println("getMaxId close() 오류~"+e.getMessage());
		}
		if(maxId == 0) maxId = 0001;  
		else maxId = maxId + 1;
		return Integer.toString(maxId);
	}
}