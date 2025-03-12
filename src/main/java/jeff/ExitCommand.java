package jeff;

/**
 * This command is used to terminate the program
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui, FileManager fileManager) throws JeffException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
