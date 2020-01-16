package main;
import java.util.Scanner;

import InterCommon.InterCarRent;
import car.CarInfo;
import member.Member;
import rent.CarHistory;
import rent.CarRent;
import rent.CarReturn;
import rent.MemberHistory;

public class CarRentMain  {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String gubun = "";
		do {
			System.out.println("* 회원관리:[1] ");
			System.out.println("* 차량관리:[2] ");
			System.out.println("* 차량렌트:[3] ");
			System.out.println("* 차량반납:[4] ");
			System.out.println("* 차량별 렌트조회:[5] ");
			System.out.println("* 회원별렌트조회:[6] ");
			System.out.println("* 시스템 종료:[0] ");
			System.out.print("* 작업선택? => ");
			
			gubun = sc.next();
			
			if(gubun.equals("1")) { //회원관리
				InterCarRent member = new Member();
				member.proc();
			}else if(gubun.equals("2")) { //차량관리
				InterCarRent carinfo = new CarInfo();
				carinfo.proc();
			}else if(gubun.equals("3")) { //차량렌트
				InterCarRent rent = new CarRent();
				rent.proc();
			}else if(gubun.equals("4")) { //차량반납
				InterCarRent ret = new CarReturn();
				ret.proc();
			}else if(gubun.equals("5")) { //차량별 렌트조회
				InterCarRent chist = new CarHistory();
				chist.proc();
			}else if(gubun.equals("6")) { //회원별 렌트조회
				InterCarRent member = new MemberHistory();
				member.proc();
			}else if(gubun.equals("0")) { //시스템 종료
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		}while(!gubun.equals("1") || !gubun.equals("2") || !gubun.equals("3") || !gubun.equals("4") 
				|| !gubun.equals("5") || !gubun.equals("6") || !gubun.equals("0"));
	}
}
