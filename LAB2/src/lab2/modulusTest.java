package lab2;

import java.util.Scanner;

public class modulusTest {

    public static int modulus(int m, int n)
    {   int count=0;
        while(m>0){m -=n;}
        if(m<0){ m+=n;}
        return m;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Divider m");
        int m = sc.nextInt();;
        System.out.println("Enter numerator n");
        int n = sc.nextInt();
        System.out.println(m+" % "+n+" = "+modulus(m,n));
    }
}
