package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TotalNumberOfToDos {

  // There are 300 total todos, so we want to test that that number is correct.
  @Test
  public void totalToDoCount() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());
    assertEquals("Incorrect total number of users", 300, allToDos.length);
  }

}
