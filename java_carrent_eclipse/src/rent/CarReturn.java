package rent;
import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarInfo_DAO;
import dao.CarRent_DAO;
import dao.CarReturn_DAO;
import dao.Member_DAO;
import dto.CarInfo_DTO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class CarReturn implements InterCarRent {
	public void proc() {
		Scanner sc          = new Scanner(System.in);
		Member_DAO dao      = new Member_DAO();
		CarInfo_DAO c_dao   = new CarInfo_DAO();
		CarRent_DAO i_dao   = new CarRent_DAO();
		CarReturn_DAO r_dao = new CarReturn_DAO();
		CommonUtil util     = new CommonUtil();
		ArrayList<Member_DTO> dtos     = new ArrayList<Member_DTO>();
		ArrayList<CarInfo_DTO> arr     = new ArrayList<CarInfo_DTO>();
		ArrayList<CarRent2_DTO> arr2   = new ArrayList<CarRent2_DTO>();
		
		String gubun = "";
		String query = "";
		String car_id = "";
		String rent_id = "";
		String rent_return_date = "";
		int driving_km = 0;
		int driving_total_km = 0;
		int total_km = 0;
		CarRent2_DTO dto = null;
		
		System.out.print("반납[1] 반납취소[2] 메인으로[0] : ");
		gubun = sc.next();
		if(gubun.equals("1")) {
				query = " select a.rent_id, c.name, c.id, substr(b.car_name,0,3), a.car_id, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
					    " decode(a.rent_return_date,null,'[반납가능]',to_char(a.rent_return_date,'yyyy-mm-dd')) "+
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
				rent_id = "";
				
				do {
					System.out.print("반납할 rent_id를 입력하세요[xxxx_xxxx] : ");
					rent_id = sc.next();
					query = " select a.rent_id, c.name, c.id, substr(b.car_name,0,3), a.car_id, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
					        " decode(a.rent_return_date,null,'[반납가능]',to_char(a.rent_return_date,'yyyy-mm-dd')), a.driving_km, b.driving_total_km "+
					        " from A02_TRACK2_CARRENT a, A02_TRACK2_CARINFO b, A02_TRACK2_MEMBER c "+ 
					        " where a.car_id = b.car_id and a.rent_return_date is null and a.member_id = c.id ";
					String query1 = query + " and a.rent_id = '"+rent_id+"' ";
					dto = r_dao.getCarInfo_2(query1);
					if(dto == null) {
						System.out.println("검색하신 내용이 없습니다.");
					}else {
						System.out.println(util.getRPad("",70,"="));
						System.out.println("rent_id\t\t성명\t\t차량명\t\t렌트일자\t\t반납일자[렌트상태]\t");
						System.out.println(util.getRPad("",70,"-"));
						System.out.print(dto.getRent_id()+"\t");
						System.out.print(dto.getName()+"["+dto.getId()+"]"+"\t");
						System.out.print(dto.getCar_name()+"["+dto.getCar_id()+"]"+"\t");
						System.out.print(dto.getRent_start_date()+"\t"); 
						System.out.print(dto.getRent_return_date()+"\n");
					}
					System.out.println(util.getRPad("",70,"="));
				}while(dto == null);
				
				String sYN = "";
				String today = util.getToday();
				int res = 0;
				String a = dto.getRent_start_date();
				a = a.replaceAll("-", "");
								
				do {
					System.out.print("반납하시겠습니까? 예[y], 아니오[n] : ");
					sYN = sc.next();
					if(sYN.equals("y") || sYN.equals("Y") || sYN.equals("ㅛ")) {
						System.out.print("직접입력[1], 자동입력[2] : ");
						String select = sc.next();
						if(select.equals("1")) {
							boolean result = true;
							do {	
								System.out.print(" 반납날짜 입력[yyyymmdd] : ");
								rent_return_date = sc.next();
								result = util.checkDate(rent_return_date);
								if(!result) {
									System.out.println(" 재입력(yyyymmdd) ");
								}else {
									if(Integer.parseInt(today) > Integer.parseInt(rent_return_date)){
										System.out.println("오늘 이후로 등록");
									}else if(Integer.parseInt(rent_return_date) < Integer.parseInt(a)) {
										System.out.println("렌트 이전날짜 입력 불가능");	
									}
								}															
							}while(!result || Integer.parseInt(rent_return_date) < Integer.parseInt(today)  
									|| Integer.parseInt(rent_return_date) < Integer.parseInt(a));
							System.out.print("주행거리 입력(km) : ");
							driving_km = sc.nextInt();							
						}else if(select.equals("2")) {
							rent_return_date = util.getToday();
							System.out.print("주행거리 입력(km) : ");
							driving_km = sc.nextInt();
						}
						query = " update A02_TRACK2_CARRENT "+ 
								" set rent_return_date = '"+rent_return_date+"', driving_km = '"+driving_km+"' "+
								" where rent_return_date is null and rent_id = '"+rent_id+"' ";
														
					    String queryUpdate 	= " update A02_TRACK2_CARINFO "+
							      			  " set status = 'y', driving_total_km = driving_total_km + "+driving_km+" "+
							      			  " where car_id = '"+dto.getCar_id()+"' ";
					   res = r_dao.CarReturnDate_Update(query, queryUpdate);		
					   		
					   if(res == 1) {
							System.out.println("렌트 반납  성공, 수정 완료");
							return;
						}else {
							System.out.println("렌트 반납  실패, 수정 실패");
						}
					}else if(sYN.equals("n") || sYN.equals("N") || sYN.equals("ㅜ")) {
						System.out.println(" === 취소되었으므로 메인화면으로 돌아갑니다 === ");
						return;
					}
				}while(!sYN.equals("y") && !sYN.equals("Y") && !sYN.equals("ㅛ"));
		}else if(gubun.equals("2")) {
			query = " select a.rent_id, c.name, a.member_id, substr(b.car_name,0,3), a.car_id, to_char(a.rent_start_date,'yyyy-mm-dd'), "+
					" to_char(a.rent_return_date,'yyyy-mm-dd'), a.driving_km, b.driving_total_km "+
					" from A02_TRACK2_CARRENT a, A02_TRACK2_CARINFO b, a02_track2_member c "+
					" where rent_id in (select max(rent_id) from A02_TRACK2_CARRENT group by car_id) "+
					" and a.car_id = b.car_id and a.member_id = c.id and a.rent_return_date is not null and b.status = 'y' ";
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
			do {
				System.out.print("반납취소할 rent_id를 입력하세요 [돌아가기:0] : ");
				rent_id = sc.next();
				if(rent_id.equals("0")) {
					System.out.println("반납취소하지 않고 메인으로 돌아갑니다.");
					return;
				}
				String query1 = query + " and a.rent_id = '"+rent_id+"' "; 
				dto = r_dao.getCarInfo_2(query1);				
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
					System.out.println(util.getRPad("",70,"="));
				}	
			}while(dto == null);				
				total_km = dto.getDriving_total_km();
				String result = dto.getDriving_km2();
				driving_total_km = (total_km-Integer.parseInt(result));
				query = " update A02_TRACK2_CARRENT "+ 
						" set rent_return_date = null, driving_km = 0 "+
						" where rent_id = '"+rent_id+"' ";
				
				car_id = dto.getCar_id();
				String queryUpdate 	= " update A02_TRACK2_CARINFO "+
						  				" set status = 'n', driving_total_km = "+driving_total_km+" "+
						  				" where car_id = '"+car_id+"' ";
//				
//				query =  " update A02_TRACK2_CARRENT "+ 
//						 " set rent_return_date = null, driving_km = 0 "+
//						 " where rent_id = '"+rent_id+"' ";
//			
//			    String queryUpdate 	= " update A02_TRACK2_CARINFO "+
//					      			  " set status = 'n', driving_total_km = driving_total_km - "+dto.getDriving_km()+" "+
//					      			  " where car_id = '"+dto.getCar_id()+"' ";
//						   
				int res = r_dao.CarReturnDate_Update(query, queryUpdate);
				if(res == 1) {
					System.out.println("렌트 반납 취소 성공, 수정 완료");
					return;
				}else {
					System.out.println("렌트 반납 취소 실패, 수정 실패");
				}
			}

	}
}