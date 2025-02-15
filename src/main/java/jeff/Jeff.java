package jeff;

public class Jeff {
    private TaskManager taskManager = new TaskManager();
    private Reader reader = new Reader(taskManager);

    public void intro() {
        UIHelper.printWithSeparator("Yo! I'm Jeff!", "What can I do for you?");
    }

    public void outro() {
        taskManager.saveContents();
        UIHelper.printWithSeparator("Bye. Hope I see you again soon!");
    }

    public static void main(String[] args){
        Jeff jeff = new Jeff();
        jeff.intro();
        jeff.reader.startScanning();
        jeff.outro();
    }
}
