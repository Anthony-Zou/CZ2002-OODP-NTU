package sales;
// *************************************************************
// WeeklySales.java
//
// Sorts the sales staff in descending order by sales.
// ************************************************************

public class WeeklySales {
  public static void main(String[] args) {
    SalePerson[] salesStaff = new SalePerson[10];
    salesStaff[0] = new SalePerson("Jane", "A", 3000);
    salesStaff[1] = new SalePerson("Daffy", "Duck", 4935);
    salesStaff[2] = new SalePerson("James", "B", 3000);
    salesStaff[3] = new SalePerson("Dick", "Lemon", 2800);
    salesStaff[4] = new SalePerson("Don", "Salt", 1570);
    salesStaff[5] = new SalePerson("Jane", "C", 3000);
    salesStaff[6] = new SalePerson("Harry", "Tea", 7300);
    salesStaff[7] = new SalePerson("Andy", "Carrot", 5000);
    salesStaff[8] = new SalePerson("Jim", "Donnut", 2850);
    salesStaff[9] = new SalePerson("Walt", "D", 3000);

    Sorting.insertionSort(salesStaff);

    System.out.println("\nRanking of Sales for the Week\n");
    for (SalePerson s : salesStaff) System.out.println(s);
  }
}
