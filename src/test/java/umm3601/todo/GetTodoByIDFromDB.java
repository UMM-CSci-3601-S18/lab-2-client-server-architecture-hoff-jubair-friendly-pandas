package umm3601.todo;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GetTodoByIDFromDB {

  // Test to see if the owner Blanche matches the id.
  @Test
  public void getBlanche() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo todo = db.getToDo("58895985a22c04e761776d54");
    assertEquals("Incorrect name", "Blanche", todo.owner);
  }
}
