package figure;

public class Square extends Shape 
{  // fields 
   double side; 
   
   // constructors
   public Square()
   {
      name = "Square";
      side = 0;
   }
   public Square(double _side) 
   { 
      
      side = _side; 
   } 
   // methods 
   public void computeArea() 
   { name = "Square"; 
      area = side * side; 
   } 
   public void computeVolume()
   {  name = "Cube"; 
      volume=side * side* side; 
   }

}