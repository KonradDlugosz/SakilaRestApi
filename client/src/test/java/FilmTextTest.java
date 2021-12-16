import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprata.sakila.entity.FilmText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class FilmTextTest {

    @DisplayName("Gets all the film details from the film Text")
    @Test
    public HttpResponse getFilmText(){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/sakila/film_text")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resq = null;
        try {
            resq = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return  resq;
    }



    @Test
    public void testForFilmID(){
        HttpResponse<String> response =  getFilmText();
        if (response != null){
            String json = response.body();

            ObjectMapper mapper = new ObjectMapper();
            try {
                FilmText filmText = mapper.readValue(json, FilmText.class);
                Assertions.assertEquals(filmText.getId(), 1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }



}
