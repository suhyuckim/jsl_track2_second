package rent;
import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.MemberHistory_DAO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class MemberHistory implements InterCarRent {
	public void proc() {
		MemberHistory_DAO dao = new MemberHistory_DAO();
		CommonUtil util       = new CommonUtil();
		Scanner sc 			  = new Scanner(System.in);
		ArrayList<Member_DTO> arr		 = new ArrayList<Member_DTO>();
		ArrayList<CarRent2_DTO> arr2     = new ArrayList<CarRent2_DTO>();
		
		String query = " select * from A02_TRACK2_MEMBER order by id ";
		System.out.println(util.getRPad("",20,"="));
		System.out.println("회원ID\t회원명\t나이");
		System.out.println(util.getRPad("",20,"-"));
		arr = dao.MemberHistory_List(query);
		for(int k=0; k<arr.size(); k++) {
			System.out.print(arr.get(k).getId()+"\t");
			System.out.print(arr.get(k).getName()+"\t");
			System.out.print(arr.get(k).getAge()+"\n");
		}
		System.out.println(util.getRPad("",20,"="));
		String member_id = "";
		do {
			do {
				System.out.print("조회할 회원코드를 입력하세요(xxx) [돌아가기:0] : ");
				member_id = sc.next();
				if(member_id.equals("0")) {
					System.out.println("취소하고 메인으로 돌아갑니다.");
					return;
				}
				query = " select a.rent_id, c.name, c.id, car_name, a.car_id, a.driving_km, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
						" decode(a.rent_return_date,null,'[미반납]',to_char(a.rent_return_date,'yyyy-mm-dd')) "+
						" from A02_TRACK2_CARRENT a, A02_TRACK2_CARINFO b, a02_track2_member c "+
						" where a.car_id = b.car_id and a.member_id = c.id "+
						" and a.member_id = '"+member_id+"' order by a.rent_id ";
				arr2 = dao.getMemberHistory_List_2(query);
				if(arr2.size() ==0) {
					System.out.println("검색하신 회원ID는 렌트중이 아닙니다.");
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