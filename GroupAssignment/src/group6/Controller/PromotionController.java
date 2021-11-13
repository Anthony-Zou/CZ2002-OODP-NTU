package Controller;

import Entity.MenuItem;
import Entity.Order;
import Entity.Promotion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PromotionController implements Controller{

    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * addPromotion Method:
     * The addPromotion Method will create a Promotion object with  requesting
     * id, name, Items, description, price from user
     * Furthermore, the Promotion object will be written and save in to the Promotion.Dat file
     * With AddPromotion method in the Database controller
     */
    public void add() {
        System.out.println("Add a Promotion");
        System.out.println("---------------------");

        int id = 1;
        ArrayList<Promotion> presentPromotions = Database_Controller.readPromotionList();
        if (presentPromotions != null) {
            id = presentPromotions.size() + 1;
        }
        int userChoice;
        System.out.println("Name of Promotion:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        MenuItemController MenuItemController = new MenuItemController();
        ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
        System.out.println("Number of items in the Promotion:");
        int num = sc.nextInt();
        String lfh = sc.nextLine();
        String itemname;
        MenuItemController.print();
        for (int i = 0; i < num; i++) {
            MenuItem MenuItem = new MenuItem();
        do {
            System.out.println("Enter the name of the " + (i + 1) + " item to be added in the promotion set:");
            itemname = sc.nextLine();
            MenuItem = Database_Controller.getMenuItemByName(itemname);
            if(MenuItem == null){
                System.out.println("Items not found! Please enter the item from Menu");
            }
        }while(MenuItem ==null);
            Items.add(MenuItem);
        }

        System.out.println("Description of Promotion:");
        String description = sc.nextLine();
        Double price;
        do {
            System.out.println("Price of Promotion:");
            sc = new Scanner(System.in);
            try {
                price = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Wrong value type entered.");
                price = 0.0;
            }
            if (price < 0) {
                System.out.println("Please enter a valid price.");
            }
        }while(price < 0);

        Promotion Promotion = new Promotion(name, Items, description, price);
        Database_Controller.addPromotion(Promotion);

        //    }

        print();
    }

    /**
     * Update MenuItem Method:
     * With in put Promotion Id, The method will first check of the object existance
     * with getPromotionById  method from the Database Controller
     * If the object exist, User can update the Price of the Promotion
     * object. Finally, the updated object will be passed to the
     * updatePromotion method in the database controller to update the Promotion.Dat file
     */
    public void UpdatePromotion() {
        print();
        System.out.println("Enter Name of Promotion to be updated");
        String name =sc.nextLine();
        if (Database_Controller.getPromotionByName(name) == null) {
            System.out.println("Promotion does not exist!");
        } else {
            Promotion Promotion = Database_Controller.getPromotionByName(name);
            //content
            System.out.println("Enter new price of the Promotion");
            Promotion.setPrice(sc.nextDouble());
            Database_Controller.updatePromotion(Promotion);
        }
    }

    /**
     * DeletePromotion method:
     * The DeletePromotion Method will request user to input the Promotion Id
     * to be deleted. It will first check the existance of the MenuItem with the
     * getPromotionById method from the Database Controller and if the object exist,
     * it will activate the deletePromotion method from the Database Controller with
     * passing in the Promotion Id
     */
    public void delete() {
        System.out.println("Remove a Promotion");
        System.out.println("---------------------");
        // find if the Promotion is in the database or not //
        System.out.println("Enter the name of the MenuItem:");
        String name = sc.nextLine();
        if (Database_Controller.getPromotionByName(name) == null) {
            System.out.println("Promotion does not exist!");

        } else {
            Database_Controller.deletePromotion(name);// =---- from the database
            System.out.println("Promotion removed!");

        }
    }

    /**
     * printPromotion Method:
     * The printPromotion Method creates an arraylist of Promotion objects with values
     * retrieved from Promotion.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Promotion detail will be printed out with a for loop
     * there there will be another forloop to print the MenuItem contained in the Promotional Set Meal
     */
    public void print() {
        System.out.println("< Available Promotions >");
        System.out.println();
        ArrayList<Promotion> Promotion = new ArrayList<Promotion>();
        System.out.println("--------------------------------------------------");
        Promotion = Database_Controller.readPromotionList();
        //System.out.println("Id" + "\t" + "Promotion Name" + "\t" + " Description" + "\t" + " Price(SGD)" + "\t");

        if(Promotion!=null) {
            for (int i = 0; i < Promotion.size(); i++) {

                System.out.println(Promotion.get(i).getName() +
                        "\t\t " + Promotion.get(i).getDecription() + "\t "+ Promotion.get(i).getPrice() );
                printPromotionByName(Promotion.get(i).getName());
                System.out.println("--------------------------------------------------"+ "\n");

            }
            
        }
    }

    /**
     * printPromotionById Method:
     * This will print the Promotional Set Meal detail specific to the input order Id
     * @param name
     */
    public void printPromotionByName(String name){
        Promotion promotion =new Promotion();
        promotion= Database_Controller.getPromotionByName(name);
        System.out.println(
                "Item "
                        + "\t"
                        + "Item Name" + "\t"
                        + " Description" + "\t"
                        );
        System.out.println("--------------------------------------------------");
        for (int j = 0; j < promotion.getItems().size(); j++) {
            if (promotion.getItems().get(j) != null) {
                System.out.println(

                        (j + 1)
                                + "\t" + promotion.getItems().get(j).getItemName()
                                + "\t " + promotion.getItems().get(j).getDescription()
                                + "\t " + promotion.getItems().get(j).getType() + "\n");
            }
        }

    }

//    public static void main(String[] args){
//        PromotionController promotionController = new PromotionController();
//        promotionController.print();
//    }
}
