package myProjects;
import javax.swing.JOptionPane;

public class BinaryConverter {
	
	public static void main(String[] args) {
		System.out.println(convertToDecimal());
		System.out.println(convertToBinary());
	}
	
	static int convertToDecimal() {
		String stringNum = JOptionPane.showInputDialog("Input a binary number.");
		int numPlaces = stringNum.length();
		int placeValue = 1;
		int decimalValue = 0;
		
		for(int i = numPlaces-1; i >= 0; i--) {
			if(stringNum.charAt(i) == '0') {
				placeValue *= 2;
			}
			else if(stringNum.charAt(i) == '1') {
				decimalValue += placeValue;
				placeValue *= 2;
			}
			else {
				System.out.println("Invalid number entered.");
				return -1;
			}
		}
		
		return decimalValue;
	}
	
	static int convertToBinary() {
		int decimalNum = Integer.parseInt(JOptionPane.showInputDialog("Input an integer."));
		int numPlaces = 1;
		int placeValue; 
		String binaryValue = "";
		
		int testValue = 1;
		while(testValue < decimalNum) {
			numPlaces++;
			testValue *= 2;
		}
		
		for(int i = numPlaces-1; i >= 0; i--) {
			placeValue = (int) Math.pow(2, i);
			
			if(decimalNum >= placeValue) {
				decimalNum -= placeValue;
				binaryValue += '1';
			} else {
				binaryValue += '0';
			}
		}
		
		return Integer.parseInt(binaryValue);
	}
}
