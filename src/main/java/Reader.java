import java.util.Scanner;

    public class Reader {
        private final Scanner scanner;
        private final TaskManager taskManager;

        public Reader(TaskManager taskManager) {
            this.scanner = new Scanner(System.in);
            this.taskManager = taskManager;
        }

        private boolean isExitCommand(String line) {
            return line.equalsIgnoreCase("bye");
        }

        private boolean isListCommand(String line) {
            return line.equalsIgnoreCase("list");
        }

        private boolean isMarkOrUnmarkCommand(String line) {
            return line.toLowerCase().startsWith("mark") || line.toLowerCase().startsWith("unmark");
        }

        private boolean isTaskCommand(String line) {
            return line.toLowerCase().startsWith("todo") ||
                    line.toLowerCase().startsWith("deadline") ||
                    line.toLowerCase().startsWith("event");
        }


        public void startScanning() {

            while (true) {
                try {
                    String line = scanner.nextLine().trim();

                    if (line.isEmpty()) {
                        UIHelper.printError("No command entered! Please type a valid command.");
                        continue;
                    }

                    if (isExitCommand(line)) {
                        break;
                    } else if (isListCommand(line)) {
                        taskManager.listTasks();
                    } else if (isMarkOrUnmarkCommand(line)) {
                        taskManager.updateItemStatus(line.split(" "));
                    } else if (isTaskCommand(line)) {
                        taskManager.addNewItem(line);
                    } else {
                        UIHelper.printError("Unknown command! Please enter a valid command");
                    }

                } catch (JeffException e) {
                    UIHelper.printError(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                }
            }
        }
    }
