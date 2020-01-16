package rent;
import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarHistory_DAO;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;

public class CarHistory implements InterCarRent {
	public void proc() {
		CarHistory_DAO dao  = new CarHistory_DAO();
		CommonUtil util     = new CommonUtil();
		Scanner sc 			= new Scanner(System.in);
		ArrayList<CarInfo_DTO> arr     = new ArrayList<CarInfo_DTO>();
		ArrayList<CarRent2_DTO> arr2     = new ArrayList<CarRent2_DTO>();
		
		String query = " select car_id, car_name, produce, produce_ym, driving_total_km, "+
				  	   " decode(status, 'y', '[렌트가능]', '[렌트불가]') "+
				  	   " from A02_TRACK2_CARINFO ";
		arr = dao.getCarHistory_List(query);
		System.out.println(util.getRPad("",60,"="));
		System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
		System.out.println(util.getRPad("",60,"-"));
		for(int k=0; k<arr.size(); k++) {
			System.out.print(arr.get(k).getCar_id()+"\t");
			System.out.print(arr.get(k).getCar_name()+"\t");
			System.out.print(arr.get(k).getProduce()+"\t");
			System.out.print(arr.get(k).getProduce_ym()+"\t");
			System.out.print(util.getLPad(arr.get(k).getDriving_total_km()+"km",5," ")+"\t");
			System.out.print(arr.get(k).getStatus()+"\n");
		}
		System.out.println(util.getRPad("",60,"="));
		String car_id = "";
		do {
			do {
				System.out.print("조회할  car_id를 입력하세요(xx_xxx) [돌아가기:0] : ");
				car_id = sc.next();
				if(car_id.equals("0")) {
					System.out.println("취소하고 메인으로 돌아갑니다.");
					return;
				}
				query = " select a.rent_id, c.name, c.id, car_name, a.car_id, a.driving_km, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
						" decode(a.rent_return_date,null,'[미반납]',to_char(a.rent_return_date,'yyyy-mm-dd')) "+
						" from A02_TRACK2_CARRENT a, A02_TRACK2_CARINFO b, a02_track2_member c "+
						" where a.car_id = b.car_id and a.member_id = c.id "+
						" and a.car_id = '"+car_id+"' order by a.rent_id ";
				arr2 = dao.getCarHistory_List_2(query);
				if(arr2.size() ==0) {
					System.out.println("검색하신 차량은 대여중이 아니거나, 존재하지 않는 차량입니다.");
				} else {
					System.out.println(util.getRPad("",80,"="));
					System.out.println("rent_id\t\t성명\t\t차량명\t\t주행거리\t렌트일자\t\t반납일자");
					System.out.println(util.getRPad("",80,"-"));
					for(int k=0; k<arr2.size(); k++) {
						System.out.print(arr2.get(k).getRent_id()+"\t");
						System.out.print(arr2.get(k).getName()+"["+arr2.get(k).getId()+"]"+"\t");
						System.out.print(arr2.get(k).getCar_name()+"["+arr2.get(k).getCar_id()+"]"+"\t");
						System.out.print(arr2.get(k).getDriving_km()+"km"+"\t");					
						System.out.print(arr2.get(k).getRent_start_date()+"\t");
						System.out.print(arr2.get(k).getRent_return_date()+"\n");
					}
					System.out.println(util.getRPad("",80,"="));	
				}
			}while(arr2.size() != 0);
		}while(arr2.size() == 0);
	}
}
