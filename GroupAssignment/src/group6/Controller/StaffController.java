package Controller;
import Entity.Staff;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffController {
    Scanner sc = new Scanner(System.in);

    public void printStaffDetails() {
        ArrayList<Staff> Staff = Database_Controller.readStaffList();
        if (Staff != null) {
            System.out.println("Employee Id" + "\t" + " Name" + "\t" + " Gender"+ "\t" + " jobTitle");
            for (int i = 0; i < Staff.size(); i++) {
                System.out.println(Staff.get(i).getEmployeeId() + "\t\t\t\t\t" + Staff.get(i).getName() + "\t\t\t\t" + Staff.get(i).getGender()+ "\t\t\t\t" + Staff.get(i).getJobTitle());

            }
        }
    }

    public void addStaff() {
        System.out.println("Add a Staff");
        System.out.println("---------------------");
        System.out.println("Enter the Employee Id of the Staff:");
        int employeeId = sc.nextInt();
        if (Database_Controller.getStaffByEmployeeId(employeeId) != null) {
            System.out.println("Staff already exists!");
            return;
        } else {
            int userChoice;
            System.out.println("Name of Staff:");
            String name = sc.next();
            System.out.println("Gender of Staff:");
            System.out.println("0. Female");
            System.out.println("1. Male");
            String gender="";
            userChoice=sc.nextInt();
            do{switch(userChoice)
            {
                case 0:
                    gender="Female";
                    break;
                case 1:
                    gender="Male";
                    break;
                default:
                    System.out.println("Invalid");
                    userChoice=sc.nextInt();
            }
            }while(userChoice <0 && userChoice>1);

            System.out.println("JobTitle of Staff:");
            System.out.println("0. Intern");
            System.out.println("1. Junior");
            System.out.println("2. Senior");
            System.out.println("3. Manager");
            String jobTitle ="";
            userChoice=sc.nextInt();
            do{switch(userChoice)
            {
                case 0:
                    jobTitle="Intern";
                    break;
                case 1:
                    jobTitle="Junior";
                    break;
                case 2:
                    jobTitle="Senior";
                    break;
                case 3:
                    jobTitle="Manager";
                    break;
                default:
                    System.out.println("Invalid");
                    userChoice=sc.nextInt();
            }
            }while(userChoice <0 && userChoice>3);
            Staff newStaff = new Staff(employeeId, name,gender,jobTitle);
            Database_Controller.addStaff(newStaff);

        }
        printStaffDetails();
    }

    public void deleteStaff() {
        System.out.println("Remove a Staff");
        System.out.println("---------------------");
        // find if the Staff is in the database or not //
        System.out.println("Enter the EmployeeId of the Staff:");
        int employeeId = sc.nextInt();
        if (Database_Controller.getStaffByEmployeeId(employeeId) == null) {
            System.out.println("Staff does not exist!");

        } else {
            Database_Controller.deleteStaff(employeeId);// =---- from the database
            System.out.println("Staff removed!");

        }
    }
    //update Staff jobtitle
    public void updateStaff(int employeeId) {

        if (Database_Controller.getStaffByEmployeeId(employeeId) == null) {
            System.out.println("Staff does not exist!");
        } else {
            Staff Staff = Database_Controller.getStaffByEmployeeId(employeeId);
            //content
            System.out.println("Update JobTitle of Staff:");
            System.out.println("0. Intern");
            System.out.println("1. Junior");
            System.out.println("2. Senior");
            System.out.println("3. Manager");
            String jobTitle ="";
            int userChoice=sc.nextInt();
            do{switch(userChoice)
            {
                case 0:
                    jobTitle="Intern";
                    break;
                case 1:
                    jobTitle="Junior";
                    break;
                case 2:
                    jobTitle="Senior";
                    break;
                case 3:
                    jobTitle="Manager";
                    break;
                default:
                    System.out.println("Invalid");
                    userChoice=sc.nextInt();
            }
            }while(userChoice <0 && userChoice>3);
            Staff.setJobTitle(jobTitle);
            Database_Controller.updateStaff(Staff);
        }
    }

    public static void main(String[] args) {
       StaffController Staff= new StaffController();
//    Staff.addStaff();
//    Staff.deleteStaff();
//    Staff.updateStaff(1);
    Staff.printStaffDetails();
    }
}
