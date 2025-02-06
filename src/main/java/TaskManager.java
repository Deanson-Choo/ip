public class TaskManager {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;


    public void addNewItem(String taskDetails) {
        String[] parts = taskDetails.split(" ", 2);
        if (parts.length < 2) {
            UIHelper.printError("You did not enter task description!");
            return;
        }
        if (taskDetails.startsWith("todo")) {
            String description = parts[1];
            tasks[taskCount] = new ToDo(description);
            System.out.println(tasks[taskCount]);
            taskCount++;
        }
        else if (taskDetails.startsWith("deadline")) {
            String[] taskParts = parts[1].split(" /by ", 2);
            String description = taskParts[0];
            String deadline = taskParts[1];
            tasks[taskCount] = new Deadline(description, deadline);
            System.out.println(tasks[taskCount]);
            taskCount++;
        }
        else if (taskDetails.startsWith("event")){
            String[] fromSplit = parts[1].split(" /from ", 2);
            String[] toSplit = fromSplit[1].split(" /to ", 2);

            String description = fromSplit[0];
            String from = toSplit[0];
            String to = toSplit[1];

            tasks[taskCount] = new Event(description, from, to);
            System.out.println(tasks[taskCount]);
            taskCount++;
        }
        else {
            UIHelper.printError("Unknown command");
        }
    }

    public void updateItemStatus(String[] words) {
        String instruction = words[0];
        if (words.length < 2) { //Error 1
            UIHelper.printWithSeparator("Please input a task number!");
            return;
        }
        int task_index = Integer.parseInt(words[1]) - 1;

        //Error 2
        if (task_index < 0 || task_index >= taskCount) {
            UIHelper.printWithSeparator("Please input a valid task number!");
            return;
        }

        Task currTask = tasks[task_index];
        boolean isMark = instruction.equalsIgnoreCase("mark");
        currTask.setStatus(isMark); //if mark - set to true, else unmark - set to false

        String status_message = isMark
                ? "Nice! I've marked this task as done:"
                : "Ok, I've marked this task as not done:";
        UIHelper.printWithSeparator(status_message + System.lineSeparator() + currTask);
    }

    public void listTasks() {
        System.out.println(UIHelper.getSeparator());
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks[i];
            System.out.print(i+1 + ". ");
            System.out.println(task);
        }
        System.out.println(UIHelper.getSeparator());
    }



}
