import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner scannie = new Scanner(System.in);
        String rawInput = scannie.nextLine();
        System.out.println(produceAnswer(rawInput));

    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
        Scanner splittie = new Scanner(input);
        splittie.useDelimiter(" "); // default value but issok

        String product1 = splittie.next();
        String operand = splittie.next();
        String product2 = splittie.next();
        
        return product2;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
