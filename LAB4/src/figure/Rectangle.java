package figure;

/**
 *  This class Rectangle calculates 
 *  the area of rectangle 
 */
public class Rectangle extends Shape 
{  // fields 
   double length, width; 
   // constructors
   public Rectangle()
   {
      name = "Rectangle";
      length = width = 0;
   }
   public Rectangle(double _length, double _width) 
   { 
      name = "Rectangle"; 
      length = _length; 
      width = _width; 
   } 
   // methods 
   public void computeArea() 
   { 
      area = length * width; 
   } 

}