/*
 *****************************************************************************
 *****************************************************************************
 *	Program: 		Sudoku Solver											**
 *	Programmers:	Saquib Hafiz											**
 *	File Name:		BruteForce.java											**
 *	Due Date:		Tuesday, May 31st, 2011									**
 *	Purpose:		Holds and tries to solve the puzzle using a random brute**
 *					force algorithm. This is done by randomly guessing as 	**
 *					permuations as possible and see if they work; not very	**
 *					efective and slows downt he computer so it only runs 	**
 *					for 30 seconds.											**
 *****************************************************************************
 *****************************************************************************
*/

import java.io.*;
import java.util.Random;	// required to generate random (pseudo-random) numbers
import javax.swing.*;
import java.util.Date;

public class BruteForce
{
	public static int[][] initiateBruteForce (int[][] values, boolean[][] permanent, int[][][] possibilities)
	{
		boolean done = false, fine = false;
		int temp, sum = 0;
		long num_of_runs = 0;
		PrintWriter moveLog;

		// creates a window taht warns the user that it is executing the Brute Force algorithm
		JFrame warning = new JFrame("Warning");		// name of frame
		JLabel warning_label = new JLabel("Either the puzzle you have input is incorrect or the puzzle is to hard to solve logically and thus a brute force algorithm is now being applied");		// message to be displayed to user
		warning.add(warning_label);	// add the label to the frame
		warning.setSize(350,90);	// set the size for the frame
		warning.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	// only hide the window after [x] is clicked
		warning.setVisible(true);	// make the frame visible

		// variables --> that we can freely manipulate
		int [][] actual_values = values;
		int [][][] actual_possibilities = possibilities;

		// permanent values
		final int [][] original_values = values;
		final int [][][] original_possibilities = possibilities;

		LogicalSolver ls = new LogicalSolver();
		Random rnd = new Random ();	//System.currentTimeMillis()

		Date og = new Date ();	// this object is used to read the oroginal time in miliseconds
		long original_time = og.getTime();	// use the time object previously defined to take the current time
		long current_time = original_time;	// take this as the current time

		// this loops will apply the brute force algorithm for 30 seconds or till the puzzle is properly solved, whichever comes first
		while (((current_time - original_time) < 5000.0)&&(done ==  false))	// keep on doing until the puzzle is done perfectly or for 3 minutes
		{
			num_of_runs++;	// increment the number of runs

			try		// output the number of times this loop has been run to the Moves Log
			{
				moveLog = new PrintWriter (new FileOutputStream("moveLog.txt"));	// create object to access text file
				moveLog.println("Brute Force algorithm has been applied " + num_of_runs + " times.");	// write to file
				moveLog.close();	// close the object
			}
			catch (FileNotFoundException fnfe)	// catch the file not found exception
			{
				System.out.println("File Not Found Error : " + fnfe);	// display error message
				System.exit(0);	// close program
			}
			catch (IOException ioe)	// catch an i/o exception
			{
				System.out.println("I/O Error : " + ioe);	// display error message
				System.exit(0);	// close program
			}

			Date ct = new Date ();		// create another time object to get the ending time
			current_time = ct.getTime();	// get the ending time

			for(int a = 0; a < 9; a++)	// for every row
			{
				for(int b = 0; b < 9; b++)	// for every column
				{
					if (permanent[a][b] == false)
					{
						actual_values[a][b] = 0;	// reset unknows values to 0
					}
				}	// ending columns
			}	// ending rows

			possibilities = ls.allPossibilities(possibilities, permanent, actual_values);	// calculate all possibilities

			for(int a = 0; a < 9; a++)	// for every row
			{
				for(int b = 0; b < 9; b++)	// for every column
				{
					if (permanent[a][b] == false)	// if the cell is not permanent
					{
						actual_possibilities[a][b] = ls.checkPossibilities(actual_values,a,b);	// check its possibilites for the cell
						sum = 0;	// reset sum value
						for (int c = 0; c < 9; c++)
						{
							sum += actual_possibilities[a][b][c];	// take sum of all possibilites, if its 0 then there are no possibilites
						}
						if (sum == 0)	// if ther are no possibilites then re-start from the beginning
						{
							a = 8;
							b = 8;
						}
						do	// do till it makes sense
						{
							temp = rnd.nextInt(9);	// takes a random integer from 0(inclusive) to 9(exclusive)
							if (possibilities[a][b][temp] == 1)
							{
								actual_values[a][b] = temp+1;	// replace the value with this possibility
								fine = true;
							}
							else
							{
								fine = false;
							}
						}
						while (fine == false);	// keep on randomizing until it is one of the possibilities
					}
				}	// ending columns
			}	// ending rows

			done = ls.checkFinish(actual_values);	// checks if the puzzle is correctly done
		}

		return values;
	}
}