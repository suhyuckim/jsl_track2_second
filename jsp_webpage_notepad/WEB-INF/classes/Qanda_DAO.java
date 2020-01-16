package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.Qanda_DTO;

public class Qanda_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;	
	
	public int qandaHit(String qanda_no){ //조회수 증가
		int result = 0;
		String query = " update track2_1조_web_qanda set hit = hit+1 where qanda_no ='"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException qandaHit():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception qandaHit():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("qandaHit() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateAnswer(String qanda_no, String answer, String reg_date_a, String status) { //답변 등록
		int result = 0;	
		String query = " update track2_1조_web_qanda "+
					   " set answer='"+answer+"', reg_date_a = '"+reg_date_a+"', "+
					   " status = '"+status+"' "+
					   " where qanda_no = '"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateAnswer():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateAnswer():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateAnswer() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateAnswer_2(String qanda_no, String answer, String user_id, String reg_date_a) { //답변 수정
		int result = 0;	
		String query = " update track2_1조_web_qanda "+
					   " set answer='"+answer+"', user_id='"+user_id+"', reg_date_q = '"+reg_date_a+"' "+
					   " where qanda_no = '"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateAnswer_2():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateAnswer_2():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateAnswer_2() close:"+e.getMessage());
			}
		}
		return result;		
	}

	public int updateQanda(String qanda_no, String title, String question, String user_id, String reg_date_q) { //qanda 수정
		int result = 0;	
		String query = " update track2_1조_web_qanda "+
					   " set title='"+title+"', question='"+question+"', user_id='"+user_id+"', reg_date_q = '"+reg_date_q+"' "+
					   " where qanda_no = '"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateQanda():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateQanda():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateQanda() close:"+e.getMessage());
			}
		}
		return result;		
	}

	public int deleteQanda_2(String qanda_no) { //답변 삭제
		int result = 0;	
		String query = " update track2_1조_web_qanda "+
					   " set answer='', reg_date_a = '', status='n' "+
					   " where qanda_no = '"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteQanda_2():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteQanda_2():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteQanda_2() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int deleteQanda(String qanda_no) { //qanda 삭제
		int result = 0;	
		String query = " delete from track2_1조_web_qanda where qanda_no = '"+qanda_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteQanda():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteQanda():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteQanda() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertQanda(Qanda_DTO dto) { //qanda 등록
		int result = 0;	
		String query = " insert into track2_1조_web_qanda(qanda_no, title, question, user_id, reg_date_q, secret) "+
					   " values('"+dto.getQanda_no()+"', '"+dto.getTitle()+"', '"+dto.getQuestion()+"', "+
					   " '"+dto.getUser_id()+"', '"+dto.getReg_date_q()+"', '"+dto.getSecret()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertQanda():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertQanda():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertQanda() close:"+e.getMessage());
			}
		}
		return result;		
	}

	public Qanda_DTO getQandaView(String qa_no){ //qanda 상세조회
		Qanda_DTO dto = null;
		String query = " select a.qanda_no, a.title, a.question, decode(a.answer, null, ' ',a.answer), a.user_id, to_char(a.reg_date_q, 'yy-MM-dd'), "+
					   " decode(a.reg_date_a, null, '-', to_char(a.reg_date_a, 'yy-MM-dd')), a.status, a.secret, a.hit, b.name "+
					   " from track2_1조_web_qanda a, A02_TRACK2_WEB_MEMBER b "+
					   " where a.user_id = b.id "+
					   " and a.qanda_no = '"+qa_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String qanda_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String question 	= rs.getString(3);
				String answer  		= rs.getString(4);
				String user_id 		= rs.getString(5);
				String reg_date_q 	= rs.getString(6);
				String reg_date_a 	= rs.getString(7);
				String status 		= rs.getString(8);
				String secret 		= rs.getString(9);				
				int    hit 			= rs.getInt(10);
				String user_name 	= rs.getString(11);	
				dto = new Qanda_DTO(qanda_no, title, question, answer, user_id, reg_date_q, reg_date_a, status, secret, hit, user_name);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getQandaView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getQandaView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getQandaView() close:"+e.getMessage());
			}
		}
		return dto;
	}	
	
	public ArrayList<Qanda_DTO> getQandaList(String selValue, String txtValue){ //목록조회
		ArrayList<Qanda_DTO> dtos = new ArrayList<Qanda_DTO>();
		String query = " select a.QANDA_NO, a.TITLE, a.user_id, TO_CHAR(a.REG_DATE_Q, 'yy-MM-dd'), decode(a.STATUS, 'n', '답변대기', '답변완료'), "+
					   " decode(a.SECRET, 'n', '[공개]', '[비공개]'), a.HIT, b.name "+
					   " from track2_1조_web_qanda a, A02_TRACK2_WEB_MEMBER b "+
					   " where a.user_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+
					   " order by a.qanda_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String qanda_no 	= rs.getString(1);
				String title 		= rs.getString(2);				
				String user_id		= rs.getString(3);
				String reg_date_q 	= rs.getString(4);
				String status 	    = rs.getString(5);				
				String secret 	    = rs.getString(6);				
				int    hit 			= rs.getInt(7);
				String user_name    = rs.getString(8);
				Qanda_DTO dto = new Qanda_DTO(qanda_no, title, "", "", user_id, reg_date_q,"",status,secret,hit,user_name);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getQandaList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getQandaList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getQandaList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public String getMaxNo(){  //qanda_no 최대값 검사
		String query  = " select max(qanda_no) from track2_1조_web_qanda ";
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
	
	public String getQandaNo() { //qanda_no 최대값 검사해서 id 만들기
		String qandaNo = getMaxNo();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(qandaNo == null) {
			qandaNo = nowYear+"_0001";
		} else {
			String dataYear = qandaNo.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(qandaNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				qandaNo = dataYear +"_"+r;
			} else {
				qandaNo = nowYear+"_"+"0001";
			}		
		}		
		return qandaNo;
	}
}