package Boundary;
import java.util.*;
import Controller.InterfaceController;
import Entity.*;

public class StaffMenu {
    /**
     * Choice selected by the user
     */
    private static int userChoice;
    /**
     *
     * A method to display the Staff functions.
     *
     * */
    private static InterfaceController InterfaceController;
    public static void display(Staff Staff){
        InterfaceController = new InterfaceController(Staff);
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your choice");
            System.out.println("1. Create/Update/Remove menu item");
            System.out.println("2. Create/Update/Remove promotion");
            System.out.println("3. Create order");
            System.out.println("4. View order");
            System.out.println("5. Add/Remove order item/s to/from order");
            System.out.println("6. Create reservation booking");
            System.out.println("7. Check/Remove reservation booking");
            System.out.println("8. Check table availability");
            System.out.println("9. Print order invoice");
            System.out.println("10. Print sale revenue report by period (eg day or month)");
            System.out.println("0. Exit");
            userChoice= sc.nextInt();
            switch(userChoice)
            {
                case 0:
                    break;
                case 1:
                    InterfaceController.addUpdateDeleteMenuItem();
                    break;
                case 2:
                    InterfaceController.addUpdateDeletePromotion();
                    break;
                case 3:
                    InterfaceController.createOrder();
                    break;
                case 4:
                    InterfaceController.viewOrder();
                    break;
                case 5:
                    InterfaceController.addDeleteOrderItem();
                    break;
                case 6:
                    InterfaceController.createReservation();
                    break;
                case 7:
                    InterfaceController.checkRemoveRervationBooking();
                    break;
                case 8:
                    InterfaceController.checkTableAvailaabitity();
                    break;
                case 9:
                    InterfaceController.printOrderInvoice();
                    break;
                case 10:
                    InterfaceController.printSalesRevenueReport();
                    break;
                default: System.out.println("Invalid");
            }
        }while(userChoice!=0);
    }
}
