package wordcount;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class ResourceFileReader implements InputReader {

    private final String filePath;

    public ResourceFileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String readInput() {
        try {
            var uri = ResourceFileReader.class.getResource(filePath).toURI();
            var path = Paths.get(uri);
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            throw new WordCountException("Unable to open and read file. file=" + filePath, e);
        }
    }
}
