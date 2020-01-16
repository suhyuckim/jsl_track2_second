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
	
	public String getCheckCount(String id){ //회원가입 id 중복 검사
		String query = " select count(*) from a02_track2_home_member "+
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
				System.out.println("close() 오류~:"+e.getMessage());
			}
		}
		return result;
	}
	
	public String checkLogin(String id, String pw){ //xxx님 환영합니다. (조회)
		String query = " select name from a02_track2_home_member "+
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
				System.out.println("close() 오류~:"+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertMember(String id, String pw, String name, String phone, String email_1, String email_2, String informaiton,
							String reg_date){ //회원가입 등록
		int result = 0;
		String query = " insert into a02_track2_home_member(id, pw, name, phone, email_1, email_2, informaiton, reg_date) "+
				       " values('"+id+"','"+pw+"','"+name+"','"+phone+"','"+email_1+"','"+email_2+"','"+informaiton+"','"+reg_date+"') "; 
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
				System.out.println("close() 오류~:"+e.getMessage());
				}
			}
			return result;		
		}
	}