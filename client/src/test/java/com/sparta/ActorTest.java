package com.sparta;

import com.sprata.sakila.ActorRequests;
import com.sprata.sakila.entity.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sprata.sakila.ActorRequests.*;

public class ActorTest {
    public Actor actor1 = null;

    @Test
    @DisplayName("Get actor when given id")
    public void getActor(){
        actor1 = getOneActors();
        Assertions.assertEquals(1,actor1.getId());
        Assertions.assertEquals("PENELOPE",actor1.getFirstName());
        Assertions.assertEquals("GUINESS",actor1.getLastName());
        Assertions.assertEquals("2006-02-15T04:34:33Z",actor1.getLastUpdate());
    }
/*
    @Test
    @DisplayName("creating a actor")
    public void createNewActor(){
        actor1 = createActor();
        Assertions.assertEquals(210,actor1.getId());
        Assertions.assertEquals("Ria",actor1.getFirstName());
        Assertions.assertEquals("Janani",actor1.getLastName());
        Assertions.assertEquals("2031-02-11T04:34:33Z",actor1.getLastUpdate());
    }


    @Test
    @DisplayName("delete actor")
    public void actorDelete(){

    }



    @Test
    @DisplayName("PUT actor")
    public void updateOrNewActor(){
        actor1 = putActor();
        Assertions.assertEquals(12,actor1.getId());
        Assertions.assertEquals("Hale",actor1.getFirstName());
        Assertions.assertEquals("BERRY",actor1.getLastName());
        Assertions.assertEquals("2021-12-14T04:34:33Z",actor1.getLastUpdate());
    }

    @Test
    @DisplayName("PATCH actor")
    public void patchActor(){
        actor1 = ActorRequests.patchActor();
        Assertions.assertEquals(12,actor1.getId());
        Assertions.assertEquals("sRia",actor1.getFirstName());
        Assertions.assertEquals("BERY",actor1.getLastName());
        Assertions.assertEquals("2021-12-14T04:34:33Z",actor1.getLastUpdate());
    }


*/

}
