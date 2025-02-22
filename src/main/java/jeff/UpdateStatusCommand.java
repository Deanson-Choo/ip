package jeff;

public class UpdateStatusCommand extends Command {

    private String[] taskDetails;
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
