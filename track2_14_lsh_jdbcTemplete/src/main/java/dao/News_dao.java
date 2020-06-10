package dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.CommonTemplate;
import common.CommonUtil;
import dto.News_dto;

public class News_dao {
	private JdbcTemplate tem = CommonTemplate.getTempl();
	
	// ==== 원본 (template까지 써서 쓰는 방법) ====
//	public ArrayList<News_dto> getNewsList(String select, String search, JdbcTemplate template){ //list 원본
//		RowMapper<News_dto> News_dtoS = new BeanPropertyRowMapper<News_dto>(News_dto.class);	
//		String query = " select a.no, a.title, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//					   " from track2_14_news a, track2_14_member b "+ 
//					   " where a.reg_info = b.id "+
//					   " and "+select+" like '%"+search+"%' "+
//				       " order by no desc ";
//		ArrayList<News_dto> dtos = (ArrayList<News_dto>)template.query(query, News_dtoS);
//		return dtos;
//	}
//	public void hitCount(String no, JdbcTemplate template) { //hitCount
//		String hitQuery = " update track2_14_news set hit = hit+1 where no='"+no+"' ";
//		int result = template.update(hitQuery);
//	}
//	public News_dto getNewsView(String no, JdbcTemplate template){ //view
//		String query= " select a.no, a.title, a.content, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//					  " from track2_14_news a, track2_14_member b "+ 
//					  " where a.reg_info = b.id "+
//				      " and a.no= '"+no+"' ";
//		BeanPropertyRowMapper<News_dto> News_dto = new BeanPropertyRowMapper<News_dto>(News_dto.class);
//		News_dto dto = (News_dto)template.queryForObject(query, News_dto);
//		return dto;
//	}
	// ==== 원본 ====
	
	public ArrayList<News_dto> getNewsList2(String select, String search){ //list
		RowMapper<News_dto> News_dtoS = new BeanPropertyRowMapper<News_dto>(News_dto.class);	
		String query = " select a.no, a.title, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
					   " from track2_14_news a, track2_14_member b "+ 
					   " where a.reg_info = b.id "+
					   " and "+select+" like '%"+search+"%' "+
				       " order by no desc ";
		ArrayList<News_dto> dtos = (ArrayList<News_dto>)tem.query(query, News_dtoS);
		return dtos;
	}
	public void hitCount2(String no) { //hitCount
		String hitQuery = " update track2_14_news set hit = hit+1 where no='"+no+"' ";
		int result = tem.update(hitQuery);
	}
	public News_dto getNewsView2(String no){ //view
		String query= " select a.no, a.title, a.content, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
					  " from track2_14_news a, track2_14_member b "+ 
					  " where a.reg_info = b.id "+
				      " and a.no= '"+no+"' ";
		BeanPropertyRowMapper<News_dto> News_dto = new BeanPropertyRowMapper<News_dto>(News_dto.class);
		News_dto dto = (News_dto)tem.queryForObject(query, News_dto);
		return dto;
	}
	public String getNewsNo(){ //번호 생성
		String maxQuery = "select max(no) from track2_14_news ";
		String no = (String)tem.queryForObject(maxQuery, String.class);
		if(no == null) {
			no = "W001";
		} else {
			no = no.substring(1); 
			int num = Integer.parseInt(no); 
			num = num + 1; 
			no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
			no = "W"+no; 
		}
		return no;
	}
	public int saveNews(String no, String title, String content, String reg_info, String reg_date){ //등록
		String query =  " insert into track2_14_news "+ 
						" (no,title,content,reg_info,reg_date) "+ 
						" values ('"+no+"', '"+title+"', '"+content+"', '"+reg_info+"', '"+reg_date+"') ";
		int result = tem.update(query);
		return result;
	}
	public int updateNews(String no, String title, String content) { //수정
		String query = " update track2_14_news "+ 
					   " set title='"+title+"', content='"+content+"' "+ 
					   " where no = '"+no+"' ";
		int result = tem.update(query);
		return result;
	}
	public int deleteNews(String no) { //삭제
		String query = " delete from track2_14_news "+ 
			  	   	   " where no = '"+no+"' ";
//		int result = tem.update(query);
		return tem.update(query);
	}
	
	
/*
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public int updateNews(String no, String title, String content) { //�닔�젙 
		int result = 0;
		String query = " update track2_14_news "+
				       " set title = '"+title+"', content = '"+content+"' "+ 
					   " where no ='"+no+"' ";		
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result 	= ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("종료 오류~~"); }
		}
		return result;
	}
	
	public int deleteNews(String no) { //�궘�젣
		int result = 0;
		String query = " delete from track2_14_news where no = '"+no+"' ";
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result 	= ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~"); }
		}	
		return result;
	}
	
	public void hitCount(String no) { //議고쉶�닔 利앷�
		String query =" update track2_14_news set hit = hit + 1 "+ 
					  " where no ='"+no+"' ";		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~"); }
		}			
	}
	
	public int saveNews(News_dto dto) { //�벑濡�
		int result =0;
		String query ="insert into track2_14_news "+ 
				"(no, title, content, reg_info, reg_date) "+ 
				"values "+ 
				"('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getReg_info()+"','"+dto.getReg_date()+"')";		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~"); }
		}			
		return result;
	}
	
	public String getNewsNo(){ //踰덊샇�깮�꽦
		String no="";
		String query ="select max(no) from track2_14_news ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				no = rs.getString(1); 
			}
			if(no == null) {
				no ="W001";
			} else {
				no = no.substring(1); 
				int num = Integer.parseInt(no); 
				num = num + 1; 
				no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
				no = "W"+no; 
			}
		}catch(SQLException se) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~"); }
		}	
		return no;
	}

	public News_dto getNewsView(String no){ //�긽�꽭蹂닿린
		News_dto dto = null;
		String query=" select a.no, a.title, a.content, a.hit, b.name, "+ 
					 " to_char(a.reg_date,'yyyy-MM-dd') as reg_date "+ 
					 " from track2_14_news a, a_common_member b "+ 
					 " where a.reg_info = b.id "+ 
					 " and a.no ='"+no+"' ";
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String nn 		= rs.getString("no");
				String title 	= rs.getString("title");
				String content 	= rs.getString("content");
				String hit 		= rs.getString("hit");
				String name 	= rs.getString("name");
				String reg_date = rs.getString("reg_date");
				
				dto = new News_dto(nn, title, content, hit, name, reg_date);
			}
		}catch(SQLException se) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e) {
				System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~");
			}
		}		
		return dto;
	}
		
	public ArrayList<News_dto> getNewsList(String select, String search){ //紐⑸줉
		ArrayList<News_dto> dtos = new ArrayList<News_dto>();
		String query = " select no, title, to_char(reg_date,'yyyy-MM-dd') as reg_date, hit "+ 
					   " from track2_14_news "+ 
					   " where "+select+" like '%"+search+"%' "+
				       " order by no desc ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String no 		= rs.getString("no");
				String title 	= rs.getString("title");
				String reg_date = rs.getString("reg_date");
				String hit 		= rs.getString("hit");
				
				News_dto dto = new News_dto(no, title, reg_date, hit);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class嶺뚮쪋�삕:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e) {
				System.out.println("占쎈꽞占쎄턁筌앾옙 �뜝�럩沅롳옙紐닷뜝占�~~");
			}
		}
		return dtos;
	}
*/	
}