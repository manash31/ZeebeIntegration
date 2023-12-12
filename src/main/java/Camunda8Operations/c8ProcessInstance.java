package Camunda8Operations;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.json.JSONObject;

public class c8ProcessInstance {

    public long createProcessinstance(String BPDID, JSONObject jasonObject) {

        long pid = 0;
        final String defaultZeebeAddress = "localhost:26500";
        final ZeebeClientBuilder clientBuilder;
        clientBuilder = ZeebeClient.newClientBuilder().gatewayAddress(defaultZeebeAddress).usePlaintext();
        try(final ZeebeClient client = clientBuilder.build()) {

            final ProcessInstanceEvent processInstanceEvent = client.newCreateInstanceCommand().bpmnProcessId(BPDID).latestVersion().variables(jasonObject).send().get();
           pid = (int)processInstanceEvent.getProcessInstanceKey();
            System.out.println(pid+"");

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
        return pid;
    }

    public void experiment(){
        final String defaultZeebeAddress = "localhost:26500";
        final ZeebeClientBuilder clientBuilder;
        clientBuilder = ZeebeClient.newClientBuilder().gatewayAddress(defaultZeebeAddress).usePlaintext();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name","Gogo");
        long pid =  Long.parseLong("2251799813713700");


       //// CREATE PROCESS INSTANCE

        try(final ZeebeClient client = clientBuilder.build()) {
            ProcessInstanceEvent processInstanceEvent =
            client.newCreateInstanceCommand()
                    .bpmnProcessId("Process_C8")
                    .latestVersion()
                    .variables(jsonObject)
                    .send()
                    .get();
            System.out.println(processInstanceEvent.getProcessInstanceKey());
        }catch (Exception e){
        e.printStackTrace();
        }

        //// UPDATE PROCESS VARIABLE
        /*
        try(final ZeebeClient client = clientBuilder.build()){
            client.newSetVariablesCommand()
                    .variables("{\"name1\":\"Gogo1\"}")
                    .send().get();
        }
        catch (Exception e){}*/


        //// CANCEL PROCESS INSTANCE
        /*
        try(final ZeebeClient client = clientBuilder.build()){
            client.newCancelInstanceCommand(pid).send().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        */
        //// MESSAGE INTERMEDIATE CATCH EVENT
        /*
        try(final ZeebeClient client = clientBuilder.build()){
            client.newPublishMessageCommand().messageName("myMsgFromJava").correlationKey("123X").send().get();
        }catch (Exception e){
            e.printStackTrace();
        }
        */

        //// MESSAGE BROADCAST EVENT
/*
        try(final ZeebeClient client = clientBuilder.build()){
            client.newBroadcastSignalCommand().signalName("StartEvent_C8").send().get();
        }catch (Exception e){
            e.printStackTrace();
        }

*/
    }


                    /*client.newPublishMessageCommand().
                            messageName("MessagName").
                            correlationKey().
                            variables().
                            send().
                            join();*/
//                    client.newSetVariablesCommand(instanceKey).variables().send().
//                    client.newCreateInstanceCommand();
//                    client.newPublishMessageCommand();
//                    client.getConfiguration();
//                    client.newActivateJobsCommand();
//                    client.newCancelInstanceCommand();
//                    client.newCancelInstanceCommand();
//                    client.newBroadcastSignalCommand();
//                    client.newDeleteResourceCommand();
//                    client.newDeployResourceCommand();
//                    client.newEvaluateDecisionCommand();
//                    client.newModifyProcessInstanceCommand();
//                    client.newResolveIncidentCommand();
//                    client.newSetVariablesCommand();
//                    client.newTopologyRequest();
//                    client.newWorker();
//                    client.newCompleteCommand();
//                    client.


} // Class
