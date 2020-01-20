package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.News_DTO;

public class News_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;
	
	public int newsHit(String new_no){ //조회수 증가
		int result = 0;
		String query = " update a02_exam_임수혁 set hit = hit+1 where news_no ='"+new_no+"' ";
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
		String query = " select news_no, title, content, reg_id, to_char(reg_date, 'yy-MM-dd'), hit "+
					   " from a02_exam_임수혁 "+
					   " where news_no = '"+new_no+"'";
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
				dto = new News_DTO(news_no, title, content, reg_id, reg_date, hit);
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
	
	public ArrayList<News_DTO> getNewsList_exam(String selValue, String txtValue){ //목록조회
		ArrayList<News_DTO> dtos = new ArrayList<News_DTO>();
		String query = " select news_no, title, content, reg_id, to_char(reg_date, 'yy-MM-dd'), hit "+
				   " from a02_exam_임수혁 "+
				   " where "+selValue+" like '%"+txtValue+"%' "+					   
				   " order by news_no desc ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {			
				News_DTO dto = new News_DTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
											rs.getString(5), rs.getInt(6));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getNewsList_exam():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getNewsList_exam():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getNewsList_exam() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public int insertNews(News_DTO dto) { //뉴스 등록
		int result = 0;	
		String query = " insert into a02_exam_임수혁(news_no, title, content, reg_id, reg_date, hit) "+
					   " values('"+dto.getNews_no()+"', '"+dto.getTitle()+"', '"+dto.getContent()+"', "+
					   " '"+dto.getReg_id()+"', '"+dto.getReg_date()+"', "+dto.getHit()+") ";
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
	
	public String getNewsNo() { // 최대값 검사	
		String query = " select max(news_no) from a02_exam_임수혁";
		int maxId = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				maxId = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getNewsNo:"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getNewsNo:"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getNewsNo:"+e.getMessage());
		}
		try{
			common.close(con,ps,rs);
		} catch(Exception e) {
			System.out.println("getNewsNo close() 오류~"+e.getMessage());
		}
		if(maxId == 0) maxId = 0001;  
		else maxId = maxId + 0001;
		return Integer.toString(maxId);
	}
}
