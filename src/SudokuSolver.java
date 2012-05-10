/*
 *****************************************************************************
 *****************************************************************************
 *	Program: 		Sudoku Solver											**
 *	Programmers:	Saquib Hafiz											**
 *	File Name:		SudokuSolver.java										**
 *	Due Date:		Tuesday, May 31st, 2011									**
 *	Purpose:		Creates the GUI for the display of the input and output	**
 *					of the program. Holds action and mouse listeners too.	**
 *					This is the main start of the program and is the core	**
 *					thus everything is branched of this class.				**
 *****************************************************************************
 *****************************************************************************
*/

// 	Code produced by: Saquib Hafiz (methods were produced together in class)

import java.io.*;			// required to access PrintWriter and Exception classes
import java.awt.*;			// required for graphical interface interaction
import java.awt.event.*;	// required for event manipulation
import java.util.Date;		// required to access time
import javax.swing.*;		// required for graphical interface

public class SudokuSolver	// the main class
{
	// the main program
	public static void main (String [] args)
	{
		startMenu();	// the program starts and calls upon the start up menu
	}

	// the start-up menu with gui and full options to start solving, read instrctions or exit
	public static void startMenu ()		// return type : none | parameters : none
	{
		final JFrame menu_frame = new JFrame("Sudoku Solver");	// create the frame for input
	    menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// close program when [x] is clicked

	    menu_frame.setLayout(new FlowLayout(1, 25, 25)); //set layout

	    final JButton inputProblem = new JButton("Enter Problem");
	    final JButton showInstructions = new JButton ("Instructions");
	    final JButton getOut = new JButton("Exit");

	    ActionListener inputClicked = new ActionListener()	// if the user clicks to input a puzzle
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		inputPuzzle();	// the mthod used to input a puzzle
      		}
    	};
    	ActionListener instructionsClicked = new ActionListener()	// if instructions is clicked
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		outputInstructions();	// open the Instructions manual
      		}
    	};
    	ActionListener exitClicked = new ActionListener()	// if exit is clicked
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		System.exit(0);		// exit program
      		}
    	};

		// add the corresponding ActionListener to each button
		inputProblem.addActionListener(inputClicked);			// input problem
		showInstructions.addActionListener(instructionsClicked);// read instructions
		getOut.addActionListener(exitClicked);					// exit

	    Container contentPane = menu_frame.getContentPane();	// create a container

	    contentPane.add(inputProblem);		// add the input option to the container containing the menu_frame
	    contentPane.add(showInstructions);	// add the instructions option to the container containing the menu_frame
	    contentPane.add(getOut);			// add the exit option to the container containing the menu_frame

	    menu_frame.setSize(400, 100);	// set the window to size 200px x 300px
	    menu_frame.setVisible(true);	// make the frame visible
	}

	public static void outputInstructions()
	{
		JFrame intructions = new JFrame("Instructions");	// create a new frame for the instructions
		intructions.setLayout(new GridLayout(0, 1, 5, 5));	// give it a grid layout

		Container instructionsPane = intructions.getContentPane();	// create a content pane for it

		// an array of labels hold the instructions
		JLabel [] intructionsLabel = new JLabel[6];
		intructionsLabel[0] = new JLabel ("The purpose of this program is to solve Sudoku puzzles for the user.");
		intructionsLabel[1] = new JLabel ("To solve a puzzle you mut first click [Enter Problem].");
		intructionsLabel[2] = new JLabel ("After that you may input the puzzle using either your mouse butons or scroll wheel.");
		intructionsLabel[3] = new JLabel ("You will notice that when the value is 0 it is dark and when it is light is has an actual value.");
		intructionsLabel[4] = new JLabel ("Therefore if the number is missing leave it as 0, else replace all the given numbers accordingly.");
		intructionsLabel[5] = new JLabel ("Please make sure to make no errors or else the program will crash!");

		// add the messages to the content pane
		for (int g = 0; g < intructionsLabel.length; g++)
		{
			instructionsPane.add(intructionsLabel[g]);
		}

		intructions.pack();	// pack the frame so it can fit all of its content
		intructions.setVisible(true);	// make the frame/pane visible
	}

	// change the value of a button as it is clicked
	public static int valueCalculator (int modifiers, int[][] values, int x, int y)	// return type : int | parameters : int, int[][], int, int
	{
		if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)	// if its a left click
    	{
        	values[x][y]++;			// increase value
        	if (values[x][y] > 9)	// if the value goes above 9
        	{
        		values[x][y] = 0;	// then reset is back to 0
        	}
        }
        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)	// if its a right click
        {
        	values[x][y]--;			// decrease value
        	if (values[x][y] < 0)	// if the value below above 0
        	{
        		values[x][y] = 9;	// then reset is back to 9
        	}
  		}
		return values[x][y];	// return this value
	}

	// calculates the value when the mouse wheel is scrolled
	public static int valueCalculator (boolean up, int[][] values, int x, int y)	// return type : int | parameters : boolean, int[][], int, int
	{
		if (up == true)	// if its a left click
    	{
        	values[x][y]++;			// increase value
        	if (values[x][y] > 9)	// if the value goes above 9
        	{
        		values[x][y] = 0;	// then reset is back to 0
        	}
        }
        if (up == false)	// if its a right click
        {
        	values[x][y]--;			// decrease value
        	if (values[x][y] < 0)	// if the value below above 0
        	{
        		values[x][y] = 9;	// then reset is back to 9
        	}
  		}
		return values[x][y];	// return this value
	}

	// change the text on a button depending on its corresponding value
	public static String choose (int value)		// return type : String | parameters : int
	{
		String outputValue = "!";	// if the value is none of them then there is a serious porblem
		switch (value)
		{
			case 0:	outputValue = "0";	break;	// if the value is 0
			case 1:	outputValue = "1";	break;	// if the value is 1
			case 2:	outputValue = "2";	break;	// if the value is 2
			case 3: outputValue = "3";	break;	// if the value is 3
			case 4:	outputValue = "4";	break;	// if the value is 4
			case 5:	outputValue = "5";	break;	// if the value is 5
			case 6:	outputValue = "6";	break;	// if the value is 6
			case 7:	outputValue = "7";	break;	// if the value is 7
			case 8:	outputValue = "8";	break;	// if the value is 8
			case 9:	outputValue = "9";	break;	// if the value is 9
		}
		return outputValue;	// return this string value to output as the text of the button
	}

	// used to input the Sudoku Puzzle, it includes the GUI for the input screen
	public static void inputPuzzle ()	// return type : none | parameters : none
	{
		// colour set one for when the value is 0
		final Color naturalColour[] = new Color [2];
		naturalColour[0] = new Color(2,71,105);
		naturalColour[1] = new Color(0,98,159);

		// colour set two for when the alue is not 0
		final Color activeColour[] = new Color [2];
		activeColour[0] = new Color(155,225,251);
		activeColour[1] = new Color(197,239,253);

		final JFrame input_frame = new JFrame("Sudoku");	// create the frame for input
	    input_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	// close program when [x] is clicked
	    input_frame.setLayout(new GridLayout(0, 9, 3, 3)); //set layout

	    final JButton[][] button = new JButton[9][9]; //names the 9x9 grid of buttons

		final int[][] values = new int [9][9];	// values that are being manipulated, elements can be manipulated by the entire matrix cannot me manipulated

		JMenuBar menu_bar = new JMenuBar();		// Menu Bar

		JMenu menuFile = new JMenu("File");		// Menu Bar Option File
		JMenu menuAction = new JMenu("Action");	// Menu Bar Option Action

		JMenuItem menuoptionInstructions = new JMenuItem("Instructions");	// Menu Option under FIle
		JMenuItem menuoptionExit = new JMenuItem("Exit");					// Menu Option under FIle

		JMenuItem menuoptionSolve = new JMenuItem("Solve");		// Menu Option under Action
		JMenuItem menuoptionClear = new JMenuItem("Clear");		// Menu Option under Action

		// MouseListener for each button
		// each time a button is clicked the value is changed for that position and the text changes for the button
		MouseListener mouseListener00 = new MouseAdapter ()		// for button[0][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][0] = valueCalculator(modifiers, values, 0, 0);	// what to do with this action
				button[0][0].setText(choose(values[0][0]));			// change button text according to change in value
				if (values[0][0] != 0)
				{
					button[0][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener01 = new MouseAdapter ()		// for button[0][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][1] = valueCalculator(modifiers, values, 0, 1);	// what to do with this action
				button[0][1].setText(choose(values[0][1]));			// change button text according to change in value
				if (values[0][1] != 0)
				{
					button[0][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener02 = new MouseAdapter ()		// for button[0][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][2] = valueCalculator(modifiers, values, 0, 2);	// what to do with this action
				button[0][2].setText(choose(values[0][2]));			// change button text according to change in value
				if (values[0][2] != 0)
				{
					button[0][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener03 = new MouseAdapter ()		// for button[0][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][3] = valueCalculator(modifiers, values, 0, 3);	// what to do with this action
				button[0][3].setText(choose(values[0][3]));			// change button text according to change in value
				if (values[0][3] != 0)
				{
					button[0][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener04 = new MouseAdapter ()		// for button[0][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][4] = valueCalculator(modifiers, values, 0, 4);	// what to do with this action
				button[0][4].setText(choose(values[0][4]));			// change button text according to change in value
				if (values[0][4] != 0)
				{
					button[0][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener05 = new MouseAdapter ()		// for button[0][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][5] = valueCalculator(modifiers, values, 0, 5);	// what to do with this action
				button[0][5].setText(choose(values[0][5]));			// change button text according to change in value
				if (values[0][5] != 0)
				{
					button[0][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener06 = new MouseAdapter ()		// for button[0][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][6] = valueCalculator(modifiers, values, 0, 6);	// what to do with this action
				button[0][6].setText(choose(values[0][6]));			// change button text according to change in value
				if (values[0][6] != 0)
				{
					button[0][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener07 = new MouseAdapter ()		// for button[0][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][7] = valueCalculator(modifiers, values, 0, 7);	// what to do with this action
				button[0][7].setText(choose(values[0][7]));			// change button text according to change in value
				if (values[0][7] != 0)
				{
					button[0][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener08 = new MouseAdapter ()		// for button[0][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[0][8] = valueCalculator(modifiers, values, 0, 8);	// what to do with this action
				button[0][8].setText(choose(values[0][8]));			// change button text according to change in value
				if (values[0][8] != 0)
				{
					button[0][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener10 = new MouseAdapter ()		// for button[1][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][0] = valueCalculator(modifiers, values, 1, 0);	// what to do with this action
				button[1][0].setText(choose(values[1][0]));			// change button text according to change in value
				if (values[1][0] != 0)
				{
					button[1][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener11 = new MouseAdapter ()		// for button[1][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][1] = valueCalculator(modifiers, values, 1, 1);	// what to do with this action
				button[1][1].setText(choose(values[1][1]));			// change button text according to change in value
				if (values[1][1] != 0)
				{
					button[1][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener12 = new MouseAdapter ()		// for button[1][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][2] = valueCalculator(modifiers, values, 1, 2);	// what to do with this action
				button[1][2].setText(choose(values[1][2]));			// change button text according to change in value
				if (values[1][2] != 0)
				{
					button[1][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener13 = new MouseAdapter ()		// for button[1][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][3] = valueCalculator(modifiers, values, 1, 3);	// what to do with this action
				button[1][3].setText(choose(values[1][3]));			// change button text according to change in value
				if (values[1][3] != 0)
				{
					button[1][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener14 = new MouseAdapter ()		// for button[1][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][4] = valueCalculator(modifiers, values, 1, 4);	// what to do with this action
				button[1][4].setText(choose(values[1][4]));			// change button text according to change in value
				if (values[1][4] != 0)
				{
					button[1][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener15 = new MouseAdapter ()		// for button[1][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][5] = valueCalculator(modifiers, values, 1, 5);	// what to do with this action
				button[1][5].setText(choose(values[1][5]));			// change button text according to change in value
				if (values[1][5] != 0)
				{
					button[1][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener16 = new MouseAdapter ()		// for button[1][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][6] = valueCalculator(modifiers, values, 1, 6);	// what to do with this action
				button[1][6].setText(choose(values[1][6]));			// change button text according to change in value
				if (values[1][6] != 0)
				{
					button[1][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener17 = new MouseAdapter ()		// for button[1][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][7] = valueCalculator(modifiers, values, 1, 7);	// what to do with this action
				button[1][7].setText(choose(values[1][7]));			// change button text according to change in value
				if (values[1][7] != 0)
				{
					button[1][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener18 = new MouseAdapter ()		// for button[1][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[1][8] = valueCalculator(modifiers, values, 1, 8);	// what to do with this action
				button[1][8].setText(choose(values[1][8]));			// change button text according to change in value
				if (values[1][8] != 0)
				{
					button[1][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener20 = new MouseAdapter ()		// for button[2][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][0] = valueCalculator(modifiers, values, 2, 0);	// what to do with this action
				button[2][0].setText(choose(values[2][0]));			// change button text according to change in value
				if (values[2][0] != 0)
				{
					button[2][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener21 = new MouseAdapter ()		// for button[2][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][1] = valueCalculator(modifiers, values, 2, 1);	// what to do with this action
				button[2][1].setText(choose(values[2][1]));			// change button text according to change in value
				if (values[2][1] != 0)
				{
					button[2][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener22 = new MouseAdapter ()		// for button[2][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][2] = valueCalculator(modifiers, values, 2, 2);	// what to do with this action
				button[2][2].setText(choose(values[2][2]));			// change button text according to change in value
				if (values[2][2] != 0)
				{
					button[2][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener23 = new MouseAdapter ()		// for button[2][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][3] = valueCalculator(modifiers, values, 2, 3);	// what to do with this action
				button[2][3].setText(choose(values[2][3]));			// change button text according to change in value
				if (values[2][3] != 0)
				{
					button[2][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener24 = new MouseAdapter ()		// for button[2][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][4] = valueCalculator(modifiers, values, 2, 4);	// what to do with this action
				button[2][4].setText(choose(values[2][4]));			// change button text according to change in value
				if (values[2][4] != 0)
				{
					button[2][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener25 = new MouseAdapter ()		// for button[2][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][5] = valueCalculator(modifiers, values, 2, 5);	// what to do with this action
				button[2][5].setText(choose(values[2][5]));			// change button text according to change in value
				if (values[2][5] != 0)

				{
					button[2][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener26 = new MouseAdapter ()		// for button[2][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][6] = valueCalculator(modifiers, values, 2, 6);	// what to do with this action
				button[2][6].setText(choose(values[2][6]));			// change button text according to change in value
				if (values[2][6] != 0)
				{
					button[2][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener27 = new MouseAdapter ()		// for button[2][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][7] = valueCalculator(modifiers, values, 2, 7);	// what to do with this action
				button[2][7].setText(choose(values[2][7]));			// change button text according to change in value
				if (values[2][7] != 0)
				{
					button[2][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener28 = new MouseAdapter ()		// for button[2][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[2][8] = valueCalculator(modifiers, values, 2, 8);	// what to do with this action
				button[2][8].setText(choose(values[2][8]));			// change button text according to change in value
				if (values[2][8] != 0)
				{
					button[2][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener30 = new MouseAdapter ()		// for button[3][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][0] = valueCalculator(modifiers, values, 3, 0);	// what to do with this action
				button[3][0].setText(choose(values[3][0]));			// change button text according to change in value
				if (values[3][0] != 0)
				{
					button[3][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener31 = new MouseAdapter ()		// for button[3][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][1] = valueCalculator(modifiers, values, 3, 1);	// what to do with this action
				button[3][1].setText(choose(values[3][1]));			// change button text according to change in value
				if (values[3][1] != 0)
				{
					button[3][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener32 = new MouseAdapter ()		// for button[3][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][2] = valueCalculator(modifiers, values, 3, 2);	// what to do with this action
				button[3][2].setText(choose(values[3][2]));			// change button text according to change in value
				if (values[3][2] != 0)
				{
					button[3][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener33 = new MouseAdapter ()		// for button[3][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][3] = valueCalculator(modifiers, values, 3, 3);	// what to do with this action
				button[3][3].setText(choose(values[3][3]));			// change button text according to change in value
				if (values[3][3] != 0)
				{
					button[3][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener34 = new MouseAdapter ()		// for button[3][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][4] = valueCalculator(modifiers, values, 3, 4);	// what to do with this action
				button[3][4].setText(choose(values[3][4]));			// change button text according to change in value
				if (values[3][4] != 0)
				{
					button[3][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener35 = new MouseAdapter ()		// for button[3][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][5] = valueCalculator(modifiers, values, 3, 5);	// what to do with this action
				button[3][5].setText(choose(values[3][5]));			// change button text according to change in value
				if (values[3][5] != 0)
				{
					button[3][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener36 = new MouseAdapter ()		// for button[3][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][6] = valueCalculator(modifiers, values, 3, 6);	// what to do with this action
				button[3][6].setText(choose(values[3][6]));			// change button text according to change in value
				if (values[3][6] != 0)
				{
					button[3][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener37 = new MouseAdapter ()		// for button[3][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][7] = valueCalculator(modifiers, values, 3, 7);	// what to do with this action
				button[3][7].setText(choose(values[3][7]));			// change button text according to change in value
				if (values[3][7] != 0)
				{
					button[3][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener38 = new MouseAdapter ()		// for button[3][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[3][8] = valueCalculator(modifiers, values, 3, 8);	// what to do with this action
				button[3][8].setText(choose(values[3][8]));			// change button text according to change in value
				if (values[3][8] != 0)
				{
					button[3][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener40 = new MouseAdapter ()		// for button[4][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][0] = valueCalculator(modifiers, values, 4, 0);	// what to do with this action
				button[4][0].setText(choose(values[4][0]));			// change button text according to change in value
				if (values[4][0] != 0)
				{
					button[4][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener41 = new MouseAdapter ()		// for button[4][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][1] = valueCalculator(modifiers, values, 4, 1);	// what to do with this action
				button[4][1].setText(choose(values[4][1]));			// change button text according to change in value
				if (values[4][1] != 0)
				{
					button[4][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener42 = new MouseAdapter ()		// for button[4][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][2] = valueCalculator(modifiers, values, 4, 2);	// what to do with this action
				button[4][2].setText(choose(values[4][2]));			// change button text according to change in value
				if (values[4][2] != 0)
				{
					button[4][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener43 = new MouseAdapter ()		// for button[4][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][3] = valueCalculator(modifiers, values, 4, 3);	// what to do with this action
				button[4][3].setText(choose(values[4][3]));			// change button text according to change in value
				if (values[4][3] != 0)
				{
					button[4][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener44 = new MouseAdapter ()		// for button[4][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][4] = valueCalculator(modifiers, values, 4, 4);	// what to do with this action
				button[4][4].setText(choose(values[4][4]));			// change button text according to change in value
				if (values[4][4] != 0)
				{
					button[4][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener45 = new MouseAdapter ()		// for button[4][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][5] = valueCalculator(modifiers, values, 4, 5);	// what to do with this action
				button[4][5].setText(choose(values[4][5]));			// change button text according to change in value
				if (values[4][5] != 0)
				{
					button[4][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener46 = new MouseAdapter ()		// for button[4][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][6] = valueCalculator(modifiers, values, 4, 6);	// what to do with this action
				button[4][6].setText(choose(values[4][6]));			// change button text according to change in value
				if (values[4][6] != 0)
				{
					button[4][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener47 = new MouseAdapter ()		// for button[4][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][7] = valueCalculator(modifiers, values, 4, 7);	// what to do with this action
				button[4][7].setText(choose(values[4][7]));			// change button text according to change in value
				if (values[4][7] != 0)
				{
					button[4][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener48 = new MouseAdapter ()		// for button[4][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[4][8] = valueCalculator(modifiers, values, 4, 8);	// what to do with this action
				button[4][8].setText(choose(values[4][8]));			// change button text according to change in value
				if (values[4][8] != 0)
				{
					button[4][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener50 = new MouseAdapter ()		// for button[5][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][0] = valueCalculator(modifiers, values, 5, 0);	// what to do with this action
				button[5][0].setText(choose(values[5][0]));			// change button text according to change in value
				if (values[5][0] != 0)
				{
					button[5][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener51 = new MouseAdapter ()		// for button[5][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][1] = valueCalculator(modifiers, values, 5, 1);	// what to do with this action
				button[5][1].setText(choose(values[5][1]));			// change button text according to change in value
				if (values[5][1] != 0)
				{
					button[5][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener52 = new MouseAdapter ()		// for button[5][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][2] = valueCalculator(modifiers, values, 5, 2);	// what to do with this action
				button[5][2].setText(choose(values[5][2]));			// change button text according to change in value
				if (values[5][2] != 0)
				{
					button[5][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener53 = new MouseAdapter ()		// for button[5][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][3] = valueCalculator(modifiers, values, 5, 3);	// what to do with this action
				button[5][3].setText(choose(values[5][3]));			// change button text according to change in value
				if (values[5][3] != 0)
				{
					button[5][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener54 = new MouseAdapter ()		// for button[5][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][4] = valueCalculator(modifiers, values, 5, 4);	// what to do with this action
				button[5][4].setText(choose(values[5][4]));			// change button text according to change in value
				if (values[5][4] != 0)
				{
					button[5][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener55 = new MouseAdapter ()		// for button[5][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][5] = valueCalculator(modifiers, values, 5, 5);	// what to do with this action
				button[5][5].setText(choose(values[5][5]));			// change button text according to change in value
				if (values[5][5] != 0)
				{
					button[5][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener56 = new MouseAdapter ()		// for button[5][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][6] = valueCalculator(modifiers, values, 5, 6);	// what to do with this action
				button[5][6].setText(choose(values[5][6]));			// change button text according to change in value
				if (values[5][6] != 0)
				{
					button[5][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener57 = new MouseAdapter ()		// for button[5][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][7] = valueCalculator(modifiers, values, 5, 7);	// what to do with this action
				button[5][7].setText(choose(values[5][7]));			// change button text according to change in value
				if (values[5][7] != 0)
				{
					button[5][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener58 = new MouseAdapter ()		// for button[5][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[5][8] = valueCalculator(modifiers, values, 5, 8);	// what to do with this action
				button[5][8].setText(choose(values[5][8]));			// change button text according to change in value
				if (values[5][8] != 0)
				{
					button[5][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener60 = new MouseAdapter ()		// for button[6][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][0] = valueCalculator(modifiers, values, 6, 0);	// what to do with this action
				button[6][0].setText(choose(values[6][0]));			// change button text according to change in value
				if (values[6][0] != 0)
				{
					button[6][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener61 = new MouseAdapter ()		// for button[6][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][1] = valueCalculator(modifiers, values, 6, 1);	// what to do with this action
				button[6][1].setText(choose(values[6][1]));			// change button text according to change in value
				if (values[6][1] != 0)
				{
					button[6][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener62 = new MouseAdapter ()		// for button[6][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][2] = valueCalculator(modifiers, values, 6, 2);	// what to do with this action
				button[6][2].setText(choose(values[6][2]));			// change button text according to change in value
				if (values[6][2] != 0)
				{
					button[6][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener63 = new MouseAdapter ()		// for button[6][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][3] = valueCalculator(modifiers, values, 6, 3);	// what to do with this action
				button[6][3].setText(choose(values[6][3]));			// change button text according to change in value
				if (values[6][3] != 0)
				{
					button[6][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener64 = new MouseAdapter ()		// for button[6][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][4] = valueCalculator(modifiers, values, 6, 4);	// what to do with this action
				button[6][4].setText(choose(values[6][4]));			// change button text according to change in value
				if (values[6][4] != 0)
				{
					button[6][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener65 = new MouseAdapter ()		// for button[6][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][5] = valueCalculator(modifiers, values, 6, 5);	// what to do with this action
				button[6][5].setText(choose(values[6][5]));			// change button text according to change in value
				if (values[6][5] != 0)
				{
					button[6][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener66 = new MouseAdapter ()		// for button[6][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][6] = valueCalculator(modifiers, values, 6, 6);	// what to do with this action
				button[6][6].setText(choose(values[6][6]));			// change button text according to change in value
				if (values[6][6] != 0)
				{
					button[6][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener67 = new MouseAdapter ()		// for button[6][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][7] = valueCalculator(modifiers, values, 6, 7);	// what to do with this action
				button[6][7].setText(choose(values[6][7]));			// change button text according to change in value
				if (values[6][7] != 0)
				{
					button[6][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener68 = new MouseAdapter ()		// for button[6][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[6][8] = valueCalculator(modifiers, values, 6, 8);	// what to do with this action
				button[6][8].setText(choose(values[6][8]));			// change button text according to change in value
				if (values[6][8] != 0)
				{
					button[6][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener70 = new MouseAdapter ()		// for button[7][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][0] = valueCalculator(modifiers, values, 7, 0);	// what to do with this action
				button[7][0].setText(choose(values[7][0]));			// change button text according to change in value
				if (values[7][0] != 0)
				{
					button[7][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener71 = new MouseAdapter ()		// for button[7][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][1] = valueCalculator(modifiers, values, 7, 1);	// what to do with this action
				button[7][1].setText(choose(values[7][1]));			// change button text according to change in value
				if (values[7][1] != 0)
				{
					button[7][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener72 = new MouseAdapter ()		// for button[7][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][2] = valueCalculator(modifiers, values, 7, 2);	// what to do with this action
				button[7][2].setText(choose(values[7][2]));			// change button text according to change in value
				if (values[7][2] != 0)
				{
					button[7][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener73 = new MouseAdapter ()		// for button[7][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][3] = valueCalculator(modifiers, values, 7, 3);	// what to do with this action
				button[7][3].setText(choose(values[7][3]));			// change button text according to change in value
				if (values[7][3] != 0)
				{
					button[7][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener74 = new MouseAdapter ()		// for button[7][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][4] = valueCalculator(modifiers, values, 7, 4);	// what to do with this action
				button[7][4].setText(choose(values[7][4]));			// change button text according to change in value
				if (values[7][4] != 0)
				{
					button[7][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][4].setBackground(naturalColour[0]);	// sets the buttons original background colour

				}
			}
		};
		MouseListener mouseListener75 = new MouseAdapter ()		// for button[7][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][5] = valueCalculator(modifiers, values, 7, 5);	// what to do with this action
				button[7][5].setText(choose(values[7][5]));			// change button text according to change in value
				if (values[7][5] != 0)
				{
					button[7][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener76 = new MouseAdapter ()		// for button[7][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][6] = valueCalculator(modifiers, values, 7, 6);	// what to do with this action
				button[7][6].setText(choose(values[7][6]));			// change button text according to change in value
				if (values[7][6] != 0)
				{
					button[7][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener77 = new MouseAdapter ()		// for button[7][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][7] = valueCalculator(modifiers, values, 7, 7);	// what to do with this action
				button[7][7].setText(choose(values[7][7]));			// change button text according to change in value
				if (values[7][7] != 0)
				{
					button[7][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener78 = new MouseAdapter ()		// for button[7][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[7][8] = valueCalculator(modifiers, values, 7, 8);	// what to do with this action
				button[7][8].setText(choose(values[7][8]));			// change button text according to change in value
				if (values[7][8] != 0)
				{
					button[7][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener80 = new MouseAdapter ()		// for button[8][0]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][0] = valueCalculator(modifiers, values, 8, 0);	// what to do with this action
				button[8][0].setText(choose(values[8][0]));			// change button text according to change in value
				if (values[8][0] != 0)
				{
					button[8][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener81 = new MouseAdapter ()		// for button[8][1]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][1] = valueCalculator(modifiers, values, 8, 1);	// what to do with this action
				button[8][1].setText(choose(values[8][1]));			// change button text according to change in value
				if (values[8][1] != 0)
				{
					button[8][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener82 = new MouseAdapter ()		// for button[8][2]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][2] = valueCalculator(modifiers, values, 8, 2);	// what to do with this action
				button[8][2].setText(choose(values[8][2]));			// change button text according to change in value
				if (values[8][2] != 0)
				{
					button[8][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener83 = new MouseAdapter ()		// for button[8][3]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][3] = valueCalculator(modifiers, values, 8, 3);	// what to do with this action
				button[8][3].setText(choose(values[8][3]));			// change button text according to change in value
				if (values[8][3] != 0)
				{
					button[8][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener84 = new MouseAdapter ()		// for button[8][4]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][4] = valueCalculator(modifiers, values, 8, 4);	// what to do with this action
				button[8][4].setText(choose(values[8][4]));			// change button text according to change in value
				if (values[8][4] != 0)
				{
					button[8][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener85 = new MouseAdapter ()		// for button[8][5]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][5] = valueCalculator(modifiers, values, 8, 5);	// what to do with this action
				button[8][5].setText(choose(values[8][5]));			// change button text according to change in value
				if (values[8][5] != 0)
				{
					button[8][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener86 = new MouseAdapter ()		// for button[8][6]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][6] = valueCalculator(modifiers, values, 8, 6);	// what to do with this action
				button[8][6].setText(choose(values[8][6]));			// change button text according to change in value
				if (values[8][6] != 0)
				{
					button[8][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener87 = new MouseAdapter ()		// for button[8][7]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][7] = valueCalculator(modifiers, values, 8, 7);	// what to do with this action
				button[8][7].setText(choose(values[8][7]));			// change button text according to change in value
				if (values[8][7] != 0)
				{
					button[8][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseListener mouseListener88 = new MouseAdapter ()		// for button[8][8]
		{
			public void mousePressed(MouseEvent mouseEvent) 			// which mouse button is clicked
			{
				int modifiers = mouseEvent.getModifiers();
				values[8][8] = valueCalculator(modifiers, values, 8, 8);	// what to do with this action
				button[8][8].setText(choose(values[8][8]));			// change button text according to change in value
				if (values[8][8] != 0)
				{
					button[8][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};

		//MouseWheelListeners:
		MouseWheelListener mouseWheelListener00 = new MouseWheelListener ()		// for button[0][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][0] = valueCalculator(up, values, 0, 0);	// what to do with this action
				button[0][0].setText(choose(values[0][0]));			// change button text according to change in value
				if (values[0][0] != 0)
				{
					button[0][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener01 = new MouseWheelListener ()		// for button[0][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][1] = valueCalculator(up, values, 0, 1);	// what to do with this action

				button[0][1].setText(choose(values[0][1]));			// change button text according to change in value
				if (values[0][1] != 0)
				{
					button[0][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener02 = new MouseWheelListener ()		// for button[0][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][2] = valueCalculator(up, values, 0, 2);	// what to do with this action
				button[0][2].setText(choose(values[0][2]));			// change button text according to change in value
				if (values[0][2] != 0)
				{
					button[0][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener10 = new MouseWheelListener ()		// for button[1][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][0] = valueCalculator(up, values, 1, 0);	// what to do with this action
				button[1][0].setText(choose(values[1][0]));			// change button text according to change in value
				if (values[1][0] != 0)
				{
					button[1][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener11 = new MouseWheelListener ()		// for button[1][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][1] = valueCalculator(up, values, 1, 1);	// what to do with this action
				button[1][1].setText(choose(values[1][1]));			// change button text according to change in value
				if (values[1][1] != 0)
				{
					button[1][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener12 = new MouseWheelListener ()		// for button[1][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][2] = valueCalculator(up, values, 1, 2);	// what to do with this action
				button[1][2].setText(choose(values[1][2]));			// change button text according to change in value
				if (values[1][2] != 0)
				{
					button[1][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener20 = new MouseWheelListener ()		// for button[2][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][0] = valueCalculator(up, values, 2, 0);	// what to do with this action
				button[2][0].setText(choose(values[2][0]));			// change button text according to change in value
				if (values[2][0] != 0)
				{
					button[2][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener21 = new MouseWheelListener ()		// for button[2][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][1] = valueCalculator(up, values, 2, 1);	// what to do with this action
				button[2][1].setText(choose(values[2][1]));			// change button text according to change in value
				if (values[2][1] != 0)
				{
					button[2][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener22 = new MouseWheelListener ()		// for button[2][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][2] = valueCalculator(up, values, 2, 2);	// what to do with this action
				button[2][2].setText(choose(values[2][2]));			// change button text according to change in value
				if (values[2][2] != 0)
				{
					button[2][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener30 = new MouseWheelListener ()		// for button[3][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][0] = valueCalculator(up, values, 3, 0);	// what to do with this action
				button[3][0].setText(choose(values[3][0]));			// change button text according to change in value
				if (values[3][0] != 0)
				{
					button[3][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener31 = new MouseWheelListener ()		// for button[3][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][1] = valueCalculator(up, values, 3, 1);	// what to do with this action

				button[3][1].setText(choose(values[3][1]));			// change button text according to change in value
				if (values[3][1] != 0)
				{
					button[3][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener32 = new MouseWheelListener ()		// for button[3][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][2] = valueCalculator(up, values, 3, 2);	// what to do with this action
				button[3][2].setText(choose(values[3][2]));			// change button text according to change in value
				if (values[3][2] != 0)
				{
					button[3][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener40 = new MouseWheelListener ()		// for button[4][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][0] = valueCalculator(up, values, 4, 0);	// what to do with this action
				button[4][0].setText(choose(values[4][0]));			// change button text according to change in value
				if (values[4][0] != 0)
				{
					button[4][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener41 = new MouseWheelListener ()		// for button[4][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][1] = valueCalculator(up, values, 4, 1);	// what to do with this action
				button[4][1].setText(choose(values[4][1]));			// change button text according to change in value
				if (values[4][1] != 0)
				{
					button[4][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener42 = new MouseWheelListener ()		// for button[4][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][2] = valueCalculator(up, values, 4, 2);	// what to do with this action
				button[4][2].setText(choose(values[4][2]));			// change button text according to change in value
				if (values[4][2] != 0)
				{
					button[4][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener50 = new MouseWheelListener ()		// for button[5][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][0] = valueCalculator(up, values, 5, 0);	// what to do with this action
				button[5][0].setText(choose(values[5][0]));			// change button text according to change in value
				if (values[5][0] != 0)
				{
					button[5][0].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener51 = new MouseWheelListener ()		// for button[5][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][1] = valueCalculator(up, values, 5, 1);	// what to do with this action
				button[5][1].setText(choose(values[5][1]));			// change button text according to change in value
				if (values[5][1] != 0)
				{
					button[5][1].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener52 = new MouseWheelListener ()		// for button[5][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][2] = valueCalculator(up, values, 5, 2);	// what to do with this action
				button[5][2].setText(choose(values[5][2]));			// change button text according to change in value
				if (values[5][2] != 0)
				{
					button[5][2].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener06 = new MouseWheelListener ()		// for button[0][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][6] = valueCalculator(up, values, 0, 6);	// what to do with this action
				button[0][6].setText(choose(values[0][6]));			// change button text according to change in value
				if (values[0][6] != 0)
				{
					button[0][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener07 = new MouseWheelListener ()		// for button[0][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][7] = valueCalculator(up, values, 0, 7);	// what to do with this action

				button[0][7].setText(choose(values[0][7]));			// change button text according to change in value
				if (values[0][7] != 0)
				{
					button[0][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener08 = new MouseWheelListener ()		// for button[0][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][8] = valueCalculator(up, values, 0, 8);	// what to do with this action
				button[0][8].setText(choose(values[0][8]));			// change button text according to change in value
				if (values[0][8] != 0)
				{
					button[0][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[0][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener16 = new MouseWheelListener ()		// for button[1][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][6] = valueCalculator(up, values, 1, 6);	// what to do with this action
				button[1][6].setText(choose(values[1][6]));			// change button text according to change in value
				if (values[1][6] != 0)
				{
					button[1][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener17 = new MouseWheelListener ()		// for button[1][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][7] = valueCalculator(up, values, 1, 7);	// what to do with this action
				button[1][7].setText(choose(values[1][7]));			// change button text according to change in value
				if (values[1][7] != 0)
				{
					button[1][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener18 = new MouseWheelListener ()		// for button[1][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][8] = valueCalculator(up, values, 1, 8);	// what to do with this action
				button[1][8].setText(choose(values[1][8]));			// change button text according to change in value
				if (values[1][8] != 0)
				{
					button[1][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[1][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener26 = new MouseWheelListener ()		// for button[2][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][6] = valueCalculator(up, values, 2, 6);	// what to do with this action
				button[2][6].setText(choose(values[2][6]));			// change button text according to change in value
				if (values[2][6] != 0)
				{
					button[2][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener27 = new MouseWheelListener ()		// for button[2][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][7] = valueCalculator(up, values, 2, 7);	// what to do with this action
				button[2][7].setText(choose(values[2][7]));			// change button text according to change in value
				if (values[2][7] != 0)
				{
					button[2][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener28 = new MouseWheelListener ()		// for button[2][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][8] = valueCalculator(up, values, 2, 8);	// what to do with this action
				button[2][8].setText(choose(values[2][8]));			// change button text according to change in value
				if (values[2][8] != 0)
				{
					button[2][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[2][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener03 = new MouseWheelListener ()		// for button[0][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][3] = valueCalculator(up, values, 0, 3);	// what to do with this action
				button[0][3].setText(choose(values[0][3]));			// change button text according to change in value
				if (values[0][3] != 0)
				{
					button[0][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener04 = new MouseWheelListener ()		// for button[0][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][4] = valueCalculator(up, values, 0, 4);	// what to do with this action

				button[0][4].setText(choose(values[0][4]));			// change button text according to change in value
				if (values[0][4] != 0)
				{
					button[0][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener05 = new MouseWheelListener ()		// for button[0][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[0][5] = valueCalculator(up, values, 0, 5);	// what to do with this action
				button[0][5].setText(choose(values[0][5]));			// change button text according to change in value
				if (values[0][5] != 0)
				{
					button[0][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[0][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener13 = new MouseWheelListener ()		// for button[1][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][3] = valueCalculator(up, values, 1, 3);	// what to do with this action
				button[1][3].setText(choose(values[1][3]));			// change button text according to change in value
				if (values[1][3] != 0)
				{
					button[1][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener14 = new MouseWheelListener ()		// for button[1][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][4] = valueCalculator(up, values, 1, 4);	// what to do with this action
				button[1][4].setText(choose(values[1][4]));			// change button text according to change in value
				if (values[1][4] != 0)
				{
					button[1][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener15 = new MouseWheelListener ()		// for button[1][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[1][5] = valueCalculator(up, values, 1, 5);	// what to do with this action
				button[1][5].setText(choose(values[1][5]));			// change button text according to change in value
				if (values[1][5] != 0)
				{
					button[1][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[1][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener23 = new MouseWheelListener ()		// for button[2][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][3] = valueCalculator(up, values, 2, 3);	// what to do with this action
				button[2][3].setText(choose(values[2][3]));			// change button text according to change in value
				if (values[2][3] != 0)
				{
					button[2][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener24 = new MouseWheelListener ()		// for button[2][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][4] = valueCalculator(up, values, 2, 4);	// what to do with this action
				button[2][4].setText(choose(values[2][4]));			// change button text according to change in value
				if (values[2][4] != 0)
				{
					button[2][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener25 = new MouseWheelListener ()		// for button[2][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[2][5] = valueCalculator(up, values, 2, 5);	// what to do with this action
				button[2][5].setText(choose(values[2][5]));			// change button text according to change in value
				if (values[2][5] != 0)
				{
					button[2][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[2][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener33 = new MouseWheelListener ()		// for button[3][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][3] = valueCalculator(up, values, 3, 3);	// what to do with this action
				button[3][3].setText(choose(values[3][3]));			// change button text according to change in value
				if (values[3][3] != 0)
				{
					button[3][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener34 = new MouseWheelListener ()		// for button[3][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][4] = valueCalculator(up, values, 3, 4);	// what to do with this action

				button[3][4].setText(choose(values[3][4]));			// change button text according to change in value
				if (values[3][4] != 0)
				{
					button[3][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener35 = new MouseWheelListener ()		// for button[3][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][5] = valueCalculator(up, values, 3, 5);	// what to do with this action
				button[3][5].setText(choose(values[3][5]));			// change button text according to change in value
				if (values[3][5] != 0)
				{
					button[3][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[3][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener43 = new MouseWheelListener ()		// for button[4][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][3] = valueCalculator(up, values, 4, 3);	// what to do with this action
				button[4][3].setText(choose(values[4][3]));			// change button text according to change in value
				if (values[4][3] != 0)
				{
					button[4][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener44 = new MouseWheelListener ()		// for button[4][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][4] = valueCalculator(up, values, 4, 4);	// what to do with this action
				button[4][4].setText(choose(values[4][4]));			// change button text according to change in value
				if (values[4][4] != 0)
				{
					button[4][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener45 = new MouseWheelListener ()		// for button[4][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][5] = valueCalculator(up, values, 4, 5);	// what to do with this action
				button[4][5].setText(choose(values[4][5]));			// change button text according to change in value
				if (values[4][5] != 0)
				{
					button[4][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[4][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener53 = new MouseWheelListener ()		// for button[5][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][3] = valueCalculator(up, values, 5, 3);	// what to do with this action
				button[5][3].setText(choose(values[5][3]));			// change button text according to change in value
				if (values[5][3] != 0)
				{
					button[5][3].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][3].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener54 = new MouseWheelListener ()		// for button[5][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][4] = valueCalculator(up, values, 5, 4);	// what to do with this action
				button[5][4].setText(choose(values[5][4]));			// change button text according to change in value
				if (values[5][4] != 0)
				{
					button[5][4].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][4].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener55 = new MouseWheelListener ()		// for button[5][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][5] = valueCalculator(up, values, 5, 5);	// what to do with this action
				button[5][5].setText(choose(values[5][5]));			// change button text according to change in value
				if (values[5][5] != 0)
				{
					button[5][5].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[5][5].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener36 = new MouseWheelListener ()		// for button[3][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][6] = valueCalculator(up, values, 3, 6);	// what to do with this action
				button[3][6].setText(choose(values[3][6]));			// change button text according to change in value
				if (values[3][6] != 0)
				{
					button[3][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener37 = new MouseWheelListener ()		// for button[3][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][7] = valueCalculator(up, values, 3, 7);	// what to do with this action

				button[3][7].setText(choose(values[3][7]));			// change button text according to change in value
				if (values[3][7] != 0)
				{
					button[3][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener38 = new MouseWheelListener ()		// for button[3][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[3][8] = valueCalculator(up, values, 3, 8);	// what to do with this action
				button[3][8].setText(choose(values[3][8]));			// change button text according to change in value
				if (values[3][8] != 0)
				{
					button[3][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[3][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener46 = new MouseWheelListener ()		// for button[4][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][6] = valueCalculator(up, values, 4, 6);	// what to do with this action
				button[4][6].setText(choose(values[4][6]));			// change button text according to change in value
				if (values[4][6] != 0)
				{
					button[4][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener47 = new MouseWheelListener ()		// for button[4][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][7] = valueCalculator(up, values, 4, 7);	// what to do with this action
				button[4][7].setText(choose(values[4][7]));			// change button text according to change in value
				if (values[4][7] != 0)
				{
					button[4][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener48 = new MouseWheelListener ()		// for button[4][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[4][8] = valueCalculator(up, values, 4, 8);	// what to do with this action
				button[4][8].setText(choose(values[4][8]));			// change button text according to change in value
				if (values[4][8] != 0)
				{
					button[4][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[4][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener56 = new MouseWheelListener ()		// for button[5][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][6] = valueCalculator(up, values, 5, 6);	// what to do with this action
				button[5][6].setText(choose(values[5][6]));			// change button text according to change in value
				if (values[5][6] != 0)
				{
					button[5][6].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener57 = new MouseWheelListener ()		// for button[5][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][7] = valueCalculator(up, values, 5, 7);	// what to do with this action
				button[5][7].setText(choose(values[5][7]));			// change button text according to change in value
				if (values[5][7] != 0)
				{
					button[5][7].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener58 = new MouseWheelListener ()		// for button[5][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[5][8] = valueCalculator(up, values, 5, 8);	// what to do with this action
				button[5][8].setText(choose(values[5][8]));			// change button text according to change in value
				if (values[5][8] != 0)
				{
					button[5][8].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[5][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener60 = new MouseWheelListener ()		// for button[6][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][0] = valueCalculator(up, values, 6, 0);	// what to do with this action
				button[6][0].setText(choose(values[6][0]));			// change button text according to change in value
				if (values[6][0] != 0)
				{
					button[6][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener61 = new MouseWheelListener ()		// for button[6][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][1] = valueCalculator(up, values, 6, 1);	// what to do with this action

				button[6][1].setText(choose(values[6][1]));			// change button text according to change in value
				if (values[6][1] != 0)
				{
					button[6][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener62 = new MouseWheelListener ()		// for button[6][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][2] = valueCalculator(up, values, 6, 2);	// what to do with this action
				button[6][2].setText(choose(values[6][2]));			// change button text according to change in value
				if (values[6][2] != 0)
				{
					button[6][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener70 = new MouseWheelListener ()		// for button[7][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][0] = valueCalculator(up, values, 7, 0);	// what to do with this action
				button[7][0].setText(choose(values[7][0]));			// change button text according to change in value
				if (values[7][0] != 0)
				{
					button[7][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener71 = new MouseWheelListener ()		// for button[7][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][1] = valueCalculator(up, values, 7, 1);	// what to do with this action
				button[7][1].setText(choose(values[7][1]));			// change button text according to change in value
				if (values[7][1] != 0)
				{
					button[7][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener72 = new MouseWheelListener ()		// for button[7][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][2] = valueCalculator(up, values, 7, 2);	// what to do with this action
				button[7][2].setText(choose(values[7][2]));			// change button text according to change in value
				if (values[7][2] != 0)
				{
					button[7][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener80 = new MouseWheelListener ()		// for button[8][0]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][0] = valueCalculator(up, values, 8, 0);	// what to do with this action
				button[8][0].setText(choose(values[8][0]));			// change button text according to change in value
				if (values[8][0] != 0)
				{
					button[8][0].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][0].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener81 = new MouseWheelListener ()		// for button[8][1]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][1] = valueCalculator(up, values, 8, 1);	// what to do with this action
				button[8][1].setText(choose(values[8][1]));			// change button text according to change in value
				if (values[8][1] != 0)
				{
					button[8][1].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][1].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener82 = new MouseWheelListener ()		// for button[8][2]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][2] = valueCalculator(up, values, 8, 2);	// what to do with this action
				button[8][2].setText(choose(values[8][2]));			// change button text according to change in value
				if (values[8][2] != 0)
				{
					button[8][2].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][2].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener63 = new MouseWheelListener ()		// for button[6][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][3] = valueCalculator(up, values, 6, 3);	// what to do with this action
				button[6][3].setText(choose(values[6][3]));			// change button text according to change in value
				if (values[6][3] != 0)
				{
					button[6][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener64 = new MouseWheelListener ()		// for button[6][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][4] = valueCalculator(up, values, 6, 4);	// what to do with this action

				button[6][4].setText(choose(values[6][4]));			// change button text according to change in value
				if (values[6][4] != 0)
				{
					button[6][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener65 = new MouseWheelListener ()		// for button[6][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][5] = valueCalculator(up, values, 6, 5);	// what to do with this action
				button[6][5].setText(choose(values[6][5]));			// change button text according to change in value
				if (values[6][5] != 0)
				{
					button[6][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[6][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener73 = new MouseWheelListener ()		// for button[7][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][3] = valueCalculator(up, values, 7, 3);	// what to do with this action
				button[7][3].setText(choose(values[7][3]));			// change button text according to change in value
				if (values[7][3] != 0)
				{
					button[7][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener74 = new MouseWheelListener ()		// for button[7][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][4] = valueCalculator(up, values, 7, 4);	// what to do with this action
				button[7][4].setText(choose(values[7][4]));			// change button text according to change in value
				if (values[7][4] != 0)
				{
					button[7][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener75 = new MouseWheelListener ()		// for button[7][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][5] = valueCalculator(up, values, 7, 5);	// what to do with this action
				button[7][5].setText(choose(values[7][5]));			// change button text according to change in value
				if (values[7][5] != 0)
				{
					button[7][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[7][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener83 = new MouseWheelListener ()		// for button[8][3]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][3] = valueCalculator(up, values, 8, 3);	// what to do with this action
				button[8][3].setText(choose(values[8][3]));			// change button text according to change in value
				if (values[8][3] != 0)
				{
					button[8][3].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener84 = new MouseWheelListener ()		// for button[8][4]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][4] = valueCalculator(up, values, 8, 4);	// what to do with this action
				button[8][4].setText(choose(values[8][4]));			// change button text according to change in value
				if (values[8][4] != 0)
				{
					button[8][4].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener85 = new MouseWheelListener ()		// for button[8][5]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][5] = valueCalculator(up, values, 8, 5);	// what to do with this action
				button[8][5].setText(choose(values[8][5]));			// change button text according to change in value
				if (values[8][5] != 0)
				{
					button[8][5].setBackground(activeColour[0]);	// sets the buttons original background colour
				}
				else
				{
					button[8][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener66 = new MouseWheelListener ()		// for button[6][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][6] = valueCalculator(up, values, 6, 6);	// what to do with this action
				button[6][6].setText(choose(values[6][6]));			// change button text according to change in value
				if (values[6][6] != 0)
				{
					button[6][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener67 = new MouseWheelListener ()		// for button[6][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][7] = valueCalculator(up, values, 6, 7);	// what to do with this action

				button[6][7].setText(choose(values[6][7]));			// change button text according to change in value
				if (values[6][7] != 0)
				{
					button[6][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener68 = new MouseWheelListener ()		// for button[6][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[6][8] = valueCalculator(up, values, 6, 8);	// what to do with this action
				button[6][8].setText(choose(values[6][8]));			// change button text according to change in value
				if (values[6][8] != 0)
				{
					button[6][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[6][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener76 = new MouseWheelListener ()		// for button[7][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][6] = valueCalculator(up, values, 7, 6);	// what to do with this action
				button[7][6].setText(choose(values[7][6]));			// change button text according to change in value
				if (values[7][6] != 0)
				{
					button[7][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener77 = new MouseWheelListener ()		// for button[7][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][7] = valueCalculator(up, values, 7, 7);	// what to do with this action
				button[7][7].setText(choose(values[7][7]));			// change button text according to change in value
				if (values[7][7] != 0)
				{
					button[7][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener78 = new MouseWheelListener ()		// for button[7][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[7][8] = valueCalculator(up, values, 7, 8);	// what to do with this action
				button[7][8].setText(choose(values[7][8]));			// change button text according to change in value
				if (values[7][8] != 0)
				{
					button[7][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[7][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener86 = new MouseWheelListener ()		// for button[8][6]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][6] = valueCalculator(up, values, 8, 6);	// what to do with this action
				button[8][6].setText(choose(values[8][6]));			// change button text according to change in value
				if (values[8][6] != 0)
				{
					button[8][6].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][6].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener87 = new MouseWheelListener ()		// for button[8][7]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][7] = valueCalculator(up, values, 8, 7);	// what to do with this action
				button[8][7].setText(choose(values[8][7]));			// change button text according to change in value
				if (values[8][7] != 0)
				{
					button[8][7].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][7].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};
		MouseWheelListener mouseWheelListener88 = new MouseWheelListener ()		// for button[8][8]
		{
			public void mouseWheelMoved(MouseWheelEvent e) 			// which way the wheel is scrolled
			{
				boolean up = (e.getUnitsToScroll() < 0);
				values[8][8] = valueCalculator(up, values, 8, 8);	// what to do with this action
				button[8][8].setText(choose(values[8][8]));			// change button text according to change in value
				if (values[8][8] != 0)
				{
					button[8][8].setBackground(activeColour[1]);	// sets the buttons original background colour
				}
				else
				{
					button[8][8].setBackground(naturalColour[1]);	// sets the buttons original background colour
				}
			}
		};

		// ActionListeners:
		ActionListener solveClicked = new ActionListener()	// when the use clicks "Solve"
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
     			LogicalSolver ls = new LogicalSolver();
     			writePuzzle(values);
     			input_frame.setVisible(false);	// close this frame, the one used for input
     			int[][] solved = ls.solver();
     			outputSolution(solved);
      		}
    	};
    	ActionListener exitClicked = new ActionListener()	// if exit is clicked
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		System.exit(0);		// exit the program
      		}
    	};
    	ActionListener instructionsClicked = new ActionListener()	// if the intructions button is clicked
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		outputInstructions();	// open the Instructions manual
      		}
    	};
    	ActionListener clearClicked = new ActionListener()	// if the clear option is chosen
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		for(int y=0; y<9; y++)
		        {
		            for(int x=0; x<9; x++)
		            {
		                button[x][y].setText("0"); //creates new button
		                values[x][y] = 0;
		                button[x][y].setBackground(naturalColour[1]);	// sets the buttons natural background colour
		            }
		        }

		        // resets specific buttons to a darker colour
		        button[3][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[3][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[3][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[0][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[0][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[0][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[1][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[1][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[1][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[2][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[2][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[2][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[6][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[6][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[6][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[7][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[7][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[7][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[8][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[8][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[8][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[3][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[3][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[3][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[4][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
				button[5][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
      		}
    	};

	   	for(int y=0; y<9; y++)
        {
            for(int x=0; x<9; x++)
            {
                button[x][y]= new JButton("0"); // creates new button
                input_frame.add(button[x][y]); 	// adds button to grid
                values[x][y] = 0;				// resets the puzzle value
				button[x][y].setBackground(naturalColour[1]);	// sets the buttons to the lighter colour
				button[x][y].setBorderPainted(true);	// gives the button a border when scrolled over
				button[x][y].setFont(new Font ("Calibri", 1, 24));
            }
        }

        // sets specific buttons to a darker colour
        button[3][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[3][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[3][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[0][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[0][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[0][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[1][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[1][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[1][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[2][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[2][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[2][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[6][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[6][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[6][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[7][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[7][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[7][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[8][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[8][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[8][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[3][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[3][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[3][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[4][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		button[5][8].setBackground(naturalColour[0]);	// sets the buttons original background colour

		// each button[x][y] has its own individual MouneListener object called mouseListener[x][y]
		button[0][0].addMouseListener(mouseListener00);		// for button[0][0]
		button[0][1].addMouseListener(mouseListener01);		// for button[0][1]
		button[0][2].addMouseListener(mouseListener02);		// for button[0][2]
		button[0][3].addMouseListener(mouseListener03);		// for button[0][3]
		button[0][4].addMouseListener(mouseListener04);		// for button[0][4]
		button[0][5].addMouseListener(mouseListener05);		// for button[0][5]
		button[0][6].addMouseListener(mouseListener06);		// for button[0][6]
		button[0][7].addMouseListener(mouseListener07);		// for button[0][7]
		button[0][8].addMouseListener(mouseListener08);		// for button[0][8]
		button[1][0].addMouseListener(mouseListener10);		// for button[1][0]
		button[1][1].addMouseListener(mouseListener11);		// for button[1][1]
		button[1][2].addMouseListener(mouseListener12);		// for button[1][2]
		button[1][3].addMouseListener(mouseListener13);		// for button[1][3]
		button[1][4].addMouseListener(mouseListener14);		// for button[1][4]
		button[1][5].addMouseListener(mouseListener15);		// for button[1][5]
		button[1][6].addMouseListener(mouseListener16);		// for button[1][6]
		button[1][7].addMouseListener(mouseListener17);		// for button[1][7]
		button[1][8].addMouseListener(mouseListener18);		// for button[1][8]
		button[2][0].addMouseListener(mouseListener20);		// for button[2][0]
		button[2][1].addMouseListener(mouseListener21);		// for button[2][1]
		button[2][2].addMouseListener(mouseListener22);		// for button[2][2]
		button[2][3].addMouseListener(mouseListener23);		// for button[2][3]
		button[2][4].addMouseListener(mouseListener24);		// for button[2][4]
		button[2][5].addMouseListener(mouseListener25);		// for button[2][5]
		button[2][6].addMouseListener(mouseListener26);		// for button[2][6]
		button[2][7].addMouseListener(mouseListener27);		// for button[2][7]
		button[2][8].addMouseListener(mouseListener28);		// for button[2][8]
		button[3][0].addMouseListener(mouseListener30);		// for button[3][0]
		button[3][1].addMouseListener(mouseListener31);		// for button[3][1]
		button[3][2].addMouseListener(mouseListener32);		// for button[3][2]
		button[3][3].addMouseListener(mouseListener33);		// for button[3][3]
		button[3][4].addMouseListener(mouseListener34);		// for button[3][4]
		button[3][5].addMouseListener(mouseListener35);		// for button[3][5]
		button[3][6].addMouseListener(mouseListener36);		// for button[3][6]
		button[3][7].addMouseListener(mouseListener37);		// for button[3][7]
		button[3][8].addMouseListener(mouseListener38);		// for button[3][8]
		button[4][0].addMouseListener(mouseListener40);		// for button[4][0]
		button[4][1].addMouseListener(mouseListener41);		// for button[4][1]
		button[4][2].addMouseListener(mouseListener42);		// for button[4][2]
		button[4][3].addMouseListener(mouseListener43);		// for button[4][3]
		button[4][4].addMouseListener(mouseListener44);		// for button[4][4]
		button[4][5].addMouseListener(mouseListener45);		// for button[4][5]
		button[4][6].addMouseListener(mouseListener46);		// for button[4][6]
		button[4][7].addMouseListener(mouseListener47);		// for button[4][7]
		button[4][8].addMouseListener(mouseListener48);		// for button[4][8]
		button[5][0].addMouseListener(mouseListener50);		// for button[5][0]
		button[5][1].addMouseListener(mouseListener51);		// for button[5][1]
		button[5][2].addMouseListener(mouseListener52);		// for button[5][2]
		button[5][3].addMouseListener(mouseListener53);		// for button[5][3]
		button[5][4].addMouseListener(mouseListener54);		// for button[5][4]
		button[5][5].addMouseListener(mouseListener55);		// for button[5][5]
		button[5][6].addMouseListener(mouseListener56);		// for button[5][6]
		button[5][7].addMouseListener(mouseListener57);		// for button[5][7]
		button[5][8].addMouseListener(mouseListener58);		// for button[5][8]
		button[6][0].addMouseListener(mouseListener60);		// for button[6][0]
		button[6][1].addMouseListener(mouseListener61);		// for button[6][1]
		button[6][2].addMouseListener(mouseListener62);		// for button[6][2]
		button[6][3].addMouseListener(mouseListener63);		// for button[6][3]
		button[6][4].addMouseListener(mouseListener64);		// for button[6][4]
		button[6][5].addMouseListener(mouseListener65);		// for button[6][5]
		button[6][6].addMouseListener(mouseListener66);		// for button[6][6]
		button[6][7].addMouseListener(mouseListener67);		// for button[6][7]
		button[6][8].addMouseListener(mouseListener68);		// for button[6][8]
		button[7][0].addMouseListener(mouseListener70);		// for button[7][0]
		button[7][1].addMouseListener(mouseListener71);		// for button[7][1]
		button[7][2].addMouseListener(mouseListener72);		// for button[7][2]
		button[7][3].addMouseListener(mouseListener73);		// for button[7][3]
		button[7][4].addMouseListener(mouseListener74);		// for button[7][4]
		button[7][5].addMouseListener(mouseListener75);		// for button[7][5]
		button[7][6].addMouseListener(mouseListener76);		// for button[7][6]
		button[7][7].addMouseListener(mouseListener77);		// for button[7][7]
		button[7][8].addMouseListener(mouseListener78);		// for button[7][8]
		button[8][0].addMouseListener(mouseListener80);		// for button[8][0]
		button[8][1].addMouseListener(mouseListener81);		// for button[8][1]
		button[8][2].addMouseListener(mouseListener82);		// for button[8][2]
		button[8][3].addMouseListener(mouseListener83);		// for button[8][3]
		button[8][4].addMouseListener(mouseListener84);		// for button[8][4]
		button[8][5].addMouseListener(mouseListener85);		// for button[8][5]
		button[8][6].addMouseListener(mouseListener86);		// for button[8][6]
		button[8][7].addMouseListener(mouseListener87);		// for button[8][7]
		button[8][8].addMouseListener(mouseListener88);		// for button[8][8]

		// each button[x][y] has its own individual MouneWheelListener object called mouseListener[x][y]
		button[0][0].addMouseWheelListener(mouseWheelListener00);		// for button[0][0]
		button[0][1].addMouseWheelListener(mouseWheelListener01);		// for button[0][1]
		button[0][2].addMouseWheelListener(mouseWheelListener02);		// for button[0][2]
		button[0][3].addMouseWheelListener(mouseWheelListener03);		// for button[0][3]
		button[0][4].addMouseWheelListener(mouseWheelListener04);		// for button[0][4]
		button[0][5].addMouseWheelListener(mouseWheelListener05);		// for button[0][5]
		button[0][6].addMouseWheelListener(mouseWheelListener06);		// for button[0][6]
		button[0][7].addMouseWheelListener(mouseWheelListener07);		// for button[0][7]
		button[0][8].addMouseWheelListener(mouseWheelListener08);		// for button[0][8]
		button[1][0].addMouseWheelListener(mouseWheelListener10);		// for button[1][0]
		button[1][1].addMouseWheelListener(mouseWheelListener11);		// for button[1][1]
		button[1][2].addMouseWheelListener(mouseWheelListener12);		// for button[1][2]
		button[1][3].addMouseWheelListener(mouseWheelListener13);		// for button[1][3]
		button[1][4].addMouseWheelListener(mouseWheelListener14);		// for button[1][4]
		button[1][5].addMouseWheelListener(mouseWheelListener15);		// for button[1][5]
		button[1][6].addMouseWheelListener(mouseWheelListener16);		// for button[1][6]
		button[1][7].addMouseWheelListener(mouseWheelListener17);		// for button[1][7]
		button[1][8].addMouseWheelListener(mouseWheelListener18);		// for button[1][8]
		button[2][0].addMouseWheelListener(mouseWheelListener20);		// for button[2][0]
		button[2][1].addMouseWheelListener(mouseWheelListener21);		// for button[2][1]
		button[2][2].addMouseWheelListener(mouseWheelListener22);		// for button[2][2]
		button[2][3].addMouseWheelListener(mouseWheelListener23);		// for button[2][3]
		button[2][4].addMouseWheelListener(mouseWheelListener24);		// for button[2][4]
		button[2][5].addMouseWheelListener(mouseWheelListener25);		// for button[2][5]
		button[2][6].addMouseWheelListener(mouseWheelListener26);		// for button[2][6]
		button[2][7].addMouseWheelListener(mouseWheelListener27);		// for button[2][7]
		button[2][8].addMouseWheelListener(mouseWheelListener28);		// for button[2][8]
		button[3][0].addMouseWheelListener(mouseWheelListener30);		// for button[3][0]
		button[3][1].addMouseWheelListener(mouseWheelListener31);		// for button[3][1]
		button[3][2].addMouseWheelListener(mouseWheelListener32);		// for button[3][2]
		button[3][3].addMouseWheelListener(mouseWheelListener33);		// for button[3][3]
		button[3][4].addMouseWheelListener(mouseWheelListener34);		// for button[3][4]
		button[3][5].addMouseWheelListener(mouseWheelListener35);		// for button[3][5]
		button[3][6].addMouseWheelListener(mouseWheelListener36);		// for button[3][6]
		button[3][7].addMouseWheelListener(mouseWheelListener37);		// for button[3][7]
		button[3][8].addMouseWheelListener(mouseWheelListener38);		// for button[3][8]
		button[4][0].addMouseWheelListener(mouseWheelListener40);		// for button[4][0]
		button[4][1].addMouseWheelListener(mouseWheelListener41);		// for button[4][1]
		button[4][2].addMouseWheelListener(mouseWheelListener42);		// for button[4][2]
		button[4][3].addMouseWheelListener(mouseWheelListener43);		// for button[4][3]
		button[4][4].addMouseWheelListener(mouseWheelListener44);		// for button[4][4]
		button[4][5].addMouseWheelListener(mouseWheelListener45);		// for button[4][5]
		button[4][6].addMouseWheelListener(mouseWheelListener46);		// for button[4][6]
		button[4][7].addMouseWheelListener(mouseWheelListener47);		// for button[4][7]
		button[4][8].addMouseWheelListener(mouseWheelListener48);		// for button[4][8]
		button[5][0].addMouseWheelListener(mouseWheelListener50);		// for button[5][0]
		button[5][1].addMouseWheelListener(mouseWheelListener51);		// for button[5][1]
		button[5][2].addMouseWheelListener(mouseWheelListener52);		// for button[5][2]
		button[5][3].addMouseWheelListener(mouseWheelListener53);		// for button[5][3]
		button[5][4].addMouseWheelListener(mouseWheelListener54);		// for button[5][4]
		button[5][5].addMouseWheelListener(mouseWheelListener55);		// for button[5][5]
		button[5][6].addMouseWheelListener(mouseWheelListener56);		// for button[5][6]
		button[5][7].addMouseWheelListener(mouseWheelListener57);		// for button[5][7]
		button[5][8].addMouseWheelListener(mouseWheelListener58);		// for button[5][8]
		button[6][0].addMouseWheelListener(mouseWheelListener60);		// for button[6][0]
		button[6][1].addMouseWheelListener(mouseWheelListener61);		// for button[6][1]
		button[6][2].addMouseWheelListener(mouseWheelListener62);		// for button[6][2]
		button[6][3].addMouseWheelListener(mouseWheelListener63);		// for button[6][3]
		button[6][4].addMouseWheelListener(mouseWheelListener64);		// for button[6][4]
		button[6][5].addMouseWheelListener(mouseWheelListener65);		// for button[6][5]
		button[6][6].addMouseWheelListener(mouseWheelListener66);		// for button[6][6]
		button[6][7].addMouseWheelListener(mouseWheelListener67);		// for button[6][7]
		button[6][8].addMouseWheelListener(mouseWheelListener68);		// for button[6][8]
		button[7][0].addMouseWheelListener(mouseWheelListener70);		// for button[7][0]
		button[7][1].addMouseWheelListener(mouseWheelListener71);		// for button[7][1]
		button[7][2].addMouseWheelListener(mouseWheelListener72);		// for button[7][2]
		button[7][3].addMouseWheelListener(mouseWheelListener73);		// for button[7][3]
		button[7][4].addMouseWheelListener(mouseWheelListener74);		// for button[7][4]
		button[7][5].addMouseWheelListener(mouseWheelListener75);		// for button[7][5]
		button[7][6].addMouseWheelListener(mouseWheelListener76);		// for button[7][6]
		button[7][7].addMouseWheelListener(mouseWheelListener77);		// for button[7][7]
		button[7][8].addMouseWheelListener(mouseWheelListener78);		// for button[7][8]
		button[8][0].addMouseWheelListener(mouseWheelListener80);		// for button[8][0]
		button[8][1].addMouseWheelListener(mouseWheelListener81);		// for button[8][1]
		button[8][2].addMouseWheelListener(mouseWheelListener82);		// for button[8][2]
		button[8][3].addMouseWheelListener(mouseWheelListener83);		// for button[8][3]
		button[8][4].addMouseWheelListener(mouseWheelListener84);		// for button[8][4]
		button[8][5].addMouseWheelListener(mouseWheelListener85);		// for button[8][5]
		button[8][6].addMouseWheelListener(mouseWheelListener86);		// for button[8][6]
		button[8][7].addMouseWheelListener(mouseWheelListener87);		// for button[8][7]
		button[8][8].addMouseWheelListener(mouseWheelListener88);		// for button[8][8]

		menuoptionExit.addActionListener(exitClicked);					// make the "Exit" button exit the program
		menuoptionSolve.addActionListener(solveClicked);				// make the "Solve" button solve the program
		menuoptionClear.addActionListener(clearClicked);				// make the "Clear" button clear the board
		menuoptionInstructions.addActionListener(instructionsClicked);	// make the "Instructions" button display instructions

	    Container contentPane = input_frame.getContentPane();	// creates a container to handle the frame

		menuFile.add(menuoptionInstructions);	// adds the "Intructions" under the "File" section
		menuFile.add(menuoptionExit);			// adds the "Exit" under the "File" section
		menu_bar.add(menuFile);					// adds the "File" menu to the menu bar
		menuAction.add(menuoptionSolve);		// adds the "Solve" under the "Action" section
		menuAction.add(menuoptionClear);		// adds the "Clear" under the "Action" section
		menu_bar.add(menuAction);				// adds the "Action" menu to the menu bar
		input_frame.setJMenuBar(menu_bar);		// adds menu bar to the frame

	    input_frame.setSize(600, 600);		// sets an absolute size for the frame
	    input_frame.setResizable(false);	// i do not want the user to reseize the window
	    input_frame.setVisible(true);		// make the input frame visible and available for input
	}

	// used to write the inputed values to a text file
	public static void writePuzzle(int[][] values)		// return type : void | parameters : int[][]
	{
		PrintWriter outw;	// used to write out to text files
		try
		{
			outw = new PrintWriter (new FileOutputStream("problem.txt"));	// state which text file
			for (int x = 0; x < 9; x++)
			{
				for (int y = 0; y < 9; y++)
				{
					outw.print(values[x][y]);	// same line
				}
				outw.println();		// new line
			}
			outw.close();	// close the object
		}
		catch (IOException ioe)	// catch any exceptions
		{
			System.out.println("IOEXCEPTION ERROR : " + ioe);	// output error message
			System.exit(0);	// exit early if error
		}
	}

	// used to output solution onto screen and into text file
	public static void outputSolution(final int[][] values)	// return type : void | parameters : int[][]
	{
		Color naturalColour[] = new Color [2];
		naturalColour[0] = new Color(155,225,251);
		naturalColour[1] = new Color(197,239,253);

		final JFrame output_frame = new JFrame("Sudoku :D"); //creates frame
        final JButton[][] grid = new JButton[9][9]; //names the grid of buttons

        output_frame.setLayout(new GridLayout(9, 9, 3, 3)); //set layout

        JMenuBar menu_bar = new JMenuBar();	// create a new menu bar named menu_bar

		JMenu menuFile = new JMenu("File");	// create a "File" option for the menu bar
		JMenu menuShow = new JMenu("Show");	// create a "Show" option for the menu bar

		JMenuItem menuoptionNew = new JMenuItem("New Puzzle");	// a new suboption that goes under "File", purpose is to create a new puzzle
		JMenuItem menuoptionExit = new JMenuItem("Exit");	// a new suboption that goes under "File", purpose is to create exit the program

		JMenuItem menuoptionMovesLog = new JMenuItem("Moves Log");	// a new suboption that goes under "Show", purpose is to show the moves made

        for(int y=0; y<9; y++)
        {
            for(int x=0; x<9; x++)
            {
                grid[x][y]=new JButton("" + values[x][y] + ""); //creates new button
                output_frame.add(grid[x][y]); //adds button to grid
                grid[x][y].setBackground(naturalColour[1]);	// sets the buttons original background colour
                grid[x][y].setFont(new Font ("Calibri", 1, 24));
            }
        }

        // sets specific buttons to a darker colour
        grid[3][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[3][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[3][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][0].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][1].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][2].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[0][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[0][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[0][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[1][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[1][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[1][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[2][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[2][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[2][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[6][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[6][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[6][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[7][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[7][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[7][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[8][3].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[8][4].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[8][5].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[3][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[3][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[3][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[4][8].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][6].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][7].setBackground(naturalColour[0]);	// sets the buttons original background colour
		grid[5][8].setBackground(naturalColour[0]);	// sets the buttons original background colour

		// ActionListeners:
		ActionListener newClicked = new ActionListener()	// when the use clicks "Solve"
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		inputPuzzle();	// input a new puzzle
        		output_frame.setVisible(false);		// make the ouput invisible
        	}
    	};
    	ActionListener exitClicked = new ActionListener()	// when user clicks exit
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
        		System.exit(0);	// exit the program
      		}
    	};
    	ActionListener movesLog = new ActionListener()	// output the moves log
		{
     		public void actionPerformed(ActionEvent actionEvent)
     		{
     			String[] moves = new String[100];	// maximum number of moves needed is 100 (randomly chosen number)
     			moves[0] = " ";

     			JLabel[] showMoves = new JLabel[100];	// maximum number of moves needed is 100 (randomly chosen number)
     			showMoves[0] = new JLabel(" ");

     			JFrame log = new JFrame("Moves Log");
     			log.setLayout(new GridLayout(0, 1, 5, 5)); //set layout

     			BufferedReader readLog;	// an object for the log file

        		try
        		{
        			readLog = new BufferedReader (new FileReader("moveLog.txt"));	// open to read from the log file

        			for (int m = 0; m < 100; m++)
        			{
        				moves[m] = readLog.readLine();
        				if (moves[m] != null)
        				{
        					showMoves[m] = new JLabel(moves[m]);	// read from the log file
        					log.add(showMoves[m]);	// add this text to a label
        				}
        			}

					log.pack();
					log.setVisible(true);	// show this
        		}
        		catch (FileNotFoundException fnfe)	// if the file is not found
        		{
        			System.out.println("Sorry file was not found : " + fnfe);	// display error message
        			System.exit(0);	// exit system
        		}
        		catch (IOException ioe)	// if there is an i/o exception
        		{
        			System.out.println("Sorry i/o error : " + ioe);		// display error message
        			System.exit(0);	// exit system
        		}
      		}
    	};


        menuoptionExit.addActionListener(exitClicked);
		menuoptionNew.addActionListener(newClicked);
		menuoptionMovesLog.addActionListener(movesLog);

	    Container contentPane = output_frame.getContentPane();

		menuFile.add(menuoptionNew);
		menuFile.add(menuoptionExit);
		menu_bar.add(menuFile);
		menuShow.add(menuoptionMovesLog);
		menu_bar.add(menuShow);
		output_frame.setJMenuBar(menu_bar);

        output_frame.setSize(600,600);
        output_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		output_frame.setResizable(false);
        output_frame.setVisible(true); //makes frame visible
	}
}