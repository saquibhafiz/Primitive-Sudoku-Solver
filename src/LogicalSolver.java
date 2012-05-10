/*
 *****************************************************************************
 *****************************************************************************
 *	Program: 		Sudoku Solver											**
 *	Programmers:	Saquib Hafiz											**
 *	File Name:		LogicalSolver.java										**
 *	Due Date:		Tuesday, May 31st, 2011									**
 *	Purpose:		Holds and solves the puzzle using a logical algorithm	**
 *					created by manipulating the simple rules into logical 	**
 *					steps.													**
 *****************************************************************************
 *****************************************************************************
*/

// 	ALL Code produced by: Saquib Hafiz

import java.io.*;			// required to access BufferedReader and Exception classes
import java.awt.*;			// required for graphical interface interaction
import java.awt.event.*;	// required for event manipulation
import java.util.Date;		// required to access time
import javax.swing.*;		// required for graphical interface

public class LogicalSolver
{
	public static int[][] solver ()
	{
		// ############################# VARIABLES AND OBJECTS ################################
		// Define all objects and variables first so taht they are ready for use.
		// ####################################################################################

		// pre built-in java objects
		BufferedReader in;		// to read from file
		PrintWriter moveLog;	// to write to a file, this is to record all the moves as they happen
		Date og = new Date ();	// this object is used to read the oroginal time in miliseconds
		long original_time = og.getTime();	// use the time object previously defined to take the current time

		// personally created and accessed objects
		BruteForce bf = new BruteForce ();	// there is another class in this file taht i have created and this object acceses it

		// simple variables
		String input;	// the line of input from the file "problem.txt"
		boolean done = false, cornered = false, boxed = false;	// these boolean variables are used later on to place numbers and check definitions
		final int rows = 9;		// constant row is always 9
		final int columns = 9;	// constant column is also always 9
		long end_time = 0;	// will be used later on after the problem is solved to take finishing time
		int temp = 0, w = 0;	// temporary variables used accordinly to need

		// array variables
		boolean [][] permanent = new boolean [rows][columns];	// the permanent variables
		int [][] values = new int [rows][columns];		// the grid itself
		int [][][] possibilities = new int [rows][columns][9];	// every square (all 81) can have a maximum of 9 possibilities (ranging from 1 to 9)

		// ############################# FINISH DEFINITIONS ###################################

		try		// used to try to read and process information from a text file
		{
			in = new BufferedReader (new FileReader("problem.txt"));	// object is used to read the text file "problem.txt."
			moveLog = new PrintWriter (new FileOutputStream("moveLog.txt"));

			for (int a = 0; a < 9; a++)	// eah line is a row of numbers
			{
				input = in.readLine();	// read directly from the text file
				input = input.trim();	// get rid of any useless spcaes
				input = input + " ";	// required becuase to perform the operation "substring" we need a non-included end value

				for (int b = 0; b < 9; b++)	// each letter in the line can be integer parsed to grab its integer column value
				{
					values[a][b] = Integer.parseInt(input.substring(b,b+1));	// takes a single letter fromt he stirng and takes its integer value
					if (values[a][b] == 0)	// if the value is "0" then the value is non-permanent and can be played with
					{
						permanent[a][b] = false;
					}
					else	// if the value is not "0" then the value is permanent and cannot be changed
					{
						permanent[a][b] = true;
					}
				}
			}
			in.close();	// close the file "problem.txt"

			do	// using the logical algorithm
			{
				// ############################# FOR SINGLE POSSIBLITIES ##############################
				// Here the program finds any square that has only one single possibility. It checks
				// all the possibilities first to and if only one is possible hen taht one is the
				// correct solution for that square.
				// ####################################################################################
				for (int a = 0; a < 9; a++)	// for every row
				{
					for (int b = 0; b < 9; b++)	// for every column
					{
						if (permanent[a][b] == false)	// if the value isnt permanent then i can change it
						{
							possibilities = allPossibilities(possibilities, permanent, values);	// check all possibilities
							// if there is a single possibility then the sum of all the possibilities is 1
							if ((possibilities[a][b][0] + possibilities[a][b][1] + possibilities[a][b][2] + possibilities[a][b][3] + possibilities[a][b][4] + possibilities[a][b][5] + possibilities[a][b][6] + possibilities[a][b][7] + possibilities[a][b][8]) == 1)
							{
								for (int c = 0; c < 9; c++)				// now that we know this square has only one possibility
								{										// we look for it
									if (possibilities[a][b][c] == 1)	// and when we find it
									{									// we replace the value of the square
										temp = c+1;						// with its correct value
										moveLog.println("Put " + (c+1) + " in cell [" + (a+1) + "][" + (b+1) + "] because its the one value that will fit");	// output to the log file
									}
								}
								values[a][b] = temp;	// put correct value
								permanent[a][b] = true;	// make it permanent
								possibilities[a][b] = permanizeValue();	// to make sure it is completely permanent we get rid of its possibilities
							}
						}
					}
				}
				// ############################# DONE SINGLE POSSIBLITIES #############################



				// ############################# FOR CORNERED POSSIBLITIES ############################
				// Here the program finds any square/cell that is being cornered with a certain value.
				// We know that a number can only appear once in its row and column, therefore if one
				// of its possibilities is the only possibility of that number in the entire row or
				// column that cell has to be that number.
				// ####################################################################################
				for (int a = 0; a < 9; a++)	// for every row
				{
					for (int b = 0; b < 9; b++)	// for every column
					{
						possibilities = allPossibilities(possibilities, permanent, values);	// check all possibilities
						if (permanent[a][b] == false)// if the value isnt permanent then i can change it
						{
							for (int c = 0; c < 9; c++)	// checks every possibility
							{
								if (possibilities[a][b][c] == 1)	// if this possibility is available
								{
									cornered = checkCornered(possibilities, a, b, c);	// check to see if the cell is cornered
									if (cornered == true)	// if the cell is cornered it will take on its correct value
									{
										values[a][b] = c+1;		// put correct value
										permanent[a][b] = true;	// make it permanent
										possibilities[a][b] = permanizeValue();	// to make sure it is completely permanent we get rid of its possibilities
										moveLog.println("Put " + (c+1) + " in cell [" + (a+1) + "][" + (b+1) + "] because it is being cornered by its adjascent rows/columns.");	// output to the log file
									}
								}
							}
						}
					}
				}
				// ############################# DONE CORNERED POSSIBLITIES ###########################



				// ############################# FOR BOXED POSSIBLITIES ###############################
				// Here the program finds any square/cell that is being boxed with a certain value. We
				// know that ever block must contain number from 1 to 9. Therefore if one of the
				// the possibilities is the only one of its kinda in the box then it is the correct
				// value for the cell/square.
				// ####################################################################################
				for (int a = 0; a < 9; a++)	// for every row
				{
					for (int b = 0; b < 9; b++)	// for every cell
					{
						possibilities = allPossibilities(possibilities, permanent, values);	// cehck all possibilites
						if (permanent[a][b] == false)	// if it is non-permamnent then i can change it
						{
							for (int c = 0; c < 9; c++)	// goes through every possibility
							{
								if (possibilities[a][b][c] == 1)	// if the possibility is possible
								{
									boxed = checkBoxed(possibilities, a, b, c);	// check if the possibility is boxed
									if (boxed == true)	// if it is boxed then change it to tis correct value
									{
										values[a][b] = c+1;		// put correct value
										permanent[a][b] = true;	// make it permanent
										possibilities[a][b] = permanizeValue();	// to make sure it is completely permanent we get rid of its possibilities
										moveLog.println("Put " + (c+1) + " in cell [" + (a+1) + "][" + (b+1) + "] because its being boxed in.");	// output to the log file
									}
								}
							}
						}
					}
				}
				// ############################# DONE BOXED POSSIBLITIES #############################

				done = checkFinish(values);	// checks if the puzzle is complete
				w++; // an increment aht is used to check how my cles it takes for the logical algorithm to process
			}
			while ((done == false)&&(w < 50));	// this logical algorithm works until the puzzle is either complete or it times out

			Date et = new Date ();		// create another time object to get the ending time
			end_time = et.getTime();	// get the ending time
			moveLog.println("Processing Time: " + (end_time - original_time) + " milliseconds");
			moveLog.close();

			if (done == false)
			{
				// warns the user that the logical algorithm was not completely successful and thus a brure force algorithm will be used
				values = bf.initiateBruteForce(values, permanent, possibilities);
				return values;	// return values for output
			}

			return values;	// return values for output
		}
		catch (FileNotFoundException fnfe)	// handle the File Not Found Execption
		{
			System.out.println("ERROR : File not found : " + fnfe);	// display error message
			System.exit(0);	// exits program early if error is found
		}
		catch (IOException ioe)	// handle the IOException
		{
			System.out.println("ERROR : IO EXCEPTION : " + ioe);	// display error message
			System.exit(0);	// exits program early if error is found
		}
		return values;	// return values for output
	}

	// checks if the process is complete with the original logical algorithm
	public static boolean checkFinish(int [][] values)	// return type : boolean | parameters : int[][]
	{
		int sum = 0;
		boolean done = false;
		for (int a = 0; a < 9; a++)	// for every row
		{
			for (int b = 0; b < 9; b++)	// for every column
			{
				sum += values[a][b];	// takes the sum of all the cells/squares
			}
		}
		if (sum == 405)	// if the sum of all the cells is 405 then the puzzle is complete
		{
			done = true;	// if the puzzle is complete then it is done therefore done = true
		}
		return done;
	}

	// turn all the possibiilities to 0
	public static int[] permanizeValue ()	// return type : int[] | parameters : none
	{
		int [] possibilities = {0,0,0,0,0,0,0,0,0};	// make all possibilities 0
		return possibilities;
	}

	// check all possibilities
	public static int[] checkPossibilities(int[][] values, int a, int b)	// return type : int[] | parameters : int[][], int, int
	{
		int [] row = new int [9];		// there are 9 rows of 9 numbers
		int [] column = new int [9];	// there are 9 columns of 9 numbers
		int [] box = new int [9];		// there are 9 boxes of 9 numbers

		int [] row_found = new int [9];		// each row has 9 possibilities ranging from 1 to 9
		int [] column_found = new int [9];	// each column has 9 possibilities ranging from 1 to 9
		int [] box_found = new int [9];		// each box has 9 possibilities ranging from 1 to 9

		int [] found = {1,1,1,1,1,1,1,1,1};
		int temp1, temp2, e = 0;	// variables required for temporary use

		for (int c = 0; c < 9; c++)	// take numbers from row
		{
			row[c] = values[c][b];
		}

		for (int c = 0; c < 9; c++)	// take numbers from column
		{
			column[c] = values[a][c];
		}

		temp1 = a/3;
		temp2 = b/3;
		// take the number from the cells corresponding block/box
		for (int c = 3*temp1; c < 3*temp1 + 3; c++)
		{
			for (int d = 3*temp2; d < 3*temp2 + 3; d++)
			{
				box[e] = values[c][d];	// values from the cells in the block
				e++;
			}
		}

		// check the possibilitties of each value in each cell
		row_found = checkRow(row);			// depending on the row
		column_found = checkColumn(column);	// depending on the column
		box_found = checkBox(box);			// depending on the box

		for (int f = 0; f < 9; f++)
		{
			if ((row_found[f] == 1)&&(column_found[f] == 1)&&(box_found[f] == 1))	// if it is allowed to be there
			{
				found[f] = 1;														// then it is a possibility
			}
			else				// however if it goes against the rules
			{
				found[f] = 0;	// then it is a possibility
			}
		}
		return found;
	}

	// check if the possibility is the only one in the its row or column
	public static boolean checkCornered(int[][][] possibilities, int a, int b, int c)	// return type : boolean | parameters : int[][][], int, int, int
	{
		boolean cornered = false, row_cornered = true, column_cornered = true;
		int d = 0;

		d = 0;
		do
		{
			if ((possibilities[a][b][c] == possibilities[d][b][c])&&(d!=a))	// make sure it doesnt self compare
			{
				row_cornered = false;
			}
			d++;
		}
		while((d < 9)&&(row_cornered == true));		// exit if the row is finished or if value is not possible

		d = 0;
		do
		{
			if ((possibilities[a][b][c] == possibilities[a][d][c])&&(d!=b))	// make sure it doesnt self compare
			{
				column_cornered = false;
			}
			d++;
		}
		while((d < 9)&&(column_cornered == true));	// exit if the column is finished or if value is not possible


		if (row_cornered == true)	// if it possible in the row
		{
			cornered = true;		// then it fits in oerfectly
		}
		else if (column_cornered == true)	// if it is possible in the columns
		{
			cornered = true;				// then it fits in oerfectly
		}
		else					// if it is not possible in either in the row or column
		{
			cornered = false;	// then it is not the correct possibility
		}

		return cornered;
	}

	// check if the possibility is the only one in the its block/box
	public static boolean checkBoxed (int[][][] possibilities, int a, int b, int c)	// return type : boolean | parameters : int[][][], int, int, int
	{
		int temp1, temp2, box_sum = 0;

		boolean boxed = false;	// initially false

		temp1 = a/3;
		temp2 = b/3;

		for (int d = 3*temp1; d < 3*temp1 + 3; d++)	// go through every row in the box
		{
			for (int e = 3*temp2; e < 3*temp2 + 3; e++)	// go through every column in the box
			{
				box_sum += possibilities[d][e][c];	// take the sum of all  the values in the cells
			}
		}

		if (box_sum == 1)	// if the sum is 1 then its the only possible spot
		{
			boxed = true;	// then its boxed
		}
		else				// if the sum is not 1 then its not the only possible spot
		{
			boxed = false;	// then its not boxed
		}
		return boxed;
	}

	// check numbers in a row to assess its possiblities
	public static int [] checkRow(int [] row)	// return type : int[] | parameters : int[]
	{
		int [] found = {1,1,1,1,1,1,1,1,1};	// start off with all the possibilites on
		for (int b = 0; b < 9; b++)	// goes through each element in the row
		{
			if (row[b] != 0)			// if the value of the element is 0
			{
				found[row[b]-1] = 0;	// then it is found and is no longer a possibility anywhere else in the row
			}
		}
		return found;
	}

	// check numbers in a column to assess its possiblities
	public static int [] checkColumn(int [] column)	// return type : int[] | parameters : int[]
	{
		int [] found = {1,1,1,1,1,1,1,1,1};	// start off with all the possibilites on
		for (int b = 0; b < 9; b++)	// goes through each element in the column
		{
			if (column[b] != 0)			// if the value of the element is 0
			{
				found[column[b]-1] = 0;	// then it is found and is no longer a possibility anywhere else in the column
			}
		}
		return found;
	}

	// check numbers in a box/block to assess its possiblities
	public static int [] checkBox(int[] box)	// return type : int[] | parameters : int[]
	{
		int [] found = {1,1,1,1,1,1,1,1,1};	// start off with all the possibilites on
		for (int b = 0; b < 9; b++)	// goes through each element in the box/column
		{
			if (box[b] != 0)			// if the value of the element is 0
			{
				found[box[b]-1] = 0;	// then it is found and is no longer a possibility anywhere else in the box
			}
		}
		return found;
	}

	// check all the possibilites for every cell
	public static int[][][] allPossibilities(int[][][] possibilities, boolean[][] permanent, int[][] values)	// return type : int[][][] | parameters : int[][][], boolean [][], int[][]
	{
		for (int x = 0; x < 9; x++)	// for every row
		{
			for (int y = 0; y < 9; y++)	// for ever column
			{
				if (permanent[x][y] == false)	// if the value is not permanent
				{
					possibilities[x][y] = checkPossibilities(values, x, y);		// checks current possibilities and finds all possibilities for the current cell
				}
				else	// if the value if permanent
				{
					possibilities[x][y] = permanizeValue(); // since it is permanent this will turn all of its possibilities to 0
				}
			}
		}
		return possibilities;	// return all possible possibilities for every cell in the puzzle
	}
}