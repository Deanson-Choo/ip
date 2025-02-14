package jeff;
import java.util.*;

public class TaskManager{
    private final ArrayList<Task> tasks= new ArrayList<>();
    private int taskCount = 0;


    public void addNewItem(String taskDetails) throws JeffException {
        String[] parts = taskDetails.split(" ", 2);
        //part[0] is the task type, //parts[1] is the details
        if (parts.length < 2) {
            throw new JeffException("You did not enter a task description");
        }

        if (taskDetails.startsWith("TODO")) {
            handleToDo(parts[1]);
        } else if (taskDetails.startsWith("DEADLINE")) {
            handleDeadline(parts[1]);
        } else if (taskDetails.startsWith("EVENT")) {
            handleEvent(parts[1]);
        }
        else {
            throw new JeffException("Error when adding item");
        }
    }

    public void deleteItem(String[] taskDetails) {
        if (taskDetails.length < 2) { //Error 1: No Task Number
            UIHelper.printError("Please input a task number!");
            return;
        }

        int task_index = Integer.parseInt(taskDetails[1]) - 1;

        if (taskCount == 0) { //Error 2: Empty List
            UIHelper.printError("You have to add a task first!");
            return;
        }
        if (task_index < 0 || task_index >= taskCount) { //Error 3: Invalid Task Number
            UIHelper.printError("Please input a valid task number!");
            return;
        }

        UIHelper.printWithSeparator("Task Deleted!");
        tasks.remove(task_index);
        taskCount--;
    }


    public void updateItemStatus(String[] words) {
        String instruction = words[0];
        if (words.length < 2) { //Error 1: No Task Number
            UIHelper.printError("Please input a task number!");
            return;
        }

        int task_index = Integer.parseInt(words[1]) - 1;

        if (taskCount == 0) { //Error 2: Empty List
            UIHelper.printError("You have to add a task first!");
            return;
        }
        if (task_index < 0 || task_index >= taskCount) { //Error 3: Invalid Task Number
            UIHelper.printError("Please input a valid task number!");
            return;
        }

        Task currTask = tasks.get(task_index);
        boolean isMark = instruction.equalsIgnoreCase("mark");
        currTask.setStatus(isMark); //if mark - set to true, else unmark - set to false

        String status_message = isMark
                ? "Nice! I've marked this task as done:"
                : "Ok, I've marked this task as not done:";
        UIHelper.printWithSeparator(status_message + System.lineSeparator() + currTask);
    }


    public void listTasks() {
        if (taskCount == 0) {
            UIHelper.printWithSeparator("No tasks found!");
            return;
        }
        System.out.println(UIHelper.getSeparator());
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            System.out.print(i+1 + ". ");
            System.out.println(task);
        }
        System.out.println(UIHelper.getSeparator());
    }


    private void handleToDo(String description) {
        tasks.add(new ToDo(description));
        System.out.println(tasks.get(taskCount));
        taskCount++;
    }


    private void handleDeadline (String details) throws JeffException{
        String[] taskParts = details.split(" /BY ", 2);
        if (taskParts.length < 2) {
            throw new JeffException("Please include task description, followed by '/by ' and the timing desired!");
        }
        String description = taskParts[0];
        String deadline = taskParts[1];
        tasks.add(new Deadline(description, deadline));
        System.out.println(tasks.get(taskCount));
        taskCount++;
    }


    private void handleEvent(String details) throws JeffException {
        String[] fromSplit = details.split(" /FROM ", 2);
        if (fromSplit.length < 2) {
            throw new JeffException("Event task must include task description followed by '/from' followed by a start time.");
        }
        String[] toSplit = fromSplit[1].split(" /TO ", 2);
        if (toSplit.length < 2) {
            throw new JeffException("Event task must include '/to' followed by an end time.");
        }
        String description = fromSplit[0];
        String from = toSplit[0];
        String to = toSplit[1];

        tasks.add(new Event(description, from, to));
        System.out.println(tasks.get(taskCount));
        taskCount++;
    }

}
