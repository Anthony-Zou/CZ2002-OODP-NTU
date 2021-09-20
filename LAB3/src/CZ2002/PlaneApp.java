package CZ2002;
import java.util.Scanner;
public class PlaneApp {
    static String[] intro ={
            "(1) Show the number of empty seats ",
            "(2) Show the list of empty seats ",
            "(3) Show the list of customers together with their seat numbers in the order of the seat numbers",
            "(4) Show the list of customers together with their seat numbers in the order of the customer ID",
            "(5) Assign a customer to a seat",
            "(6) Remove a seat assignment",
            "(7) (i.e. quit) for terminating the program"
    };
    public static void printIntro(){
        for(int i=0; i<intro.length;i++){
            System.out.println(intro[i]);
        }
    }
    public static void main(String[] args) {
        // write your code here
        int choice;
        Scanner sc = new Scanner(System.in);
        printIntro();
        Plane plane = new Plane();
        do{

            System.out.println("Enter the number of your choice:");
            choice=sc.nextInt();


            switch(choice){
            case 1:
                plane.showNumEmptySeats();
                break;
            case 2:
                plane.showEmptySeats();
                break;
            case 3:
                plane.showAssignedSeats(true);
                break;
            case 4:
                plane.showAssignedSeats(false);
                break;
            case 5:
                System.out.println("Assigning Seat .. ");
                System.out.println("Please enter SeatID:");
                int seatId=sc.nextInt();
                System.out.println("Please enter Customer ID:");
                int customerId=sc.nextInt();
                plane.assignSeat(seatId,customerId);

                break;
            case 6:
                System.out.println("Enter SeatID to unassign customer from: ");
                int unassignseat=sc.nextInt();
                plane.unAssignSeat(unassignseat);
                System.out.println("Seat Unassigned!");
                break;
            case 7:
                System.out.println("Program terminating ….");
            default:
                System.out.println("Enter the number of your choice:");
                break;}


        }while(choice!=7);


    }
}
