package Camunda8Operations;


import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskSearch;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Camunda8Ops {

    @Autowired
    RestTemplate restTemplate;
  //CREATEPROCESS INSTANCE
    public void createProcessinstance(String BPDID, JSONObject jasonObject) {

        final String defaultZeebeAddress = "localhost:26500";
        final ZeebeClientBuilder clientBuilder;
        clientBuilder =ZeebeClient.newClientBuilder().gatewayAddress(defaultZeebeAddress).usePlaintext();
        try(final ZeebeClient client = clientBuilder.build()) {

            final ProcessInstanceEvent processInstanceEvent = client.newCreateInstanceCommand().bpmnProcessId(BPDID).latestVersion().variables(jasonObject).send().get();
            System.out.println(processInstanceEvent.getProcessInstanceKey());

            /*JSONObject inputObjectForDMN = new JSONObject();
            inputObjectForDMN.put("NAME", "Manash");
            inputObjectForDMN.put("AGE", "45");
            System.out.println(inputObjectForDMN.toString());
            //final EvaluateDecisionResponse decisionResponse = client.newEvaluateDecisionCommand().decisionId("DEC_1").variables("{\"name\": \"Manash\"}").send().join();
            final EvaluateDecisionResponse decisionResponse = client.newEvaluateDecisionCommand().decisionId("DEC_1").variables(inputObjectForDMN.toString()).send().join();
            System.out.println(decisionResponse.getDecisionOutput());
            System.out.println(decisionResponse.getEvaluatedDecisions().get(0).getMatchedRules().size() + "");
            System.out.println(decisionResponse.getEvaluatedDecisions().get(0));*/
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProcessVariable(String processInstanceKey){
        try{
            final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().gatewayAddress("localhost:26500").usePlaintext();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","Gogo");

            try(final ZeebeClient zeebeClient = zeebeClientBuilder.build()){
                zeebeClient.newSetVariablesCommand(Long.parseLong(processInstanceKey)).variables(jsonObject.toString()).send().get();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void claimTask(){
        String url = "http://localhost:8082/v1/tasks/search";
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        restTemplate = new RestTemplate();

        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/json");
        headers.add("Content-Type","application/json");
        //headers.add("Authorization",getBasicAuthenticationHeader("Bearer", "FCE856D48AB40E14F33FB7CD1E2BE02F"));
        //headers.add("Authorization","Bearer 1736C73ED2F15BE921754C9A25DFA8EE");
        //headers.add("Authorization","Bearer 19D79390AF9D9358658F1D53827AE91C");
        headers.add("Authorization","Bearer 19D79390AF9D9358658F1D53827AE91C");
        JSONObject obj1 = new JSONObject();
        obj1.put("state","CREATED");
        //obj1.put("processInstanceKey","2251799813716616");

        HttpEntity<String> reqHttpEntity = new HttpEntity<>(obj1.toString(),headers);

        ResponseEntity<String> responseEntity;

        responseEntity = restTemplate.exchange(url, HttpMethod.POST,reqHttpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void taskOperation() throws TaskListException {
        SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");

        //shouldReturnVariables will change the default behaviour for the client to query variables along with tasks.
        CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();

        //get tasks from a process instance (TaskSearch can take many more parameters)
        TaskSearch ts = new TaskSearch().setProcessInstanceId("2251799813693539");
        //Get all the TaskList
        TaskList tasksFromInstance = client.getTasks(ts);

        //get tasks associated with group "toto"
       TaskList tasks = client.getGroupTasks("Toto",TaskState.CREATED, 1);
       System.out.println(tasks.first().getId());

        ////get a single task
        Task task = client.getTask(tasks.first().getId());
        System.out.println(task.getId());


        //get unassigned tasks
        //tasks = client.getTasks(false, null, null);
        for(Task task1 : tasks) {
            //assign task to paul
            client.claim(task1.getId(), "demo");
        }



        //Run Though the task and complete it
        for(Task task2 : tasks) {
            //complete task with variables
            client.completeTask(task2.getId(), new HashMap<>());

        }



        //TaskList tasksFromInstance = client.getTasks(ts);

    }


    //http://localhost:8081/swagger-ui/index.html
    //http://localhost:8082/swagger-ui/index.html
   public ResponseEntity<String> taskSearchBasedOnStateAn (String ProcessInstanceKey){
        String url = "http://localhost:8082/v1/tasks/search";
       HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
       restTemplate = new RestTemplate();

       restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);

       HttpHeaders headers = new HttpHeaders();
       headers.add("Accept","application/json");
       headers.add("Content-Type","application/json");
       //headers.add("Authorization",getBasicAuthenticationHeader("Bearer", "FCE856D48AB40E14F33FB7CD1E2BE02F"));
       headers.add("Authorization","Bearer 1736C73ED2F15BE921754C9A25DFA8EE");

       JSONObject obj1 = new JSONObject();
       obj1.put("state","CREATED");
       obj1.put("processInstanceKey","2251799813696864");

       HttpEntity<String> reqHttpEntity = new HttpEntity<>(obj1.toString(),headers);

       ResponseEntity<String> responseEntity;

       responseEntity = restTemplate.exchange(url, HttpMethod.POST,reqHttpEntity, String.class);
       System.out.println(responseEntity.getBody());
       return responseEntity;

   }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Bearer " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }





}

