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

        System.out.println("\nSales Revenue Report for " + date);
        System.out.println("===========================================");
        System.out.println();

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
        System.out.println("\nSales Revenue Report for Month " + month + "/" + year);
        System.out.println("===========================================");
        System.out.println();

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
        System.out.println("\nSales Revenue Report for Year " + year);
        System.out.println("===========================================");
        System.out.println();

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

        System.out.println("Gross Total Sales");
        System.out.println("--------------------------------------------");
        System.out.println("Name \t\t Quantity \t Item Revenue");
        System.out.println("--------------------------------------------");

        for(i=0; i<menuItemList.size(); i++){
            System.out.print(menuItemList.get(i).getItemName());
            qty = alacarteCount.get(i);
            System.out.print("\t\t" + qty);
            itemTotal = menuItemList.get(i).getPrice()*qty;
            totalGross += itemTotal;
            System.out.print("\t\t\t" +  itemTotal + "\n");
        }

        for(i=0; i<promotionItemList.size(); i++){
            System.out.print(promotionItemList.get(i).getName());
            qty = promotionCount.get(i);
            System.out.print("\t" + qty);
            itemTotal = promotionItemList.get(i).getPrice()*qty;
            totalGross += itemTotal;
            System.out.print("\t" +  itemTotal + "\n");
        }
        System.out.println("--------------------------------------------");
        System.out.printf("Total Gross Revenue: \t\t\t\t%.2f\n", totalGross);

        ArrayList<Double> DiscountsTaxes = calcTotalTaxAndDisc(paidOrders);
        double totalDiscount = DiscountsTaxes.get(0);
        double totalGST = DiscountsTaxes.get(1);
        double totalServiceCharge = DiscountsTaxes.get(2);

        System.out.println();
        System.out.println("Discount");
        System.out.println("--------------------------------------------");
        System.out.printf("%.2f\n", totalDiscount);
        System.out.println();

        System.out.println("Taxes");
        System.out.println("--------------------------------------------");
        System.out.printf("Total Service Charge: %.2f\n", totalServiceCharge);
        System.out.printf("Total GST: %.2f\n", totalGST);
        System.out.println("--------------------------------------------");
        System.out.printf("Total Taxes: \t %.2f\n", (totalGST + totalServiceCharge));
        System.out.println();

        double totalRevenue = totalGross - totalDiscount + totalGST + totalServiceCharge;
        System.out.printf("Total Net Revenue: %.2f\n", totalRevenue);

    }

    public ArrayList<Double> calcTotalTaxAndDisc (ArrayList<Order> paidOrders){
        int i;
        double totalGST, totalServiceCharge, totalDiscount, beforeGST, beforeServiceCharge;
        totalGST = totalServiceCharge = totalDiscount = 0.00;
        for(i=0; i<paidOrders.size(); i++){
            totalGST += paidOrders.get(i).getTotalPrice()/107*7;
            beforeGST = paidOrders.get(i).getTotalPrice()/107*100;
            totalServiceCharge += beforeGST/110*10;
            beforeServiceCharge = beforeGST/110*100;
            if(paidOrders.get(i).isMembership()){
                totalDiscount += beforeServiceCharge/90*10;
            }
        }

        ArrayList<Double> DiscountsTaxes = new ArrayList<Double>();
        DiscountsTaxes.add(totalDiscount);
        DiscountsTaxes.add(totalGST);
        DiscountsTaxes.add(totalServiceCharge);

        return DiscountsTaxes;
    }

    public static void main(String[] args){
        RevenueController RevenueController = new RevenueController();
        RevenueController.getSalesReportOfYear(2021);
//        RevenueController.getSalesReportOfDate(LocalDate.parse("2021-11-04"));
//        RevenueController.getSalesReportOfMonth(11, 2021);
    }
}
