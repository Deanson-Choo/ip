package jeff;
import java.io.*;
import java.util.*;

/**
 * Handles reading and writing task data to a persistent file.
 * <p>
 * This class ensures that task data is loaded from and saved to a file (`jeff_data.txt`)
 * to maintain persistence across sessions.
 */
public class FileManager {
    private final File file;
    private final File directory;

    /**
     * Initializes the FileManager by ensuring the required data directory and file exist.
     * If the directory or file is missing, it creates them.
     */
    public FileManager(String filePath) {
        this.directory = new File("data");
        this.file = new File(filePath);

        try {
            // Ensure the directory exists
            if (!directory.exists()) {
                System.out.println("Creating data directory: " + directory.mkdir());
            }
            // Ensure the file exists
            if (!file.exists()) {
                System.out.println("Creating jeff_data.txt: " + file.createNewFile());
            }
        } catch (IOException e) {
            System.err.println("Error initializing file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into a list.
     * <p>
     * Reads each line from the file and converts it into a {@link Task} object.
     *
     * @return A list of {@link Task} objects loaded from the file.
     */
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

    /**
     * Parses a task entry from a formatted text line.
     * @param line A single line of task data from the file.
     * @return A {@link Task} object reconstructed from the file data, or {@code null} if the line is corrupt.
     */
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
            System.err.println("Skipping corrupt line: " + line);
            return null; // Skip corrupted entries
        }
    }

    /**
     * Saves all tasks to the file.
     * <p>
     * Each task is converted into a formatted text representation before being written.
     * @param tasks The list of tasks to be written to the file.
     */
    public void writeToFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(formatTask(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Converts a {@link Task} object into a formatted text string for storage.
     * <p>
     * The format follows:
     * <pre>
     *     T | X | Task Name
     *     D | X | Task Name | BY (TIME)
     *     E | X | Task Name | FROM (TIME) | TO (TIME)
     * </pre>
     *
     * @param task The task to be formatted.
     * @return A string representation of the task for storage.
     */
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
