package dielen;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CallHttp {
    public String extractData(String url)  {
        // Como tratar a exceção? Colocar "throws IOException, InterruptedException" e lançar pra cima é 
        // ruim porque quem me chama vai precisar lidar com essa exceção. Portanto, tratá-la é mais conveniente.
        try{
            URI address = URI.create(url);
            var httpClient = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
             return response.body();
            
        }catch (IOException | InterruptedException exception){
            throw new RuntimeException(exception);
            
        }
        
        
        
        
    }
}
