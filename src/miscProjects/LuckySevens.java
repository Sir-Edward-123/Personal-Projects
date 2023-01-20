package miscProjects;

public class LuckySevens {
	
	public static void main(String args[]) {
		new LuckySevens(50).playGame();
	}
	
	private int balance;
	private int numOfTurns;
	
	LuckySevens(int userBalance) {
		if(userBalance < 0) {
			userBalance = 0;
		} else {
			balance = userBalance;
		}
		
		numOfTurns = 0;
	}
	void playGame() {
		while(getBalance() > 0) {
			playTurn();
		}
		
		System.out.println("Turns Survived: " + getGameReport());
	}
	
	public void playTurn() {
		int dice1 = rollDice();
		int dice2 = rollDice();	
		int sum = dice1 + dice2;
		
		if(sum == 7) {
			balance += 4;
			System.out.println(dice1 + ", " + dice2 + "\tWIN\tBalance:" + balance);
		} else {
			balance--;
			System.out.println(dice1 + ", " + dice2 + "\tLose\tBalance:" + balance);
		}
		
		numOfTurns++;
	}
	
	public int rollDice() {
		return (int) (Math.random() * 6) + 1;
	}
	
	public String getGameReport() {
		return Integer.toString(getNumOfTurns());
	}
	
	public int getNumOfTurns() {
		return numOfTurns;
	}
	
	public int getBalance() {
		return balance;
	}
}
