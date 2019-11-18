package controller;


import app.controller.TodoListHandler;
import app.model.TodoListItem;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListHandlerTest {

    private static final TodoListItem TODO_LIST_ITEM_1 = new TodoListItem("Study for quiz", 2);
    private static final TodoListItem TODO_LIST_ITEM_2 = new TodoListItem("Turn in homework", 1);
    private static final TodoListItem TODO_LIST_ITEM_3 = new TodoListItem("Write unit tests", 2);
    private static final TodoListItem TODO_LIST_ITEM_4 = new TodoListItem("Do laundry", 2);


    private TodoListHandler todoListHandler;

    @BeforeEach
    public void setUp() {
        todoListHandler = new TodoListHandler();
        todoListHandler.addTodo(TODO_LIST_ITEM_1);
        todoListHandler.addTodo(TODO_LIST_ITEM_2);
        todoListHandler.addTodo(TODO_LIST_ITEM_3);
        todoListHandler.addTodo(TODO_LIST_ITEM_4);
    }


    @Test
    public void addTodo_ShouldSucceed() {
        assertEquals(4, todoListHandler.getNumberOfTodoItems());
        assertEquals(TODO_LIST_ITEM_1, todoListHandler.getTodoListItem(TODO_LIST_ITEM_1.getUuid()));
    }

    @Test
    public void addTodo_AlreadyExists() {
        todoListHandler.addTodo(TODO_LIST_ITEM_1);
        assertEquals(4, todoListHandler.getNumberOfTodoItems());
    }

    @Test
    public void editTodoDescription_Empty_ShouldNotSucceed() {
        String newDescription = "";
        String oldDescription = TODO_LIST_ITEM_1.getDescription();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), newDescription, 1);
        assertNotEquals(newDescription, TODO_LIST_ITEM_1.getDescription());
        assertEquals(oldDescription, TODO_LIST_ITEM_1.getDescription());
    }

    @Test
    public void editTodoDescription_Null_ShouldNotSucceed() {
        String newDescription = null;
        String oldDescription = TODO_LIST_ITEM_1.getDescription();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), newDescription, TODO_LIST_ITEM_1.getPriority());
        assertNotNull(TODO_LIST_ITEM_1.getDescription());
        assertEquals(oldDescription, TODO_LIST_ITEM_1.getDescription());
    }

    @Test
    public void editTodoDescription_Normal_ShouldSucceed() {
        String newDescription = "Create test cases";
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), newDescription, TODO_LIST_ITEM_1.getPriority());
        assertEquals(newDescription, TODO_LIST_ITEM_1.getDescription());
    }

    @Test
    public void editTodoDescription_LongerThanMax_ShouldNotSucceed() {
        String newDescription = "Create test cases and then test oracles, cover all input";
        String oldDescription = TODO_LIST_ITEM_1.getDescription();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), newDescription, TODO_LIST_ITEM_1.getPriority());
        assertNotEquals(newDescription, TODO_LIST_ITEM_1.getDescription());
        assertEquals(oldDescription, TODO_LIST_ITEM_1.getDescription());
    }

    @Test
    public void editTodoDescription_EqualToMax_ShouldSucceed() {
        String newDescription = "Create test cases and then test oracles, cover all";
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), newDescription, TODO_LIST_ITEM_1.getPriority());
        assertEquals(newDescription, TODO_LIST_ITEM_1.getDescription());
    }

    @Test
    public void editTodoPriority_LessThan1_ShouldNotSucceed() {
        int newPriority = -1;
        int oldPriority = TODO_LIST_ITEM_1.getPriority();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), null, newPriority);
        assertEquals(oldPriority, TODO_LIST_ITEM_1.getPriority());
        assertNotEquals(newPriority, TODO_LIST_ITEM_1.getPriority());
    }

    @Test
    public void editTodoPriority_EqualsZero_ShouldNotSucceed() {
        int newPriority = 0;
        int oldPriority = TODO_LIST_ITEM_1.getPriority();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), null, newPriority);
        assertEquals(oldPriority, TODO_LIST_ITEM_1.getPriority());
        assertNotEquals(newPriority, TODO_LIST_ITEM_1.getPriority());
    }

    @Test
    public void editTodoPriority_EqualsOne_ShouldNotSucceed() {
        int newPriority = 1;
        int oldPriority = TODO_LIST_ITEM_1.getPriority();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), null, newPriority);
        assertEquals(newPriority, TODO_LIST_ITEM_1.getPriority());
        assertNotEquals(oldPriority, TODO_LIST_ITEM_1.getPriority());
    }

    @Test
    public void editTodoPriority_Normal_ShouldNotSucceed() {
        int newPriority = 3;
        int oldPriority = TODO_LIST_ITEM_1.getPriority();
        todoListHandler.editTodo(TODO_LIST_ITEM_1.getUuid(), null, newPriority);
        assertEquals(newPriority, TODO_LIST_ITEM_1.getPriority());
        assertNotEquals(oldPriority, TODO_LIST_ITEM_1.getPriority());
    }

    @Test
    public void deleteTodo_shouldSucceed() {
        todoListHandler.deleteTodo(TODO_LIST_ITEM_1.getUuid());
        assertEquals(3, todoListHandler.getNumberOfTodoItems());
    }

    @Test
    public void deleteTodo_doesNotExist() {
        todoListHandler.deleteTodo("myTodoItem");
        assertEquals(4, todoListHandler.getNumberOfTodoItems());
    }

    @Test
    public void markAsDone_shouldSucceed() {
        todoListHandler.markAsDone(TODO_LIST_ITEM_1.getUuid());
        assertEquals(true, todoListHandler.getTodoListItem(TODO_LIST_ITEM_1.getUuid()).isDone());
    }
}
