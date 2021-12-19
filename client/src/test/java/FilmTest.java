
import com.sparta.sakila.entity.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static com.sparta.sakila.FilmRequests.*;

public class FilmTest {

    public static Film film;
    public static Film postfilm;
    public static Film putFilm;


    @BeforeAll
    public static void getFilms() {
        film = getOneFilmJasonBody();
        postfilm = postNewFilm();
        putFilm = putNewFilm();
    }


    @Test
    @DisplayName("Given id 5, return id")
    public void getFilmIdTest() {
        Assertions.assertEquals(5, film.getId());
    }

    @Test
    @DisplayName("Given a title of id 5, return film title")
    public void getFilmTitleTest() {
        Assertions.assertEquals("AFRICAN EGG", film.getTitle());
    }

    @Test
    @DisplayName("Given a descritpion, return a description")
    public void getDescriptionTest() {
        Assertions.assertEquals("A Fast-Paced Documentary of a Pastry Chef" +
                        " And a Dentist who must Pursue a Forensic Psychologist in The Gulf of Mexico"
                , film.getDescription());
    }

    @Test
    @DisplayName("Given release year, return year")
    public void getReleaseYearTest() {
        Assertions.assertEquals(2006, film.getReleaseYear());
    }


    @Test
    @DisplayName("Given id 5, return rental duration")
    public void getRentalDurationTest() {
        Assertions.assertEquals(6, film.getRentalDuration());
    }

    @Test
    @DisplayName("Given id 5, return rental rate")
    public void getRentalRateTest() {
        Assertions.assertEquals(2.99, film.getRentalRate());
    }

    @Test
    @DisplayName("Given id 5, return length of film")
    public void getLengthTest() {
        Assertions.assertEquals(130, film.getLength());
    }

    @Test
    @DisplayName("Given id 5, return the replacement cost")
    public void getReplacementCostTest() {
        Assertions.assertEquals(22.99, film.getReplacementCost());
    }

    @Test
    @DisplayName("Given id 5, return rating")
    public void getRatingTest() {
        Assertions.assertEquals("G", film.getRating());
    }

    @Test
    @DisplayName("Given id 5, return the special features")
    public void getSpecialFeaturesTest() {
        Assertions.assertEquals("Deleted Scenes", film.getSpecialFeatures());
    }

    @Test
    @DisplayName("Given id 5, return last update")
    public void getLastUpdateTest() {
        Assertions.assertEquals("2006-02-15T05:03:42Z", film.getLastUpdate());
    }

    @Test
    @DisplayName("Adding a new film")
    public void addNewFilmTest() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/films/add"))
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                            "id": 1001,
                            "title": "This is a test movie",
                            "description": "This is a test description",
                            "releaseYear": 2006,
                            "language": {
                                "id": 1
                            },
                            "originalLanguage": null,
                            "rentalDuration": 4,
                            "rentalRate": 5.99,
                            "length": 112,
                            "replacementCost": 26.99,
                            "rating": "G",
                            "specialFeatures": "Commentaries,Behind the Scenes",
                            "lastUpdate": "2006-02-15T05:03:42Z"
                        }
                        """)).header("content-type", "application/json")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Delete a film")
    public void deleteFilmTest() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/films/delete/3"))
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @DisplayName("Updating a film")
    public void updateFilmTest() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/films/update"))
                .PUT(HttpRequest.BodyPublishers.ofString("""
                        {
                                 "id": 3,
                                 "title": "CATCH ME IF YOU CAN",
                                 "description": "An epic drama with Leonardo Di Caprio",
                                 "releaseYear": 2006,
                                 "language": {
                                     "id": 1
                                 },
                                 "originalLanguage": null,
                                 "rentalDuration": 3,
                                 "rentalRate": 0.99,
                                 "length": 115,
                                 "replacementCost": 22.99,
                                 "rating": "PG",
                                 "specialFeatures": "Deleted Scenes,Behind the Scenes",
                                 "lastUpdate": "2006-02-15T05:03:42Z"
                             }
                        """)).header("content-type", "application/json")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(200, response.statusCode());
    }


}
