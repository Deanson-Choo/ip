package jeff;

import java.util.List;

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