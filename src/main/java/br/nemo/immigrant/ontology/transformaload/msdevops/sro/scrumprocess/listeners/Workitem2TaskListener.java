
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.listeners;

import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.filters.Workitem2TaskFilter;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers.Workitem2TaskMapper;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services.ScrumDevelopmentTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Workitem2TaskListener {


    @Autowired
    private Workitem2TaskFilter filter;

    @Autowired
    private Workitem2TaskMapper mapper;

    @Autowired
    private ScrumDevelopmentTaskService service;


    @KafkaListener(topics = "application.msazuredevops.workitem", groupId = "workitem2task-group", concurrency = "2")
    public void consume(ConsumerRecord<String, String> payload) {

        try{

            log.info("Topic: {}", "application.msazuredevops.workitem");
            log.info("key: {}", payload.key());
            log.info("Headers: {}", payload.headers());
            log.info("Partion: {}", payload.partition());
            log.info("Data: {}", payload.value());
            log.info("Mapping");

            String data = payload.value();

            if (filter.isValid(data)){

                this.service.process(payload, mapper);
            }

        }catch (Exception e ){
            log.error(e.getMessage());
        }
    }
}
