package car;
import dao.CarInfo_DAO;
import dto.CarInfo_DTO;
import dto.Member_DTO;
import java.util.*;

import InterCommon.InterCarRent;
import common.CommonUtil;

public class CarInfo implements InterCarRent {
	public void proc() {
		Scanner sc 		= new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		CarInfo_DAO dao = new CarInfo_DAO();
		
		int gubun = 0;
		int i = 0;
		String produce2 = "";
		String car_name = "";
		String next = "";
		
		do {
			System.out.print("차량조회[1], 등록[2], 수정[3], 삭제[4], [메인으로:0] => ");
			gubun = sc.nextInt();
			ArrayList<CarInfo_DTO> dtos = null;
			if(gubun != 1 && gubun != 2 && gubun != 3 && gubun != 4 && gubun != 0) {
				System.out.println("차량조회[1]\n등록[2]\n수정[3]\n삭제[4]\n[시스템 종료:0]으로 입력하세요.");
			}
			if(gubun == 1) { //차량조회
				int searchGubun = 0;
				do {
					System.out.print("차량이름[1], 제조사[2], 전체조회[9], [이전으로:0] : ");
					searchGubun = sc.nextInt();
					if(searchGubun == 0) break;
					if(searchGubun == 1) {
						do {
							System.out.print("차량이름 [이전으로:0] : ");
							car_name = sc.next();
							if(car_name.equals("0")) break;
							if(car_name.length()>6) {
								System.out.println("차량이름은 6자리 이내로 입력해주세요. ");
							} else {
								dtos = dao.getCarInfo_List("car_name", car_name);
							}
						} while(car_name.length()>6);
					} else if(searchGubun == 2) {
						String produce  = "";
						do {
							System.out.print("제조사 기아[1], 현대[2], 삼성[3], 쌍용[4], [이전으로:0] : ");
							produce2 = sc.next();
							if(produce2.equals("0")) break;
							if(!produce2.equals("1") && !produce2.equals("2") 
									&& !produce2.equals("3") && !produce2.equals("4")) {
								System.out.println("존재하지 않는 제조사입니다.");
							}else {
								if(produce2.equals("1")) produce = "기아";
								else if(produce2.equals("2")) produce = "현대";
								else if(produce2.equals("3")) produce = "삼성";
								else if(produce2.equals("4")) produce = "쌍용";
							}
							dtos = dao.getCarInfo_List("produce", produce);
						}while(!produce2.equals("1") && !produce2.equals("2") 
								&& !produce2.equals("3") && !produce2.equals("4"));
					} else if(searchGubun == 9) {
						dtos = dao.getCarInfo_List("all", "");
					}
					if(!produce2.equals("0") && !car_name.equals("0")){
					if(dtos.size() == 0) {
						System.out.println("검색 내용이 없습니다.");
					}else { 
						i = 0;
						System.out.println(util.getRPad("",60,"="));
						System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
						System.out.println(util.getRPad("",60,"-"));
							for(int k=0; k<dtos.size(); k++) {
								System.out.print(dtos.get(k).getCar_id()+"\t");
								System.out.print(dtos.get(k).getCar_name()+"\t");
								System.out.print(dtos.get(k).getProduce()+"\t");
								System.out.print(dtos.get(k).getProduce_ym()+"\t");
//								System.out.print(dtos.get(k).getDriving_total_km()+"km"+"\t");
								System.out.print(util.getLPad(dtos.get(k).getDriving_total_km()+"km",5," ")+"\t");
								System.out.print(dtos.get(k).getStatus()+"\n");
								i++;
									if(i%5 == 0) {
										System.out.println(util.getRPad("",60,"="));
										System.out.println("다음 페이지 [y]");
										System.out.println("이전 메뉴 [n]");
										System.out.print("작 업 선 택 -> ");
										next = sc.next();
										if(next.equals("n") || next.equals("N") || next.equals("ㅜ")) {
											break;
										}else if(next.equals("y") || next.equals("Y") || next.equals("ㅛ")){
											System.out.println(util.getRPad("",60,"="));
											System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
											System.out.println(util.getRPad("",60,"-"));
										}
									} 
								}
							System.out.println(util.getRPad("",60,"="));
						}	
					}
				}while(searchGubun == 1 || searchGubun == 2 || searchGubun == 9 || searchGubun != 1 && searchGubun != 2 && searchGubun != 9);
			}else if(gubun == 2) { //등록
				do {
					System.out.print("차 이름 : ");
					car_name = sc.next();
					if(car_name.length() > 6) {
						System.out.println("차 이름은 6자 이내로 입력");
					}
				}while(car_name.length() > 6);
				String produce = "";
				do {
					System.out.print("제조사 기아[1], 현대[2], 삼성[3], 쌍용[4] : ");
					produce2 = sc.next();
					if(!produce2.equals("1") && !produce2.equals("2") && !produce2.equals("3") && !produce2.equals("4")) {
						System.out.println("제조사 기아[1], 현대[2], 삼성[3], 쌍용[4]로 입력하세요.");
					} else {
						if(produce2.equals("1")) produce = "기아";
						else if(produce2.equals("2")) produce = "현대";
						else if(produce2.equals("3")) produce = "삼성";
						else if(produce2.equals("4")) produce = "쌍용";
					}
				}while(!produce2.equals("1") && !produce2.equals("2") && !produce2.equals("3") && !produce2.equals("4"));
				String produce_ym = "";
				boolean result = true;
				String today = util.getToday();
				do {
					System.out.print("제조연식 입력(yyyymm) 조건:6자이내/숫자입력 : ");
					produce_ym = sc.next();
					result = util.checkDate2(produce_ym);
					if(!result || produce_ym.length() < 6) {
						System.out.println("제조연식 입력(yyyymm) 조건:6자이내/숫자입력로 입력하세요.");
					}
				}while(!result || produce_ym.length() < 6);
				
				String car_id = dao.getMaxCarId();
				
				String query = "";
				query = " insert into A02_TRACK2_CARINFO "+
						" (car_id, car_name, produce, produce_ym) "+
						" values ('"+car_id+"','"+car_name+"', '"+produce+"','"+produce_ym+"') ";
				int res = dao.insertCarList(query);
				if(res == 1) {
					System.out.println("차량 등록 완료");
				}else {
					System.out.println("차량 등록 실패");
				}
			}else if(gubun == 3) { //수정
				String car_id = "";
				do {
					i = 0;
					dtos = dao.getCarInfo_List("all", "");
					System.out.println(util.getRPad("",60,"="));
					System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
					System.out.println(util.getRPad("",60,"-"));
					for(int k=0; k<dtos.size(); k++) {
						System.out.print(dtos.get(k).getCar_id()+"\t");
						System.out.print(dtos.get(k).getCar_name()+"\t");
						System.out.print(dtos.get(k).getProduce()+"\t");
						System.out.print(dtos.get(k).getProduce_ym()+"\t");
						System.out.print(util.getLPad(dtos.get(k).getDriving_total_km()+"km",5," ")+"\t");
						System.out.print(dtos.get(k).getStatus()+"\n");
						i++;
						if(i%5 == 0) {
							System.out.println(util.getRPad("",60,"="));
							System.out.println("다음 페이지 [y]");
							System.out.println("이전 메뉴 [n]");
							System.out.print("작 업 선 택 -> ");
							next = sc.next();
							if(next.equals("n") || next.equals("N") || next.equals("ㅜ")) {
								break;
							}else if(next.equals("y") || next.equals("Y") || next.equals("ㅛ")){
								System.out.println(util.getRPad("",60,"="));
								System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
								System.out.println(util.getRPad("",60,"-"));
							}
						} 
					}
					System.out.println(util.getRPad("",60,"="));
					
					System.out.print("수정할 차량id(xx_xxx) [이전메뉴:0] : ");
					car_id = sc.next();
					if(car_id.equals("0")) break;
					CarInfo_DTO dto = dao.getCarInfo(car_id);
					if(dto == null) {
						System.out.println("검색 내용이 없습니다.");
					}else {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
						System.out.println(util.getRPad("",60,"-"));
						System.out.print(dto.getCar_id()+"\t");
						System.out.print(dto.getCar_name()+"\t");
						System.out.print(dto.getProduce()+"\t");
						System.out.print(dto.getProduce_ym()+"\t");
						System.out.print(util.getLPad(dto.getDriving_total_km()+"km",5," ")+"\t");
						System.out.print(dto.getStatus()+"\n");
						System.out.println(util.getRPad("",60,"="));
						System.out.print("수정 하겠습니까? 예:y 아니오:n => ");
						String workYN = sc.next();
						if(workYN.equals("y") || workYN.equals("Y") || workYN.equals("ㅛ")) {
							do {
								System.out.print(" 차량명["+dto.getCar_name()+"] => ");
								car_name = sc.next();
								if(car_name.length() > 6) System.out.println("차량명 6자이내");
							}while(car_name.length() > 6);
							String produce = "";
							do {
								System.out.print(" 제조사["+dto.getProduce()+"] 기아[1], 현대[2], 삼성[3], 쌍용[4] => ");
								produce2 = sc.next();
								if(!produce2.equals("1") && !produce2.equals("2") 
										&& !produce2.equals("3") && !produce2.equals("4")) {
									System.out.println(" 제조사 기아[1], 현대[2], 삼성[3], 쌍용[4]으로 입력하세요.");
								}else{
									if(produce2.equals("1")) produce = "기아";
									else if(produce2.equals("2")) produce = "현대";
									else if(produce2.equals("3")) produce = "삼성";
									else if(produce2.equals("4")) produce = "쌍용";
								}
							}while(!produce2.equals("1") && !produce2.equals("2") 
									&& !produce2.equals("3") && !produce2.equals("4"));
							String produce_ym = "";
							boolean result = true;
							do {
								System.out.print(" 제조사["+dto.getProduce_ym()+"] => ");
								produce_ym = sc.next();
								result = util.checkDate2(produce_ym);
								if(!result || produce_ym.length() < 6) {
									System.out.println("제조 연식 입력(yyyymm) 조건:6자이내/숫자입력로 입력하세요.");
								}
							}while(!result || produce_ym.length() < 6);
							String query = "";
							query = " update A02_TRACK2_CARINFO "+
									" set car_name = '"+car_name+"', produce = '"+produce+"', produce_ym = '"+produce_ym+"' "+
									" where car_id = '"+car_id+"' ";
							int res = dao.insertCarList(query);
							if(res == 1) {
								System.out.println("차량 수정 완료");
								break;
							}else {
								System.out.println("차량 수정 실패");
							}
						}
					}
				}while(!car_id.equals("0"));
			}else if(gubun == 4) { //삭제
				String car_id ="";
				int count = 0;
				do {	
					i = 0;
					dtos = dao.getCarInfo_List("all", "");
					System.out.println(util.getRPad("",60,"="));
					System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
					System.out.println(util.getRPad("",60,"-"));
					for(int k=0; k<dtos.size(); k++) {
						System.out.print(dtos.get(k).getCar_id()+"\t");
						System.out.print(dtos.get(k).getCar_name()+"\t");
						System.out.print(dtos.get(k).getProduce()+"\t");
						System.out.print(dtos.get(k).getProduce_ym()+"\t");
						System.out.print(util.getLPad(dtos.get(k).getDriving_total_km()+"km",5," ")+"\t");
						System.out.print(dtos.get(k).getStatus()+"\n");
						i++;
						if(i%5 == 0) {
							System.out.println(util.getRPad("",60,"="));
							System.out.println("다음 페이지 [y]");
							System.out.println("이전 메뉴 [n]");
							System.out.print("작 업 선 택 -> ");
							next = sc.next();
							if(next.equals("n") || next.equals("N") || next.equals("ㅜ")) {
								break;
							}else if(next.equals("y") || next.equals("Y") || next.equals("ㅛ")){
								System.out.println(util.getRPad("",60,"="));
								System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
								System.out.println(util.getRPad("",60,"-"));
							}
						} 
					}
					System.out.println(util.getRPad("",60,"="));
				do {	
					System.out.print("삭제할 차량ID를 입력하세요(xx_xxx) [이전메뉴:0] => ");
					car_id = sc.next();
					if(car_id.equals("0")) break;
					count = dao.getCar_Count_delete(car_id);
					CarInfo_DTO dto = dao.getCarInfo(car_id);
					if(dto == null) {
						System.out.println(" 검색 내용 없음~~~ ");
					}else if(count != 0) {
						System.out.println(" 해당하는 차량은 렌트 이력이 존재합니다. ~~~");
					}else {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("Car_id\t차량명\t제조자\t제조연식\t주행거리\t상태[렌트]\t");
						System.out.println(util.getRPad("",60,"-"));
						System.out.print(dto.getCar_id()+"\t");
						System.out.print(dto.getCar_name()+"\t");
						System.out.print(dto.getProduce()+"\t");
						System.out.print(dto.getProduce_ym()+"\t");
						System.out.print(util.getLPad(dto.getDriving_total_km()+"km",5," ")+"\t");
						System.out.print(dto.getStatus()+"\n");
						System.out.println(util.getRPad("",60,"="));
						System.out.print("삭제 하겠습니까? 예:y 아니오:n => ");
						String workYN = sc.next();
						if(workYN.equals("y") || workYN.equals("Y") || workYN.equals("ㅛ")) {
							String query = "";
							query = " delete from A02_TRACK2_CARINFO where car_id = '"+car_id+"' ";
							int res = dao.insertCarList(query);
							if(res == 1) {
								System.out.println("차량id 정상 삭제");
								break;
							} else {
								System.out.println("차량id 삭제 실패");
							}
						}
					}
				}while(count != 0);
				}while(!car_id.equals("0"));	
			} else if(gubun == 0) return;
		}while(gubun == 1 || gubun == 2 || gubun == 3 || gubun == 4 || gubun != 0);
	}
}