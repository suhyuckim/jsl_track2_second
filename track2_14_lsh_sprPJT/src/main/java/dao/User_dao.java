package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Member_dto;
import dto.User_dto;

public class User_dao {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;

	/*  관리자용 */
	public User_dto getMemberView(String id) { //상세보기
		User_dto dto = null;
		String query = " select id, name, tel, email_1, email_2, info_yn, to_char(reg_date, 'yyyy-MM-dd') as reg_date, ing_yn, to_char(exit_date, 'yyyy-MM-dd') as exit_date "+
					   " from track2_14_member "+
					   " where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String t_id 	= rs.getString("id");
				String name 	= rs.getString("name");
				String tel 		= rs.getString("tel");
				String email_1 	= rs.getString("email_1");
				String email_2 	= rs.getString("email_2");
				String info_yn 	= rs.getString("info_yn");
				String reg_date = rs.getString("reg_date");
				String ing_yn 	= rs.getString("ing_yn");
				String exit_date = rs.getString("exit_date");
				dto = new User_dto(t_id, name, tel, email_1, email_2, info_yn, reg_date, ing_yn, exit_date);
			}
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<User_dto> getMemberList(String select, String search){ //맴버목록
		ArrayList<User_dto> dtos = new ArrayList<User_dto>();
		String query = " select id, name, tel, email_1, email_2, to_char(reg_date, 'yyyy-MM-dd') as reg_date, ing_yn "+
					   " from track2_14_member "+
					   " where "+select+" like '%"+search+"%' "+
					   " order by id desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id 		= rs.getString("id");
				String name 	= rs.getString("name");
				String tel 		= rs.getString("tel");
				String email_1 	= rs.getString("email_1");
				String email_2 	= rs.getString("email_2");
				String reg_date = rs.getString("reg_date");
				String ing_yn 	= rs.getString("ing_yn");
				User_dto dto = new User_dto(id, name, tel, email_1, email_2, reg_date, ing_yn);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return dtos;
	}
	/*  관리자용 */	
	
	
	public int updateUser(User_dto dto) { //회원정보 수정
		int result = 0;
		String query = " update track2_14_member "+
					   " set pw='"+dto.getPw()+"', name='"+dto.getName()+"', tel='"+dto.getTel()+"', "+
					   " email_1 ='"+dto.getEmail_1()+"', email_2 ='"+dto.getEmail_2()+"', info_yn='"+dto.getInfo_yn()+"' "+
					   " where id = '"+dto.getId()+"' ";
		try { 
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return result;
	}
	
	public int exitUser(String id) { //회원탈퇴
		int result = 0;
		String exit_date = CommonUtil.getToday();
		String query = " update track2_14_member "+
		               " set ing_yn = 'n', exit_date = '"+exit_date+"' "+
		               " where id = '"+id+"' ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return result;
	}
	
	public User_dto getUserInfo(String id) { //마이페이지 폼으로 이동
		User_dto dto = null;
		String query = " select id, pw, name, tel, email_1, email_2, info_yn, to_char(reg_date, 'yyyy-MM-dd') "+ 
					   " from track2_14_member "+ 
					   " where id='"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String idd 		= rs.getString(1);
				String pw 		= rs.getString(2);
				String name 	= rs.getString(3);
				String tel 		= rs.getString(4);
				String email_1 	= rs.getString(5);
				String email_2  = rs.getString(6);
				String info_yn  = rs.getString(7);
				String reg_date = rs.getString(8);
				dto = new User_dto(idd,pw,name,tel,email_1,email_2,info_yn,reg_date,"","");
			}
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return dto;
	}
	
	public String getLoginName(String id, String pw) { //로그인 회원이름
		String name = "";
		String query = " select name "+ 
					   " from track2_14_member "+ 
					   " where id='"+id+"' and pw='"+pw+"' "+
					   " and ing_yn = 'y' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
			}
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return name;
	}
	
	public int userSave(User_dto dto) { //회원가입
		int result = 0;
		String query = " insert into track2_14_member "+ 
					   " (id,pw,name,tel,email_1,email_2,info_yn,reg_date) "+ 
					   " values ('"+dto.getId()+"', '"+dto.getPw()+"', '"+dto.getName()+"', '"+dto.getTel()+"', '"+dto.getEmail_1()+"', '"+dto.getEmail_2()+"', '"+dto.getInfo_yn()+"', '"+dto.getReg_date()+"') ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return result;
	}
	
	public String getCheck(String id) { //ID중복검사
		String result = "";
		String query = " select count(*) from track2_14_member "+ 
					   " where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				int co = rs.getInt(1);
				if(co == 0) result = "사용가능한 아이디입니다.";
				else result = "중복된 아이디입니다.";
			}
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *종료error :"+e.getMessage());
			}
		}
		return result;
	}
}