import com.sprata.sakila.entity.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sprata.sakila.StaffRequests.*;

public class StaffTest {

    public static Staff getOneStaff = null;
    public static Staff postStaff = null;


    @BeforeAll
    public static void getConnection(){
        getOneStaff = getOneStaffJsonBody();
        postStaff = postOneStaffJsonBody();
    }

    /**
     * Tests for GET by ID
     */

    @Test
    @DisplayName("1.1 Given Id one, Return id")
    public void getStaffId(){
        Assertions.assertEquals(1, getOneStaff.getId());
    }

    @Test
    @DisplayName("1.2 Given Id one, Return first name")
    public void getStaffFirstName(){
        Assertions.assertEquals("Mike", getOneStaff.getFirstName());
    }

    @Test
    @DisplayName("1.3 Given Id one, Return last name")
    public void getStaffLastName(){
        Assertions.assertEquals("Hillyer", getOneStaff.getLastName());
    }

    @Test
    @DisplayName("1.4 Given Id one, Return address")
    public void getStaffAddress(){
        Assertions.assertEquals(3, getOneStaff.getAddress().getId());
    }

    @Test
    @DisplayName("1.5 Given Id one, Return email")
    public void getStaffEmail(){
        Assertions.assertEquals("Mike.Hillyer@sakilastaff.com", getOneStaff.getEmail());
    }

    @Test
    @DisplayName("1.6 Given Id one, Return status")
    public void getStaffActivationStatus(){
        Assertions.assertTrue(getOneStaff.isActive());
    }

    @Test
    @DisplayName("1.7 Given Id one, Return username")
    public void getStaffUsername(){
        Assertions.assertEquals("Mike", getOneStaff.getUsername());
    }

    @Test
    @DisplayName("1.8 Given Id one, Return lastUpdate")
    public void getStaffLastUpdate(){
        Assertions.assertEquals("2006-02-15T03:57:16Z", getOneStaff.getLastUpdate());
    }


    /**
     * Tests for POST request
     */

    @Test
    @DisplayName("2.1 Given body to insert, Return first name")
    public void postStaffName(){
        Assertions.assertEquals("Konrad", postStaff.getFirstName());

    }

}
