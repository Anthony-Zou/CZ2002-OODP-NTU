package sales;

public class SalePerson implements Comparable<SalePerson> {
  private String firstName;
  private String lastName;
  private int totalSales;

  public SalePerson(String firstName, String lastName, int totalSales) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.totalSales = totalSales;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getTotalSales() {
    return totalSales;
  }

  @Override
  public int compareTo(SalePerson p) {
    if (this.totalSales < p.getTotalSales())
      return -1;
    else if (this.totalSales > p.getTotalSales())
      return 1;
    else {
      if (this.lastName.compareTo(p.getLastName()) > 0)
        return -1;
      else
        return 1;
    }
  }

  @java.lang.Override
  public java.lang.String toString() {
    return lastName + ", " + firstName + ":" + totalSales;
  }

  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null || getClass() != object.getClass())
      return false;
    if (!super.equals(object))
      return false;
    SalePerson that = (SalePerson) object;
    return totalSales == that.totalSales && java.util.Objects.equals(firstName, that.firstName)
        && java.util.Objects.equals(lastName, that.lastName);
  }
}
