package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Staff implements Serializable{
    private int employeeId;
    private String name;
    private String gender;
    private String jobTitle;

    public Staff() {
    }

    public Staff(int employeeId, String name, String gender, String jobTitle) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.jobTitle = jobTitle;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
