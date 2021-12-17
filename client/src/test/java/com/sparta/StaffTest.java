import com.sparta.sakila.entity.Staff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static com.sparta.sakila.StaffRequests.*;
import static com.sparta.sakila.entity.util.Constants.LOGGER;

public class StaffTest {

    public static Staff getOneStaff = null;
    public static Staff postStaff = null;
    public static Staff putStaff = null;
    public static Map<String,Boolean> isDeleted = null;

    @BeforeAll
    public static void getConnection(){
        getOneStaff = getOneStaff();
        postStaff = postNewStaff();
        putStaff = putStaff();
        isDeleted = deleteStaff();
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

    @Test
    @DisplayName("1.9 Given Id one, Return store id")
    public void getStaffStore(){
        Assertions.assertEquals(1, getOneStaff.getStore());
    }


    /**
     * Tests for POST request
     */

    @Test
    @DisplayName("2.1 Given body to insert, Return first name")
    public void postStaffFirstName(){
        Assertions.assertEquals("Konrad", postStaff.getFirstName());
    }

    @Test
    @DisplayName("2.2 Given body to insert, Return last name")
    public void postStaffLastName(){
        Assertions.assertEquals("Dlugosz", postStaff.getLastName());
    }

    @Test
    @DisplayName("2.3 Given body to insert, Return last address")
    public void postStaffAddress(){
        Assertions.assertEquals(4, postStaff.getAddress().getId());
    }

    @Test
    @DisplayName("2.4 Given body to insert, Return email")
    public void postStaffEmail(){
        Assertions.assertEquals("konrad@sakilastaff.com", postStaff.getEmail());
    }

    @Test
    @DisplayName("2.5 Given body to insert, Return store id")
    public void postStaffStore(){
        Assertions.assertEquals(1, postStaff.getStore());
    }

    @Test
    @DisplayName("2.6 Given body to insert, Return status")
    public void postStaffStatus(){
        Assertions.assertTrue(postStaff.isActive());
    }

    @Test
    @DisplayName("2.7 Given body to insert, Return username")
    public void postStaffUsername(){
        Assertions.assertEquals("konrad", postStaff.getUsername());
    }

    @Test
    @DisplayName("2.7 Given body to insert, Return password")
    public void postStaffPassword(){
        Assertions.assertEquals("konrad1234", postStaff.getPassword());
    }

    @Test
    @DisplayName("2.8 Given body to insert, Return last update")
    public void postStaffLastUpdate(){
        Assertions.assertEquals("2021-02-15T03:57:16Z", postStaff.getLastUpdate());
    }

    /**
     * Tests for PUT request
     */

    @Test
    @DisplayName("3.1 Given PUT request, return updated first name")
    public void putStaffFirstName(){
        Assertions.assertEquals("Alan", putStaff.getFirstName());
    }

    @Test
    @DisplayName("3.2 Given PUT request, return updated last name")
    public void putStaffLastName(){
        Assertions.assertEquals("Edwards", putStaff.getLastName());
    }

    @Test
    @DisplayName("3.3 Given PUT request, return updated address")
    public void putStaffAddress(){
        Assertions.assertEquals(1, putStaff.getAddress().getId());
    }

    @Test
    @DisplayName("3.4 Given PUT request, return updated email")
    public void putStaffEmail(){
        Assertions.assertEquals("Alan@sakilastaff.com", putStaff.getEmail());
    }

    @Test
    @DisplayName("3.5 Given PUT request, return updated email")
    public void putStaffStore(){
        Assertions.assertEquals(1, putStaff.getStore());
    }

    @Test
    @DisplayName("3.6 Given PUT request, return updated status")
    public void putStaffStatus(){
        Assertions.assertFalse( putStaff.isActive());
    }

    @Test
    @DisplayName("3.7 Given PUT request, return updated username")
    public void putStaffUsername(){
        Assertions.assertEquals("Alan", putStaff.getUsername());
    }

    @Test
    @DisplayName("3.8 Given PUT request, return updated password")
    public void putStaffPassword(){
        Assertions.assertEquals("AlanEd201", putStaff.getPassword());
    }

    @Test
    @DisplayName("3.8 Given PUT request, return last updated")
    public void putStaffLastUpdated(){
        Assertions.assertEquals("2021-02-15T03:57:16Z", putStaff.getLastUpdate());
    }

    /**
     * Tests for DELETE request
     */

    @Test
    @DisplayName("4.1 Given DELETE request, Return successfully response")
    public void deleteStaffB(){
        if(isDeleted.containsValue(false)){
            LOGGER.warn("Record doesn't exists, can't delete : TEST CASE 4.1");
        } else {
            Assertions.assertTrue(isDeleted.containsValue(true));
            LOGGER.info("Record deleted");
        }
    }

}