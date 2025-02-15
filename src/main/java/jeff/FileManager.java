package jeff;
import java.io.*;
import java.util.*;

public class FileManager {
    private final String filePath = "data/jeff_data.txt";
    private final File file;

    public FileManager() {
        this.file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            UIHelper.printError("Error initializing file: " + e.getMessage());
        }
    }


    /** Load tasks from file */
    public List<Task> loadFileContents() {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                Task task = parseTask(s.nextLine());
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /** Convert saved text format back to Task objects */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("X");
            String name = parts[2];

            switch (type) {
            case "T":
                ToDo todo = new ToDo(name);
                todo.setStatus(isDone);
                return todo;
            case "D":
                String by = parts[3];
                Deadline deadline = new Deadline(name, by);
                deadline.setStatus(isDone);
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Event event = new Event(name, from, to);
                event.setStatus(isDone);
                return event;
            default:
                throw new IOException("Unknown task type: " + line);
            }
        } catch (Exception e) {
            UIHelper.printError("Skipping corrupt line: " + line);
            return null; // Skip corrupted entries
        }
    }

    /** Save all tasks to the file */
    public void writeToFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(formatTask(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            UIHelper.printError("Error writing to file: " + e.getMessage());
        }
    }

    /** Convert Task object into a formatted string */
    private String formatTask(Task task) {
        String type = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";

        if (task instanceof Deadline) {
            return type + " | " + task.getDoneSymbol() + " | " + task.getName() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return type + " | " + task.getDoneSymbol() + " | " + task.getName() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        } else {
            return type + " | " + task.getDoneSymbol() + " | " + task.getName();
        }
    }
}
