package myProjects.minesweeper;

import java.util.ArrayList;

public class GridSpace {
	private boolean isRevealed = false;
	private boolean isFlagged = false;
	private boolean isMine = false;
	private int row;
	private int col;
	
	ArrayList<GridSpace> surroundingSpaces = new ArrayList<GridSpace>();
	public int numSurroundingMines;
	
	public GridSpace(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void revealSpace() {
		setRevealedTrue();
		if(numSurroundingMines == 0) {
			revealSurroundingSpaces();
		}
	}
	
	public void revealSurroundingSpaces() {	
		if(numSurroundingMines == 0 || getNumSurroundingFlags() == numSurroundingMines) {
			for(int i = 0; i < surroundingSpaces.size(); i++) {
				if(!surroundingSpaces.get(i).isRevealed && !surroundingSpaces.get(i).isFlagged) {
					surroundingSpaces.get(i).revealSpace();
				}
			}
		}
	}
	
	public boolean areSurroundingUnflaggedMines(){
		for(int i = 0; i < surroundingSpaces.size(); i++) {
			if(!surroundingSpaces.get(i).isFlagged && surroundingSpaces.get(i).isMine) {
				return true;
			}
		}
		return false;
	}
	
	public int getNumSurroundingFlags() {
		int numFlags = 0;
		for(int i = 0; i < surroundingSpaces.size(); i++) {
			if(surroundingSpaces.get(i).isFlagged) {
				numFlags++;
			}
		}
		return numFlags;
	}
	
	public void determineNumSurroundingMines() {
		int numMines = 0;
		for(int i = 0; i < surroundingSpaces.size(); i++) {
			if(surroundingSpaces.get(i).isMine) {
				numMines++;
			}
		}
		numSurroundingMines = numMines;
	}
	
	public int getNumSurroundingMines() {
		return numSurroundingMines;
	}
	
	public void determineSurroundingSpaces(GridSpace[][] gridSpaces) {
		for(int i = row - 1; i <= row + 1; i++) {
			if(i >= 0 && i < gridSpaces.length) {
				for(int j = col - 1; j <= col + 1; j++) {
					if(j >= 0 && j < gridSpaces[0].length) {
						if(!equals(i, j)) {
							surroundingSpaces.add(gridSpaces[i][j]);
						}
					}
				}
			}
		}
	}
	
	public ArrayList<GridSpace> getSurroundingSpaces(){
		return surroundingSpaces;
	}
	
	public boolean isRevealed() {
		return isRevealed;
	}
	
	public void setRevealedTrue() {
		isRevealed = true;
	}
	
	public boolean isFlagged() {
		return isFlagged;
	}
	
	public void setIsFlagged(boolean flagged) {
		isFlagged = flagged;
	}
	
	public boolean isMine() {
		return isMine;
	}
	
	public void setMineTrue() {
		isMine = true;
	}
	
	public boolean equals(int row, int col) {
		if(row == this.row && col == this.col) {
			return true;
		} else {
			return false;
		}
	}
}
