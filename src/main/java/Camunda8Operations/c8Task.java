package Camunda8Operations;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskSearch;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;

import java.util.HashMap;

public class c8Task {

    public TaskList getAllTask(String processInstanceKey) throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();
        //get tasks from a process instance (TaskSearch can take many more parameters)
        TaskSearch ts = new TaskSearch().setProcessInstanceId("2251799813693539");
        //Get all the TaskList
        TaskList tasksFromInstance = client.getTasks(ts);

        return tasksFromInstance;

    }

    public TaskList getGroupTasks(String groupName, String status) throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();

        //get tasks associated with group "toto"
        TaskList tasks = client.getGroupTasks(groupName, TaskState.valueOf(status), 1);
        return tasks;
    }

    public Task getTask(String TaskID) throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();

        //Task task = client.getTask(tasks.first().getId());

        Task task = client.getTask(TaskID);
        return task;
    }

    public Task claimTask(String TaskID, String userID) throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();

        // Task userTask = client.getTask(TaskID);
        //System.out.println(userTask.getTaskState());

        Task task = client.getTask(TaskID);
        client.unclaim(TaskID);
        task.setAssignee(userID);
        Task userTask = client.claim(TaskID, userID,true);


        return userTask;
    }

    public boolean completeTask(String taskToCompleteID) throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();

        Task userTask = client.completeTask(taskToCompleteID, new HashMap<>());
        return true;
    }
}
