package lab2;
import java.util.Scanner;

public class mulTest {

    public static int NumofCorrectAns=0;
    public static void mulTest()
    {

        int a =(int)(Math.random() * 10) + 1;
        int b =(int)(Math.random() * 10) + 1;
        int c=a*b;
        Scanner sc = new Scanner(System.in);
        System.out.println("How much is "+a+ " times "+b+"?");
        int answer = sc.nextInt();
        if(answer==c)
        {
             NumofCorrectAns++;
        }
    }
    public static void main(String[] args)
    {   NumofCorrectAns=0;
        System.out.println("Hello");
        for(int i=0;i<5;i++){
        mulTest();}
        System.out.println(NumofCorrectAns+" answers out of 5 are correct");
    }
}
