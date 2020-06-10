package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.Member_dto;

public class Member_dao {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public int deleteMember(String id) { //삭제
		int result = 0;
		String query = " delete from track2_14_spr_member "+
					   " where id = '"+id+"' ";
		try {
			con    = common.getConnection();
			ps 	   = con.prepareStatement(query);
			result = ps.executeUpdate();
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
	
	public int updateMember(Member_dto dto) { //수정 저장
		int result = 0;
		String query = " update track2_14_spr_member "+ 
					   " set name='"+dto.getName()+"', area='"+dto.getArea()+"', age="+dto.getAge()+" "+ 
					   " where id = '"+dto.getId()+"' ";
		try {
			con    = common.getConnection();
			ps 	   = con.prepareStatement(query);
			result = ps.executeUpdate();
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
	
	public Member_dto getMemberView(String id) { //상세보기
		Member_dto dto = null;
		String query = " select id, name, area, age "+
					   " from track2_14_spr_member "+
					   " where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String t_id = rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				int    age 	= rs.getInt("age");
				dto = new Member_dto(t_id, name, area, age);
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
	
	public String getCheck(String id) { //ID중복검사
		String result = "";
		String query = " select count(*) from track2_14_spr_member "+ 
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
	
	public int saveMember(Member_dto dto) { //맴버 등록
		int result = 0;
		String query = " insert into track2_14_spr_member(id,name,area,age) "+ 
					   " values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getArea()+"',"+dto.getAge()+") ";
		try {
			con    = common.getConnection();
			ps 	   = con.prepareStatement(query);
			result = ps.executeUpdate();
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
	

	public ArrayList<Member_dto> getMemberList(String select, String search){ //맴버목록
		ArrayList<Member_dto> dtos = new ArrayList<Member_dto>();
		String query = " select id, name, area, age "+
					   " from track2_14_spr_member "+
					   " where "+select+" like '%"+search+"%' "+
					   " order by id desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String id 	= rs.getString("id");
				String name = rs.getString("name");
				String area = rs.getString("area");
				int    age 	= rs.getInt("age");
				Member_dto dto = new Member_dto(id, name, area, age);
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
}