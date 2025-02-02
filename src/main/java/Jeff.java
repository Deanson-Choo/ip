import java.util.Scanner;


public class Jeff {
    private static final String SEPARATOR = "____________________________";
    private TaskManager taskManager = new TaskManager();
    private Reader reader = new Reader(taskManager);

    static void printWithSeparator(String... messages) {
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

    public void intro() {
        printWithSeparator("Hello! I'm Jeff!", "What can I do for you?");
    }

    public void outro() {
        printWithSeparator("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Jeff jeff = new Jeff();
        jeff.intro();
        jeff.reader.startScanning();
        jeff.outro();
    }
}
