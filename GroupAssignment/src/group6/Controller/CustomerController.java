package Controller;

import Entity.Customer;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class CustomerController implements Controller {
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * printCustomerDetails Method:
     * The printCustomerDetails Method creates an arraylist of customer objects with values
     * retrieved from Customer.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the customer detail will be printed out with a for loop
     */
    public void print() {
        ArrayList<Customer> Customer = Database_Controller.readCustomerList();
        if (Customer != null) {
            System.out.printf("%-20s %-10s %-5s\n", "Customer Name", "Contact", "Membership");
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < Customer.size(); i++) {
                System.out.printf("%-20s %-10s %-5s\n", Customer.get(i).getName(), Customer.get(i).getContact(), Customer.get(i).isMemberShip());
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
    public void add() {
        System.out.println("< Register new Customer>\n");
        String name = "";
        do {
        	try {
        		System.out.println("Enter the name: ");
        		name = sc.next();
        		
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid name!");
        		System.out.println("\n-----------------------------------\n");
        		name = "";
        	}
        	sc.nextLine(); //clears buffer
        } while(name == "");
        
        
        if (Database_Controller.getCustomerByName(name) != null) {
            System.out.println("Customer already exists!");
            return;
        } else {
        	//1. Input Contact number
            int contact = 0;
            do {
            	try {
            		System.out.println("Contact number: ");
            		contact = sc.nextInt();
            		while(contact < 10000000 || contact > 99999999) {
                    	System.out.println("Please enter a valid number!");
                		System.out.println("\n-----------------------------------\n");
                		System.out.println("Contact number: ");
                        contact = sc.nextInt();
                    }
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            		contact = 0;
            	}
            	sc.nextLine(); //clears buffer
            } while(contact == 0);

            
            // 2. Input Member / Non-member
            boolean membership = false;
            int userChoice = -1;
            do {
            	try {
            		System.out.println("Membership:");
                    System.out.println("0. False");
                    System.out.println("1. True");
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
                                System.out.println("Please enter a valid choice!");
                                System.out.println("\n-----------------------------------\n");
                                System.out.println("Membership");
                                System.out.println("0. False");
                                System.out.println("1. True");
                                userChoice = sc.nextInt();
                        }
                    } while (userChoice < 0 && userChoice > 1);
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid choice!");
            		System.out.println("\n-----------------------------------\n");
            		userChoice = -1;
            	}
            	sc.nextLine(); //clears buffer
            } while(userChoice == -1);
            
            Customer newCustomer = new Customer(name, contact, membership);
            Database_Controller.addCustomer(newCustomer);

        }
        print();
    }

    /**
     * DeleteCustomer method:
     * The DeleteCustomer Method will request user to input the customer name ( String)
     * to be deleted. It will first check the existance of the Customer with the
     * getCustomerByName method from the Database Controller and if the object exist,
     * it will activate the deleteCustomer method from the Database Controller with
     * passing in the customer name value
     */
    public void delete() {
        System.out.println("< Remove Customer >\n");
        // find if the Customer is in the database or not //
        String name = "";
        do {
        	try {
        		System.out.println("Please enter the name of the customer: ");
        		name = sc.nextLine();
        		
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid name!");
        		System.out.println("\n-----------------------------------\n");
        		name = "";
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
            int userChoice = -1;
            do {
            	try {
            		System.out.println("< Update Membership of Customer >\n");
                    System.out.println("0. False");
                    System.out.println("1. True");
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
                                System.out.println("Please enter a valid choice!");
                                System.out.println("\n-----------------------------------\n");
                                System.out.println("Membership:");
                                System.out.println("0. False");
                                System.out.println("1. True");
                                userChoice = sc.nextInt();
                        }
                    } while (userChoice < 0 && userChoice > 1);
            		
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid choice!");
            		System.out.println("\n-----------------------------------\n");
            		userChoice = -1;
            	}
            	sc.nextLine(); //clears buffer
            } while(userChoice == -1);
            Customer.setMemberShip(membership);
            Database_Controller.updateCustomer(Customer);
        }
    }

    public void populateCustomer(int n){
        for (int i =0; i<n;i++){
           String name="Customer "+(i+1);
            Random rand = new Random();
           int num = 80000000+ rand.nextInt((900000 - 0) + 1) + 2;
           boolean status= true;
            Customer newCustomer = new Customer(name, num, status);
            Database_Controller.addCustomer(newCustomer);
           // Database_Controller.deleteCustomer(name);
        }
        print();
    }


}
