package CZ2002;

public class PlaneSeat {
    private int SeatId;
    private boolean assigned;
    private int customerId;

    public PlaneSeat(int seatId) {
        SeatId = seatId;
    }

    public int getSeatId() {
        return SeatId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public boolean isOccupied() {
        return assigned;
    }

    public void assign(int customerId) {
    this.customerId= customerId;
    assigned=true;
    }

    public void unAssign() {
    this.assigned=false;
    }


}
