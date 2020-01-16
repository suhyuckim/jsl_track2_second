package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.News_DTO;
import dto.Notice_DTO;

public class News_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;	
	
	public int updateNews_servlet(String news_no, String title, String content, String file_name_1, String reg_id, String reg_date) { //공지사항 수정
		int result = 0;	
		String query = ""; 
		if(file_name_1 == null) {
			query = " update A02_TRACK2_SERVLET2_NEWS set title ='"+title+"', content ='"+content+"', "+
					" reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = null "+
					" where news_no = '"+news_no+"' ";
		} else {
			query = " update A02_TRACK2_SERVLET2_NEWS set title ='"+title+"', content ='"+content+"', "+
				    " reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = '"+file_name_1+"' "+
				    " where news_no = '"+news_no+"' ";
		}
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateNews_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateNews_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateNews_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int deleteNews_servlet(String news_no) { //뉴스 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_SERVLET2_NEWS where news_no = '"+news_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteNews_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteNews_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteNews_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertNews_servlet(News_DTO dto) { //뉴스 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET2_NEWS(news_no, title, content, reg_id, reg_date, hit, file_name_1) "+
					   " values('"+dto.getNews_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', "+dto.getHit()+", '"+dto.getFile_name_1()+"') ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertNews_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertNews_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertNews_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public News_DTO getNewsView_serlvet(String new_no){ //상세조회
		News_DTO dto = null;
		String query = " select a.news_no, a.title, a.content, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit, a.file_name_1 "+
					   " from A02_TRACK2_SERVLET2_NEWS a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a.news_no = '"+new_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String news_no 		= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);
				String reg_id 		= rs.getString(4);
				String reg_date 	= rs.getString(5);
				int    hit 			= rs.getInt(6);
				String file_name_1  = rs.getString(7);
				dto = new News_DTO(news_no, title, content, reg_id, reg_date, hit, file_name_1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsView_serlvet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsView_serlvet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsView_serlvet() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int newsHit_servlet(String news_no){ //조회수 증가
		int result = 0;
		String query = " update A02_TRACK2_SERVLET2_NEWS set hit = hit+1 where news_no ='"+news_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException newsHit_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception newsHit_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("newsHit_servlet() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public ArrayList<News_DTO> getNewsList_servlet(String selValue, String txtValue){ //목록조회
		ArrayList<News_DTO> dtos = new ArrayList<News_DTO>();
		String query = " select a.news_no, a.title, a.content, b.name, to_char(a.reg_date, 'yyyy-MM-dd'), a.hit, a.file_name_1 "+
					   " from A02_TRACK2_SERVLET2_NEWS a, A02_TRACK2_SERVLET2_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+					   
					   " order by a.news_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {			
				News_DTO dto = new News_DTO(rs.getString(1), rs.getString(2), rs.getString(3), 
											rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsList_servlet():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsList_servlet():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsList_servlet() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public String getMaxNo(){  //news_no 최대값 검사
		String query  = " select max(news_no) from A02_TRACK2_SERVLET2_NEWS ";
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
	
	public String getNewsNo() { //news_no 최대값 검사해서 id 만들기
		String newsNo = getMaxNo();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(newsNo == null) {
			newsNo = nowYear+"_0001";
		} else {
			String dataYear = newsNo.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(newsNo.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),4,"0");
				newsNo = dataYear +"_"+r;
			} else {
				newsNo = nowYear+"_"+"0001";
			}		
		}		
		return newsNo;
	}
	
	public int getNewsCount(){ //게시물조회
		String query  = " select count(*) from A02_TRACK2_SERVLET2_NEWS ";
		int count = 0;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsCount():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsCount():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsCount() close:"+e.getMessage());
			}
		}
		return count;
	}	
}