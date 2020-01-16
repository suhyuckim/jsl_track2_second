package 연습;

public class BreakSub {
	void test() {
		
		for(int k=0; k<3; k++) {
			for(int j=0; j<5; j++) {
				if(j == 3) return;
				System.out.println("j : "+j);
			}	
			System.out.println("k : "+k);
		}
		System.out.println(" test() ");
	}
}