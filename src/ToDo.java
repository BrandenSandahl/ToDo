import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by branden on 2/8/16 at 10:57.
 */
public class ToDo {

    public static void main(String[] args) {

        ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create To-Do Item");
            System.out.println("2. Toggle To-Do Item");
            System.out.println("3. List To-Do Items");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter Your To-Do Item: ");
                    String text = scanner.nextLine();

                    items.add(new ToDoItem(text, false));
                    break;

                case "2":
                    System.out.println("Enter the Number of the Item You Wish to Toggle:");
                    int itemNum = Integer.valueOf(scanner.nextLine());
                    ToDoItem item = items.get(itemNum - 1);
                    item.setDone(!item.isDone());
                    break;


                case "3":
                    int g = 1;
                    for (ToDoItem i : items) {
                        String checkBox = "[ ] ";
                        if (i.isDone()) {
                            checkBox = "[x] ";
                        }
                        System.out.println(checkBox + g + ". " + i.getText());
                        g++;
                    }
                    System.out.println("");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

        }

    }


}