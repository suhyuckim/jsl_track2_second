package dao;
import java.rmi.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Member_DTO;

public class Member_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int getIdPwCheck(String id, String pw){ //멤버 로그인 체크 AJAX
		int result = 0;
		String s_pw = "";
		try {
			s_pw = CommonUtil.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String query = " select count(*) "+
					   " from A02_TRACK2_SERVLET2_MEMBER "+
					   " where id = '"+id+"' "+
					   " and pw = '"+s_pw+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				result 	= rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getIdPwCheck():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getIdPwCheck():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getIdPwCheck() close:"+e.getMessage());
			}
		}
		return result;
	}
	
	public int getMemberCount_servlet(){ 
		String query  = " select count(*) from a02_track2_servlet2_member ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMemberCount_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberCount_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberCount_servlet() close:"+e.getMessage());
			}
		}
		return count;
	}	
	
	public ArrayList<Member_DTO> getListMember_servlet(String selValue, String areaValue, 
													   String txtValue, String checkValue){ //맴버조회
		ArrayList<Member_DTO> dtos = new ArrayList<Member_DTO>();
		String query ="";
		query = " select id, name, phone_1, phone_2, phone_3, email_1, email_2, area, to_char(reg_date, 'yyyy-MM-dd') reg_date, "+
				" decode(status, 'n', '탈퇴', '-') "+
				" from a02_track2_servlet2_member ";
		
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
				Member_DTO dto = new Member_DTO(id, name, area, phone_1, phone_2, phone_3, email_1, email_2, reg_date, status);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getListMember_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getListMember_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getListMember_servlet() close:"+e.getMessage());
			}
		}
		return dtos;
	}	
	
	public int deleteMember_servlet(String id) { //회원탈퇴
		int result = 0;	
		String query = " update a02_track2_servlet2_member set status = 'n' where id = '"+id+"' ";
		System.out.println(query);
		try {
			con		= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteMember_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteMember_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteMember_servlet close() 오류~:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int updateMember_servlet(String id, String name, String birth, String area, String address, String telecom,
									String phone_1, String phone_2, String phone_3, String email_1, String email_2,
									String update_date, String sex) { // 회원수정
		int result = 0;
		String query = " update a02_track2_servlet2_member set name='"+name+"', birth='"+birth+"', area='"+area+"', "+
					   " address='"+address+"', telecom='"+telecom+"', phone_1='"+phone_1+"', phone_2='"+phone_2+"', "+
					   " phone_3='"+phone_3+"', email_1='"+email_1+"', email_2='"+email_2+"', "+
					   " update_date = '"+update_date+"', sex='"+sex+"' "+
					   " where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("SQLException updateMember_servlet():" + se.getMessage());
		} catch (Exception e) {
			System.out.println("Exception updateMember_servlet():" + e.getMessage());
		} finally {
			try {
				common.close(con, ps, rs);
			} catch (Exception e) {
				System.out.println("updateMember_servlet close() 오류~:" + e.getMessage());
			}
		}
		return result;
	}
	
	public Member_DTO getMemberInfo_servlet(String memberId){ //멤버수정전 회원상세조회
		String query = " select id,pw,name,to_char(birth, 'yyyy-MM-dd'),area,address,telecom,"+
					   " phone_1,phone_2,phone_3,email_1,email_2, "+
					   " to_char(reg_date, 'yyyy-MM-dd'),sex "+
					   " from a02_track2_servlet2_member where id='"+memberId+"' ";
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
				String reg_date 	= rs.getString(13);	
				String sex 			= rs.getString(14);	
				dto = new Member_DTO(id, pw, name, birth, area, address, telecom, phone_1, phone_2, phone_3, 
				email_1, email_2, reg_date, "", sex, ""); //따로 DTO문을 안만들어도 된다.
			}
		}catch(SQLException se){
			System.out.println("SQLException getMemberInfo_servlet():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception getMemberInfo_servlet():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("getMemberInfo_servlet close() 오류~:"+e.getMessage());
			}
		}
		return dto;
	} 
	
	public String checkLogin_servlet(String id, String pw){ //xxx님 환영합니다. (조회)
		String query = " select name from a02_track2_servlet2_member "+
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
			System.out.println("SQLException checkLogin_servlet():"+se.getMessage());
		}catch(Exception s){
			System.out.println("Exception checkLogin_servlet():"+s.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("checkLogin_servlet close() 오류~:"+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertMember_servlet(String id, String pw, String name, String birth, String area, String address, 
									String telecom, String phone_1, String phone_2, String phone_3,
									String email_1, String email_2, String reg_date, String sex){ //회원가입 등록
		int result = 0;
		String query = " insert into a02_track2_servlet2_member(id, pw, name, birth, area, address, telecom, "+ 
					   " phone_1, phone_2, phone_3, email_1, email_2, reg_date, sex) "+ 
					   " values('"+id+"','"+pw+"','"+name+"','"+birth+"','"+area+"','"+address+"', "+
					   " '"+telecom+"','"+phone_1+"', '"+phone_2+"', '"+phone_3+"', '"+email_1+"', '"+email_2+"', '"+reg_date+"', '"+sex+"') ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertMember_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertMember_servlet():"+e.getMessage());
		} finally {
		try {
			common.close(con,ps,rs);
		}catch(Exception e) {
			System.out.println("insertMember_servlet close() 오류~:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public String getCheckCount(String id){ //회원가입 id 중복 검사
		String query = " select count(*) from a02_track2_servlet2_member "+
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
				System.out.println("getCheckCount close() 오류~:"+e.getMessage());
			}
		}
		return result;
	}
}
