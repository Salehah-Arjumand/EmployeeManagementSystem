public class HolidayForm {
    private int employeeId;
    private int managerId;
    private int duration;
    private boolean employeeSignature;
    private boolean managerSignature;

    public HolidayForm(int employeeId, int managerId, int duration, boolean employeeSignature,
            boolean managerSignature) {
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.duration = duration;
        this.employeeSignature = employeeSignature;
        this.managerSignature = managerSignature;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isEmployeeSignature() {
        return employeeSignature;
    }

    public void setEmployeeSignature(boolean employeeSignature) {
        this.employeeSignature = employeeSignature;
    }

    public boolean isManagerSignature() {
        return managerSignature;
    }

    public void setManagerSignature(boolean managerSignature) {
        this.managerSignature = managerSignature;
    }

    public void display() {
        System.out.println("Employee Id: " + this.employeeId);
        System.out.println("Duration: " + this.duration);
    }

    public void displayFullDetails() {
        System.out.println("Employee Id: " + this.employeeId);
        System.out.println("Manager Id: " + this.managerId);
        System.out.println("Duration: " + this.duration);
        System.out.println("Employee Signature: " + this.employeeSignature);
        System.out.println("Manager Signature: " + this.employeeSignature);
    }

}
