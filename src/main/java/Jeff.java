import java.util.Scanner;



public class Jeff {

    public static void read_and_print() {
        String[] items = new String[100];
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while (true) {
            String line = in.nextLine();

            if (line.equals("bye")) { //Exit Program
                break;
            }
            if (line.equals("list")) { //List items
                System.out.println("____________________________________________");
                for (String item : items) {
                    if (item != null) {
                        System.out.println(item);
                    }
                    else { //Since items are sloted at the front, this means it's a null here
                        break;
                    }
                }
                System.out.println("____________________________________________");
            }
            else { //Add items
                items[counter] = line;
                counter++;
                System.out.println("____________________________________________");
                System.out.println("added: " + items[counter - 1]);
                System.out.println("____________________________________________");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Jeff!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
        read_and_print();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }
}
