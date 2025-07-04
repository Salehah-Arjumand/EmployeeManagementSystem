import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    static Scanner s = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
    static String clerkPassword = "clerk123";

    static int employeeId = 0;
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<HolidayForm> holidays = new ArrayList<HolidayForm>();

    private static void menu() throws IOException {
        System.out.println("------------- Main Menu -------------");
        System.out.println("");
        System.out.println("1. Login as Clerk");
        System.out.println("2. Login as Employee");
        System.out.println("3. Exit");

        System.out.print("Option: ");

        int option = s.nextInt();
        System.out.println("");
        if (option == 1) {
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            if (password.equals(clerkPassword)) {
                System.out.println("");
                clerkMenu();

            } else {
                System.out.println("Invalid password!");
                System.out.println("");
                menu();
            }

        } else if (option == 2) {
            System.out.print("Enter ID: ");
            int id = s.nextInt();

            if (id > employeeId) {
                System.out.println("Employee with given ID does not exist!");
                System.out.println("");
                menu();

            } else {

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).getId() == id && employees.get(i).getPassword().equals(password)) {
                        if (employees.get(i).isType()) {
                            System.out.println("");
                            employeeMenu(id, true);

                        } else {
                            System.out.println("");
                            employeeMenu(id, false);

                        }
                    }
                }
                System.out.println("Invalid ID or password!");
                System.out.println("");
                menu();
            }
        } else if (option == 3) {
            writeEmployees();
            writeHolidays();
            System.out.println("");
            System.out.println("Thank you for using this system!");
            System.exit(0);

        } else {
            System.out.println("Invalid input!");
        }

    }

    private static void employeeMenu(int id, boolean type) throws IOException {
        if (type) {

            System.out.println("------------- Welcome to Employee Menu -------------");
            System.out.println("");
            System.out.println("1. Apply For Leave");
            System.out.println("2. Approve Leave Applications");
            System.out.println("3. Set Bonus");
            System.out.println("4. Back");

            System.out.print("Option: ");

            int option = s.nextInt();
            System.out.println("");

            if (option == 1) {
                applyForLeave(id);
            } else if (option == 2) {
                approveLeaves(id);
            } else if (option == 3) {
                setBonus(id);
            } else if (option == 4) {
                System.out.println("");
                menu();
            } else {
                System.out.println("Invalid input!");
            }
        } else {
            System.out.println("Part-time employees are not entitled for any operations!");
            System.out.println("");
            menu();
        }
    }

    private static void setBonus(int id) throws IOException {

        boolean checkSubOrdinates = false;
        ArrayList<Employee> subordinates = new ArrayList<Employee>();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getManagerId() == id && employees.get(i).isType()) {
                ((FullTimeEmployee) employees.get(i)).display();
                System.out.println("");

                subordinates.add(employees.get(i));
                checkSubOrdinates = true;
            }
        }
        if (checkSubOrdinates) {
            System.out.print("Enter subordinate Id: ");
            int subId = s.nextInt();

            for (int j = 0; j < subordinates.size(); j++) {
                if (subordinates.get(j).getId() == subId) {
                    System.out.print("Enter bonus %: ");
                    Double percentage = s.nextDouble();
                    ((FullTimeEmployee) subordinates.get(j)).setBonusPercentage(percentage);
                    System.out.println("");
                    System.out.println("Bonus % has been set!");
                    System.out.println("");
                    employeeMenu(id, true);
                }
            }
            System.out.println("");

            System.out.println(
                    "Employee with given employee id either does not work under you or is not entitled for bonus!");
            System.out.println("");
            employeeMenu(id, true);
        } else {
            System.out.println("No employee works under you!");
            System.out.println("");
            employeeMenu(id, true);
        }

    }

    private static void approveLeaves(int id) throws IOException {

        boolean checkHolidays = false;
        ArrayList<HolidayForm> holidayApplications = new ArrayList<HolidayForm>();

        for (int i = 0; i < holidays.size(); i++) {
            if (holidays.get(i).getManagerId() == id) {
                holidays.get(i).display();
                System.out.println("");

                holidayApplications.add(holidays.get(i));
                checkHolidays = true;
            }
        }

        if (checkHolidays) {
            boolean checkId = false;
            System.out.print("Enter subordinate Id: ");
            int subId = s.nextInt();

            for (int j = 0; j < holidayApplications.size(); j++) {
                if (holidayApplications.get(j).getEmployeeId() == subId) {
                    checkId = true;
                    System.out.println("Signature: ");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("Option: ");

                    int option = s.nextInt();

                    if (option == 1) {
                        holidays.get(j).setManagerSignature(true);
                        System.out.println("Holiday application has been approved!");
                        System.out.println("");
                        employeeMenu(id, true);
                    } else if (option == 2) {
                        holidays.get(j).setManagerSignature(false);
                        System.out.println("Holiday application has not been approved!");
                        System.out.println("");
                        employeeMenu(id, true);
                    } else {
                        System.out.println("Invalid input!");
                        System.out.println("");
                        employeeMenu(id, true);
                    }

                }
            }
            if (!checkId) {
                System.out.println(
                        "Employee with given employee id either does not work under you or is not part of the company!");
                System.out.println("");
                employeeMenu(id, true);
            }

        } else {
            System.out.println("There are no leave applications for you!");
            System.out.println("");
            employeeMenu(id, true);
        }

    }

    private static void applyForLeave(int id) throws IOException {

        System.out.println("Add the following details for Holiday Application:");
        System.out.print("Duration: ");
        int duration = s.nextInt();

        System.out.println("Signature: ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Option: ");
        boolean signature = false;

        int option = s.nextInt();

        if (option == 1) {
            signature = true;
        } else if (option == 2) {
            signature = false;
        } else {
            System.out.println("Invalid input!");
            System.out.println("");
            employeeMenu(id, true);
        }

        int managerId = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                managerId = employees.get(i).getManagerId();
            }
        }
        if (managerId != 0) {
            holidays.add(new HolidayForm(id, managerId, duration, signature, false));
            System.out.println("You have successfully applied for holidays!");
        } else {
            System.out
                    .println("You are currently not working under any manager who can approve your leave application!");
        }

        System.out.println("");
        employeeMenu(id, true);

    }

    private static void clerkMenu() throws IOException {
        System.out.println("------------- Welcome to Payroll Clerk Menu -------------");
        System.out.println("");
        System.out.println("1. Add Employee");
        System.out.println("2. Check Holiday");
        System.out.println("3. Check Bonus");
        System.out.println("4. Approve Holiday");
        System.out.println("5. Print Weekly Salary Report");
        System.out.println("6. Print Monthly Salary Report");
        System.out.println("7. Back");
        System.out.print("Option: ");

        int option = s.nextInt();
        System.out.println("");
        if (option == 1) {
            addEmployee();
        } else if (option == 4) {
            clerkApproveHoliday();
        } else if (option == 2) {
            checkHoliday();
        } else if (option == 3) {
            checkBonus();
        } else if (option == 5) {
            displayWeeklySalaryReport();
        } else if (option == 6) {
            displayMonthlySalaryReport();
        } else if (option == 7) {
            System.out.println("");
            menu();
        } else {
            System.out.println("Invalid input!");
        }

    }

    private static void displayWeeklySalaryReport() throws IOException {
        System.out.println("------------- Weekly Salary Report ------------- ");
        boolean employeeExists = false;

        for (int i = 0; i < employees.size(); i++) {
            if (!employees.get(i).isType()) {
                System.out.println("");
                employees.get(i).salaryDetails();
                employeeExists = true;

            }
        }
        if (!employeeExists) {
            System.out.println("");
            System.out.println("There is no Part-time employee working in the company!");
        }
        System.out.println("");
        clerkMenu();
    }

    private static void displayMonthlySalaryReport() throws IOException {
        System.out.println("------------- Monthly Salary Report -------------");
        boolean employeeExists = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).isType()) {
                System.out.println("");
                ((FullTimeEmployee) employees.get(i)).calculateSalary();
                ((FullTimeEmployee) employees.get(i)).setBonusPercentage(0);
                employees.get(i).salaryDetails();
                employeeExists = true;

            }
        }
        if (!employeeExists) {
            System.out.println("");
            System.out.println("There is no Full-time employee working in the company!");
        }
        System.out.println("");
        clerkMenu();
    }

    private static void checkBonus() throws IOException {
        System.out.print("Enter Employee Id: ");
        int id = s.nextInt();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                if (employees.get(i).isType()) {
                    System.out.println(
                            "Bonus Percentage: " + ((FullTimeEmployee) employees.get(i)).getBonusPercentage() + "%");

                } else {
                    System.out.println("Part-time employees are not entitled for bonus!");

                }
                System.out.println("");
                clerkMenu();
            }
        }
        System.out.println("Employee with the given ID does not exist!");
        System.out.println("");
        clerkMenu();

    }

    private static void checkHoliday() throws IOException {
        System.out.print("Enter Employee Id: ");
        int id = s.nextInt();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                if (employees.get(i).isType()) {
                    System.out.println(
                            "Entitles Holidays: " + ((FullTimeEmployee) employees.get(i)).getHolidaysEntitled());
                    System.out
                            .println("Holidays Taken: " + ((FullTimeEmployee) employees.get(i)).getHolidaysTaken());
                } else {
                    System.out.println("Part-time employees are not entitled for holidays!");

                }
                System.out.println("");
                clerkMenu();
            }
        }
        System.out.println("Employee with the given ID doesn not exist!");
        System.out.println("");
        clerkMenu();
    }

    private static void clerkApproveHoliday() throws IOException {

        boolean checkHolidays = false;
        ArrayList<HolidayForm> holidayApplications = new ArrayList<HolidayForm>();

        for (int i = 0; i < holidays.size(); i++) {

            holidays.get(i).displayFullDetails();
            System.out.println("");

            holidayApplications.add(holidays.get(i));
            checkHolidays = true;

        }

        if (checkHolidays) {
            System.out.print("Enter Employee Id: ");
            int subId = s.nextInt();
            for (int j = 0; j < holidayApplications.size(); j++) {
                if (holidayApplications.get(j).getEmployeeId() == subId) {

                    System.out.println("Signature: ");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("Option: ");
                    int option = s.nextInt();
                    System.out.println("");
                    if (option == 1) {

                        if (holidayApplications.get(j).isEmployeeSignature() && holidays.get(j).isManagerSignature()) {

                            for (int i = 0; i < employees.size(); i++) {
                                if (holidayApplications.get(j).getEmployeeId() == employees.get(j).getId()) {
                                    ((FullTimeEmployee) employees.get(j))
                                            .setHolidaysTaken(((FullTimeEmployee) employees.get(j)).getHolidaysTaken()
                                                    - holidayApplications.get(i).getDuration());
                                }
                            }

                            System.out.println("Holiday application has been approved!");

                        } else if (holidayApplications.get(j).isEmployeeSignature()
                                && !holidays.get(j).isManagerSignature()) {
                            System.out.println(
                                    "Holiday application can not be approved as the manager has not signed it yet!");

                        } else if (!holidayApplications.get(j).isEmployeeSignature()
                                && holidays.get(j).isManagerSignature()) {
                            System.out.println(
                                    "Holiday application can not be approved as the employee has not signed it yet!");

                        } else if (!holidayApplications.get(j).isEmployeeSignature()
                                && !holidays.get(j).isManagerSignature()) {
                            System.out.println(
                                    "Holiday application can not be approved as the employee and the manager have not signed it yet!");

                        }

                    } else if (option == 2) {
                        System.out.println("Holiday application has not been approved!");
                        System.out.println("");
                        clerkMenu();
                    } else {
                        System.out.println("Invalid input!");

                    }
                    System.out.println("");
                    clerkMenu();
                }

            }
        }

        else {
            System.out.println("No holidays to be approved!");
            System.out.println("");
            clerkMenu();
        }
    }

    private static void addEmployee() throws IOException {

        System.out.println("Add the following details of employee:");

        System.out.print("Manager ID (Enter 0 if no manager is assigned yet): ");
        int managerId = s.nextInt();

        if (managerId != 0 && managerId > employeeId) {
            System.out.println("Invalid Manager ID!");

        } else {

            boolean validManager = false;
            if (managerId == 0) {
                validManager = true;
            } else {
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).getId() == managerId && employees.get(i).isType()) {
                        validManager = true;
                    }
                }
            }
            if (validManager) {

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Contact Number: ");
                long contact = s.nextLong();

                System.out.print("Address: ");
                String address = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                System.out.println("Select Employee Type");
                System.out.println("1. Part-time Employee");
                System.out.println("2. Full-time Employee");
                System.out.print("Type: ");

                int option = s.nextInt();
                if (option == 1) {
                    System.out.print("Days Worked: ");
                    int daysWorked = s.nextInt();
                    if (daysWorked > 7) {
                        System.out.println("That is more than a week!");
                        System.out.println("");
                        clerkMenu();
                    } else {

                        System.out.print("Weekly Pay: ");
                        double weeklyPay = s.nextDouble();
                        employeeId++;

                        employees.add(
                                new PartTimeEmployee(employeeId, name, contact, address, password, false, managerId,
                                        daysWorked, weeklyPay));
                        System.out.println("A new employee with employee ID " + employeeId + " added!");
                    }

                } else if (option == 2) {
                    System.out.print("Pay Rate: ");
                    double payRate = s.nextDouble();

                    System.out.print("Years Worked: ");
                    int yearsWorked = s.nextInt();

                    System.out.print("Holidays Taken: ");
                    int holidaysTaken = s.nextInt();

                    employeeId++;

                    employees
                            .add(new FullTimeEmployee(employeeId, name, contact, address, password, true, managerId,
                                    payRate,
                                    yearsWorked, holidaysTaken));
                    System.out.println("A new employee with employee ID " + employeeId + " added!");

                } else {
                    System.out.println("Invalid type!");
                }
            } else {
                System.out.println("Part-time employee can not be a manager!");
            }

        }
        System.out.println("");
        clerkMenu();

    }

    public static void main(String[] args) throws IOException {
        readEmployees();
        readHolidays();
        menu();
    }

    public static void readEmployees() throws FileNotFoundException {
        Scanner s = new Scanner((Readable) new BufferedReader(new FileReader("src/main/resources/Employees.txt")));

        String info = s.nextLine();

        employeeId = Integer.parseInt(info);

        while (s.hasNext()) {
            info = s.nextLine();
            String[] employeeData = info.split(",");

            int managerId = Integer.parseInt(employeeData[0]);
            int employeeId = Integer.parseInt(employeeData[1]);
            String name = employeeData[2];
            long contact = Long.parseLong(employeeData[3]);
            String address = employeeData[4];
            String password = employeeData[5];

            if (employeeData[6].equals("Part-time")) {
                int daysWorked = Integer.parseInt(employeeData[7]);
                double weeklyPay = Double.parseDouble(employeeData[8]);
                employees.add(new PartTimeEmployee(employeeId, name, contact, address, password, false, managerId,
                        daysWorked, weeklyPay));
            } else if (employeeData[6].equals("Full-time")) {
                double payRate = Double.parseDouble(employeeData[7]);
                int yearsWorked = Integer.parseInt(employeeData[8]);
                int holidaysTaken = Integer.parseInt(employeeData[9]);
                employees.add(new FullTimeEmployee(employeeId, name, contact, address, password, true, managerId,
                        payRate,
                        yearsWorked, holidaysTaken));
            }

        }
    }

    public static void readHolidays() throws FileNotFoundException {

        Scanner s = new Scanner((Readable) new BufferedReader(new FileReader("src/main/resources/HolidayForm.txt")));
        String info;
        while (s.hasNext()) {
            info = s.nextLine();
            String[] holidayData = info.split(",");
            int employeeId = Integer.parseInt(holidayData[0]);
            int managerId = Integer.parseInt(holidayData[1]);
            int duration = Integer.parseInt(holidayData[2]);
            boolean eSignature = Boolean.parseBoolean(holidayData[3]);
            boolean mSignature = Boolean.parseBoolean(holidayData[4]);
            holidays.add(new HolidayForm(employeeId, managerId, duration, eSignature, mSignature));
        }

    }

    public static void writeEmployees() throws IOException {
        FileWriter myWriter = new FileWriter("src/main/resources/Employees.txt", false);
        myWriter.write(Integer.toString(employeeId));
        myWriter.write("\n");
        for (int i = 0; i < employees.size(); i++) {
            myWriter.write(Integer.toString(employees.get(i).getManagerId()) + ",");
            myWriter.write(Integer.toString(employees.get(i).getId()) + ",");
            myWriter.write(employees.get(i).getName() + ",");
            myWriter.write(Long.toString(employees.get(i).getContactNumber()) + ",");
            myWriter.write(employees.get(i).getAddress() + ",");
            myWriter.write(employees.get(i).getPassword() + ",");

            if (employees.get(i).isType()) {
                myWriter.write("Full-time,");
                myWriter.write(Double.toString(((FullTimeEmployee) employees.get(i)).getPayRate()) + ",");
                myWriter.write(Integer.toString(((FullTimeEmployee) employees.get(i)).getYearsWorked()) + ",");
                myWriter.write(Integer.toString(((FullTimeEmployee) employees.get(i)).getHolidaysTaken()) + ",");
                myWriter.write(Integer.toString(((FullTimeEmployee) employees.get(i)).getHolidaysEntitled()));
                myWriter.write("\n");

            } else {
                myWriter.write("Part-time,");
                myWriter.write(Integer.toString(((PartTimeEmployee) employees.get(i)).getDaysWorked()) + ",");
                myWriter.write(Double.toString(((PartTimeEmployee) employees.get(i)).getWeeklyPay()));
                myWriter.write("\n");

            }

        }

        myWriter.close();
    }

    public static void writeHolidays() throws IOException {
        FileWriter myWriter = new FileWriter("src/main/resources/HolidayForm.txt", false);
        for (int i = 0; i < holidays.size(); i++) {
            myWriter.write(Integer.toString(holidays.get(i).getEmployeeId()) + ",");
            myWriter.write(Integer.toString(holidays.get(i).getManagerId()) + ",");
            myWriter.write(Integer.toString(holidays.get(i).getDuration()) + ",");
            myWriter.write(Boolean.toString(holidays.get(i).isEmployeeSignature()) + ",");
            myWriter.write(Boolean.toString(holidays.get(i).isManagerSignature()));
            myWriter.write("\n");
        }
        myWriter.close();
    }

}