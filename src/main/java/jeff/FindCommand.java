package jeff;

import java.util.List;

public class FindCommand extends Command {
    private final String taskDetails;

    public FindCommand(String userInput) throws JeffException {
        String[] parts = userInput.split(" ");
        if (parts.length < 2) {
            throw new JeffException("Please enter a valid search query!");
        }
        taskDetails = parts[1];
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager) {
        List<Task> result = taskManager.findItem(taskDetails);
        if (result.isEmpty()) {
            ui.printWithSeparator("No matching tasks found!");
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks:");
            for (int i = 0; i < result.size(); i++) {
                response.append("\n").append(i + 1).append(". ").append(result.get(i));
            }
            ui.printWithSeparator(response.toString());
        }
    }
}