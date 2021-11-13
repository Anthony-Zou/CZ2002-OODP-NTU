package Controller;
import java.time.LocalDate;
import java.util.*;
import Entity.*;

public class RevenueController {
    /**
     * Creates empty controller object
     */
    public RevenueController() {}

    /**
     * getSalesReportOfDate Method
     * Generates Sales Report with inputing
     * @param date and caretes an array list of orders return
     * for that specific date and the arraylist is sent to printSalesRevenueReport method
     */
    public void getSalesReport(LocalDate date){
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

    /**
     * getSalesReportOfMonth Method With passed in values, an arraylist of
     * Order objects that falles in the year and month range and with paid status equals to true will be
     * created and read from the data file. Next it will be pass in to the printSalesRevenueReport method
     * @param month
     * @param year
     */
    public void getSalesReport(int month, int year){
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

    /**
     * getSalesReportOfYear Method With passed in values, an arraylist of
     * Order objects that falles in the year range and with paid status equals to true will be
     * created and read from the data file. Next it will be pass in to the printSalesRevenueReport method
     * @param year
     */
    public void getSalesReport(int year){

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

    /**
     * printSalesRevenueReport Method, with the passed in values there will be a calculated of the sum of item sold
     * for each of the item in menuitem and promotion. Next total discount, total GST, total ServiceCharge will also be
     * calculated. After the calculations, they will be printed in the sales report format.
     * @param paidOrders
     */
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
                    index = Database_Controller.getPromotionIndex(promotionItem.getName());
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

    /**
     * calcTotalTaxAndDisc Method:
     * This method calculates and returns the total tax  and discount details for the passed in order objects
     * @param paidOrders
     * @return
     */
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

}
