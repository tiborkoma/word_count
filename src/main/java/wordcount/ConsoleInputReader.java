package wordcount;

import java.util.Scanner;

class ConsoleInputReader implements InputReader {

    @Override
    public String readInput() {
        System.out.print("Enter text: ");
        return new Scanner(System.in).nextLine();
    }
}
