import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args) {
        // TODO: Read the input from the user and call produceAnswer with an equation
//        Scanner scanner = new Scanner(System.in);
//        String rawInput = "";
//
//        while (true) {
//            rawInput = scanner.nextLine();
//
//            if (rawInput.equals("quit")) {
//                break;
//            }
//
//            System.out.println(produceAnswer(rawInput));
//        }
        System.out.println(produceAnswer("-3_3/4 / -2_2/3"));
//        System.out.println(produceAnswer("5/6 / -2/3"));
//        System.out.println(produceAnswer("3/2 * 5/4"));
//        System.out.println(produceAnswer("9/2 - 1/4"));
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
        String product1 = scannie.next();
        String operator = scannie.next();
        String product2 = scannie.next();

        Fraction frac = new Fraction(product1);
        System.out.println(frac.toString());
        Fraction frac2 = new Fraction(product2);
        System.out.println(frac2.toString());

        Fraction newFrac;

        if (operator.equals( "+")) {
            newFrac = frac.sumFractions(frac2, false);
        }
        else if (operator.equals( "-")) {
            newFrac = frac.sumFractions(frac2, true);
        }
        else if (operator.equals( "*")) {
            newFrac = frac.productFraction(frac2, false);
        }
        else if (operator.equals( "/")) {
            newFrac = frac.productFraction(frac2, true);
        }
        else {
            return "Error! Operator not recognized, please try again.";
        }

        return newFrac.toString2();


    }






}



