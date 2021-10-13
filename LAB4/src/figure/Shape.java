package figure;

public class Shape
{ // fields 
   String name; 
   double area; 
   // constructor 
   public Shape() 
   { 
      name = "undetermined"; 
      area  = 0; 
   } 
   public void computeArea() 
   { 
      
      area  = 0; 
   } 
   public double getArea(){
       return area;
   }
   // methods 
   public void display() 
   { 
      System.out.println("Name: " + name); 
      System.out.println("Area: " + area); 
     
   } 
}