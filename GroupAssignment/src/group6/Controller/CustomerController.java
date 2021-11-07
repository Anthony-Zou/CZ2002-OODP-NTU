package Controller;

import Entity.Customer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;


public class CustomerController {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * printCustomerDetails Method:
     * The printCustomerDetails Method creates an arraylist of customer objects with values
     * retrieved from Customer.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the customer detail will be printed out with a for loop
     */
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

    /**
     * Add Customer Method:
     * The addCustomer Method will create a Customer object with
     * requesting Customer name, contact and setting membership status from user
     * Furthermore, the object will be written and save in to the Customer.Dat file
     * With Add customer method in the Database controller
     */
    public void addCustomer() {
        System.out.println("Add a Customer");
        System.out.println("---------------------");
        String name = "";
        do {
        	try {
        		System.out.println("Enter the name of the customer: ");
        		name = sc.next();
        		
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid name!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); //clears buffer
        } while(name == "");
        
        
        if (Database_Controller.getCustomerByName(name) != null) {
            System.out.println("Customer already exists!");
            return;
        } else {
            int contact = 0;
            do {
            	try {
            		System.out.println("Contact number: ");
            		contact = sc.nextInt();
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); //clears buffer
            } while(contact == 0);
            //int contact;
            //while(true)
               //{System.out.println("Contact of Customer:")
                //contact = sc.nextInt();
                //if(contact>10000000 && contact<99999999) break;
                //else System.out.println("Invalid contact number!");
            boolean membership = false;
            int userChoice = 0;
            do {
            	try {
            		System.out.println("Membership of Staff:");
                    System.out.println("0. False");
                    System.out.println("1. True");
            		userChoice = sc.nextInt();
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter 0 / 1");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); //clears buffer
            } while(userChoice == 0);
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

    /**
     * DeleteCustomer method:
     * The DeleteCustomer Method will request user to input the customer name ( String)
     * to be deleted. It will first check the existance of the Customer with the
     * getCustomerByName method from the Database Controller and if the object exist,
     * it will activate the deleteCustomer method from the Database Controller with
     * passing in the customer name value
     */
    public void deleteCustomer() {
        System.out.println("< Remove Registered Customer >");
        System.out.println("---------------------");
        
        // find if the Customer is in the database or not //
        String name = "";
        do {
        	try {
        		System.out.println("Enter the name of the customer: ");
        		name = sc.next();
        		
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid name!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); //clears buffer
        } while(name == "");
        
        if (Database_Controller.getCustomerByName(name) == null) {
            System.out.println("Customer does not exist!");

        } else {
            Database_Controller.deleteCustomer(name);// =---- from the database
            System.out.println("Customer removed!");

        }
    }

    /**
     * Update Customer Method:
     * with passing value of
     * @param name , the method will first check of the object  existance
     * with getCustomerByName  method from the Database Controller
     * If the object exist, User can update the membership status of the Customer
     * object. Finally, the updated object will be passed to the
     * updateCustomer method in the database controller to update the customer.Dat file
     */
    public void updateCustomer(String name) {
        if (Database_Controller.getCustomerByName(name) == null) {
            System.out.println("Customer does not exist!");
        } else {
            Customer Customer = Database_Controller.getCustomerByName(name);
            
            boolean membership = false;
            int userChoice = 0;
            do {
            	try {
            		System.out.println("Update Membership of Customer:");
                    System.out.println("0. false");
                    System.out.println("1. true");
            		userChoice = sc.nextInt();
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter 0 / 1");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); //clears buffer
            } while(userChoice == 0);
            
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

}
