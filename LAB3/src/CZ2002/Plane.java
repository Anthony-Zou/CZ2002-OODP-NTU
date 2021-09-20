package CZ2002;

public class Plane {
    int SEATSIZE = 12;
    PlaneSeat[] seat;
    PlaneSeat[] OccupiedSeats;
    int numEmptySeat;

    public Plane() {
        seat = new PlaneSeat[SEATSIZE];
        for (int i = 0; i < SEATSIZE; i++) {
            seat[i] = new PlaneSeat(i + 1);
        }
        numEmptySeat = seat.length;

    }

    public PlaneSeat sortSeats() {

        OccupiedSeats = new PlaneSeat[(SEATSIZE-numEmptySeat)];

        int oIndex = 0;//occupiedseatindex

        for (int i = 0; i < SEATSIZE; i++) {
            if (seat[i].isOccupied() == true) {
                OccupiedSeats[oIndex] = new PlaneSeat(seat[i].getSeatId());
                OccupiedSeats[oIndex].assign(seat[i].getCustomerId());
                //System.out.println(OccupiedSeats[oIndex].getSeatId()+" "+OccupiedSeats[oIndex].getCustomerId());
                oIndex++;
            }
        }

        // insertion sort
        int n = OccupiedSeats.length;
        for (int i = 1; i < n; ++i) {
            int customerId = OccupiedSeats[i].getCustomerId();
            int seatId = OccupiedSeats[i].getSeatId();
            int j = i - 1;
            while (j >= 0 && OccupiedSeats[j].getCustomerId() > customerId) {
                OccupiedSeats[j + 1] = new PlaneSeat( OccupiedSeats[j].getSeatId());
                OccupiedSeats[j + 1].assign(OccupiedSeats[j].getCustomerId());
                j = j - 1;
            }
            OccupiedSeats[j + 1]= new PlaneSeat(seatId);;
            OccupiedSeats[j + 1].assign(customerId);
        }

        return null;
    }


    public void showNumEmptySeats() {
        System.out.println("There are " + numEmptySeat + " empty seats");
    }

    public void showEmptySeats() {

        for (int i = 0; i < seat.length; i++) {
            if (seat[i].isOccupied() == false) {
                System.out.println("SeatID " + seat[i].getSeatId());
            }
        }
    }

    public void showAssignedSeats(boolean bySeatId) {
        if (bySeatId == true) {
            for (int i = 0; i < seat.length; i++) {
                if (seat[i].isOccupied() == true) {
                    System.out.println("SeatID " + seat[i].getSeatId() + "  assigned to CustomerID " + seat[i].getCustomerId() + ". ");
                }
            }
        } else {

            sortSeats();
            if (OccupiedSeats.length > 0) {
                for (int i = 0; i < OccupiedSeats.length; i++) {

                    System.out.println("SeatID " + OccupiedSeats[i].getSeatId() + "  assigned to CustomerID " + OccupiedSeats[i].getCustomerId() + ". ");
                }
            } else {
                System.out.println("No customer yet");
            }
        }

    }

    public void assignSeat(int seatId, int cust_id) {

        for (int i = 0; i < seat.length; i++) {
            if(seat[i].getSeatId()==seatId&&seat[i].isOccupied()==true)
            {
                System.out.println("Seat already assigned to a customer");
                return;
            }
            if (seat[i].getCustomerId() == cust_id) {
                System.out.println("Customer Already Assigned with a seat");
                return;
            }
        }
        for (int i = 0; i < seat.length; i++) {
            if (seat[i].getSeatId() == seatId) {
                seat[i].assign(cust_id);
                numEmptySeat = numEmptySeat - 1;
                System.out.println("Seat Assigned!");
                //System.out.println(seat[i].getSeatId()+"is Assigned to customer "+seat[i].getCustomerId());
                //System.out.println(numEmptySeat);
            }
        }

    }

    public void unAssignSeat(int seatId) {
        for (int i = 0; i < seat.length; i++) {
            if (seat[i].getSeatId() == seatId) {
                seat[i].unAssign();
                numEmptySeat = numEmptySeat + 1;
            }
        }
    }
}
