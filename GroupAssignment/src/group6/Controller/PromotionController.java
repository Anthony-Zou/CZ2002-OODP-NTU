package Controller;

import Entity.MenuItem;
import Entity.Promotion;

import java.util.ArrayList;
import java.util.Scanner;

public class PromotionController {
    //    private int Id;
//    private String Name;
//    private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
//    private String Decription;
//    private double price;
    Scanner sc = new Scanner(System.in);

    public void addPromotion() {
        System.out.println("Add a Promotion");
        System.out.println("---------------------");
        System.out.println("Enter the Id of the Promotion:");
        int id = sc.nextInt();
//        if (Database_Controller.getPromotionById(id) != null) {
//            System.out.println("Promotion already exists!");
//            return;
//        } else {
        int userChoice;
        System.out.println("Name of Promotion:");
        String name = sc.next();
        //view list of menu item
        //input number of items in the promotion set
        MenuItemController MenuItemController = new MenuItemController();
        ArrayList<MenuItem> Items = new ArrayList<MenuItem>();
        System.out.println("Number of items in the Promotion:");
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            MenuItemController.printMenuItem();
            System.out.println("Enter the name of the " + (i + 1) + " item to be added in the promotion set:");
            String itemname = sc.next();
            MenuItem MenuItem = Database_Controller.getMenuItemByName(itemname);
            Items.add(MenuItem);
        }

        System.out.println("Description of Promotion:");
        String description = sc.next();
        System.out.println("Price of Promotion:");
        Double price = sc.nextDouble();

        Promotion Promotion = new Promotion(id, name, Items, description, price);
        Database_Controller.addPromotion(Promotion);

        //    }
        printPromotion();
    }
    //update price
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

    public void printPromotion() {
        System.out.println("Print Promotion");
        System.out.println("---------------------");
        System.out.println("All available Promotion:");
        ArrayList<Promotion> Promotion = new ArrayList<Promotion>();
        Promotion = Database_Controller.readPromotionList();
        System.out.println("Id" + " Promotion Name" + "\t" + " Description" + "\t" + " Price(SGD)" + "\t");
        for (int i = 0; i < Promotion.size(); i++) {
            System.out.println(Promotion.get(i).getId() + "\t" + Promotion.get(i).getName() +
                    "\t" + Promotion.get(i).getDecription() + "\t"
                    + Promotion.get(i).getPrice() + "\t");
            System.out.println(
                            "\t\t Item "
                            + "\t"
                            + "Item Name" + "\t"
                            + " Description" + "\t"
                            + " Price(SGD)" + "\t");

            for (int j = 0; j < Promotion.get(i).getItems().size(); j++) {
                System.out.println(
                        "\t\t"
                        +
                        (j + 1)
                                + "\t" + Promotion.get(i).getItems().get(j).getItemName()
                                + "\t" + Promotion.get(i).getItems().get(j).getDescription()
                                + "\t" + Promotion.get(i).getItems().get(j).getType());
            }
        }
    }

    public static void main(String[] args) {
        PromotionController Promotion = new PromotionController();
        //Promotion.addPromotion();
        Promotion.UpdatePromotion();
        Promotion.printPromotion();
    }
}
