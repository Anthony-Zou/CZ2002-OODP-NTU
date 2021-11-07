package Controller;
import Entity.*;
import java.util.*;
import Boundary.UserInterface;
public class MenuItemController {
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
    public void addMenuItem() {
        System.out.println("Add a MenuItem");
        System.out.println("---------------------");
        System.out.println("Enter the Name of the MenuItem:");
        String itemName = sc.nextLine();
        if (Database_Controller.getMenuItemByName(itemName) != null) {
            System.out.println("MenuItem already exists!");
            return;
        } else {
            int userChoice;
            System.out.println("Description of MenuItem:");
            String description = sc.nextLine();
            System.out.println("Price of MenuItem:");
            Double price = sc.nextDouble();
            System.out.println("Type of MenuItem:");
            System.out.println("1. Main course");
            System.out.println("2. Drinks");
            System.out.println("3. Dessert");
            String type="";
            userChoice=sc.nextInt();
            do{switch(userChoice)
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
                    System.out.println("Invalid");
                    userChoice=sc.nextInt();
            }
            }while(userChoice <1 && userChoice>3);


            MenuItem MenuItem = new MenuItem(itemName, description,price,type);
            Database_Controller.addMenuItem(MenuItem);

        }
    printMenuItem();
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
        if (Database_Controller.getMenuItemByName(itemName) == null) {
            System.out.println("MenuItem does not exist!");
        } else {
            MenuItem MenuItem = Database_Controller.getMenuItemByName(itemName);
            //content
            System.out.println("Enter new price of the Item");
            MenuItem.setPrice(sc.nextDouble());
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
    public void deleteMenuItem() {
        System.out.println("Remove a MenuItem");
        System.out.println("---------------------");
        // find if the MenuItem is in the database or not //
        System.out.println("Enter the Item Name of the MenuItem:");
        String itemName = sc.next();
        if (Database_Controller.getMenuItemByName(itemName) == null) {
            System.out.println("MenuItem does not exist!");

        } else {
            Database_Controller.deleteMenuItem(itemName);// =---- from the database
            System.out.println("MenuItem removed!");

        }
    }

    /**
     * printMenuItem Method:
     * The printMenuItem Method creates an arraylist of MenuItem objects with values
     * retrieved from MenuItem.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the MenuItem detail will be printed out with a for loop
     */
    public void printMenuItem() {
        System.out.println("Print MenuItem");
        System.out.println("---------------------");
        System.out.println("All available MenuItem:");
        ArrayList<MenuItem> MenuItem = new ArrayList<MenuItem>();
        MenuItem = Database_Controller.readMenuItemList();
        System.out.println("Item Name" + "\t\t\t" + " Description" + "\t\t\t" + " Price(SGD)"+ "\t\t\t" + " Type");
        for (int i=0;i<MenuItem.size();i++){
            System.out.println(MenuItem.get(i).getItemName()+"\t\t\t"+MenuItem.get(i).getDescription()+"\t\t\t"+MenuItem.get(i).getPrice()+"\t\t\t"+MenuItem.get(i).getType());
        }

    }

}
