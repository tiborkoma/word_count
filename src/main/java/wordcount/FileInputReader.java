package wordcount;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileInputReader implements InputReader {

    private final Path filePath;

    public FileInputReader(URI resource) {
        this.filePath = Paths.get(resource);
    }

    public FileInputReader(Path path) {
        this.filePath = path;
    }

    @Override
    public String readInput() {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new WordCountException("Unable to open and read file. file=" + filePath, e);
        }
    }
}
