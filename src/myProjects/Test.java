package myProjects;

public class Test {
	public static void main(String args[]) {
		trimTest();
	}
	
	static void emptyArrTest() {
		String[][] test = new String[3][3];
		System.out.println(test[0][0]);
		if(test[0][0] != null) {
			System.out.println("God why");
		} else {
			System.out.println("Bruh");
		}
	}
	
	static void trimTest() {
		String thing = "  thing  ";
		thing = thing.trim();
		System.out.println(thing);
	}
}
