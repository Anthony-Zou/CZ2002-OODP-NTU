package figure;

public class Shape
{ // fields 
   String name; 
   double area; 
   double volume;
   // constructor 
   public Shape() 
   { 
      name = "undetermined"; 
      area  = 0; 
      volume =0;
   } 
   public void computeArea() 
   { 
      
      area  = 0; 
   } 
   public void computeVolume() 
   { 
      
      volume  = 0; 
   } 
   public double getArea(){
       return area;
   }

   public double getVolume(){
      return volume;
  }
   // methods 
   public void display() 
   { 
      System.out.println("Name: " + name); 
      System.out.println("Area: " + area); 
     
   } 
   public void displayVolume() 
   { 
      System.out.println("Name: " + name); 
      System.out.println("Volume: " + volume); 
     
   } 
}