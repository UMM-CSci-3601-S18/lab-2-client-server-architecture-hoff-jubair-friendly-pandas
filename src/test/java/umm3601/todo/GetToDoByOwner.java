package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class GetToDoByOwner {

  // We want to see that there is the correct number of todos by owner, in here we are testing
  // that Fry has 61 todos and Blanche has 43 todos.
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
