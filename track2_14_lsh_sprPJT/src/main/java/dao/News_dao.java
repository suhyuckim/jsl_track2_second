package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.News_dto;

public class News_dao {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	
	public int updateNews(String no, String title, String content) { //수정 
		int result = 0;
		String query = " update track2_14_news "+
				       " set title = '"+title+"', content = '"+content+"' "+ 
					   " where no ='"+no+"' ";		
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
	
	public int deleteNews(String no) { //삭제
		int result = 0;
		String query = " delete from track2_14_news where no = '"+no+"' ";
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
	
	public void hitCount(String no) { //조회수 증가
		String query =" update track2_14_news set hit = hit + 1 "+ 
					  " where no ='"+no+"' ";		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("�넫�굝利� 占쎌궎�몴占�~~"); }
		}			
	}
	
	
	public int saveNews(News_dto dto) { //등록
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
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("�넫�굝利� 占쎌궎�몴占�~~"); }
		}			
		return result;
	}
	
	public String getNewsNo(){ //번호생성
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
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("�넫�굝利� 占쎌궎�몴占�~~"); }
		}	
		return no;
	}

	public News_dto getNewsView(String no){ //상세보기
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
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e) {
				System.out.println("�넫�굝利� 占쎌궎�몴占�~~");
			}
		}		
		return dto;
	}
		
	public ArrayList<News_dto> getNewsList(String select, String search){ //목록
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
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class筌륅옙:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e) {
				System.out.println("�넫�굝利� 占쎌궎�몴占�~~");
			}
		}
		return dtos;
	}
}