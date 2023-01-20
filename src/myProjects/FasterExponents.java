package myProjects;

public class FasterExponents {
	public static void main(String[] args) {
		new FasterExponents();
	}
	
	FasterExponents(){
		test(2, 6);
		test(3, 7);
		test(5, 14);
		test(1, 1000000000);
	}
	
	void test(int num, int exponent) {
		findExponent(num, exponent);
		findExponentFaster(num, exponent);
	}
	
	void findExponent(int num, int exponent) {
		int iterations = 0;
		long x = 1;
		
		for(int i=0; i<exponent; i++) {
			x *= num;
			iterations++;
		}
		System.out.println("Result: " + x + "    Iterations: " + iterations);
	}
	
	void findExponentFaster(int num, int exponent) {
		int iterations = 0;
		int prefix = 1;
		long x = num;
		
		while(exponent != 1) {
			if(exponent % 2 == 0) {
				x *= x;
			}
			else {
				prefix *= x;
				x *= x;
			}
			exponent /= 2;
			iterations++;
		}
		System.out.println("Result: " + (x * prefix) + "    Iterations: " + iterations);
	}
}
