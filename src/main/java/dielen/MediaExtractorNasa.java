package dielen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MediaExtractorNasa implements MediaExtractor {
    @Data
    @JsonIgnoreProperties(ignoreUnknown= true)
    public static class Media {
        // Todas as propriedades existentes no json devem ser declaradas(contrário: erro)
        String date;
        String explanation;
        String hdurl;
        String media_type;
        String service_version;
        String title;
        String url;

        @Override//Precisa? Já apliquei o filtro no conteúdo
        public String toString() {
            return "Tittle:'" + title +
                    ", Explanation:" + explanation +
                    ", Image:" + url +
                    '\'';
        }
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Conteudo> extractor(String json) throws IOException {
        /*Vai receber um json, lê-lo com o mapper do Jackson para colocando-o dentro de uma lista de Media.
        * Depois irá stremar com um filtro e criando Conteúdo com title e url que será "acomodado"em uma lista de 
        * Conteudo e devolvendo-a ã quem o chamou. */
        return objectMapper.readValue(json, new TypeReference<List<Media>>(){})
                .stream()
                .filter(x -> x.media_type.equals("image"))
                .map(x -> new Conteudo(x.title.replaceAll("[^\\w\\s]",""), x.url))
                .collect(Collectors.toList());
                
    }

    @Override
    public String defaultFolderDestination() {
        return "pictures";
    }
}
