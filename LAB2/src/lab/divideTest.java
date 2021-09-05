package lab;
import java.util.Scanner;
import java.math.*;
public class divideTest {
    public static int divide(int m, int n)
    {   int count=0;
        while(m>0){m -=n;count++;}
        if(m<0){ m+=n;count--;}
        return count;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Divider m");
        int m = sc.nextInt();;
        System.out.println("Enter numerator n");
        int n = sc.nextInt();
        System.out.println(m+"/"+n+" = "+divide(m,n));
    }
}
