package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class GetFirstToDo {

  // Test to see that the first object in the JSON file is found correctly.
  @Test
  public void firstToDoInList() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allUsers = db.listToDos(new HashMap<>());
    ToDo firstToDo = allUsers[0];
    assertEquals("Incorrect owner", "Blanche", firstToDo.owner);
    assertEquals("Incorrect status", false, firstToDo.status);
    assertEquals("Incorrect body", "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.", firstToDo.body);
    assertEquals("Incorrect category", "software design", firstToDo.category);
  }
}
