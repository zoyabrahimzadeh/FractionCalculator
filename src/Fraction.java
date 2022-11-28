import java.util.Scanner;
import java.lang.Math;
public class Fraction {
    private int wholeNum;
    private int numerator;
    private int denominator;
    private int polarity = 1; // always 1 or -1

    public Fraction(String unparsedFrac) {

        if (!unparsedFrac.contains("/")) {
            unparsedFrac += "/1";
        }

        // assuming only one instance of negative symbol
        this.polarity = findPolarity(unparsedFrac);

        String wholeNum2 = splitBy(unparsedFrac, "_" );
        unparsedFrac = processDifference(unparsedFrac, wholeNum2, 1);
//        System.out.println(wholeNum2);
        String numerator2 = splitBy(unparsedFrac, "/" );
        unparsedFrac = processDifference(unparsedFrac, numerator2, 0);
        String denominator2 = splitBy(unparsedFrac, "/");

        if (denominator2.equals("0")) {
            denominator2 = "1";
        }

        this.wholeNum = Math.abs(Integer.parseInt(wholeNum2));
        this.numerator = Math.abs(Integer.parseInt(numerator2));
        this.denominator = Math.abs(Integer.parseInt(denominator2));
    }

    public Fraction(int whole, int num, int denom, int polar) {
        wholeNum = Math.abs(whole);
        numerator = Math.abs(num);
        denominator = Math.abs(denom);
        polarity = polar;
    }

    private static String splitBy(String input, String delimiter) {
        if (input.contains(delimiter)) {
            Scanner tempScannie = new Scanner(input);
            tempScannie.useDelimiter(delimiter);
            return tempScannie.next();
        }
        return "0";
    }

    private static int findPolarity(String input) {
        int index = input.indexOf("-");
        if (index == -1) {
            return 1;
        }
        return -1;
    }

    private static String processDifference(String fullString, String retedString, int add) {
        int length = retedString.length() + add;
        if (retedString.equals("0")) {
            length = 0;
        }
        return fullString.substring(length);

    }

    public void polarize() {
        polarity *= -1;
    }


    // no memory issues with integers, right?
    // fix for simplifying with negative numbers, do -= denom * polarity
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
    }

    public static int findPolarity(int value) {
        if (value < 0) {
            return -1;
        }
        return 1;
    }

    // later adapt to take in an array of values or smt
    public static int findPolarity(int value1, int value2) {
        if (value1/value2 < 0) {
            return -1;
        }
        return 1;
    }
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

        // the formula for adding fractions when bases are different
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

    public Fraction productFraction(Fraction otherFraction, boolean division) {
        int b = this.getDenominator();
        int a = (this.getNumerator() + this.getWholeNum() * b) * this.getPolarity() ;
        int d = otherFraction.getDenominator();
        int c = (otherFraction.getNumerator() + otherFraction.getWholeNum() * d) * otherFraction.getPolarity() ;

//         division is multiplication with the numerator and denominator switched
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




    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getWholeNum() {
        return wholeNum;
    }

    public int getPolarity() {
        return polarity;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setWholeNum(int wholeNum) {
        this.wholeNum = wholeNum;
    }

    @Override
    public String toString() {
        return "whole:" + wholeNum +
                " numerator:" + numerator +
                " denominator:" + denominator +
                " polarity: " + polarity;
    }

    // fraction format
    public String toString2() {
        if (wholeNum != 0) {
            return polarity * wholeNum + "_" + numerator + "/" + denominator;
        }
        return polarity * numerator + "/" + denominator;
    }
}

