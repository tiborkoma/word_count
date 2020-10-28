package wordcount;

import java.nio.file.Paths;

class InputReaderFactory {

    public InputReader getInstance(String filePath) {
        return filePath != null
                ? new FileInputReader(Paths.get(filePath))
                : new ConsoleInputReader();
    }
}
