package dao;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DBConnectionOracle;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class MemberHistory_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con 		 = null;
	PreparedStatement ps = null;
	ResultSet rs		 = null;
	
	public ArrayList<CarRent2_DTO> getMemberHistory_List_2(String query){ 
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
			System.out.println("RemoteException getMemberHistory_List_2():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException getMemberHistory_List_2():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception getMemberHistory_List_2():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("getMemberHistory_List_2 close() 오류~"+e.getMessage());
			}		
		}	
		return arr;
	}		
	
	public ArrayList<Member_DTO> MemberHistory_List(String query){
		ArrayList<Member_DTO> arr = new ArrayList<Member_DTO>();
		Member_DTO dto = null;	
		try {
			con = common.getConnection();
			ps	= con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){				
				dto = new Member_DTO();	
				dto.setId(rs.getString(1)); //아디
				dto.setName(rs.getString(2));  //이름
				dto.setAge(rs.getInt(3)); //나이
				arr.add(dto);
			}						
		} catch(RemoteException re) {
			System.out.println("RemoteException MemberHistory_List():"+re.getMessage());
		} catch(SQLException se) {
			System.out.println("SQLException MemberHistory_List():"+se.getMessage());
		} catch(Exception e) {
			System.out.println("Exception MemberHistory_List():"+e.getMessage());
		} finally {
			try{
				common.close(con,ps,rs);
			} catch(Exception e) {
				System.out.println("MemberHistory_List close() 오류~"+e.getMessage());
			}		
		}	
		return arr;
	}
}