package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Member_DTO;

public class Manager_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;		
	
	public int getMemberCount(){ 
		String query  = " select count(*) from A02_TRACK2_WEB_MEMBER ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMemberCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberCount() close:"+e.getMessage());
			}
		}
		return count;
	}	
	
	public Member_DTO getMemberInfo(String memberId){ //멤버수정전 회원상세조회
		String query = " select id,pw,name,birth,area,address,telecom,phone_1,phone_2,phone_3,email_1,email_2, "+
					   " nvl(att_desk, 'n'), nvl(att_note, 'n'), nvl(att_print, 'n'), "+
					   " nvl(att_beam, 'n'), to_char(reg_date, 'yyyy-MM-dd') "+
					   " from A02_TRACK2_WEB_MEMBER where id='"+memberId+"' ";
		Member_DTO dto = null;					   
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String id 			= rs.getString(1);
				String pw 			= rs.getString(2);
				String name 		= rs.getString(3);
				String birth 		= rs.getString(4);
				String area 		= rs.getString(5);
				String address 		= rs.getString(6);
				String telecom 		= rs.getString(7);
				String phone_1 		= rs.getString(8);
				String phone_2 		= rs.getString(9);
				String phone_3 		= rs.getString(10);
				String email_1 		= rs.getString(11);
				String email_2 		= rs.getString(12);
				String att_desk 	= rs.getString(13);
				String att_note 	= rs.getString(14);
				String att_print 	= rs.getString(15);
				String att_beam 	= rs.getString(16);			
				String reg_date 	= rs.getString(17);			
				dto = new Member_DTO(id, pw, name, birth, area, address, telecom, phone_1, phone_2, phone_3, 
				email_1, email_2, att_desk, att_note, att_print, att_beam,reg_date,"",""); 
			}
		}catch(SQLException se){
			System.out.println("SQLException getMemberInfo():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception getMemberInfo():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("getMemberInfo close() 오류~:"+e.getMessage());
			}
		}
		return dto;
	} 
	
	public ArrayList<Member_DTO> getListMember(String selValue, String areaValue, String txtValue, String checkValue){ //목록조회
		ArrayList<Member_DTO> dtos = new ArrayList<Member_DTO>();
		String query ="";
		query = " select id, name, phone_1, phone_2, phone_3, email_1, email_2, area, to_char(reg_date, 'yyyy-MM-dd') reg_date, decode(status, 'n', '탈퇴', '-') "+
					   " from A02_TRACK2_WEB_MEMBER ";
		
		if(selValue.equals("id") || selValue.equals("name")){
			query += " where "+selValue+" like '%"+txtValue+"%' ";
			if(!areaValue.equals("all")){
				query += "and area like '%"+areaValue+"%' order by "+checkValue+" ";
			}
		} else {
			query += " where area like '%"+areaValue+"%' "+
					 " order by "+checkValue+" ";
		}		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id		= rs.getString(1);
				String name 	= rs.getString(2);
				String phone_1 	= rs.getString(3);
				String phone_2  = rs.getString(4);
				String phone_3 	= rs.getString(5);
				String email_1 	= rs.getString(6);				
				String email_2 	= rs.getString(7);
				String area 	= rs.getString(8);
				String reg_date = rs.getString(9);
				String status 	= rs.getString(10);
				Member_DTO dto = new Member_DTO(id, name, phone_1, phone_2, phone_3, email_1, email_2, area, reg_date, status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getListMember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getListMember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getListMember() close:"+e.getMessage());
			}
		}
		return dtos;
	}	
}