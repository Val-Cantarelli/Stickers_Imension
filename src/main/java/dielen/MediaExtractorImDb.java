package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MediaExtractorImDb implements MediaExtractor {
    @Data
    // This class was created because Jackson doesn't have the ability to identify what it is seeing.
    // So, this class grabs "items" as a list (which will be movies) and also "errorMessage" -
    // which I don't use, but when you don't identify all properties in Jackson, you get an error.
    public static class ImDbQuery {
        List<AppImDb.Movie> items;
        String errorMessage;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Media {
        // All properties existing in the JSON must be declared (otherwise, it throws an error)
        String id;
        int rank;
        String title;
        String fullTitle;
        int year;
        String image;
        String crew;
        double imDbRating;
        double imdbCounting;

        @Override // Is this needed? I already applied the filter to the content
        public String toString() {
            return "Title:'" + title +
                    ", Image:" + image +
                    '\'';
        }
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Conteudo> extractor(String json) throws IOException {
        return objectMapper.readValue(json, ImDbQuery.class).items.stream()
                .map(x -> new Conteudo(x.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg"), x.getImage()))
                .collect(Collectors.toList());
    }

    @Override
    public String defaultFolderDestination() {
        return "movies";
    }
}
