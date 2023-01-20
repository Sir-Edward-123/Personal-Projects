package miscProjects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DigitalTimer implements ActionListener {
	public static void main(String[] args) {
		new DigitalTimer();
	}
	
	JFrame frame;
	JPanel panel;
	JLabel timer;
	Timer secondTimer = new Timer(1000, this);
	
	int hour;
	int minute;
	int second;
	boolean militaryTime;
	
	DigitalTimer(){
		int selection = JOptionPane.showConfirmDialog(null, "Use military time?");
		if(selection == 0) {
			militaryTime = true;
		} else if(selection == 1) {
			militaryTime = false;
		} else {
			System.exit(0);
		}
		
		setTime(Integer.parseInt(JOptionPane.showInputDialog("Input Hour")), 
				Integer.parseInt(JOptionPane.showInputDialog("Input Minute")), 
				Integer.parseInt(JOptionPane.showInputDialog("Input Second")));
		
		frame = new JFrame();
		panel = new JPanel();
		timer = new JLabel();
		panel.add(timer);
		frame.add(panel);
		timer.setText(timeToString());
		frame.pack();
		secondTimer.start();
		frame.setVisible(true);
	}
	
	void setTime(int h, int m, int s){
		setHour(h);
		setMinute(m);
		setSecond(s);
	}
	
	void setHour(int h) {
		if(!militaryTime && (h < 1 || h > 12)) {
			hour = 1;
		} else if(militaryTime && (h < 0 || h > 23)){
			hour = 0;
		} else {
			hour = h;
		}
	}
	
	void setMinute(int m) {
		if(m < 0 || m > 59) {
			minute = 0;
		} else {
			minute = m;
		}
	}
	
	void setSecond(int s) {
		if(s < 0 || s > 59) {
			second = 0;
		} else {
			second = s;
		}
	}
	
	int getHour() {
		return hour;
	}
	
	int getMinute() {
		return minute;
	}
	
	int getSecond() {
		return second;
	}
	
	void nextHour() {
		setHour(getHour() + 1);
	}
	
	void nextMinute() {
		if(getMinute() + 1 > 59) {
			nextHour();
		}
		setMinute(getMinute() + 1);
	}
	
	void nextSecond() {
		if(getSecond() + 1 > 59) {
			nextMinute();
		}
		setSecond(getSecond() + 1);
	}
	
	String timeToString() {
		String time = "";
		if(hour < 10) {
			time = "0" + getHour() + ":";
		} else {
			time = getHour() + ":";
		}
		
		if(minute < 10) {
			time += "0" + getMinute() + ":";
		} else {
			time += getMinute() + ":";
		}
		
		if(second < 10) {
			time += "0" + getSecond();
		} else {
			time += getSecond();
		}
		
		return time;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == secondTimer) {
			nextSecond();
			timer.setText(timeToString());
		}
	}
}
