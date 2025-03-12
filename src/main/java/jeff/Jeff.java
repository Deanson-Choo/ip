package jeff;

public class Jeff {
    private final TaskManager taskManager;
    private final FileManager fileManager;
    private final Ui ui;

    public Jeff(String filePath) {
        ui = new Ui();
        fileManager = new FileManager(filePath);
        taskManager = new TaskManager(fileManager.loadFileContents());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskManager, ui, fileManager);
                isExit = command.isExit();
            } catch (JeffException e) {
               ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Jeff("data/jeff_data.txt").run();
    }
}
