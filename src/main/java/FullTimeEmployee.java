public class FullTimeEmployee extends Employee {

    private double payRate;
    private int yearsWorked;
    private int holidaysTaken;
    private int holidaysEntitled;
    private double bonusPercentage;
    private double salary;

    public FullTimeEmployee(int id, String name, long contactNumber, String address, String password, boolean type,
            int managerId, double payRate, int yearsWorked, int holidaysTaken) {
        super(id, name, contactNumber, address, password, type, managerId);
        this.payRate = payRate;
        this.yearsWorked = yearsWorked;
        this.holidaysTaken = holidaysTaken;
        if (this.yearsWorked % 3 == 0) {
            this.holidaysEntitled = (20) + 1;
        } else {
            this.holidaysEntitled = 20;
        }
        this.bonusPercentage = 0;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked) {
        this.yearsWorked = yearsWorked;
    }

    public int getHolidaysTaken() {
        return holidaysTaken;
    }

    public void setHolidaysTaken(int holidaysTaken) {
        this.holidaysTaken = holidaysTaken;
    }

    public int getHolidaysEntitled() {
        return holidaysEntitled;
    }

    public void setHolidaysEntitled(int holidaysEntitled) {
        this.holidaysEntitled = holidaysEntitled;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public void display() {
        System.out.println("Id: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Contact Number: " + this.getContactNumber());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Pay Rate: " + this.getPayRate());
        System.out.println("Years Worked: " + this.getYearsWorked());
        System.out.println("Holidays Taken: " + this.getHolidaysTaken());
        System.out.println("Holidays Entitled: " + this.getHolidaysEntitled());

    }

    public void calculateSalary() {
        double calculatedSalary = this.payRate / 12;
        calculatedSalary = calculatedSalary + ((this.bonusPercentage / 100) * calculatedSalary);

        if (this.holidaysTaken > this.holidaysEntitled) {
            calculatedSalary = calculatedSalary - ((this.payRate / 365) * (this.holidaysTaken - this.holidaysEntitled));
        }
        this.salary = calculatedSalary;

    }

    public void salaryDetails() {
        System.out.println("Id: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Salary: " + this.getSalary());
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
