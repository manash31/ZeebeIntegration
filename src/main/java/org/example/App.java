package org.example;

import Camunda8Operations.Camunda8Ops;
import Camunda8Operations.c8ProcessInstance;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import workflow.config.DBAppConfig;
import workflow.entity.BOPMPROCESS;
import workflow.entity.Employee;
import workflow.entity.Person;
import workflow.service.impl.BOPMProcessDAO;
import workflow.service.impl.EmployeeDao;
import workflow.service.impl.PersonDAO;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
    public static void main( String[] args )  {

        //SpringApplication.run(App.class, args);


        //////////////////SEQUENTIAL CALLING FROM JAVA Code//////////////////

        long pid = 0;
        c8ProcessInstance c8processInstance = new c8ProcessInstance();
        c8Task c8task = new c8Task();
        ////////////////////////////
        try {
           c8processInstance.experiment();

        }catch (Exception e){

        }



   /*//////////////////DATABASE CALL//////////////////

    This is for sa
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBAppConfig.class);
        BOPMProcessDAO bopmProcessDAO = context.getBean(BOPMProcessDAO.class);

        BOPMPROCESS bopmprocess = new BOPMPROCESS(pid,"122T","Gogo");

        bopmProcessDAO.createBOPMProcessByID(bopmprocess);
        context.close();*/

/*
PersonDAO personDAO = context.getBean(PersonDAO.class);
        System.out.println("List of person is:");
        for (Person p : personDAO.getAllPersons()) {
            System.out.println(p);
        }

        System.out.println("\nGet person with ID 2");

        Person personById = personDAO.getPersonById(2L);
        System.out.println(personById);



        System.out.println("\nCreating person: ");
        Person person = new Person(4L, 36, "Sergey", "Emets");
        System.out.println(person);
        personDAO.createPerson(person);
        System.out.println("\nList of person is:");

        for (Person p : personDAO.getAllPersons()) {
            System.out.println(p);
        }
        System.out.println("\nDeleting person with ID 2");
        personDAO.deletePerson(personById);

        System.out.println("\nUpdate person with ID 4");

        Person pperson = personDAO.getPersonById(4L);
        pperson.setLastName("CHANGED");
        personDAO.updatePerson(pperson);

        System.out.println("\nList of person is:");
        for (Person p : personDAO.getAllPersons()) {
            System.out.println(p);
        }*/


      /*  System.out.println( "Hello World!" );

        ApplicationContext ctx=new ClassPathXmlApplication
        Context("applicationContext.xml");

        EmployeeDao dao=(EmployeeDao)ctx.getBean("edao");
        int status=dao.saveEmployee(new Employee(102,"Amit",3500));
        System.out.println(status);
*/
        /*************************
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

*************************/

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
