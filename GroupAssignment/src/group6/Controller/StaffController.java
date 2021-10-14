package Controller;
import Entity.*;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Entity.Staff;

public class StaffController {
    Staff CurrentStaff;
    public StaffController(Staff staff) {
        CurrentStaff=staff;
    }
    Scanner sc = new Scanner(System.in);


    OrderController OrderController = new OrderController();
    ReservationController ReservationController = new ReservationController();

    public void addUpdateDeleteMenuItem() {
        MenuItemController MenuItemController = new MenuItemController();
        MenuItemController.addMenuItem();
        MenuItemController.updateMenuItem();
        MenuItemController.deleteMenuItem();
    }

    public void addUpdateDeletePromotion() {
        PromotionController PromotionController = new PromotionController();
        PromotionController.addPromotion();
        PromotionController.UpdatePromotion();
        PromotionController.DeletePromotion();
    }

    public void createOrder() {
        OrderController.createOrder();
    }

    public void viewOrder() {
        OrderController.viewOrder();
    }

    public void addDeleteOrderItem() {
        OrderController.addOrderItem();
        OrderController.deleteOrderItem();
    }

    public void createReservation() {
        ReservationController.createReservation();
    }

    public void checkRemoveRervationBooking() {
        ReservationController.checkRemoveRervationBooking();
    }

    public void checkTableAvailaabitity() {
        TableController TableController = new TableController();
        TableController.checkTableAvailaabitity();
    }

    public void printOrderInvoice() {
        OrderController.printOrderInvoice();
    }

    public void printSalesRevenueReport() {
        RevenueController RevenueController = new RevenueController();
        RevenueController.printSalesRevenueReport();
    }
}
