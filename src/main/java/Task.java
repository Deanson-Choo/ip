public class Task {
    protected String name = null;
    boolean done;


    public Task(String name) {
        this.name = name;
        this.done = false; // Default to not done
    }

    public String getName() {
        return name;
    }
    public boolean getStatus() {
        return done;
    }
    public void setStatus(boolean done) {
        this.done = done;
    }
    public String getDoneSymbol() {
        return done ? "X": "";
    }

    public String toString() {
        return "[" + getDoneSymbol() + "] " + getName();
    }
}
