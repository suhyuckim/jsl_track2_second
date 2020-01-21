package dao;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Notice_DTO;

public class Notice_DAO {
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
	
	public String getLikeCount(String no){ //좋아요 카운트 조회
		String likeCount = "";
		String query = " select likecount "+
					   " from a02_exam_임수혁 "+
					   " where notice_no = '"+no+"'";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				likeCount 	= rs.getString(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getLikeCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getLikeCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getLikeCount() close:"+e.getMessage());
			}
		}
		return likeCount;
	}
	
	public int likeCountUpdate(String no){ //좋아요 카운트 증가
		String query = " update a02_exam_임수혁  set likecount = likecount+1 where notice_no = '"+no+"' ";
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException likeCountUpdate():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception likeCountUpdate():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("likeCountUpdate() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int noticeHit(String noti_no){ //조회수 증가
		int result = 0;
		String query = " update a02_exam_임수혁 set hit = hit+1 where notice_no ='"+noti_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException noticeHit():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception noticeHit():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("noticeHit() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int updateNotice(String notice_no, String title, String content, String file_name, String reg_id, String reg_date) { //공지사항 수정
		int result = 0;	
		String query = ""; 
		if(file_name == null) {
			query = " update a02_exam_임수혁 set title ='"+title+"', content ='"+content+"', "+
					" reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name = null "+
					" where notice_no = '"+notice_no+"' ";
		}else {
			query = " update a02_exam_임수혁 set title ='"+title+"', content ='"+content+"', "+
				    " reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name = '"+file_name+"' "+
				    " where notice_no = '"+notice_no+"' ";
		}
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateNotice():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateNotice():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateNotice() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int deleteNotice(String notice_no) { //공지사항 삭제
		int result = 0;	
		String query = " delete from a02_exam_임수혁 where notice_no = '"+notice_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteNotice():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteNotice():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteNotice() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public Notice_DTO getNoticeView(String noti_no){ //상세조회
		Notice_DTO dto = null;
		String query = " select notice_no, title, content, file_name, reg_id, to_char(reg_date, 'yy-MM-dd'), hit, likecount "+
					   " from a02_exam_임수혁 "+
					   " where notice_no = '"+noti_no+"'";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String notice_no 	= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);
				String file_name  	= rs.getString(4);
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				int    hit 			= rs.getInt(7);
				int    likecount 	= rs.getInt(8);
				dto = new Notice_DTO(notice_no, title, content, file_name, reg_id, reg_date, hit, likecount);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNoticeView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNoticeView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNoticeView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<Notice_DTO> getNoticeList_servlet(String selValue, String txtValue){ //목록조회
		ArrayList<Notice_DTO> dtos = new ArrayList<Notice_DTO>();
		String query = " select notice_no, title, content, file_name, reg_id, to_char(reg_date, 'yy-MM-dd'), hit "+
					   " from a02_exam_임수혁 "+
					   " where "+selValue+" like '%"+txtValue+"%' "+					   
					   " order by notice_no desc ";
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
			System.out.println("SQLException getNoticeList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNoticeList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNoticeList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public int insertNotice(Notice_DTO dto) { //공지사항 등록
		int result = 0;	
		String query = " insert into a02_exam_임수혁(notice_no, title, content, file_name, reg_id, reg_date, hit) "+
					   " values('"+dto.getNotice_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getFile_name()+"', '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', "+dto.getHit()+") ";
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
	
	public String getNoticeNo() { //notice_no 최대값 검사	
		String query = " select max(notice_no) from a02_exam_임수혁";
		int maxId = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				maxId = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getNoticeNo:"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getNoticeNo:"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getNoticeNo:"+e.getMessage());
		}
		try{
			common.close(con,ps,rs);
		} catch(Exception e) {
			System.out.println("getNoticeNo close() 오류~"+e.getMessage());
		}
		if(maxId == 0) maxId = 0001;  
		else maxId = maxId + 0001;
		return Integer.toString(maxId);
	}
}
