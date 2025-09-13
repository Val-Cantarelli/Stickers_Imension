package dielen;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

// Add to Gradle dependencies:
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1'
// To use @Data (which treats JSON as data and abstracts: getters, setters, constructor, etc.):
// compileOnly 'org.projectlombok:lombok:1.18.26'
// annotationProcessor 'org.projectlombok:lombok:1.18.26'
public class AppImDb {
    @Data
    // This class was created because Jackson cannot identify what it is seeing.
    // So, this class grabs "items" as a list (which will be movies) and also "errorMessage"
    // - which I don't use, but when you don't identify all properties in Jackson,
    // an error occurs.
    public static class ImDbQuery {
        List<Movie> items;
        String errorMessage;
    }

    @Data
    // @JsonIgnoreProperties or @JsonIgnore because we won't use all properties.
    // Otherwise, it causes an error.
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Movie {

        String fullTitle;
        int year;
        double imDbRating;
        String id;
        int rank;
        String title;
        String image;
        String crew;
        double imdbCounting;

        @Override
        public String toString() {
            return "Title: '" + title +
                    ", Ranking: " + imDbRating +
                    ", Image: " + image +
                    '\'';
        }
        // Due to @Data, I don't need to do anything else other than define the properties.
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Fetch data from IMDb:
        // - access the API via HTTP (GET);
        // - store the response in a string.

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e";
        String json = new CallHttp().extractData("https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy&start_date=2022-12-01&end_date=2022-12-30");

        // Create an instance and call it to read each movie and populate it in the list.

        var objectMapper = new ObjectMapper(); // Create an instance of ObjectMapper
        var stickers = new StickersCreator();
        var imDbQuery = objectMapper.readValue(json, ImDbQuery.class);

        // Convert my JSON into a list of movies
        for (Movie movie : imDbQuery.getItems()) {
            String urlImage = movie.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg");

            String name = movie.getTitle();

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = name + ".png";

            stickers.creator(inputStream, fileName, "pictures/");

            System.out.println(name);
            System.out.println();
        }
    }
}
