package figure;
/**
 *  This class Triangle calculates 
 *  the area of triangle 
 */

public class Circle extends Shape 
{  // fields 
   double radius; 
   // constructors
   public Circle()
   {
      name = "Circle";
      radius = 0;
   }
   public Circle(double _radius) 
   { 
      name = "Circle"; 
      radius = _radius; 
   } 
   // methods 
   
   public void computeArea() 
   { 
      area = Math.PI * radius * radius; 
   } 

}