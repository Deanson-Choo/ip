package jeff;


public class UpdateStatusCommand extends Command {

    private String[] taskDetails;
    /**
     * Represents a command that updates the status of a task.
     * <p>
     * This command handles marking or unmarking tasks as 'done'.
     */
    public UpdateStatusCommand(String[] taskDetails) {
        this.taskDetails = taskDetails;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager){
        String response = taskManager.updateItemStatus(taskDetails);
        ui.printWithSeparator(response);
        fileManager.writeToFile(taskManager.getTasks());
    }

}
