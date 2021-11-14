package Controller;

import Entity.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 Controller for OrderController create, convert and create from reservation, view all orders, view all unpaid orders,
 print Order By Id,Print Invoice and populate orders
 @author Zou Zeren
 @version 1.0
 @since 24-22-2021
 */
public class OrderController {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * createOrder Method:
     * The createOrder Method will create a Order object with generating Order Id, date and time
     * and  requesting  staffId, membership, userContact, alacarteList,
     * promotionList, totalPrice, tableId, paid from user
     * Furthermore, the Order object will be written and save in to the Order.Dat file
     * With AddOrder method in the Database controller
     */
    public void createOrder() {
        System.out.println("< Create an Order >\n");

        TableController TableController = new TableController();
        // If all tables are fully reserved, exit this operation
        if(!TableController.printAvailableTables(2))
            return;

        System.out.println("Generating Order Id...");
        System.out.println("---------------------");
        //region order ID
        int orderId = 1;
        ArrayList<Order> presentOrders = Database_Controller.readOrderList();
        if (presentOrders != null) {
            orderId = presentOrders.size() + 1;
        }
        //endregion

        //region Staff ID
        StaffController StaffController = new StaffController();
        StaffController.print();

        // 1. Input Staff ID
        int staffId = 0;
        do {
            try {
                while (true) {
                	System.out.println("Enter Your Staff Id: ");
                    staffId = sc.nextInt();
                    if (Database_Controller.getStaffByEmployeeId(staffId) != null) {
                        break;
                    } else {
                		System.out.println("Please enter a valid Staff ID!");
                		System.out.println("\n-----------------------------------\n");
                    }
                }
            } catch (InputMismatchException e) {
            		System.out.println("Please enter a valid  Staff ID!");
            		System.out.println("\n-----------------------------------\n");
            		staffId = 0;
            	}
            	sc.nextLine();
            } while(staffId == 0);
        
        // 2. Input number of pax
        System.out.println("< Assign Available Table >\n");
        
        int pax = 0;
        do {
        	try {
        		while(true) {
        			System.out.println("Enter pax:");
        			pax = sc.nextInt();
        			if (pax < 2) {
        				System.out.println("\nMinimum capacity is 2!");
        			} else if(pax> 10) {
                        System.out.println("Maximum capacity is 10, please break into smaller groups.");
                    }else break;
        		}
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid number!");
        		System.out.println("\n-----------------------------------\n");
        		pax = 0;
        	}
        	sc.nextLine();
        } while(pax == 0);
        
         TableController = new TableController();
        if(!TableController.printAvailableTables(pax)){
            return;
        }
        
        // 3. Input table ID
        int tableId = 0;
        do {
        	try {
        		while (true) {
        			System.out.println("Enter Table Id of choices:");
                    tableId = sc.nextInt();
                    if (Database_Controller.getTableById(tableId) != null
                            && Database_Controller.getTableById(tableId).isReserved() == false
                                && Database_Controller.getTableById(tableId).getCapacity()>=pax) {
                        break;
                    } else {
                    	System.out.println("Table " + tableId + " unavailable! Please choose another table.");
                    }
                }
        	} catch (InputMismatchException e) {
        		System.out.println("\nPlease enter a valid Table ID!");
        		System.out.println("\n-----------------------------------\n");
        		tableId = 0;
        	}
        	sc.nextLine();
        } while(tableId == 0);    
        
        
        // 4. member / non-member choice
        boolean membership = false;
        int choice  = -1;
        do {
        	try {
        		while(true) {
        			System.out.println("Is Customer a member?");
                    System.out.println("0. No");
                    System.out.println("1. Yes");
                    System.out.println("---------------------");
                    choice = sc.nextInt();
                    if(choice == 0 || choice == 1) break;
                    else {
                    	System.out.println("\nPlease enter a valid choice!");
                		System.out.println("\n-----------------------------------\n");
                    }
        		}
        		
        	} catch (InputMismatchException e) {
        		System.out.println("\nPlease enter a valid choice!");
        		System.out.println("\n-----------------------------------\n");
        		choice = -1;
        	}
        	sc.nextLine();
        } while(choice == -1);
        
        // 4-1 if user chooses member, get contact number to verify membership
        int userContact = 0;
        if (choice == 1) {
            System.out.println("< Verify Membership >\n");
            System.out.println("Enter Customer Contact: ");
            
            do {
            	try {
            		System.out.println("Contact number: ");
            		userContact = sc.nextInt();
            		while(userContact < 10000000 || userContact > 99999999) {
                    	System.out.println("\nPlease enter a valid number!");
                		System.out.println("\n-----------------------------------\n");
                		System.out.println("Contact number: ");
                		userContact = sc.nextInt();
                    }
            		
            	} catch (InputMismatchException e) {
            		System.out.println("\nPlease enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            		userContact = 0;
            	}
            	sc.nextLine(); //clears buffer
            } while(userContact == 0);
            
            if (Database_Controller.getCustomerByContact(userContact) != null) {
                membership = Database_Controller.getCustomerByContact(userContact).isMemberShip();
                String membershipName = Database_Controller.getCustomerByContact(userContact).getName();
                if(membership){
                    System.out.println("Membership verified. Welcome Dear "+membershipName+".");
                }else{
                    System.out.println("Membership not found.");
                }
            }else{
                System.out.println("Membership not found.");
            }
        } else if (choice == 0) {
            membership = false;
        }
        
        // 5. Input order item
        System.out.println("< Add Order Item >\n");
        MenuItemController MenuItemController = new MenuItemController();
        PromotionController PromotionController = new PromotionController();
        ArrayList<MenuItem> alacarteList = new ArrayList<MenuItem>();
        ArrayList<Promotion> promotionList = new ArrayList<Promotion>();

        choice = -1;
        boolean checkOrder = false;
        do {
        	try {
                do {
                    System.out.println("Choose Item Type");
                    System.out.println("1. Alacarte");
                    System.out.println("2. Promotion");
                    System.out.println("0. Done");
                    System.out.println("---------------------");
                    choice = sc.nextInt();
                	switch (choice) {
                        case 0:
                            if (checkOrder) {
                                System.out.println("Returning to main menu...");
                            } else {
                                System.out.println("Please run option 1 or 2 at least once.");
                            }
                            break;
                        case 1:
                            MenuItem MenuItem = new MenuItem();
                            do {
                                System.out.println("< Alacarte Item >\n");
                                MenuItemController.print();
                                System.out.println("Enter the name of the alacarte item: ");
                                Scanner sc = new Scanner(System.in);
                                String itemname = sc.nextLine();
                                MenuItem = Database_Controller.getMenuItemByName(itemname);
                                if(MenuItem==null){
                                    System.out.println("Item not on menu! Please try again.");
                                }
                            }while(MenuItem==null);
                            alacarteList.add(MenuItem);
                            checkOrder = true;
                            break;
                        case 2:
                            Promotion Promotion = new Promotion();
                            do {

                                    System.out.println("< Enter Promotion Set Item Name >\n");
                                    PromotionController.print();
                                    System.out.println("Enter the name of the Promotion item: ");
                                    String promotionName;
                                sc = new Scanner(System.in);

                                    promotionName = sc.nextLine();

                                    Promotion = Database_Controller.getPromotionByName(promotionName);
                                    if(Promotion == null){
                                        System.out.println("promotion not on menu! Please try again.");
                                    }

                            }while(Promotion == null);
                            promotionList.add(Promotion);
                            checkOrder = true;
                            break;
                        default:
                            System.out.println("Please enter a valid choice!");
                            System.out.println("\n-----------------------------------\n");

                	}
                		
                } while (choice !=0 || checkOrder !=true);
                
                
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid choice!");
        		System.out.println("\n-----------------------------------\n");
        		choice = -1;
        	}
        	sc.nextLine();
        } while(choice == -1);
        
        


        double totalPrice = 0;
        if (alacarteList != null) {
            for (int i = 0; i < alacarteList.size(); i++) {
                totalPrice += alacarteList.get(i).getPrice();
            }
        }
        if (promotionList != null) {
            for (int i = 0; i < promotionList.size(); i++) {
                totalPrice += promotionList.get(i).getPrice();
            }
        }
        if (membership == true) {
            totalPrice *= 0.9;
        }

        // Service Charge 10%
        totalPrice *= 1.10;

        // GST 7%
        totalPrice *=1.07;

        boolean paid = false;

        Table table = Database_Controller.getTableById(tableId);
        table.setReserved(true);
        Database_Controller.updateTable(table);

        LocalDate Date = LocalDate.now();
        LocalTime Time = LocalTime.now();

        Order Order = new Order(orderId, staffId, membership, userContact, alacarteList, promotionList, totalPrice, tableId, paid, Date, Time);
        Database_Controller.addOrder(Order);
    }

    /**
     * Convert Reservation to an Order with keeping the sam content in the reservation object passed in with
     * reservation object ,addtional information that are required in the order are requested from user
     * to input into the variables.
     * @param reservation
     */
    public void convertResToOrder(Reservation reservation) {
        System.out.println("< Create an Order >\n");
        System.out.println("Generating Order Id...");
        System.out.println("---------------------");
        //region order ID
        int orderId = 1;
        ArrayList<Order> presentOrders = Database_Controller.readOrderList();
        if (presentOrders != null) {
            orderId = presentOrders.size() + 1;
        }
        //endregion

        //region Staff ID
        StaffController StaffController = new StaffController();
        StaffController.print();

        // 1. Input Staff ID
        int staffId = 0;
        do {
            try {
                while (true) {
                    System.out.println("Enter Your Staff Id: ");
                    staffId = sc.nextInt();
                    if (Database_Controller.getStaffByEmployeeId(staffId) != null) {
                        break;
                    } else {
                        System.out.println("Please enter a valid Staff ID!");
                        System.out.println("\n-----------------------------------\n");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid Staff ID!");
                System.out.println("\n-----------------------------------\n");
                staffId = 0;
            }
            sc.nextLine();
        } while(staffId == 0);

        // 2. Input number of pax
        System.out.println("< Assign Available Table >\n");




        // 3. Input table ID
        int tableId =reservation.getTableId();




        boolean membership=Database_Controller.getCustomerByName(reservation.getCustomerName()).isMemberShip();
         int userContact=Database_Controller.getCustomerByName(reservation.getCustomerName()).getContact();

        // 5. Input order item
        System.out.println("< Add Order Item >\n");
        MenuItemController MenuItemController = new MenuItemController();
        PromotionController PromotionController = new PromotionController();
        ArrayList<MenuItem> alacarteList = new ArrayList<MenuItem>();
        ArrayList<Promotion> promotionList = new ArrayList<Promotion>();

        int choice = -1;
        boolean checkOrder = false;
        do {
            try {
                do {
                    System.out.println("Choose Item Type");
                    System.out.println("1. Alacarte");
                    System.out.println("2. Promotion");
                    System.out.println("0. Done");
                    System.out.println("---------------------");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 0:
                            if (checkOrder) {
                                System.out.println("Returning to main menu...");
                            } else {
                                System.out.println("Please run option 1 or 2 at least once.");
                            }
                            break;
                        case 1:
                            MenuItem MenuItem = new MenuItem();
                            do {
                                System.out.println("< Alacarte Item >\n");
                                MenuItemController.print();
                                System.out.println("Enter the name of the alacarte item: ");
                                Scanner sc = new Scanner(System.in);
                                String itemname = sc.nextLine();
                                MenuItem = Database_Controller.getMenuItemByName(itemname);
                                if(MenuItem==null){
                                    System.out.println("Item not on menu! Please try again.");
                                }
                            }while(MenuItem==null);
                            alacarteList.add(MenuItem);
                            checkOrder = true;
                            break;
                        case 2:
                            Promotion Promotion = new Promotion();
                            do {

                                System.out.println("< Enter Promotion Set Item Name >\n");
                                PromotionController.print();
                                System.out.println("Enter the name of the Promotion item: ");
                                String promotionName;
                                Scanner sb = new Scanner(System.in);
                                promotionName = sb.nextLine();

                                Promotion = Database_Controller.getPromotionByName(promotionName);
                                if(Promotion == null){
                                    System.out.println("promotion not on menu! Please try again.");
                                }

                            }while(Promotion == null);
                            promotionList.add(Promotion);
                            checkOrder = true;
                            break;
                        default:
                            System.out.println("Please enter a valid choice!");
                            System.out.println("\n-----------------------------------\n");

                    }

                } while (choice !=0 || checkOrder !=true);


            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice!");
                System.out.println("\n-----------------------------------\n");
                choice = -1;
            }
            sc.nextLine();
        } while(choice == -1);




        double totalPrice = 0;
        if (alacarteList != null) {
            for (int i = 0; i < alacarteList.size(); i++) {
                totalPrice += alacarteList.get(i).getPrice();
            }
        }
        if (promotionList != null) {
            for (int i = 0; i < promotionList.size(); i++) {
                totalPrice += promotionList.get(i).getPrice();
            }
        }
        if (membership == true) {
            totalPrice *= 0.9;
        }

        totalPrice *= 1.10;
        totalPrice *=1.07;

        boolean paid = false;

        Table table = Database_Controller.getTableById(tableId);
        table.setReserved(true);
        Database_Controller.updateTable(table);

        LocalDate Date = LocalDate.now();
        LocalTime Time = LocalTime.now();

        Order Order = new Order(orderId, staffId, membership, userContact, alacarteList, promotionList, totalPrice, tableId, paid, Date, Time);
        Database_Controller.addOrder(Order);
    }
    /**
     * deleteOrder method:
     * The deleteOrder Method will request user to input the Order Id
     * to be deleted. It will first check the existance of the Order with the
     * getOrderById method from the Database Controller and if the object exist,
     * it will activate the deleteOrder method from the Database Controller with
     * passing in the Order Id
     */
    public void deleteOrder() {
        System.out.println("< Remove an Order >\n");
        // find if the Reservation is in the database or not //
        int Number = 0;
        do {
        	try {
        		System.out.println("Enter the number of the Order:");
        		Number = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid number!");
        		System.out.println("\n-----------------------------------\n");
        		Number = 0;
        	}
        	sc.nextLine();
        } while(Number == 0);
        
        if (Database_Controller.getOrderById(Number) == null) {
            System.out.println("Order does not exist!");

        } else {
            Table table = Database_Controller.getTableById(Database_Controller.getOrderById(Number).getTableNum());
            table.setReserved(false);
            Database_Controller.updateTable(table);
            Database_Controller.deleteOrder(Number);// remove the Reservation from the database
            System.out.println("Order removed!");
        }
    }

    /**
     * allOrder Method:
     * The allOrder Method creates an arraylist of Order objects with values
     * retrieved from Orders.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Order will be printed out with a for loop
     * Furthermore, there are two array list to be printed
     * They are the alacarte list and promotion list that are stored in the order
     */
    public void allOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {

            for (int i = 0; i < Order.size(); i++) {
                System.out.println("orderId   staffId   membership   userContact   totalPrice   tableId   paid      Date             Time");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
//                System.out.println(Order.get(i).getOrderId() + "\t\t" + Order.get(i).getStaffId() +
//                        "\t\t" + Order.get(i).isMembership() + "\t\t\t"
//                        + Order.get(i).getUserContact() + "\t\t" + String.format("%.2f", Order.get(i).getTotalPrice()) + "\t\t\t" + Order.get(i).getTableNum() + "\t\t" + Order.get(i).isPaid() + "\t\t\t\t" + Order.get(i).getDate()
//                        + "\t\t\t\t" + Order.get(i).getTime() + "\t\t\n");

                System.out.printf("%-10d%-10d%-13b%-13d%-13.2f%-10d%-10b%-17s%s\n", Order.get(i).getOrderId(), Order.get(i).getStaffId()
                        , Order.get(i).isMembership(), Order.get(i).getUserContact(), Order.get(i).getTotalPrice(), Order.get(i).getTableNum()
                        , Order.get(i).isPaid(), Order.get(i).getDate(), Order.get(i).getTime());
                //Print Alacarte Item in the order
                if (Order.get(i).getAlacarte() != null) {
                System.out.println("< Alacarte Item >");
                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                for (int j = 0; j < Order.get(i).getAlacarte().size(); j++) {
                    System.out.println(Order.get(i).getAlacarte().get(j).getItemName()
                            + "\t\t " + Order.get(i).getAlacarte().get(j).getPrice());
                }
                System.out.println();}

                //Print Promotion Item in the order
                if (Order.get(i).getPromotion() != null) {
                System.out.println("< Promotion Item >");
                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                for (int j = 0; j < Order.get(i).getPromotion().size(); j++) {
                    System.out.println(
                            Order.get(i).getPromotion().get(j).getName()
                                    + "\t\t " + Order.get(i).getPromotion().get(j).getPrice());
                }
                System.out.println("\n\n");}
            }
        }
    }

    /**
     * viewUnpaidOrder Method:
     * This wll print all orders that have not been paid
     * The allOrder Method creates an arraylist of Order objects with values
     * retrieved from Orders.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Order will be printed out with a for loop
     * Furthermore, there are two array list to be printed
     * They are the alacarte list and promotion list that are stored in the order
     */
    public void viewUnpaidOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            System.out.println("orderId   staffId   membership   userContact   totalPrice   tableId   paid      Date                Time");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < Order.size(); i++) {
                if (Order.get(i).isPaid() == false) {

                    System.out.printf("%-10d%-10d%-13b%-14d%-13.2f%-10d%-10s%-20s%s\n", Order.get(i).getOrderId(), Order.get(i).getStaffId()
                            ,Order.get(i).isMembership(), Order.get(i).getUserContact(), Order.get(i).getTotalPrice(),
                            Order.get(i).getTableNum(), Order.get(i).isPaid(), Order.get(i).getDate(),Order.get(i).getTime());

                    //Print Alacarte Item in the order
                    System.out.println("Alacarte Items");
                    System.out.printf("%-15s%-15s\n", "Item Name", "Price(SGD)");
                    System.out.println("---------------------------");

                    for (int j = 0; j < Order.get(i).getAlacarte().size(); j++) {
                        System.out.printf("%-15s%-15.2f\n", Order.get(i).getAlacarte().get(j).getItemName(), Order.get(i).getAlacarte().get(j).getPrice());
                    }
                    //Print Promotion Item in the order
                    System.out.println("Promotion Items");
                    System.out.printf("%-15s%-15s\n", "Promotion Name", "Price(SGD)");
                    System.out.println("---------------------------");
                    for (int j = 0; j < Order.get(i).getPromotion().size(); j++) {
                        System.out.printf("%-15s%-15.2f\n", Order.get(i).getPromotion().get(j).getName(), Order.get(i).getPromotion().get(j).getPrice());
                    }

                }
            }
        }
    }

    /**
     * viewpaidOrder Method:
     * This wll print all orders that are paid
     * The allOrder Method creates an arraylist of Order objects with values
     * retrieved from Orders.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Order will be printed out with a for loop
     * Furthermore, there are two array list to be printed
     * They are the alacarte list and promotion list that are stored in the order
     */
    public void viewpaidOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            System.out.println("orderId   staffId   membership   userContact   totalPrice   tableId   paid      Date                Time");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < Order.size(); i++) {
                System.out.printf("%-10d%-10d%-13b%-14d%-13.2f%-10d%-10s%-20s%s\n", Order.get(i).getOrderId(), Order.get(i).getStaffId()
                        ,Order.get(i).isMembership(), Order.get(i).getUserContact(), Order.get(i).getTotalPrice(),
                        Order.get(i).getTableNum(), Order.get(i).isPaid(), Order.get(i).getDate(),Order.get(i).getTime());

                    //Print Alacarte Item in the order
                    System.out.println("Alacarte Item");
                    System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                    for (int j = 0; j < Order.get(i).getAlacarte().size(); j++) {
                        System.out.println("\t" + Order.get(i).getAlacarte().get(j).getItemName()
                                + "\t" + Order.get(i).getAlacarte().get(j).getPrice());
                    }
                    //Print Promotion Item in the order
                    System.out.println("Promotion Item");
                    System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                    for (int j = 0; j < Order.get(i).getPromotion().size(); j++) {
                        System.out.println(
                                "\t" + Order.get(i).getPromotion().get(j).getName()
                                        + "\t" + Order.get(i).getPromotion().get(j).getPrice());
                    }

                }
            }
        }


    /**
     * printOrderInvoice Method:
     * If the order is Unpaid, This method will allow users to make payment for the order
     * While the the enter order id is valid, the method will first update the order payment status
     * to be true, then it will release the vacancy of the table. Next, the Order invoice will be printed
     */
    public void printOrderInvoice() {

        PrintallOrderbrief();
        System.out.println("Enter Order Id ");
        int Number = sc.nextInt();
        int c = 0;
        Order order = Database_Controller.getOrderById(Number);
        while (order == null || order.isPaid()) {
            System.out.println("orderId does not exist! Please enter a valid orderId:");
            Number = sc.nextInt();
            order = Database_Controller.getOrderById(Number);
            c++;
            if (c == 3)
                return;
        }
            //update order paid status to be true to mark
            Table table = Database_Controller.getTableById(Database_Controller.getOrderById(Number).getTableNum());
            table.setReserved(false);
            Database_Controller.updateTable(table);
            System.out.println("Table released!");

            //release Table
            Order Order = Database_Controller.getOrderById(Number);
            Order.setPaid(true);
            Database_Controller.updateOrder(Order);
            System.out.println("Order Paid! Printing Invoice");
            System.out.println("=======================================");
            //region print invoice

            System.out.println("\t\t\t Check #: " + Order.getOrderId()+ "\n");
            System.out.println(" Server: " + Order.getStaffId() + "\t\t  " + "Date: " + Order.getDate());
            System.out.println(" Table: " + Order.getTableNum() + "\t\t      " + "Time: " + Order.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

            System.out.println("---------------------------------------");


            //Print Alacarte Item in the order

            Map<String, Double> dictionary1 = new HashMap<String, Double>();
            Map<String, Integer> dictionary2 = new HashMap<String, Integer>();
            for (int j = 0; j < Order.getAlacarte().size(); j++) {

                String name = Order.getAlacarte().get(j).getItemName();
                double price = Order.getAlacarte().get(j).getPrice();
                double prevPrice;
                int prevQty;
                if (dictionary1.get(name) == null) {
                    dictionary1.put(name, price);
                    dictionary2.put(name, 1);
                } else {
                    prevPrice = dictionary1.get(name);
                    prevQty = dictionary2.get(name);
                    dictionary1.replace(name, prevPrice += price);
                    dictionary2.replace(name, prevQty += 1);
                }
            }

            for (Map.Entry<String, Double> entry: dictionary1.entrySet()){
                System.out.printf("%-4d%-22s%.2f\n", dictionary2.get(entry.getKey()), entry.getKey(), entry.getValue());
            }

            //Print Promotion Item in the order

            Map<String, Double> dictionary3 = new HashMap<String, Double>();
            Map<String, Integer> dictionary4 = new HashMap<String, Integer>();

            for (int j = 0; j < Order.getPromotion().size(); j++) {

                String name = Order.getPromotion().get(j).getName();
                double price = Order.getPromotion().get(j).getPrice();
                double prevPrice;
                int prevQty;
                if (dictionary3.get(name) == null) {
                    dictionary3.put(name, price);
                    dictionary4.put(name, 1);
                } else {
                    prevPrice = dictionary3.get(name);
                    prevQty = dictionary4.get(name);
                    dictionary3.replace(name, prevPrice += price);
                    dictionary4.replace(name, prevQty += 1);
                }
            }

            for (Map.Entry<String, Double> entry: dictionary3.entrySet()){
                System.out.printf("  %-4d%-22s%.2f\n", dictionary4.get(entry.getKey()), entry.getKey(), entry.getValue());
            }

            System.out.println("---------------------------------------");
//            System.out.printf("Subtotal:\t%.2f\nGST:\t%.2f\nTotal:\t%.2f", );
            System.out.printf("%25s\t%.2f\n", "Total:", Order.getTotalPrice());
            //endregion

    }

    /**
     * printOrderById Method:
     * This will print the order detail specific to the input order Id
     * @param orderId
     */
    public void printOrderById(int orderId) {
        if (Database_Controller.getOrderById(orderId) == null) {
            System.out.println("orderId does not exist!");
        } else {
            Order Order = Database_Controller.getOrderById(orderId);
            System.out.println("orderId   staffId   membership   userContact   totalPrice   tableId   paid      Date                Time");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10d%-10d%-13b%-14d%-13.2f%-10d%-10s%-20s%s\n", Order.getOrderId(), Order.getStaffId(), Order.isMembership(), Order.getUserContact(), Order.getTotalPrice(), Order.getTableNum(), Order.isPaid(), Order.getDate(), Order.getTime());

            //Print Alacarte Item in the order
            System.out.println("Alacarte Items");
            System.out.printf("%-15s%-15s\n", "Item Name", "Price(SGD)");
            System.out.println("---------------------------");

            for (int j = 0; j < Order.getAlacarte().size(); j++) {
                System.out.printf("%-15s%-15.2f\n", Order.getAlacarte().get(j).getItemName(), Order.getAlacarte().get(j).getPrice());
            }
            //Print Promotion Item in the order
            System.out.println("Promotion Items");
            System.out.printf("%-15s%-15s\n", "Promotion Name", "Price(SGD)");
            System.out.println("---------------------------");
            for (int j = 0; j < Order.getPromotion().size(); j++) {
                System.out.printf("%-15s%-15.2f\n", Order.getPromotion().get(j).getName(), Order.getPromotion().get(j).getPrice());
            }
        }
    }

    /**
     * updateOrderById Method:
     * Add/Remove order item/s to/from order
     * If the order is still unpaid, Method allow user to input valid order id and choose option
     * to add or remove alacarte/promotion items in the current order
     */
    public void updateOrderById() {

        this.viewUnpaidOrder();
        
        // Input order id to update
        int orderId = -1;
        do {
        	try {
        		System.out.println("Enter Order Id to be Updated: ");
                orderId = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("\nPlease enter a valid Order ID!");
        		System.out.println("\n-----------------------------------\n");
        		orderId = -1;
        	}
        	sc.nextLine();
        } while(orderId == -1);
        
        if (Database_Controller.getOrderById(orderId) == null ||
                Database_Controller.getOrderById(orderId).isPaid()) {
            System.out.println("orderId does not exist!");
        } else {
            this.printOrderById(orderId);
            Order order = Database_Controller.getOrderById(orderId);
            MenuItemController MenuItemController = new MenuItemController();
            PromotionController PromotionController = new PromotionController();
            ArrayList<MenuItem> alacarteList = order.getAlacarte();
            ArrayList<Promotion> promotionList = order.getPromotion();
            
            

            System.out.println("< Add/Remove an Order Item >\n");
            int choice = -1;
            do {
            	try {
                    System.out.println("Enter 1. Add an Order Item");
                    System.out.println("Enter 2. Remove an Order Item");
                    System.out.println("Enter 0. Complete");
                    System.out.println("---------------------");
                    choice = sc.nextInt();
                    do {
                    	switch (choice) {
                        case 1:
                            System.out.println("< Add an Order Item >\n");
                            
                            choice = -1;
                            do {
                            	try {
                            		System.out.println("Choose Item Type: ");
                                    System.out.println("1. Alacarte");
                                    System.out.println("2. Promotion");
                                    System.out.println("0. Done");
                                    System.out.println("---------------------");
                            		choice = sc.nextInt();
                            		do {
                                    	switch (choice) {
                                    	case 0:
                                    		System.out.println("Done adding!");
                                    		break;
                                        case 1:
                                            MenuItemController.print();
                                            System.out.println("\nEnter Alacarte Item: ");
                                            sc = new Scanner(System.in);
                                            String itemname = sc.nextLine();
                                            
                                            while(true) {
                                            	if(Database_Controller.getMenuItemByName(itemname)!=null){
                                                	MenuItem MenuItem = Database_Controller.getMenuItemByName(itemname);
                                                	alacarteList.add(MenuItem);
                                                	order.setAlacarte(alacarteList);
                                                	System.out.println("Menu added!");
                                                	break;
                                                } else {
                                                    System.out.println("Please enter a vaild item name!");
                                                    itemname= sc.nextLine();
                                                }
                                            }
                                            break;
                                        case 2:
                                            PromotionController.print();

                                            String promotionName="0";
                                            do {
                                            	try {
                                            		while(true) {
                                            			System.out.println("\nEnter Promotion Set Item Name: ");
                                                        sc = new Scanner(System.in);
                                            			promotionName = sc.nextLine();
                                            			if(Database_Controller.getPromotionByName(promotionName)!=null){
                                            				Promotion Promotion = Database_Controller.getPromotionByName(promotionName);
                                            				promotionList.add(Promotion);
                                            				order.setPromotion(promotionList);
                                            				System.out.println("Menu added!");
                                            				break;
                                                        } else {
                                                        	System.out.println("\n There is no"+promotionName+"Please enterPromotion Set Item Name");
                                                    		System.out.println("\n-----------------------------------\n");
                                                        }
                                            		}
                                            		
                                            	} catch (InputMismatchException e) {
                                            		System.out.println("\nPlease enter a valid Promotion ID!");
                                            		System.out.println("\n-----------------------------------\n");
                                            		promotionName = "0";
                                            	}
                                            	sc.nextLine();
                                            } while(promotionName == "0");

                                            break;
                                        default:
                                        	System.out.println("\nPlease enter a valid choice!");
                                    		System.out.println("\n-----------------------------------\n");
                                        	System.out.println("Choose Item Type");
                                            System.out.println("1. Alacarte");
                                            System.out.println("2. Promotion");
                                            System.out.println("0. Done");
                                            System.out.println("-----------------------------------");
                                            choice = sc.nextInt();

                                    	}
                                    } while (choice != 1 && choice != 2 && choice != 0);
                            	} catch (InputMismatchException e) {
                            		System.out.println("\nPlease enter a valid choice!");
                            		System.out.println("\n-----------------------------------\n");
                            		choice = -1;
                            	}
                            	sc.nextLine();
                            } while(choice == -1); 
                            break;
                            
                        case 2:
                            System.out.println("< Remove an Order Item >\n");
                            
                            System.out.println("Choose Item Type to remove");
                            System.out.println("1. Alacarte");
                            System.out.println("2. Promotion");
                            System.out.println("0. Done");
                            System.out.println("-----------------------------------");
                            choice = -1;
                            do {
                            	try {
                            		choice = sc.nextInt();
                            		do {
                            			switch (choice) {
                            			case 0:
                                    		System.out.println("Done removing!");
                                    		break;
                                        case 1:
                                            if (alacarteList.size() < 1) {
                                                System.out.println("There is no item here!");
                                                break;
                                            } else {
                                                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t" + " Index" + "\t");
                                                for (int j = 0; j < alacarteList.size(); j++) {
                                                    System.out.println(j + "\t\t\t" + alacarteList.get(j).getItemName()
                                                            + "\t" + alacarteList.get(j).getPrice() + "\t");
                                                }
                                                System.out.println("-----------------------------------");

                                                int alacarteindex;
                                                do {
                                                	try {
                                                		while(true) {
                                                			System.out.println("Enter the index of the Alacarte Item to remove: ");
                                                			alacarteindex = sc.nextInt();
                                                			if(alacarteindex > alacarteList.size()-1) {
                                                				System.out.println("\nPlease enter a valid index!");
                                                        		System.out.println("\n-----------------------------------\n");
                                                			}
                                                			else break;
                                                		}
                                                		
                                                	} catch (InputMismatchException e) {
                                                		System.out.println("\nPlease enter a valid index!");
                                                		System.out.println("\n-----------------------------------\n");
                                                		alacarteindex = -1;
                                                	}
                                                	sc.nextLine();
                                                } while(alacarteindex == -1);
                                                
                                                alacarteList.remove(alacarteindex);
                                                order.setAlacarte(alacarteList);
                                                System.out.println("Item removed!");
                                            }
                                            break;
                                        case 2:
                                            if (promotionList.size() < 1) {
                                                System.out.println("There is no item here!");
                                                break;
                                            } else {
                                                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t" + " Index" + "\t");
                                                for (int j = 0; j < promotionList.size(); j++) {
                                                    System.out.println(j + "\t\t\t" + promotionList.get(j).getDecription()
                                                            + "\t" + promotionList.get(j).getPrice() + "\t");
                                                }
                                                System.out.println("-----------------------------------");

                                                
                                                int promotionindex = -1;
                                                do {
                                                	try {
                                                		while(true) {
                                                			System.out.println("Enter the index of the Promotion Item to remove: ");
                                                			promotionindex = sc.nextInt();
                                                			if(promotionindex > promotionList.size()-1) {
                                                				System.out.println("\nPlease enter a valid index!");
                                                        		System.out.println("\n-----------------------------------\n");
                                                			}
                                                			else break;
                                                		}
                                                	} catch (InputMismatchException e) {
                                                		System.out.println("\nPlease enter a valid index!");
                                                		System.out.println("\n-----------------------------------\n");
                                                		promotionindex = -1;
                                                	}
                                                	sc.nextLine();
                                                } while(promotionindex == -1);
                                                
                                                promotionList.remove(promotionindex);
                                                order.setPromotion(promotionList);
                                                System.out.println("Item removed!");
                                            }
                                            break;
                                        default:
                                        	System.out.println("\nPlease enter a valid choice!");
                                    		System.out.println("\n-----------------------------------\n");
                                            
                                            System.out.println("Choose Item Type to remove");
                                            System.out.println("1. Alacarte");
                                            System.out.println("2. Promotion");
                                            System.out.println("0. Done");
                                            System.out.println("-----------------------------------");
                                            choice = sc.nextInt();
                                    	}
                            		} while(choice != 1 && choice != 2 && choice != 0);
                            	}catch (InputMismatchException e) {
                            		System.out.println("\nPlease enter a valid choice!");
                            		System.out.println("\n-----------------------------------\n");
                            		choice = -1;
                            	}
                            	sc.nextLine();
                            } while(choice == -1);
                                
                            break;
                            
                        default:
                        	System.out.println("\nPlease enter a valid choice!");
                    		System.out.println("\n-----------------------------------\n");
                            System.out.println("Enter 1. Add an Order Item");
                            System.out.println("Enter 2. Remove an Order Item");
                            System.out.println("Enter 0. Complete");
                            System.out.println("-----------------------------------");
                            choice = sc.nextInt();
                    	}
                    } while(choice != 1 && choice != 2 && choice != 0);
                    
            	} catch (InputMismatchException e) {
            		System.out.println("\nPlease enter a valid choice!");
            		System.out.println("\n-----------------------------------\n");
            		choice = -1;
            	}
            	sc.nextLine();
            } while(choice == -1);
            

            Database_Controller.updateOrder(order);
        }
    }

    /**
     * PrintallOrderbrief Method:
     * The PrintallOrderbrief Method creates an arraylist of Order objects with values
     * retrieved from Orders.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Order will be printed out with a for loop
     * The items and promotions ordered will not be printed
     */
    public void PrintallOrderbrief() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            System.out.println("orderId   staffId   membership   userContact   totalPrice   tableId   paid      Date             Time");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < Order.size(); i++) {

                if (Order.get(i).isPaid() == false) {
                    System.out.printf("%-10d%-10d%-13b%-14d%-13.2f%-10d%-10b%-17s%s\n", Order.get(i).getOrderId(), Order.get(i).getStaffId()
                            , Order.get(i).isMembership(), Order.get(i).getUserContact(), Order.get(i).getTotalPrice(), Order.get(i).getTableNum()
                            , Order.get(i).isPaid(), Order.get(i).getDate(), Order.get(i).getTime());
                }

            }
        }
    }


    public void populateOrder(){
        int counter = 100;
        for(int q = 0; q < counter; q++){
            int orderId = 1;
            ArrayList<Order> presentOrders = Database_Controller.readOrderList();
            if (presentOrders != null) {
                orderId = presentOrders.size() + 1;
            }
            Random rand = new Random();

            // nextInt as provided by Random is exclusive of the top value so you need to add 1

            ArrayList<Staff> Staff = Database_Controller.readStaffList();
            int randomStaffId= rand.nextInt((Staff.size() - 2) + 1) + 0;;
            int staffId=Staff.get(randomStaffId).getEmployeeId();
            boolean membership=false ;
            int userContact = 10000000;
            if((rand.nextInt((1 - 0) + 1) + 1)>1){membership=true;
                ArrayList<Customer> Customer = Database_Controller.readCustomerList();
                int randomUserId= rand.nextInt((Customer.size() - 2) + 1) + 0;;
                userContact=Customer.get(randomUserId).getContact();}

            ArrayList<MenuItem> alacarteoptions = Database_Controller.readMenuItemList();
            ArrayList<Promotion> promotionoptions= Database_Controller.readPromotionList();

            ArrayList<MenuItem> alacarteList = new ArrayList<MenuItem>();
            ArrayList<Promotion> promotionList = new ArrayList<Promotion>();

            for( int  i = 0; i < rand.nextInt((6 - 0) + 1) + 1;i++){
                int randomalacarteoptions= rand.nextInt((alacarteoptions.size() - 2) + 1) + 1;;
                MenuItem MenuItem = Database_Controller.getMenuItemByName(alacarteoptions.get(randomalacarteoptions).getItemName());
                alacarteList.add(MenuItem);
            }

            for(  int i = 0; i < rand.nextInt((6 - 0) + 1) + 1;i++){
                int randompromotionoptions= rand.nextInt((promotionoptions.size() - 2) + 1) + 1;;
                Promotion Promotion = Database_Controller.getPromotionByName(promotionoptions.get(randompromotionoptions).getName());
                promotionList.add(Promotion);
            }


            double totalPrice = 0;
            if (alacarteList != null) {
                for ( int i = 0; i < alacarteList.size(); i++) {
                    totalPrice += alacarteList.get(i).getPrice();
                }
            }
            if (promotionList != null) {
                for ( int i = 0; i < promotionList.size(); i++) {
                    totalPrice += promotionList.get(i).getPrice();
                }
            }
            if (membership == true) {
                totalPrice *= 0.9;
            }
            totalPrice *=1.07;
            boolean paid = true;

            ArrayList<Table> Table = Database_Controller.readTableList();
            int randtableId= rand.nextInt((Table.size() - 2) + 1) + 0;;
            int tableId= Table.get(randtableId).getId();

            //region randomDate
            long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2021, 11, 5).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate Date = LocalDate.ofEpochDay(randomDay);
            //endregion

            //region randomTime
            LocalTime time1 = LocalTime.of(8, 0, 0);
            LocalTime time2 = LocalTime.of(20, 0, 0);
            int secondOfDayTime1 = time1.toSecondOfDay();
            int secondOfDayTime2 = time2.toSecondOfDay();
            Random random = new Random();
            int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2-secondOfDayTime1);
            LocalTime Time = LocalTime.ofSecondOfDay(randomSecondOfDay);
            //endregion

            Order Order = new Order(orderId, staffId, membership, userContact, alacarteList, promotionList, totalPrice, tableId, paid, Date, Time);
            Database_Controller.addOrder(Order);}
    }



}
