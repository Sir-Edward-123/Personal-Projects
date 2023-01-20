package myProjects.ConnectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFour implements MouseListener, MouseMotionListener, ActionListener {
	JFrame window;
	JPanel panel;
	JButton resetButton;
	Grid grid;
	BoxLayout layout;
	boolean isRedTurn = true;
	boolean win = false;
	
	public static void main(String[] args) {
		new ConnectFour().setup();
	}
	
	public void setup() {
		window = new JFrame("Connect Four");
		panel = new JPanel();
		resetButton = new JButton("Reset Game");
		grid = new Grid();
		layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		
		panel.setLayout(layout);
		resetButton.addActionListener(this);
		grid.addMouseListener(this);
		grid.addMouseMotionListener(this);
		panel.add(resetButton);
		panel.add(grid);
		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		grid.repaint();
		window.pack();
		window.setVisible(true);
		
		JOptionPane.showMessageDialog(null, 
				"Rules:\n"
				+ "Turns alternate between red and yellow players, starting with red.\n"
				+ "Whichever player gets four of their pieces in a row, horizontal, vertical, or diagonal, wins.\n"
				+ "Pieces will fall down columns until they hit a piece below them or the bottom of the board.\n"
				+ "Have fun!");
	}
	
	public void reset() {
		isRedTurn = true;
		win = false;
		
		panel.remove(grid);
		grid = new Grid();
		grid.addMouseListener(this);
		grid.addMouseMotionListener(this);
		panel.add(grid);
		grid.repaint();
		window.pack();
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
		if(!win && grid.checkGridClicked(e.getX())){
			grid.updateGridClicked(e.getX(), isRedTurn);
			if(grid.checkWin(isRedTurn)) {
				win = true;
				if(isRedTurn) {
					JOptionPane.showMessageDialog(null, "Red WINS!");
				} else {
					JOptionPane.showMessageDialog(null, "Yellow WINS!");
				}
			} else if(grid.checkGridFull()) {
				win = true;
				JOptionPane.showMessageDialog(null, "It's a TIE!");
			}
			isRedTurn = !isRedTurn;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		grid.setColHighlight(e.getX());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resetButton) {
			reset();
		}
	}
	
}
