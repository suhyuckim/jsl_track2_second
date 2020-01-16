package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.Smember_DTO;

public class Smember_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int getMemberDelete(String id) { // 삭제
		int result = 0;	
		String query = " delete from a02_track2_servlet where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException getMemberDelete():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberDelete():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberDelete() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int getMemberUpdate(Smember_DTO dto) { //수정
		int result = 0;	
		String	query = " update a02_track2_servlet set name ='"+dto.getName()+"', area ='"+dto.getArea()+"', age="+dto.getAge()+" "+
						" where id='"+dto.getId()+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException getMemberUpdate():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberUpdate():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberUpdate() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public Smember_DTO getMemberView(String id){ //상세조회
		Smember_DTO dto = null;
		String query = " select id, name, area, age from a02_track2_servlet "+
					   " where id = '"+id+"' order by id ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				dto = new Smember_DTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMemberView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<Smember_DTO> getSmemberList(){ //목록조회
		ArrayList<Smember_DTO> dtos = new ArrayList<Smember_DTO>();
		String query = " select id, name, area, age from a02_track2_servlet order by id ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				Smember_DTO dto = new Smember_DTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getSmemberList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getSmemberList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getSmemberList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public int insertSmember(Smember_DTO dto) {
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET(id, name, area, age) "+
					   " values('"+dto.getId()+"', '"+dto.getName()+"', '"+dto.getArea()+"', "+
					   " '"+dto.getAge()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertSmember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertSmember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertSmember() close:"+e.getMessage());
			}
		}
		return result;		
	}	
}