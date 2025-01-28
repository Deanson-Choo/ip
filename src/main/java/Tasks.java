public class Tasks {
    private String name = null;
    boolean done = false;

    public Tasks(String name) {
        this.name = name;
        this.done = false; // Default to not done
    }

    public String get_name() {
        return name;
    }
    public boolean get_status() {
        return done;
    }
    public void set_status(boolean done) {
        this.done = done;
    }
    public String getDoneSymbol() {
        return done ? "X": "";
    }
}
