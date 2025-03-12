package jeff;

import java.util.List;

/**
 * Represents a command that lists all tasks in the task manager.
 * <p>
 * This command retrieves all tasks stored in {@link TaskManager}
 * and displays them in a numbered list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager) {
        List<Task> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            ui.printWithSeparator("No tasks found!");
            return;
        }

        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            response.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        ui.printWithSeparator(response.toString());
    }
}