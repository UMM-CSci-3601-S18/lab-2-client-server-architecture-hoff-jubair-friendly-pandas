package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import static umm3601.Util.buildFailJsonResponse;
import static umm3601.Util.buildSuccessJsonResponse;

/**
 * Controller that manages requests for info about todos.
 */
public class ToDoController {

  private final Gson gson;
  private ToDoDatabase database;

  /**
   * Construct a controller for todos.
   *
   * This loads the "database" of user info from a JSON file and
   * stores that internally so that (subsets of) todos can be returned
   * in response to requests.
   *
   * @param database the database containing todo data
   */
  public ToDoController(ToDoDatabase database) {
    gson = new Gson();
    this.database = database;
  }

  /**
   * Get the single user specified by the `id` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the user with that ID is found, a fail
   * JSON object if no todo with that ID is found
   */
  public JsonObject getToDo(Request req, Response res) {
    res.type("application/json");
    String id = req.params("id");
    ToDo todo = database.getToDo(id);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "ToDo with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

  /**
   * Get a JSON response with a list of all the todos in the "database".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the todos
   */
  public JsonObject getToDos(Request req, Response res) {
    res.type("application/json");
    ToDo[] todos = database.listToDos(req.queryMap().toMap());
    return buildSuccessJsonResponse("todos", gson.toJsonTree(todos));
  }

}
