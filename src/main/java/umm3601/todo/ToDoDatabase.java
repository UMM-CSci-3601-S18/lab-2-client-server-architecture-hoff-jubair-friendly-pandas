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

   // Filter status if defined

    if(queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];
      filteredToDos = filterToDosByStatus(filteredToDos, targetStatus);
      return filteredToDos;
    }
    if(queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredToDos = listByLimit(filteredToDos, targetLimit);
      return filteredToDos;

    }
    // Process other query parameters here...
    return null;

  }

  /**
   * Get an array of all the users having the target status.
   *
   * @param todos the list of users to filter by status
   * @param targetStatus the target status to look for
   * @return an array of all the users from the given list that have
   * the target status
   */
  public ToDo[] filterToDosByStatus(ToDo[] todos, String targetStatus) {

    if (targetStatus.equals("complete")) {

      return Arrays.stream(todos).filter(x -> x.status == true).toArray(ToDo[]::new);

    }

    if (targetStatus.equals("incomplete")) {

      return Arrays.stream(todos).filter(x -> x.status == false).toArray(ToDo[]::new);

    }

    return todos;
  }

  public ToDo[] listByLimit(ToDo[] todos, int targetLimit) {
    return Arrays.stream(todos).limit(targetLimit).toArray(ToDo[]::new);
  }

}
