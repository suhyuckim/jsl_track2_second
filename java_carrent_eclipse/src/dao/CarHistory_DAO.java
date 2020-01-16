package dao;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;

public class CarHistory_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public ArrayList<CarRent2_DTO> getCarHistory_List_2(String query){ 
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
				int driving_km = rs.getInt(6);
				String rent_start_date = rs.getString(7); 
				String rent_return_date = rs.getString(8); 
				dtos = new CarRent2_DTO(rent_id, name, id, car_name, car_id, driving_km, rent_start_date, rent_return_date);
				arr.add(dtos);
			}						
		} catch(RemoteException re) {
			System.out.println("RemoteException getCarHistory_List_2():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getCarHistory_List_2():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getCarHistory_List_2():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("getCarHistory_List_2 close() 오류~"+e.getMessage());
			}		
		}	
		return arr;
	}		
	
	public ArrayList<CarInfo_DTO> getCarHistory_List(String query){ //차량조회
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
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
			System.out.println("SQLException getCarHistory_List():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCarHistory_List():"+e.getMessage());
		} finally {
			try {
				common.close(con,ps,rs);
			}catch(Exception e) {
				System.out.println("getCarHistory_List() close"+e.getMessage());
			}
		}
		return dtos;
	}
}
