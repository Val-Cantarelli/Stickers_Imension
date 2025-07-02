package dielen;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

//Adicionar às dependencies do Gradle:
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1' 
// Para poder usar o @Data(que vai tratar o json como dados e vai abstrair: getters,setters, constructor, etc.:
// compileOnly 'org.projectlombok:lombok:1.18.26'
// annotationProcessor 'org.projectlombok:lombok:1.18.26'
public class App {

    public static void main(String[] args) throws IOException, InterruptedException {      
        //String url = "https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy&start_" +
        //        "date=2022-12-01&end_date=2022-12-30";
        String url = "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e";
        
        // Cria uma instancia de CallHttp e depois chama o método que coloca o arquivo no json
        var body = new CallHttp();
        String json = body.extractData(url);
        
        //Cria uma instancia de mediaExtractor invocando o método extractor passando o jason e recebendo uma lista de conteúdos
        // A chamada vai depender da url (nasa ou imdb)
        MediaExtractor mediaExtractor = MediaExtractorFactory.createMediaExtractor(url);
        List<Conteudo> listConteudo = mediaExtractor.extractor(json);
        createStickers(listConteudo, mediaExtractor.defaultFolderDestination());
    }

    private static void createStickers(List<Conteudo> listConteudo, String destFolder) throws IOException {
        var stickers = new StickersCreator();

        // Para cada elemento da lista de conteudos eu quero gerar um sticker e salvá-lo no dir pictures
        for (Conteudo conteudos: listConteudo) {
            String urlImage = conteudos.getUrlImage();//getUrl se for nasa
            String name = conteudos.getTitle();//por acaso são o mesmo nome
            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = name +".png";
            stickers.creator(inputStream,fileName, destFolder);//cria um sticker para cada objeto da lista
            System.out.println(fileName);
            System.out.println();
        }
    }
}
