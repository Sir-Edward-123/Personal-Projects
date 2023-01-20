package myProjects.minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Grid extends JPanel{
	private static final long serialVersionUID = 1L;
	private boolean minesGenerated = false;
	
	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 320;
	private final int ROWS = 30;
	private final int COLS = 16;
	private final int PIXEL_WIDTH = 20;
	private final int PIXEL_HEIGHT = 20;
	
	private boolean hasWon = false;
	private boolean hasLost = false;
	
	private Color defaultColor = new Color(150, 150, 150);
	private Color revealedColor = new Color(200, 200, 200);
	private Color flaggedColor = new Color(230, 230, 0);
	private Color falseFlaggedColor = new Color(255, 100, 0);
	private Color mineColor = new Color(200, 0, 0);
	
	GridSpace[][] gridSpaces = new GridSpace[ROWS][COLS];
	
	public Grid() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				gridSpaces[i][j] = new GridSpace(i, j);
			}
		}
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				gridSpaces[i][j].determineSurroundingSpaces(gridSpaces);
			}
		}
		
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	}
	
	public int getNumFlags() {
		int numFlags = 0;
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(gridSpaces[i][j].isFlagged()) {
					numFlags++;
				}
			}
		}
		return numFlags;
	}
	
	public boolean hasWon() {
		return hasWon;
	}
	
	public void checkWin() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(!gridSpaces[i][j].isMine() && !gridSpaces[i][j].isRevealed()) {
					hasWon = false;
					return;
				}
			}
		}
		hasWon = true;
		finishFlags();
	}
	
	public boolean hasLost() {
		return hasLost;
	}
	
	public void generateMines(GridSpace firstClickedSpace) {
		ArrayList<GridSpace> excludedSpaces = new ArrayList<GridSpace>();
		Random rand = new Random();
		
		//First clicked space should be excluded, as well as the surrounding spaces
		excludedSpaces.add(firstClickedSpace);
		ArrayList<GridSpace> surroundingSpaces = firstClickedSpace.getSurroundingSpaces();
		for(int i = 0; i < surroundingSpaces.size(); i++) {
			excludedSpaces.add(surroundingSpaces.get(i));
		}
		
		while(excludedSpaces.size() < 100 + surroundingSpaces.size()) {
			int chosenX = rand.nextInt(ROWS);
			int chosenY = rand.nextInt(COLS);
			boolean alreadyChosen = false;
			
			for(int i = 0; i < excludedSpaces.size(); i++) {
				if(excludedSpaces.get(i).equals(gridSpaces[chosenX][chosenY])) {
					alreadyChosen = true;
					break;
				}
			}
			if(!alreadyChosen) {
				excludedSpaces.add(gridSpaces[chosenX][chosenY]);
				gridSpaces[chosenX][chosenY].setMineTrue();
			}
		}
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				gridSpaces[i][j].determineNumSurroundingMines();
			}
		}
	}
	
	public void testMineGeneration(Graphics g) {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(gridSpaces[i][j].isMine()) {
					g.setColor(Color.red);
					g.fillRect(i * PIXEL_HEIGHT, j * PIXEL_WIDTH , PIXEL_WIDTH, PIXEL_HEIGHT);
					g.setColor(Color.black);
					g.drawRect(i * PIXEL_HEIGHT, j * PIXEL_WIDTH , PIXEL_WIDTH, PIXEL_HEIGHT);
				}
			}
		}
	}
	
	public void gridSpaceClicked(int clickX, int clickY, int button) {
		GridSpace clickedSpace = gridSpaces[clickX / PIXEL_WIDTH][clickY / PIXEL_HEIGHT];
		
		if(button == 1 && !clickedSpace.isFlagged()) {
			if(!minesGenerated) {
				generateMines(clickedSpace);
				minesGenerated = true;
			}
			clickedSpace.revealSpace();
			if(clickedSpace.isMine()) {
				revealMines();
			}
		} else if(button == 2 && clickedSpace.isRevealed()) {
			clickedSpace.revealSurroundingSpaces();
			if(clickedSpace.getNumSurroundingFlags() == clickedSpace.numSurroundingMines && clickedSpace.areSurroundingUnflaggedMines()) {
				revealMines();
			}
		} else if(button == 3 && !clickedSpace.isRevealed()) {
			clickedSpace.setIsFlagged(!clickedSpace.isFlagged());
		}
	}
	
	public void revealMines() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(gridSpaces[i][j].isMine() && !gridSpaces[i][j].isFlagged()) {
					gridSpaces[i][j].setRevealedTrue();
				}
			}
		}
		hasLost = true;
	}
	
	public void finishFlags() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(gridSpaces[i][j].isMine() && !gridSpaces[i][j].isFlagged()) {
					gridSpaces[i][j].setIsFlagged(true);
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				GridSpace space = gridSpaces[i][j];
				if(space.isRevealed()) {
					if(space.isMine()) {
						g.setColor(mineColor);
					} else {
						g.setColor(revealedColor);
					}
				} else if(space.isFlagged()){
					if(hasLost && !space.isMine()) {
						g.setColor(falseFlaggedColor);
					} else {
						g.setColor(flaggedColor);
					}
				} else {
					g.setColor(defaultColor);
				}
				g.fillRect(i * PIXEL_WIDTH, j * PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
				g.setColor(Color.black);
				g.drawRect(i * PIXEL_WIDTH, j * PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
				if(space.isRevealed() && !space.isMine() && space.getNumSurroundingMines() > 0) {
					g.setColor(Color.blue);
					g.drawString(Integer.toString(space.getNumSurroundingMines()), i * PIXEL_WIDTH + 8, j * PIXEL_HEIGHT + 12);
				} 
			}
		}
	}
}
