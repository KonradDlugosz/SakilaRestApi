
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class FilmTest {

    public static Film film;

    public static HttpResponse<String> getOneFilmConnection() {
        String getOneFilmUrl = "http://localhost:8080/sakila/films/4";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getOneFilmUrl)).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Film getOneFilmJasonBody() {
        String json = getOneFilmConnection().body();
        ObjectMapper mapper = new ObjectMapper();
        Film film = null;
        try {
            film = mapper.readValue(json, Film.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return film;
    }

    @BeforeAll
    public static void getFilms() {
        film = getOneFilmJasonBody();
    }

    @Test
    @DisplayName("Given id 4, return id")
    public void getFilmIdTest() {
        Assertions.assertEquals(4, film.getId());
    }

    @Test
    @DisplayName("Given a title of id 4, return film title")
    public void getFilmTitleTest() {
        Assertions.assertEquals("AFFAIR PREJUDICE", film.getTitle());
    }

    @Test
    @DisplayName("Given a descritpion, return a description")
    public void getDescriptionTest() {
        Assertions.assertEquals("A Fanciful Documentary of a Frisbee And a Lumberjack who must Chase a Monkey in A Shark Tank", film.getDescription());
    }

    @Test
    @DisplayName("Given release year, return year")
    public void getReleaseYearTest() {
        Assertions.assertEquals(2006, film.getReleaseYear());
    }

//    @Test
//    @DisplayName("Given id 4, get language id ")
//    public void getLanguageId(){
//        Assertions.assertEquals(1, film.getLanguage());
//    }

    @Test
    @DisplayName("Given id 4, return rental duration")
    public void getRentalDurationTest() {
        Assertions.assertEquals(5, film.getRentalDuration());
    }

    @Test
    @DisplayName("Given id 4, return rental rate")
    public void getRentalRateTest() {
        Assertions.assertEquals(2.99, film.getRentalRate());
    }

    @Test
    @DisplayName("Given id 4, return length of film")
    public void getLengthTest() {
        Assertions.assertEquals(117, film.getLength());
    }

    @Test
    @DisplayName("Given id 4, return the replacement cost")
    public void getReplacementCostTest() {
        Assertions.assertEquals(26.99, film.getReplacementCost());
    }

    @Test
    @DisplayName("Given id 4, return rating")
    public void getRatingTest() {
        Assertions.assertEquals("G", film.getRating());
    }

    @Test
    @DisplayName("Given id 4, return the special features")
    public void getSpecialFeaturesTest() {
        Assertions.assertEquals("Commentaries,Behind the Scenes", film.getSpecialFeatures());
    }

    @Test
    @DisplayName("Given id 4, return last update")
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
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    @DisplayName("Delete a film")
    public void deleteFilmTest() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("http://localhost:8080/sakila/films/delete/4"))
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


}
