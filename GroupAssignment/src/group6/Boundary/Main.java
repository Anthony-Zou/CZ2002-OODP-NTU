package Boundary;
import Controller.*;
import java.util.*;
import Entity.*;
import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        int option;

        Welcome();

        do{

            option= scan.nextInt();
            switch (option) {
                case 1 -> System.out.println("\nThis is AdminMenu");

                // AdminMenu.display(Admin);
                case 2 -> System.out.println("\nThis is CustomerMenu");

                //CustomerMenu.display(Customer);
                default -> System.out.println("Choose from the options");
            }
        }while(option!=-1);

        scan.close();
    }
    private static void Welcome(){
        System.out.println("#\t\tWelcome to Stars Planner\t\t#");
        System.out.println("\nEnter 1 for Admin Menu");
        System.out.println("\nEnter 2 for Customer Menu");
        System.out.println("\nEnter -1 To Quit");
    }

}