import java.util.Scanner;


public class Jeff {

    static final String SEPARATOR = "____________________________";
    private static final Tasks[] tasks = new Tasks[100];
    private static int taskCount = 0;

    public static void printWithSeparator(String... messages) {
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

    public static void Intro() {
        printWithSeparator("Hello! I'm Jeff!", "What can I do for you?");
    }

    public static void Outro() {
        printWithSeparator("Bye. Hope to see you again soon!");
    }

    public static void addNewItem(String item) {
        tasks[taskCount] = new Tasks(item); //Initializing Task List
        printWithSeparator("Added new item: " + item);
        taskCount++;
    }

    public static void updateItemStatus(String[] words) {
        String instruction = words[0];
        if (words.length < 2) { //Error 1
            printWithSeparator("Please input a task number!");
            return;
        }
        int task_index = Integer.parseInt(words[1]) - 1;

        //Error 2
        if (task_index < 0 || task_index >= taskCount) {
            printWithSeparator("Please input a valid task number!");
            return;
        }

        Tasks currTask = tasks[task_index];
        boolean isMark = instruction.equalsIgnoreCase("mark");
        currTask.set_status(isMark); //if mark - set to true, else unmark - set to false

        String status_message = isMark
                ? "Nice! I've marked this task as done:"
                : "Ok, I've marked this task as not done:";
        printWithSeparator(status_message, "[" + currTask.getDoneSymbol() + "] " + currTask.get_name());
    }

    public static void listTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Tasks task = tasks[i];
            System.out.println((i + 1) + ". [" + task.getDoneSymbol() + "] " + task.get_name());
        }
        System.out.println(SEPARATOR);
    }

    public static void Reader() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();

            if ((line.equalsIgnoreCase("bye"))) { //Case 1: Exit Program
                break;
            }
            else if (line.equalsIgnoreCase("list")) { //Case 2: List all items
                listTasks();
            }
            else if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) { //Case 3: Marking items
                updateItemStatus(line.split(" "));
            }
            else {
                addNewItem(line); //Case 4: add items
            }

        }
    }

    public static void main(String[] args) {
        Jeff.Intro();
        Reader();
        Jeff.Outro();
    }
}
