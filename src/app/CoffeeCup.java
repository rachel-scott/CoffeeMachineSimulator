/*
Author: Mr.Cutler
        Rachel Scott
Class: ICS4U
Program: Coffee Machine Assignment "Coffee Cup"

Coffee Cup has method that set the customer name and size of the cup, as well as a drink method to "drink" the coffee
that can only run once all of the steps to brew the coffee have been completed.

*/


package app;

// scanner to get user input
import java.util.Scanner;

public class CoffeeCup {

    // scanner
    Scanner input = new Scanner(System.in);
    // declaring variables for the customer's name, drink size
    private static String name;
    String cupSize = "";
    // an integer variable that tracks how much water each size uses (2/3/4 units), set further down
    int waterPerCup;
    // tracking whether the coffee in the cup has been consumed yet
    String drankStatus = "no";

    // asking the customer for their name and setting that as the name of the cup
    public String setName(){
        // It just asks for a first name so I just used input.next instead of input.nextLine to get a full name
        System.out.println("What's your first name?");
        CoffeeCup.name = input.next();
        return name;
    }

    // getting the customer's name (for ex. used in the Coffee Machine brew method)
    public String getName(){
       return name;
    }

    // setting the cup size as either s/m/l and outputting an error message if the user inputs something else
    public void setSize() {
        while (cupSize.equals("s") == false && cupSize.equals("m") == false &&  cupSize.equals("l") == false) {
            System.out.println("What size cup what you like: (s)mall, (m)edium, or (l)arge?");
            cupSize = input.next();
            if (cupSize.equals("s") == false && cupSize.equals("m") == false && cupSize.equals("l") == false) {
                System.out.println("Sorry, this machine can only brew small, medium, or large coffees.");
            }
        }
        // how much the water level will decrease after a cup of coffee is brewed (2 for small, 3 for medium, 4 for large)
        if (cupSize.equals("s")){
            waterPerCup = 2;
        }
        if (cupSize.equals("m")){
            waterPerCup = 3;
        }
        if (cupSize.equals("l")){
            waterPerCup = 4;
        }
    }



    private boolean isFull;  // Is this cup full?  Default value false.

    /*
     * Returns whether this cup is full (true) or empty(false);
     * @return is this cup full?
     */
    public boolean isFull() {
        return isFull;
    }

    /*
     * Fill this cup to the top
     */
    public void fill() {
        isFull = true;
    }

    /*
     * Drink this cup entirely
     */
    public boolean drink(CoffeeMachine x) {
        if (x.cupPoured == true) {
            if (isFull) {
                System.out.println(getName() + ", you glug the coffee down.");
                drankStatus = "Yes";
                isFull = false;
                return true;

            }
            else {
                System.out.println(getName() + ", you sip furiously, but only suck air.");
                return false;
            }
        }
        else {
            System.out.println(getName() + ", you must have coffee in the cup first.");
            return false;
        }
    }


}

