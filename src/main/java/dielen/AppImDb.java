<<<<<<< HEAD
package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


//Adicionar às dependencies do Gradle:
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1' 
// Para poder usar o @Data(que vai tratar o json como dados e vai abstrair: getters,setters, constructor, etc.:
// compileOnly 'org.projectlombok:lombok:1.18.26'
// annotationProcessor 'org.projectlombok:lombok:1.18.26'
public class AppImDb {
    @Data
    //Essa classe foi criada porque o Jackson não tem capacidade de identificar o que ele está vendo. Então, essa classe
    // pega "items" como lista(que será de filmes) e também "errorMessage" - com o qual eu não faço 
    // nada, mas quando você não identifica todas as propriedades no Jackson tem-se um erro.
    public static class  ImDbQuery{
        List<Movie> items;
        String errorMessage;
    }
    @Data
    //@JsonIgnoreProperties ou @JsonIgnore porque não iremos usar todas as propriedades. Do contrário, dá erro.
    @JsonIgnoreProperties(ignoreUnknown= true)
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
            return "Tittle:'" + title +
                    ", Ranking:" + imDbRating + 
                    ", Image:" + image+
                    '\'';
        }
        //Por causa do @Data não preciso fazer mais nada além das propriedades
    }
    public static void main(String[] args) throws IOException, InterruptedException {
       //Pegar os dados do IMDb:
       //- acessar via http(get) os dados da api;e
       //- guardar a resposta numa string
        
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e";
        String json = new CallHttp().extractData("https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy&start_date=2022-12-01&end_date=2022-12-30");

        //Cria uma instância e depois chama ela lendo cada filme e populando na lista.
        
        var objectMapper = new ObjectMapper();//Criada uma instancia
        var stickers = new StickersCreator();
        var imDbQuery = objectMapper.readValue(json,ImDbQuery.class);
        
          
        
        //transforma meu Json numa lista de filmes
        for (Movie movie: imDbQuery.getItems()) {
            String urlImage = movie.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg");
            
            String name = movie.getTitle();
            
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = name +".png";          
            
            stickers.creator(inputStream,fileName, "pictures/");
           
            System.out.println(name);
            System.out.println();
     
        }
 
    }

}
=======
package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


//Adicionar às dependencies do Gradle:
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1' 
// Para poder usar o @Data(que vai tratar o json como dados e vai abstrair: getters,setters, constructor, etc.:
// compileOnly 'org.projectlombok:lombok:1.18.26'
// annotationProcessor 'org.projectlombok:lombok:1.18.26'
public class AppImDb {
    @Data
    //Essa classe foi criada porque o Jackson não tem capacidade de identificar o que ele está vendo. Então, essa classe
    // pega "items" como lista(que será de filmes) e também "errorMessage" - com o qual eu não faço 
    // nada, mas quando você não identifica todas as propriedades no Jackson tem-se um erro.
    public static class  ImDbQuery{
        List<Movie> items;
        String errorMessage;
    }
    @Data
    //@JsonIgnoreProperties ou @JsonIgnore porque não iremos usar todas as propriedades. Do contrário, dá erro.
    @JsonIgnoreProperties(ignoreUnknown= true)
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
            return "Tittle:'" + title +
                    ", Ranking:" + imDbRating + 
                    ", Image:" + image+
                    '\'';
        }
        //Por causa do @Data não preciso fazer mais nada além das propriedades
    }
    public static void main(String[] args) throws IOException, InterruptedException {
       //Pegar os dados do IMDb:
       //- acessar via http(get) os dados da api;e
       //- guardar a resposta numa string
        
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e";
        String json = new CallHttp().extractData("https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy&start_date=2022-12-01&end_date=2022-12-30");

        //Cria uma instância e depois chama ela lendo cada filme e populando na lista.
        
        var objectMapper = new ObjectMapper();//Criada uma instancia
        var stickers = new StickersCreator();
        var imDbQuery = objectMapper.readValue(json,ImDbQuery.class);
        
          
        
        //transforma meu Json numa lista de filmes
        for (Movie movie: imDbQuery.getItems()) {
            String urlImage = movie.getTitle().replaceAll("(@+)(.*).jpg$", "$1.jpg");
            
            String name = movie.getTitle();
            
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = name +".png";          
            
            stickers.creator(inputStream,fileName, "pictures/");
           
            System.out.println(name);
            System.out.println();
     
        }
 
    }

}
>>>>>>> 3f9f22dc416646baa1053c867a56108ecfb62d8d
