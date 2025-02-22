package jeff;

public class AddCommand extends Command {
    private final String taskDetails;

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
