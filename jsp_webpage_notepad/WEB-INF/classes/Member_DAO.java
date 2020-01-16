package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Member_DTO;

public class Member_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;	

	public int deleteMember(String id) { //ȸ��Ż��
		int result = 0;	
		String query = " update A02_TRACK2_WEB_MEMBER set status = 'n' where id = '"+id+"' ";
		try {
			con		= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteMember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteMember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteMember close() ����~:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int updateMember(String id, String name, String birth, String area, String address, String telecom, String phone_1, String phone_2, String phone_3,
						    String email_1, String email_2, String att_desk, String att_note, String att_print, String att_beam, String update_date) { //ȸ������
		int result = 0;	
		String query = " update A02_TRACK2_WEB_MEMBER set name='"+name+"',birth='"+birth+"',area='"+area+"',address='"+address+"',telecom='"+telecom+"', "+
					   " phone_1='"+phone_1+"', phone_2='"+phone_2+"', "+	
				       " phone_3='"+phone_3+"', email_1='"+email_1+"', email_2='"+email_2+"', "+
					   " att_desk='"+att_desk+"', att_note='"+att_note+"', att_print='"+att_print+"', att_beam='"+att_beam+"', update_date='"+update_date+"' "+		
					   " where id = '"+id+"' ";
		try {
			con		= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateMember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateMember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateMember close() ����~:"+e.getMessage());
			}
		}
		return result;		
	}		
	
	public Member_DTO getMemberInfo(String memberId){ //��������� ȸ������ȸ
		String query = " select id,pw,name,birth,area,address,telecom,phone_1,phone_2,phone_3,email_1,email_2, "+
					   " nvl(att_desk, 'n'), nvl(att_note, 'n'), nvl(att_print, 'n'), "+
					   " nvl(att_beam, 'n'), to_char(reg_date, 'yy-MM-dd') "+
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
				email_1, email_2, att_desk, att_note, att_print, att_beam,reg_date,"",""); //���� DTO���� �ȸ��� �ȴ�.
			}
		}catch(SQLException se){
			System.out.println("SQLException getMemberInfo():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception getMemberInfo():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("getMemberInfo close() ����~:"+e.getMessage());
			}
		}
		return dto;
	} 
	
	public int insertMember(String id, String pw, String name, String birth, String area, String address, String telecom,
							String phone_1, String phone_2, String phone_3, String email_1, String email_2, String att_desk,
							String att_note, String att_print, String att_beam, String reg_date){ //ȸ������ ���
		int result = 0;
		String query = " insert into a02_track2_web_member(id,pw,name,birth,area,address,telecom, "+
				       " phone_1,phone_2,phone_3,email_1,email_2,att_desk,att_note,att_print,att_beam, "+
					   " reg_date) "+
					   " values('"+id+"','"+pw+"','"+name+"','"+birth+"','"+area+"','"+address+"','"+telecom+"', "+
					   " '"+phone_1+"','"+phone_2+"','"+phone_3+"','"+email_1+"','"+email_2+"','"+att_desk+"', "+
					   " '"+att_note+"','"+att_print+"','"+att_beam+"','"+reg_date+"') ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertMember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertMember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("close() ����~:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getCheckCount(String id){ //ȸ������ id �ߺ� �˻�
		String query = " select count(*) from a02_track2_web_member "+
					   " where id='"+id+"' ";
		String result = "";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch(SQLException se){
			System.out.println("SQLException getCheckCount():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception getCheckCount():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("close() ����~:"+e.getMessage());
			}
		}
		return result;
	}
	
	public String checkLogin(String id, String pw){ //xxx�� ȯ���մϴ�. (��ȸ)
		String query = " select name from a02_track2_web_member "+
				       " where id = '"+id+"' "+
				       " and pw = '"+pw+"' "+
				       " and status = 'y' ";
		String result = null;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		}catch(SQLException se){
			System.out.println("SQLException checkLogin():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception checkLogin():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("close() ����~:"+e.getMessage());
			}
		}
		return result;
	}
}