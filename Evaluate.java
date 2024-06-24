//This is a class that evaluates the state of a game. It makes use of a dictionary to check and see if the player or computer has won or if it's a draw or if the game is undecided.
//Student no. 251144211
//Name of author - Marc Alex Crasto
public class Evaluate {
	//size of the board.
	private int size;
	//no. of tiles required to win.
	private int tilesToWin;
	//difficulty level.
	private int maxLevels;
	//stores the gridboard of the game and which tiles have been used by either player/computer, and which tiles are unavailable.
	private char[][] gameBoard;
	
	//constructor method that sets  the size of the board, the number of adjacent tiles needed to win the game and the playing quality of the program.
	public Evaluate (int size, int tilesToWin, int maxLevels) {
		this.size = size;
		this.tilesToWin = tilesToWin;
		this.maxLevels = maxLevels;
		gameBoard = new char[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				gameBoard[i][j] = 'e';
			}
		}
	}
	
	//public method that returns an empty Dictionary of the size that you have selected.
	public Dictionary createDictionary() {
		Dictionary dict = new Dictionary(7);
		return dict;
	}
	
	//public method that  represents the content of the gameBoard as a string, checks whether there is a record in dict with this string as key attribute, returns the Record object that contains it; otherwise the method returns the value null.
	 public Record repeatedState(Dictionary dict) {
		 String key = "";
		 Record result = null;
		 for(int i=0;i<size;i++) {
			 for(int j=0;j<size;j++) {
					key = key + gameBoard[i][j];
				}
		 }
		 result = dict.get(key);
		 return result;
	 }
	 
	 //public method that represents the content of gameBoard as a string, creates an object of the class Record storing this string, score, and level; finally, this Record object is stored in dict.
	 public void insertState(Dictionary dict, int score, int level) {
		 String key = "";
		 Record result = null;
		 for(int i=0;i<size;i++) {
			 for(int j=0;j<size;j++) {
					key = key + gameBoard[i][j];
				}
		 }
		 Record newRecord = new Record(key, score, level);
		 dict.put(newRecord);
	 }
	 
	 //public void method that stores symbol in gameBoard[row][col].
	 public void storePlay(int row, int col, char symbol) {
		 gameBoard[row][col] = symbol;
	 }
	 
	 //public method that returns true if gameBoard[row][col] is 'e'; otherwise it returns false.
	 public boolean squareIsEmpty(int row, int col) {
		 boolean result = false;
		 if(gameBoard[row][col] == 'e') {
			 result = true;
		 }
		 else {
			 //do nothing
		 }
		 return result;
	 }
	 
	//public method that returns true if gameBoard[row][col] is 'c'; otherwise it returns false.
	 public boolean tileOfComputer(int row, int col) {
		 boolean result = false;
		 if(gameBoard[row][col] == 'c') {
			 result = true;
		 }
		 else {
			 //do nothing
		 }
		 return result;
	 }
	 
	//public method that returns true if gameBoard[row][col] is 'h'; otherwise it returns false.
	 public boolean tileOfHuman(int row, int col) {
		 boolean result = false;
		 if(gameBoard[row][col] == 'h') {
			 result = true;
		 }
		 else {
			 //do nothing
		 }
		 return result;
	 }
	 
	 //public method that returns true if there are the required number of adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard; otherwise it returns false. Uses private helper methods to help evaluate.
	 public boolean wins(char symbol) {
		 boolean result = false;
		 for(int i=0;i<size && result == false;i++) {
			 for(int j=0;j<size && result == false;j++) {
					if(isDiagonal(gameBoard, i, j, symbol) == true) {
						result = true;
					}
					else {
						if(isVertical(gameBoard, i, j, symbol) == true) {
							result = true;
						}
						else {
							if(isHorizontal(gameBoard, i, j, symbol) == true) {
								result = true;
							}
						}
					}
				}
		 }
		 return result;
	 }
	 
	 //private helper method that checks to see if the user/computer put the correct no. of tiles in the same diagonal (one after another) in order to win. Returns true if this is the case; otherwise false.
	 private boolean isDiagonal(char[][] gameBoard, int i, int j, char symbol) {
		 boolean result = false;
		 boolean pattern = true;
		 int count = 0;
		 if(gameBoard[i][j] == symbol) {
			 int tempI = i;
			 int tempJ = j;
			 if(tempI+1<size && tempJ+1<size) {
				 if(gameBoard[tempI+1][tempJ+1] == symbol) {
					 while(count != tilesToWin && 0<=tempI && tempI<size && 0<=tempJ && tempJ<size && pattern == true) {
						 if(gameBoard[tempI][tempJ] == symbol) {
							 count++;
						 }
						 else {
							 pattern = false;
							 count = 0;
						 }
						 tempI = tempI+1;
						 tempJ = tempJ+1;
					 }
				 }
		 	 }
			 if(tempI+1<size && 0<=tempJ-1) {
				 if(gameBoard[tempI+1][tempJ-1] == symbol) {
					 while(count != tilesToWin && 0<=tempI && tempI<size && 0<=tempJ && tempJ<size && pattern == true) {
						 if(gameBoard[tempI][tempJ] == symbol) {
							 count++;
						 }
						 else {
							 pattern = false;
							 count = 0;
						 }
						 tempI = tempI+1;
						 tempJ = tempJ-1;
					 }
				 }
				 else {
					 //do nothing
				 }
			 }
		 }
		 if(count != tilesToWin) {
			 pattern = false;
		 }
		 if(pattern == true) {
			 result = true;
		 }
		 return result;
	 }
	 
	//private helper method that checks to see if the user/computer put the correct no. of tiles vertically (one after another) in the same column in order to win. Returns true if this is the case; otherwise false.
	 private boolean isVertical(char[][] gameBoard, int i, int j, char symbol) {
		 boolean result = false;
		 boolean pattern = true;
		 int count = 0;
		 if(gameBoard[i][j] == symbol) {
			 int tempI = i;
			 int tempJ = j;
			 if(tempJ+1<size) {
				 if(gameBoard[tempI][tempJ+1] == symbol) {
					 while(count != tilesToWin && 0<=tempJ && tempJ<size && pattern == true) {
						 if(gameBoard[tempI][tempJ] == symbol) {
							 count++;
						 }
						 else {
							 pattern = false;
							 count = 0;
						 }
						 tempJ = tempJ+1;
					 }
				 }
			 }
		 }
		 if(count != tilesToWin) {
			 pattern = false;
		 }
		 if(pattern == true) {
			 result = true;
		 }
		 return result;
	 }
	 
	//private helper method that checks to see if the user/computer put the correct no. of tiles horizontally (one after another) in the same row in order to win. Returns true if this is the case; otherwise false.
	 private boolean isHorizontal(char[][] gameBoard, int i, int j, char symbol) {
		 boolean result = false;
		 boolean pattern = true;
		 int count = 0;
		 if(gameBoard[i][j] == symbol) {
			 int tempI = i;
			 int tempJ = j;
			 if(tempI+1<size) {
				 if(gameBoard[tempI+1][tempJ] == symbol) {
					 while(count != tilesToWin && 0<=tempI && tempI<size && pattern == true) {
						 if(gameBoard[tempI][tempJ] == symbol) {
							 count++;
						 }
						 else {
							 pattern = false;
							 count = 0;
						 }
						 tempI = tempI+1;
					 }
				 }
			 }
		 }
		 if(count != tilesToWin) {
			 pattern = false;
		 }
		 if(pattern == true) {
			 result = true;
		 }
		 return result;
	 }
	 
	 //public method that returns true if there are no empty positions left in gameBoard; otherwise it returns false.
	 public boolean isDraw() {
		 boolean result = true;
		 for(int i=0;i<size && result == true;i++) {
				for(int j=0;j<size && result == true;j++) {
					if(squareIsEmpty(i,j) == true) {
						result = false;
					}
				}
			}
		 return result;
	 }
	 
	 //public method that returns one of the following values : 3 if the computer has won; 0 if the user has won; 1 if the game is still being played and no one has won and all tiles haven't been filled; 2 if the game is a draw.
	 public int evalBoard() {
		 if(wins('c') == true) {
			 return 3;
		 }
		 else {
			 if(wins('h') == true) {
				 return 0;
			 }
			 else {
				 if(isDraw() == true) {
					 return 2;
				 }
				 else {
					 return 1;
				 }
			 }
		 }
	 }
}
