import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args) {
        // TODO: Read the input from the user and call produceAnswer with an equation
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

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"


    public static String produceAnswer(String input) {
        Scanner scannie = new Scanner(input);
        String product = scannie.next();
        String operator = scannie.next();
        String product2 = scannie.next();

        Scanner scantie = new Scanner(product2);
        String wholeNum2 = splitBy(scantie, "_");
        System.out.println(wholeNum2);
        String numerator2 = splitBy(scantie, "/");
        String denominator2 = splitBy(scantie, "/");

        return "whole:" + wholeNum2 + " numerator:" + numerator2 + " denominator:" + denominator2;

    }

    public static String splitBy(Scanner scannie, String delimiter) {
        scannie.useDelimiter(delimiter);
        if (scannie.hasNext()) {
            return scannie.next();
        }
        return "0";

    }


}



