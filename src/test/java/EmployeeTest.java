import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {
    @Test
    public void testGetAddress() {
        Employee employee = new FullTimeEmployee(1, "Jane", 57803456, "Address 1", "jane123", true, 3, 45, 3, 3);
        employee.setName("Jane");
        assertEquals("Jane", employee.getName());
    }

    @Test
    public void testGetContactNumber() {
        Employee employee = new FullTimeEmployee(1, "Jane", 57803456, "Address 1", "jane123", true, 3, 45, 3, 3);
        assertEquals(57803456, employee.getContactNumber());
    }

    @Test
    public void testGetId() {
        Employee employee = new FullTimeEmployee(1, "Jane", 57803456, "Address 1", "jane123", true, 3, 45, 3, 3);
        assertEquals(1, employee.getId());
    }

}
