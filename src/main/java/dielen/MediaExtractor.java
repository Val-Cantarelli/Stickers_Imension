package dielen;

import java.io.IOException;
import java.util.List;

public interface MediaExtractor {
    List<Conteudo> extractor(String json) throws IOException;
    
    String defaultFolderDestination();
}
