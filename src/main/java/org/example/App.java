package org.example;

import Camunda8Operations.Camunda8Ops;
import Camunda8Operations.c8Task;
import io.camunda.operate.dto.ProcessInstance;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        System.out.println( "Hello World!" );
        try {
            c8Task c8task = new c8Task();
            TaskList taskList = c8task.getAllTask("2251799813696223");

            //TaskList tasks = c8task.getGroupTasks("demo", String.valueOf(TaskState.CREATED));
            //System.out.println(tasks.size());

            //TaskList tasks = c8task.getGroupTasks("Toto", String.valueOf(TaskState.CREATED));
            //System.out.println(tasks.size());
            //taskList.first();

            Task task = c8task.getTask(taskList.first().getId());
            System.out.println(taskList.size());
            for(Task currentTask: taskList)
                    System.out.println(currentTask.getId()+" "+currentTask.getTaskState());



            Task userTask = c8task.claimTask(task.getId(), "demo");

            boolean isTaskComplete = c8task.completeTask(userTask.getId());
        } catch (Exception e){
            e.printStackTrace();
        }



       /* Camunda8Ops camunda8Ops = new Camunda8Ops();

        JSONObject inputObjectForProcess = new JSONObject();
        inputObjectForProcess.put("NAME", "Manash");

        //CREATE INSTANCE
       // camunda8Ops.createProcessinstance("Process_C8",inputObjectForProcess);
        try {
            camunda8Ops.taskOperation();
        }catch(Exception e){
            e.printStackTrace();
        }
       //  camunda8Ops.taskSearchBasedOnStateAn("2251799813696864");*/

    }

}
