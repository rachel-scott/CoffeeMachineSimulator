/*
Author: Mr.Cutler
        Rachel Scott
Class: ICS4U
Program: Coffee Machine Assignment "Coffee Machine"

Coffee Machine has a method to set the strength of the coffee then the methods to 'make' the coffee:
add water, add beans, grind the beans, brew the coffee, and pour the coffee which can only be done in that order (and do exactly what they are named).
***Note: Other than water I assumed everything else is only enough to make one cup of coffee,
so every other step (ex adding beans) will be reset to 'not done' and has to be redone before the user can make another cup of coffee.

*/

package app;

// for user input
import java.util.Scanner;

public class CoffeeMachine {

    // Scanner
    Scanner input = new Scanner(System.in);

    // Keeping track of how many units of water are in the machine
    int waterLevel = 0;

    // a variable for the strength of coffee
    String strength = "";

    // booleans to track what step in the brewing process the machine is at
    boolean beansAdded = false;;
    boolean waterAdded = false;
    boolean beansGround = false;
    boolean cupBrewed = false;
    boolean cupPoured = false;

    // keeping track of the status of the machine (this gets outputted to the user)
    String beanStatus = "not added";
    String groundStatus = "no";
    String brewedStatus = "no";
    String pouredStatus = "no";


    // Setting the strength of the coffee and outputting an error message if the user inputs something other than the given options
    public void promptStrength() {
        while (strength.equals("weak") == false && strength.equals("regular") == false && strength.equals("strong") == false) {
            System.out.println("How strong would you like your coffee: weak, regular, or strong?");
            strength = input.next();
            // setting the input to all lowercase so even if they input "WEAK" for example it still works as long as it's spelt right
            strength = strength.toLowerCase();

            if (strength.equals("weak") == false && strength.equals("regular") == false && strength.equals("strong") == false) {
                System.out.println("Sorry, this machine can only brew weak, regular, or strong coffee.");
            }
        }
    }

    /*
     * Grind the beans for the coffee
     */
    public void grindBeans() {
        // only able to grind the beans if they've been added and if they haven't outputting a message telling the user that
        if (beansAdded == true) {
            System.out.println("Grinding beans for " + strength + " coffee." + "\n");
            beansGround = true;
            groundStatus = "yes";
        }
        else {
            System.out.println("You must add beans first." + "\n");
        }
    }

    /*
     * Brew the coffee into given cup c
     * @param c The cup of coffee to be filled
     */
    public void brew(app.CoffeeCup c) {
        // only able to brew if the beans have been ground (and therefore beans and water have been added) and if they haven't outputting a message telling the user that
        if (beansGround == true) {
            // getting the strength and customer name for a more detailed output
            System.out.println("Brewing " + strength + " coffee, " + c.getName());
            c.fill();
            cupBrewed = true;
            brewedStatus = "yes";
        }
        else {
            System.out.println("You need to grind the coffee beans first." + "\n");
        }
    }


    /*
     * Add water to the machine reservoir
     */
    public void addWater() {
            System.out.println("Adding Water" + "\n");
            // setting water added to true now lets the user move on to the next step
            waterAdded = true;
            // filling water to the max of 10 units
            waterLevel = 10;
    }


    /*
     * Add Beans to the Machine
     */
    public void addBeans() {
        // only letting them add beans if water has been added and if not outputting a message telling the user that
        if (waterAdded == true) {
            System.out.println("Adding Beans" + "\n");
            beansAdded = true;
            beanStatus = "added";
        }
        else {
            System.out.println("You must add water first!" + "\n");
        }
    }

    public void pourCoffee(){
        // only letting them pour the coffee if it has been brewed and if not outputting a message telling the user that
        if (cupBrewed == true){
            System.out.println("Pouring coffee" + "\n");
            cupPoured = true;
            pouredStatus = "yes";
        }
        else {
            System.out.println("You must brew the coffee first!" + "\n");
        }
    }

}