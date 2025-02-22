package jeff;

public class DeleteCommand extends Command {
    private String[] taskDetails;
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
