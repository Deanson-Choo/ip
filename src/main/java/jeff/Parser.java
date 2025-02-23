package jeff;

public class Parser {
    public static Command parse(String userInput) throws JeffException {
        String[] parts = userInput.split(" ", 2);
        String commandType = parts[0].toUpperCase();
        try {
            Commands command = Commands.valueOf(commandType); // Use Enum instead of string
            switch (command) {
            case BYE: return new ExitCommand();
            case LIST: return new ListCommand();
            case MARK, UNMARK: return new UpdateStatusCommand(parts);
            case TODO, DEADLINE, EVENT: return new AddCommand(userInput);
            case DELETE: return new DeleteCommand(parts);
            case FIND: return new FindCommand(userInput);
            default: throw new JeffException("Unknown command: " + commandType);
            }
        } catch (IllegalArgumentException e) {
            throw new JeffException("Invalid command: " + commandType);
        }
    }
}
