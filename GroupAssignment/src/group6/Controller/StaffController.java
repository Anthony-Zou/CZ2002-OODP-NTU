package Controller;
import Entity.Staff;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffController {
    Scanner sc = new Scanner(System.in);

    public void printStaffDetails() {
        ArrayList<Staff> Staff = Database_Controller.readStaffList();
        if (Staff != null) {
            System.out.println("Employee Id" + "\t" + " Name" + "\t" + " Gender"+ "\t" + " jobTitle");
            for (int i = 0; i < Staff.size(); i++) {
                System.out.println(Staff.get(i).getEmployeeId() + "           \t" + Staff.get(i).getName() + "\t" + Staff.get(i).getGender()+ "\t" + Staff.get(i).getJobTitle());

            }
        }
    }

    public void addStaff() {
        System.out.println("< Register New Staff >\n");
        
     // Input Exception Handling
        int employeeId = 0;
        do {
        	try {
        		System.out.println("Enter the Employee ID of the Staff (0000):");
        		employeeId = sc.nextInt();
        	} catch (InputMismatchException e) {
        		System.out.println("Please enter a valid ID!");
        		System.out.println("\n-----------------------------------\n");
        	}
        	sc.nextLine(); // clears the buffer
        } while (employeeId <= 0);
        
        if (Database_Controller.getStaffByEmployeeId(employeeId) != null) {
            System.out.println("Staff ID already taken!");
            return;
        } else {
            


         // Input Exception Handling
            String name = "";
            do {
            	try {
            		System.out.println("Name of Staff: ");
            		name = sc.nextLine();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid name!");
            		System.out.println("\n-----------------------------------\n");
            	}
            } while (name == "");
            
            
            
         // Input Exception Handling
            int userChoice = -1;
            do {
            	try {
            		System.out.println("Gender of Staff: ");
            		System.out.println("0. Female");
                    System.out.println("1. Male");
            		userChoice=sc.nextInt();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (userChoice == -1);
            
            
            String gender="";
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
            
         // Input Exception Handling
            userChoice = -1;
            do {
            	try {
            		System.out.println("Job Title of Staff: ");
            		userChoice=sc.nextInt();
            	} catch (InputMismatchException e) {
            		System.out.println("Please enter a valid number!");
            		System.out.println("\n-----------------------------------\n");
            	}
            	sc.nextLine(); // clears the buffer
            } while (userChoice == -1);
            
            String jobTitle ="";
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
