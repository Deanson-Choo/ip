import java.util.Scanner;

public class Jeff {
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Jeff!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.println("____________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }
}
