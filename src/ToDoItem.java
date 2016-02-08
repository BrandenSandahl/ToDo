/**
 * Created by branden on 2/8/16 at 11:08.
 */
public class ToDoItem {


// Set up Vars
    private String text;
    private boolean isDone;


// Constructors if used
    public ToDoItem(String text, boolean isDone) {
        setText(text);
        setDone(isDone);
    }

    /** Start Getters and Setters */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /** End of Getters and Setters */

}