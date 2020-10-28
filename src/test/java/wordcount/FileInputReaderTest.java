package wordcount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileInputReaderTest {

    @Test
    public void shouldReadStopWordsFile() throws Exception {
        var fileReader = new FileInputReader(FileInputReader.class.getResource("/stopwords.txt").toURI());

        assertEquals("""
                        the
                        a
                        on
                        off""",
                fileReader.readInput()
        );
    }
}