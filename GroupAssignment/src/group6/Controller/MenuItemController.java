package Controller;
import Entity.*;
import java.util.*;
import Boundary.UserInterface;
public class MenuItemController {
    Scanner sc = new Scanner(System.in);

    public void addMenuItem() {
        System.out.println("Add MenuItem");
        System.out.println("---------------------");
        System.out.println("MenuItem name: ");
        String itemname = sc.nextLine().toUpperCase();

//        if (MenuItemExistsOrNot(itemname)== true){
//            System.out.println("Item already exists! ");
//            return;
//        }
//        else{
//            Course newcourse = new Course();
//            newcourse.setCourseCode(courseCode);
//            System.out.println("Course name:");
//            String courseName = sc.nextLine();
//            newcourse.setCourseName(courseName);
//            System.out.println("AU of the course:");
//            int au = sc.nextInt();
//            newcourse.setAu(au);
//
//            System.out.println("School of the course:");
//            String school = sc.next();
//            newcourse.setschoolName(school);
//            newcourse.setIndexes(new Index[30]);
//            Database_Controller.addCourse(newcourse);
//        }
    }

    public void updateMenuItem() {
    }

    public void deleteMenuItem() {
    }

    public void printMenuItem() {
        System.out.println("Print MenuItem");
        System.out.println("---------------------");
        System.out.println("All available MenuItem:");
        ArrayList<MenuItem> MenuItem = new ArrayList<MenuItem>();
        //MenuItem = Database_Controller.readCourseList();
        for (int i=0;i<MenuItem.size();i++){
            System.out.println(MenuItem.get(i).getItemName()+"\t"+MenuItem.get(i).getDescription()+"\t"+MenuItem.get(i).getPrice());
        }
        sc.close();
    }

}
