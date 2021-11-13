package Controller;
import Entity.Staff;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffController implements Controller{
    //region Scanner
    Scanner sc = new Scanner(System.in);
    //endregion

    /**
     * printStaffDetails Method:
     * The printStaffDetails Method creates an arraylist of Staff objects with values
     * retrieved from Staff.Dat file with the Database_Controller. If the ArrayList is not empty,
     * All entries in the Staff will be printed out with a for loop
     *
     */
    public void print() {
        ArrayList<Staff> Staff = Database_Controller.readStaffList();
        if (Staff != null) {
            System.out.println("Employee Id" + "\t" + " Name" + "\t" + " Gender"+ "\t" + " jobTitle");
            for (int i = 0; i < Staff.size(); i++) {
                System.out.println(Staff.get(i).getEmployeeId() + "\t\t\t\t\t" + Staff.get(i).getName() + "\t\t\t\t" + Staff.get(i).getGender()+ "\t\t\t\t" + Staff.get(i).getJobTitle());

            }
        }
    }

    /**
     * addStaff Method:
     * The addStaff Method will create a Order object with
     * employeeId, name,gender,jobTitle from user
     * Furthermore, the Staff object will be written and save in to the Staff.Dat file
     * With AddStaff method in the Database controller
     */
    public void add() {
        System.out.println("Add a Staff");
        System.out.println("---------------------");
        System.out.println("Enter the Employee Id of the Staff(0000):");
        int employeeId = sc.nextInt();
        if (Database_Controller.getStaffByEmployeeId(employeeId) != null) {
            System.out.println("Staff already exists!");
            return;
        } else {
            int userChoice;
            System.out.println("Name of Staff:");
            String name = sc.nextLine();
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
        print();
    }

    /**
     * deleteStaff method:
     * The deleteStaff Method will request user to input the Staff Id
     * to be deleted. It will first check the existance of the Staff with the
     * getStaffById method from the Database Controller and if the object exist,
     * it will activate the deleteStaff method from the Database Controller with
     * passing in the Staff Id
     */
    public void delete() {
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

    /**
     * updateStaff Method:
     * with passing value of
     * @param employeeId , the method will first check of the object existance
     * with getStaffById  method from the Database Controller
     * If the object exist, User can update the setJobTitle of the Staff
     * object. Finally, the updated object will be passed to the
     * updateStaff method in the database controller to update the Staff.Dat file
     */
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
}
