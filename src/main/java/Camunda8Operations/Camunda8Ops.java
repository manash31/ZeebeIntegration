package Camunda8Operations;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Camunda8Ops {


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


    //http://localhost:8081/swagger-ui/index.html
    //http://localhost:8082/swagger-ui/index.html
   public ResponseEntity<String> taskSearchBasedOnStateAndProcessInstanceKey(String ProcessInstanceKey){

   }
}

