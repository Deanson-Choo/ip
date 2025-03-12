package jeff;


public class DeleteCommand extends Command {
    private String[] taskDetails;
    /**
     * Represents a command that deletes a task from the task manager.
     * <p>
     * This command processes user input, identifies the task to delete,
     * and removes it from {@link TaskManager}. It then saves the updated task list.
     */
    public DeleteCommand(String[] taskDetails) {
        this.taskDetails = taskDetails;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager) {
        String response = taskManager.deleteItem(taskDetails);
        ui.printWithSeparator(response);
        fileManager.writeToFile(taskManager.getTasks());
    }
}
