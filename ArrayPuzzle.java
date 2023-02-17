/**
 * @Mustafa Al-Shebeeb
 * CS 110 Section 12
 * 10/26/2022
 */
//Imports all the tools needed
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz1.txt
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz1word.txt
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz2.txt
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz2word.txt
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz3.txt
//C:\Users\omara\eclipse-workspace\Program Assignment 4\src\puzz3word.txt
public class ArrayPuzzle {

	public static void main(String[] args) {
		//array to hold the fill method
		char [][] fillArr = fill();
		//call for printing method
		printPuzzle(fillArr);
		//file reader block for scrambled puzzle file
		//initiates a scanner that reads the user input
		Scanner scan = new Scanner(System.in);
		//initiates a scanner that reads the file contents
		Scanner inputFile = null;
		//Creates a string that holds the user's input
		String filePath = null;
		// The try block will try to get a file path from the user input
				try {
					//Prompts the user to enter a valid file path
					System.out.println("Please enter a valid file path for the puzzle: ");
					//Stores the file path in the user input
					filePath = scan.nextLine();
					//Initiates a file reader
					inputFile = new Scanner(new FileReader(filePath));
				} catch (IOException e) {
					//Catches any exceptions
					System.out.println(filePath + " is invalid. Please try again.");
				}
				while(inputFile.hasNext()) {
					String word = inputFile.next();
					play(word,fillArr);
				}
	}
				
	//fill method for holding the array in the file
	public static char[][] fill(){
		//this file reader is for the words puzzle file
		//initiates a scanner that reads the user input
				Scanner scan = new Scanner(System.in);
				//initiates a scanner that reads the file contents
				Scanner inputFile = null;
				//Creates a string that holds the user's input
				String filePath = null;
				// The try block will try to get a file path from the user input
				
		try {
			// Prompts the user to enter a valid file path
			System.out.println("Please enter the valid file path for the words: "); 
			// Stores the file path in the user input
			filePath = scan.nextLine();
			// Initiates a file reader
			inputFile = new Scanner(new FileReader(filePath));
		} catch (IOException e) {
			// Catches any exceptions
			System.out.println(filePath + " is invalid. Please try again.");
		}
		
		// variables for scanning file contents and turning them into rows and columns
		int rows = inputFile.nextInt();
		int cols = inputFile.nextInt();
		// stores both the variables above into a 2D array
		char [][] fileArr = new char[rows][cols];
		// for loop for reading each column and row 
		for(int i = 0 ; i < fileArr.length; i++) {
			for(int j = 0; j < fileArr[i].length; j++) {
				fileArr[i][j] = inputFile.next().charAt(0);
				
			}
		}
		//returns the fill array to the main method
		return fileArr;
	}

	// method for printing the file contents into a 2d array
	public static void printPuzzle(char[][] puzzle) {
		//this for loop will scan the contents and print them on a 2D array grid
		for(int row = 0 ; row < puzzle.length; row++) {
			for(int col = 0; col < puzzle[row].length; col++) {
				System.out.print( puzzle[row][col] + " ");
			}
			System.out.println();
	}

	}
	//play method for the word search
	public static void play(String word, char[][] puzzle) {
		//boolean variable for finding words
		boolean found = false;
		//prints the current word that the previous methods read in order
		System.out.println("Current Word = " + word);
		//nested loop for reading the columns and rows 
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				//nested if statement to check the first letter
			if(word.charAt(0) == puzzle[row][col]) {
				//if statements for each checking method
				// if statement for checking up
				if (checkUp(puzzle, word, row, col)) {
					//prints the direction if any of the checking methods find the word
			System.out.println("The word " + word + " was found beggining in cell " + row + " " + col + " going up");
			found = true;
			} 
				//if statement for checking down
			else if (checkDown(puzzle, word, row, col)) {
			System.out.println("The word " + word + " was found beggining in cell " + row + " " + col + " going down");
			found = true;
			} 
				//if statement for checking right
			else if (checkRight(puzzle, word, row, col)) {
			System.out.println("The word " + word + " was found beggining in cell " + row + " " + col + " going right");
			found = true;
			}
				// if statement for checking left
			else if (checkLeft(puzzle, word, row, col)) {
			System.out.println("The word " + word + " was found beggining in cell " + row + " " + col + " going left");
			found = true;
			}
			}
			}
			}
		//catches exceptions for words if thy don't match
		if(found == false) {
			System.out.println("the word " + word + " not in puzzle");
		}
						
		
	}
// each of the checking methods for each direction
	//checking method for checking up
	public static boolean checkUp(char [][] puzzle, String word, int row, int col) {
	//try catch block was more effective since if statement weren't functioning correctly
		try {
			boolean found = true;
			for (int j = 1; j < word.length(); j++) {
				if (puzzle[row - 1][col] != word.charAt(j)) {
					found = false;
					}
				// decrements a row to go up in the 2D array
				row = row - 1;
			}
			return found;
					
				//catches exceptions and returns false, then moves to the next method	
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
//checking method for checking down, similar to up with the difference of incrementing
	public static boolean checkDown(char [][] puzzle, String word, int row, int col) {
		try {
			boolean found = true;
			for (int j = 1; j < word.length(); j++) {
				if (puzzle[row + 1][col] != word.charAt(j)) {
					found = false;
					}
				//increments to go down in a 2D array
				row = row + 1;
			} 
			return found;
					
					
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
//checking method for checking left, also similar to the previous methods but using columns instead of rows this time
	public static boolean checkLeft(char [][] puzzle, String word, int row, int col) {
		//similar try catch block method
		try {
			boolean found = true;
			for (int j = 1; j < word.length(); j++) {
				if (puzzle[row][col - 1] != word.charAt(j)) {
					found = false;
					}
				// decrements columns instead of rows to go left 
				col = col -1;
			}
			return found;
					
					
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		
	}
	// checking method for checking right 
	//similar to the prevous method but it increments the column
	public static boolean checkRight(char [][] puzzle, String word, int row, int col) {
		try {
			boolean found = true;
			for (int j = 1; j < word.length(); j++) {
				if (puzzle[row][col + 1] != word.charAt(j)) {
					found = false;
					}
				//increment the column to go right
				col = col  + 1;
			}
			return found;
					
					
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}

	}
}
//end of program