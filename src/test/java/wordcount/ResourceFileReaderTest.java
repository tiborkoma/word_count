package wordcount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceFileReaderTest {

    @Test
    public void shouldReadStopWordsFile() {
        var fileReader = new ResourceFileReader("/stopwords.txt");
        assertEquals("""
                        the
                        a
                        on
                        off""",
                fileReader.readInput()
        );
    }
}