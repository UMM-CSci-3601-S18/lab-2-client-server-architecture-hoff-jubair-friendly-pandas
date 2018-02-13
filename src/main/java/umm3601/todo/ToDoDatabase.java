package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * A fake "database" of todo info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of todo data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `ToDoController` to "query" the "database".
 */

public class ToDoDatabase {

  private ToDo[] allToDos;

  public ToDoDatabase(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allToDos = gson.fromJson(reader, ToDo[].class);
  }

  /**
   * Get the single todo specified by the given ID. Return
   * `null` if there is no todo with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo
   * with that ID
   */

  public ToDo getToDo(String id) {
    return Arrays.stream(allToDos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */

  public ToDo[] listToDos(Map<String, String[]> queryParams) {
    ToDo[] filteredToDos = allToDos;

    // Filter status if defined

    // Check if status is passed through
    if(queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status")[0];
      filteredToDos = filterToDosByStatus(filteredToDos, targetStatus);
    }

    // Check if limit is passed through
    if(queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredToDos = listByLimit(filteredToDos, targetLimit);
    }

    // Check if contains is passed through
    if(queryParams.containsKey("contains")) {
      String targetString = queryParams.get("contains")[0];
      filteredToDos = filterToDosByString(filteredToDos, targetString);
    }

    // Check if owner is passed through
    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredToDos = filterToDosByOwner(filteredToDos, targetOwner);
    }

    // Check if category is passed through
    if(queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredToDos = filterToDosByCategory(filteredToDos, targetCategory);
    }

    // Check if orderBy is passed through
    if(queryParams.containsKey("orderBy")) {
      String targetType = queryParams.get("orderBy")[0];
      filteredToDos = filterToDosByType(filteredToDos, targetType);
    }

    // Return filteredToDos based on given parameters. Works for multiple
    // arbitrary parameters.
    return filteredToDos;

  }



  /**
   * Get an array of all the todos having the target status.
   */

  public ToDo[] filterToDosByStatus(ToDo[] todos, String targetStatus) {

    // It works by entering true or false by itself, but we want to allow users to
    // enter the string complete. If they enter complete, we check all instances
    // of the boolean true in the JSON data and return those.
    if (targetStatus.equals("complete")) {
      return Arrays.stream(todos).filter(x -> x.status == true).toArray(ToDo[]::new);
    }

    // It works by entering true or false by itself, but we want to allow users to
    // enter the string incomplete. If they enter complete, we check all instances
    // of the boolean false in the JSON data and return those.
    if (targetStatus.equals("incomplete")) {
      return Arrays.stream(todos).filter(x -> x.status == false).toArray(ToDo[]::new);
    }

    // Return all todos if neither is specified.
    return todos;
  }

  /**
   * Get an array of all the todos having the target limit.
   */

  public ToDo[] listByLimit(ToDo[] todos, int targetLimit) {

    return Arrays.stream(todos).limit(targetLimit).toArray(ToDo[]::new);

  }

  /**
   * Get an array of all the todos having the target string.
   */

  public ToDo[] filterToDosByString(ToDo[] todos, String targetString) {

    return Arrays.stream(todos).filter(x -> x.body.contains(targetString)).toArray(ToDo[]::new);

  }

  /**
   * Get an array of all the todos having the target owner.
   */

  public ToDo[] filterToDosByOwner(ToDo[] todos, String targetOwner) {

    return Arrays.stream(todos).filter(x -> x.owner.contains(targetOwner)).toArray(ToDo[]::new);

  }

  /**
   * Get an array of all the todos having the target category.
   */

  public ToDo[] filterToDosByCategory(ToDo[] todos, String targetCategory) {

    return Arrays.stream(todos).filter(x -> x.category.contains(targetCategory)).toArray(ToDo[]::new);

  }

  /**
   * Get an array of all the todos sorted by the target type alphabetically.
   */

  public ToDo[] filterToDosByType(ToDo[] todos, String targetType) {

    // If owner is entered, sort owner names alphabetically.
    if(targetType.equals("owner")) {

      return Arrays.stream(todos).sorted((a,b)-> a.owner.compareTo(b.owner)).toArray(ToDo[]::new);

    }

    // If category is entered, sort them alphabetically.
    if(targetType.equals("category")) {

      return Arrays.stream(todos).sorted((a,b)-> a.category.compareTo(b.category)).toArray(ToDo[]::new);

    }

    // If body is entered, sort that latin stuff alphabetically.
    if(targetType.equals("body")) {

      return Arrays.stream(todos).sorted((a,b)-> a.body.compareTo(b.body)).toArray(ToDo[]::new);

    }

    // If status is entered, sort them alphabetically. They must be first converted from booleans
    // to strings because you cannot use the .sorted() method on booleans. This sorts them as false
    // first, then true next because f comes before t.
    if(targetType.equals("status")) {

      return Arrays.stream(todos).sorted((a,b)-> String.valueOf(a.status).compareTo(String.valueOf(b.status))).toArray(ToDo[]::new);

    }

    // Return all todos if all the above fail.
    return todos;

  }

}
