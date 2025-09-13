
---

[![Alura](https://www.alura.com.br/assets/img/alura-share.1647533644.png)](https://www.alura.com.br/)

---
# Alura Stickers

Generate custom stickers from images and movie APIs!

## Description
This Java project consumes public APIs (such as IMDb and NASA), extracts media information (movies, images), and generates custom stickers from these images. The stickers are saved in the `data/images/output` folder.

## Features
- Consumes movie (IMDb) and image (NASA) APIs
- Extracts relevant data (title, image, etc.)
- Generates stickers from the extracted images
- Saves stickers to disk

## Project Structure
```
├── src/main/java/dielen/        # Main source code
│   ├── App.java                 # Main class
│   ├── AppImDb.java             # IMDb API app
│   ├── CallHttp.java            # HTTP client
│   ├── Conteudo.java            # Content model
│   ├── MediaExtractor.java      # Extraction interface
│   ├── MediaExtractorFactory.java
│   ├── MediaExtractorImDb.java  # IMDb extractor
│   ├── MediaExtractorNasa.java  # NASA extractor
│   └── StickersCreator.java     # Sticker generator
├── data/images/input/           # Input images
├── data/images/output/          # Generated stickers
├── build.gradle                 # Gradle configuration
```

## How to Run
1. **Prerequisites:**
   - Java 17+
   - Gradle (or use the included wrapper)

2. **Build the project:**
   ```sh
   ./gradlew build
   ```

3. **Run the project:**
   ```sh
   ./gradlew run
   ```
   Or run the main class directly:
   ```sh
   java -cp build/libs/alura-stickers.jar dielen.App
   ```

4. **Output:**
   Stickers will be generated in `data/images/output/`.

## Example Usage
After running the project, check the generated stickers in the `data/images/output/` folder, such as:
- The Shawshank Redemption.png
- The Godfather.png
- ...

## Credits
Project based on [Alura](https://www.alura.com.br/) courses.

## License
Educational use only.
