import java.util.Scanner; // Scanner is necessary for obtaining and parsing user input.

public class NumberSystems
{
    static Scanner input = new Scanner(System.in); // Declare scanner to obtain input.
    
    /**
     * Returns the boolean "inputIsValid" that explains the validity of the user's inputed string for binary conversion.
     * The "userInput" parameter is a string that must represent a binary number.
     * The returned bool will be false unless the following conditions are met:
     * userInput is less than or equal to sixteen.
     * All characters of userInput is either zero or one.
     * 
     * @param userInput A string that represents binary.
     * @return           A boolean that validates the userInput.
     */
    public static boolean validateIfBinary(String userInput) {
        boolean inputIsValid = true;

        if (userInput.length() > 16) {
            inputIsValid = false;
            System.out.print("\nInvalid input length! Enter a binary number:");
        } else {
            int i = 0;
            
            while (i < userInput.length() && inputIsValid) {
                if (userInput.charAt(i) != '1' && userInput.charAt(i) != '0') {
                    inputIsValid = false;
                    System.out.println("\nInvalid input type! Enter a binary number:");
                }
                i++;
            }
        }
        return inputIsValid;
    }
    
    /**
     * Returns a given integer to the power of a given integer.
     * The "base" parameter is an integer that is part of the product-increment.
     * The "exponent" parameter is an integer that tells the product-increment loop how many times it needs to do multiplication.
     * The result is a power method for integer inputs and outputs only.
     * This will be used as a more efficent power method than the Math.pow() method that inputs and outputs doubles.
     * It is used for a power of two in the "convertBinaryToDecimal(String binaryNumber)" method; in that method, it is inside of a for-loop.
     * 
     * @param base     An integer that represents the base in a power function (algebra).
     * @param exponent An integer that represents the exponent in a power function (algebra).
     * @return         The base parameter to the power of the exponent parameter.
     */
    public static int byteBaseToByteExponent(int base, int exponent) {

        int powerOfBase = 1;

        for (int i = 0; i < exponent; i++) {
            powerOfBase *= base;
        }

        return powerOfBase;
    }

    /**
     * Returns a integer "decimalValue" used in conversions in the "printBaseListFromDecimal(int decimalNumber, int base)" method.
     * The "binaryNumber" parameter is a String which is equivalent to the "user_input" variable in the "main(String[] args)" method.
     * It is used once the user_input validation loop was finished, hence the String is now recongised as a reasonably valid binary number and not just an input.
     * "Horner's method" is then used to expand the binary into powers of two from zero to n.
     * This results in a complete conversion to decimal.
     * The method can be found here: en.wikipedia.org/wiki/Positional_notation#Base_conversion
     * 
     * @param binaryNumber A string that represents a binary number.
     * @return              An integer that represents an decimal number equal to that binary number.
     */
    public static int convertBinaryToDecimal(String binaryNumber) {
        int decimalValue = 0;
        int binaryNumberLength = binaryNumber.length();

        for (int i = 0; i < binaryNumberLength; i++) {
            int tempDigit = Character.getNumericValue(binaryNumber.charAt(i));
            int tempPart = tempDigit*byteBaseToByteExponent(2, binaryNumberLength - i - 1);
            decimalValue += tempPart;
        }

        return decimalValue;
    }

    /**
     * Returns nothing: The main purpose of the method is to print out two or three columns of text about the base that is inputed.
     * The "base" parameter is an integer that is the chosen base to convert to.
     * The "decimalNumber" parameter is an integer that is the value being converted for numbers on this table.
     * The two character arrays are declared: an array to store remainder values and an constant array to store the extra character "digits" to account for hexidecimal values.
     * An throwaway index value is initialized to equal five and four other variables are declared and initialized to hold values throughout a following while-loop.
     * If base is not hexidecimal (not equal to sixteen), then a secondary if-then statement that adds a third column can be ignored.
     * The while-loop is equivalent for all bases otherwise.
     * The loops essentially print out the powers of the base respective to the digit significance, the remainders of the decimal_number divided by the base, and the lastly the complete number converted from decimal into the correct base.  
     * The process is modeled by the "Divison Method," otherwise known as "Euclidean Division."
     * This method can also be found here: en.wikipedia.org/wiki/Positional_notation#Base_conversion
     * The construction of the method was made with the goal of recyclability in mind, as the method uses a second parameter that changes what base to print.
     * Calling the same method three times is a better alternative than declaring three near identical methods once. 
     * 
     * @param decimalNumber An integer that represents a decimal number.
     * @param base           An integer that represents the chosen base to convert the printed table to. 
     */
    public static void printBaseListFromDecimal(int decimalNumber, int base) {
        
        char[] remainderDigits = new char[6];
        char[] HEXIDECIMAL_DIGITS = {'a', 'b', 'c', 'd', 'e', 'f'};
        int i = 5;

        int basePower = 1;
        int remainderValue = decimalNumber%base;
        int quotientValue = decimalNumber/base;

        boolean divisionIsNotFinished = true;

        System.out.print(basePower + "\t\t");

        if (base != 16) {
            remainderDigits[i] = (char) (remainderValue + '0');
            System.out.print(remainderValue + "\n");
        } else {
            System.out.print(remainderValue + "\t\t");

            if (remainderValue <= 9) {
                remainderDigits[i] = (char) (remainderValue + '0');
                System.out.print(remainderValue + "\n");
            } else {
                char charRemainderValue = HEXIDECIMAL_DIGITS[remainderValue - 10];
                remainderDigits[i] = charRemainderValue;
                System.out.print(charRemainderValue + "\n");
            }
        }

        if (quotientValue == 0) {
            divisionIsNotFinished = false;
        }

        --i;

        /*
         * We print base power. Then multiply it by base.
         * We find the 1st remainder of decimal number divided by base. Then we print it.
         * We reset loop.
         * We print base power. Then mutiply it by base.
         * We find the 2nd remainder of decimal_number.
         * ...
         * We loop until quotient value is equal to zero.
         */

        if (base != 16) {
            while (divisionIsNotFinished) {
                basePower *= base;
                remainderValue = quotientValue % base;
                quotientValue /= base;
                
                
                System.out.print(basePower + "\t\t");

                remainderDigits[i] = (char) (remainderValue + '0');

                System.out.print(remainderValue + "\n");

                if (quotientValue == 0) {
                    divisionIsNotFinished = false;
                }
                --i;
            }

            String newBaseValue = new String(remainderDigits);

            if (base == 8) {
                System.out.print("Octal: " + newBaseValue);
            } else {
                System.out.print("Decimal: " + newBaseValue);
            }

        } else {
            while (divisionIsNotFinished) {
                basePower *= base;
                remainderValue = quotientValue % base;
                quotientValue /= base;
                
                System.out.print(basePower + "\t\t");
                System.out.print(remainderValue + "\t\t");

                if (remainderValue <= 9) {
                    remainderDigits[i] = (char) (remainderValue + '0');
                    System.out.print(remainderValue + "\n");
                } else {
                    char charRemainderValue = HEXIDECIMAL_DIGITS[remainderValue - 10];
                    remainderDigits[i] = charRemainderValue;
                    System.out.print(charRemainderValue + "\n");
                }

                if (quotientValue == 0) {
                    divisionIsNotFinished = false;
                }
                --i;
            }

            String new_base_value = new String(remainderDigits);

            System.out.print("Hexidecimal: " + new_base_value);
        }
    }

    /**
     * Returns nothing: this is the main method.
     * It goes through a validation-loop for binary input.
     * Once it obtains this, it is inputed into the "convertBinaryToDecimal(String binaryNumber)" to be initialized as the integer "decimalUserInput."
     * It prints out the original binary input, then prints three tables displaying the binary conversion to Octal, Decimal, and Hexidecimal.
     * With that, the program main task is complete.
     * Finally, the program asks if the user wishes to input more binary numbers, so the user does not need to rerun each time.
     * 
     * @param args Default "main" parameter.
     */
    public static void main(String[] args) {

        System.out.print("Enter a binary number: ");
        String userInput = input.next();

        while (!validateIfBinary(userInput)) {
            userInput = input.next();
        }
        
        int decimalUserInput = convertBinaryToDecimal(userInput);
        System.out.print("\nInput: " + userInput + "\n\n");

        printBaseListFromDecimal(decimalUserInput, 8);
        System.out.print("\n");
        printBaseListFromDecimal(decimalUserInput, 10);
        System.out.print("\n");
        printBaseListFromDecimal(decimalUserInput, 16);
    }
}