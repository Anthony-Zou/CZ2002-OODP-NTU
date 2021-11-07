package Entity;
import java.util.Objects;
import java.io.Serializable;
public class Staff implements Serializable{
    //region Variables for Revenue
    /**
     * Variables for Revenue
     */
    private int employeeId;
    private String name;
    private String gender;
    private String jobTitle;
    //endregion

    //region Constructor for Staff

    /**
     * Construct empty Staff object
     */
    public Staff() {
    }

    /**
     * Construct Staff with the following 4 paramenters
     * @param employeeId
     * @param name
     * @param gender
     * @param jobTitle
     */
    public Staff(int employeeId, String name, String gender, String jobTitle) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.jobTitle = jobTitle;
    }
    //endregion

    //region Setter

    /**
     * Set/Update employee object Id
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Set/Update employee object name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set/Update employee object gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Set/Update employee object title
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    //endregion

    //region for getter

    /**
     * return employee object id
     * @return
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * return employee object name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * return employee object gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * return employee object title
     * @return
     */
    public String getJobTitle() {
        return jobTitle;
    }
    //endregion
}
