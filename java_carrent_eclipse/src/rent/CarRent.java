package rent;
import java.util.ArrayList;

import InterCommon.InterCarRent;

import java.util.*;
import common.CommonUtil;
import dao.CarInfo_DAO;
import dao.CarRent_DAO;
import dao.Member_DAO;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class CarRent implements InterCarRent{
	public void proc() {
		Scanner sc 	      = new Scanner(System.in);
		Member_DAO dao    = new Member_DAO();
		CarInfo_DAO c_dao = new CarInfo_DAO();
		CarRent_DAO i_dao = new CarRent_DAO();
		CommonUtil util   = new CommonUtil();
		ArrayList<Member_DTO> dtos     = new ArrayList<Member_DTO>();
		ArrayList<CarInfo_DTO> arr     = new ArrayList<CarInfo_DTO>();
		ArrayList<CarRent2_DTO> arr2   = new ArrayList<CarRent2_DTO>();
				
		int i = 0;
		int gubun = 0;
		String next            = "";
		String car_id          = "";
		String select          = "";
		String member_id 	   = "";
		String rent_start_date = "";
		String today 		   = "";
		boolean result = true;
		
		do {
			System.out.print("차량대여[1] 렌트취소[2] 메인으로[0] => ");
			gubun = sc.nextInt();
			if(gubun == 1) {
				dtos = dao.getMemberList("all","");
				System.out.println(util.getRPad("",60,"="));
				System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일\t");
				System.out.println(util.getRPad("",60,"-"));
				for(int k=0; k<dtos.size(); k++) {
					System.out.print(dtos.get(k).getId()+"\t");
					System.out.print(dtos.get(k).getName()+"\t");
					System.out.print(dtos.get(k).getAge()+"\t");
					System.out.print(dtos.get(k).getDept()+"\t");
					System.out.print(dtos.get(k).getRank()+"\t");
					System.out.print(dtos.get(k).getAddress()+"\t");
					System.out.print(dtos.get(k).getReg_date()+"\n");
				}
				System.out.println(util.getRPad("",60,"="));
				do {
					System.out.print("Member_id 입력(101~999) : ");
					member_id = sc.next();
					dtos = dao.getMemberList("member_id", member_id);
					if(dtos.size() == 0) {
						System.out.println(" ===== 검색하신 내용이 없습니다 ===== ");
					}else {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일\t");
						System.out.println(util.getRPad("",60,"-"));
						for(int k=0; k<dtos.size(); k++) {
							System.out.print(dtos.get(k).getId()+"\t");
							System.out.print(dtos.get(k).getName()+"\t");
							System.out.print(dtos.get(k).getAge()+"\t");
							System.out.print(dtos.get(k).getDept()+"\t");
							System.out.print(dtos.get(k).getRank()+"\t");
							System.out.print(dtos.get(k).getAddress()+"\t");
							System.out.print(dtos.get(k).getReg_date()+"\n");
						}
						System.out.println(util.getRPad("",60,"="));
					}
				}while(dtos.size() == 0);
				arr = i_dao.getCarInfo_List("all", "");
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
					i++;
					if(i%5 == 0) {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("다음 페이지 [y]");
						System.out.println("메인 으로 [n]");
						System.out.print("작 업 선 택 -> ");
						next = sc.next();
						if(next.equals("n") || next.equals("N") || next.equals("ㅜ")) {
							return;
						}else if(next.equals("y") || next.equals("Y") || next.equals("ㅛ")){
							System.out.println(util.getRPad("",60,"="));
							System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
							System.out.println(util.getRPad("",60,"-"));
						}
					}
				}
				System.out.println(util.getRPad("",60,"="));
				do {
					System.out.print("Car_id 입력(xx_xxx) [입력취소:0] : ");
					car_id = sc.next();
					if(car_id.equals("0")) {
						return;
					}
					arr = i_dao.getCarInfo_List("car_id", car_id);
					if(arr.size()==0) {
						System.out.println(" === 렌트불가능 차량이거나 없는 차량입니다. === ");
					}else {
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
						do {
							System.out.print("렌트가능 차량입니다. 렌트하겠습니까? => 예[y] 아니오[n] : ");
							select = sc.next();
							if(select.equals("Y") || select.equals("y") || select.equals("ㅛ")){
								
							}else if (select.equals("n") || select.equals("N") || select.equals("ㅜ")){
								return;
							}else {
								System.out.println(" ===== 잘못된 입력값 입니다. =====");
							}
						}while(!select.equals("Y") && !select.equals("y") && !select.equals("ㅛ"));
					}
				}while(arr.size() == 0);
									
				today = util.getToday();
				do {
					System.out.print("렌트날짜 입력 [yyyymmdd] : ");
					rent_start_date = sc.next();
					result = util.checkDate(rent_start_date);
					if(!result) {
						System.out.println(" 유효하지 않는 날짜 형식 [yyyymmdd] ");
					} else {
						if(Integer.parseInt(rent_start_date) < Integer.parseInt(today)) {
							System.out.println(" 이전 날짜는 등록이 안됩니다.");
						}
					}					
				}while(!result || Integer.parseInt(rent_start_date) < Integer.parseInt(today));
				
				String rent_id = i_dao.getMaxCarId2();
				
//				String query = " insert into A02_TRACK2_CARRENT "+
//				       		   " (rent_id, car_id, member_id, rent_start_date) "+ 
//					   	       " values('"+rent_id+"', '"+car_id+"', '"+member_id+"', '"+rent_start_date+"') ";
				
//				int res = i_dao.insertCarRent(query);
//				car_id = arr.get(0).getCar_id();
//				query 	= " update A02_TRACK2_CARINFO "+
//						  " set status = 'n' "+
//						  " where car_id ='"+car_id+"' ";
//				int res2 = i_dao.insertCarRent(query);
				String query = " insert into A02_TRACK2_CARRENT "+
			       		   " (rent_id, car_id, member_id, rent_start_date) "+ 
				   	       " values('"+rent_id+"', '"+car_id+"', '"+member_id+"', '"+rent_start_date+"') ";
				
				String queryUpdate = " update A02_TRACK2_CARINFO "+
						  	  " set status = 'n' "+
						  	  " where car_id ='"+car_id+"' ";
				int res = i_dao.insertCarRent_commit(query, queryUpdate); //insert, update 동시에 
				if(res == 1) {
					System.out.println("렌트 성공, 수정 완료");
					return;
				}else {
					System.out.println("렌트 실패, 수정 실패");
				}
//				if(res == 1) {
//					int res2 = i_dao.insertCarRent(query);
//					System.out.println("렌트 성공, 수정 완료");
//					return;
//				}else {
//					System.out.println("렌트 실패, 수정 실패");
//				}
			}else if(gubun == 2) {
				String query = " select a.rent_id, c.name, c.id, substr(b.car_name,0,3), a.car_id, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
						       " decode(a.rent_return_date,null,'[렌트취소가능]',to_char(a.rent_return_date,'yyyy-mm-dd')) "+
						       " from A02_TRACK2_CARRENT a, A02_TRACK2_CARINFO b, A02_TRACK2_MEMBER c "+ 
						       " where a.car_id = b.car_id and a.rent_return_date is null and a.member_id = c.id ";
				arr2 = i_dao.getCarRent_List(query);
				System.out.println(util.getRPad("",70,"="));
				System.out.println("rent_id\t\t성명\t\t차량명\t\t렌트일자\t\t반납일자\t");
				System.out.println(util.getRPad("",70,"-"));
				for(int k=0; k<arr2.size(); k++) {
					System.out.print(arr2.get(k).getRent_id()+"\t");
					System.out.print(arr2.get(k).getName()+"["+arr2.get(k).getId()+"]"+"\t");
					System.out.print(arr2.get(k).getCar_name()+"["+arr2.get(k).getCar_id()+"]"+"\t");
					System.out.print(arr2.get(k).getRent_start_date()+"\t");
					System.out.print(arr2.get(k).getRent_return_date()+"\n");
				}
				System.out.println(util.getRPad("",70,"="));
				CarRent2_DTO dto = null;
				String rent_id = "";
				do {
					System.out.print("렌트취소하실 렌트아이디를 입력하세요[xxxx_xxxx] : ");
					rent_id = sc.next();
					String query1 = query + " and a.rent_id = '"+rent_id+"' ";
					dto = i_dao.getCarInfo(query1);
					
					if(dto == null) {
						System.out.println("검색하신 내용은 없습니다.");
					}else {
						System.out.println(util.getRPad("",70,"="));
						System.out.println("rent_id\t\t성명\t\t차량명\t\t렌트일자\t\t반납일자\t");
						System.out.println(util.getRPad("",70,"-"));
						System.out.print(dto.getRent_id()+"\t");
						System.out.print(dto.getName()+"["+dto.getId()+"]"+"\t");
						System.out.print(dto.getCar_name()+"["+dto.getCar_id()+"]"+"\t");
						System.out.print(dto.getRent_start_date()+"\t");
						System.out.print(dto.getRent_return_date()+"\n");
					}
				}while(dto == null);
					System.out.println(util.getRPad("",70,"="));
					String sYN = "";
					do {
						System.out.print("이 렌트를 취소하시겠습니끼? 예[y] 아니오[n] : ");
						sYN = sc.next();
						if(sYN.equals("y") || sYN.equals("Y") || sYN.equals("ㅛ")) {
							query = " delete from A02_TRACK2_CARRENT where rent_id = '"+rent_id+"' ";
							int res = i_dao.deleteCar(query);
							car_id = dto.getCar_id();
							String queryUpdate = " update A02_TRACK2_CARINFO "+
									  	  " set status = 'y' "+
									  	  " where car_id = '"+car_id+"' ";
							res = i_dao.insertCarRent_commit(query, queryUpdate);
							if(res == 1) {
								System.out.println("렌트 취소 성공, 수정 완료");
								return;
							}else {
								System.out.println("렌트 취소 실패, 수정 실패");
							}
						}else if(sYN.equals("n") || sYN.equals("N") || sYN.equals("ㅜ")) {
							System.out.println("취소되었으므로 메인화면으로 돌아갑니다.");
							return;
						}
					}while(!sYN.equals("y") && !sYN.equals("Y") && !sYN.equals("ㅛ"));
			}else if(gubun == 0) {
				System.out.println("메인화면으로 돌아갑니다.");
				return;
			}
		}while(gubun == 1 || gubun == 2 || gubun != 0);
	}
}
