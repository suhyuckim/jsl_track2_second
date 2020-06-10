package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.News_dto;
import dto.Notice_dto;

public class Notice_dao {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs 		 = null;
	
	public int updateNotice(String no, String title, String content, String file_name_1) { //수정 
		int result = 0;
		String query = " update track2_14_notice "+
				       " set title = '"+title+"', content = '"+content+"', file_name_1 = '"+file_name_1+"' "+ 
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
	
	public int deleteNotice(String no) { //삭제
		int result = 0;
		String query = " delete from track2_14_notice where no = '"+no+"' ";
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
		String query =" update track2_14_notice set hit = hit + 1 "+ 
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
	
	
	public int saveNotice(Notice_dto dto) { //등록
		int result =0;
		String query ="insert into track2_14_notice "+ 
				"(no, title, content, file_name_1, reg_id, reg_date) "+ 
				"values "+ 
				"('"+dto.getNo()+"','"+dto.getTitle()+"','"+dto.getContent()+"','"+dto.getFile_name_1()+"','"+dto.getReg_id()+"','"+dto.getReg_date()+"')";		
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try { common.close(con, ps, rs); }catch(Exception e) {	System.out.println("종료 오류~~"); }
		}			
		return result;
	}
	
	public String getNoticeNo(){ //번호생성
		String no="";
		String query ="select max(no) from track2_14_notice ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				no = rs.getString(1); 
			}
			if(no == null) {
				no ="N001";
			} else {
				no = no.substring(1); 
				int num = Integer.parseInt(no); 
				num = num + 1; 
				no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
				no = "N"+no; 
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

	public Notice_dto getNoticeView(String no){ //상세보기
		Notice_dto dto = null;
		String query=" select a.no, a.title, a.content, a.file_name_1, a.reg_id, b.name, "+ 
					 " to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
					 " from track2_14_notice a, track2_14_member b "+ 
					 " where a.reg_id = b.id "+ 
					 " and a.no ='"+no+"' ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String nn 			= rs.getString("no");
				String title 		= rs.getString("title");
				String content 		= rs.getString("content");
				String file_name_1 	= rs.getString("file_name_1");
				String reg_id 		= rs.getString("reg_id");
				String reg_name 	= rs.getString("name");
				String reg_date 	= rs.getString("reg_date");
				String hit 			= rs.getString("hit");
				dto = new Notice_dto(nn, title, content, file_name_1, reg_id, reg_name, reg_date, hit);
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
		
	public ArrayList<Notice_dto> getNoticeList(String select, String search){ //목록
		ArrayList<Notice_dto> dtos = new ArrayList<Notice_dto>();
		String query = " select no, title, file_name_1, to_char(reg_date,'yyyy-MM-dd') as reg_date, hit "+ 
					   " from track2_14_notice "+ 
					   " where "+select+" like '%"+search+"%' "+
				       " order by no desc ";
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String no 			= rs.getString("no");
				String title 		= rs.getString("title");
				String file_name_1 	= rs.getString("file_name_1");
				String reg_date 	= rs.getString("reg_date");
				String hit 			= rs.getString("hit");
				Notice_dto dto = new Notice_dto(no, title, file_name_1, reg_date, hit);
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("*Class명 : "+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+se.getMessage());			
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" :"+e.getMessage());			
		} finally {
			try {
				common.close(con, ps, rs);
			}catch(Exception e) {
				System.out.println("종료 오류~~");
			}
		}
		return dtos;
	}
}