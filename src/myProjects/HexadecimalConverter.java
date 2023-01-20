package myProjects;
import javax.swing.JOptionPane;

public class HexadecimalConverter {
	public static void main(String args[]) {
		new HexadecimalConverter();
	}
	
	HexadecimalConverter(){
		System.out.println(convertToHex(Integer.parseInt(JOptionPane.showInputDialog("Input a decimal number."))));
		/*
		for(int i=0; i<=1024; i++) {
			System.out.println(convertToHex(i));
		}
		*/
	}
	
	String convertToHex(int num) {
		String hexNum = "";
		int numPlaces = 1;
		int placeValue = 1;
		
		while(num > (16 * placeValue) - 1) {
			numPlaces++;
			placeValue = (int) Math.pow(16, numPlaces-1);
		}
		
		for(int i=numPlaces; i>0; i--) {
			placeValue = (int) Math.pow(16, i-1);
			for(int j=1; j<=16; j++) {
				if(j * placeValue > num) {
					hexNum = appendToHexNum(j-1, hexNum);
					num -= (j-1) * placeValue;
					break;
				}
			}
		}
		
		return hexNum;
	}
	
	String appendToHexNum(int value, String hexNum) {
		switch(value) {
		case 10:
			hexNum += 'A';
			break;
		case 11:
			hexNum += 'B';
			break;
		case 12:
			hexNum += 'C';
			break;
		case 13:
			hexNum += 'D';
			break;
		case 14:
			hexNum += 'E';
			break;
		case 15:
			hexNum += 'F';
			break;
		default:
			hexNum += value;
		}
		return hexNum;
	}
}
