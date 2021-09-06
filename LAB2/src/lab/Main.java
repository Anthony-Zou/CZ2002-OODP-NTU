package lab;

import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.math.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        int choice;
        Scanner sc = new Scanner(System.in);
        String[] arguments = new String[] {"123"};

        do {
            System.out.println("“Perform the following methods:”");
            System.out.println("“1: miltiplication test”");
            System.out.println("“2: quotient using division by subtraction”");
            System.out.println("“3: remainder using division by subtraction”");
            System.out.println("“4: count the number of digits”");
            System.out.println("“5: position of a digit”");
            System.out.println("“6: extract all odd digits”");
            System.out.println("“7: quit”");
            choice = sc.nextInt();
            switch (choice) {
                case 1: /* add mulTest() call */
                    mulTest multest = new mulTest();
                    multest.main(args);
                    break;
                case 2: /* add divide() call */
                    divideTest div = new divideTest();
                    div.main(args);
                    break;
                case 3: /* add modulus() call */
                    modulusTest modulusTest=new modulusTest();
                    modulusTest.main(args);
                    break;
                case 4: /* add countDigits() call */
                    countDigits countDigits = new countDigits();
                    countDigits.main(args);
                    break;
                case 5: /* add position() call */
                    position position = new position();
                    position.main(args);
                    break;
                case 6: /* add extractOddDigits() call */
                    extractOddDigits extractOddDigits = new extractOddDigits();
                    extractOddDigits.main(args);
                    break;
                case 7: System.out.println("“Program terminating ….”");
            }
        } while (choice < 7);
    }
    /* add method code here */
}