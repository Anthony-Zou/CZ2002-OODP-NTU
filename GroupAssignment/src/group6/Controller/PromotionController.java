package Controller;

import Entity.MenuItem;
import Entity.Promotion;

import java.util.ArrayList;
import java.util.Scanner;

public class PromotionController {

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
    public void addPromotion() {
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
        String name = sc.next();
        //view list of menu item
        //input number of items in the promotion set
        MenuItemController MenuItemController = new MenuItemController();
        ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
        System.out.println("Number of items in the Promotion:");
        int num = sc.nextInt();
        String lfh = sc.nextLine();
        String itemname;
        MenuItemController.printMenuItem();
        for (int i = 0; i < num; i++) {

            System.out.println("Enter the name of the " + (i + 1) + " item to be added in the promotion set:");
            itemname = sc.nextLine();
            MenuItem MenuItem = Database_Controller.getMenuItemByName(itemname);
            Items.add(MenuItem);
        }

        System.out.println("Description of Promotion:");
        String description = sc.nextLine();
        System.out.println("Price of Promotion:");
        Double price = sc.nextDouble();

        Promotion Promotion = new Promotion(id, name, Items, description, price);
        Database_Controller.addPromotion(Promotion);

        //    }
        printPromotion();
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
        printPromotion();
        System.out.println("Enter Id of Promotion to be updated");
        int id=sc.nextInt();
        if (Database_Controller.getPromotionById(id) == null) {
            System.out.println("Promotion does not exist!");
        } else {
            Promotion Promotion = Database_Controller.getPromotionById(id);
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
    public void DeletePromotion() {
        System.out.println("Remove a Promotion");
        System.out.println("---------------------");
        // find if the Promotion is in the database or not //
        System.out.println("Enter the Id of the MenuItem:");
        int id = sc.nextInt();
        if (Database_Controller.getPromotionById(id) == null) {
            System.out.println("Promotion does not exist!");

        } else {
            Database_Controller.deletePromotion(id);// =---- from the database
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
    public void printPromotion() {
        System.out.println("< Available Promotions >");
        System.out.println();
        ArrayList<Promotion> Promotion = new ArrayList<Promotion>();
        System.out.println("--------------------------------------------------");
        Promotion = Database_Controller.readPromotionList();
        //System.out.println("Id" + "\t" + "Promotion Name" + "\t" + " Description" + "\t" + " Price(SGD)" + "\t");

        if(Promotion!=null) {
            for (int i = 0; i < Promotion.size(); i++) {

                System.out.println(Promotion.get(i).getId() + "\t" + Promotion.get(i).getName() +
                        "\t\t " + Promotion.get(i).getDecription() + "\t "+ Promotion.get(i).getPrice() );
                printPromotionById(Promotion.get(i).getId());
                System.out.println("--------------------------------------------------"+ "\n");

            }
            
        }
    }

    /**
     * printPromotionById Method:
     * This will print the Promotional Set Meal detail specific to the input order Id
     * @param Id
     */
    public void printPromotionById(int Id){
        Promotion promotion =new Promotion();
        promotion= Database_Controller.getPromotionById(Id);
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


}
