package jeff;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, FileManager fileManager) throws JeffException;
    public boolean isExit() { return false; }
}
