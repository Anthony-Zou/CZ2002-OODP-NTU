package lab2;

import java.util.Scanner;

public class countDigits {
    public static int countDigits(int n)
    {
        int count=0;
        while(n>0){
            n/=10;
            count++;
        }
        return count;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Digits");
        int m = sc.nextInt();;
        if(m>=0) {
            System.out.println("n : " + m + " - count = " + countDigits(m) + "");
        }
        else{
            System.out.println("n : " + m + " - count = Error input!!");
        }
    }
}
