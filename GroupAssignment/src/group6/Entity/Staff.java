package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Staff implements Serializable{
    private String name;
    private String gender;
    private String employeeId;
    private String jobTitle;
    private String staffId;

    public Staff() {
    }

    public Staff(String staffId, String name, String gender, String employeeId, String jobTitle) {
        this.name = name;
        this.gender = gender;
        this.employeeId = employeeId;
        this.jobTitle = jobTitle;
        this.staffId = staffId;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(name, staff.name) && Objects.equals(gender, staff.gender) && Objects.equals(employeeId, staff.employeeId) && Objects.equals(jobTitle, staff.jobTitle) && Objects.equals(staffId, staff.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, employeeId, jobTitle, staffId);
    }
}
