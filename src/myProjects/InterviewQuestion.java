package myProjects;

import java.util.Random;

public class InterviewQuestion {
	public static void main(String[] args) {
		test();
	}

	// Given (x, y)
	// Possible Operations: (x + y, y) and (x, x + y)
	// Return true if (a, b) can turn into (c, d), return false otherwise
//	public static boolean isitpossible(int a, int b, int c, int d) {
//		if (a == c && b == d) {
//			System.out.println("c = " + c + ", d = " + d);
//			return true;
//		}
//		if (c < a || d < b) {
//			return false;
//		}
//		boolean result = isitpossible(a, b, c - d, d) || isitpossible(a, b, c, d - c);
//		if (result) {
//			System.out.println("c = " + c + ", d = " + d);
//		}
//		return result;
//	}
	
	public static boolean isitpossible(int a, int b, int c, int d) {
		if (a == c && b == d) {
			System.out.println("a = " + a + ", b = " + b);
			return true;
		}
		if (a > c || b > d) {
			return false;
		}
		boolean result = isitpossible(a + b, b, c, d) || isitpossible(a, b + a, c, d);
		if (result) {
			System.out.println("a = " + a + ", b = " + b);
		}
		return result;
	}

	public static void test() {
		for (int iter = 0; iter < 100; iter++) {
			Random r = new Random();
			int a = r.nextInt(101); // 0 - 100
			int b = r.nextInt(101); // 0 - 100
			int c = a;
			int d = b;
			int numAdds = r.nextInt(20) + 1; // 1 - 20
			for (int i = 0; i < numAdds; i++) {
				if (r.nextInt(2) == 0) {
					c += d;
				} else {
					d += c;
				}
			}
			System.out.println("-- " + iter + " ---------------------------------");
			System.out.println(a + ", " + b +", " + c + ", " + d);
			System.out.println(isitpossible(a, b, c, d));
			System.out.println((a - 1) + ", " + (b + 1) +", " + c + ", " + d);
			System.out.println(isitpossible(a + 1, b + 1, c, d));
		}
	}
	
}
