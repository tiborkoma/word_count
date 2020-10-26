package wordcount;

public class Application {

    private final InputReader consoleInputReader = new ConsoleInputReader();
    private final WordSplitter wordSplitter = new WordSplitter();

    public void run() {
        System.out.print("Enter text: ");
        var inputText = consoleInputReader.readInput();
        var words = wordSplitter.split(inputText);
        System.out.println("Number of words: " + words.size());
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
