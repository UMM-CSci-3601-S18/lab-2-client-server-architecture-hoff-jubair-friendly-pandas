package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests umm3601.user.Database filterUsersByAge
 * and listUsers with _age_ query parameters
 */
public class GetToDoByOwner {

  @Test
  public void filterToDosByOwner() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] ownerFryToDos = db.filterToDosByOwner(allToDos, "Fry");
    assertEquals("Incorrect number of todos with name Fry", 61, ownerFryToDos.length);

    ToDo[] ownerBlancheToDos = db.filterToDosByOwner(allToDos, "Blanche");
    assertEquals("Incorrect number of todos with name Blanche", 43, ownerBlancheToDos.length);
  }

}
