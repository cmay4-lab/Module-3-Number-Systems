import java.util.Scanner; // Scanner is necessary for obtaining and parsing user input.

public class Number_Systems
{
    static Scanner input = new Scanner(System.in); // Declare scanner to obtain input.

    public static boolean validateIfBinary(String user_input) {
        boolean input_is_valid = true;

        if (user_input.length() > 16) {
            input_is_valid = false;
            System.out.print("\nInvalid input length! Enter a binary number:");
        } else {
            int i = 0;
            
            while (i < user_input.length() && input_is_valid) {
                if (user_input.charAt(i) != '1' && user_input.charAt(i) != '0') {
                    input_is_valid = false;
                    System.out.println("\nInvalid input type! Enter a binary number:");
                }
                i++;
            }
        }
        return input_is_valid;
    }
    
    public static int byteBaseToByteExponent(int base, int exponent) {

        int power_of_base = 1;

        for (int i = 0; i < exponent; i++) {
            power_of_base *= base;
        }

        return power_of_base;
    }

    public static int convertBinaryToDecimal(String binary_number) {
        int decimal_value = 0; // Initialize decimal value to equal zero.
        int binary_number_length = binary_number.length();

        for (int i = 0; i < binary_number_length; i++) {
            int temp_digit = Character.getNumericValue(binary_number.charAt(i));
            int temp_part = temp_digit*byteBaseToByteExponent(2, binary_number_length - i - 1);
            decimal_value += temp_part;
        }

        return decimal_value; // Return the number in decimal form.
    }

    public static void printBaseListFromDecimal(int decimal_number, int base) {
        
        char[] remainder_digits = new char[6];
        char[] hexidecimal_digits = {'a', 'b', 'c', 'd', 'e', 'f'};
        int i = 5;

        int base_power = 1;
        int remainder_value = decimal_number%base;
        int quotient_value = decimal_number/base;

        boolean division_is_not_finished = true;

        System.out.print(base_power + "\t\t");

        if (base != 16) {
            remainder_digits[i] = (char) (remainder_value + '0');
            System.out.print(remainder_value + "\n");
        } else {
            System.out.print(remainder_value + "\t\t");

            if (remainder_value <= 9) {
                remainder_digits[i] = (char) (remainder_value + '0');
                System.out.print(remainder_value + "\n");
            } else {
                char char_remainder_value = hexidecimal_digits[remainder_value - 10];
                remainder_digits[i] = char_remainder_value;
                System.out.print(char_remainder_value + "\n");
            }
        }

        if (quotient_value == 0) {
            division_is_not_finished = false;
        }

        --i;

        // We print base power. Then multiply it by base. 
        // We find the 1st remainder of decimal number divided by base. Then we print it.
        // We reset loop
        // We print base power. Then mutiply it by base.
        // We find the 2nd remainder of decimal_number
        // ... 
        // We loop until quotient value is equal to zero.

        if (base != 16) {
            while (division_is_not_finished) {
                base_power *= base;
                remainder_value = quotient_value % base;
                quotient_value /= base;
                
                
                System.out.print(base_power + "\t\t");

                remainder_digits[i] = (char) (remainder_value + '0');

                System.out.print(remainder_value + "\n");

                if (quotient_value == 0) {
                    division_is_not_finished = false;
                }
                --i;
            }

            String new_base_value = new String(remainder_digits);

            if (base == 8) {
                System.out.print("Octal: " + new_base_value);
            } else {
                System.out.print("Decimal: " + new_base_value);
            }

        } else {
            while (division_is_not_finished) {
                base_power *= base;
                remainder_value = quotient_value % base;
                quotient_value /= base;
                
                System.out.print(base_power + "\t\t");
                System.out.print(remainder_value + "\t\t");

                if (remainder_value <= 9) {
                    remainder_digits[i] = (char) (remainder_value + '0');
                    System.out.print(remainder_value + "\n");
                } else {
                    char char_remainder_value = hexidecimal_digits[remainder_value - 10];
                    remainder_digits[i] = char_remainder_value;
                    System.out.print(char_remainder_value + "\n");
                }

                if (quotient_value == 0) {
                    division_is_not_finished = false;
                }
                --i;
            }

            String new_base_value = new String(remainder_digits);

            System.out.print("Hexidecimal: " + new_base_value);
        }
    }

    public static void main(String[] args) {

        System.out.print("Enter a binary number: ");
        String user_input = input.next();

        while (!validateIfBinary(user_input)) {
            user_input = input.next();
        }
        
        int decimal_user_input = convertBinaryToDecimal(user_input);
        System.out.print("\nInput: " + user_input + "\n\n");

        printBaseListFromDecimal(decimal_user_input, 8);
        System.out.print("\n");
        printBaseListFromDecimal(decimal_user_input, 10);
        System.out.print("\n");
        printBaseListFromDecimal(decimal_user_input, 16);
    }
}