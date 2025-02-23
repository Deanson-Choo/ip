package jeff;

/**
 * Parses user input and converts it into executable commands.
 * <p>
 * This class interprets user input and maps it to the corresponding
 * {@link Command} objects for execution.
 */
public class Parser {
    /**
     * Parses a user input string and returns the appropriate {@link Command}.
     * @param userInput
     * @return {@link Command}
     * @throws JeffException
     */
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
