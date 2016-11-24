package practice.exercise;

import java.util.Scanner;

public class Game {
	
	Scanner input = new Scanner(System.in);
	
	private String floor[][] = new String[20][20];
	private String commands[] = new String[40];
	boolean drawStatus;
	
	// keeps track of the which direction the turtle is facing
	private enum Facing {UP, DOWN, LEFT, RIGHT};
	private Facing position;
	
	// variables to keep track of the current position in the array
	private int rowPos;
	private int colPos;
	
	private int spaces;
	
	private void getCommands() {
		
		/* also set the defaults here */
		position = Facing.RIGHT;
		rowPos = 0;
		colPos = 0;
		spaces = 0;
		
		System.out.println("Enter your commands");
		String cmd;
		int crntPos = 0;
		
		do {
			
			cmd = input.next();			
			if(isCorrect(cmd)) {
				
				commands[crntPos] = cmd;
				crntPos++;
				
			} else {
				
					System.out.println("Enter a valid input");
				
			} // end if
			
		} while(!"9".equals(cmd) ); // end while loop
		
	} // end method getCommands()
	
	void readCommands() {
		
		getCommands();
		
		for(int i = 0; !( "9".equals(commands[i]) ); i++) {
			
			switch( commands[i].substring(0, 1) ) {
			
			case "1":
			case "2":
				penStatus(commands[i]);
				break;
				
			case "3":
			case "4":
				setTurtleDirection(commands[i]);
				break;
				
			case "5":
				String strSpaces = commands[i].substring(2);
				spaces = Integer.parseInt(strSpaces);
				chkSpaces(i);
				drawShape();
				break;
				
			case "6":
				displayArray();
				break;
			} // end switch
			
			
		} // end for
		
	} // end method readCommands()
	
	private void chkSpaces(int index) {
		
		switch (position) {
		
		case UP:
			if( (rowPos - spaces) < 0 ) {
				
				System.out.println("Out-of-bound: You cannot make that much movements");
				System.out.println("You entered " + commands[index]);
				System.out.println("Enter a smaller number for the spaces value");
				spaces = input.nextInt();
				
			} // end if
			break;
			
		case LEFT:
			if( (colPos - spaces) < 0 ) {
				
				System.out.println("Out-of-bound: You cannot make that much movements");
				System.out.println("You entered " + commands[index]);
				System.out.println("Enter a smaller number for the spaces value");
				spaces = input.nextInt();
				
			} // end if
			break;
			
		case DOWN:
			if( (rowPos + spaces) > 19 ) {
				
				System.out.println("Out-of-bound: You cannot make that much movements");
				System.out.println("You entered " + commands[index]);
				System.out.println("Enter a smaller number for the spaces value");
				spaces = input.nextInt();
				
			} // end if
			break;
			
		case RIGHT:
			if( (colPos + spaces) > 19 ) {
				
				System.out.println("Out-of-bound: You cannot make that much movements");
				System.out.println("You entered " + commands[index]);
				System.out.println("Enter a smaller number for the spaces value");
				spaces = input.nextInt();
				
			} // end if
			break;
		
		
		} // end switch
		
	} // end method chkSpaces()
	
	private void displayArray() {
		
		floor[rowPos][colPos] = "*";
		
		for(int row = 0; row < floor.length; row++) {
			
			for(int col = 0; col < floor[row].length; col++) {
				
				System.out.printf( "%2s", floor[row][col] );
				
			} // end inner for loop
			
			System.out.println();
		} // end for
		
	} // end method displayArray()
	
	private void drawShape() {
		
		switch(position) {
		
		case UP:
				if(drawStatus) {
					while(--spaces > 0) {
				
						floor[--rowPos][colPos] = "1";
					
					} // end while loop
				}else {
					
					while(--spaces > 0) {
						
						floor[--rowPos][colPos] = " ";
					
					} // end while loop
					
				}
				break;
			
		case DOWN:
				if(drawStatus) {
					while(--spaces > 0) {
				
						floor[++rowPos][colPos] = "1";
					
					} // end while loop
				} else {
					
					while(--spaces > 0) {
						
						floor[++rowPos][colPos] = " ";
					
					} // end while loop
					
				} // end if
			break;
			
		case LEFT:
				if(drawStatus) {
					while(--spaces > 0) {
				
						floor[rowPos][--colPos] = "1";
					
					} // end while loop
				} else {
					
					while(--spaces > 0) {
						
						floor[rowPos][--colPos] = " ";
					
					} // end while loop
					
				} // end if
			break;
			
		case RIGHT:
				if(drawStatus) {
					while(--spaces > 0) {
				
						floor[rowPos][++colPos] = "1";
				
					} // end while loop
				} else {
					
					while(--spaces > 0) {
						
						floor[rowPos][++colPos] = " ";
					
					} // end while loop
					
				} // end if
			break;
			
		} // end switch
		
	} // end method drawShape()
	
	private void setTurtleDirection(String val) {
		
		switch(position) {
		
		case UP:
			
			if("3".equals(val) ) {
				position = Facing.RIGHT;
			} else if("4".equals(val)){
				position = Facing.LEFT;
			}
			
			break;
			
		case DOWN:
			
			if("3".equals(val) ) {
				position = Facing.LEFT;
			} else if("4".equals(val)){
				position = Facing.RIGHT;
			}
			
			break;
			
		case LEFT:
			
			if("3".equals(val) ) {
				position = Facing.UP;
			} else if("4".equals(val)){
				position = Facing.DOWN;
			}
			
			break;
			
		case RIGHT:
			
			if("3".equals(val) ) {
				position = Facing.DOWN;
			} else if("4".equals(val)){
				position = Facing.UP;
			}
			
			break;
		
		} // end switch
		
	} // end method setTurtleDirection()
	
	private void penStatus(String val) {
		
		boolean status = false;
		
		if("1".equals(val) ) {
			
			status = false;
			
		}else if("2".equals(val)) {
			
			status = true;
			
		}
		
		drawStatus = status;
		
	} //  end method penStatus()
	
	private boolean isCorrect(String val) {
		
		boolean status = false;
		String sglCmd = val.substring(0, 1);
		int cmd = Integer.parseInt(sglCmd);
		
		switch(cmd) {
			
			
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 9:
				status = true;
				break;
			case 0:
			case 7:
			case 8:
				status = false;
				System.out.println("Not a command");
				break;				
		
		} // end switch statement
		
		return status;
		
	} // end method isCorrect()
	
	public Game() {
		
		// display commands
		System.out.println("COMMANDS\t\t" + "MEANING");
		System.out.println();
		System.out.printf("%-8s\t\t%s\n", "1","Pen up");
		System.out.printf("%-8s\t\t%s\n", "2","Pen down");
		System.out.printf("%-8s\t\t%s\n", "3","Turn right");
		System.out.printf("%-8s\t\t%s\n", "4","Turn left");
		System.out.printf("%-8s\t\t%s\n", "5,10","Move forward 10 spaces(replace 10 for another number)");
		System.out.printf("%-8s\t\t%s\n", "6","Display 20-by-20 array");
		System.out.printf("%-8s\t\t%s\n\n", "9","End of Data (sentinel value)");
		
		// initialise each element in the array
		for(int row = 0; row < floor.length; row++) {
			
			for(int col = 0; col < floor[row].length; col++) {
				
				floor[row][col] = ""; // assigns each element in the array a space.
				
			} // end inner for loop
			
		} // end for loop
		
	} // end constructor
	
} // end class Game