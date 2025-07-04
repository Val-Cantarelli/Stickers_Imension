package dielen;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaExtractorNasa implements MediaExtractor {

    // A URL da API da NASA será passada via MediaExtractorFactory
    @Override
    public List<Conteudo> extractor(String json) throws IOException {
        // Usando o Jackson para mapear o JSON em objetos
        var objectMapper = new ObjectMapper();
        // A NASA retorna um objeto com "title", "url", "explanation", etc.
        NasaApiResponse response = objectMapper.readValue(json, NasaApiResponse.class);

        List<Conteudo> conteudos = new ArrayList<>();

        // Aqui, transformamos o resultado da NASA (que pode ter várias imagens) em uma lista de Conteudo
        // Para o exemplo, estamos tratando como se a resposta fosse apenas uma imagem, mas você pode
        // expandir para vários dias, etc., dependendo da lógica de seu programa.
        Conteudo conteudo = new Conteudo(response.getTitle(), response.getUrlImage());
        conteudos.add(conteudo);

        return conteudos;
    }

    // Este método pode fornecer o destino padrão para onde os stickers serão salvos
    @Override
    public String defaultFolderDestination() {
        return "pictures";
    }

    // Classe interna que mapeia a resposta da API da NASA
    private static class NasaApiResponse {
        private String title;
        private String url;
        private String explanation;

        public String getTitle() {
            return title;
        }

        public String getUrlImage() {
            return url;
        }

        public String getExplanation() {
            return explanation;
        }
    }
}
