package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class GetLimitOfToDos {

  // If we wanted to get only 7 todos from the JSON data, this test will test to see
  // if 7 is the total number of todos taken from the data.
  @Test
  public void limitOfToDos() throws IOException {
    ToDoDatabase db = new ToDoDatabase("src/main/data/todos.json");
    ToDo[] allToDos = db.listToDos(new HashMap<>());

    ToDo[] limitedNum = db.listByLimit(allToDos, 7);
    assertEquals("Incorrect number limit of todos", 7, limitedNum.length);

  }

}
