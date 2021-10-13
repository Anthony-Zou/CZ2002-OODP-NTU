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
      
      radius = _radius; 
   } 
   // methods 
   
   public void computeArea() 
   { 
      name = "Circle"; 
      area = Math.PI * radius * radius; 
   } 
   public void computeVolume() 
   { 
      name = "Sphere"; 
      volume = Math.PI * radius * radius* radius * (4/3); 
      //System.out.println(volume);
   } 

}