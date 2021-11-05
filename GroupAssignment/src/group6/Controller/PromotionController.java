package Controller;

import Entity.MenuItem;
import Entity.Promotion;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        
        int id = 0;
        do {
        	try {
        		System.out.println("Enter the ID of the Promotion:");
                id = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid ID!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (id == 0);
        
//        if (Database_Controller.getPromotionById(id) != null) {
//            System.out.println("Promotion already exists!");
//            return;
//        } else {
        
        int userChoice;
        
        String name = "";
        do {
        	try {
        		System.out.println("Name of Promotion:");
        		name = sc.next();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid name!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (name == "");
        
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

        String description = "";
        do {
        	try {
        		System.out.println("Description of Promotion:");
                description = sc.next();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid description!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine();
        } while (description == "");
        
        Double price = 0.0;
        do {
        	try {
        		System.out.println("Price of Promotion:");
                price = sc.nextDouble();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid price!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine();
        } while(price == 0);
        

        Promotion Promotion = new Promotion(id, name, Items, description, price);
        Database_Controller.addPromotion(Promotion);

        //    }
        printPromotion();
    }
    //update price
    public void UpdatePromotion() {
        printPromotion();
        
        int id = 0;
        do {
        	try {
        		System.out.println("Enter ID of Promotion to be updated");
                id=sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid ID!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine();
        } while(id == 0);
        
        if (Database_Controller.getPromotionById(id) == null) {
            System.out.println("Promotion does not exist!");
        } else {
            Promotion Promotion = Database_Controller.getPromotionById(id);
            //content
            
            Double newPrice = 0.0;
            do {
            	try {
            		System.out.println("Enter new price of the Promotion");
                    newPrice = sc.nextDouble();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid price!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine();
            } while(newPrice == 0);
            Promotion.setPrice(newPrice);
            Database_Controller.updatePromotion(Promotion);
        }
    }

    public void DeletePromotion() {
        System.out.println("Remove a Promotion");
        System.out.println("---------------------");
        // find if the Promotion is in the database or not //
        
        int id = 0;
        do {
        	try {
        		System.out.println("Enter the Id of the MenuItem:");
                id = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid ID!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine();
        } while(id == 0);
        
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
        Promotion = Database_Controller.readPromotionList();
        if(Promotion!=null) {
            for (int i = 0; i < Promotion.size(); i++) {
            	System.out.println("Id" + "\t" + "Promotion Name" + "\t" + " Description" + "\t" + " Price(SGD)" + "\t");
            	System.out.println("--------------------------------------------------");
                System.out.println(Promotion.get(i).getId() + "\t" + Promotion.get(i).getName() +
                        "\t\t " + Promotion.get(i).getDecription() + "\t "+ Promotion.get(i).getPrice() + "\n");
                
                System.out.println(
                        "Item "
                                + "\t"
                                + "Item Name" + "\t"
                                + " Description" + "\t"
                                + " Price(SGD)" + "\t");
                System.out.println("--------------------------------------------------");
                for (int j = 0; j < Promotion.get(i).getItems().size(); j++) {
                    System.out.println(
                            
                                    (j + 1)
                                    + "\t" + Promotion.get(i).getItems().get(j).getItemName()
                                    + "\t " + Promotion.get(i).getItems().get(j).getDescription()
                                    + "\t " + Promotion.get(i).getItems().get(j).getType() + "\n");
                }
            }
            
        }
    }

    public static void main(String[] args) {
        PromotionController Promotion = new PromotionController();
        Promotion.addPromotion();
        //Promotion.UpdatePromotion();
        //Promotion.printPromotion();
    }
}
