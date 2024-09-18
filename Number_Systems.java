import java.util.Scanner; // Scanner is necessary for obtaining and parsing user input.
import java.lang.Math; // pow() is useful.

public class Number_Systems
{
    public static double convertBinaryToDecimal(String number)
    {
        int num_of_digits = number.length(); // Declare variable for length.
        double decimal_value = 0; // Initialize decimal value to equal zero.

        for (int i = 0; i < num_of_digits; i++)
        {
            int temp_digit = number.charAt(num_of_digits - i - 1); // Cycle through individual digits of binary number.
            double temp_part = temp_digit*Math.pow(2,i); // Times then by corresponding power of two.
            decimal_value = decimal_value + temp_part; // Sum up each converted value into decimal value.
        }

        return decimal_value; // Return the number in decimal form.
    }

    public static void main(String[] args)
    {


    }


}
