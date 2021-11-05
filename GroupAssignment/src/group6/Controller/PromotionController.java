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
        int id = 1;
        ArrayList<Promotion> presentPromotions = Database_Controller.readPromotionList();
        if (presentPromotions != null) {
            id = presentPromotions.size() + 1;
        }
        int userChoice;
        System.out.println("Name of Promotion:");
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

    public static void main(String[] args) {
        PromotionController Promotion = new PromotionController();
     //   Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion(); Promotion.addPromotion();
        //Promotion.UpdatePromotion();
        //Promotion.DeletePromotion();
        Promotion.printPromotion();
        //Promotion.printPromotionById(1);
    }
}
