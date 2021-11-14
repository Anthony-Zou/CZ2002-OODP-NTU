package Controller;
import Entity.*;
import java.util.*;

public class MenuItemController implements Controller{
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * addMenuItemMethod:
     * The addMenuItem Method will create a MenuItem object with
     * requesting itemName, description,price,type from user
     * Furthermore, the MenuItem object will be written and save in to the MenuItem.Dat file
     * With AddMenuItem method in the Database controller
     */
    public void add() {
        System.out.println("< Add a MenuItem >\n");
        
        
     // inputs name of the menu (check if it exists in the arraylist)
        String itemName = "";
        do {
        	try {
        		System.out.println("Enter the Name of the MenuItem:");
                sc=new Scanner(System.in);
                itemName = sc.nextLine();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid choice!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine();
        } while(itemName == "");
        
        // if the item already exists
        if (Database_Controller.getMenuItemByName(itemName) != null) {
            System.out.println("MenuItem already exists!");
            return;
            
        // or else the item is not in the arraylist --> proceed to register
        } else {
            
        	// 1. inputs description of the new menu
            String description = "";
            do {
            	try {
            		System.out.println("Description of the Menu: ");
            		description = sc.nextLine();
            	} catch (InputMismatchException e) {
            		System.out.println("Please describe the menu properly!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine();
            } while(description == "");
            
            
            // 2. inputs the price of the menu
            Double price = 0.0;
            do {
            	try {
            		System.out.println("Price of MenuItem:");
                    price = sc.nextDouble();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid price!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine();
            } while(price <= 0);
            
            
            // 3. inputs the type of the new menu
            
            String type="";
            
            // control invalid type / wrong value inputs
            int userChoice = 0;
            do {
            	try {
            		do {
            			System.out.println("Type of MenuItem:");
                        System.out.println("1. Main course");
                        System.out.println("2. Drinks");
                        System.out.println("3. Dessert");
            			userChoice=sc.nextInt();
	            		switch(userChoice)
	                    {
	                        case 1:
	                            type="Main course";
	                            break;
	                        case 2:
	                            type="Drinks";
	                            break;
	                        case 3:
	                            type="Dessert";
	                            break;
	                        default:
	                        	System.out.println("\nPlease enter a valid choice!");
	                    		System.out.println("\n-----------------------------------\n");

	                        	
	                    }
            		} while(userChoice < 1 || userChoice > 3);
            		
            	} catch (InputMismatchException e) {
            		System.out.println("\nPlease enter a valid choice!");
            		System.out.println("\n-----------------------------------\n");
            		userChoice = 0;
            	}
            	sc.nextLine(); //clears buffer
            } while(userChoice == 0);
            
            

            MenuItem MenuItem = new MenuItem(itemName, description,price,type);
            Database_Controller.addMenuItem(MenuItem);

        }
    print();
    }

    /**
     * Update MenuItem Method:
     * with passing value of
     * @param itemName , the method will first check of the object  existance
     * with getMenuItemByName  method from the Database Controller
     * If the object exist, User can update the Price of the Customer
     * object. Finally, the updated object will be passed to the
     * updateMenuItem method in the database controller to update the MenuItem.Dat file
     */
    public void updateMenuItem(String itemName) {
    	
    	// if the item does not exist in the arraylist
        if (Database_Controller.getMenuItemByName(itemName) == null) {
            System.out.println("MenuItem does not exist!");
            
         // or else it exists in the arraylist
        } else {
            MenuItem MenuItem = Database_Controller.getMenuItemByName(itemName);
            
         // get input & update the new price & update
            double newPrice = 0.0;
            do {
            	try {
            		System.out.println("Enter new price of the Item: ");
            		newPrice = sc.nextDouble();
            		
            	} catch (InputMismatchException e) {
            		System.out.println("\nPlease enter a valid price!");
            		System.out.println("\n-----------------------------------\n");
            		newPrice = 0.0;
            	}
            	sc.nextLine(); //clears buffer
            } while(newPrice == 0);
            
            MenuItem.setPrice(newPrice);
            Database_Controller.updateMenuItem(MenuItem);
        }
    }

    /**
     * DeleteMenuItem method:
     * The DeleteMenuItem Method will request user to input the MenuItem name ( String)
     * to be deleted. It will first check the existance of the MenuItem with the
     * getMenuItemByName method from the Database Controller and if the object exist,
     * it will activate the deleteMenuItem method from the Database Controller with
     * passing in the MenuItem name value
     */
    public void delete() {
        System.out.println("< Remove a MenuItem >\n");
        
        String itemName = "";
        do {
        	try {
        		System.out.println("Enter the Item Name of the MenuItem:");
        		itemName = sc.nextLine();
        	} catch (InputMismatchException e) {
        		System.out.println("\nPlease enter a valid item name!");
        		System.out.println("\n-----------------------------------\n");
        		itemName = "";
        	}
        	sc.nextLine();
        } while(itemName == "");
        
        // if the item does not exist in the arraylist
        if (Database_Controller.getMenuItemByName(itemName) == null) {
            System.out.println("MenuItem does not exist!");

        // if exists
        } else {
            Database_Controller.deleteMenuItem(itemName);// ---- from the database
            System.out.println("MenuItem removed!");

        }
    }

    /**
     * printMenuItem Method:
     * The printMenuItem Method creates an arraylist of MenuItem objects with values
     * retrieved from MenuItem.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the MenuItem detail will be printed out with a for loop
     */
    public void print() {
        System.out.println("< Available MenuItems >");
        ArrayList<MenuItem> MenuItem = new ArrayList<MenuItem>();
        MenuItem = Database_Controller.readMenuItemList();
        System.out.println("Item Name                          Description                                  Price(SGD)          Type");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (int i=0;i<MenuItem.size();i++){
//            System.out.println(MenuItem.get(i).getItemName()+"\t\t\t"+MenuItem.get(i).getDescription()+"\t\t\t"+
//                    MenuItem.get(i).getPrice()+"\t\t\t"+MenuItem.get(i).getType());

            System.out.printf("%-35s%-45s%-20.2f%s\n",MenuItem.get(i).getItemName(), MenuItem.get(i).getDescription(),MenuItem.get(i).getPrice(), MenuItem.get(i).getType() );
        }

    }
}
