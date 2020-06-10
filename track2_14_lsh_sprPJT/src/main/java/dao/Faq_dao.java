package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Faq_dto;

public class Faq_dao {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public int deleteNews(String no) { //삭제
		int result = 0;
		String query = " delete from track2_14_faq where no = '"+no+"' ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result 	= ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("�넫�굝利� 占쎌궎�몴占�~~"); }
		}	
		return result;
	}
	
	public int updateFaq(String no, String question, String answer, String seq, String id, String date) { //수정
		int result = 0;
		String query = " update track2_14_faq "+ 
					   " set question = '"+question+"', answer = '"+answer+"', "+ 
					   " seq = "+seq+", update_id = '"+id+"', "+ 
					   " update_date = '"+date+"' "+ 
					   " where no = '"+no+"' ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *醫낅즺error :"+e.getMessage());
			}
		}
		return result;
	}	
	
	public Faq_dto getFaqView(String no) { //상세보기
		Faq_dto dto = null;
		String query = " select a.no, a.question, a.answer, a.seq, "+ 
					   " b.name as reg_name, to_char(a.reg_date, 'yyyy-MM-dd') as reg_date, " + 
					   " c.name as update_name, to_char(a.update_date, 'yyyy-MM-dd') as update_date "+ 
					   " from track2_14_faq a, a_common_member b, "+ 
					   " a_common_member c "+ 
					   " where a.reg_id = b.id "+ 
					   " and a.update_id = c.id(+) "+ 
					   " and a.no = '"+no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String nn       	= rs.getString("no");
				String question 	= rs.getString("question");
				String answer   	= rs.getString("answer");
				String seq   		= rs.getString("seq");
				String reg_name   	= rs.getString("reg_name");
				String reg_date   	= rs.getString("reg_date");
				String update_name  = rs.getString("update_name");
				String update_date  = rs.getString("update_date");
				dto = new Faq_dto(nn, question, answer, seq, reg_name, reg_date, update_name, update_date);
			}
		}catch(SQLException se) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *醫낅즺error :"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int saveFaq(String no, String question, String answer, String seq, String id, String date) { //등록
		int result = 0;
		String query = " insert into track2_14_faq "+ 
					   " (no, question, answer, seq, reg_id, reg_date) "+ 
					   " values('"+no+"', '"+question+"','"+answer+"',"+seq+", '"+id+"', '"+date+"') ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *醫낅즺error :"+e.getMessage());
			}
		}
		return result;
	}	
	
	public String getFaqNo(){ //踰덊샇�깮�꽦
		String no="";
		String query ="select max(no) from track2_14_faq ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				no = rs.getString(1); 
			}
			if(no == null) {
				no ="F001";
			} else {
				no = no.substring(1); 
				int num = Integer.parseInt(no); 
				num = num + 1; 
				no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
				no = "F"+no; 
			}
		}catch(SQLException se) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("�넫�굝利� 占쎌궎�몴占�~~"); }
		}	
		return no;
	}
	
	public ArrayList<Faq_dto> getFaqList() { //목록
		ArrayList<Faq_dto> dtos = new ArrayList<Faq_dto>();
		String query = " select no, question, answer "+ 
					   " from track2_14_faq "+ 
					   " order by seq, no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String no       = rs.getString("no");
				String question = rs.getString("question");
				String answer   = rs.getString("answer");
				Faq_dto dto = new Faq_dto(no, question, answer);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());
		}catch(Exception e) {
			System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("*Class紐�:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *醫낅즺error :"+e.getMessage());
			}
		}
		return dtos;
	}
}