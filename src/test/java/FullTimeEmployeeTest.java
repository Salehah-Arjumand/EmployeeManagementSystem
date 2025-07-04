import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FullTimeEmployeeTest {
    @Test
    public void testCalculateSalary() {
        Employee employee = new FullTimeEmployee(1, "Jane", 57803456, "Address 1", "jane123", true, 3, 45, 3, 3);
        ((FullTimeEmployee) employee).setBonusPercentage(20);
        ((FullTimeEmployee) employee).calculateSalary();
        assertEquals("", 4.5, ((FullTimeEmployee) employee).getSalary(), 0);
    }

}
