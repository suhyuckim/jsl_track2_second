package dao;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class CarRent_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public int deleteCar(String query) { //차량삭제
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException deleteMember():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteMember():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("deleteMember() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public CarRent2_DTO getCarInfo(String query){ // 렌트취소할때 렌트아이디 조회
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
			}
		}catch(SQLException se) {
			System.out.println("SQLException getCarInfo():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCarInfo():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getCarInfo() close"+e.getMessage());
			}
		}
		return dto;
	}	
	
	public ArrayList<CarRent2_DTO> getCarRent_List(String query){ 
		ArrayList<CarRent2_DTO> arr = new ArrayList<CarRent2_DTO>();
		CarRent2_DTO dtos = null;	
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){				
				String rent_id = rs.getString(1); 
				String name = rs.getString(2);  
				String id = rs.getString(3);
				String car_name = rs.getString(4); 
				String car_id = rs.getString(5); 			
				String rent_start_date = rs.getString(6); 
				String rent_return_date = rs.getString(7); 
				dtos = new CarRent2_DTO(rent_id, name, id, car_name, car_id, rent_start_date, rent_return_date);
				arr.add(dtos);
			}						
		} catch(RemoteException re) {
			System.out.println("RemoteException getCarRent_List():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getCarRent_List():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getCarRent_List():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("getCarRent_List close() 오류~"+e.getMessage());
			}		
		}	
		return arr;
	}	
	
	public ArrayList<CarInfo_DTO> getCarInfo_List(String gubun, String search){ //차량조회
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
		String query = "";
		query = " select car_id, substr(car_name,0,5), produce, produce_ym, driving_total_km, "+
				" decode(status, 'y', '[렌트가능]','[렌트불가능]') status "+
				" from A02_TRACK2_CARINFO ";				
		if(gubun.equals("all")) {
			query = query + " where status = 'y' order by car_id desc ";
		} else if(gubun.equals("car_id")) {
			query = query + " where car_id like '%"+search+"%' and status = 'y' order by car_id desc ";
		} 
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				CarInfo_DTO dto = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setProduce(rs.getString(3));
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getInt(5));
				dto.setStatus(rs.getString(6));
				dtos.add(dto);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getCarInfo_List():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCarInfo_List():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getCarInfo_List() close"+e.getMessage());
			}
		}
		return dtos;
	}	
	
	public String getMaxid2(){ //car_id 최대값 
		String query  = " select max(rent_id) from A02_TRACK2_CARRENT ";
		String result = null;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMaxid2():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMaxid2():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMaxid2() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public String getMaxCarId2() { //car_id 체크할떼 19_   001 체크
		CommonUtil util = new CommonUtil();
		String carMaxId = getMaxid2(); //yymm_000n
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); //2019
		int month = cal.get(Calendar.MONTH)+1;
		
		String nowYear = Integer.toString(year); //"2019"
		nowYear = nowYear.substring(2,4); //"19"
		String nowMonth = util.getLPad(Integer.toString(month),2,"0"); //08
						
		if(getMaxid2() == null) {
			carMaxId = nowYear+nowMonth+"_0001"; 
		} else {
			String datayear = getMaxid2().substring(0,4); //yymm
 			if(datayear.equals(nowYear+nowMonth)) {
				int y = Integer.parseInt(carMaxId.substring(5,9)); 
				y++;
				String left = util.getLPad(Integer.toString(y), 4, "0"); 
				carMaxId =  datayear+"_"+left; //
			} else if(Integer.parseInt(datayear) > Integer.parseInt(nowYear+nowMonth)) {
				int y = Integer.parseInt(carMaxId.substring(5,9)); 
				y++;
				String left = util.getLPad(Integer.toString(y), 4, "0");
				carMaxId = datayear+"_"+left; 
			} else if(Integer.parseInt(datayear) < Integer.parseInt(nowYear+nowMonth)) { 
				carMaxId = carMaxId+"_0001"; 
			}
		}
		return carMaxId;
	}
	
	public int insertCarRent(String query) { //차량렌트 등록,차량상태수정,삭제
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException insertCarRent():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertCarRent():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertCarRent() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public int insertCarRent_commit(String query, String queryUpdate) { //차량렌트 등록,차량상태수정,삭제
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
			System.out.println("SQLException insertCarRent_commit():"+se.getMessage());
			try {
				con.rollback();
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}catch(Exception e) {
			System.out.println("Exception insertCarRent_commit():"+e.getMessage());
		} finally {
			try {
				con.setAutoCommit(true);
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertCarRent_commit() close"+e.getMessage());
			}
		}
		return result;
	}
}