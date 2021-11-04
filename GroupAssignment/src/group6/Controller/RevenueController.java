package Controller;
import java.time.LocalDate;
import java.util.*;
import Entity.*;

public class RevenueController {

    public RevenueController() {}

    public void getSalesReportOfDate(LocalDate date){

        int i;

        ArrayList<Order> paidOrders = new ArrayList<Order>();
        ArrayList<Order> allOrders = Database_Controller.readOrderList();

        for(i=0; i<allOrders.size(); i++){
            if(allOrders.get(i).getDate().equals(date) && allOrders.get(i).isPaid())
                paidOrders.add(allOrders.get(i));
        }

        printSalesRevenueReport(paidOrders);
    }

    public void getSalesReportOfMonth(int month, int year){

        int i;

        ArrayList<Order> paidOrders = new ArrayList<Order>();
        ArrayList<Order> allOrders = Database_Controller.readOrderList();

        for(i=0; i<allOrders.size(); i++){
            if(allOrders.get(i).getDate().getYear() == year && allOrders.get(i).getDate().getMonthValue() == month && allOrders.get(i).isPaid())
                paidOrders.add(allOrders.get(i));
        }

        printSalesRevenueReport(paidOrders);
    }

    public void getSalesReportOfYear(int year){

        int i;

        ArrayList<Order> paidOrders = new ArrayList<Order>();
        ArrayList<Order> allOrders = Database_Controller.readOrderList();

        for(i=0; i<allOrders.size(); i++){
            if(allOrders.get(i).getDate().getYear() == year && allOrders.get(i).isPaid())
                paidOrders.add(allOrders.get(i));
        }

        printSalesRevenueReport(paidOrders);
    }

    public void printSalesRevenueReport(ArrayList<Order> paidOrders) {

        int i, j, index, qty;

        // get list of all existing Menu & Promotions
        ArrayList<MenuItem> menuItemList = Database_Controller.readMenuItemList();
        ArrayList<Promotion> promotionItemList = Database_Controller.readPromotionList();
        ArrayList<Integer> alacarteCount = new ArrayList<Integer>(Collections.nCopies(menuItemList.size(), 0));
        ArrayList<Integer> promotionCount = new ArrayList<Integer>(Collections.nCopies(promotionItemList.size(), 0));

        for(i=0; i<paidOrders.size(); i++){ // for each paid order
            if(paidOrders.get(i).getAlacarte() != null) {
                for (j = 0; j < paidOrders.get(i).getAlacarte().size(); j++) { // for each alacarte item in the order, count the quantity
                    MenuItem menuItem = paidOrders.get(i).getAlacarte().get(j);
                    index = Database_Controller.getMenuItemIndex(menuItem.getItemName()); // find index in alacarte menu
                    if(index != -1) {
                        qty = alacarteCount.get(index) + 1;
                        alacarteCount.set(index, qty); // update qty
                    }
                }
            }
            if(paidOrders.get(i).getPromotion() != null) {
                for (j = 0; j < paidOrders.get(i).getPromotion().size(); j++) {
                    Promotion promotionItem = paidOrders.get(i).getPromotion().get(j);
                    index = Database_Controller.getPromotionIndex(promotionItem.getId());
                    if(index != -1) {
                        qty = promotionCount.get(index) + 1;
                        promotionCount.set(index, qty);
                    }
                }
            }
        }

        double totalGross = 0.00;
        double itemTotal;

        System.out.println("Date: ");
        System.out.println("Gross Total Sales");
        System.out.println("Name \t Quantity \t Total Gross Revenue");
        System.out.println("===========================================");
        for(i=0; i<menuItemList.size(); i++){
            System.out.print(menuItemList.get(i).getItemName());
            qty = alacarteCount.get(i);
            System.out.print("\t" + qty);
            itemTotal = menuItemList.get(i).getPrice()*qty;
            totalGross += itemTotal;
            System.out.print("\t" +  itemTotal + "\n");
        }

        for(i=0; i<promotionItemList.size(); i++){
            System.out.print(promotionItemList.get(i).getName());
            qty = promotionCount.get(i);
            System.out.print("\t" + qty);
            itemTotal = promotionItemList.get(i).getPrice()*qty;
            totalGross += itemTotal;
            System.out.print("\t" +  itemTotal);
        }

    }

    public double calcTotalTax(ArrayList<Order> paidOrders){
        int i;
        double totalGST, totalServiceCharge, totalTax;
        totalTax = totalGST = totalServiceCharge = 0.00;
        for(i=0; i<paidOrders.size(); i++){
            paidOrders.get(i).getTotalPrice()/107*100 =
        }
    }
    public double calcTotalDiscount()

    public static void main(String[] args){
        RevenueController RevenueController = new RevenueController();
        RevenueController.printSalesRevenueReport();
    }
}
