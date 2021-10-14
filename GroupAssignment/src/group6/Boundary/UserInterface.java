package Boundary;
        import Controller.*;
        import java.util.*;
        import Entity.*;
        import java.io.Console;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.Scanner;
/**
 * Main program method.
 *
 * @param_args
 */
public class UserInterface {
    private static Staff Staff;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option;
        System.out.println("#\t\tWelcome to Stars Planner\t\t#");
        Welcome();
        do {
            option = scan.nextInt();
            switch (option) {
                case 1:
                    StaffMenu.display(Staff);
                    System.out.println("\nThis is AdminMenu");
                    option = -1;
                    break;
                default:
                    System.out.println("Choose from the options");
                    Welcome();
                    break;
            }
        } while (option != 0);
        scan.close();
    }
    private static void Welcome() {
        System.out.println("\nEnter 1 for Admin Menu");
        System.out.println("\nEnter 0 To Quit");
    }
}