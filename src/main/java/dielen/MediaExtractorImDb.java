<<<<<<< HEAD
package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MediaExtractorImDb implements MediaExtractor{
    @Data
    //Essa classe foi criada porque o Jackson não tem capacidade de identificar o que ele está vendo. Então, essa classe
    // pega "items" como lista(que será de filmes) e também "errorMessage" - com o qual eu não faço 
    // nada, mas quando você não identifica todas as propriedades no Jackson tem-se um erro.
    public static class  ImDbQuery{
        List<AppImDb.Movie> items;
        String errorMessage;
    }
    

    @Data
    @JsonIgnoreProperties(ignoreUnknown= true)
    public static class Media {
        // Todas as propriedades existentes no json devem ser declaradas(contrário: erro)
        String id;
        int rank;
        String title;
        String fullTitle;
        int year;
        String image;
        String crew;
        double imDbRating;
        double imdbCounting;       
        
        
        @Override//Precisa? Já apliquei o filtro no conteúdo
        public String toString() {
            return "Title:'" + title +
                    ", Image:" + image+
                    '\'';
        }
    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public List<Conteudo> extractor(String json) throws IOException {
        
        return objectMapper.readValue(json,ImDbQuery.class).items.stream()
                .map(x->new Conteudo(x.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg"), x.getImage())).
                collect(Collectors.toList());
    }

    @Override
    public String defaultFolderDestination() {
        return "movies";
    }
}
=======
package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MediaExtractorImDb implements MediaExtractor{
    @Data
    //Essa classe foi criada porque o Jackson não tem capacidade de identificar o que ele está vendo. Então, essa classe
    // pega "items" como lista(que será de filmes) e também "errorMessage" - com o qual eu não faço 
    // nada, mas quando você não identifica todas as propriedades no Jackson tem-se um erro.
    public static class  ImDbQuery{
        List<AppImDb.Movie> items;
        String errorMessage;
    }
    

    @Data
    @JsonIgnoreProperties(ignoreUnknown= true)
    public static class Media {
        // Todas as propriedades existentes no json devem ser declaradas(contrário: erro)
        String id;
        int rank;
        String title;
        String fullTitle;
        int year;
        String image;
        String crew;
        double imDbRating;
        double imdbCounting;       
        
        
        @Override//Precisa? Já apliquei o filtro no conteúdo
        public String toString() {
            return "Title:'" + title +
                    ", Image:" + image+
                    '\'';
        }
    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public List<Conteudo> extractor(String json) throws IOException {
        
        return objectMapper.readValue(json,ImDbQuery.class).items.stream()
                .map(x->new Conteudo(x.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg"), x.getImage())).
                collect(Collectors.toList());
    }

    @Override
    public String defaultFolderDestination() {
        return "movies";
    }
}
>>>>>>> 3f9f22dc416646baa1053c867a56108ecfb62d8d
