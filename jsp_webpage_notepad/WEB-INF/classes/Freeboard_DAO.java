package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Freeboard_DTO;

public class Freeboard_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;	
	
	public ArrayList<Freeboard_DTO> getFreeboardIndex(){ //index freeboard list
		ArrayList<Freeboard_DTO> dtos = new ArrayList<Freeboard_DTO>();
		String query = " select freeboard_no, substr(title,1,10), substr(content,1,5), to_char(reg_date, 'yyyy-mm-dd') "+
					   " from (select freeboard_no, title, content, reg_date from A02_TRACK2_WEB_FREEBOARD "+
					   " order by reg_date desc) where rownum <= 5 ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {		
				String freeboard_no   = rs.getString(1);
				String title   		  = rs.getString(2);
                String content  	  = rs.getString(3);
                String reg_date 	  = rs.getString(4);                
                Freeboard_DTO dto  = new Freeboard_DTO(freeboard_no, title, content, reg_date);
                dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFreeboardIndex():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFreeboardIndex():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFreeboardIndex() close:"+e.getMessage());
			}
		}
		return dtos;
	}	
	
	public int deleteFreeboard(String freeboard_no) { //자유게시판 삭제
		int result = 0;	
		String query = " delete from a02_track2_web_freeboard where freeboard_no = '"+freeboard_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteFreeboard():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteFreeboard():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteFreeboard() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateFreeboard(String freeboard_no, String title, String content, String reg_id, String reg_date) { //자유게시판 수정
		int result = 0;	
		String query = " update a02_track2_web_freeboard set title='"+title+"', content='"+content+"', reg_id='"+reg_id+"', reg_date = '"+reg_date+"' "+
					   " where freeboard_no = '"+freeboard_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateFreeboard():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateFreeboard():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateFreeboard() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertFreeboard(Freeboard_DTO dto) { //자유게시판 등록
		int result = 0;	
		String query = " insert into a02_track2_web_freeboard(freeboard_no, title, content, reg_id, reg_date, hit, password, status) "+
					   " values('"+dto.getFreeboard_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', "+dto.getHit()+", '"+dto.getPassword()+"', '"+dto.getStatus()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertFreeboard():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertFreeboard():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertFreeboard() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int freeboardHit(String free_no){ //조회수 증가
		int result = 0;
		String query = " update a02_track2_web_freeboard set hit = hit+1 where freeboard_no ='"+free_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException freeboardHit():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception freeboardHit():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("freeboardHit() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public Freeboard_DTO getFreeboardView(String free_no){ //상세조회
		Freeboard_DTO dto = null;
		String query = " select freeboard_no, title, content, reg_id, to_char(reg_date, 'yy-MM-dd'), hit, password, status "+
					   " from a02_track2_web_freeboard "+
					   " where freeboard_no = '"+free_no+"' ";					   
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String freeboard_no = rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);				
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				int    hit 			= rs.getInt(6);	
				String password 	= rs.getString(7);				
				String status 		= rs.getString(8);				
				dto = new Freeboard_DTO(freeboard_no, title, content, reg_id, reg_date, hit, password, status);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFreeboardView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFreeboardView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFreeboardView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<Freeboard_DTO> getFreeboardList(String selValue, String txtValue){ //목록조회
		ArrayList<Freeboard_DTO> dtos = new ArrayList<Freeboard_DTO>();
		String query = " select freeboard_no, title, content, reg_id, to_char(reg_date, 'yy-MM-dd'), hit, status "+
					   " from a02_track2_web_freeboard "+
					   " where "+selValue+" like '%"+txtValue+"%' "+
					   " order by freeboard_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String freeboard_no = rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);				
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				int    hit 			= rs.getInt(6);
				String status 		= rs.getString(7);				
				Freeboard_DTO dto   = new Freeboard_DTO(freeboard_no, title, content, reg_id, reg_date, hit, "", status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFreeboardList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFreeboardList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFreeboardList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public String getMaxNo(){  //freeboard_no 최대값 검사
		String query  = " select max(freeboard_no) from a02_track2_web_freeboard ";
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
	
	public String getFreeboardNo() { //freeboard_no 최대값 검사해서 id 만들기
		String freeboardNo = getMaxNo();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(freeboardNo == null) {
			freeboardNo = nowYear+"_0001";
		} else {
			String dataYear = freeboardNo.substring(0,2); //"19"
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(freeboardNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				freeboardNo = dataYear +"_"+r;
			} else {
				freeboardNo = nowYear+"_"+"0001";
			}		
		}		
		return freeboardNo;
	}
}