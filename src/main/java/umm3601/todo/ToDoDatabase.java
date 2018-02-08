package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class ToDoDatabase {

  private ToDo[] allToDos;

  public ToDoDatabase(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allToDos = gson.fromJson(reader, ToDo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public ToDo getToDo(String id) {
    return Arrays.stream(allToDos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public ToDo[] listToDos(Map<String, String[]> queryParams) {
    ToDo[] filteredToDos = allToDos;

   // Filter age if defined
    if(queryParams.containsKey("status")) {
      boolean targetStatus = Boolean.parseBoolean(queryParams.get("status")[0]);
      filteredToDos = filterToDosByStatus(filteredToDos, targetStatus);
    }
    // Process other query parameters here...

    return filteredToDos;
  }

  /**
   * Get an array of all the users having the target age.
   *
   * @param todos the list of users to filter by age
   * @param targetStatus the target age to look for
   * @return an array of all the users from the given list that have
   * the target age
   */
  public ToDo[] filterToDosByStatus(ToDo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(ToDo[]::new);
  }

}
