package miscProjects;

import java.io.File;

public class CsvReaderWriter {
	
	public static void main(String args[]) {
		new CsvReaderWriter().reader();
	}
	
	public void reader() {
		File csvFile = new File("src/miscProjects/Resources/Eevees.csv");
		if(csvFile.isFile()) {
			System.out.println("File Detected");
		} else {
			System.out.println("No File Detected");
		}
	}
}
