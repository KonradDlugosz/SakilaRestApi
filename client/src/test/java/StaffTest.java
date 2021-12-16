package java;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;

public class StaffTest {

    @Test
    public void getStaffWithIdOne(){

        String getOneStaffUrl = "http://localhost:8080/sakila/staff/one?id=1";

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getOneStaffUrl)).build();

    }

}
