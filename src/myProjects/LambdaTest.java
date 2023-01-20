package myProjects;

public class LambdaTest {
	public static void main(String[] args) {
		printAdditionResult((a, b) -> a + b, 5, 6);
	}
	
	public static void printAdditionResult(Addition add, int a, int b) {
		System.out.println(add.add(a, b));
	}
}

interface Addition {
	int add(int a, int b);
}
