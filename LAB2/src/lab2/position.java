package lab2;

import java.util.Scanner;

public class position {
    public static int position(int n, int digit)
    {
        // Converting the integer input to string data
        String string_number = Integer.toString(n);
        String digits = Integer.toString(digit);
        int count=0;
        // Traversing through the string using for loop
        for (int i = string_number.length()-1; i >= 0; i--) {
            count++;
            // Printing the characters at each position
            //System.out.println(string_number.charAt(i));
            if(string_number.charAt(i)==digits.charAt(0))
            {
                return count;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Numbers n");
        int n = sc.nextInt();
        System.out.println("Enter Digit");
        int digit = sc.nextInt();
        System.out.println("position = " + position(n,digit) + "");
    }
}
