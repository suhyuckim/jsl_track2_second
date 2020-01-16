package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Faq_DTO;

public class Faq_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public String getMaxNo(){  //faq_no 최대값 검사
		String query  = " select max(faq_no) from A02_TRACK2_HOME_FAQ ";
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
	
	public int deleteFaq(String faq_no) { //공지사항 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_HOME_FAQ where faq_no = '"+faq_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteFaq():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteFaq():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteFaq() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateFaq(String faq_no, String question, String answer, String reg_id, String reg_date) { //faq 수정
		int result = 0;	
		String query = " update A02_TRACK2_HOME_FAQ set question='"+question+"', answer='"+answer+"', reg_id='"+reg_id+"', reg_date = '"+reg_date+"' "+
					   " where faq_no = '"+faq_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateFaq():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateFaq():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateFaq() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public int insertFaq(Faq_DTO dto) { //공지사항 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_HOME_FAQ(faq_no, question, answer, reg_id, reg_date) "+
					   " values('"+dto.getFaq_no()+"', '"+dto.getQuestion()+"', '"+dto.getAnswer()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertNotice():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertNotice():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertNotice() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public Faq_DTO getFaqView(String fa_no){ //상세조회
		Faq_DTO dto = null;
		String query = " select a.faq_no, a.question, a.answer, b.name, to_char(a.reg_date, 'yy-MM-dd') "+
					   " from A02_TRACK2_HOME_FAQ a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.faq_no = '"+fa_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String faq_no 		= rs.getString(1);
				String question 	= rs.getString(2);
				String answer 		= rs.getString(3);
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				dto = new Faq_DTO(faq_no, question, answer, reg_id, reg_date);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getFaqView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getFaqView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getFaqView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<Faq_DTO> getFaqList(String selValue, String txtValue){ //faq목록조회
		ArrayList<Faq_DTO> dtos = new ArrayList<Faq_DTO>();
		String query = " select a.faq_no, a.question, a.answer, b.name, to_char(a.reg_date, 'yy-MM-dd') "+
					   " from A02_TRACK2_HOME_FAQ a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id ";
		
		if(selValue.equals("question") || selValue.equals("answer")){
			query += " and "+selValue+" like '%"+txtValue+"%' order by a.faq_no desc ";
		} else if(selValue.equals("all")) {
			query += " order by a.faq_no desc ";
		}
		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String faq_no 		= rs.getString(1);
				String question 	= rs.getString(2);
				String answer 		= rs.getString(3);	
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				Faq_DTO dto = new Faq_DTO(faq_no, question, answer, reg_id, reg_date);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
}
