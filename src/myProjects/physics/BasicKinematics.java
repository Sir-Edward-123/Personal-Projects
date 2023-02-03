package myProjects.physics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BasicKinematics extends JPanel implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private double timeStep = 0.01; // seconds
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 700;
	private final int BOX_WIDTH = 20;
	private final int BOX_HEIGHT = 20;
	private final int PIXEL_TO_METER_RATIO = 100;
	private final double COLLISION_VELOCITY_LOSS_RATIO = 0.4;
	private boolean boxIsBeingDragged = false;
	private double xPos = 490;
	private double yPos = 0; // starts from bottom instead of top
	private double prevXPos = 490;
	private double prevYPos = 0;
	private double xVelocity = 0;
	private double yVelocity = 0;
	private final double Y_ACCELERATION = -9.81;
	
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		BasicKinematics bk = new BasicKinematics();
		
		window.setSize(bk.WINDOW_WIDTH, bk.WINDOW_HEIGHT);
		window.setResizable(false);
		window.add(bk);
		bk.setup();
		window.pack();
		window.setVisible(true);
	}
	
	void setup() {
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.addMouseListener(this);
		repaint();
		timer = new Timer((int)(timeStep * 1000), this);
		timer.start();
	}
	
	void updateKinematics() {
		if(xPos < 0) {
			xVelocity = -xVelocity * COLLISION_VELOCITY_LOSS_RATIO;
			yVelocity *= COLLISION_VELOCITY_LOSS_RATIO;
			xPos = 0;
		} else if(xPos + BOX_WIDTH > WINDOW_WIDTH){
			xVelocity = -xVelocity * COLLISION_VELOCITY_LOSS_RATIO;
			yVelocity *= COLLISION_VELOCITY_LOSS_RATIO;
			xPos = WINDOW_WIDTH - BOX_WIDTH;
		} else {
			xPos += xVelocity * timeStep * PIXEL_TO_METER_RATIO;
		}
		
		if(yPos < 0) {
			xVelocity *= COLLISION_VELOCITY_LOSS_RATIO;
			yVelocity = -yVelocity * COLLISION_VELOCITY_LOSS_RATIO;
			yPos = 0;
		} else if(yPos + BOX_WIDTH > WINDOW_HEIGHT) {
			xVelocity *= COLLISION_VELOCITY_LOSS_RATIO;
			yVelocity = -yVelocity * COLLISION_VELOCITY_LOSS_RATIO;
			yPos = WINDOW_HEIGHT - BOX_HEIGHT;
		} else {
			yVelocity += Y_ACCELERATION * timeStep;
			yPos += yVelocity * timeStep * PIXEL_TO_METER_RATIO;
		}
		
		if(Math.abs(xVelocity) < (0.5 / PIXEL_TO_METER_RATIO)) {
			xVelocity = 0;
		}
		if(Math.abs(yVelocity) < (0.5 / PIXEL_TO_METER_RATIO)) {
			yVelocity = 0;
		}
	}
	
	// Returns actual y position of a given point
	double calculateYPosActual(double relativeYPos) {
		return -(relativeYPos - WINDOW_HEIGHT);
	}
	
	// Returns actual y position of a box based on a given reference point
	double calculateBoxYActual(double relativeBoxY) {
		return calculateYPosActual(relativeBoxY) - BOX_HEIGHT;
	}
	
	// Returns relative y position of a given point
	double calculateYPosRelative(double actualYPos) {
		return -(actualYPos - WINDOW_HEIGHT);
	}
	
	// Returns relative y position of a box based on a given reference point
	double calculateBoxYRelative(double actualBoxY) {
		return calculateYPosRelative(actualBoxY) + BOX_HEIGHT;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		g.setColor(Color.blue);
		g.fillRect((int)xPos, (int)calculateBoxYActual(yPos), BOX_WIDTH, BOX_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == timer) {
			if(boxIsBeingDragged) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(p, this);
				double mouseX = p.getX();
				double mouseY = p.getY();
				
				xPos = mouseX - BOX_WIDTH / 2;
				yPos = calculateYPosRelative(mouseY + BOX_HEIGHT / 2);
				
				yVelocity = ((yPos - prevYPos) / PIXEL_TO_METER_RATIO) / timeStep;
				xVelocity = ((xPos - prevXPos) / PIXEL_TO_METER_RATIO) / timeStep;
				
				prevYPos = yPos;
				prevXPos = xPos;
			} else {
				updateKinematics();
			}
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(p, this);
		double mouseX = p.getX();
		double mouseY = p.getY();
		if(mouseX >= xPos && mouseX <= xPos + BOX_WIDTH && mouseY >= calculateBoxYActual(yPos) && mouseY <= calculateBoxYActual(yPos) + BOX_HEIGHT) {
			boxIsBeingDragged = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		boxIsBeingDragged = false;
	}
	
}
