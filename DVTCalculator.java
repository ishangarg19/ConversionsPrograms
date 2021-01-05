/*
Name: Ishan Garg
Teacher: Mr. Guglielmi
Date: October 15, 2020
Program: Will ask the user what they want to calculate out
	 of distance, velocity, and time and ask for the other
	 two then calculate.

*/

import java.awt.*;
import hsa.Console;

public class DVTCalculator
{
    /*
    Console c -> delares an instance of the console used
    private static char input -> declares the input variable that controls the actions of the program (it is accessed in the main method to control the do-while loop 
    private double distance -> declares the variable used to get the distance input, or output the final distance
    private double velocity -> declares the variable used to get the velocity input, or output the final velocity
    private double time -> declares the variable used to get the time input, or output the final time
    */

    Console c;
    private static char input;
    private double distance;
    private double velocity;
    private double time;
    
    
    /*
    Class constructor.
    Initializes console window with title "DVT Calculator"
    */
    public DVTCalculator()
    {
	c = new Console ("DVT Calculator");
    }
    
    /*
    Method to create title
    arguments: none
    returns: none
    */
    private void title()
    {
	c.clear();
	c.print("",21);
	c.println("Distance - Velocity - Time Calculator");
	c.println();
    }
    
    /*
    Method to display introduction explanation
    arguments: none
    returns: none
    */
    public void intro()
    {
	title();
	c.println("This program will calculate the distance, velocity, or time. You will be asked  what you would like to calculate, then you will be asked for the other 2 values so the DVT calculation can be performed");
	c.println();
	c.println("What would you like to calculate?");
	c.println("1. Distance (in metres)");
	c.println("2. Velocity (in metres/second)");
	c.println("3. Time (in seconds)");
	c.println("4. EXIT CALCULATOR");
	c.print("Type the corresponding number: ");
    }
    
    /*
    Method to ask the user what they would like to calculate, 
    then asking for the required values for the calculation.
    arguments: none
    returns: none
    */
    public void askData()
    {
	intro();
	input = c.readChar();
	
	if (input == '1')
	{
	    c.clear();
	    c.println("DISTANCE: ");
	    c.println();
	    c.println("What is the velocity in metres/second?");
	    velocity = c.readDouble();
	    
	    c.println();
	    c.println("What is the time in seconds?");
	    time = c.readDouble();
	    
	    display();
	}
	
	else if (input == '2')
	{
	    c.clear();
	    c.println("VELOCITY: ");
	    c.println();
	    c.println("What is the distance in metres?");
	    distance = c.readDouble();
	    
	    c.println();
	    c.println("What is the time in seconds?");
	    time = c.readDouble();
	    
	    display();
	}
	
	else if (input == '3')
	{
	    c.clear();
	    c.println("TIME: ");
	    c.println();
	    c.println("What is the distance in metres?");
	    distance = c.readDouble();
	    
	    c.println();
	    c.println("What is the velocity in metres/second?");
	    velocity = c.readDouble();
	    
	    display();
	}
    }
    
    /*
    Method to display the answer of the calculation 
    by using the values recieved in askData().
    arguments: none
    returns: none
    */
    public void display()
    {
	c.println();
	
	if (input == '1')
	{
	    distance = velocity*time;
	    c.println("The distance is " + distance + " metres.");
	}
	
	else if (input == '2')
	{ 
	    velocity = distance/time;
	    c.println("The velocity is " + velocity + " metres/second.");
	}
	
	else if (input == '3')
	{
	    time = distance/velocity;
	    c.println("The time is " + time + " seconds."); 
	}
	
	pauseProgram();
    }
    
    /*
    Method to ask the user if they would like to continue 
    using the program or not.
    arguments: none
    returns: none
    */
    private void pauseProgram()
    {
	c.println();
	c.println("Would you like to continue using the calculator (Y/N) ?:");
	input = c.readChar();
	
	if (input == 'y' || input == 'Y')
	{
	    input = '0';
	}
    }
    
    /*
    Method to thank the user for using the program and exits 
    when they click enter, or any character then enter.
    arguments: none
    returns: none
    */
    public void goodbye()
    {
	c.println();
	c.println("Thank you for using this calculator!");
	c.println("Press ENTER to exit the program:");
	input = c.getChar();
	
	System.exit(0);
    }
    
    /*
    Main method to create an instance of my class and call the required methods.
    The do-while loop runs askData() at least once then doesnt run if the input
    value equals the characters that represents EXIT.
    arguments: none
    returns: none
    */
    public static void main (String[] args)
    {
	DVTCalculator d;
	d = new DVTCalculator();
	
	do
	{
	    d.askData();
	} while (input != '4' && input != 'n' && input != 'N');
	
	d.goodbye();
    }
}
