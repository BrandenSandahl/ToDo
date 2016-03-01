import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by branden on 2/8/16 at 10:57.
 */
public class ToDo {

    static public void insertTodo(Connection conn, String text) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos VALUES (null, ?, false)");
        stmt.setString(1, text);
        stmt.execute();
    }

   static public ArrayList<ToDoItem> selectTodos(Connection conn) throws SQLException {
        ArrayList<ToDoItem> items = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM todos");

        while (results.next()) {
            int id = results.getInt("id");
            String text = results.getString("text");
            boolean isDone = results.getBoolean("is_done");
            items.add(new ToDoItem(id, text, isDone));
        }
        return items;
    }


   static public void toggleTodo(Connection conn, int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE todos SET is_done = NOT is_done WHERE id = ? ");
        statement.setInt(1, id);
        statement.execute();
    }


    public static void main(String[] args) throws SQLException {

       // ArrayList<ToDoItem> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN)");

        while (true) {
            System.out.println("1. Create To-Do Item");
            System.out.println("2. Toggle To-Do Item");
            System.out.println("3. List To-Do Items");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Enter Your To-Do Item: ");
                    String text = scanner.nextLine();

                    insertTodo(conn, text);
                    break;

                case "2":
                    System.out.println("Enter the Number of the Item You Wish to Toggle:");
                    int itemNum = Integer.valueOf(scanner.nextLine());
                    toggleTodo(conn, itemNum);
                    break;


                case "3":
                    ArrayList<ToDoItem> items = selectTodos(conn);
                    for (ToDoItem i : items) {
                        String checkBox = "[ ]";
                        if (i.isDone()) {
                            checkBox = "[x]";
                        }
                        System.out.printf("%s %d. %s\n", checkBox, i.getId() , i.getText());
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