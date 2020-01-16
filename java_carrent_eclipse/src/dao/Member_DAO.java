package dao;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class Member_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public int getMember_Count_delete(String no) {
		String query = " select count(*) from A02_TRACK2_CARRENT where member_id = '"+no+"' ";
		int count = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {				
				count = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getMember_Count_delete():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMember_Count_delete():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMember_Count_delete():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("getMember_Count_delete close() 오류~"+e.getMessage());
			}		
		}
		return count;
	}

	
	public int deleteMember(String id) { //삭제
		String query = " delete from A02_TRACK2_MEMBER where id = '"+id+"' ";
		int result = 0;
		try {
			con 	= common.getConnection();
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
				System.out.println("deleteMember() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public int updateMember(String id, String name, int age, String dept_no, 
			 				String rank_no, String address, String reg_date) { //수정
		String query = " update A02_TRACK2_MEMBER "+
					   " set name = '"+name+"', age = "+age+", dept_no = '"+dept_no+"', rank_no = '"+rank_no+"', "+
					   " address = '"+address+"', reg_date = '"+reg_date+"' "+
					   " where id = '"+id+"' ";
		int result = 0;
		try {
			con 	= common.getConnection();
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
				System.out.println("updateMember() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public Member_DTO getMemberInfo(String id){ // 수정할때 아이디 조회
		Member_DTO dto = null;
		String query = " select a.id, a.name, a.age, b.dept_name, c.rank_name, nvl(a.address, '-') address, "+
					   " to_char(a.reg_date, 'yyyy-mm-dd') reg_date "+
					   " from A02_TRACK2_MEMBER a, A02_TRACK2_DEPT_DESC b, A02_TRACK2_RANK_DESC c "+
					   " where a.dept_no = b.dept_no and a.rank_no = c.rank_no "+
					   " and a.id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMemberInfo():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberInfo():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberInfo() close"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int insertMember_2(String id, String name, int age, String dept_no, 
							 String rank_no, String address, String reg_date) { //멤버등록3
		String query = " insert into A02_TRACK2_MEMBER (id, name, age, dept_no, rank_no, address, reg_date) "+
			           " values (?,?,?,?,?,?,?) ";
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, dept_no);
			ps.setString(5, rank_no);
			ps.setString(6, address);
			ps.setString(7, reg_date);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException insertMember_2():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertMember_2():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertMember_2() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertMember_DTO(Member_DTO dto) { //멤버등록2
		String query = " insert into A02_TRACK2_MEMBER "+
			 	   	   " (id, name, age, dept_no, rank_no, address, reg_date) "+
				       " values ('"+dto.getId()+"','"+dto.getName()+"', "+dto.getAge()+",'"+dto.getDept()+"',"+
				       " '"+dto.getRank()+"','"+dto.getAddress()+"','"+dto.getReg_date()+"') ";
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException insertMember_DTO():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertMember_DTO():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertMember_DTO() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertMember(String id, String name, int age, String dept_no, 
							String rank_no, String address, String reg_date) { //멤버등록1
		String query = " insert into A02_TRACK2_MEMBER "+
				 	   " (id, name, age, dept_no, rank_no, address, reg_date) "+
					   " values ('"+id+"','"+name+"', "+age+",'"+dept_no+"',"+
					   " '"+rank_no+"','"+address+"','"+reg_date+"') ";
		int result = 0;
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
				System.out.println("insertMember() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public ArrayList<Member_DTO> getMemberList(String gubun, String search){ //멤버 조회
		ArrayList<Member_DTO> dtos = new ArrayList<Member_DTO>();
		String query = "";
		query = " select a.id, a.name, a.age, b.dept_name, c.rank_name, nvl(a.address, '-') address, "+
				" to_char(a.reg_date, 'yyyy-mm-dd') reg_date "+
			    " from A02_TRACK2_MEMBER a, A02_TRACK2_DEPT_DESC b, A02_TRACK2_RANK_DESC c "+
			    " where a.dept_no = b.dept_no and a.rank_no = c.rank_no ";
		if(gubun.equals("name")) {
			query = query + " and a.name like '%"+search+"%' order by a.id ";
		} else if(gubun.equals("dept")) {
			query = query + " and a.dept_no = '"+search+"' order by a.id ";
		} else if(gubun.equals("member_id")) {
			query = query + " and a.id = '"+search+"' order by a.id ";
		} else if(gubun.equals("all")) {
			query = query + " order by a.id ";
		} 
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				Member_DTO dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMemberList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMemberList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		return dtos;
	}

	public String getMaxId(){ //id자동생성
		String query = " select max(id) from A02_TRACK2_MEMBER ";
		int maxid = 0;
		
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				maxid = rs.getInt(1);
			}
		}catch(RemoteException re){
			System.out.println("RemoteException getMaxId: "+re.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException getMaxId: "+se.getMessage());
		}catch(Exception e){
			System.out.println("Exception getMaxId: "+e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.println("getMaxId() close : "+e.getMessage());
			}
		}
		if(maxid == 0)  maxid = 101;
		else maxid += 1;
		return Integer.toString(maxid);
	}
}