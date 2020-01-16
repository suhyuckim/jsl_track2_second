package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Faq_DTO;
import dto.News_DTO;

public class Faq_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public String getMaxNo(){  //faq_no 최대값 검사
		String query  = " select max(faq_id) from A02_TRACK2_SERVLET2_FAQ ";
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
	
	public String getFaqNo() { //faq_no 최대값 검사해서 id 만들기
		String faqNo = getMaxNo();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(faqNo == null) {
			faqNo = nowYear+"_0001";
		} else {
			String dataYear = faqNo.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(faqNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				faqNo = dataYear +"_"+r;
			} else {
				faqNo = nowYear+"_"+"0001";
			}		
		}		
		return faqNo;
	}
	
	public int deleteFaq_servlet(String faq_id) { //faq 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_SERVLET2_FAQ where faq_id = '"+faq_id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteFaq_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteFaq_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteFaq_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateFaq_servlet(String faq_id, String question, String answer, String reg_id, String reg_date) { //faq 수정
		int result = 0;	
		String query = " update A02_TRACK2_SERVLET2_FAQ set question='"+question+"', answer='"+answer+"', reg_id='"+reg_id+"', reg_date = '"+reg_date+"' "+
					   " where faq_id = '"+faq_id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateFaq_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateFaq_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateFaq_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int insertFaq_servlet(Faq_DTO dto) { //faq 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET2_FAQ(faq_id, question, answer, reg_id, reg_date) "+
					   " values('"+dto.getFaq_id()+"', '"+dto.getQuestion()+"', '"+dto.getAnswer()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertFaq_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertFaq_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertFaq_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int getFaqCount(){ //게시물조회
		String query  = " select count(*) from A02_TRACK2_SERVLET2_FAQ ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFaqCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFaqCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFaqCount() close:"+e.getMessage());
			}
		}
		return count;
	}	
	
	public Faq_DTO getFaqView_serlvet(String faq_no){ //상세조회
		Faq_DTO dto = null;
		String query = " select a.faq_id, a.question, a.answer, b.name, to_char(a.reg_date, 'yy-MM-dd') "+
					   " from A02_TRACK2_SERVLET2_FAQ a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.faq_id = '"+faq_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String faq_id 		= rs.getString(1);
				String question 	= rs.getString(2);
				String answer 		= rs.getString(3);
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				dto = new Faq_DTO(faq_id, question, answer, reg_id, reg_date);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFaqView_serlvet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFaqView_serlvet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFaqView_serlvet() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<Faq_DTO> getFaqList_servlet(String selValue, String txtValue){ //목록조회
		ArrayList<Faq_DTO> dtos = new ArrayList<Faq_DTO>();
		String query = " select a.faq_id, a.question, a.answer, b.name, to_char(a.reg_date, 'yy-MM-dd')"+
					   " from A02_TRACK2_SERVLET2_FAQ a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+					   
					   " order by a.faq_id desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {			
				Faq_DTO dto = new Faq_DTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
										  rs.getString(5));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFaqList_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFaqList_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFaqList_servlet() close:"+e.getMessage());
			}
		}
		return dtos;
	}
}