package dielen;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

// Add to Gradle dependencies:
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1'
// To use @Data (which treats JSON as data and abstracts: getters, setters, constructor, etc.):
// compileOnly 'org.projectlombok:lombok:1.18.26'
// annotationProcessor 'org.projectlombok:lombok:1.18.26'
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        // String url = "https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy&start_" +
        //        "date=2022-12-01&end_date=2022-12-30";
        String url = "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e";

        // Create an instance of CallHttp and then call the method that puts the file in JSON
        var body = new CallHttp();
        String json = body.extractData(url);

        // Create an instance of mediaExtractor by invoking the extractor method, passing the JSON and receiving a list of contents
        // The call will depend on the URL (NASA or IMDb)
        MediaExtractor mediaExtractor = MediaExtractorFactory.createMediaExtractor(url);
        List<Conteudo> listConteudo = mediaExtractor.extractor(json);
        createStickers(listConteudo, mediaExtractor.defaultFolderDestination());
    }

    private static void createStickers(List<Conteudo> listConteudo, String destFolder) throws IOException {
        var stickers = new StickersCreator();

        // For each element in the content list, I want to generate a sticker and save it in the "pictures" directory
        for (Conteudo conteudos: listConteudo) {
            String urlImage = conteudos.getUrlImage(); // getUrl if it's NASA
            String name = conteudos.getTitle(); // In this case, they have the same name
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = name + ".png";
            stickers.creator(inputStream, fileName, destFolder); // Creates a sticker for each object in the list
            System.out.println(fileName);
            System.out.println();
        }
    }
}
