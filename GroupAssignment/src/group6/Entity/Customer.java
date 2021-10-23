package Entity;

public class Customer {
    private String name;
    private int contact;
    private boolean memberShip;

    public Customer(String name, int contact, boolean memberShip) {
        this.name = name;
        this.contact = contact;
        this.memberShip = memberShip;
    }
    public Customer(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public boolean isMemberShip() {
        return memberShip;
    }

    public void setMemberShip(boolean memberShip) {
        this.memberShip = memberShip;
    }
}
