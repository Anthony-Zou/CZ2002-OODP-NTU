package Controller;
import Entity.*;

import java.time.LocalDate;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Entity.Staff;

public class InterfaceController {
    Staff CurrentStaff;
    public InterfaceController(Staff staff) {
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
                    String itemName=sc.next();
                    MenuItemController.updateMenuItem(itemName);
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
        OrderController.allOrder();
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
                    OrderController.allOrder();
                    break;
                case 2:
                    OrderController.createOrder();
                    break;
                case 3:
                    OrderController.deleteOrder();
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

    public void checkRemoveReservationBooking() {
        ReservationController.checkRemoveReservationBooking();
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
        //  RevenueController.printSalesRevenueReport();

          System.out.println("Print report by: ");
          System.out.println("1. Date \n2.Month \n 3.Year \n0.Cancel Operation");
          int choice = sc.nextInt();
          while(choice != 0) {
              switch (choice) { // need to add exceptions
                  case 1:
                      System.out.println("Enter date YYYY-MM-DD: ");
                      String date = sc.next();
                      RevenueController.getSalesReportOfDate(LocalDate.parse(date));
                      break;
                  case 2:
                      System.out.println("Enter month (1 to 12): ");
                      int month = sc.nextInt();
                      System.out.println("Enter year: ");
                      int year2 = sc.nextInt();
                      RevenueController.getSalesReportOfMonth(month, year2);
                      break;
                  case 3:
                      System.out.println("Enter year: ");
                      int year3 = sc.nextInt();
                      RevenueController.getSalesReportOfYear(year3);
                      break;
                  default:
                      System.out.println("Please enter a valid choice: ");
                      choice = sc.nextInt();
                      break;
              }
          }
      }
  }
