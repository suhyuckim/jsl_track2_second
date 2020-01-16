package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Notice_DTO;
import dto.Score_DTO;
import dto.Smember_DTO;

public class Score_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 			  con = null;
	PreparedStatement 	   ps = null;
	ResultSet 			   rs = null;

	public int getTotal(int kor, int eng, int mat) {
		return kor+eng+mat;
	}
	
	public double getAva(int total, int subject) {
		DecimalFormat df = new DecimalFormat("0.#");
		double re = (double)total/subject;
		String r = df.format(re);
		return Double.parseDouble(r);
	}
	
	public ArrayList<Score_DTO> getScoreList(){ //목록조회
		ArrayList<Score_DTO> dtos = new ArrayList<Score_DTO>();
		String query = " select id, name, kor, eng, mat, total, ava from A02_TRACK2_SERVLET_SCORE order by id ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				Score_DTO dto = new Score_DTO(rs.getString(1),rs.getString(2),rs.getInt(3),
											  rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getScoreList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getScoreList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getScoreList() close:"+e.getMessage());
			}
		}
		return dtos;
	}
	
	public Score_DTO getScoreView(String id){ //상세조회
		Score_DTO dto = null;
		String query = " select id, name, kor, eng, mat, total, ava from A02_TRACK2_SERVLET_SCORE "+
					   " where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				dto = new Score_DTO(rs.getString(1),rs.getString(2),rs.getInt(3),
									rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7));
			}
		}catch(SQLException se) {
			System.out.println("SQLException getScoreView():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getScoreView():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getScoreView() close:"+e.getMessage());
			}
		}
		return dto;
	}
	
	public int getScoreDelete(String id) { // 삭제
		int result = 0;	
		String query = " delete from A02_TRACK2_SERVLET_SCORE where id = '"+id+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException getScoreDelete():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getScoreDelete():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getScoreDelete() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int getScoreUpdate(Score_DTO dto) { //수정
		int result = 0;	
		String	query = " update A02_TRACK2_SERVLET_SCORE set "+
				 		" name ='"+dto.getName()+"', kor ="+dto.getKor()+", eng ="+dto.getEng()+", "+
				 		" mat ="+dto.getMat()+", total="+dto.getTotal()+", ava="+dto.getAva()+" "+
						" where id='"+dto.getId()+"' ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException getScoreUpdate():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getScoreUpdate():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getScoreUpdate() close:"+e.getMessage());
			}
		}
		return result;		
	}
	
	public int insertScore(Score_DTO dto) { //score 등록
		int result = 0;	
		String query = " insert into A02_TRACK2_SERVLET_SCORE(id, name, kor, eng, mat, total, ava) "+
					   " values('"+dto.getId()+"', '"+dto.getName()+"', '"+dto.getKor()+"', "+
					   " '"+dto.getEng()+"', '"+dto.getMat()+"', '"+dto.getTotal()+"', "+dto.getAva()+") ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			result  = ps.executeUpdate();			
		}catch(SQLException se) {
			System.out.println("SQLException insertScore():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertScore():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertScore() close:"+e.getMessage());
			}
		}
		return result;		
	}	
	
	public String getMaxId() {		
		String query = " select max(id) from A02_TRACK2_SERVLET_SCORE ";
		int maxId = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {				
				maxId = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getMaxId:"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMaxId:"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMaxId:"+e.getMessage());
		}
		try{
			common.close(con,ps,rs);
		} catch(Exception e) {
			System.out.println("close() 오류~"+e.getMessage());
		}
		if(maxId == 0) maxId = 101;  
		else maxId = maxId + 1;
		return Integer.toString(maxId);
	}
}