package dao;
import java.sql.*;
import common.DBConnectionOracle;
import dto.CarRent2_DTO;

public class CarReturn_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public int CarReturnDate_Update(String query, String queryUpdate) { //차량렌트 등록,차량상태수정,삭제
		int result = 0;
		try {
			con 	= common.getConnection();
//			ps  	= con.prepareStatement(query);
//			result  = ps.executeUpdate();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(query);
			result = stmt.executeUpdate(queryUpdate);
			con.commit();
		}catch(SQLException se) {
			System.out.println("SQLException CarReturnDate_Update():"+se.getMessage());
			try {
				con.rollback();
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}catch(Exception e) {
			System.out.println("Exception CarReturnDate_Update():"+e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("CarReturnDate_Update() close"+e.getMessage());
			}
		}
		return result;
	}
	
	
	public CarRent2_DTO getCarInfo_2(String query){ // 렌트취소할때 렌트아이디 조회
		CarRent2_DTO dto = null;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				dto = new CarRent2_DTO();
				dto.setRent_id(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setCar_name(rs.getString(4));
				dto.setCar_id(rs.getString(5));
				dto.setRent_start_date(rs.getString(6));
				dto.setRent_return_date(rs.getString(7));
				dto.setDriving_km2(rs.getString(8));
				dto.setDriving_total_km(rs.getInt(9));
			}
		}catch(SQLException se) {
			System.out.println("SQLException getCarInfo_2():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCarInfo_2():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getCarInfo_2() close"+e.getMessage());
			}
		}
		return dto;
	}	
	
	public int CarReturnDate_Update(String query) { //차량반납일자 갱신
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException CarReturnDate_Update():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception CarReturnDate_Update():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("CarReturnDate_Update() close"+e.getMessage());
			}
		}
		return result;
	}
}
