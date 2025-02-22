package jeff;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "____________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printWithSeparator("Yo! I'm Jeff!", "What can I do for you?");
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public void showError(String errorMessage) {
        printWithSeparator("ERROR: " + errorMessage);
    }

    public void printWithSeparator(String... messages) {
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

    public void showLine() {
        System.out.println(SEPARATOR);
    }

    public void showGoodbye() {
        printWithSeparator("Bye. Hope I see you again soon!");
    }
}
