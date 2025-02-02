public class UIHelper {
    private static final String SEPARATOR = "____________________________";

    public static void printWithSeparator(String... messages) {
        System.out.println(SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(SEPARATOR);
    }

    public static void printError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    public static String getSeperator() {
        return SEPARATOR;
    }
}
