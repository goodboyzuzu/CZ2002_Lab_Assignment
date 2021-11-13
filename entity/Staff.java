package entity;

public class Staff{

    private String name;
    private char gender;
    private int empId;
    private String jobTitle;

    public Staff(String name, int empId, char gender, String jobTitle) {
        this.empId=empId;
        this.name=name;
        this.gender=gender;
        this.jobTitle=jobTitle;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getEmpId() {
        return empId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
