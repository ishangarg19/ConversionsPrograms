/*
    Name: Ishan Garg
    Teacher: Mr. Guglielmi
    Date: October 20, 2020
    Program: This program converts mass from either pounds to
	     kilograms or vice versa. It utilizes a return
	     method to determine which must be done, and to
	     calculate the conversion. Zero is not errortrapped
	     out because it will not cause an error in the program,
	     it will simply make the conversion useless, as having
	     the mass equal zero makes the conversion value obvious.
	     Negative mass values are errortrapped, however.
*/

import java.awt.*;
import hsa.Console;

public class MassConverter
{
    /*
	Console c -> declares the console used in the program
	private static String input -> declares the input variable used to select the option from the main menu (accessed in main method to control do-while loop/goodbye)
	private double mass -> delcares variable used to receive mass input (does not matter if it is pounds or kilograms, as return method will handle which)
    */
    
    Console c;
    private static String input;
    private double mass;
    
    /*
	Class constructor
	Console c is initialized with window title "Mass Converter"
    */
    public MassConverter ()
    {
	c = new Console ("Mass Converter");
    }

    /*
	Method to clear screen then display centered title with blank line
	arguments: none
	returns: none
    */
    private void title ()
    {
	c.clear ();
	c.print ("", 33);
	c.println ("Mass Converter");
	c.println ();
    }

    /*
	Method to ask the user if they would like to continue, then receives any keyboard input
	arguments: none
	returns: none
    */
    private void pauseProgram ()
    {
	c.println ();
	c.println ("Please press any key to continue.");
	c.getChar ();
    }

    /*
	Method to display splash screen using graphics for 3 seconds
	arguments: none
	returns: none
    */
    public void splashScreen ()
    {
	c.setColor (new Color (204, 255, 229));
	c.fillRect (0, 0, 640, 500);
	
	c.setColor(Color.black);
	c.setFont(new Font ("Times New Roman", Font.BOLD, 50));
	c.drawString("Mass Converter", 25,60);
	
	c.setFont(new Font ("Garamond", Font.PLAIN ,40));
	c.drawString("Pounds to",25,135);
	c.drawString("Kilograms",25,180);
	
	c.drawString("Kilograms to",400,335);
	c.drawString("Pounds",400,380);
	
	c.setFont(new Font("Arial", Font.PLAIN, 40));
	c.drawString("OR", 265,255);
	
	try
	{
	    Thread.sleep(3000);
	}
	catch (Exception e)
	{

	}
    }

    /*
	Method to inform the user about how the program works, while calling title() and pauseProgram() to confirm continue.
	arguments: none
	returns: none
    */
    public void instructions ()
    {
	title();
	c.println("This program will convert from either pounds to kilograms or vice versa, based  on what the user (you) would like to do.");
	pauseProgram();
    }

    /*
	Method to display title, instructions (so the user always knows), and the list of options.
	The selection is errortrapped to ensure the only possible entries are 1,2, and 3.
	arguments: none
	returns: none
    */
    public void mainMenu ()
    {
	title();
	
	c.println("What conversion would you like to perform?");
	c.println("1.   Pounds to Kilograms");
	c.println("2.   Kilograms to Pounds");
	c.println("3.   EXIT CONVERTER");
	c.print("Please type the corresponding number: ");
	
	input = c.readLine();
	
	while (!input.equals("1") && !input.equals("2") && !input.equals("3"))
	{
	    c.setCursor(7,1);
	    c.println("Please enter a valid selection!: \n");
	    c.setCursor(7,34);
	    input = c.readLine();
	}
    }

    /*
	Method to ask the user what the input mass is using "str", then assigning value to "mass" variable.
	Mass input is errortrapped to make sure only a positive number is entered. Display is directly
	called from here to calculate/display output of calculation.
	
	boolean valid = false -> declares and initializes variable used to repeatedly errortrap until input is valid.
	String str -> used to actually gather input for errortrap, which is then parsed into a double and assigned to "mass".
	arguments: none
	returns: none
    */
    public void askData ()
    {
	boolean valid = false;
	String str;
    
	title();
	
	if (input.equals("1"))
	{
	    c.println("POUNDS to KILOGRAMS");
	    c.println();
	    c.print("What is the mass in pounds?: ");
	    
	    while (!valid) //Pound input errortrap
	    {
		try
		{
		    str = c.readLine();
		    mass = Double.parseDouble(str);
		    
		    if(mass<0)
		    {
			c.setCursor(5,1);
			c.println("Only positive values are allowed. Please re-enter the mass in lb: \n");
			c.setCursor(5, 67);
		    }
		    else
		    {
			valid = true;
		    }
		}
		catch (NumberFormatException e)
		{
		    c.setCursor(5,1);
		    c.println("The mass must be a numerical value. Please re-enter the mass in lb: \n");
		    c.setCursor(5,69);
		}
	    }
	    
	    display();
	}
	
	else if (input.equals("2"))
	{
	    c.println("KILOGRAMS to POUNDS");
	    c.println();
	    c.print("What is the mass in kilograms?: ");
	    
	    while (!valid) //Kilogram input errortrap
	    {
		try
		{
		    str = c.readLine();
		    mass = Double.parseDouble(str);
		    
		    if(mass<0)
		    {
			c.setCursor(5,1);
			c.println("Only positive values are allowed. Please re-enter the mass in kg: \n");
			c.setCursor(5, 67);
		    }
		    else
		    {
			valid = true;
		    }
		}
		catch (NumberFormatException e)
		{
		    c.setCursor(5,1);
		    c.println("The mass must be a numerical value. Please re-enter the mass in kg: \n");
		    c.setCursor(5,69);
		}
	    }
	    
	    display();
	}
    }
    
    /*
	Method to gather input mass and convert to specified mass using formulas.
	arguments: double massVal -> gathers mass input
		   char convertTo -> selection for which to convert to (kg or lb)
	returns: converted mass answer(double)
    */
    private double convertMass (double massVal, char convertTo)
    {
	double answer = 0;
	
	if (convertTo == 'K' || convertTo == 'k')
	{
	    answer = massVal*0.453592;
	}
	else if (convertTo == 'P' || convertTo == 'p')
	{
	    answer = massVal*2.20462;
	}
	
	return answer;
    }

    /*
	Method to display title, original mass input, and final conversion depending on conversion selection.
	Then, it confirms if the user is ready to continue back to the main menu using pauseProgram().
	arguments: none
	returns: none
    */
    public void display ()
    {
	title();
	
	if (input.equals("1"))
	{
	    c.println("The mass in pounds is: " + mass + " lb.");
	    c.println();
	    c.println(mass + " Pounds in Kilograms is " + convertMass(mass,'K') + " kg.");
	}
	else if (input.equals("2"))
	{
	    c.println("The mass in kilograms is: " + mass + " kg.");
	    c.println();
	    c.println(mass + " Kilograms in Pounds is " + convertMass(mass,'P') + " lb.");
	}
	
	pauseProgram();
    }

    /*
	Method to display title, thank the suer, tell them they can exit, and uses pauseProgram() to let the user exit.
	arguments: none
	returns: none
    */
    public void goodbye ()
    {
	title();
	
	c.println("Thank you for using this converter. You may exit now.");
	
	pauseProgram();
	System.exit(0);
    }

    /*
	Main Method to create an instance of the MassConverter class, then calls all methods is required 
	order. Do-while loop runs the inside methods at least once, and continues until the selection is 
	"3", or EXIT CONVERTER, then it runs goodbye().
	arguments: none
	returns: none
    */
    public static void main (String[] args)
    {

	MassConverter m = new MassConverter ();
	
	m.splashScreen ();

	do
	{
	    m.instructions ();
	    m.mainMenu ();
	    m.askData ();
	} while (!input.equals("3"));

	m.goodbye ();
    }
}

