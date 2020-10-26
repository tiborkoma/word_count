package wordcount;

import java.util.Scanner;

class ConsoleInputReader implements InputReader {

    @Override
    public String readInput() {
        return new Scanner(System.in).nextLine();
    }
}
