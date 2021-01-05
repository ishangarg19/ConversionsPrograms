/*
    Names: Ishan Garg & Mahbod B. Jandaghi
    Teacher: Mr. Guglielmi
    Date: November 2, 2020
    Purpose: The purpose of this program is to work with files
	     and arrays be reading/writing student names and
	     corresponding marks in an external file. The program
	     will also update student marks, create new classes,
	     provide class averages, and get a list of students
	     within a class.
*/

import java.awt.*;
import hsa.Console;
import java.io.*;

public class StudentMarks
{
    /*
	Console c; --> Declares console variable
	private static String menuControl; --> Declares variable used for menu control - it is also accessed in the main method to constantly run program until user quits
	private String[] nameList; --> Declares array String variable that stores names
	private int[] markList; --> Declares array Int variable that stores marks
	private int numStudents = 0; --> Declares and initializes variable that stores number of students for calculation purposes.
    */
    Console c;
    private static String menuControl;
    private String[] nameList;
    private int[] markList;
    private int numStudents = 0;

    /*
    ISHAN - Constructor of StudentMarks
    initializes console with window title "Sudent Marks"
    */
    public StudentMarks ()
    {
	c = new Console ("Student Marks");
    }


    /*
    ISHAN - Method to display the title and clears the screen
    args: none
    returns: none
    */
    private void title ()
    {
	c.clear ();
	c.print ("", 33);
	c.println ("Student Marks\n");
    }


    /*
    ISHAN - Method to display the intro information
    args: none
    returns: none
    */
    public void intro ()
    {
	title ();
	c.println ("This program will read/write to files containing student names and marks. You   will have the options to retrieve the class average, student names and marks,   update a student's mark, create a new class, and open an existing class/file.");
	c.println ();
    }


    /*
    ISHAN - Method to allow the user to select what they would like to do.
    Fully errortrapped to make sure only valid options are chosen.
    int select --> declares variable used for switch-case
    args: none
    returns: none
    */
    public void mainMenu ()
    {
	int select;

	intro ();

	c.println ("The options are as follows: ");
	c.println ("1.   Display Class Average");
	c.println ("2.   All Students and their Marks");
	c.println ("3.   Update Student Mark");
	c.println ("4.   Create New Class");
	c.println ("5.   Open File");
	c.println ("6.   Save to File");
	c.println ("7.   EXIT PROGRAM");
	c.print ("Please type the corresponding number: ");

	menuControl = c.readLine ();

	while (!menuControl.equals ("1") && !menuControl.equals ("2") && !menuControl.equals ("3") && !menuControl.equals ("4") && !menuControl.equals ("5") && !menuControl.equals ("6") && !menuControl.equals ("7"))
	{
	    c.setCursor (15, 1);
	    c.println ("Please type a valid number selection: \n");
	    c.setCursor (15, 39);
	    menuControl = c.readLine ();
	}

	c.println ();
	select = Integer.parseInt (menuControl);

	switch (select)
	{
	    case 1:
		displayAverage ();
		break;
	    case 2:
		displayStudents ();
		break;
	    case 3:
		updateStudentMark ();
		break;
	    case 4:
		createNewClass ();
		break;
	    case 5:
		readFile ();
		break;
	    case 6:
		writeFile ();
		break;
	    case 7:
		goodbye ();
		break;
	}
    }


    /*
    ISHAN - Pauses the program and confirms with the user if they would like to continue.
    args: none
    returns: none
    */
    private void pauseProgram ()
    {
	c.println ();
	c.println ("Please press any key to continue.");
	c.getChar ();
    }


    /*
    ISHAN - Method to retrieve data from the specified file and writes to arrays.
    String fileName = null; --> variable to retrieve file name (initialized with null value)
    boolean valid = false; --> used for errortrapping
    String line; --> retrieves data from file by line
    int size = 0; --> variable to represent class size retrieved from file
    args: none
    returns: none
    */
    public void readFile ()
    {
	String fileName = null;
	boolean valid = false;
	String line;
	int size = 0;

	BufferedReader in;

	c.println ("Please enter the filename with extension: \n");
	c.setCursor (17, 43);

	while (!valid)
	{
	    fileName = c.readLine ();

	    try
	    {

		in = new BufferedReader (new FileReader (fileName));

		line = in.readLine ();
		if (line.equals ("SDFY"))
		{
		    valid = true;
		}
		else
		{
		    throw new IOException (); //Runs the catch() block, since the file isnt valid if the header isnt valid.
		}

		int count = 0;

		line = in.readLine ();
		size = Integer.parseInt (line);
		nameList = new String [size];
		markList = new int [size];
		numStudents = size;

		line = in.readLine ();
		line = in.readLine ();
		while (count < size) //Retrieves data constantly for the total size of the array/number of students
		{
		    nameList [count] = line;
		    line = in.readLine ();

		    markList [count] = Integer.parseInt (line);
		    line = in.readLine ();

		    count++;
		}
	    }
	    catch (IOException e)  //Catches exception and displays all retrieved data to show what has been collected
	    {
		c.setCursor (17, 1);
		c.print ("Not a valid file. \n");
		c.setCursor (17, 19);
		c.println ("Please enter the filename with extension: \n");
		c.setCursor (17, 61);
	    }

	}

	c.println ();
	c.println ("File is valid. Data retrieved from " + fileName);
	c.println ("Size of class: " + size);
	pauseProgram ();

    }


    /*
    MAHBOD - Method that creates a new file and writes the input data onto the file. 
    PrintWriter output; = creates PrintWriter object
    args: none
    returns: none
    */
    public void writeFile ()
    {
	PrintWriter output;

	c.println ("Please enter the file name: ");
	String fileName = c.readLine ();

	try
	{
	    output = new PrintWriter (new FileWriter (fileName));

	    //this loop will write all the data in the array to the file, with each element on a new line
	    output.println ("SDFY");            //This is the header needed for the file to be valid
	    output.println (nameList.length);   //Displays the number of the students in the next line, which is necessary for the file to be valid
	    output.println ();
	    for (int i = 0 ; i < nameList.length ; i++)
	    {
		output.println (nameList [i]);
		output.println (markList [i]);
	    }

	    output.close ();
	    c.println ();
	    c.print ("Data writing finished");
	}
	catch (IOException e)
	{
	    c.print ("Something went wrong."); //error message if file can't be written to. Returns to main menu afterwards.
	}

	pauseProgram ();
    }


    /*
    MAHBOD - Method that displays the students names and marks from the current class. 
    args: none
    returns: none
    */
    public void displayStudents ()
    {
	c.println ("INDEX:\t   NAME:\t       MARK:");
	for (int i = 0 ; i < numStudents ; i++)
	{
	    c.print (i);
	    c.print ("", 10);
	    c.print (nameList [i], 20);
	    c.print (markList [i]);
	    c.println ();
	}
	pauseProgram ();
    }


    /*
    MAHBOD - Method that displays the average of the students in the class. 
    args: none
    returns: none
    */
    public void displayAverage ()
    {
	c.println ("The class average is " + calcAverage (markList));
	pauseProgram ();
    }


    /*
    ISHAN - Method to calculate the average using an array of marks retrieved from a file
    args: int[] marks -> stores the retrieved mark data
    returns: average
    */
    private double calcAverage (int[] marks)
    {
	double average = 0;
	for (int i = 0 ; i < numStudents ; i++)
	{
	    average += marks [i];
	}
	average /= numStudents;
	return average;
    }


    /*
    MAHBOD - Method that updates the student mark when given the student's name. 
    valid = variable that helps with error checking the input.
    nameChange = variable for the name of the student the user wants to change.
    markChange = variable for the updated mark the user inputs. 
    args: none
    returns: none
    */
    public void updateStudentMark ()
    {
	boolean valid = false;
	String nameChange;
	c.println ("Please enter the name of the student whose mark you want changed:");
	nameChange = c.readLine ();
	    
	while (!valid)
	{
	    
	    for (int i = 0 ; i < numStudents ; i++)
	    {
		if (nameChange.equals (nameList [i]))
		{
		    c.println ("Please enter the mark you want to assign to this student:");
		    int markChange = c.readInt ();
		    while (markChange < 0 || markChange > 100)
		    {
			c.println ("Please input a valid mark to assign:");
			markChange = c.readInt ();
		    }
		    markList [i] = markChange;
		    valid = true;
		}
	    }
	    if (!valid)
	    {
		c.setCursor(17,1);
		c.println ("Please enter a name that exists in the class.\n");
		c.setCursor(18,1);
		nameChange = c.readLine ();
	    }

	}
	
	c.println("Mark Updated. Please save file to confirm change.");
	pauseProgram ();
    }


    /*
    MAHBOD - Method that creates a new class with the indicated number of students. 
    args: none
    returns: none
    */
    public void createNewClass ()
    {
	c.println ("How many students do you want to have in this class?");
	numStudents = c.readInt ();

	nameList = new String [numStudents];
	markList = new int [numStudents];

	for (int i = 1 ; i <= numStudents ; i++)
	{
	    c.setCursor (17, 1);
	    c.println ("Enter the name for Student " + i + ":\n\n\n");
	    c.setCursor (18, 1);
	    nameList [i - 1] = c.readLine ();
	    c.println ("Enter the mark for Student " + i + ":\n");
	    c.setCursor (20, 1);
	    markList [i - 1] = c.readInt ();
	    while (markList [i - 1] < 0 || markList [i - 1] > 100)
	    {
		c.setCursor (23, 1);
		c.println ("Please enter a valid input for the mark of Student" + i + ":\n");
		c.setCursor (24, 1);
		markList [i - 1] = c.readInt ();
	    }
	    c.setCursor (17, 1);
	}
	c.setCursor (17, 1);
	c.println ("Entry complete.\n");
	pauseProgram ();
    }


    /*
    ISHAN - Method to end the program
    args: none
    returns: none
    */
    public void goodbye ()
    {
	title ();

	c.println ("Thank you for using this program. You may now exit.");
	pauseProgram ();

	System.exit (0);
    }


    /*
    Main method which has a do-while loop to run the program until
    the user specifies to quit (selection 7)
    */
    public static void main (String[] args)
    {
	StudentMarks s = new StudentMarks ();

	do
	{
	    s.mainMenu ();
	}
	while (!menuControl.equals ("7"));
    }
}
