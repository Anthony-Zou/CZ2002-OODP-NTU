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
      name = "Square"; 
      side = _side; 
   } 
   // methods 
   public void computeArea() 
   { 
      area = side * side; 
   } 

}