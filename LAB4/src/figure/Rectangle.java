package figure;

/**
 *  This class Rectangle calculates 
 *  the area of rectangle 
 */
public class Rectangle extends Shape 
{  // fields 
   double length, width, height; 
   // constructors
   public Rectangle()
   {
      name = "Rectangle";
      length = width = 0;
   }
   public Rectangle(double _length, double _width) 
   { 
     
      length = _length; 
      width = _width; 
   } 
   public Rectangle(double _length, double _width,double _height) 
   { 
     
      length = _length; 
      width = _width; 
      height = _height;
   } 
   // methods 
   public void computeArea() 
   { 
      name = "Rectangle"; 
      area = length * width; 
   } 
   public void computeVolume() 
   { 
      name = "Cubiod"; 
      volume = length * width*height; 
   } 

}