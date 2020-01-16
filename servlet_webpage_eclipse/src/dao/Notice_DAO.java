package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Notice_DTO;

public class Notice_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;	
	
	public int getNoticeCount(){ //게시물조회
		String query  = " select count(*) from A02_TRACK2_SERVLET2_NOTICE ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNoticeCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNoticeCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNoticeCount() close:"+e.getMessage());
			}
		}
		return count;
	}	
	
	public String getMaxNo(){  //notice_no 최대값 검사
		String query  = " select max(notice_no) from A02_TRACK2_SERVLET2_NOTICE ";
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
	
	public String getNoticeNo() { //notice_no 최대값 검사해서 id 만들기
		String noticeNo = getMaxNo();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(noticeNo == null) {
			noticeNo = nowYear+"_0001";
		} else {
			String dataYear = noticeNo.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(noticeNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				noticeNo = dataYear +"_"+r;
			} else {
				noticeNo = nowYear+"_"+"0001";
			}		
		}		
		return noticeNo;
	}
	
	public Notice_DTO getNoticeView_serlvet(String noti_no){ //상세조회
		Notice_DTO dto = null;
		String query = " select a.notice_no, a.title, a.content, a.file_name_1, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit "+
					   " from A02_TRACK2_SERVLET2_NOTICE a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.notice_no = '"+noti_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String notice_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);
				String file_name_1  = rs.getString(4);
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				int    hit 			= rs.getInt(7);
				dto = new Notice_DTO(notice_no, title, content, file_name_1, reg_id, reg_date, hit);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNoticeView_serlvet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNoticeView_serlvet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNoticeView_serlvet() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int noticeHit_servlet(String noti_no){ //조회수 증가
		int result = 0;
		String query = " update A02_TRACK2_SERVLET2_NOTICE set hit = hit+1 where notice_no ='"+noti_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException noticeHit_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception noticeHit_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("noticeHit_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateNotice_servlet(String notice_no, String title, String content, String file_name_1, String reg_id, String reg_date) { //공지사항 수정
		int result = 0;	
		String query = ""; 
		if(file_name_1 == null) {
			query = " update A02_TRACK2_SERVLET2_NOTICE set title ='"+title+"', content ='"+content+"', "+
					" reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = null "+
					" where notice_no = '"+notice_no+"' ";
		} else {
			query = " update A02_TRACK2_SERVLET2_NOTICE set title ='"+title+"', content ='"+content+"', "+
				    " reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = '"+file_name_1+"' "+
				    " where notice_no = '"+notice_no+"' ";
		}
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateNotice_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateNotice_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateNotice_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int deleteNotice_servlet(String notice_no) { //공지사항 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_SERVLET2_NOTICE where notice_no = '"+notice_no+"' ";
		System.out.println(query);
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteNotice_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteNotice_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteNotice_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertNotice_servlet(Notice_DTO dto) { //공지사항 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET2_NOTICE(notice_no, title, content, file_name_1, reg_id, reg_date, hit) "+
					   " values('"+dto.getNotice_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getFile_name_1()+"', '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', "+dto.getHit()+") ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertNotice_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertNotice_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertNotice_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public ArrayList<Notice_DTO> getNoticeList_servlet(String selValue, String txtValue){ //목록조회
		ArrayList<Notice_DTO> dtos = new ArrayList<Notice_DTO>();
		String query = " select a.notice_no, a.title, a.content, a.file_name_1, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit "+
					   " from A02_TRACK2_SERVLET2_NOTICE a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+					   
					   " order by a.notice_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {			
				Notice_DTO dto = new Notice_DTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
												rs.getString(5), rs.getString(6), rs.getInt(7));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNoticeList_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNoticeList_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNoticeList_servlet() close:"+e.getMessage());
			}
		}
		return dtos;
	}
}
