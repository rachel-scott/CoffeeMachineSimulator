/*
 Author: Mr.Cutler
         Rachel Scott
 Class: ICS4U
 Program: Coffee Machine Assignment "Coffee Klatch"

 Input: The user inputs letters which correspond to commands that add ingredients, use the machine, and drink the coffee.

 Processing: I used a while loop that will continue presenting the user with options for making and drinking their coffee until they
 choose "new customer", in which case everything resets and continues, or "exit" which ends the program.

 Output: The program will first ask for the customer's name, drink size, and coffee strength. If they choose a size or
 strength that is not an option, they will receive an error message asking them to try again.
 If the user inputs commands in the correct order, the program will outputs confirmation that the command has
 been completed (for example: "Adding beans"). If the user attempts a command out of order, the program will
 output an error message (for example: "You must add beans first" if the user tries to grind beans but has not added beans).
 After each command, the program will re-output the list of options and status of the machine.
 */

package app;

// Importing the Scanner to get user input
import java.util.Scanner;

public class CoffeeKlatch {

    public static void main(String[] args) {

        boolean customerExit = false;

        Scanner input = new Scanner(System.in);

        // constructing the coffee machine and cup
        app.CoffeeMachine coffeeMachine = new app.CoffeeMachine();
        app.CoffeeCup coffeeCup = new app.CoffeeCup();

        // Asking the user their name, how strong they want their coffee, what size they want their cup
        coffeeCup.setName();
        coffeeCup.setSize();
        coffeeMachine.promptStrength();


        // giving the customer the options so they can make coffee
        // the loop will allow them to continue making coffee/become a new customer until they choose to exit
     while (customerExit == false) {

         System.out.printf( "Water Level: " + coffeeMachine.waterLevel + "\t Beans: " + coffeeMachine.beanStatus + "\t Beans Ground: " + coffeeMachine.groundStatus + "\t Brewed: " + coffeeMachine.brewedStatus + "\t Poured: " + coffeeMachine.pouredStatus + "\t Consumed: " + coffeeCup.drankStatus + "\n");
         System.out.printf("Options: \n w - add water \t b - add beans \t g - grind beans \t r - brew coffee \t " + "p - pour into cup \t d - drink coffee \t n - new customer \t x - exit \n");

         String command = input.next();

         // as long as you have enough water for your size coffee, it doesn't have to be 10 units full to make coffee
         if (coffeeMachine.waterLevel >= coffeeCup.waterPerCup){
             coffeeMachine.waterAdded = true;
         }
         else if (coffeeMachine.waterLevel < coffeeCup.waterPerCup){
             coffeeMachine.waterAdded = false;
         }

         // The letter the user enters will run the corresponding step in CoffeeMachine
         // The nested if statements check to see if the step has been completed and if so output a statement. For example: you can't brew or pour the coffee twice.
         // For water you can add more as long as you are under 10 units. Whether you are currently at 0, 3, 8, etc units it will fill the the max of 10.
         if (command.equals("w")) {
             if (coffeeMachine.waterLevel < 10){
                 coffeeMachine.addWater();
                }
             //If you are already at the max level of 10 it won't let you add any more water.
             else if (coffeeMachine.waterLevel == 10){
                    System.out.println("You've already added water!");
             }
         }
         else if (command.equals("b")) {
             if (!coffeeMachine.beansAdded) {
                    coffeeMachine.addBeans();
             }
             else if (coffeeMachine.beansAdded){
                    System.out.println("You've already added beans!");
             }
         }
            else if (command.equals("g")) {
             if (!coffeeMachine.beansGround){
                 coffeeMachine.grindBeans();
             }
             else if (coffeeMachine.beansGround){
                 System.out.println("You've already ground the beans!");
             }
         } else if (command.equals("r")) {
             if (!coffeeMachine.cupBrewed) {
                 coffeeMachine.brew(coffeeCup);
                 // decreasing the water level based on the cup size once you've used some to brew coffee
                 if (coffeeMachine.beansGround == true) {
                     if (coffeeCup.cupSize.equals("s")) {
                         coffeeMachine.waterLevel -= 2;
                     } else if (coffeeCup.cupSize.equals("m")) {
                         coffeeMachine.waterLevel -= 3;
                     } else if (coffeeCup.cupSize.equals("l")) {
                         coffeeMachine.waterLevel -= 4;
                     }
                 }
             }
             else if (coffeeMachine.cupBrewed){
                 System.out.println("You already brewed the coffee!");
             }
         } else if (command.equals("p")) {
             if (!coffeeMachine.cupPoured) {
                 coffeeMachine.pourCoffee();
             }
             else if (coffeeMachine.cupPoured){
                 System.out.println("You've already poured the coffee!");
             }
         } else if (command.equals("d")) {
             coffeeCup.drink(coffeeMachine);
             // resetting everything (except water) so they can't drink more coffee unless they make more.
             // The name, cup size, and strength will remain the same unless they select new customer
             coffeeMachine.beansAdded = false;
             coffeeMachine.beansGround = false;
             coffeeMachine.cupBrewed = false;
             coffeeMachine.cupPoured = false;
             coffeeMachine.beanStatus = "not added";
             coffeeMachine.groundStatus = "no";
             coffeeMachine.brewedStatus = "no";
             coffeeMachine.pouredStatus = "no";
             coffeeCup.drankStatus = "no";
         }
         else if (command.equals("n")) {
             // resetting everything for the new customer as if the previous customer didn't exist
             coffeeMachine.waterLevel = 0;
             coffeeMachine.beansAdded = false;;
             coffeeMachine.waterAdded = false;
             coffeeMachine.beansGround = false;
             coffeeMachine.cupBrewed = false;
             coffeeMachine.cupPoured = false;
             coffeeMachine.beanStatus = "not added";
             coffeeMachine.groundStatus = "no";
             coffeeMachine.brewedStatus = "no";
             coffeeMachine.pouredStatus = "no";
             coffeeCup.drankStatus = "no";

             coffeeCup.cupSize = "";
             coffeeMachine.strength = "";

             // allowing the new customer to set their name/cup size/coffee strength
             coffeeCup.setName();

             coffeeCup.setSize();

             coffeeMachine.promptStrength();
         }
         // exiting the loop is the customer chooses to leave
         else if (command.equals("x")){
             System.out.println("Thank you, goodbye.");
             customerExit = true;
         }
         // only letting the user input w,b,g,r,p,d,n,x
         else {
             System.out.println("You must choose an option from the list.");
         }
     }


        }

    }
