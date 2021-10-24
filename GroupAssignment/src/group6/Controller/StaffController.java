package Controller;
import Entity.*;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Entity.Staff;

public class StaffController {
    Staff CurrentStaff;
    public StaffController(Staff staff) {
        CurrentStaff=staff;
    }
    Scanner sc = new Scanner(System.in);

    public void addUpdateDeleteMenuItem() {
        MenuItemController MenuItemController = new MenuItemController();
        System.out.println("Add/Update/Delete a MenuItem");
        System.out.println("---------------------");
        int choice=0;
        do{
            System.out.println("\nWhat do you wish to do:");
            System.out.println("1. Print All MenuItem\n" + "2. Add MenuItem\n" + "3. Update Existing MenuItem\n"
                    + "4. Delete MenuItem\n"+"5.Cancel");

            while(!sc.hasNextInt()){
                sc.next();
                System.out.println("Please enter valid option:");

            }
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    MenuItemController.printMenuItem();
                    break;
                case 2:
                    MenuItemController.addMenuItem();
                    break;
                case 3:
                    MenuItemController.updateMenuItem();
                    break;
                case 4:
                    MenuItemController.deleteMenuItem();
                    break;

                default:
                    System.out.println("Please enter a valid choice number.");
                    break;
            }
        }while(choice<5 );

    }

    public void addUpdateDeletePromotion() {
        PromotionController PromotionController = new PromotionController();
        System.out.println("Add/Update/Delete a Promotion");
        System.out.println("---------------------");
        int choice=0;
        do{
            System.out.println("\nWhat do you wish to do:");
            System.out.println("1. Print All Promotion\n" + "2. Add Promotion\n" + "3. Update Existing Promotion\n"
                    + "4. Delete Promotion\n"+"5.Cancel");

            while(!sc.hasNextInt()){
                sc.next();
                System.out.println("Please enter valid option:");

            }
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    PromotionController.printPromotion();
                    break;
                case 2:
                    PromotionController.addPromotion();
                    break;
                case 3:
                    PromotionController.UpdatePromotion();
                    break;
                case 4:
                    PromotionController.DeletePromotion();
                    break;

                default:
                    System.out.println("Please enter a valid choice number.");
                    break;
            }
        }while(choice<5 );
    }



    OrderController OrderController = new OrderController();

    public void createOrder() {
        OrderController.createOrder();
    }

    public void viewOrder() {
        OrderController.viewOrder();
    }

    public void addDeleteOrderItem() {
        System.out.println("Add/Delete a OrderItem");
        System.out.println("---------------------");
        int choice=0;
        do{
            System.out.println("\nWhat do you wish to do:");
            System.out.println("1. Print All OrderItem\n" + "2. Add OrderItem\n"
                    + "3. Delete Promotion\n"+"4.Cancel");

            while(!sc.hasNextInt()){
                sc.next();
                System.out.println("Please enter valid option:");

            }
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    OrderController.allOrderItem();
                    break;
                case 2:
                    OrderController.addOrderItem();
                    break;
                case 3:
                    OrderController.deleteOrderItem();
                    break;
                default:
                    System.out.println("Please enter a valid choice number.");
                    break;
            }
        }while(choice<4 );
    }



    ReservationController ReservationController = new ReservationController();

    public void createReservation() {
        ReservationController.createReservation();
    }

    public void checkRemoveRervationBooking() {
        ReservationController.checkRemoveRervationBooking();
    }

    public void checkTableAvailaabitity() {
        TableController TableController = new TableController();
        TableController.checkTableAvailabitity();
    }

    public void printOrderInvoice() {
        OrderController.printOrderInvoice();
    }

    public void printSalesRevenueReport() {
        RevenueController RevenueController = new RevenueController();
        RevenueController.printSalesRevenueReport();
    }
}
