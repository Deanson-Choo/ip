package jeff;


public class AddCommand extends Command {
    private final String taskDetails;
    /**
     * Represents a command that adds a new task to the task manager.
     * <p>
     * This command processes user input, determines the task type,
     * and delegates task creation to {@link TaskManager}. It then saves
     * the updated task list.
     */
    public AddCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager){
        String response = taskManager.addNewItem(taskDetails);
        ui.printWithSeparator(response);
        fileManager.writeToFile(taskManager.getTasks());
    }
}
