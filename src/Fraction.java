import java.util.Scanner;
import java.lang.Math;

/**
 * A class which represents a Fraction object, containing the many properties of a value and supports
 * multiple operations or manipulations of the current Fraction
 * @author Zoya Brahimzadeh
 */
public class Fraction {
    // instance variables
    private int wholeNum;
    private int numerator;
    private int denominator;
    private int polarity = 1; // always 1 or -1

    /**
     * A constructor that fully parses and initializes instance variables, allotting default values
     * if the space is unforgiving in the input String
     * @param unparsedFrac the fraction string representation to be split into whole, numerator, and denominator parts
     */
    public Fraction(String unparsedFrac) {

        // adds formatting so whole number will be parsed by the rest of the method easily
        if (!unparsedFrac.contains("/")) {
            unparsedFrac += "/1";
        }

        this.polarity = findPolarity(unparsedFrac);


        // parses and finds all 3 parts of the fraction
        String wholeNum2 = splitBy(unparsedFrac, "_" );
        unparsedFrac = processDifference(unparsedFrac, wholeNum2, 1);
        String numerator2 = splitBy(unparsedFrac, "/" );
        unparsedFrac = processDifference(unparsedFrac, numerator2, 0);
        String denominator2 = splitBy(unparsedFrac, "/");

        // accounts for division by 0 errors when there's no denominator based on the way the algorithm works
        if (denominator2.equals("0")) {
            denominator2 = "1";
        }

        // sets the absolute value of each of the terms because polarity is tracked using a seperate instance variable
        this.wholeNum = Math.abs(Integer.parseInt(wholeNum2));
        this.numerator = Math.abs(Integer.parseInt(numerator2));
        this.denominator = Math.abs(Integer.parseInt(denominator2));
    }

    /**
     * A constructor in which the instance variables are all initalized using the parameters, has minimal preprocessing
     * @param whole
     * @param num
     * @param denom
     * @param polar
     */
    public Fraction(int whole, int num, int denom, int polar) {
        wholeNum = Math.abs(whole);
        numerator = Math.abs(num);
        denominator = Math.abs(denom);
        polarity = polar;
    }

    /**
     * A method in which an input String's next token, according to the delimeter, is returned,
     * essentially taking a substring of the input string which has a certain meaning, relative to
     * the delimiter, to be used in order to split a fractional piece into its seperate parts
     * @param input
     * @param delimiter
     * @return the token substring determned by the delimeter (in this case one segment of the fraction)
     */
    private static String splitBy(String input, String delimiter) {
        if (input.contains(delimiter)) {
            Scanner tempScannie = new Scanner(input);
            tempScannie.useDelimiter(delimiter);
            return tempScannie.next();
        }
        return "0";
    }

    /**
     * Determines the polarity of a fraction (as a string) by seeing if there's a negative symbol in it
     * @param input the fraction (represented by a String)
     * @return 1 or -1, indicating whether the fraction is negative (-1) or positive (1)
     */
    private static int findPolarity(String input) {
        int index = input.indexOf("-");
        if (index == -1) {
            return 1;
        }
        return -1;
    }

    /**
     * Finds part of a string that does not contain the second string passed, starts from the begining of the string
     * @param fullString base string, the bigger string
     * @param retedString secondary string
     * @param add an offset, essentially like padding, if the the user desires there to be an offset between
     *            where the base string and secondary string stop overlaping and the return string starts being processed
     * @return Substring of full string that doesn't overlap with secondary string
     */
    private static String processDifference(String fullString, String retedString, int add) {
        int length = retedString.length() + add;

        // Accounts for instances where a value is missing, such as a whole number component (x) in X 2/3 so that
        // this algorithm will still produce expected output
        if (retedString.equals("0")) {
            length = 0;
        }
        return fullString.substring(length);

    }

    /**
     * Acessor method, switches the polarity instance variable
     */
    public void polarize() { this.polarity *= -1; }


    /**
     * Simplifies a complex fraction into a mixed fraction
     * @param frac the fraction to be simplified
     */
    public static void simplify(Fraction frac) {
        int numeratorSum = frac.getNumerator();
        int wholeCount = 0;
        int denom = frac.getDenominator();

        while (numeratorSum >= denom) {
            numeratorSum -= denom ;
            wholeCount ++;
        }
        frac.setWholeNum(wholeCount);
        frac.setNumerator(numeratorSum);

        reduce(frac);
    }

    /**
     * Simplifies the fraction so that the denominator and the numerator are at the lowest terms they can be by dividing
     * until the two values no longer share a common factor
     * @param frac The fraction to be reduced
     */
    public static void reduce(Fraction frac) {
        for (int i = 2; i <= frac.getNumerator(); i ++) {
            if (frac.getNumerator()% i == 0 && frac.getDenominator()% i == 0) {
                frac.denominator = frac.getDenominator() / i;
                frac.numerator = frac.getNumerator() / i;
                i = 1;

            }
        }
    }

    /**
     * Helper function used after computations to determine if a value is negative or positive
     * @param value the value whos polarity is being tested
     * @return -1 or 1 to represent polarity
     */
    public static int findPolarity(int value) {
        if (value < 0) {
            return -1;
        }
        return 1;
    }

    /**
     * Helper function used after computations to determine if a complex fraction is negative or positive depending on
     * which values are negative, if any
     * @param value1 the first component of the fraction whos polarity is being tested
     * @param value2 the second component of the fraction whos polarity is being tested
     * @return -1 or 1 to represent polarity
     */
    public static int findPolarity(int value1, int value2) {
        if (( (double) value1)/value2 < 0) {
            return -1;
        }
        return 1;
    }

    /**
     * Adds two fractions together or subtracts them
     * @param otherFraction The second term
     * @param subtraction signifies whether the second term should be polarized in order to perform subtraction
     *                    instead of addition
     * @return a new Fraction object as the sum of the two fractions
     */
    public Fraction sumFractions(Fraction otherFraction, boolean subtraction) {

        // subtraction is just addition with the sign of the subtrahend switched
        if (subtraction) {
            otherFraction.polarize();
        }
        // initialize variables
        int b = this.getDenominator();
        int a = (this.getNumerator() + this.getWholeNum() * b) * this.getPolarity() ;
        int d = otherFraction.getDenominator();
        int c = (otherFraction.getNumerator() + otherFraction.getWholeNum() * d) * otherFraction.getPolarity() ;
        int sumNumerator;
        int baseDenominator;

        // simpler steps if fractions share denominator
        if (b == d) {
            baseDenominator = b;
            sumNumerator = a + c;
        }

        // the formula/algorithm for adding fractions when bases are different
        else {
            sumNumerator = a * d + b * c;
            baseDenominator = b * d;
        }

        // determining the polarity of the new fraction object (whether its a negative number or not)
        int newPolarity = Fraction.findPolarity(sumNumerator);


        // resetting the polarity of the fraction because of memory
        if (subtraction) {
            otherFraction.polarize();
        }

        Fraction retFrac = new Fraction(0, sumNumerator, baseDenominator, newPolarity);
        Fraction.simplify(retFrac);
        return retFrac;
    }

    /**
     * Calculates the product of two fractions, supports divison
     * @param otherFraction The second fractional term to be used as a product or the quotient
     * @param division signifies whether the second term should be "flipped" in order to perform division (using the
     *                 reciprocal instead of multiplication (a/b / c/d = a/b * d/c)
     * @return the product of the two fractional terms or product of the fraction and the reciprocal of the second
     * fractional term
     */
    public Fraction productFraction(Fraction otherFraction, boolean division) {
        int b = this.getDenominator();
        int a = (this.getNumerator() + this.getWholeNum() * b) * this.getPolarity() ;
        int d = otherFraction.getDenominator();
        int c = (otherFraction.getNumerator() + otherFraction.getWholeNum() * d) * otherFraction.getPolarity() ;

        // division is multiplication with the numerator and denominator switched so this sets otherFraction
        // equal to its reciprocal to follow standard fraction multiplication
        if (division) {
            int swappie = c;
            c = d;
            d = swappie;
        }

        int productNum = a * c;
        int productDenom = b * d;

        // determining the polarity of the new fraction object (whether its a negative number or not)
        int newPolarity = Fraction.findPolarity(productNum, productDenom);

        Fraction retFrac = new Fraction(0, productNum, productDenom, newPolarity);
        Fraction.simplify(retFrac); // this includes changing it to a mixed fraction
        return retFrac;
    }

    /**
     * Acesses denominator instance variable
     * @return denominator of Fraction object
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Acesses numerator instance variable
     * @return numerator of Fraction object
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Acesses whole number instance variable
     * @return whole number of Fraction object
     */
    public int getWholeNum() {
        return wholeNum;
    }

    /**
     * Acesses polarity instance variable
     * @return polarity (1 or -1) of Fraction object
     */
    public int getPolarity() {
        return polarity;
    }

    /**
     * Mutator method for changing the denominator of the Fraction object
     * @param denominator denominator of Fraction object
     */
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * Mutator method for changing the denominator of the Fraction object
     * @param numerator numerator of Fraction object
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Mutator method for changing the numerator of the Fraction object
     * @param wholeNum whole number of Fraction object
     */
    public void setWholeNum(int wholeNum) {
        this.wholeNum = wholeNum;
    }


    @Override
    /**
     * toString method that represents a fraction by its instance variables
     * @return a String containing each instance variable and it's label
     */
    public String toString() {
        return "whole:" + wholeNum +
                " numerator:" + numerator +
                " denominator:" + denominator +
                " polarity: " + polarity;
    }


    /**
     * toString method that represents a fraction in its mixed form
     * @return a String representing the fraction object
     */
    public String toString3() {

        if (getNumerator() == 0) {
            return polarity * wholeNum + "";
        }

        if (wholeNum != 0) {
            return polarity * wholeNum + "_" + numerator + "/" + denominator;
        }


        return polarity * numerator + "/" + denominator;
    }
}

