package com.sparta;

import com.sprata.sakila.entity.Staff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sprata.sakila.StaffRequests.getOneStaffJsonBody;

public class StaffTest {

    public static Staff staff = null;

    @BeforeAll
    public static void getConnection(){
        staff = getOneStaffJsonBody();
    }


    @Test
    @DisplayName("Given Id one, Return id")
    public void getStaffId(){
        Assertions.assertEquals(1, staff.getId());
    }

    @Test
    @DisplayName("Given Id one, Return first name")
    public void getStaffFirstName(){
        Assertions.assertEquals("Mike", staff.getFirstName());
    }

    @Test
    @DisplayName("Given Id one, Return last name")
    public void getStaffLastName(){
        Assertions.assertEquals("Hillyer", staff.getLastName());
    }

    @Test
    @DisplayName("Given Id one, Return address")
    public void getStaffAddress(){
        Assertions.assertEquals(3, staff.getAddress().getId());
    }

    @Test
    @DisplayName("Given Id one, Return email")
    public void getStaffEmail(){
        Assertions.assertEquals("Mike.Hillyer@sakilastaff.com", staff.getEmail());
    }

    @Test
    @DisplayName("Given Id one, Return status")
    public void getStaffActivationStatus(){
        Assertions.assertTrue(staff.isActive());
    }

    @Test
    @DisplayName("Given Id one, Return username")
    public void getStaffUsername(){
        Assertions.assertEquals("Mike", staff.getUsername());
    }

    @Test
    @DisplayName("Given Id one, Return lastUpdate")
    public void getStaffLastUpdate(){
        Assertions.assertEquals("2006-02-15T03:57:16Z", staff.getLastUpdate());
    }

}
