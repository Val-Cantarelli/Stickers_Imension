
package dielen;

public class MediaExtractorFactory {
    public static MediaExtractor createMediaExtractor(String url) {
        if(url == null || url.isEmpty()) return null;
        return switch (url) {
            case "https://imdb-api.com/en/API/Top250Movies/k_gazcrb8e" -> new MediaExtractorImDb();
            case "https://api.nasa.gov/planetary/apod?api_key=pxUZVnievX1WK0dwfy4I8mfdDVbFvviIm8LY9Ayy" +
                    "&start_date=2022-12-01&end_date=2022-12-30" -> new MediaExtractorNasa();
            default -> throw new IllegalArgumentException("Unknown url: " + url);
        };
    }
}
