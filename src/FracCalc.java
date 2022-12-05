import java.util.Scanner;

/**
 * A class that acts like a calculator, able to compute any operation between two fractions or whole numbers inputted
 * by the user and supports multiple successions of input.
 * @author Zoya Brahimzadeh
 */

public class FracCalc {
    /**
     * A main method that takes in initial user input and moderates the feedback loop between user and computer,
     * printing out completed calculations.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawInput = "";

        while (true) {
            rawInput = scanner.nextLine();

            if (rawInput.equals("quit")) {
                break;
            }

            System.out.println(produceAnswer(rawInput));
        }

    }

    /** A function that parses the input, seperating the main components in order to create a Fraction object
     * and then develop a correct method call using static calculation methods from the Fraction class in order
     * to compute the output
     * @param input the input String provided by the user containing the two values and the operand to relate them
     * @return a String containing the computed product of the two values with the requested operand
     */
    public static String produceAnswer(String input) {
        // Parses input into 3 parts: 2 fractional parts and the operand
        Scanner scannie = new Scanner(input);
        String product1 = scannie.next();
        String operator = scannie.next();
        String product2 = scannie.next();

        Fraction frac = new Fraction(product1);
        Fraction frac2 = new Fraction(product2);


        Fraction newFrac;

        // checks if the user requested addition
        if (operator.equals( "+")) {
            newFrac = frac.sumFractions(frac2, false);
        }
        // checks if the user requested subtraction
        else if (operator.equals( "-")) {
            newFrac = frac.sumFractions(frac2, true);
        }
        // checks if the user requested multiplication
        else if (operator.equals( "*")) {
            newFrac = frac.productFraction(frac2, false);
        }
        // checks if the user requested division
        else if (operator.equals( "/")) {
            newFrac = frac.productFraction(frac2, true);
        }

        // Operand is not one of the ones supported by the calculator
        else {
            return "Error! Operator not recognized, please try again.";
        }

        // Reduces the fraction before returning it
        Fraction.reduce(newFrac);

        return newFrac.toString3();


    }






}



