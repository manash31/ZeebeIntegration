package org.example;

import Camunda8Operations.Camunda8Ops;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Camunda8Ops camunda8Ops = new Camunda8Ops();

        JSONObject inputObjectForProcess = new JSONObject();
        inputObjectForProcess.put("NAME", "Manash");
        camunda8Ops.createProcessinstance("Process_C8",inputObjectForProcess);
    }
}
