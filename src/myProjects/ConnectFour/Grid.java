package myProjects.ConnectFour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Grid extends JPanel {

	private static final long serialVersionUID = 1L;
	
	final int GRID_LENGTH = 701;
	final int GRID_HEIGHT = 601;
	final int SPACE_LENGTH = 100;
	final int SPACE_HEIGHT = 100;
	final int ROWS = 6;
	final int COLS = 7;
	
	GridSpace[][] gridSpaces = new GridSpace[ROWS][COLS];
	ArrayList<GridSpace> winningSpaces = new ArrayList<GridSpace>();
	int lastRow;
	int lastCol;
	int highlightCol = 0;
	Color highlightColor = new Color(220, 220, 220);
	
	public Grid() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				gridSpaces[i][j] = new GridSpace(i, j);
			}
		}
		
		this.setPreferredSize(new Dimension(GRID_LENGTH, GRID_HEIGHT));
	}
	
	public boolean checkGridClicked(int x) {
		int col = x / SPACE_LENGTH;
		for(int row = gridSpaces.length - 1; row >= 0; row--) {
			if(gridSpaces[row][col].getStatus() == SpaceStatus.EMPTY) {
				lastRow = row;
				lastCol = col;
				return true;
			}
		}
		return false;
	}
	
	public void updateGridClicked(int x, boolean isRedTurn) {
		gridSpaces[lastRow][lastCol].setStatus(isRedTurn);
		repaint();
	}
	
	public boolean checkWin(boolean isRedTurn) {
		if(checkVerticalWin(isRedTurn)) {
			return true;
		} else if(checkHorizontalWin(isRedTurn)) {
			return true;
		} else if(checkDiagonalWin1(isRedTurn)) {
			return true;
		} else if(checkDiagonalWin2(isRedTurn)) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkGridFull() {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(gridSpaces[row][col].getStatus() == SpaceStatus.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean checkVerticalWin(boolean isRedTurn) {
		int num = 1;
		int searchRow = lastRow;
		ArrayList<GridSpace> possWinSpaces = new ArrayList<GridSpace>();
		possWinSpaces.add(gridSpaces[lastRow][lastCol]);
		
		while(true) {
			searchRow++;
			if(searchRow >= ROWS) {
				break;
			} else if(gridSpaces[searchRow][lastCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[searchRow][lastCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[searchRow][lastCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[searchRow][lastCol]);
				num++;
			}
		}
		
		if(num >= 4) {
			winningSpaces = possWinSpaces;
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkHorizontalWin(boolean isRedTurn) {
		int num = 1;
		int searchCol = lastCol;
		ArrayList<GridSpace> possWinSpaces = new ArrayList<GridSpace>();
		possWinSpaces.add(gridSpaces[lastRow][lastCol]);
		
		while(true) {
			searchCol++;
			if(searchCol >= COLS) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[lastRow][searchCol]);
				num++;
			}
		}
		
		searchCol = lastCol;
		while(true) {
			searchCol--;
			if(searchCol < 0) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[lastRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[lastRow][searchCol]);
				num++;
			}
		}
		
		if(num >= 4) {
			winningSpaces = possWinSpaces;
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkDiagonalWin1(boolean isRedTurn) {
		int num = 1;
		int searchRow = lastRow;
		int searchCol = lastCol;
		ArrayList<GridSpace> possWinSpaces = new ArrayList<GridSpace>();
		possWinSpaces.add(gridSpaces[lastRow][lastCol]);
		
		while(true) {
			searchRow++;
			searchCol++;
			if(searchRow >= ROWS || searchCol >= COLS) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[searchRow][searchCol]);
				num++;
			}
		}
		
		searchRow = lastRow;
		searchCol = lastCol;
		while(true) {
			searchRow--;
			searchCol--;
			if(searchRow < 0 || searchCol < 0) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[searchRow][searchCol]);
				num++;
			}
		}
		
		if(num >= 4) {
			winningSpaces = possWinSpaces;
			return true;
		} else {
			return false;
		}
	}
	
	boolean checkDiagonalWin2(boolean isRedTurn) {
		int num = 1;
		int searchRow = lastRow;
		int searchCol = lastCol;
		ArrayList<GridSpace> possWinSpaces = new ArrayList<GridSpace>();
		possWinSpaces.add(gridSpaces[lastRow][lastCol]);
		
		while(true) {
			searchRow++;
			searchCol--;
			if(searchRow >= ROWS || searchCol < 0) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[searchRow][searchCol]);
				num++;
			}
		}
		
		searchRow = lastRow;
		searchCol = lastCol;
		while(true) {
			searchRow--;
			searchCol++;
			if(searchRow < 0 || searchCol >= COLS) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.EMPTY) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.RED && !isRedTurn) {
				break;
			} else if(gridSpaces[searchRow][searchCol].getStatus() == SpaceStatus.YELLOW && isRedTurn) {
				break;
			} else {
				possWinSpaces.add(gridSpaces[searchRow][searchCol]);
				num++;
			}
		}
		
		if(num >= 4) {
			winningSpaces = possWinSpaces;
			return true;
		} else {
			return false;
		}
	}
	
	public void setColHighlight(int x) {
		highlightCol = x / SPACE_LENGTH;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(col == highlightCol) {
					g.setColor(highlightColor);
				} else {
					g.setColor(Color.WHITE);
				}
				
				if(!winningSpaces.isEmpty()) {
					for(int i = 0; i < winningSpaces.size(); i++) {
						if(winningSpaces.get(i).getRow() == row && winningSpaces.get(i).getCol() == col) {
							g.setColor(Color.GRAY);
						}
					}
				}
				
				g.fillRect(col * SPACE_LENGTH, row * SPACE_HEIGHT, SPACE_LENGTH, SPACE_HEIGHT);
				
				if(gridSpaces[row][col].getStatus() == SpaceStatus.RED){
					g.setColor(Color.RED);
				} else if(gridSpaces[row][col].getStatus() == SpaceStatus.YELLOW){
					g.setColor(Color.YELLOW);
				}
				g.fillOval(col * SPACE_LENGTH + 5, row * SPACE_HEIGHT + 5, SPACE_LENGTH - 10, SPACE_HEIGHT - 10);
				
				g.setColor(Color.BLACK);
				if(gridSpaces[row][col].getStatus() != SpaceStatus.EMPTY) {
					g.drawOval(col * SPACE_LENGTH + 5, row * SPACE_HEIGHT + 5, SPACE_LENGTH - 10, SPACE_HEIGHT - 10);
				}
				g.drawRect(col * SPACE_LENGTH, row * SPACE_HEIGHT, SPACE_LENGTH, SPACE_HEIGHT);
			}
		}
	}
	
}
