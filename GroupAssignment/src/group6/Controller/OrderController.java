package Controller;

import Entity.MenuItem;
import Entity.Order;
import Entity.Promotion;
import Entity.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderController {
    Scanner sc = new Scanner(System.in);

    public void createOrder() {
        System.out.println("Create an Order");
        System.out.println("---------------------");

        System.out.println("Generating Order Id");
        System.out.println("---------------------");
        int orderId = 1;
        ArrayList<Order> presentOrders = Database_Controller.readOrderList();
        if (presentOrders != null) {
            orderId = presentOrders.size() + 1;
        }

        System.out.println("Enter Your Staff Id");
        System.out.println("---------------------");
        StaffController StaffController = new StaffController();
        StaffController.printStaffDetails();
        int staffId;
        while(true) {
             staffId = sc.nextInt();
            if (Database_Controller.getStaffByEmployeeId(staffId) != null) {
                break;
            } else {
                System.out.println("Invalid staff ID! Enter again");
            }
        }

        System.out.println("Assign Available Table");
        System.out.println("---------------------");
        System.out.println("Enter pax:");
        int pax = sc.nextInt();
        TableController TableController = new TableController();
        TableController.printAvailableTables(pax);
        System.out.println("Enter Table Id of choices:");
        int tableId;
        while(true) {
             tableId = sc.nextInt();
            if (Database_Controller.getTableById(tableId) != null) {
                break;
            } else {
                System.out.println("Invalid table ID! Enter again");
            }
        }

        System.out.println("Is Customer a member?");
        System.out.println("0. No");
        System.out.println("1. Yes");
        System.out.println("---------------------");
        boolean membership = false;
        int userContact = 00000000;
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Verify Membership");
            System.out.println("Enter Customer Contact");
            System.out.println("---------------------");
            userContact = sc.nextInt();
            if (Database_Controller.getCustomerByContact(userContact) != null) {
                membership = Database_Controller.getCustomerByContact(userContact).isMemberShip();
            }
        } else if (choice == 0) {
            membership = false;
        }


        System.out.println("Add Order Item");
        System.out.println("---------------------");
        MenuItemController MenuItemController = new MenuItemController();
        PromotionController PromotionController = new PromotionController();
        ArrayList<MenuItem> alacarteList = new ArrayList<MenuItem>();
        ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
        //do while loop
        //choose alacarte
        //choose promotion
        do {
            System.out.println("Choose Item Type");
            System.out.println("1. Alacarte");
            System.out.println("2. Promotion");
            System.out.println("0. Done");
            System.out.println("---------------------");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Alacarte Item");
                    System.out.println("---------------------");
                    MenuItemController.printMenuItem();
                    System.out.println("Enter the name of the alacarte item :");
                    String itemname = sc.next();
                    MenuItem MenuItem = Database_Controller.getMenuItemByName(itemname);
                    alacarteList.add(MenuItem);
                    break;
                case 2:
                    System.out.println("Enter Promotion Set Item Id");
                    System.out.println("---------------------");
                    PromotionController.printPromotion();
                    System.out.println("Enter the Id of the alacarte item :");
                    int promotionId = sc.nextInt();
                    Promotion Promotion = Database_Controller.getPromotionById(promotionId);
                    promotionList.add(Promotion);
                    break;
                default:
                    break;

            }
        } while (choice != 0);


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

        boolean paid = false;


        Table table = Database_Controller.getTableById(tableId);
        table.setReserved(true);
        Database_Controller.updateTable(table);

        Order Order = new Order(orderId, staffId, membership, userContact, alacarteList, promotionList, totalPrice, tableId, paid);
        Database_Controller.addOrder(Order);
    }

    public void deleteOrder() {
        System.out.println("Remove a Order");
        System.out.println("---------------------");
        // find if the Reservation is in the database or not //
        System.out.println("Enter the number of the Order:");
        int Number = sc.nextInt();
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

    public void allOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            
            for (int i = 0; i < Order.size(); i++) {
            	System.out.println("orderId" + "\t\t" + "staffId" + "\t\t" + "membership" + "\t\t" + "userContact" + "\t\t" + "totalPrice" + "\t\t" + "tableId" + "\t\t" + "paid" + "\t\t");
                System.out.println("=============================================================================================================================");
                System.out.println(Order.get(i).getOrderId() + "\t\t" + Order.get(i).getStaffId() +
                        "\t\t" + Order.get(i).isMembership() + "\t\t\t"
                        + Order.get(i).getUserContact() + "\t\t" + String.format("%.2f", Order.get(i).getTotalPrice()) + "\t\t\t" + Order.get(i).getTableNum() + "\t\t" + Order.get(i).isPaid() + "\t\t\n");

                //Print Alacarte Item in the order
                System.out.println("< Alacarte Item >");
                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                for (int j = 0; j < Order.get(i).getAlacarte().size(); j++) {
                    System.out.println(Order.get(i).getAlacarte().get(j).getItemName()
                            + "\t\t " + Order.get(i).getAlacarte().get(j).getPrice());
                } System.out.println();
                
                //Print Promotion Item in the order
                System.out.println("< Promotion Item >");
                System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
                for (int j = 0; j < Order.get(i).getPromotion().size(); j++) {
                    System.out.println(
                            Order.get(i).getPromotion().get(j).getName()
                                    + "\t\t " + Order.get(i).getPromotion().get(j).getPrice());
                } System.out.println("\n\n");
            }
        }
    }

    public void viewUnpaidOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            System.out.println("orderId" + "\t\t\t" + "staffId" + "\t\t\t" + "membership" + "\t\t\t" + "userContact" + "\t\t\t" + "totalPrice" + "\t\t\t" + "tableId" + "\t\t\t" + "paid" + "\t\t\t");
            for (int i = 0; i < Order.size(); i++) {
                if(Order.get(i).isPaid()==false) {
                    System.out.println(Order.get(i).getOrderId() + "\t\t\t\t" + Order.get(i).getStaffId() +
                            "\t\t\t\t" + Order.get(i).isMembership() + "\t\t\t"
                            + Order.get(i).getUserContact() + "\t\t\t" + Order.get(i).getTotalPrice() + "\t\t\t" + Order.get(i).getTableNum() + "\t\t\t" + Order.get(i).isPaid() + "\t\t\t");

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
    }

    public void viewpaidOrder() {
        ArrayList<Order> Order = Database_Controller.readOrderList();
        if (Order != null) {
            System.out.println("orderId" + "\t\t\t" + "staffId" + "\t\t\t" + "membership" + "\t\t\t" + "userContact" + "\t\t\t" + "totalPrice" + "\t\t\t" + "tableId" + "\t\t\t" + "paid" + "\t");
            for (int i = 0; i < Order.size(); i++) {
                if(Order.get(i).isPaid()==true) {
                    System.out.println(Order.get(i).getOrderId() + "\t\t\t\t" + Order.get(i).getStaffId() +
                            "\t\t\t\t" + Order.get(i).isMembership() + "\t\t\t"
                            + Order.get(i).getUserContact() + "\t\t\t" + Order.get(i).getTotalPrice() + "\t\t\t" + Order.get(i).getTableNum() + "\t\t\t" + Order.get(i).isPaid() + "\t\t\t");

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
    }

    // maybe add number of pax +/........
    public void printOrderInvoice() {

        viewUnpaidOrder();
        System.out.println("Enter Order choice");
        int Number=sc.nextInt();
        if (Database_Controller.getOrderById(Number) == null) {
            System.out.println("orderId does not exist!");
        } else {
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

            //region print invoice
            System.out.println(Order.getOrderId() + "\t\t\t\t" + Order.getStaffId() +
                    "\t\t\t\t" + Order.isMembership() + "\t\t\t"
                    + Order.getUserContact() + "\t\t\t" + Order.getTotalPrice() + "\t\t\t" + Order.getTableNum() + "\t\t\t" + Order.isPaid() + "\t\t\t");

            //Print Alacarte Item in the order
            System.out.println("Alacarte Item");
            System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
            for (int j = 0; j < Order.getAlacarte().size(); j++) {
                System.out.println("\t" + Order.getAlacarte().get(j).getItemName()
                        + "\t" + Order.getAlacarte().get(j).getPrice());
            }
            //Print Promotion Item in the order
            System.out.println("Promotion Item");
            System.out.println("Item Name" + "\t" + " Price(SGD)" + "\t");
            for (int j = 0; j < Order.getPromotion().size(); j++) {
                System.out.println(
                        "\t" + Order.getPromotion().get(j).getName()
                                + "\t" + Order.getPromotion().get(j).getPrice());
            }
            //endregion
        }
    }

  //  public void  Add/Remove order item/s to/from order (){}

    public static void main(String[] args) {

            OrderController OrderController= new OrderController();
           OrderController.createOrder();
      // OrderController.allOrder();
        //OrderController.deleteOrder();
     //  OrderController.viewUnpaidOrder();
        OrderController.printOrderInvoice();

    }

}
