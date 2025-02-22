package jeff;
import java.util.*;

import java.util.ArrayList;

public class TaskManager{
    private List<Task> tasks = new ArrayList<>();
    private int taskCount;

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
        taskCount = tasks.size();
    }

    public String addNewItem(String taskDetails) {
        String[] parts = taskDetails.split(" ", 2);
        //part[0] is the task type, //parts[1] is the details
        if (parts.length < 2) {
            return ("You did not enter a task description");
        }
        Commands command = Commands.valueOf(parts[0].toUpperCase());
        switch (command) {
        case TODO:
            return handleToDo(parts[1]);
        case EVENT:
            return handleEvent(parts[1]);
        case DEADLINE, UNMARK:
            return handleDeadline(parts[1]);
        default:
            return "Error when adding task!";
        }
    }

    public String deleteItem(String[] taskDetails) {
        if (taskDetails.length < 2) { //Error 1: No Task Number
            return("Please input a task number!");
        }

        try {
            int task_index = Integer.parseInt(taskDetails[1]) - 1;
            if (taskCount == 0) { //Error 2: Empty List
                return ("You have to add a task first!");
            }
            if (task_index < 0 || task_index >= taskCount) { //Error 3: Invalid Task Number
                return ("Please input a valid task number!");
            }

            tasks.remove(task_index);
            taskCount--;
            return ("Task deleted!");
        } catch (NumberFormatException e) {
            return ("Invalid task number format! Please enter a number.");
        }
    }


    public String updateItemStatus(String[] words) {
        String instruction = words[0];
        if (words.length < 2) { //Error 1: No Task Number
            return("Please input a task number!");
        }

        int task_index = Integer.parseInt(words[1]) - 1;

        if (taskCount == 0) { //Error 2: Empty List
            return("You have to add a task first!");
        }
        if (task_index < 0 || task_index >= taskCount) { //Error 3: Invalid Task Number
            return("Please input a valid task number!");
        }

        Task currTask = tasks.get(task_index);
        boolean isMark = instruction.equalsIgnoreCase("mark");
        currTask.setStatus(isMark); //if mark - set to true, else unmark - set to false

        String status_message = isMark
                ? "Nice! I've marked this task as done:"
                : "Ok, I've marked this task as not done:";
        return(status_message + System.lineSeparator() + currTask);
    }

    public List<Task> findItem(String taskDetails) {
        String query = taskDetails.trim().toLowerCase(); // Normalize input
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            String taskName = task.getName().toLowerCase(); // Convert task name to lowercase

            if (query.contains(" ")) { // Multi-word phrase search
                if (taskName.contains(query)) {
                    result.add(task);
                }
            } else { // Single keyword search (matches full words only)
                List<String> taskWords = Arrays.asList(taskName.split(" "));
                if (taskWords.contains(query)) {
                    result.add(task);
                }
            }
        }
        return result;
    }



    private String handleToDo(String description) {
        tasks.add(new ToDo(description));
        taskCount++;
        return("I've added: " + tasks.get(taskCount - 1));
    }


    private String handleDeadline (String details) {
        String[] taskParts = details.split(" /BY ", 2);
        if (taskParts.length < 2) {
            return("Please include task description, followed by '/by ' and the timing desired!");
        }
        String description = taskParts[0];
        String deadline = taskParts[1];
        tasks.add(new Deadline(description, deadline));
        taskCount++;
        return ("I've added: " + tasks.get(taskCount - 1));
    }


    private String handleEvent(String details) {
        String[] fromSplit = details.split(" /FROM ", 2);
        if (fromSplit.length < 2) {
            return("Event task must include task description followed by '/from' followed by a start time.");
        }
        String[] toSplit = fromSplit[1].split(" /TO ", 2);
        if (toSplit.length < 2) {
            return("Event task must include '/to' followed by an end time.");
        }
        String description = fromSplit[0];
        String from = toSplit[0];
        String to = toSplit[1];

        tasks.add(new Event(description, from, to));
        taskCount++;
        return("I've added: " + tasks.get(taskCount - 1));
    }

    public List<Task> getTasks() {
        return tasks;
    }

}
