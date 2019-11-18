package app.controller;

import app.model.TodoListItem;

import java.util.HashMap;
import java.util.Map;

public class TodoListHandler {

    private Map<String, TodoListItem> todos = new HashMap();

    /**
     * Adds a todoItem to the map keyed on uuid.
     * @param todo
     */
    public void addTodo(TodoListItem todo) {
        todos.put(todo.getUuid(), todo);
    }

    /**
     * Finds a todoItem in the list and edits the description if a non-empty description is passed in and the priority
     * if a non-zero priority is passed in.
     *
     * @param id
     * @param description
     * @param priority
     */
    public void editTodo(String id, String description, int priority) {
        if (description != null && !description.isEmpty() && description.length() <= 50) {
            todos.get(id).editDescription(description);
        }
        if (priority > 0) {
            todos.get(id).setPriority(priority);
        }
    }

    /**
     * Deletes a todoItem from the collection of items.
     * @param id
     */
    public void deleteTodo(String id) {
        todos.remove(id);
    }

    /**
     * Marks a todoItem as done.
     * @param id
     */
    public void markAsDone(String id) {
        todos.get(id).markDone();
    }

    public int getNumberOfTodoItems() {
        return todos.size();
    }

    public TodoListItem getTodoListItem(String id) {
        return todos.get(id);
    }
}
