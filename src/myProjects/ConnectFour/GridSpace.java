package myProjects.ConnectFour;

public class GridSpace {
	private int row;
	private int col;
	private SpaceStatus status;
	
	public GridSpace(int row, int col) {
		this.row = row;
		this.col = col;
		status = SpaceStatus.EMPTY;
	}
	
	public void setStatus(boolean isRedTurn) {
		if(isRedTurn) {
			status = SpaceStatus.RED;
		} else {
			status = SpaceStatus.YELLOW;
		}
	}
	
	public SpaceStatus getStatus() {
		return status;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
