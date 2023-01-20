package myProjects;

import javax.swing.*;

public class Hangman {
	JFrame window;
	JPanel panel;
	JLabel wordDisplay;
	JLabel guessDisplay;
	JTextField guessInput;
	
	String[] words = new String[] {"Awesome", "Beautiful", "Cute", "Delightful", "Extraordinary"};
	
	public Hangman() {
		window = new JFrame();
		panel = new JPanel();
		wordDisplay = new JLabel();
		guessDisplay = new JLabel();
		guessInput = new JTextField();
	}
	
	void run() {
		
	}
	
	public static void main(String[] args) {
		new Hangman().run();;
	}
}
