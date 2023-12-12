package workflow.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class C8Worker {

    @JobWorker(type = "TestWorker", autoComplete = true)
    public void testWorker(final ActivatedJob job, JobClient client){
        System.out.println("I am in workers");

    }
}
