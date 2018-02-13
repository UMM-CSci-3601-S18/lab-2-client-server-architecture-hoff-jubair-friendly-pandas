package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Tests umm3601.todo.Database listUser functionality
 */
public class TotalNumberOfToDos {

  @Test
  public void totalToDoCount() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());
    assertEquals("Incorrect total number of users", 300, allToDos.length);
  }

}
