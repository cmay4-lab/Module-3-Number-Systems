import java.util.Scanner; // Scanner is necessary for obtaining and parsing user input.
import java.lang.Math; // pow() is useful.

public class Number_Systems
{
    static Scanner input = new Scanner(System.in); // Declare scanner to obtain input.
    
    public static double convertBinaryToDecimal(String binary_number) {
        double decimal_value = 0; // Initialize decimal value to equal zero.

        for (int i = 0; i < binary_number.length(); i++) {
            
            int temp_digit = binary_number.charAt(i); // Cycle through individual digits of binary number.
            double temp_part = temp_digit*Math.pow(2,binary_number.length() - i - 1); // Times then by corresponding power of two.
            decimal_value += temp_part; // Sum up each converted value into decimal value.
        }

        return decimal_value; // Return the number in decimal form.
    }
    
    public static boolean validateIfBinary(String user_input) {
        boolean input_is_valid = true;

        if (user_input.length() > 16) {
            input_is_valid = false;
            System.out.println("Invalid input! Enter a binary number");
        } else {
            int i = 0;
            while (i < user_input.length() && input_is_valid) {
                if (user_input.charAt(i) != 1 && user_input.charAt(i) != 0) {
                    input_is_valid = false;
                    System.out.println("Invalid input! Enter a binary number");
                }
                i++;
            }
        }
        return input_is_valid;
    }

    public static void main(String[] args) {

        System.out.println("Enter a binary number");
        String user_input = input.next();

        while (!validateIfBinary(user_input)) {
            user_input = input.next();
        }
        
        double decimal_user_input = convertBinaryToDecimal(user_input);
        
        
        

    }
}
