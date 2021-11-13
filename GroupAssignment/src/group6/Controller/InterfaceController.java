package Controller;

import Entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

import Entity.Staff;

public class InterfaceController {
    Staff CurrentStaff;
    static Timer timer;

    public InterfaceController() {
        // CurrentStaff=staff;
    }

    Scanner sc = new Scanner(System.in);

    public void addUpdateDeleteMenuItem() {
        MenuItemController MenuItemController = new MenuItemController();
        System.out.println("< Add/Update/Delete a MenuItem >\n");
        int choice = 0;
        do {
            try {
                System.out.println("\nWhat do you wish to do:");
                System.out.println("1. Print All MenuItem\n" + "2. Add MenuItem\n" + "3. Update Existing MenuItem\n"
                        + "4. Delete MenuItem\n" + "5. Cancel");
                choice = sc.nextInt();

                do {
                    switch (choice) {
                        case 1:
                            MenuItemController.print();
                            break;
                        case 2:
                            MenuItemController.add();
                            break;
                        case 3:
                            System.out.println("Please enter valid Item Name:");
                            Scanner sc = new Scanner(System.in);
                            String itemName = sc.nextLine();
                            MenuItemController.updateMenuItem(itemName);
                            break;
                        case 4:
                            MenuItemController.delete();
                            break;
                        case 5:
                            System.out.println("Returning to main menu...");
                            break;
                        default:
                            System.out.println("\nPlease enter a valid choice!");
                            System.out.println("\n-----------------------------------\n");
                    }
                } while (choice > 5);
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid choice!");
                System.out.println("\n-----------------------------------\n");
                choice = 0;
            }
            sc.nextLine(); //clears buffer
        } while (choice == 0);


    }

    public void addUpdateDeletePromotion() {
        PromotionController PromotionController = new PromotionController();
        System.out.println("< Add/Update/Delete a Promotion >\n");


        int choice = 0;
        do {
            try {
                System.out.println("\nWhat do you wish to do:");
                System.out.println("1. Print All Promotion\n" + "2. Add Promotion\n" + "3. Update Existing Promotion\n"
                        + "4. Delete Promotion\n" + "5. Cancel");
                choice = sc.nextInt();

                do {
                    switch (choice) {
                        case 1:
                            PromotionController.print();
                            break;
                        case 2:
                            PromotionController.add();
                            break;
                        case 3:
                            PromotionController.UpdatePromotion();
                            break;
                        case 4:
                            PromotionController.delete();
                            break;
                        case 5:
                            System.out.println("Returning to main menu...");
                            break;

                        default:
                            System.out.println("\nPlease enter a valid choice!");
                            System.out.println("\n-----------------------------------\n");

                    }
                } while (choice > 5);
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid choice!");
                System.out.println("\n-----------------------------------\n");
                choice = 0;
            }
            sc.nextLine(); //clears buffer
        } while (choice == 0);

    }

    OrderController OrderController = new OrderController();

    public void createOrder() {
        OrderController.createOrder();
    }

    public void viewOrder() {
        OrderController.PrintallOrderbrief();
    }

    public void addDeleteOrderItem() {
        OrderController.updateOrderById();
//        System.out.println("Add/Delete a OrderItem");
//        System.out.println("---------------------");
//        int choice=0;
//        do{
//            System.out.println("\nWhat do you wish to do:");
//            System.out.println("1. Print All OrderItem\n" + "2. Add OrderItem\n"
//                    + "3. Delete Promotion\n"+"4.Cancel");
//
//            while(!sc.hasNextInt()){
//                sc.next();
//                System.out.println("Please enter valid option:");
//
//            }
//            choice = sc.nextInt();
//            switch(choice){
//                case 1:
//                    OrderController.allOrder();
//                    break;
//                case 2:
//                    OrderController.createOrder();
//                    break;
//                case 3:
//                    OrderController.deleteOrder();
//                    break;
//                default:
//                    System.out.println("Please enter a valid choice number.");
//                    break;
//            }
//        }while(choice<4 );
    }

    ReservationController ReservationController = new ReservationController();

    public void createReservation() {
        ReservationController.createReservation();
    }

    public void checkRemoveReservationBooking() {
        Scanner sc = new Scanner(System.in);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                ReservationController.deleteOverdueBookings();
            }
        },0,1*60*1000);

        int choice = -1;
        do {
            try {
                System.out.println("Check/Remove Reservation Booking");
                System.out.println("------------------------------------");
                System.out.println("1. View all Reservations \n2. Check Reservation \n3. Remove Reservation \n0. Cancel");
                choice = sc.nextInt();
                while (choice != 0) {
                    switch (choice) {
                        case 1:
                            ReservationController.printReservationList();
                            break;
                        case 2:
                            System.out.println("Enter customer name: ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            ReservationController.checkReservationBooking(name);
                            break;
                        case 3:
                            ReservationController.printReservationList();
                            System.out.println("Enter reservation ID to be deleted: ");
                            int id = sc.nextInt();
                            ReservationController.deleteReservation(id);
                            break;
                        default:
                            System.out.println("\nPlease enter a valid choice!");
                            System.out.println("\n-----------------------------------\n");
                            break;
                    }
                    System.out.println("1. View all Reservations \n2. Check Reservation \n3. Remove Reservation \n0. Cancel");
                    choice = sc.nextInt();
                }

            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid choice: ");
                System.out.println("\n-----------------------------------\n");
                choice = -1;
            }
            sc.nextLine(); //clears buffer
        } while (choice == -1);

    }

    public void checkTableAvailability() {
        TableController TableController = new TableController();
        TableController.print();
    }

    public void printOrderInvoice() {
        OrderController.printOrderInvoice();
    }

    public void printSalesRevenueReport() {
        Scanner sc = new Scanner(System.in);
        RevenueController RevenueController = new RevenueController();
        //  RevenueController.printSalesRevenueReport();


        int choice = -1;
        do {
            try {
                System.out.println("Print report by: ");
                System.out.println("1. Date \n2. Month \n3. Year \n0. Cancel");
                choice = sc.nextInt();
                while (choice != 0) {
                    switch (choice) {
                        case 1:
                            do{
                            try {
                                System.out.println("Enter date [DD-MM-YYYY]: ");
                                String date = sc.nextLine();
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                RevenueController.getSalesReport(LocalDate.parse(date, df));
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Please input correct format!");
                            }
                            sc.nextLine();
                    } while(true);
                            break;
                        case 2:
                            int month;
                            do {
                                System.out.println("Enter month (1 to 12): ");
                                month = sc.nextInt();
                                if(month < 1 || month > 12)
                                    System.out.println("Please enter valid month!");
                            } while(month < 1 || month > 12);
                            int year2;
                            do {
                                System.out.println("Enter year (2018 - 2021): ");
                                year2 = sc.nextInt();
                                if(year2 < 2018 || year2 > 2021)
                                    System.out.println("Please enter valid year!");
                            } while (year2 < 2018 || year2 > 2021);
                            RevenueController.getSalesReport(month, year2);
                            break;
                        case 3:
                            int year3;
                            do {
                                System.out.println("Enter year (2018 to 2021): ");
                                year3 = sc.nextInt();
                                if(year3 < 2018 || year3 > 2021)
                                    System.out.println("Please enter valid year!");
                            } while (year3 < 2018 || year3 > 2021);
                            RevenueController.getSalesReport(year3);
                            break;
                        default:
                            System.out.println("\nPlease enter a valid choice! ");
                            System.out.println("\n-----------------------------------\n");
                            break;
                    }
                    System.out.println("Print report by: ");
                    System.out.println("1. Date \n2. Month \n3. Year \n0. Cancel");
                    choice = sc.nextInt();
                }

            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid choice: ");
                System.out.println("\n-----------------------------------\n");
                choice = -1;
            }
            sc.nextLine(); //clears buffer
        } while (choice == -1);

    }
}
