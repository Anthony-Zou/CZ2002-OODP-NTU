package lab;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.math.*;

public class extractOddDigits {
    public static long extractOddDigits(long n) {
        long result = 0;
        long temp=0;
        while(n > 0)
        {
            int left = (int) (n % 10);
            if(left % 2 != 0)
            {temp = temp*10+left  ; }
            n /= 10;
        }
        while(temp != 0) {

            // get last digit from num
            long digit = temp % 10;
            result = result * 10 + digit;

            // remove the last digit from num
            temp /= 10;
        }
        return result;

    }

    ;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Numbers n");
        int n = sc.nextInt();
        if (n >= 0) {
            long result = extractOddDigits(n);
            if (result > 0) {
                System.out.println("OddDigits = " + result + "");
            } else {
                System.out.println("OddDigits = -1");
            }
        } else {
            System.out.println("OddDigits = Error input!!");
        }
    }
}
