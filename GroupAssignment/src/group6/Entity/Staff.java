package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Staff implements Serializable{
    String name;
    String gender;
    String employeeId;
    String jobTitle;

    public Staff(String name, String gender, String employeeId, String jobTitle) {
        this.name = name;
        this.gender = gender;
        this.employeeId = employeeId;
        this.jobTitle = jobTitle;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(name, staff.name) && Objects.equals(gender, staff.gender) && Objects.equals(employeeId, staff.employeeId) && Objects.equals(jobTitle, staff.jobTitle);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
