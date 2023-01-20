package myProjects.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Minesweeper implements ActionListener, MouseListener{
	private JFrame window;
	private JPanel panel;
	private Grid grid;
	private JLabel flagCountLabel;
	private JLabel timeLabel;
	private JButton restartButton;
	private Timer gameTimer = new Timer(1000, this);
	private int numSecs = 0;
	
	public void setup() {
		window = new JFrame("Minesweeper");
		panel = new JPanel();
		grid = new Grid();
		flagCountLabel = new JLabel();
		timeLabel = new JLabel();
		restartButton = new JButton();
		
		window.add(panel);
		panel.add(flagCountLabel);
		panel.add(timeLabel);
		panel.add(grid);
		panel.add(restartButton);
		grid.addMouseListener(this);
		restartButton.addActionListener(this);
		
		flagCountLabel.setText(Integer.toString(99));
		timeLabel.setText("000");
		restartButton.setText("Restart");
		
		grid.repaint();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		JOptionPane.showMessageDialog(null, 
				"Welcome to Minesweeper!\n"
				+ "If it's your first time playing, here's the rules:\n"
				+ "Your goal is to reveal all of the squares that aren't mined. You can click on a square to reveal it.\n"
				+ "However, be careful! If you left-click on a square that is mined, you will lose!\n"
				+ "The numbers on revealed sqaures tell you how many mines are in the surrounding squares.\n"
				+ "If you think a square is mined, right-click on it to mark it. Marked squares will turn a lime color, and cannot be revealed. You can right-click again to unmark a square.\n"
				+ "If the number of marked squares surrounding a square is equal to the number on the square, you can middle-click to reveal all of the surrounding, unmarked squares.\n"
				+ "However, if any of the squares are improperly marked, you will lose, since at least one mine will be revealed.\n"
				+ "If you lose, all of the mines on the grid will be shown by red squares. Any improperly marked squares will be red-orange, and properly marked squares will remain lime.\n"
				+ "Have fun!");			
	}
	
	public void restart(){
		panel.remove(grid);
		panel.remove(restartButton);
		
		grid = new Grid();
		grid.addMouseListener(this);
		flagCountLabel.setText(Integer.toString(99));
		timeLabel.setText("000");
		
		panel.add(grid);
		panel.add(restartButton);
		numSecs = 0;
		
		grid.repaint();
		window.pack();
	}
	
	public void setTimeLabel() {
		if(numSecs < 10) {
			timeLabel.setText("00" + numSecs);
		} else if(numSecs < 100) {
			timeLabel.setText("0" + numSecs);
		} else {
			timeLabel.setText(Integer.toString(numSecs));
		}
	}
	
	public void setFlagLabel() {
		if(99 - grid.getNumFlags() >= 10) {
			flagCountLabel.setText(Integer.toString(99 - grid.getNumFlags()));
		} else if(99 - grid.getNumFlags() >= 0){
			flagCountLabel.setText("0" + (99 - grid.getNumFlags()));
		} else {
			flagCountLabel.setText("00");
		}
	}
	
	public static void main(String[] args) {
		new Minesweeper().setup();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!grid.hasWon() && !grid.hasLost()) {
			if(!gameTimer.isRunning()) {
				gameTimer.restart();
			}
			grid.gridSpaceClicked(e.getX(), e.getY(), e.getButton());
			grid.repaint();
			grid.checkWin();
			setFlagLabel();
			if(grid.hasLost()) {
				gameTimer.stop();
				JOptionPane.showMessageDialog(null, "You Lost!");
			} else if(grid.hasWon()){
				gameTimer.stop();
				JOptionPane.showMessageDialog(null, "You Won!");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gameTimer) {
			numSecs++;
			setTimeLabel();
		} else if(e.getSource() == restartButton) {
			restart();
		}
	}
}
