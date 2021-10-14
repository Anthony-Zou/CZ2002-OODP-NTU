package figure;

/**
 * This class Triangle calculates the area of triangle
 */

public class Triangle extends Shape { // fields
   double height, base;

   // constructors
   public Triangle() {
      name = "Triangle";
      height = base = 0;
   }

   public Triangle(double _height, double _base) {
      height = _height;
      base = _base;
   }

   // methods
   public void computeArea() {
      name = "Triangle";
      area = (height * base) / 2;
   }

   public void computeVolume() {
      name = "Square-based Pyramid";
      volume = base * base * (height / 3);
   }
}