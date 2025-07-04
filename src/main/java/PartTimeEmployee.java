public class PartTimeEmployee extends Employee {
    private int daysWorked;
    private double weeklyPay;

    public PartTimeEmployee(int id, String name, long contactNumber, String address, String password, boolean type,
            int managerId, int daysWorked, double weeklyPay) {
        super(id, name, contactNumber, address, password, type, managerId);
        this.daysWorked = daysWorked;
        this.weeklyPay = weeklyPay;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public double getWeeklyPay() {
        return weeklyPay;
    }

    public void setWeeklyPay(double weeklyPay) {
        this.weeklyPay = weeklyPay;
    }

    public void salaryDetails() {
        System.out.println("Id: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Salary: " + this.daysWorked * this.weeklyPay);
    }

}
