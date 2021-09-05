package lab;

import java.util.Scanner;
public class Lab2p1 {
    public static void main(String[] args)
    {
        int choice;
        int[] intList;
        int size=100;
        intList = new int[size];
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Perform the following methods:");
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
                    System.out.println("This is a 2 digit multiplyer");
                    System.out.println("Enter first digit");
                    double a = sc.nextDouble();
                    System.out.println("Enter second digit");
                    double b = sc.nextDouble();
                    double c = multiplyer2D(a,b);
                    System.out.println("The result of "+a+" X "+b+" = "+c);
                    break;
                case 2: /* add divide() call */
                    System.out.println("This is a 2 digit Divider");
                    System.out.println("Enter numerator");
                    double a2 = sc.nextDouble();
                    double b2=0;
                    while (b2==0)
                    {
                    System.out.println("Enter non zero denominator");
                    b2= sc.nextDouble();
                    }
                    double c2 = divider2D(a2,b2);
                    System.out.println("The result of "+a2+" / "+b2+" = "+c2);
                    break;
                case 3: /* add modulus() call */
                    System.out.println("This is a 2 digit modulo");
                    System.out.println("Enter numerator");
                    double a3 = sc.nextDouble();
                    double b3=0;
                    while (b3==0)
                    {
                        System.out.println("Enter non zero denominator");
                        b3= sc.nextDouble();
                    }
                    double c3 = modulo2D(a3,b3);
                    System.out.println("The result of "+a3+" / "+b3+" = "+c3);
                    break;
                case 4: /* add countDigits() call */

                    System.out.println("Enter your digits for the counter and enter -1 to stop");
                    int num;
                    int count =0;
                    do
                    {
                        num=sc.nextInt();
                        intList[count] = num;
                        count++;
                    }while(num!=-1);

                    System.out.println("Counter "+ (count-1)+" digits");
                    break;
                case 5: /* add position() call */
                    break;
                case 6: /* add extractOddDigits() call */
                    break;
                case 7: System.out.println("“Program terminating ….”");
            }
        } while (choice < 7);
    }
    /* add method code here */
    public static float multiplyer2D(double a, double b)
    {
        return (float) (a * b);
    }
    public static float divider2D(double a, double b)
    {
        return (float) (a / b);
    }

    public static float modulo2D(double a, double b)
    {
        return (float) (a % b);
    }
}


/* // import java library classes
// google "java api spec" to view available classes in JDK
// Scanner class is most commonly used class to get console input and others..(check API Spec)
import java.util.Scanner;

class ClassTemplate   // class name - file name must be same as class name
{
	// for a start, use "public static" when defining your methods (function)
	// "public static" make the method global just like 'C'
	// when you have learnt OO, you will use LESS of static
   public static <return type> methodname(<argument list>) {
		//write your code here
		// think in 'C' syntax
   }

   // entry into a Java program - in 'C' is int main()
   public static void main(String args[])
   {
		//write your code here
		// think in 'C' syntax
	  // use "System.out.println" for output display
      // "System.out.println" uses "+" to concatenate (join) String and other variable data
   }
}*/