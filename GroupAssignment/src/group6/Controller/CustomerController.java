package Controller;

import Entity.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {

    Scanner sc = new Scanner(System.in);

    public void printCustomerDetails() {
        ArrayList<Customer> Customer = Database_Controller.readCustomerList();
        if (Customer != null) {
            System.out.println("Customer Name" + "\t" + " Contact" + "\t" + " MemberShip");
            for (int i = 0; i < Customer.size(); i++) {
                System.out.println(Customer.get(i).getName() + "\t\t\t\t\t" + Customer.get(i).getContact()
                        + "\t\t\t\t" + Customer.get(i).isMemberShip());

            }
        }
    }

    public void addCustomer() {
        System.out.println("Add a Customer");
        System.out.println("---------------------");
        System.out.println("Enter the Name of the Customer:");
        String name = sc.next();
        if (Database_Controller.getCustomerByName(name) != null) {
            System.out.println("Customer already exists!");
            return;
        } else {
            int userChoice;
            System.out.println("Contact of Customer:");
            int contact = sc.nextInt();
            //int contact;
            //while(true)
               //{System.out.println("Contact of Customer:")
                //contact = sc.nextInt();
                //if(contact>10000000 && contact<99999999) break;
                //else System.out.println("Invalid contact number!");
            System.out.println("Membership of Staff:");
            System.out.println("0. False");
            System.out.println("1. True");
            boolean membership = false;
            userChoice = sc.nextInt();
            do {
                switch (userChoice) {
                    case 0:
                        membership = false;
                        break;
                    case 1:
                        membership = true;
                        break;
                    default:
                        System.out.println("Invalid");
                        userChoice = sc.nextInt();
                }
            } while (userChoice < 0 && userChoice > 1);
            Customer newCustomer = new Customer(name, contact, membership);
            Database_Controller.addCustomer(newCustomer);

        }
        printCustomerDetails();
    }

    public void deleteCustomer() {
        System.out.println("Remove a Customer");
        System.out.println("---------------------");
        // find if the Customer is in the database or not //
        System.out.println("Enter the Name of the Customer:");
        String name = sc.next();
        if (Database_Controller.getCustomerByName(name) == null) {
            System.out.println("Customer does not exist!");

        } else {
            Database_Controller.deleteCustomer(name);// =---- from the database
            System.out.println("Customer removed!");

        }
    }

    public void updateCustomer(String name) {

        if (Database_Controller.getCustomerByName(name) == null) {
            System.out.println("Customer does not exist!");
        } else {
            Customer Customer = Database_Controller.getCustomerByName(name);
            //content
            System.out.println("Update Membership of Customer:");
            System.out.println("0. false");
            System.out.println("1. true");
            boolean membership = false;
            int userChoice = sc.nextInt();
            do {
                switch (userChoice) {
                    case 0:
                        membership = false;
                        break;
                    case 1:
                        membership = true;
                        break;
                    default:
                        System.out.println("Invalid");
                        userChoice = sc.nextInt();
                }
            } while (userChoice < 0 && userChoice > 1);
            Customer.setMemberShip(membership);
            Database_Controller.updateCustomer(Customer);
        }
    }

    public static void main(String[] args) {
        CustomerController Customer = new CustomerController();
//        Customer.printCustomerDetails();
      //      Customer.addCustomer();
//        Customer.addCustomer();
//        Customer.deleteCustomer();
//        Customer.updateCustomer("Cathy");
  Customer.printCustomerDetails();

    }
}
