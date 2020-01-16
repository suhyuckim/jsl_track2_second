package dao;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import common.*;
import dto.News_DTO;

public class News_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int updateNews(String news_no, String title, String content,  String file_name_1, String reg_id, String reg_date) { //뉴스 공지사항 수정
		int result = 0;	
		String query = ""; 
		if(file_name_1 == null) {
			query = " update A02_TRACK2_HOME_NEWS set title ='"+title+"', content ='"+content+"', "+
					" reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = null "+
					" where news_no = '"+news_no+"' ";
		}else {
			query = " update A02_TRACK2_HOME_NEWS set title ='"+title+"', content ='"+content+"', "+
				    " reg_id='"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = '"+file_name_1+"' "+
				    " where news_no = '"+news_no+"' ";
		}
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException updateNews():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception updateNews():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("updateNews() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int deleteNews(String news_no) { //공지사항 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_HOME_NEWS where news_no = '"+news_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteNews():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteNews():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteNews() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertNews(News_DTO dto) { //뉴스 공지사항 등록
		int result = 0;
		String query = " insert into A02_TRACK2_HOME_NEWS "+
					   " (news_no, title, content, file_name_1, reg_id, reg_date, hit) "+ 
					   " values ('"+dto.getNews_no()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getFile_name_1()+"','"+dto.getReg_id()+"','"+dto.getReg_date()+"', "+dto.getHit()+") ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertNews():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertNews():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertNews() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public String getMaxNo(){  //news_no 최대값 검사
		String query  = " select max(news_no) from A02_TRACK2_HOME_NEWS ";
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
	
	public String getNewsNo() {
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
	
	public int newsHit(String new_no){ //조회수 증가
		int result = 0;
		String query = " update A02_TRACK2_HOME_NEWS set hit = hit+1 where news_no ='"+new_no+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException newsHit():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception newsHit():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("newsHit() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public News_DTO getNewsView(String new_no){ //상세조회
		News_DTO dto = null;
		String query = " select a.news_no, a.title, a.content, a.file_name_1, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit "+
					   " from A02_TRACK2_HOME_NEWS a, A02_TRACK2_HOME_MEMBER b "+
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
				String file_name_1  = rs.getString(4);				
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				int    hit 			= rs.getInt(7);			
				dto = new News_DTO(news_no, title, content, file_name_1, reg_id, reg_date, hit);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<News_DTO> getNewsList(String selValue, String txtValue){ //뉴스목록조회
		ArrayList<News_DTO> dtos = new ArrayList<News_DTO>();
		String query = "";
		query = " select a.news_no, a.title, a.content, a.file_name_1, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit "+
					   " from A02_TRACK2_HOME_NEWS a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id ";
		
		if(selValue.equals("title") || selValue.equals("content")){
			query += " and "+selValue+" like '%"+txtValue+"%' order by a.news_no desc ";
		} else if(selValue.equals("all")) {
			query += " order by a.news_no desc ";
		}
		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				String news_no 		= rs.getString(1);
				String title 		= rs.getString(2);
				String content 		= rs.getString(3);	
				String file_name_1  = rs.getString(4);
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				int    hit 			= rs.getInt(7);
				News_DTO dto = new News_DTO(news_no, title, content, file_name_1, reg_id, reg_date, hit);
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
	
	//servlet jsp new
	public ArrayList<News_DTO> getNewsList_servlet(String selValue, String txtValue){ //뉴스목록조회
		ArrayList<News_DTO> dtos = new ArrayList<News_DTO>();
		String query = " select a.news_no, a.title, a.content, a.file_name_1, b.name, to_char(a.reg_date, 'yy-MM-dd'), a.hit "+
					   " from A02_TRACK2_HOME_NEWS a, A02_TRACK2_HOME_MEMBER b "+
					   " where a.reg_id = b.id "+
					   " and a."+selValue+" like '%"+txtValue+"%' "+
					   " order by a.news_no desc";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				News_DTO dto = new News_DTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
										    rs.getString(5), rs.getString(6), rs.getInt(7));
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
}
