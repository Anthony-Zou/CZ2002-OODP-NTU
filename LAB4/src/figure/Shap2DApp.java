package figure;

import java.util.*;

public class Shap2DApp {
  // change

  public static void main(String[] args) {
    int size;
    int shapetype;
    char option;
    Scanner scan = new Scanner(System.in);
    do {
      System.out.print("\nEnter the total number of shapes? ");
      size = scan.nextInt();

      List<Object> list = new ArrayList<Object>();

      for (int i = 0; i < size; i++) {
        System.out.print("\nChoose the shapetype for shape " + (i + 1));
        System.out.print("\nEnter 1 for Circle ");
        System.out.print("\nEnter 2 for Square ");
        System.out.print("\nEnter 3 for Rectangle");
        System.out.print("\nEnter 4 for Triangle\n ");
        shapetype = scan.nextInt();

        switch (shapetype) {
          case 1:
            System.out.print("\nEnter dimension for Circle ");
            System.out.print("\nEnter radius\n ");
            double radius = scan.nextDouble();
            Circle c = new Circle(radius);
            list.add(c);
            break;
          case 2:
            System.out.print("\nEnter side\n");
            double side = scan.nextDouble();
            Square s = new Square(side);
            list.add(s);
            break;
          case 3:
            System.out.print("\nEnter length\n");
            double l = scan.nextDouble();
            System.out.print("\nEnter breadth\n");
            double b = scan.nextDouble();
            Rectangle r = new Rectangle(l, b);
            list.add(r);
            break;

          case 4:
            System.out.print("\nEnter dimension for Triangle\n ");
            System.out.print("\nEnter height\n");
            double height = scan.nextDouble();
            System.out.print("\nEnter base\n");
            double base = scan.nextDouble();
            Triangle t = new Triangle(height, base);
            list.add(t);
            break;
          default:
            System.out.print("\nEnter option listed");
        }
      }
      System.out.print("\nChoose the type of calculation");
      System.out.print("\nEnter 1 for Area ");
      System.out.print("\nEnter 2 for Volume \n");
      int calculationType = scan.nextInt();
      switch (calculationType) {
        case 1:
          double totalarea = 0;
          ;
          for (Object obj : list) {
            ((Shape) obj).computeArea();

            ((Shape) obj).display();

            totalarea += ((Shape) obj).getArea();
          }
          System.out.print("\nTotal Area = " + totalarea + "\n");
          break;
        case 2:
          break;
        default:
          System.out.print("\nEnter option listed");
          break;
      }
      System.out.println("Do you want to Create new shapes?");
      System.out.println("Y - yes,  N - no");
      System.out.print("option: ");
      option = scan.next().charAt(0);
    } while (Character.toUpperCase(option) == 'Y');

    scan.close();
  }

}
