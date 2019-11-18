package app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TodoListItem {

    private String description;
    private Date dateCreated;
    private int priority;
    private boolean done;

    private String uuid;

    public TodoListItem(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.done = false;
        this.dateCreated = Calendar.getInstance().getTime();
        this.uuid = UUID.randomUUID().toString();
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void markDone() {
        setDone(true);
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getPriority() {
        return priority;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isDone() {
        return done;
    }

    private void setDone(boolean done) {
        this.done = done;
    }
}
