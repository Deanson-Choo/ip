import java.util.Scanner;

    public class Reader {
        private final Scanner scanner;
        private final TaskManager taskManager;

        public Reader(TaskManager taskManager) {
            this.scanner = new Scanner(System.in);
            this.taskManager = taskManager;
        }

        public void startScanning() {
            while (true) {
                String line = scanner.nextLine();
               if ((line.equalsIgnoreCase("bye"))) { //Case 1: Exit Program
                    break;
                }
                else if (line.equalsIgnoreCase("list")) { //Case 2: List all items
                    taskManager.listTasks();
                }
                else if (line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark")) { //Case 3: Marking items
                    taskManager.updateItemStatus(line.split(" "));
                }
                else {
                    taskManager.addNewItem(line); //Case 4: add items
                }
            }
        }
    }
