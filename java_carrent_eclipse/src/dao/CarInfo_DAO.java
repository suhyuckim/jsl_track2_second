package dao;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.Member_DTO;

public class CarInfo_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public int getCar_Count_delete(String car_id) {
		String query = " select count(*) from A02_TRACK2_CARRENT where car_id = '"+car_id+"' ";
		int count = 0;
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {				
				count = rs.getInt(1);							
			}
		} catch(RemoteException re) {
			System.out.println("RemoteException getCar_Count_delete():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getCar_Count_delete():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getCar_Count_delete():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("getCar_Count_delete close() 오류~"+e.getMessage());
			}		
		}
		return count;
	}
	
	public CarInfo_DTO getCarInfo(String car_id){ // 수정할때 차량 한대 조회
		CarInfo_DTO dto = null;
		String query = " select car_id, car_name, produce, produce_ym, driving_total_km, decode(status, 'y', '[렌트가능]','[렌트불가능]') status "+
					   " from A02_TRACK2_CARINFO "+
					   " where car_id = '"+car_id+"' order by car_id ";
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				dto = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setProduce(rs.getString(3));
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getInt(5));
				dto.setStatus(rs.getString(6));
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
	
	public int insertCarList(String query) { //차량등록,수정,삭제
		int result = 0;
		try {
			con 	= common.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(SQLException se) {
			System.out.println("SQLException insertCarList():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception insertCarList():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("insertCarList() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public ArrayList<CarInfo_DTO> getCarInfo_List(String gubun, String search){ //차량조회
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
		String query = "";
		query = " select car_id, substr(car_name,0,5), produce, produce_ym, driving_total_km, "+
				" decode(status, 'y', '[렌트가능]', '[렌트불가능]') status "+
				" from A02_TRACK2_CARINFO ";				
		if(gubun.equals("car_name")) {
			query = query + " where car_name like '%"+search+"%' order by car_id desc ";
		} else if(gubun.equals("produce")) {
			query = query + " where produce like '%"+search+"%' order by car_id desc ";
		} else if(gubun.equals("all")) {
			query = query + " order by car_id desc ";
		} else if(gubun.equals("car_id")) {
			query = query + " where car_id like '%"+search+"%' order by car_id desc ";
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
	
	public String getMaxid(){ //car_id 최대값 
		String query  = " select max(car_id) from A02_TRACK2_CARINFO ";
		String result = null;
		try {
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		}catch(SQLException se) {
			System.out.println("SQLException getMaxid():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMaxid():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getMaxid() close"+e.getMessage());
			}
		}
		return result;
	}
	
	public String getMaxCarId() { //car_id 체크할떼 19_   001 체크
		String carMaxId = getMaxid();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year);
		nowYear = nowYear.substring(2,4); //"19"
		if(carMaxId == null) {
			carMaxId = nowYear+"_001";
		}else {
			String dataYear = carMaxId.substring(0,2); //"19"
			
			if(nowYear.equals(dataYear)) {
				int y = Integer.parseInt(carMaxId.substring(3)); //3
				y++; //4
				String r = CommonUtil.getLPad(Integer.toString(y),3,"0");
				carMaxId = dataYear +"_"+r;
			} else {
				carMaxId = nowYear+"_"+"001";
			}		
		}		
//		System.out.println(" carMaxId : "+carMaxId);
		return carMaxId;
	}
}