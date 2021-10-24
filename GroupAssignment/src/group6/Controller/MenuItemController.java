package Controller;
import Entity.*;
import java.util.*;
import Boundary.UserInterface;
public class MenuItemController {
    Scanner sc = new Scanner(System.in);
    //String itemName, String description, double price, String type
    public void addMenuItem() {
        System.out.println("Add a MenuItem");
        System.out.println("---------------------");
        System.out.println("Enter the Name of the MenuItem:");
        String itemName = sc.next();
        if (Database_Controller.getMenuItemByName(itemName) != null) {
            System.out.println("MenuItem already exists!");
            return;
        } else {
            int userChoice;
            System.out.println("Description of MenuItem:");
            String description = sc.next();
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
    //update MenuItem Price
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

    public void printMenuItem() {
        System.out.println("Print MenuItem");
        System.out.println("---------------------");
        System.out.println("All available MenuItem:");
        ArrayList<MenuItem> MenuItem = new ArrayList<MenuItem>();
        MenuItem = Database_Controller.readMenuItemList();
        System.out.println("Item Name" + "\t" + " Description" + "\t" + " Price(SGD)"+ "\t" + " Type");
        for (int i=0;i<MenuItem.size();i++){
            System.out.println(MenuItem.get(i).getItemName()+"\t"+MenuItem.get(i).getDescription()+"\t"+MenuItem.get(i).getPrice()+"\t"+MenuItem.get(i).getType());
        }

    }
    public static void main(String[] args) {
        MenuItemController MenuItem= new MenuItemController();
//    MenuItem.addMenuItem();
//
//        MenuItem.updateMenuItem("Coke");
//        MenuItem.deleteMenuItem();
       MenuItem.printMenuItem();
    }
}
