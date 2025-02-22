package jeff;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from  + " to: " + to + ")";
    }

    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
}
