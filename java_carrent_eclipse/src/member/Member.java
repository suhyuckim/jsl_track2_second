package member;
import java.util.*;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.Member_DAO;
import dto.CarRent2_DTO;
import dto.Member_DTO;

public class Member implements InterCarRent {
	public void proc() {
		Scanner sc = new Scanner(System.in);
		Member_DAO dao = new Member_DAO();
		CommonUtil util = new CommonUtil();
		ArrayList<CarRent2_DTO> arr = new ArrayList<CarRent2_DTO>();
		
		int gubun = 0;
		do {
			System.out.print("회원검색[1], 등록[2], 수정[3], 삭제[4], [메인으로:0] => ");
			gubun = sc.nextInt();
			ArrayList<Member_DTO> dtos = null;
			if(gubun == 1) { //회원검색
				int searchGubun = 0;
				do {
					System.out.print(" 이름검색[1], 부서검색[2], 전체출력[9], [이전메뉴:0] => ");
					searchGubun = sc.nextInt();
					if(searchGubun == 0) break;
					if(searchGubun == 1) {
						System.out.print(" 이름? => ");
						String name = sc.next();
						dtos = dao.getMemberList("name", name);
					}else if(searchGubun == 2) {
						System.out.print(" 부서검색 영업[10], 인사[20], 기획[30], 총무[40]? => ");
						String dept = sc.next();
						dtos = dao.getMemberList("dept", dept);
					}else if(searchGubun == 9) {
						dtos = dao.getMemberList("all", "");
					}
					if(dtos.size() == 0) {
						System.out.println("검색 내용이 없습니다.");
					} else {
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
				}while(searchGubun == 1 || searchGubun == 2 || searchGubun == 9);
			} else if(gubun == 2) { //등록
				String name = "";
				do {
					System.out.print(" 성명? => ");
					name = sc.next();
					if(name.length() > 3) System.out.println("성명 3자이내");
				}while(name.length() > 3);
				int age = 0;
				do {
					System.out.print(" 나이? => ");
					age = sc.nextInt();
					if(age > 100 || age < 1) System.out.println("나이 1~99살까지 입력");
				}while(age > 100 || age < 1);
				String dept_no = "";
				do {
					System.out.print(" 부서등록 영업[10], 인사[20], 기획[30], 총무[40]? => ");
					dept_no = sc.next();
					if(!dept_no.equals("10")&&!dept_no.equals("20")
							&&!dept_no.equals("30")&&!dept_no.equals("40")) {
						System.out.println("부서코드 재입력");
					}
				}while(!dept_no.equals("10")&&!dept_no.equals("20")
						&&!dept_no.equals("30")&&!dept_no.equals("40"));
				String rank_no = "";
				do {
					System.out.print(" 직위등록 사원[10], 대리[20], 과장[30], 부장[40]? => ");
					rank_no = sc.next();
					if(!rank_no.equals("10")&&!rank_no.equals("20")
							&&!rank_no.equals("30")&&!rank_no.equals("40")) {
						System.out.println("직위코드 재입력");
					}
				}while(!rank_no.equals("10")&&!rank_no.equals("20")
						&&!rank_no.equals("30")&&!rank_no.equals("40"));
				String address = "";
				do {
					System.out.print(" 주소? => ");
					address = sc.next();
					if(address.length() > 5) System.out.println("주소 5자 이내로 입력");
				}while(address.length() > 5);
				String reg_date = "";
				boolean result = true;
				do {
					System.out.print(" 등록일(YYYY-MM-DD) => ");
					reg_date = sc.next();
					result = util.checkDate(reg_date);
					if(!result) System.out.println("날짜(YYYY-MM-DD) 다시입력!");
				}while(!result);
				
				String id = dao.getMaxId();
//				int res = dao.insertMember(id,name,age,dept_no,rank_no,address,reg_date); //멤버등록1
//				Member_DTO dto = new Member_DTO(id,name,age,dept_no,rank_no,address,reg_date); //멤버등록2
//				int res = dao.insertMember_DTO(dto); //멤버등록2
				int res = dao.insertMember_2(id,name,age,dept_no,rank_no,address,reg_date); //멤버등록3
				if(res == 1) {
					System.out.println("회원 정상 등록");
				} else {
					System.out.println("회원 등록 실패");
				}
			} else if(gubun == 3) { //수정
				String id = "";
				do {
					System.out.print(" 수정 ID ? [이전메뉴:0] => ");
					id = sc.next();
					if(id.equals("0")) break;
					
					Member_DTO dto = dao.getMemberInfo(id);
					if(dto == null) {
						System.out.println(" 검색 내용 없음~~~ ");
					}else {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일\t");
						System.out.println(util.getRPad("",60,"-"));
						System.out.print(dto.getName()+"\t");
						System.out.print(dto.getAge()+"\t");
						System.out.print(dto.getDept()+"\t");
						System.out.print(dto.getRank()+"\t");
						System.out.print(dto.getAddress()+"\t");
						System.out.print(dto.getReg_date()+"\n");
						System.out.println(util.getRPad("",60,"="));
						System.out.print("수정 하겠습니까? 예:y 아니오:n => ");
						String workYN = sc.next();
						if(workYN.equals("y") || workYN.equals("Y") || workYN.equals("ㅛ")) {
							String name = "";
							do {
								System.out.print(" 성명["+dto.getName()+"] => ");
								name = sc.next();
								if(name.length() > 3) System.out.println("성명 3자이내");
							}while(name.length() > 3);
							int age = 0;
							do {
								System.out.print(" 나이["+dto.getAge()+"] => ");
								age = sc.nextInt();
								if(age > 100 || age < 1) System.out.println("나이 1~99살까지 입력");
							}while(age > 100 || age < 1);
							String dept_no = "";
							do {
								System.out.print(" 부서["+dto.getDept()+"] 영업[10], 인사[20], 기획[30], 총무[40]? => ");
								dept_no = sc.next();
								if(!dept_no.equals("10")&&!dept_no.equals("20")
										&&!dept_no.equals("30")&&!dept_no.equals("40")) {
									System.out.println("부서코드 재입력");
								}
							}while(!dept_no.equals("10")&&!dept_no.equals("20")
									&&!dept_no.equals("30")&&!dept_no.equals("40"));
							String rank_no = "";
							do {
								System.out.print(" 직위["+dto.getRank()+"] 사원[10], 대리[20], 과장[30], 부장[40]? => ");
								rank_no = sc.next();
								if(!rank_no.equals("10")&&!rank_no.equals("20")
										&&!rank_no.equals("30")&&!rank_no.equals("40")) {
									System.out.println("직위코드 재입력");
								}
							}while(!rank_no.equals("10")&&!rank_no.equals("20")
									&&!rank_no.equals("30")&&!rank_no.equals("40"));
							String address = "";
							do {
								System.out.print(" 주소["+dto.getAddress()+"] => ");
								address = sc.next();
								if(address.length() > 5) System.out.println("주소 5자 이내로 입력");
							}while(address.length() > 5);
							String reg_date = "";
							boolean result = true;
							do {
								System.out.print(" 등록일["+dto.getReg_date()+"](YYYY-MM-DD) => ");
								reg_date = sc.next();
								result = util.checkDate(reg_date);
								if(!result) System.out.println("날짜(YYYY-MM-DD) 다시입력!");
							}while(!result);
							int res = dao.updateMember(id,name,age,dept_no,rank_no,address,reg_date);
							if(res == 1) {
								System.out.println("회원 정상 수정");
							} else {
								System.out.println("회원 수정 실패");
							}
						}
					}
				}while(!id.equals("0"));
			}else if(gubun == 4) { //삭제
				String id ="";
				int count = 0;
				do {	
					System.out.print("삭제할 ID를 입력하세요 [이전으로:0] => ");
					id = sc.next();
					if(id.equals("0")) break;
					count = dao.getMember_Count_delete(id);
					Member_DTO dto = dao.getMemberInfo(id);
					if(dto == null) {
						System.out.println(" 검색 내용 없음~~~ ");
					}else if(count != 0) {
						System.out.println(" 렌트 이력에 존재하는 회원입니다. ~~~ ");
					}else {
						System.out.println(util.getRPad("",60,"="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일\t");
						System.out.println(util.getRPad("",60,"-"));
						System.out.print(dto.getName()+"\t");
						System.out.print(dto.getAge()+"\t");
						System.out.print(dto.getDept()+"\t");
						System.out.print(dto.getRank()+"\t");
						System.out.print(dto.getAddress()+"\t");
						System.out.print(dto.getReg_date()+"\n");
						System.out.println(util.getRPad("",60,"="));
						System.out.print("삭제 하겠습니까? 예:y 아니오:n => ");
						String workYN = sc.next();
						if(workYN.equals("y") || workYN.equals("Y") || workYN.equals("ㅛ")) {
							int res = dao.deleteMember(id);
							if(res == 1) {
								System.out.println("회원 정상 삭제");
							} else {
								System.out.println("회원 삭제 실패");
							}
						}
					}
				}while(!id.equals("0") || count != 0);		
			} else if(gubun == 0) return;
		}while(gubun == 1 || gubun == 2 || gubun == 3 || gubun == 4);
	}
}